package net.setrion.koratio.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlockEntityType;

public class SignBlockEntity extends net.minecraft.world.level.block.entity.SignBlockEntity {

	public SignBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
    public BlockEntityType<?> getType() {
        return KoratioBlockEntityType.SIGN.get();
    }
}