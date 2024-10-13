package net.setrion.koratio.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.registry.KoratioTags;

@OnlyIn(Dist.CLIENT)
public class DripParticle extends TextureSheetParticle {
    private final Fluid type;
    protected boolean isGlowing;

    protected DripParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, Fluid type) {
        super(level, x, y, z);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = type;
        setSpriteFromAge(spriteSet);
    }

    protected Fluid getType() {
        return this.type;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getLightColor(float partialTick) {
        return this.isGlowing ? 240 : super.getLightColor(partialTick);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= 0.9800000190734863;
                this.yd *= 0.9800000190734863;
                this.zd *= 0.9800000190734863;
                if (this.type != Fluids.EMPTY) {
                    BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
                    FluidState fluidstate = this.level.getFluidState(blockpos);
                    if (fluidstate.getType() == this.type && this.y < (double)((float)blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
                        this.remove();
                    }
                }
            }
        }
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }
    }

    protected void postMoveUpdate() {
    }

    public static TextureSheetParticle createSugarHangParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, float color1, float color2, float color3, Fluid fluid, ParticleOptions particle) {
        return new DripParticle.CoolingDripHangParticle(spriteSet, level, x, y, z, color1, color2, color3, fluid, particle);
    }

    public static TextureSheetParticle createSugarFallParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, float color1, float color2, float color3, Fluid fluid, ParticleOptions particle) {
        DripParticle dripparticle = new DripParticle.FallAndLandParticle(spriteSet, level, x, y, z, fluid, particle);
        dripparticle.setColor(color1, color2, color3);
        return dripparticle;
    }

    public static TextureSheetParticle createSugarLandParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, float color1, float color2, float color3, Fluid fluid) {
        DripParticle dripparticle = new DripParticle.DripLandParticle(spriteSet, level, x, y, z, fluid);
        dripparticle.setColor(color1, color2, color3);
        return dripparticle;
    }

    public static TextureSheetParticle createDripstoneSugarHangParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, float color1, float color2, float color3, Fluid fluid, ParticleOptions particle) {
        return new DripParticle.CoolingDripHangParticle(spriteSet, level, x, y, z, color1, color2, color3, fluid, particle);
    }

    public static TextureSheetParticle createDripstoneSugarFallParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, float color1, float color2, float color3, Fluid fluid, ParticleOptions particle) {
        DripParticle dripparticle = new DripParticle.DripstoneFallAndLandParticle(spriteSet, level, x, y, z, fluid, particle);
        dripparticle.setColor(color1, color2, color3);
        return dripparticle;
    }

    @OnlyIn(Dist.CLIENT)
    static class DripHangParticle extends DripParticle {
        private final ParticleOptions fallingParticle;

        DripHangParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions fallingParticle) {
            super(spriteSet, level, x, y, z, type);
            this.fallingParticle = fallingParticle;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

        protected void postMoveUpdate() {
            this.xd *= 0.02;
            this.yd *= 0.02;
            this.zd *= 0.02;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class FallAndLandParticle extends DripParticle.FallingParticle {
        protected final ParticleOptions landParticle;

        public FallAndLandParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions landParticle) {
            super(spriteSet, level, x, y, z, type);
            this.landParticle = landParticle;
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class CoolingDripHangParticle extends DripParticle.DripHangParticle {
        public CoolingDripHangParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, float color1, float color2, float color3, Fluid type, ParticleOptions particle) {
            super(spriteSet, level, x, y, z, type, particle);
            this.rCol = color1;
            this.gCol = color2;
            this.bCol = color3;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class DripLandParticle extends DripParticle {
        public DripLandParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, Fluid type) {
            super(spriteSet, level, x, y, z, type);
            this.lifetime = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class DripstoneFallAndLandParticle extends DripParticle.FallAndLandParticle {
        public DripstoneFallAndLandParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions particle) {
            super(spriteSet, level, x, y, z, type, particle);
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
                SoundEvent soundevent = this.getType().is(KoratioTags.Fluids.MOLTEN_SUGAR) ? SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA : SoundEvents.POINTED_DRIPSTONE_DRIP_WATER;
                float f = Mth.randomBetween(this.random, 0.3F, 1.0F);
                this.level.playLocalSound(this.x, this.y, this.z, soundevent, SoundSource.BLOCKS, f, 1.0F, false);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class FallingParticle extends DripParticle {
        FallingParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, Fluid type) {
            this(spriteSet, level, x, y, z, type, (int)(64.0 / (Math.random() * 0.8 + 0.2)));
        }

        FallingParticle(SpriteSet spriteSet, ClientLevel level, double x, double y, double z, Fluid type, int lifetime) {
            super(spriteSet, level, x, y, z, type);
            this.lifetime = lifetime;
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
            }
        }
    }
}