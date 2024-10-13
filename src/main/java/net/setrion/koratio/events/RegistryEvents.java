package net.setrion.koratio.events;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.data.*;
import net.setrion.koratio.data.compat.KoratioCuriosCompat;
import net.setrion.koratio.registry.*;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;
import net.setrion.koratio.world.level.chunk.chunkgenerators.FantasiaChunkGenerator;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void registerSerializers(RegisterEvent evt) {
        if (Objects.equals(evt.getRegistry(), BuiltInRegistries.RECIPE_SERIALIZER)) {
            Registry.register(BuiltInRegistries.BIOME_SOURCE, Koratio.prefix("fantasia_biomes"), FantasiaBiomeProvider.CODEC);

            Registry.register(BuiltInRegistries.CHUNK_GENERATOR, Koratio.prefix("fantasia_chunk_generator"), FantasiaChunkGenerator.CODEC);
        }
    }
	
	public static void registerEverything(IEventBus modEventBus) {
		KoratioCreativeTabs.TABS.register(modEventBus);
		KoratioParticles.PARTICLES.register(modEventBus);
        KoratioFluids.FLUID_TYPES.register(modEventBus);
        KoratioCriteriaTriggers.TRIGGERS.register(modEventBus);
        KoratioDataComponents.DATA_COMPONENTS.register(modEventBus);
        KoratioDimensions.CHUNK_GENERATORS.register(modEventBus);
        KoratioArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        KoratioStats.STATS.register(modEventBus);
        KoratioFluids.FLUIDS.register(modEventBus);
        KoratioBlocks.BLOCKS.register(modEventBus);
        KoratioFeatures.FEATURES.register(modEventBus);
        KoratioMenuTypes.MENU_TYPES.register(modEventBus);
        KoratioBlockEntityType.BLOCK_ENTITY_TYPES.register(modEventBus);
        KoratioItems.ITEMS.register(modEventBus);
        KoratioItems.SPAWN_EGGS.register(modEventBus);
        KoratioLootItemFunctions.LOOT_FUNCTION_TYPES.register(modEventBus);
        KoratioEntityType.ENTITY_TYPES.register(modEventBus);
        KoratioRecipeType.RECIPE_TYPES.register(modEventBus);
        KoratioRecipeSerializer.RECIPE_SERIALIZERS.register(modEventBus);
        KoratioSoundEvents.SOUNDS.register(modEventBus);
        KoratioPoiTypes.TYPES.register(modEventBus);
        KoratioTrunkPlacerTypes.TRUNK_PLACERS.register(modEventBus);
        KoratioFoliagePlacerTypes.FOLIAGE_PLACERS.register(modEventBus);
        KoratioTreeDecoratorTypes.DECORATOR_TYPES.register(modEventBus);
        KoratioVillagerTypes.PROFESSIONS.register(modEventBus);
	}
	
	@SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = event.getGenerator().getPackOutput();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

        CompletableFuture<HolderLookup.Provider> newLookup = RegistryDataGenerator.addProviders(event.includeServer(), generator, output, provider, helper).getRegistryProvider();
        generator.addProvider(event.includeServer(), new net.neoforged.neoforge.common.data.AdvancementProvider(output, newLookup, helper, List.of(new AdvancementGenerator())));
        generator.addProvider(event.includeClient(), new BlockStateGenerator(output, helper));
        generator.addProvider(event.includeClient(), new ItemModelGenerator(output, helper));
        generator.addProvider(event.includeClient(), new ParticleGenerator(output, helper));
        generator.addProvider(event.includeServer(), new DataMapGenerator(output, newLookup));
        KoratioTagsGenerator.BlockTagGenerator blockTags = new KoratioTagsGenerator.BlockTagGenerator(output, newLookup, helper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new KoratioTagsGenerator.ItemTagGenerator(output, newLookup, blockTags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new KoratioTagsGenerator.EntityTagGenerator(output, newLookup, helper));
        generator.addProvider(event.includeServer(), new KoratioTagsGenerator.FluidTagGenerator(output, newLookup, helper));
        generator.addProvider(event.includeServer(), new KoratioCompatTagGenerator(output, newLookup, blockTags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new KoratioTagsGenerator.BiomeTagGenerator(output, newLookup, helper));
        generator.addProvider(event.includeServer(), new LootGenerator(output, newLookup));
        generator.addProvider(event.includeServer(), new RecipeGenerator(output, newLookup));
        generator.addProvider(event.includeServer(), new SoundGenerator(output, helper));
        generator.addProvider(event.includeServer(), new LanguageGenerator.English(output));
        generator.addProvider(event.includeServer(), new LanguageGenerator.German(output));
        if (ModList.get().isLoaded("curios")) {
            generator.addProvider(event.includeServer(), new KoratioCuriosCompat.KoratioCuriosProvider(output, helper, newLookup));
        }
	}

    @SubscribeEvent
    public static void registerCauldronFluidContent(RegisterCauldronFluidContentEvent event) {
        event.register(KoratioBlocks.MOLTEN_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_BLUE_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_BLUE_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_GREEN_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_GREEN_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_YELLOW_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_RED_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_RED_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
    }
}