package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlockEntityType;

public class RemainsBlockEntity extends BlockEntity {

    public RemainsBlockEntity(BlockPos pos, BlockState blockState) {
        super(KoratioBlockEntityType.REMAINS.get(), pos, blockState);
    }
}