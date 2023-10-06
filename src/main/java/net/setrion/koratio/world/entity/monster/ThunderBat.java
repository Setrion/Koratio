package net.setrion.koratio.world.entity.monster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class ThunderBat extends AbstractHostileBat {

	public ThunderBat(EntityType<? extends ThunderBat> type, Level level) {
		super(type, level);
	}
	
	@Override
	public void aiStep() {
		super.aiStep();
		if (this.level().isClientSide()) {
			for(int i = 0; i < 2; ++i) {
				this.level().addParticle(ParticleTypes.ELECTRIC_SPARK, this.getRandomX(0.5D), this.getRandomY() - 0.125D, this.getRandomZ(0.5D), 0, 0, 0);
			}
		}
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);
		if (this.level().isThundering()) {
			LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
			lightningbolt.moveTo(this.getX(), this.getY(), this.getZ());
			this.level().addFreshEntity(lightningbolt);
		}
	}
}