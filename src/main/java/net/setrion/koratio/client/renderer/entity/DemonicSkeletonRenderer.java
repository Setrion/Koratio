package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.DemonicSkeletonModel;
import net.setrion.koratio.client.renderer.entity.layer.GlowingLayer;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.demonic.DemonicSkeleton;

public class DemonicSkeletonRenderer extends HumanoidMobRenderer<DemonicSkeleton, DemonicSkeletonModel<DemonicSkeleton>> {

	private static final ResourceLocation DEMONIC_SKELETON_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/demon/skeleton.png");
	private static final ResourceLocation DEMONIC_SKELETON_EYES_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/demon/skeleton_eyes.png");

	public DemonicSkeletonRenderer(Context context) {
		super(context, new DemonicSkeletonModel<>(context.bakeLayer(ModelLayers.DEMONIC_SKELETON)), 0.5f);
		this.addLayer(new GlowingLayer<>(this, DEMONIC_SKELETON_EYES_LOCATION));
		this.addLayer(new HumanoidArmorLayer<>(this, new DemonicSkeletonModel<>(context.bakeLayer(ModelLayers.DEMONIC_SKELETON_INNER_ARMOR)), new DemonicSkeletonModel<>(context.bakeLayer(ModelLayers.DEMONIC_SKELETON_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(DemonicSkeleton entity) {
		return DEMONIC_SKELETON_LOCATION;
	}
}