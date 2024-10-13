package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.MagicalCatModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.animal.MagicalCat;

public class MagicalCatRenderer extends MobRenderer<MagicalCat, MagicalCatModel<MagicalCat>>{
	
	public static final ResourceLocation TEXTURES_1 = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/magical_cat/red.png");
	public static final ResourceLocation TEXTURES_2 = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/magical_cat/blue.png");
	public static final ResourceLocation TEXTURES_3 = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/magical_cat/yellow.png");
	public static final ResourceLocation TEXTURES_4 = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/magical_cat/green.png");
	public static final ResourceLocation TEXTURES_5 = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/magical_cat/black.png");
	public static final ResourceLocation TEXTURES_6 = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/magical_cat/pink.png");
	public static final ResourceLocation TEXTURES_7 = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/magical_cat/purple.png");
	public static final ResourceLocation TEXTURES_8 = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/magical_cat/orange.png");

	public MagicalCatRenderer(Context context) {
		super(context, new MagicalCatModel<>(context.bakeLayer(ModelLayers.MAGICAL_CAT)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(MagicalCat entity) {
        return switch (entity.getCatType()) {
            default -> TEXTURES_1;
            case 1 -> TEXTURES_2;
            case 2 -> TEXTURES_3;
            case 3 -> TEXTURES_4;
            case 4 -> TEXTURES_5;
            case 5 -> TEXTURES_6;
            case 6 -> TEXTURES_7;
            case 7 -> TEXTURES_8;
        };
	}
}