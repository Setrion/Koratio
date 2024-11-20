package net.setrion.koratio.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.client.renderer.entity.state.MooshroomRenderState;

@OnlyIn(Dist.CLIENT)
public class MooshroomMushroomLayer extends RenderLayer<MooshroomRenderState, CowModel> {
    private final BlockRenderDispatcher blockRenderer;

    public MooshroomMushroomLayer(RenderLayerParent<MooshroomRenderState, CowModel> renderer, BlockRenderDispatcher blockRenderer) {
        super(renderer);
        this.blockRenderer = blockRenderer;
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, MooshroomRenderState state, float limbSwing, float limbSwingAmount) {
        if (!state.isBaby) {
            boolean flag = state.appearsGlowing && state.isInvisible;
            if (!state.isInvisible || flag) {
                BlockState blockstate = state.variant.getBlockState();
                int i = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
                BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, buffer, packedLight, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, buffer, packedLight, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, buffer, packedLight, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
            }
        }

    }

    private void renderMushroomBlock(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean outlineOnly, BlockState state, int packedOverlay, BakedModel model) {
        if (outlineOnly) {
            this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), buffer.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), state, model, 0.0F, 0.0F, 0.0F, packedLight, packedOverlay);
        } else {
            this.blockRenderer.renderSingleBlock(state, poseStack, buffer, packedLight, packedOverlay);
        }
    }
}