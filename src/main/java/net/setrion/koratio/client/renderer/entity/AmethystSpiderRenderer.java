package net.setrion.koratio.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.entity.monster.AmethystSpider;

@OnlyIn(Dist.CLIENT)
public class AmethystSpiderRenderer extends SpiderRenderer<AmethystSpider> {
	private static final ResourceLocation AMETHYST_SPIDER_LOCATION = new ResourceLocation(Koratio.MOD_ID, "textures/entity/amethyst_spider.png");
	
	public AmethystSpiderRenderer(EntityRendererProvider.Context context) {
		super(context, ModelLayers.AMETHYST_SPIDER);
		this.shadowRadius *= 0.45F;
	}

	protected void scale(AmethystSpider spider, PoseStack stack, float scale) {
		stack.scale(0.45F, 0.45F, 0.45F);
	}

	public ResourceLocation getTextureLocation(AmethystSpider spider) {
		return AMETHYST_SPIDER_LOCATION;
	}
}