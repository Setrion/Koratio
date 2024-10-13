package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.JumStemModel;
import net.setrion.koratio.client.renderer.entity.layer.JumStemMushroomLayer;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.monster.JumStem;

public class JumStemRenderer extends MobRenderer<JumStem, JumStemModel<JumStem>> {

	public JumStemRenderer(Context context) {
		super(context, new JumStemModel<>(context.bakeLayer(ModelLayers.JUMSTEM)), 0.25F);
		this.addLayer(new JumStemMushroomLayer<>(this, context.getBlockRenderDispatcher()));
	}
	
	protected void scale(JumStem jumstem, PoseStack stack, float f) {
		stack.scale(0.999F, 0.999F, 0.999F);
		stack.translate(0.0F, 0.001F, 0.0F);
		float f2 = Mth.lerp(f, jumstem.oSquish, jumstem.squish) / (0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		stack.scale(f3, 1.0F / f3, f3);
	}

	@Override
	public ResourceLocation getTextureLocation(JumStem jumstem) {
		return ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/jumstem/"+jumstem.getVariant().getName()+".png");
	}
}