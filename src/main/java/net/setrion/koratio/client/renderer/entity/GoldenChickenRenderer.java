package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.GoldenChicken;

public class GoldenChickenRenderer extends MobRenderer<GoldenChicken, ChickenModel<GoldenChicken>> {
	private static final ResourceLocation CHICKEN_LOCATION = new ResourceLocation(Koratio.MOD_ID, "textures/entity/golden_chicken.png");

	public GoldenChickenRenderer(EntityRendererProvider.Context context) {
		super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.GOLDEN_CHICKEN)), 0.3F);
	}

	public ResourceLocation getTextureLocation(GoldenChicken chicken) {
		return CHICKEN_LOCATION;
	}

	protected float getBob(GoldenChicken chicken, float fl) {
		float f = Mth.lerp(fl, chicken.oFlap, chicken.flap);
		float f1 = Mth.lerp(fl, chicken.oFlapSpeed, chicken.flapSpeed);
		return (Mth.sin(f) + 1.0F) * f1;
	}
}