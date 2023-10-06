package net.setrion.koratio.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.feature.AmethystSpikeFeature;
import net.setrion.koratio.world.level.levelgen.feature.HugeCandyCaneFeature;
import net.setrion.koratio.world.level.levelgen.feature.HugeGreenMushroomFeature;
import net.setrion.koratio.world.level.levelgen.feature.HugePurpleMushroomFeature;

public class KoratioFeatures {
	
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Koratio.MOD_ID);
	
	public static final RegistryObject<HugePurpleMushroomFeature> HUGE_PURPLE_MUSHROOM = FEATURES.register("huge_purple_mushroom", () -> new HugePurpleMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
	public static final RegistryObject<HugeGreenMushroomFeature> HUGE_GREEN_MUSHROOM = FEATURES.register("huge_green_mushroom", () -> new HugeGreenMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
	public static final RegistryObject<HugeCandyCaneFeature> HUGE_CANDY_CANE = FEATURES.register("huge_candy_cane", () -> new HugeCandyCaneFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<AmethystSpikeFeature> AMETHYST_SPIKES = FEATURES.register("amethyst_spikes", () -> new AmethystSpikeFeature(NoneFeatureConfiguration.CODEC));
}