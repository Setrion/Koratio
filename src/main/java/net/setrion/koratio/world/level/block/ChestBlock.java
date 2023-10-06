package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlockEntityType;
import net.setrion.koratio.world.level.block.entity.ChestBlockEntity;

public class ChestBlock extends net.minecraft.world.level.block.ChestBlock {
	public ChestBlock(Properties properties) {
		super(properties, KoratioBlockEntityType.CHEST::get);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ChestBlockEntity(pos, state);
	}
}