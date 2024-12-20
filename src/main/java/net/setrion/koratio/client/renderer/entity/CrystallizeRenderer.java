package net.setrion.koratio.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.CrystallizeModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.monster.Crystallize;

public class CrystallizeRenderer extends MobRenderer<Crystallize, LivingEntityRenderState, CrystallizeModel> {

	public CrystallizeRenderer(Context context) {
		super(context, new CrystallizeModel(context.bakeLayer(ModelLayers.CRYSTALLIZE)), 0.25F);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/crystallize.png");
	}
}