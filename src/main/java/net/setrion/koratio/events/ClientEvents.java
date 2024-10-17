package net.setrion.koratio.events;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.ItemStackBlockEntityRenderer;
import net.setrion.koratio.client.gui.screens.inventory.ScrollScreen;
import net.setrion.koratio.client.model.block.GlazedModelLoader;
import net.setrion.koratio.client.particle.DripParticle;
import net.setrion.koratio.client.particle.ElvenLeafParticle;
import net.setrion.koratio.client.particle.TeleporterAscendParticle;
import net.setrion.koratio.client.particle.TeleporterDescendParticle;
import net.setrion.koratio.client.renderer.blockentity.KoratioChestRenderer;
import net.setrion.koratio.client.renderer.entity.*;
import net.setrion.koratio.registry.*;
import net.setrion.koratio.scroll.ScrollUtils;
import net.setrion.koratio.world.item.PipingBagItem;
import net.setrion.koratio.world.item.ScrollItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

@EventBusSubscriber(modid = Koratio.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public static void onModelRegistry(ModelEvent.RegisterGeometryLoaders event) {
		event.register(Koratio.prefix("glazed"), GlazedModelLoader.INSTANCE);
	}

	@SubscribeEvent
	public static void clientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			Sheets.addWoodType(KoratioBlocks.PANGO_TYPE);
			Sheets.addWoodType(KoratioBlocks.RUGONA_TYPE);
			Sheets.addWoodType(KoratioBlocks.ELVEN_WOOD_TYPE);
			Sheets.addWoodType(KoratioBlocks.BLUE_ELVEN_WOOD_TYPE);
			Sheets.addWoodType(KoratioBlocks.CYAN_ELVEN_WOOD_TYPE);
			Sheets.addWoodType(KoratioBlocks.GREEN_ELVEN_WOOD_TYPE);
			Sheets.addWoodType(KoratioBlocks.VARESO_TYPE);
			Sheets.addWoodType(KoratioBlocks.CANDY_WOOD_TYPE);
			ItemProperties.register(KoratioItems.PIPING_BAG.get(),
					ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "empty"), (itemStack, clientLevel, livingEntity, i) -> {
						if (itemStack.is(KoratioItems.PIPING_BAG)) {
							PipingBagItem bag = (PipingBagItem) itemStack.getItem();
							if (bag.isEmpty(itemStack)) {
								return 1.0F;
							}
						}
						return 0;
					});
		});
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.MOLTEN_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.FLOWING_MOLTEN_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.MOLTEN_BLUE_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.FLOWING_MOLTEN_BLUE_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.MOLTEN_GREEN_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.FLOWING_MOLTEN_GREEN_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.FLOWING_MOLTEN_YELLOW_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.MOLTEN_RED_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioFluids.FLOWING_MOLTEN_RED_SUGAR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(), RenderType.CUTOUT_MIPPED);
		ItemBlockRenderTypes.setRenderLayer(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(), RenderType.CUTOUT_MIPPED);
		ItemBlockRenderTypes.setRenderLayer(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get(), RenderType.CUTOUT_MIPPED);
		ItemBlockRenderTypes.setRenderLayer(KoratioBlocks.GINGERBREAD_BLOCK.get(), RenderType.CUTOUT_MIPPED);
		ItemBlockRenderTypes.setRenderLayer(KoratioBlocks.GINGERBREAD_BRICKS.get(), RenderType.CUTOUT_MIPPED);
		ItemBlockRenderTypes.setRenderLayer(KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get(), RenderType.CUTOUT_MIPPED);
	}

	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(KoratioParticles.GOLD_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
		event.registerSpriteSet(KoratioParticles.ELVEN_LEAVES.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> new ElvenLeafParticle(level, x, y, z, spriteset));
		event.registerSpriteSet(KoratioParticles.DRIPPING_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarHangParticle(spriteset, level, x, y, z, 240F / 255F, 240F / 255F, 240F / 255F, KoratioFluids.MOLTEN_SUGAR.get(), KoratioParticles.FALLING_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarFallParticle(spriteset, level, x, y, z, 240F / 255F, 240F / 255F, 240F / 255F, KoratioFluids.MOLTEN_SUGAR.get(), KoratioParticles.LANDING_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.LANDING_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarLandParticle(spriteset, level, x, y, z, 240F / 255F, 240F / 255F, 240F / 255F, KoratioFluids.MOLTEN_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_DRIPSTONE_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarHangParticle(spriteset, level, x, y, z, 240F / 255F, 240F / 255F, 240F / 255F, KoratioFluids.MOLTEN_SUGAR.get(), KoratioParticles.FALLING_DRIPSTONE_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_DRIPSTONE_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarFallParticle(spriteset, level, x, y, z, 240F / 255F, 240F / 255F, 240F / 255F, KoratioFluids.MOLTEN_SUGAR.get(), KoratioParticles.LANDING_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_BLUE_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarHangParticle(spriteset, level, x, y, z, 14F / 255F, 40F / 255F, 170F / 255F, KoratioFluids.MOLTEN_BLUE_SUGAR.get(), KoratioParticles.FALLING_BLUE_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_BLUE_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarFallParticle(spriteset, level, x, y, z, 14F / 255F, 40F / 255F, 170F / 255F, KoratioFluids.MOLTEN_BLUE_SUGAR.get(), KoratioParticles.LANDING_BLUE_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.LANDING_BLUE_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarLandParticle(spriteset, level, x, y, z, 14F / 255F, 40F / 255F, 170F / 255F, KoratioFluids.MOLTEN_BLUE_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_DRIPSTONE_BLUE_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarHangParticle(spriteset, level, x, y, z, 14F / 255F, 40F / 255F, 170F / 255F, KoratioFluids.MOLTEN_BLUE_SUGAR.get(), KoratioParticles.FALLING_DRIPSTONE_BLUE_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_DRIPSTONE_BLUE_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarFallParticle(spriteset, level, x, y, z, 14F / 255F, 40F / 255F, 170F / 255F, KoratioFluids.MOLTEN_BLUE_SUGAR.get(), KoratioParticles.LANDING_BLUE_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_GREEN_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarHangParticle(spriteset, level, x, y, z, 0F / 255F, 131F / 255F, 0F / 255F, KoratioFluids.MOLTEN_GREEN_SUGAR.get(), KoratioParticles.FALLING_GREEN_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_GREEN_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarFallParticle(spriteset, level, x, y, z, 0F / 255F, 131F / 255F, 0F / 255F, KoratioFluids.MOLTEN_GREEN_SUGAR.get(), KoratioParticles.LANDING_GREEN_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.LANDING_GREEN_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarLandParticle(spriteset, level, x, y, z, 0F / 255F, 131F / 255F, 0F / 255F, KoratioFluids.MOLTEN_GREEN_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_DRIPSTONE_GREEN_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarHangParticle(spriteset, level, x, y, z, 0F / 255F, 131F / 255F, 0F / 255F, KoratioFluids.MOLTEN_GREEN_SUGAR.get(), KoratioParticles.FALLING_DRIPSTONE_GREEN_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_DRIPSTONE_GREEN_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarFallParticle(spriteset, level, x, y, z, 0F / 255F, 131F / 255F, 0F / 255F, KoratioFluids.MOLTEN_GREEN_SUGAR.get(), KoratioParticles.LANDING_GREEN_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_YELLOW_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarHangParticle(spriteset, level, x, y, z, 233F / 255F, 209F / 255F, 77F / 255F, KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), KoratioParticles.FALLING_YELLOW_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_YELLOW_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarFallParticle(spriteset, level, x, y, z, 233F / 255F, 209F / 255F, 77F / 255F, KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), KoratioParticles.LANDING_YELLOW_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.LANDING_YELLOW_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarLandParticle(spriteset, level, x, y, z, 233F / 255F, 209F / 255F, 77F / 255F, KoratioFluids.MOLTEN_YELLOW_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_DRIPSTONE_YELLOW_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarHangParticle(spriteset, level, x, y, z, 233F / 255F, 209F / 255F, 77F / 255F, KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), KoratioParticles.FALLING_DRIPSTONE_YELLOW_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_DRIPSTONE_YELLOW_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarFallParticle(spriteset, level, x, y, z, 233F / 255F, 209F / 255F, 77F / 255F, KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), KoratioParticles.LANDING_YELLOW_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_RED_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarHangParticle(spriteset, level, x, y, z, 180F / 255F, 24F / 255F, 25F / 255F, KoratioFluids.MOLTEN_RED_SUGAR.get(), KoratioParticles.FALLING_RED_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_RED_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarFallParticle(spriteset, level, x, y, z, 180F / 255F, 24F / 255F, 25F / 255F, KoratioFluids.MOLTEN_RED_SUGAR.get(), KoratioParticles.LANDING_RED_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.LANDING_RED_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createSugarLandParticle(spriteset, level, x, y, z, 180F / 255F, 24F / 255F, 25F / 255F, KoratioFluids.MOLTEN_RED_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.DRIPPING_DRIPSTONE_RED_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarHangParticle(spriteset, level, x, y, z, 180F / 255F, 24F / 255F, 25F / 255F, KoratioFluids.MOLTEN_RED_SUGAR.get(), KoratioParticles.FALLING_DRIPSTONE_RED_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.FALLING_DRIPSTONE_RED_SUGAR.get(), (spriteset) -> (type, level, x, y, z, vx, vy, vz) -> DripParticle.createDripstoneSugarFallParticle(spriteset, level, x, y, z, 180F / 255F, 24F / 255F, 25F / 255F, KoratioFluids.MOLTEN_RED_SUGAR.get(), KoratioParticles.LANDING_RED_SUGAR.get()));
		event.registerSpriteSet(KoratioParticles.RAINBOW_FIRE_FLAME.get(), FlameParticle.Provider::new);
		event.registerSpriteSet(KoratioParticles.SMALL_RAINBOW_FIRE_FLAME.get(), FlameParticle.SmallFlameProvider::new);
		event.registerSpriteSet(KoratioParticles.RAINBOW_LAVA.get(), LavaParticle.Provider::new);
		event.registerSpriteSet(KoratioParticles.DEMONIC_FIRE_FLAME.get(), FlameParticle.Provider::new);
		event.registerSpriteSet(KoratioParticles.TELEPORTER_ASCEND.get(), TeleporterAscendParticle.Provider::new);
		event.registerSpriteSet(KoratioParticles.TELEPORTER_DESCEND.get(), TeleporterDescendParticle.Provider::new);
	}

	public static void openMenu(Player player, InteractionHand hand, ItemStack stack) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			if(player.level().isClientSide && stack.getItem() instanceof ScrollItem){
				Minecraft.getInstance().setScreen(new ScrollScreen(ScrollUtils.getScroll(stack)));
			}
		}
	}

	@SubscribeEvent
	public static void clientExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {

			@Override
			public ResourceLocation getStillTexture() {
				return Koratio.prefix("block/chocolate_milk_still");
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return Koratio.prefix("block/chocolate_milk_flow");
			}

			@Override
			public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
				return new Vector3f(139F / 255F, 86F / 255F, 54F / 255F);
			}

			@Override
			public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
				RenderSystem.setShaderFogStart(0.5f);
				RenderSystem.setShaderFogEnd(1f);
			}
		}, KoratioFluids.CHOCOLATE_MILK_TYPE.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {

			@Override
			public ResourceLocation getStillTexture() {
				return Koratio.prefix("block/blood_still");
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return Koratio.prefix("block/blood_flow");
			}

			@Override
			public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
				return new Vector3f(127F / 255F, 0F / 255F, 0F / 255F);
			}

			@Override
			public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
				RenderSystem.setShaderFogStart(0.5f);
				RenderSystem.setShaderFogEnd(1f);
			}
		}, KoratioFluids.BLOOD_TYPE.get());

		event.registerFluidType(createSugarFluidExtensions("molten_sugar", new Vector3f(1.0f, 1.0f, 1.0F)), KoratioFluids.MOLTEN_SUGAR_TYPE.get());
		event.registerFluidType(createSugarFluidExtensions("molten_blue_sugar", new Vector3f(0F / 255F, 0F / 255F, 200F / 255F)), KoratioFluids.MOLTEN_BLUE_SUGAR_TYPE.get());
		event.registerFluidType(createSugarFluidExtensions("molten_green_sugar", new Vector3f(0F / 255F, 200F / 255F, 0F / 255F)), KoratioFluids.MOLTEN_GREEN_SUGAR_TYPE.get());
		event.registerFluidType(createSugarFluidExtensions("molten_yellow_sugar", new Vector3f(0F / 255F, 200F / 255F, 200F / 255F)), KoratioFluids.MOLTEN_YELLOW_SUGAR_TYPE.get());
		event.registerFluidType(createSugarFluidExtensions("molten_red_sugar", new Vector3f(200F / 255F, 0F / 255F, 0F / 255F)), KoratioFluids.MOLTEN_RED_SUGAR_TYPE.get());

		event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new ItemStackBlockEntityRenderer();
			}
		}, KoratioItems.PANGO_CHEST.asItem(), KoratioItems.RUGONA_CHEST.asItem(), KoratioItems.VARESO_CHEST.asItem(), KoratioItems.CANDY_CHEST.asItem(), KoratioItems.ELVEN_CHEST.asItem(), KoratioItems.BLUE_ELVEN_CHEST.asItem(), KoratioItems.CYAN_ELVEN_CHEST.asItem(), KoratioItems.GREEN_ELVEN_CHEST.asItem(), KoratioItems.TRAPPED_PANGO_CHEST.asItem(), KoratioItems.TRAPPED_RUGONA_CHEST.asItem(), KoratioItems.TRAPPED_VARESO_CHEST.asItem(), KoratioItems.TRAPPED_CANDY_CHEST.asItem(), KoratioItems.TRAPPED_ELVEN_CHEST.asItem(), KoratioItems.TRAPPED_BLUE_ELVEN_CHEST.asItem(), KoratioItems.TRAPPED_CYAN_ELVEN_CHEST.asItem(), KoratioItems.TRAPPED_GREEN_ELVEN_CHEST.asItem());
	}

	private static IClientFluidTypeExtensions createSugarFluidExtensions(String name, Vector3f fogColor) {
		return new IClientFluidTypeExtensions() {

			@Override
			public ResourceLocation getStillTexture() {
				return Koratio.prefix("block/"+name+"_still");
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return Koratio.prefix("block/"+name+"_flow");
			}

			@Override
			public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
				return fogColor;
			}

			@Override
			public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
				RenderSystem.setShaderFogStart(0.5f);
				RenderSystem.setShaderFogEnd(1f);
			}
		};
	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(KoratioEntityType.MAGICAL_CAT.get(), MagicalCatRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.FLUFFER.get(), FlufferRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.SPIKY_PIG.get(), SpikyPigRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.GOLDEN_FOX_SPIRIT.get(), GoldenFoxSpiritRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.JUMSTEM.get(), JumStemRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.MUSHROOM_SPORE.get(), MushroomSporeRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.DEMONIC_ZOMBIE.get(), DemonicZombieRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.DEMONIC_SKELETON.get(), DemonicSkeletonRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.DEMONIC_SOLDIER.get(), DemonicSoldierRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.CRYSTALLIZE.get(), CrystallizeRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.GINGERBREAD_MAN.get(), GingerbreadManRenderer::new);
		event.registerEntityRenderer(KoratioEntityType.BOAT.get(), (context) -> new BoatRenderer(context, false));
		event.registerEntityRenderer(KoratioEntityType.CHEST_BOAT.get(), (context) -> new BoatRenderer(context, true));
		event.registerEntityRenderer(KoratioEntityType.LEVITATING_BLOCK.get(), LevitatingBlockRenderer::new);
		BlockEntityRenderers.register(KoratioBlockEntityType.CAMPFIRE.get(), CampfireRenderer::new);
		BlockEntityRenderers.register(KoratioBlockEntityType.CHEST.get(), KoratioChestRenderer::new);
		BlockEntityRenderers.register(KoratioBlockEntityType.TRAPPED_CHEST.get(), KoratioChestRenderer::new);
	}
}