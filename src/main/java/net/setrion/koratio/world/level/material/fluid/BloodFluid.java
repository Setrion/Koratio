package net.setrion.koratio.world.level.material.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
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

public abstract class BloodFluid extends FlowingFluid {
	
	public Fluid getFlowing() {
		return KoratioFluids.FLOWING_BLOOD.get();
	}

	public Fluid getSource() {
		return KoratioFluids.BLOOD.get();
	}

	public Item getBucket() {
		return KoratioItems.BLOOD_BUCKET.get();
	}
	
	protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
		this.fizz(level, pos);
	}
	
	public boolean canBeReplacedWith(FluidState state, BlockGetter getter, BlockPos pos, Fluid fluid, Direction direction) {
		return state.getHeight(getter, pos) >= 0.44444445F && fluid.is(FluidTags.WATER);
	}
	
	public int getSlopeFindDistance(LevelReader level) {
		return 4;
	}
	
	@Override
	public boolean isSame(Fluid fluid) {
		return fluid == KoratioFluids.BLOOD.get() || fluid == KoratioFluids.FLOWING_BLOOD.get();
	}
	
	public BlockState createLegacyBlock(FluidState state) {
		return KoratioBlocks.BLOOD.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(state)));
	}
	
	private void fizz(LevelAccessor level, BlockPos pos) {
		level.levelEvent(1501, pos, 0);
	}
	
	protected boolean canConvertToSource(Level level) {
		return false;
	}
	
	public int getDropOff(LevelReader level) {
		return 1;
	}
	
	public int getTickDelay(LevelReader level) {
	      return 15;
	   }
	
	protected float getExplosionResistance() {
		return 100.0F;
	}
	
	@Override
	public FluidType getFluidType() {
		return KoratioFluids.BLOOD_TYPE.get();
	}
	
	public static class Flowing extends BloodFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> state) {
			super.createFluidStateDefinition(state);
			state.add(LEVEL);
		}

		@Override
		protected boolean canConvertToSource(ServerLevel serverLevel) {
			return false;
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}

	public static class Source extends BloodFluid {
		@Override
		protected boolean canConvertToSource(ServerLevel serverLevel) {
			return false;
		}

		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

}