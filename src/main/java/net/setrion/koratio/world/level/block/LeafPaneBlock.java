package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class LeafPaneBlock extends IronBarsBlock {

    public LeafPaneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor accessor, BlockPos pos, BlockPos newPos) {
        if (state.getValue(WATERLOGGED)) {
            accessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }

        return direction.getAxis().isHorizontal() ? state.setValue(PROPERTY_BY_DIRECTION.get(direction), Boolean.valueOf(this.attachsToBlock(newState, newState.isFaceSturdy(accessor, newPos, direction.getOpposite())))) : super.updateShape(state, direction, newState, accessor, pos, newPos);
    }

    public final boolean attachsToBlock(BlockState state, boolean isSturdy) {
        return attachsTo(state, isSturdy) || state.is(BlockTags.LEAVES);
    }
}