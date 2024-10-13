package net.setrion.koratio.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.registry.KoratioTags;

@EventBusSubscriber(Dist.CLIENT)
public class ForgeClientEvents {
	
	private static float fade;
	
	@SubscribeEvent
	public static void onFogRenderDensity(ViewportEvent.RenderFog event) {
		Minecraft minecraft = Minecraft.getInstance();
		LocalPlayer player = minecraft.player;
		if (event.getMode() != FogMode.FOG_TERRAIN || player == null || player.isSpectator() || player.isInLava() || player.isUnderWater() || !player.isAlive() || player.level().dimension() != KoratioDimensions.FANTASIA_DIMENSION_KEY || player.hasEffect(MobEffects.BLINDNESS) || player.hasEffect(MobEffects.DARKNESS)) {
			return;
		}
		
		Holder<Biome> currentBiome = player.level().getBiome(player.blockPosition());
		if (!currentBiome.is(KoratioTags.Biomes.FOGGY_BIOMES)) {
			fade = Math.max(0, fade-1);
		} else {
			fade = Math.min(250, fade+1);
		}
		
		if (fade == 0) {
			return;
		}
		event.setCanceled(true);
		event.setFarPlaneDistance(minecraft.gameRenderer.getRenderDistance()-((minecraft.gameRenderer.getRenderDistance()-60)*(fade/250.0F)));
		float f = Mth.clamp(minecraft.gameRenderer.getRenderDistance()/10.0F, 4.0F, 64.0F);
		event.setNearPlaneDistance(minecraft.gameRenderer.getRenderDistance()-f-(minecraft.gameRenderer.getRenderDistance()-f)*(fade/250.0F));
	}
}