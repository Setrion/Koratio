package net.setrion.koratio.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.koratio.client.model.JumStemModel;
import net.setrion.koratio.client.renderer.entity.state.JumstemRenderState;
import net.setrion.koratio.world.entity.monster.JumStem;

public class JumStemMushroomLayer extends RenderLayer<JumstemRenderState, JumStemModel> {

	private final BlockRenderDispatcher blockRenderer;
	
	public JumStemMushroomLayer(RenderLayerParent<JumstemRenderState, JumStemModel> parent, BlockRenderDispatcher dispatcher) {
		super(parent);
		this.blockRenderer = dispatcher;
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int p_117258_, JumstemRenderState state, float limbSwing, float limbSwingAmount) {
		if (!state.isBaby) {
			Minecraft minecraft = Minecraft.getInstance();
			boolean flag = state.appearsGlowing && state.isInvisible;
			if ((!state.isInvisible || flag) && state.variant != JumStem.Variant.SHEARED) {
				BlockState blockstate = state.variant.getBlock().defaultBlockState();
				int i = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
				BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
				if (state.mushrooms > 0) {
					stack.pushPose();
					stack.translate(0, (double)-0.05F, 0);
					stack.mulPose(Axis.YP.rotationDegrees(-69.0F));
					stack.scale(-0.5F, -0.5F, 0.5F);
					stack.translate(-0.5D, -0.5D, -0.5D);
					this.renderMushroomBlock(stack, buffer, p_117258_, flag, blockstate, i, bakedmodel);
					stack.popPose();
				}
				if (state.mushrooms > 1) {
					stack.pushPose();
					stack.translate(-0.35D, 0.45, -0.25F);
					stack.mulPose(Axis.YP.rotationDegrees(-99.0F));
					stack.scale(-0.5F, -0.5F, 0.5F);
					stack.translate(-0.5D, -0.5D, -0.5D);
					this.renderMushroomBlock(stack, buffer, p_117258_, flag, blockstate, i, bakedmodel);
					stack.popPose();
				}
				if (state.mushrooms > 2) {
					stack.pushPose();
					stack.translate(0.6D, 0.575F, 0.25F);
					stack.mulPose(Axis.XP.rotationDegrees(135.0F));
					stack.mulPose(Axis.ZP.rotationDegrees(135.0F));
					stack.mulPose(Axis.YP.rotationDegrees(5.0F));
					stack.scale(-0.5F, -0.5F, 0.5F);
					stack.translate(-0.5D, -0.5D, -0.5D);
					this.renderMushroomBlock(stack, buffer, p_117258_, flag, blockstate, i, bakedmodel);
					stack.popPose();					
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void renderMushroomBlock(PoseStack p_234853_, MultiBufferSource p_234854_, int p_234855_, boolean p_234856_, BlockState p_234857_, int p_234858_, BakedModel p_234859_) {
		if (p_234856_) {
			this.blockRenderer.getModelRenderer().renderModel(p_234853_.last(), p_234854_.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), p_234857_, p_234859_, 0.0F, 0.0F, 0.0F, p_234855_, p_234858_);
		} else {
			this.blockRenderer.renderSingleBlock(p_234857_, p_234853_, p_234854_, p_234855_, p_234858_);
		}
	}

}
