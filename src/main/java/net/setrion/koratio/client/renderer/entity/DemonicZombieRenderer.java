package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.DemonicZombieModel;
import net.setrion.koratio.client.renderer.entity.layer.GlowingLayer;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.demonic.DemonicZombie;

public class DemonicZombieRenderer extends HumanoidMobRenderer<DemonicZombie, ZombieRenderState, DemonicZombieModel<ZombieRenderState>> {

	private static final ResourceLocation DEMONIC_ZOMBIE_LOCATION = Koratio.prefix("textures/entity/demonic/zombie.png");
	private static final ResourceLocation DEMONIC_ZOMBIE_EYES_LOCATION = Koratio.prefix("textures/entity/demonic/zombie_eyes.png");

	public DemonicZombieRenderer(Context context) {
		super(context, new DemonicZombieModel<>(context.bakeLayer(ModelLayers.DEMONIC_ZOMBIE)), 0.5f);
		this.addLayer(new GlowingLayer<>(this, RenderType.eyes(DEMONIC_ZOMBIE_EYES_LOCATION)));
		this.addLayer(new HumanoidArmorLayer<>(this, new DemonicZombieModel<>(context.bakeLayer(ModelLayers.DEMONIC_ZOMBIE_INNER_ARMOR)), new DemonicZombieModel<>(context.bakeLayer(ModelLayers.DEMONIC_ZOMBIE_OUTER_ARMOR)), context.getEquipmentRenderer()));
	}

	@Override
	public ZombieRenderState createRenderState() {
		return new ZombieRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieRenderState state) {
		return DEMONIC_ZOMBIE_LOCATION;
	}
}