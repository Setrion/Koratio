package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.model.MushroomSporeModel;
import net.setrion.koratio.client.renderer.entity.state.MushroomSporeRenderState;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.projectile.MushroomSpore;

public class MushroomSporeRenderer extends EntityRenderer<MushroomSpore, MushroomSporeRenderState> {
	private static final ResourceLocation MUSHROOM_SPORE_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/entity/mushroom_spore.png");
	private final MushroomSporeModel model;

	public MushroomSporeRenderer(Context context) {
		super(context);
		this.model = new MushroomSporeModel(context.bakeLayer(ModelLayers.MUSHROOM_SPORE));
	}
	
	public void render(MushroomSporeRenderState state, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		stack.pushPose();
		stack.translate(0.0F, -1.215F, 0.0F);
		stack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
		stack.mulPose(Axis.ZP.rotationDegrees(state.xRot));
		this.model.setupAnim(state);
		VertexConsumer vertexconsumer = buffer.getBuffer(this.model.renderType(MUSHROOM_SPORE_LOCATION));
		this.model.renderToBuffer(stack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
		stack.popPose();
		super.render(state, stack, buffer, packedLight);
	}

	@Override
	public MushroomSporeRenderState createRenderState() {
		return new MushroomSporeRenderState();
	}

	public void extractRenderState(MushroomSpore spore, MushroomSporeRenderState state, float f) {
		super.extractRenderState(spore, state, f);
		state.xRot = spore.getXRot(f);
		state.yRot = spore.getYRot(f);
	}
}