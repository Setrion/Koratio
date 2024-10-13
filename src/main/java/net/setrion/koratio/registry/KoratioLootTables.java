package net.setrion.koratio.registry;

import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.setrion.koratio.Koratio;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class KoratioLootTables {

	private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();
	private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);
	
	//Chest Loot Tables
	public static final ResourceKey<LootTable> OUTCAST_HOUSE = register("chests/outcast_house");
	
	//Entity Loot Tables
	public static final ResourceKey<LootTable> FLUFFER_WHITE = register("entities/fluffer/white");
	public static final ResourceKey<LootTable> FLUFFER_ORANGE = register("entities/fluffer/orange");
	public static final ResourceKey<LootTable> FLUFFER_MAGENTA = register("entities/fluffer/magenta");
	public static final ResourceKey<LootTable> FLUFFER_LIGHT_BLUE = register("entities/fluffer/light_blue");
	public static final ResourceKey<LootTable> FLUFFER_YELLOW = register("entities/fluffer/yellow");
	public static final ResourceKey<LootTable> FLUFFER_LIME = register("entities/fluffer/lime");
	public static final ResourceKey<LootTable> FLUFFER_PINK = register("entities/fluffer/pink");
	public static final ResourceKey<LootTable> FLUFFER_GRAY = register("entities/fluffer/gray");
	public static final ResourceKey<LootTable> FLUFFER_LIGHT_GRAY = register("entities/fluffer/light_gray");
	public static final ResourceKey<LootTable> FLUFFER_CYAN = register("entities/fluffer/cyan");
	public static final ResourceKey<LootTable> FLUFFER_PURPLE = register("entities/fluffer/purple");
	public static final ResourceKey<LootTable> FLUFFER_BLUE = register("entities/fluffer/blue");
	public static final ResourceKey<LootTable> FLUFFER_BROWN = register("entities/fluffer/brown");
	public static final ResourceKey<LootTable> FLUFFER_GREEN = register("entities/fluffer/green");
	public static final ResourceKey<LootTable> FLUFFER_RED = register("entities/fluffer/red");
	public static final ResourceKey<LootTable> FLUFFER_BLACK = register("entities/fluffer/black");


	private static ResourceKey<LootTable> register(String name) {
		return register(ResourceKey.create(Registries.LOOT_TABLE, Koratio.prefix(name)));
	}

	private static ResourceKey<LootTable> register(ResourceKey<LootTable> name) {
		if (LOCATIONS.add(name)) {
			return name;
		} else {
			throw new IllegalArgumentException(name.location() + " is already a registered built-in loot table");
		}
	}

	public static Set<ResourceKey<LootTable>> all() {
		return IMMUTABLE_LOCATIONS;
	}
}