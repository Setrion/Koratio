package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.renderer.entity.layer.SilverEnderEyesLayer;
import net.setrion.koratio.world.entity.monster.SilverEnderMan;

public class SilverEndermanRenderer extends MobRenderer<SilverEnderMan, EndermanModel<SilverEnderMan>> {
	private static final ResourceLocation ENDERMAN_LOCATION = new ResourceLocation(Koratio.MOD_ID, "textures/entity/silver_enderman/silver_enderman.png");
	private final RandomSource random = RandomSource.create();

	public SilverEndermanRenderer(EntityRendererProvider.Context context) {
		super(context, new EndermanModel<>(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
		this.addLayer(new SilverEnderEyesLayer<>(this));
		this.addLayer(new SilverCarriedBlockLayer(this, context.getBlockRenderDispatcher()));
	}

	public void render(SilverEnderMan enderman, float p_114340_, float p_114341_, PoseStack stack, MultiBufferSource buffer, int p_114344_) {
		BlockState blockstate = enderman.getCarriedBlock();
		EndermanModel<SilverEnderMan> endermanmodel = this.getModel();
		endermanmodel.carrying = blockstate != null;
		endermanmodel.creepy = enderman.isCreepy();
		super.render(enderman, p_114340_, p_114341_, stack, buffer, p_114344_);
	}

	public Vec3 getRenderOffset(SilverEnderMan enderman, float offset) {
		if (enderman.isCreepy()) {
			return new Vec3(this.random.nextGaussian() * 0.02D, 0.0D, this.random.nextGaussian() * 0.02D);
		} else {
			return super.getRenderOffset(enderman, offset);
		}
	}

	public ResourceLocation getTextureLocation(SilverEnderMan enderman) {
		return ENDERMAN_LOCATION;
	}
}