package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlockEntityType;

public class ChestBlockEntity extends net.minecraft.world.level.block.entity.ChestBlockEntity {
	public ChestBlockEntity(BlockPos pos, BlockState state) {
		super(KoratioBlockEntityType.CHEST.get(), pos, state);
	}
}