package net.setrion.koratio.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.FlufferModel;
import net.setrion.koratio.client.model.FlufferWoolModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.Fluffer;

@OnlyIn(Dist.CLIENT)
public class FlufferWoolLayer extends RenderLayer<Fluffer, FlufferModel<Fluffer>> {
    private static final ResourceLocation FLUFFER_WOOL_LOCATION = Koratio.prefix("textures/entity/fluffer/fluffer_wool.png");
    private final FlufferWoolModel<Fluffer> model;

    public FlufferWoolLayer(RenderLayerParent<Fluffer, FlufferModel<Fluffer>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new FlufferWoolModel<>(modelSet.bakeLayer(ModelLayers.FLUFFER_WOOL));
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Fluffer livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.isSheared()) {
            if (livingEntity.isInvisible()) {
                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = minecraft.shouldEntityAppearGlowing(livingEntity);
                if (flag) {
                    this.getParentModel().copyPropertiesTo(this.model);
                    this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
                    this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.outline(FLUFFER_WOOL_LOCATION));
                    this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F), -16777216);
                }
            } else {
                int i;
                if (livingEntity.hasCustomName() && "jeb_".equals(livingEntity.getName().getString())) {
                    int k = livingEntity.tickCount / 25 + livingEntity.getId();
                    int l = DyeColor.values().length;
                    int i1 = k % l;
                    int j1 = (k + 1) % l;
                    float f = ((float)(livingEntity.tickCount % 25) + partialTicks) / 25.0F;
                    int k1 = Fluffer.getColor(DyeColor.byId(i1));
                    int l1 = Fluffer.getColor(DyeColor.byId(j1));
                    i = FastColor.ARGB32.lerp(f, k1, l1);
                } else {
                    i = Fluffer.getColor(livingEntity.getColor());
                }

                coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, FLUFFER_WOOL_LOCATION, poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, i);
            }
        }
    }
}
