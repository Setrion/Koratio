package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.ElvenTrunkPlacer;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.RugonaTrunkPlacer;
import net.setrion.koratio.world.level.levelgen.feature.trunkplacers.VaresoTrunkPlacer;

public class KoratioTrunkPlacerTypes {

	public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Koratio.MOD_ID);

	public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<RugonaTrunkPlacer>> RUGONA_PLACER = TRUNK_PLACERS.register("rugona_trunk_placer", () -> new TrunkPlacerType<>(RugonaTrunkPlacer.CODEC));
	public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<VaresoTrunkPlacer>> VARESO_PLACER = TRUNK_PLACERS.register("vareso_trunk_placer", () -> new TrunkPlacerType<>(VaresoTrunkPlacer.CODEC));
	public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<ElvenTrunkPlacer>> ELVEN_PLACER = TRUNK_PLACERS.register("elven_trunk_placer", () -> new TrunkPlacerType<>(ElvenTrunkPlacer.CODEC));
}