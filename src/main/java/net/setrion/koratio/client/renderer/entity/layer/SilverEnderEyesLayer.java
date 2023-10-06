package net.setrion.koratio.client.renderer.entity.layer;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.setrion.koratio.Koratio;

public class SilverEnderEyesLayer<T extends LivingEntity> extends EyesLayer<T, EndermanModel<T>> {
	private static final RenderType ENDERMAN_EYES = RenderType.eyes(new ResourceLocation(Koratio.MOD_ID, "textures/entity/silver_enderman/silver_enderman_eyes.png"));

	public SilverEnderEyesLayer(RenderLayerParent<T, EndermanModel<T>> parent) {
		super(parent);
	}

	public RenderType renderType() {
		return ENDERMAN_EYES;
	}
}