package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.feature.HugeCandyCaneFeature;
import net.setrion.koratio.world.level.levelgen.feature.HugeGreenMushroomFeature;
import net.setrion.koratio.world.level.levelgen.feature.HugePurpleMushroomFeature;

public class KoratioFeatures {
	
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, Koratio.MOD_ID);
	
	public static final DeferredHolder<Feature<?>, HugePurpleMushroomFeature> HUGE_PURPLE_MUSHROOM = FEATURES.register("huge_purple_mushroom", () -> new HugePurpleMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, HugeGreenMushroomFeature> HUGE_GREEN_MUSHROOM = FEATURES.register("huge_green_mushroom", () -> new HugeGreenMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, HugeCandyCaneFeature> HUGE_CANDY_CANE = FEATURES.register("huge_candy_cane", () -> new HugeCandyCaneFeature(NoneFeatureConfiguration.CODEC));
}