package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.MushroomSporeModel;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.projectile.MushroomSpore;

public class MushroomSporeRenderer extends EntityRenderer<MushroomSpore> {
	private static final ResourceLocation MUSHROOM_SPORE_LOCATION = new ResourceLocation(Koratio.MOD_ID, "textures/entity/mushroom_spore.png");
	private final MushroomSporeModel<MushroomSpore> model;

	public MushroomSporeRenderer(Context context) {
		super(context);
		this.model = new MushroomSporeModel<>(context.bakeLayer(ModelLayers.MUSHROOM_SPORE));
	}
	
	public void render(MushroomSpore spore, float p_115374_, float p_115375_, PoseStack stack, MultiBufferSource buffer, int p_115378_) {
		stack.pushPose();
		stack.translate(0.0F, -1.215F, 0.0F);
		stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_115375_, spore.yRotO, spore.getYRot()) - 90.0F));
		stack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_115375_, spore.xRotO, spore.getXRot())));
		this.model.setupAnim(spore, p_115375_, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexconsumer = buffer.getBuffer(this.model.renderType(MUSHROOM_SPORE_LOCATION));
		this.model.renderToBuffer(stack, vertexconsumer, p_115378_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		stack.popPose();
		super.render(spore, p_115374_, p_115375_, stack, buffer, p_115378_);
	}

	public ResourceLocation getTextureLocation(MushroomSpore spore) {
		return MUSHROOM_SPORE_LOCATION;
	}
}