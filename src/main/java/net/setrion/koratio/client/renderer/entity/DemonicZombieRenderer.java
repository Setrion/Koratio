package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.DemonicZombieModel;
import net.setrion.koratio.client.renderer.entity.layer.GlowingLayer;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.demonic.DemonicZombie;

public class DemonicZombieRenderer extends HumanoidMobRenderer<DemonicZombie, DemonicZombieModel<DemonicZombie>> {

	private static final ResourceLocation DEMONIC_ZOMBIE_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/demon/zombie.png");
	private static final ResourceLocation DEMONIC_ZOMBIE_EYES_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/demon/zombie_eyes.png");

	public DemonicZombieRenderer(Context context) {
		super(context, new DemonicZombieModel<>(context.bakeLayer(ModelLayers.DEMONIC_ZOMBIE)), 0.5f);
		this.addLayer(new GlowingLayer<>(this, DEMONIC_ZOMBIE_EYES_LOCATION));
		this.addLayer(new HumanoidArmorLayer<>(this, new DemonicZombieModel<>(context.bakeLayer(ModelLayers.DEMONIC_ZOMBIE_INNER_ARMOR)), new DemonicZombieModel<>(context.bakeLayer(ModelLayers.DEMONIC_ZOMBIE_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(DemonicZombie entity) {
		return DEMONIC_ZOMBIE_LOCATION;
	}
}