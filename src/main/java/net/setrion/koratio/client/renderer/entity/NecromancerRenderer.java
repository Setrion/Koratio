package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.NecromancerModel;
import net.setrion.koratio.client.renderer.entity.layer.GlowingLayer;
import net.setrion.koratio.client.renderer.entity.layer.NecromancerOuterLayer;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.demonic.Necromancer;

public class NecromancerRenderer extends HumanoidMobRenderer<Necromancer, HumanoidRenderState, NecromancerModel<HumanoidRenderState>> {

	private static final ResourceLocation NECROMANCER_LOCATION = Koratio.prefix("textures/entity/demonic/necromancer.png");
	private static final ResourceLocation NECROMANCER_EYES_LOCATION = Koratio.prefix("textures/entity/demonic/necromancer_eyes.png");

	public NecromancerRenderer(Context context) {
		super(context, new NecromancerModel<>(context.bakeLayer(ModelLayers.NECROMANCER)), 0.5f);
		this.addLayer(new GlowingLayer<>(this, RenderType.eyes(NECROMANCER_EYES_LOCATION)));
		this.addLayer(new NecromancerOuterLayer<>(this, context.getModelSet()));
	}

	@Override
	public HumanoidRenderState createRenderState() {
		return new HumanoidRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(HumanoidRenderState state) {
		return NECROMANCER_LOCATION;
	}
}