package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.DemonicSoldierModel;
import net.setrion.koratio.client.renderer.entity.layer.GlowingLayer;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.demonic.DemonicSoldier;

public class DemonicSoldierRenderer extends HumanoidMobRenderer<DemonicSoldier, HumanoidRenderState, DemonicSoldierModel<HumanoidRenderState>> {

	private static final ResourceLocation DEMONIC_SOLDIER_LOCATION = Koratio.prefix("textures/entity/demonic/soldier.png");
	private static final ResourceLocation DEMONIC_SOLDIER_EYES_LOCATION = Koratio.prefix("textures/entity/demonic/soldier_eyes.png");

	public DemonicSoldierRenderer(Context context) {
		super(context, new DemonicSoldierModel<>(context.bakeLayer(ModelLayers.DEMONIC_SOLDIER)), 0.5f);
		this.addLayer(new GlowingLayer<>(this, RenderType.eyes(DEMONIC_SOLDIER_EYES_LOCATION)));
		this.addLayer(new HumanoidArmorLayer<>(this, new DemonicSoldierModel<>(context.bakeLayer(ModelLayers.DEMONIC_SOLDIER_INNER_ARMOR)), new DemonicSoldierModel<>(context.bakeLayer(ModelLayers.DEMONIC_SOLDIER_OUTER_ARMOR)), context.getEquipmentRenderer()));
	}

	@Override
	public HumanoidRenderState createRenderState() {
		return new HumanoidRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(HumanoidRenderState state) {
		return DEMONIC_SOLDIER_LOCATION;
	}
}