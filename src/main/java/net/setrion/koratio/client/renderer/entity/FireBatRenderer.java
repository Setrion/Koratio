package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.KoratioBatModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.monster.AbstractHostileBat;

@OnlyIn(Dist.CLIENT)
public class FireBatRenderer extends MobRenderer<AbstractHostileBat, KoratioBatModel> {
	private static final ResourceLocation FIRE_BAT_LOCATION = new ResourceLocation(Koratio.MOD_ID, "textures/entity/fire_bat.png");

	public FireBatRenderer(EntityRendererProvider.Context context) {
		super(context, new KoratioBatModel(context.bakeLayer(ModelLayers.FIRE_BAT)), 0.25F);
	}

	public ResourceLocation getTextureLocation(AbstractHostileBat fire_bat) {
		return FIRE_BAT_LOCATION;
	}

	protected void scale(AbstractHostileBat fire_bat, PoseStack stack, float scale) {
		stack.scale(0.35F, 0.35F, 0.35F);
	}

	protected void setupRotations(AbstractHostileBat fire_bat, PoseStack stack, float x, float y, float z) {
		if (fire_bat.isResting()) {
			stack.translate(0.0D, (double)-0.1F, 0.0D);
		} else {
			stack.translate(0.0D, (double)(Mth.cos(x * 0.3F) * 0.1F), 0.0D);
		}

		super.setupRotations(fire_bat, stack, x, y, z);
	}
}