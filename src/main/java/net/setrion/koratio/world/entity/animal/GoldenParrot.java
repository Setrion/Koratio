package net.setrion.koratio.world.entity.animal;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowMobGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class GoldenParrot extends TamableAnimal implements FlyingAnimal {
	private static final Predicate<Mob> NOT_PARROT_PREDICATE = new Predicate<Mob>() {
		public boolean test(@Nullable Mob mob) {
			return mob != null && GoldenParrot.MOB_SOUND_MAP.containsKey(mob.getType());
		}
	};
	private static final Item POISONOUS_FOOD = Items.COOKIE;
	private static final Set<Item> TAME_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.TORCHFLOWER_SEEDS, Items.PITCHER_POD);
	static final Map<EntityType<?>, SoundEvent> MOB_SOUND_MAP = Util.make(Maps.newHashMap(), (p_29398_) -> {
	p_29398_.put(EntityType.BLAZE, SoundEvents.PARROT_IMITATE_BLAZE);
	p_29398_.put(EntityType.CAVE_SPIDER, SoundEvents.PARROT_IMITATE_SPIDER);
	p_29398_.put(EntityType.CREEPER, SoundEvents.PARROT_IMITATE_CREEPER);
	p_29398_.put(EntityType.DROWNED, SoundEvents.PARROT_IMITATE_DROWNED);
	p_29398_.put(EntityType.ELDER_GUARDIAN, SoundEvents.PARROT_IMITATE_ELDER_GUARDIAN);
	p_29398_.put(EntityType.ENDER_DRAGON, SoundEvents.PARROT_IMITATE_ENDER_DRAGON);
	p_29398_.put(EntityType.ENDERMITE, SoundEvents.PARROT_IMITATE_ENDERMITE);
	p_29398_.put(EntityType.EVOKER, SoundEvents.PARROT_IMITATE_EVOKER);
	p_29398_.put(EntityType.GHAST, SoundEvents.PARROT_IMITATE_GHAST);
	p_29398_.put(EntityType.GUARDIAN, SoundEvents.PARROT_IMITATE_GUARDIAN);
	p_29398_.put(EntityType.HOGLIN, SoundEvents.PARROT_IMITATE_HOGLIN);
	p_29398_.put(EntityType.HUSK, SoundEvents.PARROT_IMITATE_HUSK);
	p_29398_.put(EntityType.ILLUSIONER, SoundEvents.PARROT_IMITATE_ILLUSIONER);
	p_29398_.put(EntityType.MAGMA_CUBE, SoundEvents.PARROT_IMITATE_MAGMA_CUBE);
	p_29398_.put(EntityType.PHANTOM, SoundEvents.PARROT_IMITATE_PHANTOM);
	p_29398_.put(EntityType.PIGLIN, SoundEvents.PARROT_IMITATE_PIGLIN);
	p_29398_.put(EntityType.PIGLIN_BRUTE, SoundEvents.PARROT_IMITATE_PIGLIN_BRUTE);
	p_29398_.put(EntityType.PILLAGER, SoundEvents.PARROT_IMITATE_PILLAGER);
	p_29398_.put(EntityType.RAVAGER, SoundEvents.PARROT_IMITATE_RAVAGER);
	p_29398_.put(EntityType.SHULKER, SoundEvents.PARROT_IMITATE_SHULKER);
	p_29398_.put(EntityType.SILVERFISH, SoundEvents.PARROT_IMITATE_SILVERFISH);
	p_29398_.put(EntityType.SKELETON, SoundEvents.PARROT_IMITATE_SKELETON);
	p_29398_.put(EntityType.SLIME, SoundEvents.PARROT_IMITATE_SLIME);
	p_29398_.put(EntityType.SPIDER, SoundEvents.PARROT_IMITATE_SPIDER);
	p_29398_.put(EntityType.STRAY, SoundEvents.PARROT_IMITATE_STRAY);
	p_29398_.put(EntityType.VEX, SoundEvents.PARROT_IMITATE_VEX);
	p_29398_.put(EntityType.VINDICATOR, SoundEvents.PARROT_IMITATE_VINDICATOR);
	p_29398_.put(EntityType.WARDEN, SoundEvents.PARROT_IMITATE_WARDEN);
	p_29398_.put(EntityType.WITCH, SoundEvents.PARROT_IMITATE_WITCH);
	p_29398_.put(EntityType.WITHER, SoundEvents.PARROT_IMITATE_WITHER);
	p_29398_.put(EntityType.WITHER_SKELETON, SoundEvents.PARROT_IMITATE_WITHER_SKELETON);
	p_29398_.put(EntityType.ZOGLIN, SoundEvents.PARROT_IMITATE_ZOGLIN);
	p_29398_.put(EntityType.ZOMBIE, SoundEvents.PARROT_IMITATE_ZOMBIE);
	p_29398_.put(EntityType.ZOMBIE_VILLAGER, SoundEvents.PARROT_IMITATE_ZOMBIE_VILLAGER);
	});
	public float flap;
	public float flapSpeed;
	public float oFlapSpeed;
	public float oFlap;
	private float flapping = 1.0F;
	private float nextFlap = 1.0F;
	private boolean partyParrot;
	@Nullable
	private BlockPos jukebox;

	public GoldenParrot(EntityType<? extends GoldenParrot> type, Level level) {
		super(type, level);
		this.moveControl = new FlyingMoveControl(this, 10, false);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
		this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType type, @Nullable SpawnGroupData groupdata, @Nullable CompoundTag tag) {
		if (groupdata == null) {
			groupdata = new AgeableMob.AgeableMobGroupData(false);
		}
		return super.finalizeSpawn(level, difficulty, type, groupdata, tag);
	}

	public boolean isBaby() {
		return false;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
		this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
		this.goalSelector.addGoal(2, new GoldenParrot.ParrotWanderGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
	}

	protected PathNavigation createNavigation(Level level) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.6F;
	}

	public void aiStep() {
		if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
			this.partyParrot = false;
			this.jukebox = null;
		}

		if (this.level().random.nextInt(400) == 0) {
			imitateNearbyMobs(this.level(), this);
		}

		super.aiStep();
		this.calculateFlapping();
	}

	public void setRecordPlayingNearby(BlockPos pos, boolean party) {
		this.jukebox = pos;
		this.partyParrot = party;
	}

	public boolean isPartyParrot() {
		return this.partyParrot;
	}

	private void calculateFlapping() {
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed += (float)(!this.onGround() && !this.isPassenger() ? 4 : -1) * 0.3F;
		this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
		if (!this.onGround() && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}

		this.flapping *= 0.9F;
		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround() && vec3.y < 0.0D) {
			this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
		}

		this.flap += this.flapping * 2.0F;
	}

	public static boolean imitateNearbyMobs(Level level, Entity entity) {
		if (entity.isAlive() && !entity.isSilent() && level.random.nextInt(2) == 0) {
			List<Mob> list = level.getEntitiesOfClass(Mob.class, entity.getBoundingBox().inflate(20.0D), NOT_PARROT_PREDICATE);
			if (!list.isEmpty()) {
				Mob mob = list.get(level.random.nextInt(list.size()));
				if (!mob.isSilent()) {
					SoundEvent soundevent = getImitatedSound(mob.getType());
					level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), soundevent, entity.getSoundSource(), 0.7F, getPitch(level.random));
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!this.isTame() && TAME_FOOD.contains(itemstack.getItem())) {
			if (!player.getAbilities().instabuild) {
				itemstack.shrink(1);
			}

			if (!this.isSilent()) {
				this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
			}
			
			if (!this.level().isClientSide) {
				if (this.random.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
					this.tame(player);
					this.level().broadcastEntityEvent(this, (byte)7);
				} else {
					this.level().broadcastEntityEvent(this, (byte)6);
				}
			}

			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (itemstack.is(POISONOUS_FOOD)) {
			if (!player.getAbilities().instabuild) {
				itemstack.shrink(1);
			}

			this.addEffect(new MobEffectInstance(MobEffects.POISON, 900));
			if (player.isCreative() || !this.isInvulnerable()) {
				this.hurt(this.damageSources().playerAttack(player), Float.MAX_VALUE);
			}

			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (!this.isFlying() && this.isTame() && this.isOwnedBy(player)) {
			if (!this.level().isClientSide) {
				this.setOrderedToSit(!this.isOrderedToSit());
			}

			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else {
			return super.mobInteract(player, hand);
		}
	}

	public boolean isFood(ItemStack stack) {
		return false;
	}

	public static boolean checkParrotSpawnRules(EntityType<GoldenParrot> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(level, pos);
	}

	protected void checkFallDamage(double d, boolean b, BlockState state, BlockPos pos) {
	}

	public boolean canMate(Animal animal) {
		return false;
	}

	@Nullable
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return null;
	}

	public boolean doHurtTarget(Entity target) {
		return target.hurt(this.damageSources().mobAttack(this), 3.0F);
	}

	@Nullable
	public SoundEvent getAmbientSound() {
		return getAmbient(this.level(), this.level().random);
	}

	public static SoundEvent getAmbient(Level level, RandomSource random) {
		if (level.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(1000) == 0) {
			List<EntityType<?>> list = Lists.newArrayList(MOB_SOUND_MAP.keySet());
			return getImitatedSound(list.get(random.nextInt(list.size())));
		} else {
			return SoundEvents.PARROT_AMBIENT;
		}
	}

	private static SoundEvent getImitatedSound(EntityType<?> type) {
		return MOB_SOUND_MAP.getOrDefault(type, SoundEvents.PARROT_AMBIENT);
	}

	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.PARROT_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.PARROT_DEATH;
	}

	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
	}

	protected boolean isFlapping() {
		return this.flyDist > this.nextFlap;
	}

	protected void onFlap() {
		this.playSound(SoundEvents.PARROT_FLY, 0.15F, 1.0F);
		this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
	}

	public float getVoicePitch() {
		return getPitch(this.random);
	}

	public static float getPitch(RandomSource random) {
	return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
	}

	public SoundSource getSoundSource() {
		return SoundSource.NEUTRAL;
	}

	public boolean isPushable() {
		return true;
	}

	protected void doPush(Entity entity) {
		if (!(entity instanceof Player)) {
			super.doPush(entity);
		}
	}

	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			if (!this.level().isClientSide) {
				this.setOrderedToSit(false);
			}

			return super.hurt(source, amount);
		}
	}

	public boolean isFlying() {
		return !this.onGround();
	}

	public Vec3 getLeashOffset() {
		return new Vec3(0.0D, (double)(0.5F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
	}

	static class ParrotWanderGoal extends WaterAvoidingRandomFlyingGoal {
		public ParrotWanderGoal(PathfinderMob mob, double d) {
			super(mob, d	);
		}

		@Nullable
		protected Vec3 getPosition() {
			Vec3 vec3 = null;
			if (this.mob.isInWater()) {
				vec3 = LandRandomPos.getPos(this.mob, 15, 15);
			}

			if (this.mob.getRandom().nextFloat() >= this.probability) {
				vec3 = this.getTreePos();
			}

			return vec3 == null ? super.getPosition() : vec3;
		}

		@Nullable
		private Vec3 getTreePos() {
			BlockPos blockpos = this.mob.blockPosition();
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

			for(BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - 3.0D), Mth.floor(this.mob.getY() - 6.0D), Mth.floor(this.mob.getZ() - 3.0D), Mth.floor(this.mob.getX() + 3.0D), Mth.floor(this.mob.getY() + 6.0D), Mth.floor(this.mob.getZ() + 3.0D))) {
				if (!blockpos.equals(blockpos1)) {
					BlockState blockstate = this.mob.level().getBlockState(blockpos$mutableblockpos1.setWithOffset(blockpos1, Direction.DOWN));
					boolean flag = blockstate.getBlock() instanceof LeavesBlock || blockstate.is(BlockTags.LOGS);
					if (flag && this.mob.level().isEmptyBlock(blockpos1) && this.mob.level().isEmptyBlock(blockpos$mutableblockpos.setWithOffset(blockpos1, Direction.UP))) {
						return Vec3.atBottomCenterOf(blockpos1);
					}
				}
			}

			return null;
		}
	}
}