package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.SpikyPigModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.SpikyPig;

public class SpikyPigRenderer extends MobRenderer<SpikyPig, PigRenderState, SpikyPigModel>{
	
	public static final ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/spiky_pig.png");

	public SpikyPigRenderer(Context context) {
		super(context, new SpikyPigModel(context.bakeLayer(ModelLayers.SPIKY_PIG)), 0.5f);
		this.addLayer(new SaddleLayer<>(this, new SpikyPigModel(context.bakeLayer(net.minecraft.client.model.geom.ModelLayers.PIG_SADDLE)), ResourceLocation.withDefaultNamespace("textures/entity/pig/pig_saddle.png")));
	}

	@Override
	public PigRenderState createRenderState() {
		return new PigRenderState();
	}

	public void extractRenderState(SpikyPig pig, PigRenderState state, float f) {
		super.extractRenderState(pig, state, f);
		state.isSaddled = pig.isSaddled();
	}

	@Override
	public ResourceLocation getTextureLocation(PigRenderState state) {
		return TEXTURES;
	}
}