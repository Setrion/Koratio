package net.setrion.koratio.world.entity.projectile;

import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.world.entity.monster.JumStem;

public class MushroomSpore extends Projectile {

	public MushroomSpore(EntityType<? extends MushroomSpore> type, Level level) {
		super(type, level);
	}
	
	public MushroomSpore(Level level, JumStem jumstem) {
		this(KoratioEntityType.MUSHROOM_SPORE.get(), level);
		this.setOwner(jumstem);
		this.setPos(jumstem.getX() - (double)(jumstem.getBbWidth() + 1.0F) * 0.5D * (double)Mth.sin(jumstem.yBodyRot * ((float)Math.PI / 180F)), jumstem.getEyeY() - (double)0.1F, jumstem.getZ() + (double)(jumstem.getBbWidth() + 1.0F) * 0.5D * (double)Mth.cos(jumstem.yBodyRot * ((float)Math.PI / 180F)));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	public void tick() {
		super.tick();
		Vec3 vec3 = this.getDeltaMovement();
		HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
		if (hitresult.getType() != HitResult.Type.MISS && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult))
			this.onHit(hitresult);
		double d0 = this.getX() + vec3.x;
		double d1 = this.getY() + vec3.y;
		double d2 = this.getZ() + vec3.z;
		this.updateRotation();
		if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
			this.discard();
		} else if (this.isInWaterOrBubble()) {
			this.discard();
		} else {
			this.setDeltaMovement(vec3.scale(0.99F));
			if (!this.isNoGravity()) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.06F, 0.0D));
			}

			this.setPos(d0, d1, d2);
		}
	}

	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = this.getOwner();
		if (entity instanceof JumStem livingentity && result.getEntity() instanceof LivingEntity livingtarget) {
			livingtarget.hurt(this.damageSources().mobProjectile(this, livingentity), 1.0F);
			if (livingentity.getVariant() != JumStem.Variant.SHEARED) {
				livingtarget.addEffect(new MobEffectInstance(livingentity.getVariant().getEffect(), 100, 1),  livingentity);
			}
			this.discard();
		}
	}

	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!this.level().isClientSide() && this.getOwner() instanceof JumStem jumstem && jumstem.getVariant() != JumStem.Variant.SHEARED) {
			AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
			areaeffectcloud.setRadius(2.0F * jumstem.getMushroomAmount());
			areaeffectcloud.setRadiusOnUse(-0.5F);
			areaeffectcloud.setWaitTime(10);
			areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
			areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
			areaeffectcloud.addEffect(new MobEffectInstance(jumstem.getVariant().getEffect(), 100, jumstem.getMushroomAmount()));
		
			this.level().addFreshEntity(areaeffectcloud);
			this.discard();
		}
	}

	public void recreateFromPacket(ClientboundAddEntityPacket packet) {
		super.recreateFromPacket(packet);
		double d0 = packet.getXa();
		double d1 = packet.getYa();
		double d2 = packet.getZa();

		for(int i = 0; i < 7; ++i) {
			if (this.getOwner() instanceof JumStem jumstem && jumstem.getVariant() != JumStem.Variant.SHEARED) {
				int c = jumstem.getVariant().getEffect().value().getColor();
				if (c != -1) {
					double c0 = (double)(c >> 16 & 255) / 255.0D;
					double c1 = (double)(c >> 8 & 255) / 255.0D;
					double c2 = (double)(c >> 0 & 255) / 255.0D;

					for(int j = 0; j < 2; ++j) {
						this.level().addParticle(
								ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, i),
								this.getRandomX(0.5),
								this.getRandomY(),
								this.getRandomZ(0.5),
								c0,
								c1,
								c2
						);
					}
				}
			}
		}

		this.setDeltaMovement(d0, d1, d2);
	}
}