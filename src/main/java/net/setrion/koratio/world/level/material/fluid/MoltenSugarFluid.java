package net.setrion.koratio.world.level.material.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioFluids;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioParticles;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class MoltenSugarFluid extends FlowingFluid {

    public static List<MoltenSugarFluid> sugarFluids = new ArrayList<>();

    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        this.fizz(level, pos);
    }

    public boolean canBeReplacedWith(FluidState state, BlockGetter getter, BlockPos pos, Fluid fluid, Direction direction) {
        return state.getHeight(getter, pos) >= 0.44444445F && fluid.is(FluidTags.WATER);
    }

    public int getSlopeFindDistance(LevelReader level) {
        return 4;
    }

    private void fizz(LevelAccessor level, BlockPos pos) {
        level.levelEvent(1501, pos, 0);
    }

    protected boolean canConvertToSource(Level level) {
        return false;
    }

    public int getDropOff(LevelReader level) {
        return 2;
    }

    public int getTickDelay(LevelReader level) {
        return 15;
    }

    protected float getExplosionResistance() {
        return 100.0F;
    }

    public static class FlowingSugar extends MoltenSugarFluid {

        private final DeferredHolder<Fluid, FlowingFluid> flowing;
        private final DeferredHolder<Fluid, FlowingFluid> source;
        private final DeferredItem<BucketItem> bucket;
        private final DeferredHolder<ParticleType<?>, SimpleParticleType> particle;
        private final DeferredBlock<Block> block;
        private final DeferredHolder<FluidType, FluidType> type;

        public FlowingSugar(DeferredHolder<Fluid, FlowingFluid> flowing, DeferredHolder<Fluid, FlowingFluid> source, DeferredItem<BucketItem> bucket, DeferredHolder<ParticleType<?>, SimpleParticleType> particle, DeferredBlock<Block> block, DeferredHolder<FluidType, FluidType> type) {
            sugarFluids.add(this);
            this.flowing = flowing;
            this.source = source;
            this.bucket = bucket;
            this.particle = particle;
            this.block = block;
            this.type = type;
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> state) {
            super.createFluidStateDefinition(state);
            state.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }

        public Fluid getFlowing() {
            return flowing.get();
        }

        public Fluid getSource() {
            return source.get();
        }

        @Override
        protected boolean canConvertToSource(ServerLevel serverLevel) {
            return false;
        }

        public Item getBucket() {
            return bucket.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return particle.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == source.get() || fluid == flowing.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return block.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return type.get();
        }
    }

    public static class SourceSugar extends MoltenSugarFluid {

        private final DeferredHolder<Fluid, FlowingFluid> flowing;
        private final DeferredHolder<Fluid, FlowingFluid> source;
        private final DeferredItem<BucketItem> bucket;
        private final DeferredHolder<ParticleType<?>, SimpleParticleType> particle;
        private final DeferredBlock<Block> block;
        private final DeferredHolder<FluidType, FluidType> type;

        public SourceSugar(DeferredHolder<Fluid, FlowingFluid> flowing, DeferredHolder<Fluid, FlowingFluid> source, DeferredItem<BucketItem> bucket, DeferredHolder<ParticleType<?>, SimpleParticleType> particle, DeferredBlock<Block> block, DeferredHolder<FluidType, FluidType> type) {
            sugarFluids.add(this);
            this.flowing = flowing;
            this.source = source;
            this.bucket = bucket;
            this.particle = particle;
            this.block = block;
            this.type = type;
        }

        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }

        public Fluid getFlowing() {
            return flowing.get();
        }

        public Fluid getSource() {
            return source.get();
        }

        @Override
        protected boolean canConvertToSource(ServerLevel serverLevel) {
            return false;
        }

        public Item getBucket() {
            return bucket.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return particle.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == source.get() || fluid == flowing.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return block.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return type.get();
        }
    }

    public static class FlowingWhite extends FlowingSugar {
        public FlowingWhite() {
            super(KoratioFluids.FLOWING_MOLTEN_WHITE_SUGAR, KoratioFluids.MOLTEN_WHITE_SUGAR, KoratioItems.MOLTEN_WHITE_SUGAR_BUCKET, KoratioParticles.DRIPPING_WHITE_SUGAR, KoratioBlocks.MOLTEN_WHITE_SUGAR, KoratioFluids.MOLTEN_WHITE_SUGAR_TYPE);
        }
    }

    public static class SourceWhite extends SourceSugar {
        public SourceWhite() {
            super(KoratioFluids.FLOWING_MOLTEN_WHITE_SUGAR, KoratioFluids.MOLTEN_WHITE_SUGAR, KoratioItems.MOLTEN_WHITE_SUGAR_BUCKET, KoratioParticles.DRIPPING_WHITE_SUGAR, KoratioBlocks.MOLTEN_WHITE_SUGAR, KoratioFluids.MOLTEN_WHITE_SUGAR_TYPE);
        }
    }

    public static class FlowingLightGray extends FlowingSugar {
        public FlowingLightGray() {
            super(KoratioFluids.FLOWING_MOLTEN_LIGHT_GRAY_SUGAR, KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR, KoratioItems.MOLTEN_LIGHT_GRAY_SUGAR_BUCKET, KoratioParticles.DRIPPING_LIGHT_GRAY_SUGAR, KoratioBlocks.MOLTEN_LIGHT_GRAY_SUGAR, KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR_TYPE);
        }
    }

    public static class SourceLightGray extends SourceSugar {
        public SourceLightGray() {
            super(KoratioFluids.FLOWING_MOLTEN_LIGHT_GRAY_SUGAR, KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR, KoratioItems.MOLTEN_LIGHT_GRAY_SUGAR_BUCKET, KoratioParticles.DRIPPING_LIGHT_GRAY_SUGAR, KoratioBlocks.MOLTEN_LIGHT_GRAY_SUGAR, KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR_TYPE);
        }
    }

    public static class FlowingGray extends FlowingSugar {
        public FlowingGray() {
            super(KoratioFluids.FLOWING_MOLTEN_GRAY_SUGAR, KoratioFluids.MOLTEN_GRAY_SUGAR, KoratioItems.MOLTEN_GRAY_SUGAR_BUCKET, KoratioParticles.DRIPPING_GRAY_SUGAR, KoratioBlocks.MOLTEN_GRAY_SUGAR, KoratioFluids.MOLTEN_GRAY_SUGAR_TYPE);
        }
    }

    public static class SourceGray extends SourceSugar {
        public SourceGray() {
            super(KoratioFluids.FLOWING_MOLTEN_GRAY_SUGAR, KoratioFluids.MOLTEN_GRAY_SUGAR, KoratioItems.MOLTEN_GRAY_SUGAR_BUCKET, KoratioParticles.DRIPPING_GRAY_SUGAR, KoratioBlocks.MOLTEN_GRAY_SUGAR, KoratioFluids.MOLTEN_GRAY_SUGAR_TYPE);
        }
    }

    public static class FlowingBlack extends FlowingSugar {
        public FlowingBlack() {
            super(KoratioFluids.FLOWING_MOLTEN_BLACK_SUGAR, KoratioFluids.MOLTEN_BLACK_SUGAR, KoratioItems.MOLTEN_BLACK_SUGAR_BUCKET, KoratioParticles.DRIPPING_BLACK_SUGAR, KoratioBlocks.MOLTEN_BLACK_SUGAR, KoratioFluids.MOLTEN_BLACK_SUGAR_TYPE);
        }
    }

    public static class SourceBlack extends SourceSugar {
        public SourceBlack() {
            super(KoratioFluids.FLOWING_MOLTEN_BLACK_SUGAR, KoratioFluids.MOLTEN_BLACK_SUGAR, KoratioItems.MOLTEN_BLACK_SUGAR_BUCKET, KoratioParticles.DRIPPING_BLACK_SUGAR, KoratioBlocks.MOLTEN_BLACK_SUGAR, KoratioFluids.MOLTEN_BLACK_SUGAR_TYPE);
        }
    }

    public static class FlowingBrown extends FlowingSugar {
        public FlowingBrown() {
            super(KoratioFluids.FLOWING_MOLTEN_BROWN_SUGAR, KoratioFluids.MOLTEN_BROWN_SUGAR, KoratioItems.MOLTEN_BROWN_SUGAR_BUCKET, KoratioParticles.DRIPPING_BROWN_SUGAR, KoratioBlocks.MOLTEN_BROWN_SUGAR, KoratioFluids.MOLTEN_BROWN_SUGAR_TYPE);
        }
    }

    public static class SourceBrown extends SourceSugar {
        public SourceBrown() {
            super(KoratioFluids.FLOWING_MOLTEN_BROWN_SUGAR, KoratioFluids.MOLTEN_BROWN_SUGAR, KoratioItems.MOLTEN_BROWN_SUGAR_BUCKET, KoratioParticles.DRIPPING_BROWN_SUGAR, KoratioBlocks.MOLTEN_BROWN_SUGAR, KoratioFluids.MOLTEN_BROWN_SUGAR_TYPE);
        }
    }

    public static class FlowingRed extends FlowingSugar {
        public FlowingRed() {
            super(KoratioFluids.FLOWING_MOLTEN_RED_SUGAR, KoratioFluids.MOLTEN_RED_SUGAR, KoratioItems.MOLTEN_RED_SUGAR_BUCKET, KoratioParticles.DRIPPING_RED_SUGAR, KoratioBlocks.MOLTEN_RED_SUGAR, KoratioFluids.MOLTEN_RED_SUGAR_TYPE);
        }
    }

    public static class SourceRed extends SourceSugar {
        public SourceRed() {
            super(KoratioFluids.FLOWING_MOLTEN_RED_SUGAR, KoratioFluids.MOLTEN_RED_SUGAR, KoratioItems.MOLTEN_RED_SUGAR_BUCKET, KoratioParticles.DRIPPING_RED_SUGAR, KoratioBlocks.MOLTEN_RED_SUGAR, KoratioFluids.MOLTEN_RED_SUGAR_TYPE);
        }
    }

    public static class FlowingOrange extends FlowingSugar {
        public FlowingOrange() {
            super(KoratioFluids.FLOWING_MOLTEN_ORANGE_SUGAR, KoratioFluids.MOLTEN_ORANGE_SUGAR, KoratioItems.MOLTEN_ORANGE_SUGAR_BUCKET, KoratioParticles.DRIPPING_ORANGE_SUGAR, KoratioBlocks.MOLTEN_ORANGE_SUGAR, KoratioFluids.MOLTEN_ORANGE_SUGAR_TYPE);
        }
    }

    public static class SourceOrange extends SourceSugar {
        public SourceOrange() {
            super(KoratioFluids.FLOWING_MOLTEN_ORANGE_SUGAR, KoratioFluids.MOLTEN_ORANGE_SUGAR, KoratioItems.MOLTEN_ORANGE_SUGAR_BUCKET, KoratioParticles.DRIPPING_ORANGE_SUGAR, KoratioBlocks.MOLTEN_ORANGE_SUGAR, KoratioFluids.MOLTEN_ORANGE_SUGAR_TYPE);
        }
    }

    public static class FlowingYellow extends FlowingSugar {
        public FlowingYellow() {
            super(KoratioFluids.FLOWING_MOLTEN_YELLOW_SUGAR, KoratioFluids.MOLTEN_YELLOW_SUGAR, KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET, KoratioParticles.DRIPPING_YELLOW_SUGAR, KoratioBlocks.MOLTEN_YELLOW_SUGAR, KoratioFluids.MOLTEN_YELLOW_SUGAR_TYPE);
        }
    }

    public static class SourceYellow extends SourceSugar {
        public SourceYellow() {
            super(KoratioFluids.FLOWING_MOLTEN_YELLOW_SUGAR, KoratioFluids.MOLTEN_YELLOW_SUGAR, KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET, KoratioParticles.DRIPPING_YELLOW_SUGAR, KoratioBlocks.MOLTEN_YELLOW_SUGAR, KoratioFluids.MOLTEN_YELLOW_SUGAR_TYPE);
        }
    }

    public static class FlowingLime extends FlowingSugar {
        public FlowingLime() {
            super(KoratioFluids.FLOWING_MOLTEN_LIME_SUGAR, KoratioFluids.MOLTEN_LIME_SUGAR, KoratioItems.MOLTEN_LIME_SUGAR_BUCKET, KoratioParticles.DRIPPING_LIME_SUGAR, KoratioBlocks.MOLTEN_LIME_SUGAR, KoratioFluids.MOLTEN_LIME_SUGAR_TYPE);
        }
    }

    public static class SourceLime extends SourceSugar {
        public SourceLime() {
            super(KoratioFluids.FLOWING_MOLTEN_LIME_SUGAR, KoratioFluids.MOLTEN_LIME_SUGAR, KoratioItems.MOLTEN_LIME_SUGAR_BUCKET, KoratioParticles.DRIPPING_LIME_SUGAR, KoratioBlocks.MOLTEN_LIME_SUGAR, KoratioFluids.MOLTEN_LIME_SUGAR_TYPE);
        }
    }

    public static class FlowingGreen extends FlowingSugar {
        public FlowingGreen() {
            super(KoratioFluids.FLOWING_MOLTEN_GREEN_SUGAR, KoratioFluids.MOLTEN_GREEN_SUGAR, KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET, KoratioParticles.DRIPPING_GREEN_SUGAR, KoratioBlocks.MOLTEN_GREEN_SUGAR, KoratioFluids.MOLTEN_GREEN_SUGAR_TYPE);
        }
    }

    public static class SourceGreen extends SourceSugar {
        public SourceGreen() {
            super(KoratioFluids.FLOWING_MOLTEN_GREEN_SUGAR, KoratioFluids.MOLTEN_GREEN_SUGAR, KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET, KoratioParticles.DRIPPING_GREEN_SUGAR, KoratioBlocks.MOLTEN_GREEN_SUGAR, KoratioFluids.MOLTEN_GREEN_SUGAR_TYPE);
        }
    }

    public static class FlowingCyan extends FlowingSugar {
        public FlowingCyan() {
            super(KoratioFluids.FLOWING_MOLTEN_CYAN_SUGAR, KoratioFluids.MOLTEN_CYAN_SUGAR, KoratioItems.MOLTEN_CYAN_SUGAR_BUCKET, KoratioParticles.DRIPPING_CYAN_SUGAR, KoratioBlocks.MOLTEN_CYAN_SUGAR, KoratioFluids.MOLTEN_CYAN_SUGAR_TYPE);
        }
    }

    public static class SourceCyan extends SourceSugar {
        public SourceCyan() {
            super(KoratioFluids.FLOWING_MOLTEN_CYAN_SUGAR, KoratioFluids.MOLTEN_CYAN_SUGAR, KoratioItems.MOLTEN_CYAN_SUGAR_BUCKET, KoratioParticles.DRIPPING_CYAN_SUGAR, KoratioBlocks.MOLTEN_CYAN_SUGAR, KoratioFluids.MOLTEN_CYAN_SUGAR_TYPE);
        }
    }

    public static class FlowingLightBlue extends FlowingSugar {
        public FlowingLightBlue() {
            super(KoratioFluids.FLOWING_MOLTEN_LIGHT_BLUE_SUGAR, KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR, KoratioItems.MOLTEN_LIGHT_BLUE_SUGAR_BUCKET, KoratioParticles.DRIPPING_LIGHT_BLUE_SUGAR, KoratioBlocks.MOLTEN_LIGHT_BLUE_SUGAR, KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR_TYPE);
        }
    }

    public static class SourceLightBlue extends SourceSugar {
        public SourceLightBlue() {
            super(KoratioFluids.FLOWING_MOLTEN_LIGHT_BLUE_SUGAR, KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR, KoratioItems.MOLTEN_LIGHT_BLUE_SUGAR_BUCKET, KoratioParticles.DRIPPING_LIGHT_BLUE_SUGAR, KoratioBlocks.MOLTEN_LIGHT_BLUE_SUGAR, KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR_TYPE);
        }
    }

    public static class FlowingBlue extends FlowingSugar {
        public FlowingBlue() {
            super(KoratioFluids.FLOWING_MOLTEN_BLUE_SUGAR, KoratioFluids.MOLTEN_BLUE_SUGAR, KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET, KoratioParticles.DRIPPING_BLUE_SUGAR, KoratioBlocks.MOLTEN_BLUE_SUGAR, KoratioFluids.MOLTEN_BLUE_SUGAR_TYPE);
        }
    }

    public static class SourceBlue extends SourceSugar {
        public SourceBlue() {
            super(KoratioFluids.FLOWING_MOLTEN_BLUE_SUGAR, KoratioFluids.MOLTEN_BLUE_SUGAR, KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET, KoratioParticles.DRIPPING_BLUE_SUGAR, KoratioBlocks.MOLTEN_BLUE_SUGAR, KoratioFluids.MOLTEN_BLUE_SUGAR_TYPE);
        }
    }

    public static class FlowingPurple extends FlowingSugar {
        public FlowingPurple() {
            super(KoratioFluids.FLOWING_MOLTEN_PURPLE_SUGAR, KoratioFluids.MOLTEN_PURPLE_SUGAR, KoratioItems.MOLTEN_PURPLE_SUGAR_BUCKET, KoratioParticles.DRIPPING_PURPLE_SUGAR, KoratioBlocks.MOLTEN_PURPLE_SUGAR, KoratioFluids.MOLTEN_PURPLE_SUGAR_TYPE);
        }
    }

    public static class SourcePurple extends SourceSugar {
        public SourcePurple() {
            super(KoratioFluids.FLOWING_MOLTEN_PURPLE_SUGAR, KoratioFluids.MOLTEN_PURPLE_SUGAR, KoratioItems.MOLTEN_PURPLE_SUGAR_BUCKET, KoratioParticles.DRIPPING_PURPLE_SUGAR, KoratioBlocks.MOLTEN_PURPLE_SUGAR, KoratioFluids.MOLTEN_PURPLE_SUGAR_TYPE);
        }
    }

    public static class FlowingMagenta extends FlowingSugar {
        public FlowingMagenta() {
            super(KoratioFluids.FLOWING_MOLTEN_MAGENTA_SUGAR, KoratioFluids.MOLTEN_MAGENTA_SUGAR, KoratioItems.MOLTEN_MAGENTA_SUGAR_BUCKET, KoratioParticles.DRIPPING_MAGENTA_SUGAR, KoratioBlocks.MOLTEN_MAGENTA_SUGAR, KoratioFluids.MOLTEN_MAGENTA_SUGAR_TYPE);
        }
    }

    public static class SourceMagenta extends SourceSugar {
        public SourceMagenta() {
            super(KoratioFluids.FLOWING_MOLTEN_MAGENTA_SUGAR, KoratioFluids.MOLTEN_MAGENTA_SUGAR, KoratioItems.MOLTEN_MAGENTA_SUGAR_BUCKET, KoratioParticles.DRIPPING_MAGENTA_SUGAR, KoratioBlocks.MOLTEN_MAGENTA_SUGAR, KoratioFluids.MOLTEN_MAGENTA_SUGAR_TYPE);
        }
    }

    public static class FlowingPink extends FlowingSugar {
        public FlowingPink() {
            super(KoratioFluids.FLOWING_MOLTEN_PINK_SUGAR, KoratioFluids.MOLTEN_PINK_SUGAR, KoratioItems.MOLTEN_PINK_SUGAR_BUCKET, KoratioParticles.DRIPPING_PINK_SUGAR, KoratioBlocks.MOLTEN_PINK_SUGAR, KoratioFluids.MOLTEN_PINK_SUGAR_TYPE);
        }
    }

    public static class SourcePink extends SourceSugar {
        public SourcePink() {
            super(KoratioFluids.FLOWING_MOLTEN_PINK_SUGAR, KoratioFluids.MOLTEN_PINK_SUGAR, KoratioItems.MOLTEN_PINK_SUGAR_BUCKET, KoratioParticles.DRIPPING_PINK_SUGAR, KoratioBlocks.MOLTEN_PINK_SUGAR, KoratioFluids.MOLTEN_PINK_SUGAR_TYPE);
        }
    }
}