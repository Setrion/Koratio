package net.setrion.koratio.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.MagicalCatModel;
import net.setrion.koratio.client.renderer.entity.state.MagicalCatRenderState;
import net.setrion.koratio.registry.ModelLayers;

@OnlyIn(Dist.CLIENT)
public class MagicalCatCollarLayer extends RenderLayer<MagicalCatRenderState, MagicalCatModel> {
    private static final ResourceLocation MAGICAL_CAT_COLLAR_LOCATION = Koratio.prefix("textures/entity/magical_cat/magical_cat_collar.png");
    private final MagicalCatModel adultModel;
    private final MagicalCatModel babyModel;

    public MagicalCatCollarLayer(RenderLayerParent<MagicalCatRenderState, MagicalCatModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.adultModel = new MagicalCatModel(modelSet.bakeLayer(ModelLayers.MAGICAL_CAT_COLLAR));
        this.babyModel = new MagicalCatModel(modelSet.bakeLayer(ModelLayers.BABY_MAGICAL_CAT_COLLAR));
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int p_116668_, MagicalCatRenderState state, float p_116670_, float p_116671_) {
        DyeColor dyecolor = state.collarColor;
        if (dyecolor != null) {
            int i = dyecolor.getTextureDiffuseColor();
            MagicalCatModel catmodel = state.isBaby ? this.babyModel : this.adultModel;
            coloredCutoutModelCopyLayerRender(catmodel, MAGICAL_CAT_COLLAR_LOCATION, stack, buffer, p_116668_, state, i);
        }

    }
}