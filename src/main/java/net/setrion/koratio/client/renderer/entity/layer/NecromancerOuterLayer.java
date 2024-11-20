package net.setrion.koratio.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.NecromancerModel;
import net.setrion.koratio.registry.ModelLayers;

public class NecromancerOuterLayer<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {
    private static final ResourceLocation NECROMANCER_OUTER_LAYER_LOCATION = Koratio.prefix("textures/entity/demonic/necromancer_outer.png");

    private final NecromancerModel<S> layerModel;

    public NecromancerOuterLayer(RenderLayerParent<S, M> renderer, EntityModelSet models) {
        super(renderer);
        this.layerModel = new NecromancerModel<>(models.bakeLayer(ModelLayers.NECROMANCER_OUTER_LAYER));
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, S state, float p_326921_, float p_326877_) {
        coloredCutoutModelCopyLayerRender(this.layerModel, NECROMANCER_OUTER_LAYER_LOCATION, stack, buffer, packedLight, state, -1);
    }
}
