package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.client.renderer.entity.state.LevitatingBlockRenderState;
import net.setrion.koratio.world.entity.item.LevitatingBlockEntity;

@OnlyIn(Dist.CLIENT)
public class LevitatingBlockRenderer extends EntityRenderer<LevitatingBlockEntity, LevitatingBlockRenderState> {
    private final BlockRenderDispatcher dispatcher;

    public LevitatingBlockRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.dispatcher = context.getBlockRenderDispatcher();
    }

    public boolean shouldRender(LevitatingBlockEntity block, Frustum frustum, double x, double y, double z) {
        return super.shouldRender(block, frustum, x, y, z) && block.getBlockState() != block.level().getBlockState(block.blockPosition());
    }

    public void render(LevitatingBlockRenderState state, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        BlockState blockstate = state.blockState;
        if (blockstate.getRenderShape() == RenderShape.MODEL) {
            stack.pushPose();
            stack.translate(-0.5, 0.0, -0.5);
            var model = this.dispatcher.getBlockModel(blockstate);
            for (var renderType : model.getRenderTypes(blockstate, RandomSource.create(blockstate.getSeed(state.startBlockPos)), net.neoforged.neoforge.client.model.data.ModelData.EMPTY))
                this.dispatcher
                        .getModelRenderer()
                        .tesselateBlock(
                                state,
                                this.dispatcher.getBlockModel(blockstate),
                                blockstate,
                                state.blockPos,
                                stack,
                                buffer.getBuffer(net.neoforged.neoforge.client.RenderTypeHelper.getMovingBlockRenderType(renderType)),
                                false,
                                RandomSource.create(),
                                blockstate.getSeed(state.startBlockPos),
                                OverlayTexture.NO_OVERLAY,
                                net.neoforged.neoforge.client.model.data.ModelData.EMPTY,
                                renderType
                        );
            stack.popPose();
            super.render(state, stack, buffer, packedLight);
        }
    }

    public LevitatingBlockRenderState createRenderState() {
        return new LevitatingBlockRenderState();
    }

    public void extractRenderState(LevitatingBlockEntity block, LevitatingBlockRenderState state, float f) {
        super.extractRenderState(block, state, f);
        BlockPos blockpos = BlockPos.containing(block.getX(), block.getBoundingBox().maxY, block.getZ());
        state.startBlockPos = block.getStartPos();
        state.blockPos = blockpos;
        state.blockState = block.getBlockState();
        state.biome = block.level().getBiome(blockpos);
        state.level = block.level();
    }
}
