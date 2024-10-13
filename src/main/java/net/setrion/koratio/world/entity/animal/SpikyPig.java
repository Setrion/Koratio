package net.setrion.koratio.world.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioSoundEvents;

import javax.annotation.Nullable;

public class SpikyPig extends Pig {

	public SpikyPig(EntityType<? extends SpikyPig> type, Level level) {
		super(type, level);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 25.0D).add(Attributes.MOVEMENT_SPEED, 0.325D);
	}
	
	protected SoundEvent getAmbientSound() {
		return KoratioSoundEvents.SPIKY_PIG_AMBIENT.get();
	}

	protected SoundEvent getHurtSound(DamageSource source) {
		return KoratioSoundEvents.SPIKY_PIG_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return KoratioSoundEvents.SPIKY_PIG_DEATH.get();
	}

	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(KoratioSoundEvents.SPIKY_PIG_STEP.get(), 0.15F, 1.0F);
	}
	
	@Nullable
	public Pig getBreedOffspring(ServerLevel level, AgeableMob ageable) {
		return KoratioEntityType.SPIKY_PIG.get().create(level);
	}
}