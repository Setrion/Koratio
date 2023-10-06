package net.setrion.koratio.data;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.registry.KoratioCarvers;
import net.setrion.koratio.registry.KoratioConfiguredFeatures;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.registry.KoratioPlacedFeatures;
import net.setrion.koratio.registry.KoratioPools;
import net.setrion.koratio.registry.KoratioProcessorLists;
import net.setrion.koratio.registry.KoratioStructureSets;
import net.setrion.koratio.registry.KoratioStructures;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, (RegistrySetBuilder.RegistryBootstrap)KoratioConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, KoratioPlacedFeatures::bootstrap)
			.add(Registries.CONFIGURED_CARVER, (RegistrySetBuilder.RegistryBootstrap)KoratioCarvers::bootstrap)
			.add(Registries.NOISE_SETTINGS, KoratioDimensions::bootstrapNoise)
			.add(Registries.DIMENSION_TYPE, KoratioDimensions::bootstrapType)
			.add(Registries.LEVEL_STEM, KoratioDimensions::bootstrapStem)
			.add(Registries.BIOME, KoratioBiomes::bootstrap)
			.add(Registries.TEMPLATE_POOL, KoratioPools::bootstrap)
			.add(Registries.STRUCTURE, KoratioStructures::bootstrap)
			.add(Registries.STRUCTURE_SET, KoratioStructureSets::bootstrap)
			.add(Registries.PROCESSOR_LIST, KoratioProcessorLists::bootstrap);

	// Use addProviders() instead
	private RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider, BUILDER, Set.of("minecraft", Koratio.MOD_ID));
	}

	public static void addProviders(boolean isServer, DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
		generator.addProvider(isServer, new RegistryDataGenerator(output, provider));
	}
}