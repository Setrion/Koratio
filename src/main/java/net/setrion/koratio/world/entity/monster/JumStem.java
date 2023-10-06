package net.setrion.koratio.world.entity.monster;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.entity.projectile.MushroomSpore;

public class JumStem extends Monster implements net.minecraftforge.common.IForgeShearable {
	
	private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(JumStem.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> DATA_SHROOMS = SynchedEntityData.defineId(JumStem.class, EntityDataSerializers.INT);
	
	public float targetSquish;
	public float squish;
	public float oSquish;
	private boolean wasOnGround;
	private int cloud;

	public JumStem(EntityType<? extends JumStem> type, Level level) {
		super(type, level);
		this.moveControl = new JumStem.JumStemMoveControl(this);
		this.entityData.define(DATA_VARIANT, 0);
		this.entityData.define(DATA_SHROOMS, 0);
	}
	
	@Override
	protected void checkFallDamage(double fallDistance, boolean bool, BlockState state, BlockPos pos) {
		if (this.getVariant() == JumStem.Variant.SHEARED || (this.getVariant().getBlock() == level().getBlockState(pos.above()).getBlock() && this.getMushroomAmount() < 3)) {
			if (this.fallDistance >= 1.5) {
				if (this.getVariant().getBlock() != level().getBlockState(pos.above()).getBlock()) {
					this.setVariant(JumStem.Variant.byBlock(level().getBlockState(pos.above()).getBlock()));
				} else {
					this.setMushroomAmount(this.getMushroomAmount()+1);
				}
				
				//level.playSound(null, pos, SoundEvents.SOUL_ESCAPE, SoundSource.HOSTILE, 1.0F, 1.0F);
				level().removeBlock(pos.above(), true);
			}
		}
		super.checkFallDamage(fallDistance, bool, state, pos);
	}
	
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new JumStem.JumStemAttackGoal(this));
		this.goalSelector.addGoal(2, new JumStem.JumStemRandomDirectionGoal(this));
		this.goalSelector.addGoal(4, new JumStem.JumStemKeepOnJumpingGoal(this));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (target) -> {
			return Math.abs(target.getY() - this.getY()) <= 4.0D;
		}));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("wasOnGround", this.wasOnGround);
		tag.putInt("Variant", this.getVariant().getId());
		tag.putInt("Mushrooms", this.getMushroomAmount());
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.wasOnGround = tag.getBoolean("wasOnGround");
		this.setVariant(JumStem.Variant.BY_ID[tag.getInt("Variant")]);
		this.setMushroomAmount(tag.getInt("Mushrooms"));
	}

	public JumStem.Variant getVariant() {
		return JumStem.Variant.BY_ID[this.entityData.get(DATA_VARIANT)];
	}

	private void setVariant(JumStem.Variant variant) {
		this.entityData.set(DATA_VARIANT, variant.getId());
	}
	
	public int getMushroomAmount() {
		return this.entityData.get(DATA_SHROOMS);
	}

	private void setMushroomAmount(int amount) {
		this.entityData.set(DATA_SHROOMS, amount);
	}
	
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType type, SpawnGroupData groupData, CompoundTag tag) {
		this.setVariant(JumStem.Variant.BY_ID[level.getRandom().nextIntBetweenInclusive(1, 4)]);
		this.setMushroomAmount(level.getRandom().nextInt(4));
		return super.finalizeSpawn(level, difficulty, type, groupData, tag);
	}
	
	public void tick() {
		if (this.hasEffect(this.getVariant().getEffect())) {
			this.removeEffect(this.getVariant().getEffect());
		}
		this.squish += (this.targetSquish - this.squish) * 0.5F;
		this.oSquish = this.squish;
		super.tick();
		if (this.onGround() && !this.wasOnGround) {
			this.targetSquish = -0.5F;
		} else if (!this.onGround() && this.wasOnGround) {
			this.targetSquish = 1.0F;
		}

		this.wasOnGround = this.onGround();
		this.decreaseSquish();
		--cloud;
	}

	protected void decreaseSquish() {
		this.targetSquish *= 0.6F;
	}

	protected int getJumpDelay() {
		return this.random.nextInt(20) + 10;
	}
	
	public void refreshDimensions() {
		double d0 = this.getX();
		double d1 = this.getY();
		double d2 = this.getZ();
		super.refreshDimensions();
		this.setPos(d0, d1, d2);
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		Entity s = source.getDirectEntity();
		if (--cloud <= 0 && this.getVariant() != JumStem.Variant.SHEARED && (s instanceof LivingEntity || source.is(DamageTypeTags.IS_PROJECTILE))) {
			AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
			areaeffectcloud.setRadius(2.5F);
			areaeffectcloud.setRadiusOnUse(-0.5F);
			areaeffectcloud.setWaitTime(10);
			areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
			areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
			areaeffectcloud.addEffect(new MobEffectInstance(this.getVariant().getEffect(), 100, this.getMushroomAmount()));
		
			this.level().addFreshEntity(areaeffectcloud);
			cloud = 200;
		}
		return super.hurt(source, amount);
	}
	
	static class JumStemKeepOnJumpingGoal extends Goal {
		private final JumStem jumstem;
	
		public JumStemKeepOnJumpingGoal(JumStem jumstem) {
			this.jumstem = jumstem;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}
	
		public boolean canUse() {
			return !this.jumstem.isPassenger();
		}
	
		public void tick() {
			MoveControl movecontrol = this.jumstem.getMoveControl();
			if (movecontrol instanceof JumStem.JumStemMoveControl jumstem$jumstemmovecontrol) {
				jumstem$jumstemmovecontrol.setWantedMovement(1.0D);
			}
		}
	}
	
	static class JumStemMoveControl extends MoveControl {
		private float yRot;
		private int jumpDelay;
		private final JumStem jumstem;
		private boolean isAggressive;
	
		public JumStemMoveControl(JumStem jumstem) {
			super(jumstem);
			this.jumstem = jumstem;
			this.yRot = 180.0F * jumstem.getYRot() / (float)Math.PI;
		}
	
		public void setDirection(float yRot, boolean isAggressive) {
			this.yRot = yRot;
			this.isAggressive = isAggressive;
		}
	
		public void setWantedMovement(double speed) {
			this.speedModifier = speed;
			this.operation = MoveControl.Operation.MOVE_TO;
		}
	
		public void tick() {
			this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
			this.mob.yHeadRot = this.mob.getYRot();
			this.mob.yBodyRot = this.mob.getYRot();
			if (this.operation != MoveControl.Operation.MOVE_TO) {
				this.mob.setZza(0.0F);
			} else {
				this.operation = MoveControl.Operation.WAIT;
				if (this.mob.onGround()) {
					this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
					if (this.jumpDelay-- <= 0) {
						this.jumpDelay = this.jumstem.getJumpDelay();
						if (this.isAggressive) {
							this.jumpDelay /= 3;
						}
	
						this.jumstem.getJumpControl().jump();
					} else {
						this.jumstem.xxa = 0.0F;
						this.jumstem.zza = 0.0F;
						this.mob.setSpeed(0.0F);
					}
				} else {
					this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
				}
			}
		}
	}
	
	static class JumStemAttackGoal extends Goal {
		private final JumStem jumstem;
		private int growTiredTimer, cooldown;
	
		public JumStemAttackGoal(JumStem jumstem) {
			this.jumstem = jumstem;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}
	
		public boolean canUse() {
			LivingEntity livingentity = this.jumstem.getTarget();
			if (livingentity == null) {
				return false;
			} else {
				return !this.jumstem.canAttack(livingentity) ? false : this.jumstem.getMoveControl() instanceof JumStem.JumStemMoveControl;
			}
		}
	
		public void start() {
			this.growTiredTimer = reducedTickDelay(300);
			super.start();
		}
	
		public boolean canContinueToUse() {
			LivingEntity livingentity = this.jumstem.getTarget();
			if (livingentity == null) {
				return false;
			} else if (--cooldown > 0) {
				return false;
			} else if (!this.jumstem.canAttack(livingentity)) {
				return false;
			} else {
				return --this.growTiredTimer > 0;
			}
		}
	
		public boolean requiresUpdateEveryTick() {
			return true;
		}
	
		public void tick() {
			LivingEntity target = this.jumstem.getTarget();
			if (target != null) {
				this.jumstem.lookAt(target, 10.0F, 10.0F);
			}
	
			MoveControl movecontrol = this.jumstem.getMoveControl();
			if (movecontrol instanceof JumStem.JumStemMoveControl slime$slimemovecontrol) {
				if (target.distanceTo(jumstem) < 10.0F && !(cooldown > 0) && jumstem.onGround()) {
					this.jumstem.lookAt(target, 10.0F, 10.0F);
					MushroomSpore spore = new MushroomSpore(jumstem.level(), jumstem);
					double d0 = target.getX() - jumstem.getX();
					double d1 = target.getY(0.3333333333333333D) - spore.getY();
					double d2 = target.getZ() - jumstem.getZ();
					double d3 = Math.sqrt(d0 * d0 + d2 * d2);
					spore.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - jumstem.level().getDifficulty().getId() * 4));
					jumstem.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (jumstem.getRandom().nextFloat() * 0.4F + 0.8F));
					jumstem.level().addFreshEntity(spore);
					this.cooldown = 30;
				}
			}
		}
	}

	static class JumStemRandomDirectionGoal extends Goal {
		private final JumStem jumstem;
		private float chosenDegrees;
		private int nextRandomizeTime;
	
		public JumStemRandomDirectionGoal(JumStem jumstem) {
			this.jumstem = jumstem;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}
	
		public boolean canUse() {
			return this.jumstem.getTarget() == null && (this.jumstem.onGround() || this.jumstem.isInWater() || this.jumstem.isInLava() || this.jumstem.hasEffect(MobEffects.LEVITATION)) && this.jumstem.getMoveControl() instanceof JumStem.JumStemMoveControl;
		}
	
		public void tick() {
			if (--this.nextRandomizeTime <= 0) {
				this.nextRandomizeTime = this.adjustedTickDelay(40 + this.jumstem.getRandom().nextInt(60));
				this.chosenDegrees = (float)this.jumstem.getRandom().nextInt(360);
			}
	
			MoveControl movecontrol = this.jumstem.getMoveControl();
			if (movecontrol instanceof JumStem.JumStemMoveControl jumstem$jumstemmovecontrol) {
				jumstem$jumstemmovecontrol.setDirection(this.chosenDegrees, false);
			}
		}
	}
	
	@Override
	public java.util.List<ItemStack> onSheared(@org.jetbrains.annotations.Nullable Player player, @org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
		this.gameEvent(GameEvent.SHEAR, player);
		return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
	}

	public void shear(SoundSource source) {
		shearInternal(source).forEach(s -> this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(1.0D), this.getZ(), s)));
	}

	private java.util.List<ItemStack> shearInternal(SoundSource source) {
		this.level().playSound((Player)null, this, SoundEvents.MOOSHROOM_SHEAR, source, 1.0F, 1.0F);
		if (!this.level().isClientSide()) {
			((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for(int i = 0; i < this.getMushroomAmount(); ++i) {
				items.add(new ItemStack(this.getVariant().getBlock()));
			}
			this.setVariant(Variant.SHEARED);
			this.setMushroomAmount(0);
			return items;
		}
		return java.util.Collections.emptyList();
	}

	public boolean readyForShearing() {
		return this.isAlive() && !this.isBaby() && this.getMushroomAmount() > 0 && this.getVariant() != JumStem.Variant.SHEARED;
	}
	
	@Override
	public boolean isShearable(@org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos) {
		return readyForShearing();
	}
	
	public static enum Variant {
		SHEARED(0, "sheared", Blocks.AIR, null),
		RED(1, "red", Blocks.RED_MUSHROOM, MobEffects.BLINDNESS),
		BROWN(2, "brown", Blocks.BROWN_MUSHROOM, MobEffects.HUNGER),
		PURPLE(3, "purple", KoratioBlocks.PURPLE_MUSHROOM.get(), MobEffects.CONFUSION),
		GREEN(4, "green", KoratioBlocks.GREEN_MUSHROOM.get(), MobEffects.POISON);
		
		public static final JumStem.Variant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(JumStem.Variant::getId)).toArray((id) -> {
			return new JumStem.Variant[id];
		});
		private final int id;
		private final String name;
		private final Block block;
		private final MobEffect effect;

		Variant(int id, String name, @NotNull Block block, @Nullable MobEffect effect) {
			this.id = id;
			this.name = name;
			this.block = block;
			this.effect = effect;
		}
		
		public static JumStem.Variant byBlock(Block block) {
			JumStem.Variant[] mushroomslime$variant = values();
	
			for(int i = 0; i < mushroomslime$variant.length; ++i) {
				if (mushroomslime$variant[i].getBlock() == block) {
					return mushroomslime$variant[i];
				}
			}
	
			return mushroomslime$variant[0];
		}
		
		public int getId() {
			return this.id;
		}
		
		public String getName() {
			return this.name;
		}

		public Block getBlock() {
			return this.block;
		}
		
		public MobEffect getEffect() {
			return this.effect;
		}
	}
}