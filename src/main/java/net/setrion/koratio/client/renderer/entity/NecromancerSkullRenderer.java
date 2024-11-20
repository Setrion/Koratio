package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.NecromancerSkullModel;
import net.setrion.koratio.client.renderer.entity.layer.GlowingLayer;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.demonic.NecromancerSkull;

public class NecromancerSkullRenderer extends MobRenderer<NecromancerSkull, LivingEntityRenderState, NecromancerSkullModel> {

	private static final ResourceLocation NECROMANCER_SKULL_LOCATION = Koratio.prefix("textures/entity/demonic/necromancer_skull.png");
	private static final ResourceLocation NECROMANCER_SKULL_EYES_LOCATION = Koratio.prefix("textures/entity/demonic/necromancer_skull_eyes.png");

	public NecromancerSkullRenderer(Context context) {
		super(context, new NecromancerSkullModel(context.bakeLayer(ModelLayers.NECROMANCER_SKULL)), 0.5f);
		this.addLayer(new GlowingLayer<>(this, RenderType.eyes(NECROMANCER_SKULL_EYES_LOCATION)));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return NECROMANCER_SKULL_LOCATION;
	}
}