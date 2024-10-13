package net.setrion.koratio.world.level.material.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.FluidType;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioFluids;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioParticles;
import org.jetbrains.annotations.Nullable;

public abstract class MoltenSugarFluid extends FlowingFluid {

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

    public static class FlowingWhite extends MoltenSugarFluid {
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
            return KoratioFluids.FLOWING_MOLTEN_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_SUGAR_TYPE.get();
        }
    }

    public static class SourceWhite extends MoltenSugarFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }

        public Fluid getFlowing() {
            return KoratioFluids.FLOWING_MOLTEN_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_SUGAR_TYPE.get();
        }
    }

    public static class FlowingBlue extends MoltenSugarFluid {
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
            return KoratioFluids.FLOWING_MOLTEN_BLUE_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_BLUE_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_BLUE_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_BLUE_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_BLUE_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_BLUE_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_BLUE_SUGAR_TYPE.get();
        }
    }

    public static class SourceBlue extends MoltenSugarFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }

        public Fluid getFlowing() {
            return KoratioFluids.FLOWING_MOLTEN_BLUE_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_BLUE_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_BLUE_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_BLUE_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_BLUE_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_BLUE_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_BLUE_SUGAR_TYPE.get();
        }
    }

    public static class FlowingGreen extends MoltenSugarFluid {
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
            return KoratioFluids.FLOWING_MOLTEN_GREEN_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_GREEN_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_GREEN_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_GREEN_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_GREEN_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_GREEN_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_GREEN_SUGAR_TYPE.get();
        }
    }

    public static class SourceGreen extends MoltenSugarFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }

        public Fluid getFlowing() {
            return KoratioFluids.FLOWING_MOLTEN_GREEN_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_GREEN_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_GREEN_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_GREEN_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_GREEN_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_GREEN_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_GREEN_SUGAR_TYPE.get();
        }
    }

    public static class FlowingYellow extends MoltenSugarFluid {
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
            return KoratioFluids.FLOWING_MOLTEN_YELLOW_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_YELLOW_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_YELLOW_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_YELLOW_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_YELLOW_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_YELLOW_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_YELLOW_SUGAR_TYPE.get();
        }
    }

    public static class SourceYellow extends MoltenSugarFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }

        public Fluid getFlowing() {
            return KoratioFluids.FLOWING_MOLTEN_YELLOW_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_YELLOW_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_YELLOW_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_YELLOW_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_YELLOW_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_YELLOW_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_YELLOW_SUGAR_TYPE.get();
        }
    }

    public static class FlowingRed extends MoltenSugarFluid {
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
            return KoratioFluids.FLOWING_MOLTEN_RED_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_RED_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_RED_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_RED_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_RED_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_RED_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_RED_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_RED_SUGAR_TYPE.get();
        }
    }

    public static class SourceRed extends MoltenSugarFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }

        public Fluid getFlowing() {
            return KoratioFluids.FLOWING_MOLTEN_RED_SUGAR.get();
        }

        public Fluid getSource() {
            return KoratioFluids.MOLTEN_RED_SUGAR.get();
        }

        public Item getBucket() {
            return KoratioItems.MOLTEN_RED_SUGAR_BUCKET.get();
        }

        @Nullable
        @Override
        protected ParticleOptions getDripParticle() {
            return KoratioParticles.DRIPPING_RED_SUGAR.get();
        }

        @Override
        public boolean isSame(Fluid fluid) {
            return fluid == KoratioFluids.MOLTEN_RED_SUGAR.get() || fluid == KoratioFluids.FLOWING_MOLTEN_RED_SUGAR.get();
        }

        public BlockState createLegacyBlock(FluidState state) {
            return KoratioBlocks.MOLTEN_RED_SUGAR.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
        }

        @Override
        public FluidType getFluidType() {
            return KoratioFluids.MOLTEN_RED_SUGAR_TYPE.get();
        }
    }
}