package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlockEntityType;

public class TrappedChestBlockEntity extends net.minecraft.world.level.block.entity.ChestBlockEntity {
    public TrappedChestBlockEntity(BlockPos pos, BlockState state) {
        super(KoratioBlockEntityType.TRAPPED_CHEST.get(), pos, state);
    }

    @Override
    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int newCount, int oldCount) {
        super.signalOpenCount(level, pos, state, newCount, oldCount);
        if (newCount != oldCount) {
            Block block = state.getBlock();
            level.updateNeighborsAt(pos, block);
            level.updateNeighborsAt(pos.below(), block);
        }
    }
}