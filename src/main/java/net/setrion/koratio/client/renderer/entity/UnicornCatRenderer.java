package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.UnicornCatModel;
import net.setrion.koratio.world.entity.animal.UnicornCat;

public class UnicornCatRenderer extends MobRenderer<UnicornCat, UnicornCatModel<UnicornCat>>{
	
	public static final ResourceLocation TEXTURES_1 = new ResourceLocation(Koratio.MOD_ID + ":textures/entity/unicorn_cat/unicorn_cat_1.png");
	public static final ResourceLocation TEXTURES_2 = new ResourceLocation(Koratio.MOD_ID + ":textures/entity/unicorn_cat/unicorn_cat_2.png");
	public static final ResourceLocation TEXTURES_3 = new ResourceLocation(Koratio.MOD_ID + ":textures/entity/unicorn_cat/unicorn_cat_3.png");
	public static final ResourceLocation TEXTURES_4 = new ResourceLocation(Koratio.MOD_ID + ":textures/entity/unicorn_cat/unicorn_cat_4.png");
	public static final ResourceLocation TEXTURES_5 = new ResourceLocation(Koratio.MOD_ID + ":textures/entity/unicorn_cat/unicorn_cat_5.png");

	public UnicornCatRenderer(Context context) {
		super(context, new UnicornCatModel<>(context.bakeLayer(new ModelLayerLocation(Koratio.prefix("unicorn_cat"), "main"))), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(UnicornCat entity) {
		switch (entity.getCatType()) {
        case 0:
        default:
            return TEXTURES_1;
        case 1:
            return TEXTURES_2;
        case 2:
            return TEXTURES_3;
        case 3:
            return TEXTURES_4;
        case 4:
            return TEXTURES_5;
		}
	}
	
	protected void setupRotations(UnicornCat unicorn_cat, PoseStack stack, float p_115016_, float p_115017_, float p_115018_) {
		super.setupRotations(unicorn_cat, stack, p_115016_, p_115017_, p_115018_);
		/*if (!((double)unicorn_cat.animationSpeed < 0.01D)) {
			float f1 = unicorn_cat.animationPosition - unicorn_cat.animationSpeed * (1.0F - p_115018_) + 6.0F;
			float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
			stack.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
		}*/
	}
}