package net.setrion.koratio.registry;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;

public class KoratioPoiTypes {
	
	public static final DeferredRegister<PoiType> TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Koratio.MOD_ID);

	public static final RegistryObject<PoiType> FANTASIA_PORTAL = TYPES.register("fantasia_portal", () -> new PoiType(ImmutableSet.copyOf(KoratioBlocks.FANTASIA_PORTAL.get().getStateDefinition().getPossibleStates()), 1, 1));
}