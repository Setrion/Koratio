package net.setrion.koratio.world.entity.vehicle;

import com.google.common.collect.Lists;
import net.minecraft.BlockUtil;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.IntFunction;

public class Boat extends VehicleEntity implements Leashable, VariantHolder<Boat.Type>, net.neoforged.neoforge.common.extensions.IBoatExtension {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE_LEFT = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE_RIGHT = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> DATA_ID_BUBBLE_TIME = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);
	private final float[] paddlePositions = new float[2];
	private float invFriction;
	private float outOfControlTicks;
	private float deltaRotation;
	private int lerpSteps;
	private double lerpX;
	private double lerpY;
	private double lerpZ;
	private double lerpYRot;
	private double lerpXRot;
	private boolean inputLeft;
	private boolean inputRight;
	private boolean inputUp;
	private boolean inputDown;
	private double waterLevel;
	private float landFriction;
	private Boat.Status status;
	private Boat.Status oldStatus;
	private double lastYd;
	private boolean isAboveBubbleColumn;
	private boolean bubbleColumnDirectionIsDown;
	private float bubbleMultiplier;
	private float bubbleAngle;
	private float bubbleAngleO;
	@Nullable
	private Leashable.LeashData leashData;

	public Boat(EntityType<? extends Boat> type, Level level) {
		super(type, level);
		this.blocksBuilding = true;
	}

	public Boat(Level level, double x, double y, double z) {
		this(KoratioEntityType.BOAT.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	protected Entity.MovementEmission getMovementEmission() {
		return Entity.MovementEmission.EVENTS;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ID_TYPE, Boat.Type.PANGO.ordinal());
		builder.define(DATA_ID_PADDLE_LEFT, false);
		builder.define(DATA_ID_PADDLE_RIGHT, false);
		builder.define(DATA_ID_BUBBLE_TIME, 0);
	}

	public boolean canCollideWith(Entity other) {
		return canVehicleCollide(this, other);
	}

	public static boolean canVehicleCollide(Entity vehicle, Entity other) {
		return (other.canBeCollidedWith() || other.isPushable()) && !vehicle.isPassengerOfSameVehicle(other);
	}

	public boolean canBeCollidedWith() {
		return true;
	}

	public boolean isPushable() {
		return true;
	}

	public Vec3 getRelativePortalPosition(Direction.Axis axis, BlockUtil.FoundRectangle rect) {
	return LivingEntity.resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(axis, rect));
	}

	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!this.level().isClientSide && !this.isRemoved()) {
			this.setHurtDir(-this.getHurtDir());
			this.setHurtTime(10);
			this.setDamage(this.getDamage() + amount * 10.0F);
			this.markHurt();
			this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
			boolean flag = source.getEntity() instanceof Player && ((Player)source.getEntity()).getAbilities().instabuild;
			if (flag || this.getDamage() > 40.0F) {
				if (!flag && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
					this.destroy(source);
				}

				this.discard();
			}

			return true;
		} else {
			return true;
		}
	}

	protected void destroy(DamageSource source) {
		this.spawnAtLocation(this.getDropItem());
	}

	public void onAboveBubbleCol(boolean isDown) {
		if (!this.level().isClientSide()) {
			this.isAboveBubbleColumn = true;
			this.bubbleColumnDirectionIsDown = isDown;
			if (this.getBubbleTime() == 0) {
				this.setBubbleTime(60);
			}
		}

		this.level().addParticle(ParticleTypes.SPLASH, this.getX() + (double)this.random.nextFloat(), this.getY() + 0.7D, this.getZ() + (double)this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
		if (this.random.nextInt(20) == 0) {
			this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), this.getSwimSplashSound(), this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat(), false);
			this.gameEvent(GameEvent.SPLASH, this.getControllingPassenger());
		}
	}

	public void push(Entity entity) {
		if (entity instanceof Boat) {
			if (entity.getBoundingBox().minY < this.getBoundingBox().maxY) {
				super.push(entity);
			}
		} else if (entity.getBoundingBox().minY <= this.getBoundingBox().minY) {
			super.push(entity);
		}
	}

	public Item getDropItem() {
        return switch (this.getVariant()) {
            case VARESO -> KoratioItems.VARESO_BOAT.get();
            case RUGONA -> KoratioItems.RUGONA_BOAT.get();
            case ELVEN -> KoratioItems.ELVEN_BOAT.get();
            case BLUE_ELVEN -> KoratioItems.BLUE_ELVEN_BOAT.get();
            case CYAN_ELVEN -> KoratioItems.CYAN_ELVEN_BOAT.get();
            case GREEN_ELVEN -> KoratioItems.GREEN_ELVEN_BOAT.get();
            default -> KoratioItems.PANGO_BOAT.get();
        };
	}

	public boolean isPickable() {
		return !this.isRemoved();
	}

	@Override
	public void lerpTo(double pX, double pY, double pZ, float pYRot, float pXRot, int pSteps) {
		this.lerpX = pX;
		this.lerpY = pY;
		this.lerpZ = pZ;
		this.lerpYRot = pYRot;
		this.lerpXRot = pXRot;
		this.lerpSteps = 10;
	}

	@Override
	public double lerpTargetX() {
		return this.lerpSteps > 0 ? this.lerpX : this.getX();
	}

	@Override
	public double lerpTargetY() {
		return this.lerpSteps > 0 ? this.lerpY : this.getY();
	}

	@Override
	public double lerpTargetZ() {
		return this.lerpSteps > 0 ? this.lerpZ : this.getZ();
	}

	@Override
	public float lerpTargetXRot() {
		return this.lerpSteps > 0 ? (float)this.lerpXRot : this.getXRot();
	}

	@Override
	public float lerpTargetYRot() {
		return this.lerpSteps > 0 ? (float)this.lerpYRot : this.getYRot();
	}

	@Override
	public Direction getMotionDirection() {
		return this.getDirection().getClockWise();
	}

	public void tick() {		
		this.oldStatus = this.status;
		this.status = this.getStatus();
		if (this.status != Boat.Status.UNDER_WATER && this.status != Boat.Status.UNDER_FLOWING_WATER) {
			this.outOfControlTicks = 0.0F;
		} else {
			++this.outOfControlTicks;
		}

		if (!this.level().isClientSide() && this.outOfControlTicks >= 60.0F) {
			this.ejectPassengers();
		}

		if (this.getHurtTime() > 0) {
			this.setHurtTime(this.getHurtTime() - 1);
		}

		if (this.getDamage() > 0.0F) {
			this.setDamage(this.getDamage() - 1.0F);
		}

		super.tick();
		this.tickLerp();
		if (this.isControlledByLocalInstance()) {
			if (!(this.getFirstPassenger() instanceof Player)) {
				this.setPaddleState(false, false);
			}

			this.floatBoat();
			if (this.level().isClientSide()) {
				if (this.getFirstPassenger() instanceof LocalPlayer player) {
					Input input = player.input;
					this.inputLeft = input.left;
					this.inputRight = input.right;
					this.inputUp = input.up;
					this.inputDown = input.down;
				}
				this.controlBoat();
				this.level().sendPacketToServer(new ServerboundPaddleBoatPacket(this.getPaddleState(0), this.getPaddleState(1)));
			}

			this.move(MoverType.SELF, this.getDeltaMovement());
		} else {
			this.setDeltaMovement(Vec3.ZERO);
		}

		this.tickBubbleColumn();

		for(int i = 0; i <= 1; ++i) {
			if (this.getPaddleState(i)) {
				if (!this.isSilent() && (double)(this.paddlePositions[i] % ((float)Math.PI * 2F)) <= (double)((float)Math.PI / 4F) && (double)((this.paddlePositions[i] + ((float)Math.PI / 8F)) % ((float)Math.PI * 2F)) >= (double)((float)Math.PI / 4F)) {
					SoundEvent soundevent = this.getPaddleSound();
					if (soundevent != null) {
						Vec3 vec3 = this.getViewVector(1.0F);
						double d0 = i == 1 ? -vec3.z : vec3.z;
						double d1 = i == 1 ? vec3.x : -vec3.x;
						this.level().playSound(null, this.getX() + d0, this.getY(), this.getZ() + d1, soundevent, this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
					}
				}

				this.paddlePositions[i] += ((float)Math.PI / 8F);
			} else {
				this.paddlePositions[i] = 0.0F;
			}
		}

		this.checkInsideBlocks();
		List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
		if (!list.isEmpty()) {
			boolean flag = !this.level().isClientSide() && !(this.getControllingPassenger() instanceof Player);

			for(int j = 0; j < list.size(); ++j) {
				Entity entity = list.get(j);
				if (!entity.hasPassenger(this)) {
					if (flag && this.getPassengers().size() < this.getMaxPassengers() && !entity.isPassenger() && entity.getBbWidth() < this.getBbWidth() && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
						entity.startRiding(this);
					} else {
						this.push(entity);
					}
				}
			}
		}
	}

	private void tickBubbleColumn() {
		if (this.level().isClientSide()) {
			int i = this.getBubbleTime();
			if (i > 0) {
				this.bubbleMultiplier += 0.05F;
			} else {
				this.bubbleMultiplier -= 0.1F;
			}

			this.bubbleMultiplier = Mth.clamp(this.bubbleMultiplier, 0.0F, 1.0F);
			this.bubbleAngleO = this.bubbleAngle;
			this.bubbleAngle = 10.0F * (float)Math.sin(0.5F * (float)this.level().getGameTime()) * this.bubbleMultiplier;
		} else {
			if (!this.isAboveBubbleColumn) {
				this.setBubbleTime(0);
			}

			int k = this.getBubbleTime();
			if (k > 0) {
				--k;
				this.setBubbleTime(k);
				int j = 60 - k - 1;
				if (j > 0 && k == 0) {
					this.setBubbleTime(0);
					Vec3 vec3 = this.getDeltaMovement();
					if (this.bubbleColumnDirectionIsDown) {
						this.setDeltaMovement(vec3.add(0.0D, -0.7D, 0.0D));
						this.ejectPassengers();
					} else {
						this.setDeltaMovement(vec3.x, this.hasPassenger((p_150274_) -> {
							return p_150274_ instanceof Player;
						}) ? 2.7D : 0.6D, vec3.z);
					}
				}

				this.isAboveBubbleColumn = false;
			}
		}
	}

	@Nullable
	protected SoundEvent getPaddleSound() {
		switch (this.getStatus()) {
		case IN_WATER:
		case UNDER_WATER:
		case UNDER_FLOWING_WATER:
			return SoundEvents.BOAT_PADDLE_WATER;
		case ON_LAND:
			return SoundEvents.BOAT_PADDLE_LAND;
		case IN_AIR:
		default:
			return null;
		}
	}

	private void tickLerp() {
		if (this.isControlledByLocalInstance()) {
			this.lerpSteps = 0;
			this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
		}

		if (this.lerpSteps > 0) {
			double d0 = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
			double d3 = Mth.wrapDegrees(this.lerpYRot - (double)this.getYRot());
			this.setYRot(this.getYRot() + (float)d3 / (float)this.lerpSteps);
			this.setXRot(this.getXRot() + (float)(this.lerpXRot - (double)this.getXRot()) / (float)this.lerpSteps);
			--this.lerpSteps;
			this.setPos(d0, d1, d2);
			this.setRot(this.getYRot(), this.getXRot());
		}
	}

	public void setPaddleState(boolean p_38340_, boolean p_38341_) {
		this.entityData.set(DATA_ID_PADDLE_LEFT, p_38340_);
		this.entityData.set(DATA_ID_PADDLE_RIGHT, p_38341_);
	}

	public float getRowingTime(int p_38316_, float p_38317_) {
		return this.getPaddleState(p_38316_) ? Mth.clampedLerp(this.paddlePositions[p_38316_] - ((float)Math.PI / 8F), this.paddlePositions[p_38316_], p_38317_) : 0.0F;
	}

	@Nullable
	@Override
	public Leashable.LeashData getLeashData() {
		return this.leashData;
	}

	@Override
	public void setLeashData(@Nullable Leashable.LeashData pLeashData) {
		this.leashData = pLeashData;
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0.0, 0.88F * this.getEyeHeight(), this.getBbWidth() * 0.64F);
	}

	@Override
	public void elasticRangeLeashBehaviour(Entity pLeashHolder, float pDistance) {
		Vec3 vec3 = pLeashHolder.position().subtract(this.position()).normalize().scale((double)pDistance - 6.0);
		Vec3 vec31 = this.getDeltaMovement();
		boolean flag = vec31.dot(vec3) > 0.0;
		this.setDeltaMovement(vec31.add(vec3.scale(flag ? 0.15F : 0.2F)));
	}

	private Boat.Status getStatus() {
		Boat.Status boat$status = this.isUnderwater();
		if (boat$status != null) {
			this.waterLevel = this.getBoundingBox().maxY;
			return boat$status;
		} else if (this.checkInWater()) {
			return Boat.Status.IN_WATER;
		} else {
			float f = this.getGroundFriction();
			if (f > 0.0F) {
				this.landFriction = f;
				return Boat.Status.ON_LAND;
			} else {
				return Boat.Status.IN_AIR;
			}
		}
	}

	public float getWaterLevelAbove() {
		AABB aabb = this.getBoundingBox();
		int i = Mth.floor(aabb.minX);
		int j = Mth.ceil(aabb.maxX);
		int k = Mth.floor(aabb.maxY);
		int l = Mth.ceil(aabb.maxY - this.lastYd);
		int i1 = Mth.floor(aabb.minZ);
		int j1 = Mth.ceil(aabb.maxZ);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
	
		label39:
		for(int k1 = k; k1 < l; ++k1) {
			float f = 0.0F;

			for(int l1 = i; l1 < j; ++l1) {
				for(int i2 = i1; i2 < j1; ++i2) {
					blockpos$mutableblockpos.set(l1, k1, i2);
					FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
					f = Math.max(f, fluidstate.getHeight(this.level(), blockpos$mutableblockpos));

					if (f >= 1.0F) {
						continue label39;
					}
				}
			}

			if (f < 1.0F) {
				return (float)blockpos$mutableblockpos.getY() + f;
			}
		}
		return (float)(l + 1);
	}

	public float getGroundFriction() {
		AABB aabb = this.getBoundingBox();
		AABB aabb1 = new AABB(aabb.minX, aabb.minY - 0.001D, aabb.minZ, aabb.maxX, aabb.minY, aabb.maxZ);
		int i = Mth.floor(aabb1.minX) - 1;
		int j = Mth.ceil(aabb1.maxX) + 1;
		int k = Mth.floor(aabb1.minY) - 1;
		int l = Mth.ceil(aabb1.maxY) + 1;
		int i1 = Mth.floor(aabb1.minZ) - 1;
		int j1 = Mth.ceil(aabb1.maxZ) + 1;
		VoxelShape voxelshape = Shapes.create(aabb1);
		float f = 0.0F;
		int k1 = 0;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int l1 = i; l1 < j; ++l1) {
			for(int i2 = i1; i2 < j1; ++i2) {
				int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
				if (j2 != 2) {
					for(int k2 = k; k2 < l; ++k2) {
						if (j2 <= 0 || k2 != k && k2 != l - 1) {
							blockpos$mutableblockpos.set(l1, k2, i2);
							BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
							if (!(blockstate.getBlock() instanceof WaterlilyBlock) && Shapes.joinIsNotEmpty(blockstate.getCollisionShape(this.level(), blockpos$mutableblockpos).move(l1, k2, i2), voxelshape, BooleanOp.AND)) {
								f += blockstate.getFriction(this.level(), blockpos$mutableblockpos, this);
								++k1;
							}
						}
					}
				}
			}
		}
		
		return f / (float)k1;
	}

	private boolean checkInWater() {
		AABB aabb = this.getBoundingBox();
		int i = Mth.floor(aabb.minX);
		int j = Mth.ceil(aabb.maxX);
		int k = Mth.floor(aabb.minY);
		int l = Mth.ceil(aabb.minY + 0.001D);
		int i1 = Mth.floor(aabb.minZ);
		int j1 = Mth.ceil(aabb.maxZ);
		boolean flag = false;
		this.waterLevel = -Double.MAX_VALUE;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int k1 = i; k1 < j; ++k1) {
			for(int l1 = k; l1 < l; ++l1) {
				for(int i2 = i1; i2 < j1; ++i2) {
					blockpos$mutableblockpos.set(k1, l1, i2);
					FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
					if (this.isInWater()) {
						float f = (float)l1 + fluidstate.getHeight(this.level(), blockpos$mutableblockpos);
						this.waterLevel = Math.max(f, this.waterLevel);
						flag |= aabb.minY < (double)f;
					}
				}
			}
		}

		return flag;
	}

	@Nullable
	private Boat.Status isUnderwater() {
		AABB aabb = this.getBoundingBox();
		double d0 = aabb.maxY + 0.001D;
		int i = Mth.floor(aabb.minX);
		int j = Mth.ceil(aabb.maxX);
		int k = Mth.floor(aabb.maxY);
		int l = Mth.ceil(d0);
		int i1 = Mth.floor(aabb.minZ);
		int j1 = Mth.ceil(aabb.maxZ);
		boolean flag = false;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int k1 = i; k1 < j; ++k1) {
			for(int l1 = k; l1 < l; ++l1) {
				for(int i2 = i1; i2 < j1; ++i2) {
					blockpos$mutableblockpos.set(k1, l1, i2);
					FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
					if (this.isInWater() && d0 < (double)((float)blockpos$mutableblockpos.getY() + fluidstate.getHeight(this.level(), blockpos$mutableblockpos))) {
						if (!fluidstate.isSource()) {
							return Boat.Status.UNDER_FLOWING_WATER;
						}

						flag = true;
					}
				}
			}
		}

		return flag ? Boat.Status.UNDER_WATER : null;
	}

	private void floatBoat() {
		double d1 = this.isNoGravity() ? 0.0D : (double)-0.04F;
		double d2 = 0.0D;
		this.invFriction = 0.05F;
		if (this.oldStatus == Boat.Status.IN_AIR && this.status != Boat.Status.IN_AIR && this.status != Boat.Status.ON_LAND) {
			this.waterLevel = this.getY(1.0D);
			this.setPos(this.getX(), (double)(this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
			this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
			this.lastYd = 0.0D;
			this.status = Boat.Status.IN_WATER;
		} else {
			if (this.status == Boat.Status.IN_WATER) {
				d2 = (this.waterLevel - this.getY()) / (double)this.getBbHeight();
				this.invFriction = 0.9F;
			} else if (this.status == Boat.Status.UNDER_FLOWING_WATER) {
				d1 = -7.0E-4D;
				this.invFriction = 0.9F;
			} else if (this.status == Boat.Status.UNDER_WATER) {
				d2 = 0.01F;
				this.invFriction = 0.45F;
			} else if (this.status == Boat.Status.IN_AIR) {
				this.invFriction = 0.9F;
			} else if (this.status == Boat.Status.ON_LAND) {
				this.invFriction = this.landFriction;
				if (this.getControllingPassenger() instanceof Player) {
					this.landFriction /= 2.0F;
				}
			}

			Vec3 vec3 = this.getDeltaMovement();
			this.setDeltaMovement(vec3.x * (double)this.invFriction, vec3.y + d1, vec3.z * (double)this.invFriction);
			this.deltaRotation *= this.invFriction;
			if (d2 > 0.0D) {
				Vec3 vec31 = this.getDeltaMovement();
				this.setDeltaMovement(vec31.x, (vec31.y + d2 * 0.06153846016296973D) * 0.75D, vec31.z);
			}
		}
	}

	private void controlBoat() {
		if (this.isVehicle()) {
			
			float f = 0.0F;
			if (this.inputLeft) {
				--this.deltaRotation;
			}

			if (this.inputRight) {
				++this.deltaRotation;
			}

			if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
				f += 0.005F;
			}

			this.setYRot(this.getYRot() + this.deltaRotation);
			if (this.inputUp) {
				f += 0.04F;
			}

			if (this.inputDown) {
				f -= 0.005F;
			}

			this.setDeltaMovement(this.getDeltaMovement().add(Mth.sin(-this.getYRot() * ((float)Math.PI / 180F)) * f, 0.0D, Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * f));
			this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
		}
	}

	@Override
	protected void positionRider(Entity passenger, Entity.MoveFunction callback) {
		super.positionRider(passenger, callback);
		if (!passenger.getType().is(EntityTypeTags.CAN_TURN_IN_BOATS)) {
			passenger.setYRot(passenger.getYRot() + this.deltaRotation);
			passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
			this.clampRotation(passenger);
			if (passenger instanceof Animal && this.getPassengers().size() == this.getMaxPassengers()) {
				int i = passenger.getId() % 2 == 0 ? 90 : 270;
				passenger.setYBodyRot(((Animal)passenger).yBodyRot + (float)i);
				passenger.setYHeadRot(passenger.getYHeadRot() + (float)i);
			}
		}
	}

	public Vec3 getDismountLocationForPassenger(LivingEntity entity) {
		Vec3 vec3 = getCollisionHorizontalEscapeVector(this.getBbWidth() * Mth.SQRT_OF_TWO, entity.getBbWidth(), entity.getYRot());
		double d0 = this.getX() + vec3.x;
		double d1 = this.getZ() + vec3.z;
		BlockPos blockpos = new BlockPos((int) d0, (int) this.getBoundingBox().maxY, (int) d1);
		BlockPos blockpos1 = blockpos.below();
		if (!this.level().isWaterAt(blockpos1)) {
			List<Vec3> list = Lists.newArrayList();
			double d2 = this.level().getBlockFloorHeight(blockpos);
			if (DismountHelper.isBlockFloorValid(d2)) {
				list.add(new Vec3(d0, (double)blockpos.getY() + d2, d1));
			}

			double d3 = this.level().getBlockFloorHeight(blockpos1);
			if (DismountHelper.isBlockFloorValid(d3)) {
				list.add(new Vec3(d0, (double)blockpos1.getY() + d3, d1));
			}

			for(Pose pose : entity.getDismountPoses()) {
				for(Vec3 vec31 : list) {
					if (DismountHelper.canDismountTo(this.level(), vec31, entity, pose)) {
						entity.setPose(pose);
						return vec31;
					}
				}
			}
		}

		return super.getDismountLocationForPassenger(entity);
	}

	protected void clampRotation(Entity entity) {
		entity.setYBodyRot(this.getYRot());
		float f = Mth.wrapDegrees(entity.getYRot() - this.getYRot());
		float f1 = Mth.clamp(f, -105.0F, 105.0F);
		entity.yRotO += f1 - f;
		entity.setYRot(entity.getYRot() + f1 - f);
		entity.setYHeadRot(entity.getYRot());
	}

	public void onPassengerTurned(Entity entity) {
		this.clampRotation(entity);
	}

	protected void addAdditionalSaveData(CompoundTag tag) {
		tag.putString("Type", this.getVariant().getName());
	}

	protected void readAdditionalSaveData(CompoundTag tag) {
		if (tag.contains("Type", 8)) {
			this.setVariant(Boat.Type.byName(tag.getString("Type")));
		}
	}

	@Override
	public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
		InteractionResult interactionresult = super.interact(pPlayer, pHand);
		if (interactionresult != InteractionResult.PASS) {
			return interactionresult;
		} else if (pPlayer.isSecondaryUseActive()) {
			return InteractionResult.PASS;
		} else if (this.outOfControlTicks < 60.0F) {
			if (!this.level().isClientSide) {
				return pPlayer.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
			} else {
				return InteractionResult.SUCCESS;
			}
		} else {
			return InteractionResult.PASS;
		}
	}

	protected void checkFallDamage(double distance, boolean flying, BlockState state, BlockPos pos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (flying) {
				if (this.fallDistance > 3.0F) {
					if (this.status != Boat.Status.ON_LAND) {
						this.resetFallDistance();
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F, damageSources().fall());
					if (!this.level().isClientSide() && !this.isRemoved()) {
						this.kill();
						if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							for(int i = 0; i < 3; ++i) {
								this.spawnAtLocation(this.getVariant().getPlanks());
							}

							for(int j = 0; j < 2; ++j) {
								this.spawnAtLocation(Items.STICK);
							}
						}
					}
				}

				this.resetFallDistance();
			} else if (!this.isInWater() && distance < 0.0D) {
				this.fallDistance -= (float)distance;
			}
		}
	}

	public boolean getPaddleState(int state) {
		return this.entityData.<Boolean>get(state == 0 ? DATA_ID_PADDLE_LEFT : DATA_ID_PADDLE_RIGHT) && this.getControllingPassenger() != null;
	}

	public void setDamage(float damage) {
		this.entityData.set(DATA_ID_DAMAGE, damage);
	}

	public float getDamage() {
		return this.entityData.get(DATA_ID_DAMAGE);
	}

	public void setHurtTime(int i) {
		this.entityData.set(DATA_ID_HURT, i);
	}

	public int getHurtTime() {
		return this.entityData.get(DATA_ID_HURT);
	}

	private void setBubbleTime(int i) {
		this.entityData.set(DATA_ID_BUBBLE_TIME, i);
	}

	private int getBubbleTime() {
		return this.entityData.get(DATA_ID_BUBBLE_TIME);
	}

	public float getBubbleAngle(float angle) {
		return Mth.lerp(angle, this.bubbleAngleO, this.bubbleAngle);
	}

	public void setHurtDir(int i) {
		this.entityData.set(DATA_ID_HURTDIR, i);
	}

	public int getHurtDir() {
		return this.entityData.get(DATA_ID_HURTDIR);
	}

	public void setVariant(Boat.Type pVariant) {
		this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
	}

	public Boat.Type getVariant() {
		return Boat.Type.byId(this.entityData.get(DATA_ID_TYPE));
	}

	protected boolean canAddPassenger(Entity passenger) {
		return this.getPassengers().size() < this.getMaxPassengers();
	}

	protected int getMaxPassengers() {
		return 2;
	}

	@Nullable
	public LivingEntity getControllingPassenger() {
		return (LivingEntity) this.getFirstPassenger();
	}

	public boolean isUnderWater() {
		return this.status == Boat.Status.UNDER_WATER || this.status == Boat.Status.UNDER_FLOWING_WATER;
	}

	// Forge: Fix MC-119811 by instantly completing lerp on board
	@Override
	protected void addPassenger(Entity passenger) {
		super.addPassenger(passenger);
		if (this.isControlledByLocalInstance() && this.lerpSteps > 0) {
			this.lerpSteps = 0;
			this.absMoveTo(this.lerpX, this.lerpY, this.lerpZ, (float)this.lerpYRot, (float)this.lerpXRot);
		}
	}

	public ItemStack getPickResult() {
		return new ItemStack(this.getDropItem());
	}

	public enum Status {
		IN_WATER,
		UNDER_WATER,
		UNDER_FLOWING_WATER,
		ON_LAND,
		IN_AIR
    }

	@net.neoforged.fml.common.asm.enumextension.NamedEnum(1)
	@net.neoforged.fml.common.asm.enumextension.NetworkedEnum(net.neoforged.fml.common.asm.enumextension.NetworkedEnum.NetworkCheck.CLIENTBOUND)
	public enum Type implements StringRepresentable {
		PANGO(KoratioBlocks.PANGO_PLANKS.get(), "pango"),
		RUGONA(KoratioBlocks.RUGONA_PLANKS.get(), "rugona"),
		VARESO(KoratioBlocks.VARESO_PLANKS.get(), "vareso"),
		CANDY(KoratioBlocks.CANDY_PLANKS.get(), "candy"),
		CHOCOLATE_OAK(KoratioBlocks.CHOCOLATE_OAK_PLANKS.get(), "chocolate_oak"),
		ELVEN(KoratioBlocks.ELVEN_PLANKS.get(), "elven"),
		BLUE_ELVEN(KoratioBlocks.BLUE_ELVEN_PLANKS.get(), "blue_elven"),
		CYAN_ELVEN(KoratioBlocks.CYAN_ELVEN_PLANKS.get(), "cyan_elven"),
		GREEN_ELVEN(KoratioBlocks.GREEN_ELVEN_PLANKS.get(), "green_elven");

		private final String name;
		private final java.util.function.Supplier<Block> planksSupplier;
		public static final StringRepresentable.EnumCodec<Boat.Type> CODEC = StringRepresentable.fromEnum(Boat.Type::values);
		private static final IntFunction<Boat.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

		@net.neoforged.fml.common.asm.enumextension.ReservedConstructor
        Type(Block pPlanks, String pName) {
			this.name = pName;
			this.planksSupplier = () -> pPlanks;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

		public Block getPlanks() {
			return this.planksSupplier.get();
		}

		@Override
		public String toString() {
			return this.name;
		}

		/**
		 * Get a boat type by its enum ordinal
		 */
		public static Boat.Type byId(int pId) {
			return BY_ID.apply(pId);
		}

		public static Boat.Type byName(String pName) {
			return CODEC.byName(pName, PANGO);
		}
	}
}