package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.setrion.koratio.world.entity.projectile.RevivingSoul;

public class RevivingSoulRenderer extends EntityRenderer<RevivingSoul, EntityRenderState> {

	public RevivingSoulRenderer(Context context) {
		super(context);
	}

	@Override
	public void render(EntityRenderState state, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		super.render(state, stack, buffer, packedLight);
	}

	@Override
	public EntityRenderState createRenderState() {
		return new EntityRenderState();
	}
}