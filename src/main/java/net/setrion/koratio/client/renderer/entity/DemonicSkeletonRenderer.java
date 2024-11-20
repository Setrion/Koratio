package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.DemonicSkeletonModel;
import net.setrion.koratio.client.renderer.entity.layer.GlowingLayer;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.demonic.DemonicSkeleton;

public class DemonicSkeletonRenderer extends HumanoidMobRenderer<DemonicSkeleton, SkeletonRenderState, DemonicSkeletonModel<SkeletonRenderState>> {

	private static final ResourceLocation DEMONIC_SKELETON_LOCATION = Koratio.prefix("textures/entity/demonic/skeleton.png");
	private static final ResourceLocation DEMONIC_SKELETON_EYES_LOCATION = Koratio.prefix("textures/entity/demonic/skeleton_eyes.png");

	public DemonicSkeletonRenderer(Context context) {
		super(context, new DemonicSkeletonModel<>(context.bakeLayer(ModelLayers.DEMONIC_SKELETON)), 0.5f);
		this.addLayer(new GlowingLayer<>(this, RenderType.eyes(DEMONIC_SKELETON_EYES_LOCATION)));
		this.addLayer(new HumanoidArmorLayer<>(this, new DemonicSkeletonModel<>(context.bakeLayer(ModelLayers.DEMONIC_SKELETON_INNER_ARMOR)), new DemonicSkeletonModel<>(context.bakeLayer(ModelLayers.DEMONIC_SKELETON_OUTER_ARMOR)), context.getEquipmentRenderer()));
	}

	@Override
	public SkeletonRenderState createRenderState() {
		return new SkeletonRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(SkeletonRenderState state) {
		return DEMONIC_SKELETON_LOCATION;
	}
}