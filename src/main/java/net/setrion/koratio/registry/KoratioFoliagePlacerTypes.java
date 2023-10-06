package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.RugonaFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.VaresoFoliagePlacer;

public class KoratioFoliagePlacerTypes {

	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Koratio.MOD_ID);
	
	public static final RegistryObject<FoliagePlacerType<RugonaFoliagePlacer>> RUGONA_PLACER = FOLIAGE_PLACERS.register("rugona_foliage_placer", () -> new FoliagePlacerType<>(RugonaFoliagePlacer.CODEC));
	public static final RegistryObject<FoliagePlacerType<VaresoFoliagePlacer>> VARESO_PLACER = FOLIAGE_PLACERS.register("vareso_foliage_placer", () -> new FoliagePlacerType<>(VaresoFoliagePlacer.CODEC));
}