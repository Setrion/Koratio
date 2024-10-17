package net.setrion.koratio.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class TeleporterDescendParticle extends TextureSheetParticle {
    protected TeleporterDescendParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ, 0.0, 0.0, 0.0);
        this.yd = -(Math.random() * 0.1F);
        this.setSize(0.005F, 0.005F);
        this.gravity = -0.0001F;
        this.lifetime = (int) (30.0 + (Math.random()));
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        } else {
            this.alpha=(lifetime*0.03333F);
            this.yd = this.yd + (double) this.gravity;
            this.move(0, this.yd, 0);
            if (this.onGround) {
                if (Math.random() < 0.5) {
                    this.remove();
                }

                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }

            BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
            double d0 = Math.max(
                    this.level
                            .getBlockState(blockpos)
                            .getCollisionShape(this.level, blockpos)
                            .max(Direction.Axis.Y, this.x - (double) blockpos.getX(), this.z - (double) blockpos.getZ()),
                    this.level.getFluidState(blockpos).getHeight(this.level, blockpos)
            );
            if (d0 > 0.0 && this.y < (double) blockpos.getY() - d0) {
                this.remove();
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(
                SimpleParticleType pType,
                ClientLevel pLevel,
                double pX,
                double pY,
                double pZ,
                double pXSpeed,
                double pYSpeed,
                double pZSpeed
        ) {
            TeleporterDescendParticle teleportAscendParticle = new TeleporterDescendParticle(pLevel, pX, pY, pZ);
            teleportAscendParticle.pickSprite(this.sprite);
            return teleportAscendParticle;
        }
    }
}