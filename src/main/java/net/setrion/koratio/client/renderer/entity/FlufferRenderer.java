package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.FlufferModel;
import net.setrion.koratio.client.renderer.entity.layer.FlufferWoolLayer;
import net.setrion.koratio.client.renderer.entity.state.FlufferRenderState;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.Fluffer;

@OnlyIn(Dist.CLIENT)
public class FlufferRenderer extends MobRenderer<Fluffer, FlufferRenderState, FlufferModel> {
    private static final ResourceLocation FLUFFER_LOCATION = Koratio.prefix("textures/entity/fluffer/fluffer.png");

    public FlufferRenderer(EntityRendererProvider.Context context) {
        super(context, new FlufferModel(context.bakeLayer(ModelLayers.FLUFFER)), 0.7F);
        this.addLayer(new FlufferWoolLayer(this, context.getModelSet()));
    }

    @Override
    public FlufferRenderState createRenderState() {
        return new FlufferRenderState();
    }

    @Override
    public void extractRenderState(Fluffer entity, FlufferRenderState state, float f) {
        super.extractRenderState(entity, state, f);
        state.isSheared = entity.isSheared();
        state.woolColor = entity.getColor();
    }

    public ResourceLocation getTextureLocation(FlufferRenderState state) {
        return FLUFFER_LOCATION;
    }
}