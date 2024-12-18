package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.model.AbstractEquineModel;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.renderer.entity.layer.GlowingLayer;
import net.setrion.koratio.registry.ModelLayers;

public class DemonicSkeletonHorseRenderer extends AbstractHorseRenderer<AbstractHorse, EquineRenderState, AbstractEquineModel<EquineRenderState>> {

    private static final ResourceLocation DEMONIC_SKELETON_HORSE_LOCATION = Koratio.prefix("textures/entity/demonic/skeleton_horse.png");
    private static final ResourceLocation DEMONIC_SKELETON_HORSE_EYES_LOCATION = Koratio.prefix("textures/entity/demonic/skeleton_horse_eyes.png");

    public DemonicSkeletonHorseRenderer(EntityRendererProvider.Context context) {
        super(context, new HorseModel(context.bakeLayer(ModelLayers.DEMONIC_SKELETON_HORSE)), new HorseModel(context.bakeLayer(ModelLayers.DEMONIC_SKELETON_HORSE_BABY)));
        this.addLayer(new GlowingLayer<>(this, RenderType.eyes(DEMONIC_SKELETON_HORSE_EYES_LOCATION)));
    }

    @Override
    public EquineRenderState createRenderState() {
        return new EquineRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(EquineRenderState state) {
        return DEMONIC_SKELETON_HORSE_LOCATION;
    }
}
