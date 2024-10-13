package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.GingerbreadManModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.monster.GingerbreadMan;

public class GingerbreadManRenderer extends MobRenderer<GingerbreadMan, GingerbreadManModel<GingerbreadMan>> {
	
	private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/gingerbread_man.png");

	public GingerbreadManRenderer(Context context) {
		super(context, new GingerbreadManModel<>(context.bakeLayer(ModelLayers.GINGERBREAD_MAN)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(GingerbreadMan entity) {
		return TEXTURE_LOCATION;
	}
}