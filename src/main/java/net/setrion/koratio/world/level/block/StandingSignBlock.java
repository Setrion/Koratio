package net.setrion.koratio.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.setrion.koratio.world.level.block.entity.SignBlockEntity;

public class StandingSignBlock extends net.minecraft.world.level.block.StandingSignBlock {
	
	public StandingSignBlock(WoodType type, BlockBehaviour.Properties property) {
		super(property, type);
		this.registerDefaultState(this.stateDefinition.any().setValue(ROTATION, Integer.valueOf(0)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SignBlockEntity(pos, state);
	}
}