package net.setrion.koratio.world.entity.projectile;

import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioParticles;
import net.setrion.koratio.registry.KoratioTags;
import net.setrion.koratio.world.entity.demonic.NecromancerSkull;

public class RevivingSoul extends Projectile {

    public RevivingSoul(EntityType<? extends RevivingSoul> type, Level level) {
        super(type, level);
    }

    public RevivingSoul(Level level, NecromancerSkull skull) {
        this(KoratioEntityType.REVIVING_SOUL.get(), level);
        this.setOwner(skull);
        this.setNoGravity(true);
        this.setPos(skull.getX() - (double)(skull.getBbWidth() + 1.0F) * 0.5D * (double) Mth.sin(skull.yBodyRot * ((float)Math.PI / 180F)), skull.getEyeY() - (double)0.1F, skull.getZ() + (double)(skull.getBbWidth() + 1.0F) * 0.5D * (double)Mth.cos(skull.yBodyRot * ((float)Math.PI / 180F)));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            for(int i = 0; i < 1; ++i) {
                for(int j = 0; j < 2; ++j) {
                    this.level().addParticle(
                            KoratioParticles.DEMONIC_SOUL.get(),
                            this.getRandomX(0.5),
                            this.getRandomY(),
                            this.getRandomZ(0.5),
                            0,
                            0,
                            0
                    );
                }
            }
        }
        Vec3 vec3 = this.getDeltaMovement();
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult))
            this.onHit(hitresult);
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();
        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir) && this.level().getBlockStates(this.getBoundingBox()).noneMatch(state -> state.is(KoratioTags.Blocks.UNDEAD_REMAINS))) {
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
        //this.discard();
    }

    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
    }

    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        double d0 = packet.getXa();
        double d1 = packet.getYa();
        double d2 = packet.getZa();
        this.setDeltaMovement(d0, d1, d2);
    }
}