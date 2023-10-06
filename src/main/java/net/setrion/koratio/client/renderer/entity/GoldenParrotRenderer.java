package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.GoldenParrotModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.GoldenParrot;

public class GoldenParrotRenderer extends MobRenderer<GoldenParrot, GoldenParrotModel> {
	private static final ResourceLocation PARROT_LOCATION = new ResourceLocation(Koratio.MOD_ID, "textures/entity/golden_parrot.png");

	public GoldenParrotRenderer(EntityRendererProvider.Context context) {
		super(context, new GoldenParrotModel(context.bakeLayer(ModelLayers.GOLDEN_PARROT)), 0.3F);
	}

	public ResourceLocation getTextureLocation(GoldenParrot chicken) {
		return PARROT_LOCATION;
	}
	
	public float getBob(GoldenParrot parrot, float fl) {
		float f = Mth.lerp(fl, parrot.oFlap, parrot.flap);
		float f1 = Mth.lerp(fl, parrot.oFlapSpeed, parrot.flapSpeed);
		return (Mth.sin(f) + 1.0F) * f1;
	}
}