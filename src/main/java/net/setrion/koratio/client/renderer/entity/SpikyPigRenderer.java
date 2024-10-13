package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.SpikyPigModel;
import net.setrion.koratio.world.entity.animal.SpikyPig;

public class SpikyPigRenderer extends MobRenderer<SpikyPig, SpikyPigModel<SpikyPig>>{
	
	public static final ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/spiky_pig.png");

	public SpikyPigRenderer(Context context) {
		super(context, new SpikyPigModel<>(context.bakeLayer(new ModelLayerLocation(Koratio.prefix("spiky_pig"), "main"))), 0.5f);
		this.addLayer(new SaddleLayer<>(this, new SpikyPigModel<>(context.bakeLayer(ModelLayers.PIG_SADDLE)), ResourceLocation.withDefaultNamespace("textures/entity/pig/pig_saddle.png")));
	}

	@Override
	public ResourceLocation getTextureLocation(SpikyPig entity) {
		return TEXTURES;
	}
}