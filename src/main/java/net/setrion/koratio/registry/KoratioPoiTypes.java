package net.setrion.koratio.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;

public class KoratioPoiTypes {
	
	public static final DeferredRegister<PoiType> TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, Koratio.MOD_ID);

	public static final DeferredHolder<PoiType, PoiType> FANTASIA_PORTAL = TYPES.register("fantasia_portal", () -> new PoiType(ImmutableSet.copyOf(KoratioBlocks.FANTASIA_PORTAL.get().getStateDefinition().getPossibleStates()), 1, 1));
	public static final DeferredHolder<PoiType, PoiType> OUTCAST = TYPES.register("outcast", () -> new PoiType(ImmutableSet.copyOf(KoratioBlocks.DECRYPTING_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));
}