package net.setrion.koratio.world.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.setrion.koratio.registry.KoratioSoundEvents;

public class Crystallize extends Monster {

	public Crystallize(EntityType<? extends Crystallize> type, Level level) {
		super(type, level);
		this.setPathfindingMalus(PathType.WATER, -1.0F);
		this.setPathfindingMalus(PathType.LAVA, -1.0F);
		this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
		this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
		this.setPathfindingMalus(PathType.POWDER_SNOW, 0.0F);
		this.setPathfindingMalus(PathType.DANGER_POWDER_SNOW, 0.0F);
		this.xpReward = 10;
	}
	
	protected void registerGoals() {
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 6.0D).add(Attributes.MOVEMENT_SPEED, (double)0.23F).add(Attributes.FOLLOW_RANGE, 48.0D);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return KoratioSoundEvents.CRYSTALLIZE_AMBIENT.get();
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return KoratioSoundEvents.CRYSTALLIZE_HURT.get();
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return KoratioSoundEvents.CRYSTALLIZE_DEATH.get();
	}
	
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(KoratioSoundEvents.CRYSTALLIZE_STEP.get(), 0.15F, 1.0F);
	}
	
	@Override
	public void aiStep() {
		if (this.level().isClientSide) {
			for(int i = 1; i < 9; i++) {
				double angle = 45*i;
				double pixelheight = (random.nextDouble()*this.getBbHeight()/2);
				double x = Math.sin(Math.toRadians(angle+(tickCount*5)));
				double z = Math.cos(Math.toRadians(angle+(tickCount*5)));
				if (random.nextInt(2) == 0) {
					this.level().addParticle(ParticleTypes.SNOWFLAKE, position().x + x, position().y + pixelheight, position().z + z, 0.0D, 0.0D, 0.0D);
				}
			}
		}
		super.aiStep();
	}
	
	public boolean isSensitiveToWater() {
		return true;
	}
}