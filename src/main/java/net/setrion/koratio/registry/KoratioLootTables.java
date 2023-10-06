package net.setrion.koratio.registry;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;

public class KoratioLootTables {
	
	private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();
	private static final Set<ResourceLocation> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);
	
	//Chest Loot Tables
	public static final ResourceLocation OUTCAST_HOUSE = register("chests/outcast/outcast_house");
	
	//Entity Loot Tables
	public static final ResourceLocation MUSHROOM_SLIME_RED = register("entities/mushroom_slime/red");
	public static final ResourceLocation MUSHROOM_SLIME_BROWN = register("entities/mushroom_slime/brown");
	public static final ResourceLocation MUSHROOM_SLIME_PURPLE = register("entities/mushroom_slime/purple");
	public static final ResourceLocation MUSHROOM_SLIME_GREEN = register("entities/mushroom_slime/green");

	private static ResourceLocation register(String name) {
		return register(new ResourceLocation(Koratio.MOD_ID, name));
	}

	private static ResourceLocation register(ResourceLocation location) {
		if (LOCATIONS.add(location)) {
			return location;
		} else {
			throw new IllegalArgumentException(location + " is already a registered built-in loot table");
		}
	}

	public static Set<ResourceLocation> all() {
		return IMMUTABLE_LOCATIONS;
	}
}