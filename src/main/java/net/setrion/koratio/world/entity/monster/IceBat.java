package net.setrion.koratio.world.entity.monster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class IceBat extends AbstractHostileBat {

	public IceBat(EntityType<? extends IceBat> type, Level level) {
		super(type, level);
	}
	
	@Override
	public void aiStep() {
		super.aiStep();
		if (this.level().isClientSide()) {
			for(int i = 0; i < 2; ++i) {
				this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getRandomX(0.5D), this.getRandomY() - 0.125D, this.getRandomZ(0.5D), 0, 0, 0);
			}
		}
	}
	
	public boolean doHurtTarget(Entity target) {
		boolean flag = target.hurt(damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, target);
			if (target instanceof LivingEntity && target.canFreeze()) {
				int i = 0;
				if (this.level().getDifficulty() == Difficulty.NORMAL) {
					i = 2;
				} else if (this.level().getDifficulty() == Difficulty.HARD) {
					i = 4;
				}

				if (i > 0) {
					((LivingEntity)target).setTicksFrozen(Math.min(this.getTicksRequiredToFreeze()+60, target.getTicksFrozen() + (i*20)));
				}
			}
		}
		return flag;
	}
}