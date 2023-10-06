package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.world.entity.monster.SilverEnderMan;

public class SilverCarriedBlockLayer extends RenderLayer<SilverEnderMan, EndermanModel<SilverEnderMan>> {
	private final BlockRenderDispatcher blockRenderer;

	public SilverCarriedBlockLayer(RenderLayerParent<SilverEnderMan, EndermanModel<SilverEnderMan>> parent, BlockRenderDispatcher renderer) {
		super(parent);
		this.blockRenderer = renderer;
	}

	public void render(PoseStack stack, MultiBufferSource p_116640_, int p_116641_, SilverEnderMan enderman, float p_116643_, float p_116644_, float p_116645_, float p_116646_, float p_116647_, float p_116648_) {
		BlockState blockstate = enderman.getCarriedBlock();
		if (blockstate != null) {
			stack.pushPose();
			stack.translate(0.0F, 0.6875F, -0.75F);
			stack.mulPose(Axis.XP.rotationDegrees(20.0F));
			stack.mulPose(Axis.YP.rotationDegrees(45.0F));
			stack.translate(0.25F, 0.1875F, 0.25F);
			stack.scale(-0.5F, -0.5F, 0.5F);
			stack.mulPose(Axis.YP.rotationDegrees(90.0F));
			this.blockRenderer.renderSingleBlock(blockstate, stack, p_116640_, p_116641_, OverlayTexture.NO_OVERLAY, net.minecraftforge.client.model.data.ModelData.EMPTY, null);
			stack.popPose();
		}
	}
}