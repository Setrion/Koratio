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

public class DemonicZombieHorseRenderer extends AbstractHorseRenderer<AbstractHorse, EquineRenderState, AbstractEquineModel<EquineRenderState>> {

    private static final ResourceLocation DEMONIC_ZOMBIE_HORSE_LOCATION = Koratio.prefix("textures/entity/demonic/zombie_horse.png");
    private static final ResourceLocation DEMONIC_ZOMBIE_HORSE_EYES_LOCATION = Koratio.prefix("textures/entity/demonic/zombie_horse_eyes.png");

    public DemonicZombieHorseRenderer(EntityRendererProvider.Context context) {
        super(context, new HorseModel(context.bakeLayer(ModelLayers.DEMONIC_ZOMBIE_HORSE)), new HorseModel(context.bakeLayer(ModelLayers.DEMONIC_ZOMBIE_HORSE_BABY)), 1.0F);
        this.addLayer(new GlowingLayer<>(this, RenderType.eyes(DEMONIC_ZOMBIE_HORSE_EYES_LOCATION)));
    }

    @Override
    public EquineRenderState createRenderState() {
        return new EquineRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(EquineRenderState state) {
        return DEMONIC_ZOMBIE_HORSE_LOCATION;
    }
}
