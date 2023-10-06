package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.setrion.koratio.world.level.block.entity.HangingSignBlockEntity;

public class WallHangingSignBlock extends net.minecraft.world.level.block.WallHangingSignBlock {

	public WallHangingSignBlock(Properties property, WoodType type) {
		super(property, type);
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HangingSignBlockEntity(pos, state);
	}
}