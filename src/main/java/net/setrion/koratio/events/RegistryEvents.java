package net.setrion.koratio.events;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.setrion.koratio.data.AdvancementProvider;
import net.setrion.koratio.data.BiomeTagGenerator;
import net.setrion.koratio.data.BlockStateGenerator;
import net.setrion.koratio.data.BlockTagGenerator;
import net.setrion.koratio.data.ItemModelGenerator;
import net.setrion.koratio.data.ItemTagGenerator;
import net.setrion.koratio.data.LanguageGenerator;
import net.setrion.koratio.data.LootGenerator;
import net.setrion.koratio.data.RecipeGenerator;
import net.setrion.koratio.data.RegistryDataGenerator;
import net.setrion.koratio.data.SoundGenerator;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.registry.KoratioBlockEntityType;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioCreativeTabs;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioFeatures;
import net.setrion.koratio.registry.KoratioFluids;
import net.setrion.koratio.registry.KoratioFoliagePlacerTypes;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioLootItemFunctions;
import net.setrion.koratio.registry.KoratioMenuTypes;
import net.setrion.koratio.registry.KoratioParticles;
import net.setrion.koratio.registry.KoratioPoiTypes;
import net.setrion.koratio.registry.KoratioSoundEvents;
import net.setrion.koratio.registry.KoratioTreeDecoratorTypes;
import net.setrion.koratio.registry.KoratioTrunkPlacerTypes;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
	
	public static void registerEverything(IEventBus modEventBus) {
		KoratioCreativeTabs.TABS.register(modEventBus);
		KoratioParticles.PARTICLES.register(modEventBus);
        KoratioFluids.FLUID_TYPES.register(modEventBus);
        KoratioFluids.FLUIDS.register(modEventBus);
        KoratioBlocks.BLOCKS.register(modEventBus);
        KoratioFeatures.FEATURES.register(modEventBus);
        KoratioMenuTypes.MENU_TYPES.register(modEventBus);
        KoratioBlockEntityType.BLOCK_ENTITY_TYPES.register(modEventBus);
        KoratioItems.ITEMS.register(modEventBus);
        KoratioItems.SPAWN_EGGS.register(modEventBus);
        KoratioLootItemFunctions.LOOT_FUNCTION_TYPES.register(modEventBus);
        KoratioEntityType.ENTITY_TYPES.register(modEventBus);
        KoratioBiomes.BIOMES.register(modEventBus);
        KoratioSoundEvents.SOUNDS.register(modEventBus);
        KoratioPoiTypes.TYPES.register(modEventBus);
        KoratioTrunkPlacerTypes.TRUNK_PLACERS.register(modEventBus);
        KoratioFoliagePlacerTypes.FOLIAGE_PLACERS.register(modEventBus);
        KoratioTreeDecoratorTypes.DECORATOR_TYPES.register(modEventBus);
	}
	
	@SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = event.getGenerator().getPackOutput();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();
		
        generator.addProvider(event.includeServer(), new AdvancementProvider(output, provider, helper));
        generator.addProvider(event.includeClient(), new BlockStateGenerator(output, helper));
        generator.addProvider(event.includeClient(), new ItemModelGenerator(output, helper));
        BlockTagGenerator blockTags = new BlockTagGenerator(output, provider, helper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ItemTagGenerator(output, provider, blockTags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new BiomeTagGenerator(output, provider, helper));
        generator.addProvider(event.includeServer(), new LootGenerator(output));
        generator.addProvider(event.includeServer(), new RecipeGenerator(output));
        generator.addProvider(event.includeServer(), new SoundGenerator(output, helper));
        RegistryDataGenerator.addProviders(event.includeServer(), generator, output, provider, helper);
        generator.addProvider(event.includeServer(), new LanguageGenerator.English(output));
        generator.addProvider(event.includeServer(), new LanguageGenerator.German(output));
	}
}