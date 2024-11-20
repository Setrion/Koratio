package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.GoldenFoxSpiritModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.GoldenFoxSpirit;

public class GoldenFoxSpiritRenderer extends MobRenderer<GoldenFoxSpirit, LivingEntityRenderState, GoldenFoxSpiritModel>{

	public static final ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/golden_fox_spirit.png");

	public GoldenFoxSpiritRenderer(Context context) {
		super(context, new GoldenFoxSpiritModel(context.bakeLayer(ModelLayers.GOLDEN_FOX_SPIRIT)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return TEXTURES;
	}
}