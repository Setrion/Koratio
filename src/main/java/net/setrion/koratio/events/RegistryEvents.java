package net.setrion.koratio.events;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.data.*;
//import net.setrion.koratio.data.compat.KoratioCuriosCompat;
import net.setrion.koratio.registry.*;
import net.setrion.koratio.world.level.biome.FantasiaBiomeProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.BIOME_SOURCE)) {
            Registry.register(BuiltInRegistries.BIOME_SOURCE, Koratio.prefix("fantasia_biomes"), FantasiaBiomeProvider.CODEC);
            //Registry.register(BuiltInRegistries.CHUNK_GENERATOR, Koratio.prefix("fantasia_chunk_generator"), FantasiaChunkGenerator.CODEC);
        }
    }
	
	public static void registerEverything(IEventBus modEventBus) {
        KoratioItems.ITEMS.register(modEventBus);
		KoratioCreativeTabs.TABS.register(modEventBus);
		KoratioParticles.PARTICLES.register(modEventBus);
        KoratioFluids.FLUID_TYPES.register(modEventBus);
        KoratioCriteriaTriggers.TRIGGERS.register(modEventBus);
        KoratioDataComponents.DATA_COMPONENTS.register(modEventBus);
        KoratioDimensions.CHUNK_GENERATORS.register(modEventBus);
        KoratioStats.STATS.register(modEventBus);
        KoratioFluids.FLUIDS.register(modEventBus);
        KoratioBlocks.BLOCKS.register(modEventBus);
        KoratioFeatures.FEATURES.register(modEventBus);
        KoratioMenuTypes.MENU_TYPES.register(modEventBus);
        KoratioBlockEntityType.BLOCK_ENTITY_TYPES.register(modEventBus);
        KoratioItems.SPAWN_EGGS.register(modEventBus);
        KoratioLootItemFunctions.LOOT_FUNCTION_TYPES.register(modEventBus);
        KoratioLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        KoratioDataSerializers.DATA_SERIALIZERS.register(modEventBus);
        MagicalCatVariants.MAGICAL_CAT_VARIANTS.register(modEventBus);
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
    static void registerRegistries(NewRegistryEvent event) {
        event.register(KoratioRegistries.MAGICAL_CAT_VARIANT_REGISTRY);
    }
	
	@SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        generator.addProvider(true, new ModelGenerator(output));
        generator.addProvider(true, new KoratioEquipmentAssets(output));
        generator.addProvider(true, new ParticleGenerator(output));

        CompletableFuture<HolderLookup.Provider> newLookup = RegistryDataGenerator.addProviders(true, generator, output, provider).getRegistryProvider();
        generator.addProvider(true, new AdvancementProvider(output, newLookup, List.of(new AdvancementGenerator())));
        generator.addProvider(true, new DataMapGenerator(output, newLookup));
        KoratioTagsGenerator.BlockTagGenerator blockTags = new KoratioTagsGenerator.BlockTagGenerator(output, newLookup);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new KoratioTagsGenerator.ItemTagGenerator(output, newLookup, blockTags.contentsGetter()));
        generator.addProvider(true, new KoratioTagsGenerator.EnchantmentTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioTagsGenerator.EntityTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioTagsGenerator.MagicalCatTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioTagsGenerator.FluidTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioCompatTagGenerator(output, newLookup, blockTags.contentsGetter()));
        generator.addProvider(true, new KoratioTagsGenerator.PoiTypeTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioTagsGenerator.BiomeTagGenerator(output, newLookup));
        generator.addProvider(true, new LootGenerator(output, newLookup));
        generator.addProvider(true, new LootModifierGenerator(output, newLookup));
        generator.addProvider(true, new RecipeRunner(output, provider));
        generator.addProvider(true, new SoundGenerator(output));
        generator.addProvider(true, new LanguageGenerator.English(output));
        generator.addProvider(true, new LanguageGenerator.German(output));
        if (ModList.get().isLoaded("curios")) {
            //generator.addProvider(true, new KoratioCuriosCompat.KoratioCuriosProvider(output, helper, newLookup));
        }
	}

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        CompletableFuture<HolderLookup.Provider> newLookup = RegistryDataGenerator.addProviders(true, generator, output, provider).getRegistryProvider();
        generator.addProvider(true, new AdvancementProvider(output, newLookup, List.of(new AdvancementGenerator())));
        generator.addProvider(true, new DataMapGenerator(output, newLookup));
        KoratioTagsGenerator.BlockTagGenerator blockTags = new KoratioTagsGenerator.BlockTagGenerator(output, newLookup);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new KoratioTagsGenerator.ItemTagGenerator(output, newLookup, blockTags.contentsGetter()));
        generator.addProvider(true, new KoratioTagsGenerator.EnchantmentTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioTagsGenerator.EntityTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioTagsGenerator.MagicalCatTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioTagsGenerator.FluidTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioCompatTagGenerator(output, newLookup, blockTags.contentsGetter()));
        generator.addProvider(true, new KoratioTagsGenerator.PoiTypeTagGenerator(output, newLookup));
        generator.addProvider(true, new KoratioTagsGenerator.BiomeTagGenerator(output, newLookup));
        generator.addProvider(true, new LootGenerator(output, newLookup));
        generator.addProvider(true, new LootModifierGenerator(output, newLookup));
        generator.addProvider(true, new RecipeRunner(output, provider));
        generator.addProvider(true, new SoundGenerator(output));
        generator.addProvider(true, new LanguageGenerator.English(output));
        generator.addProvider(true, new LanguageGenerator.German(output));
        if (ModList.get().isLoaded("curios")) {
            //generator.addProvider(true, new KoratioCuriosCompat.KoratioCuriosProvider(output, helper, newLookup));
        }
    }

    @SubscribeEvent
    public static void registerCauldronFluidContent(RegisterCauldronFluidContentEvent event) {
        event.register(KoratioBlocks.MOLTEN_WHITE_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_WHITE_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_LIGHT_GRAY_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_GRAY_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_GRAY_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_BLACK_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_BLACK_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_BROWN_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_BROWN_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_RED_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_RED_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_ORANGE_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_ORANGE_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_YELLOW_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_LIME_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_LIME_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_GREEN_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_GREEN_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_CYAN_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_CYAN_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_LIGHT_BLUE_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_BLUE_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_BLUE_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_PURPLE_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_PURPLE_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_MAGENTA_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_MAGENTA_SUGAR.get(), FluidType.BUCKET_VOLUME, null);
        event.register(KoratioBlocks.MOLTEN_PINK_SUGAR_CAULDRON.get(), KoratioFluids.MOLTEN_PINK_SUGAR.get(), FluidType.BUCKET_VOLUME, null);

    }
}