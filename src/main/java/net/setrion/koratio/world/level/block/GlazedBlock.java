package net.setrion.koratio.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.registry.KoratioBlockEntityType;
import org.jetbrains.annotations.Nullable;

public class GlazedBlock extends BaseEntityBlock {

    public static final MapCodec<GlazedBlock> CODEC = simpleCodec(GlazedBlock::new);

    public GlazedBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return KoratioBlockEntityType.GLAZED_BLOCK.get().create(blockPos, blockState);
    }
}