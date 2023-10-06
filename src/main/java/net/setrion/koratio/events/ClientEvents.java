package net.setrion.koratio.events;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.renderer.blockentity.KoratioChestRenderer;
import net.setrion.koratio.client.renderer.entity.AmethystSpiderRenderer;
import net.setrion.koratio.client.renderer.entity.BoatRenderer;
import net.setrion.koratio.client.renderer.entity.CrystallizeRenderer;
import net.setrion.koratio.client.renderer.entity.DemonicSoldierRenderer;
import net.setrion.koratio.client.renderer.entity.FireBatRenderer;
import net.setrion.koratio.client.renderer.entity.GingerbreadManRenderer;
import net.setrion.koratio.client.renderer.entity.GoldenChickenRenderer;
import net.setrion.koratio.client.renderer.entity.GoldenParrotRenderer;
import net.setrion.koratio.client.renderer.entity.IceBatRenderer;
import net.setrion.koratio.client.renderer.entity.JumStemRenderer;
import net.setrion.koratio.client.renderer.entity.MushroomSporeRenderer;
import net.setrion.koratio.client.renderer.entity.SilverEndermanRenderer;
import net.setrion.koratio.client.renderer.entity.SpikyPigRenderer;
import net.setrion.koratio.client.renderer.entity.ThunderBatRenderer;
import net.setrion.koratio.client.renderer.entity.UnicornCatRenderer;
import net.setrion.koratio.registry.KoratioBlockEntityType;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEntityType;

@Mod.EventBusSubscriber(modid = Koratio.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public void clientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			Sheets.addWoodType(KoratioBlocks.PANGO);
			Sheets.addWoodType(KoratioBlocks.RUGONA);
			Sheets.addWoodType(KoratioBlocks.VARESO);
		});
	}
	
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(KoratioEntityType.FIRE_BAT.get(), FireBatRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.ICE_BAT.get(), IceBatRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.THUNDER_BAT.get(), ThunderBatRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.UNICORN_CAT.get(), UnicornCatRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.SPIKY_PIG.get(), SpikyPigRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.JUMSTEM.get(), JumStemRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.GOLDEN_CHICKEN.get(), GoldenChickenRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.GOLDEN_PARROT.get(), GoldenParrotRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.MUSHROOM_SPORE.get(), MushroomSporeRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.DEMONIC_SOLDIER.get(), DemonicSoldierRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.SILVER_ENDERMAN.get(), SilverEndermanRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.CRYSTALLIZE.get(), CrystallizeRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.GINGERBREAD_MAN.get(), GingerbreadManRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.AMETHYST_SPIDER.get(), AmethystSpiderRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.BOAT.get(), (context) -> {
			return new BoatRenderer(context, false);
		});
		event.registerEntityRenderer(KoratioEntityType.CHEST_BOAT.get(), (context) -> {
			return new BoatRenderer(context, true);
		});
		BlockEntityRenderers.register(KoratioBlockEntityType.SIGN.get(), SignRenderer::new);
		BlockEntityRenderers.register(KoratioBlockEntityType.HANGING_SIGN.get(), HangingSignRenderer::new);
		BlockEntityRenderers.register(KoratioBlockEntityType.CHEST.get(), KoratioChestRenderer::new);
		BlockEntityRenderers.register(KoratioBlockEntityType.CAMPFIRE.get(), CampfireRenderer::new);
	}
}