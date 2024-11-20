package net.setrion.koratio.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowingLayer<S extends EntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    private final RenderType renderType;

    public GlowingLayer(RenderLayerParent<S, M> renderer, RenderType renderType) {
        super(renderer);
        this.renderType = renderType;
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, S state, float p_116987_, float p_116988_) {
        VertexConsumer vertexconsumer = buffer.getBuffer(this.renderType);
        this.getParentModel().renderToBuffer(stack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }
}
