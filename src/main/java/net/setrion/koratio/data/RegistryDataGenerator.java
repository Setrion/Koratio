package net.setrion.koratio.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.*;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, (RegistrySetBuilder.RegistryBootstrap)KoratioConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, KoratioPlacedFeatures::bootstrap)
			.add(Registries.CONFIGURED_CARVER, (RegistrySetBuilder.RegistryBootstrap)KoratioCarvers::bootstrap)
			.add(Registries.NOISE_SETTINGS, KoratioDimensions::bootstrapNoise)
			.add(Registries.DIMENSION_TYPE, KoratioDimensions::bootstrapType)
			.add(Registries.LEVEL_STEM, KoratioDimensions::bootstrapStem)
			.add(Registries.TRIM_MATERIAL, KoratioTrimMaterials::bootstrap)
			.add(Registries.BIOME, KoratioBiomes::bootstrap)
			.add(Registries.TEMPLATE_POOL, KoratioPools::bootstrap)
			.add(Registries.STRUCTURE, KoratioStructures::bootstrap)
			.add(Registries.STRUCTURE_SET, KoratioStructureSets::bootstrap)
			.add(Registries.ENCHANTMENT, KoratioEnchantments::bootstrap)
			.add(Registries.PROCESSOR_LIST, KoratioProcessorLists::bootstrap);

	// Use addProviders() instead
	public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider, BUILDER, Set.of("minecraft", Koratio.MOD_ID));
	}

	public static RegistryDataGenerator addProviders(boolean isServer, DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
		var gen = new RegistryDataGenerator(output, provider);
		generator.addProvider(isServer, gen);
		return gen;
	}
}