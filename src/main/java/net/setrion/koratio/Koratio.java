package net.setrion.koratio;

import java.util.Objects;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.setrion.koratio.client.gui.screens.inventory.DecryptingScreen;
import net.setrion.koratio.events.Events;
import net.setrion.koratio.events.ForgeClientEvents;
import net.setrion.koratio.events.RegistryEvents;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioCriteriaTriggers;
import net.setrion.koratio.registry.KoratioMenuTypes;
import net.setrion.koratio.registry.ModelLayers;
import net.setrion.koratio.world.level.biome.DemonicioBiomeProvider;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;
import net.setrion.koratio.world.level.chunk.chunkgenerators.DemonicioChunkGenerator;
import net.setrion.koratio.world.level.chunk.chunkgenerators.FantasiaChunkGenerator;

@Mod(Koratio.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Koratio {
	
    public static final String MOD_ID = "koratio";
    private static final Logger LOGGER = LogUtils.getLogger();
    
    private static final Rarity RARITY_FANTASY = Rarity.create("FANTASY", ChatFormatting.GREEN);
    private static final Rarity RARITY_DEMONIC = Rarity.create("DEMONIC", ChatFormatting.RED);

    public Koratio() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RegistryEvents.registerEverything(modEventBus);
        
        modEventBus.addListener(this::setupEntityModelLayers);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        
        KoratioBlocks.registerWoodTypes();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Events());
        MinecraftForge.EVENT_BUS.register(new ForgeClientEvents());
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
    	KoratioCriteriaTriggers.init();
    	KoratioBlocks.registerPots();
    	KoratioBlocks.registerStrippables();
    }
    
    public void clientSetup(final FMLClientSetupEvent event) {
    	MenuScreens.register(KoratioMenuTypes.DECRYPTING.get(), DecryptingScreen::new);
    }

	public static Logger getLogger() {
		return LOGGER;
	}
	
	public void setupEntityModelLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModelLayers.register(event);
    }
	
	@SubscribeEvent
	public static void registerSerializers(RegisterEvent evt) {
		if (Objects.equals(evt.getForgeRegistry(), ForgeRegistries.RECIPE_SERIALIZERS)) {
			Registry.register(BuiltInRegistries.BIOME_SOURCE, Koratio.prefix("fantasia_biomes"), FantasiaBiomeProvider.CODEC);
			Registry.register(BuiltInRegistries.BIOME_SOURCE, Koratio.prefix("demonicio_biomes"), DemonicioBiomeProvider.CODEC);

			Registry.register(BuiltInRegistries.CHUNK_GENERATOR, Koratio.prefix("fantasia_chunk_generator"), FantasiaChunkGenerator.CODEC);
			Registry.register(BuiltInRegistries.CHUNK_GENERATOR, Koratio.prefix("demonicio_chunk_generator"), DemonicioChunkGenerator.CODEC);
		}
	}
	
	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(MOD_ID, name);
	}
	
	public static Rarity getFantasyRarity() {
		return RARITY_FANTASY;
	}
	
	public static Rarity getDemonicRarity() {
		return RARITY_DEMONIC;
	}
}