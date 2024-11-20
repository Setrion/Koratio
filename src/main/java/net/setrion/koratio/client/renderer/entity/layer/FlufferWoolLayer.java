package net.setrion.koratio.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.FlufferModel;
import net.setrion.koratio.client.model.FlufferWoolModel;
import net.setrion.koratio.client.renderer.entity.state.FlufferRenderState;

@OnlyIn(Dist.CLIENT)
public class FlufferWoolLayer extends RenderLayer<FlufferRenderState, FlufferModel> {
    private static final ResourceLocation FLUFFER_WOOL_LOCATION = Koratio.prefix("textures/entity/fluffer/fluffer_wool.png");
    private final EntityModel<FlufferRenderState> model;

    public FlufferWoolLayer(RenderLayerParent<FlufferRenderState, FlufferModel> model, EntityModelSet set) {
        super(model);
        this.model = new FlufferWoolModel(set.bakeLayer(net.setrion.koratio.registry.ModelLayers.FLUFFER_WOOL));
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, FlufferRenderState state, float p_363845_, float p_360883_) {
        if (!state.isSheared) {
            EntityModel<FlufferRenderState> entitymodel = this.model;
            if (state.isInvisible) {
                if (state.appearsGlowing) {
                    entitymodel.setupAnim(state);
                    VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.outline(FLUFFER_WOOL_LOCATION));
                    entitymodel.renderToBuffer(stack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(state, 0.0F), -16777216);
                }
            } else {
                int i;
                if (state.customName != null && "jeb_".equals(state.customName.getString())) {
                    int k = Mth.floor(state.ageInTicks);
                    int l = k / 25 + state.id;
                    int i1 = DyeColor.values().length;
                    int j1 = l % i1;
                    int k1 = (l + 1) % i1;
                    float f = ((float)(k % 25) + Mth.frac(state.ageInTicks)) / 25.0F;
                    int l1 = Sheep.getColor(DyeColor.byId(j1));
                    int i2 = Sheep.getColor(DyeColor.byId(k1));
                    i = ARGB.lerp(f, l1, i2);
                } else {
                    i = Sheep.getColor(state.woolColor);
                }

                coloredCutoutModelCopyLayerRender(entitymodel, FLUFFER_WOOL_LOCATION, stack, buffer, packedLight, state, i);
            }
        }
    }
}
