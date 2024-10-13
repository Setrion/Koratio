package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.CandyFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.ElvenFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.RugonaFoliagePlacer;
import net.setrion.koratio.world.level.levelgen.feature.foliageplacers.VaresoFoliagePlacer;

public class KoratioFoliagePlacerTypes {

	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Koratio.MOD_ID);

	public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<RugonaFoliagePlacer>> RUGONA_PLACER = FOLIAGE_PLACERS.register("rugona_foliage_placer", () -> new FoliagePlacerType<>(RugonaFoliagePlacer.CODEC));
	public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<CandyFoliagePlacer>> CANDY_PLACER = FOLIAGE_PLACERS.register("candy_foliage_placer", () -> new FoliagePlacerType<>(CandyFoliagePlacer.CODEC));
	public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<VaresoFoliagePlacer>> VARESO_PLACER = FOLIAGE_PLACERS.register("vareso_foliage_placer", () -> new FoliagePlacerType<>(VaresoFoliagePlacer.CODEC));
	public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<ElvenFoliagePlacer>> ELVEN_PLACER = FOLIAGE_PLACERS.register("elven_foliage_placer", () -> new FoliagePlacerType<>(ElvenFoliagePlacer.CODEC));
}