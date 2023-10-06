package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlockEntityType;

public class CampfireBlockEntity extends net.minecraft.world.level.block.entity.CampfireBlockEntity {

	public CampfireBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return KoratioBlockEntityType.CAMPFIRE.get();
	}
}