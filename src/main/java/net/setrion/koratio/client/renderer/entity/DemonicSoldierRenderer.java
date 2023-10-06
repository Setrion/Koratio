package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.DemonicSoldierModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.monster.DemonicSoldier;

public class DemonicSoldierRenderer extends HumanoidMobRenderer<DemonicSoldier, DemonicSoldierModel<DemonicSoldier>> {
	
	private static final ResourceLocation DEMONIC_SOLDIER_LOCATION = new ResourceLocation(Koratio.MOD_ID, "textures/entity/demon/soldier.png");

	public DemonicSoldierRenderer(Context context) {
		super(context, new DemonicSoldierModel<>(context.bakeLayer(ModelLayers.DEMONIC_SOLDIER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer<>(this, new DemonicSoldierModel<>(context.bakeLayer(ModelLayers.DEMONIC_SOLDIER_INNER_ARMOR)), new DemonicSoldierModel<>(context.bakeLayer(ModelLayers.DEMONIC_SOLDIER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(DemonicSoldier entity) {
		return DEMONIC_SOLDIER_LOCATION;
	}
}