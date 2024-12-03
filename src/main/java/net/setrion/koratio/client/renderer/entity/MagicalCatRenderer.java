package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.client.model.MagicalCatModel;
import net.setrion.koratio.client.renderer.entity.layer.MagicalCatCollarLayer;
import net.setrion.koratio.client.renderer.entity.state.MagicalCatRenderState;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.MagicalCat;

public class MagicalCatRenderer extends AgeableMobRenderer<MagicalCat, MagicalCatRenderState, MagicalCatModel> {

	public MagicalCatRenderer(Context context) {
		super(context, new MagicalCatModel(context.bakeLayer(ModelLayers.MAGICAL_CAT)), new MagicalCatModel(context.bakeLayer(ModelLayers.BABY_MAGICAL_CAT)), 0.5f);
		this.addLayer(new MagicalCatCollarLayer(this, context.getModelSet()));
	}

	@Override
	public MagicalCatRenderState createRenderState() {
		return new MagicalCatRenderState();
	}

	public void extractRenderState(MagicalCat cat, MagicalCatRenderState state, float f) {
		super.extractRenderState(cat, state, f);
		state.texture = cat.getVariant().value().texture();
		state.isSitting = cat.isInSittingPose();
		state.collarColor = cat.isTame() ? cat.getCollarColor() : null;
	}

	@Override
	public ResourceLocation getTextureLocation(MagicalCatRenderState state) {
        return state.texture;
	}
}