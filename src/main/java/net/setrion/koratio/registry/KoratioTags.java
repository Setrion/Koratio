package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.setrion.koratio.Koratio;

public class KoratioTags {
	
	public class Items {
		public static final TagKey<Item> SUGAR = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "sugar"));

		public static final TagKey<Item> FLUFFER_FOOD = createItemKey(Koratio.prefix("fluffer_food"));
		public static final TagKey<Item> MAGICAL_CAT_FOOD = createItemKey(Koratio.prefix("magical_cat_food"));

		public static final TagKey<Item> GEMS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "gems"));

		public static final TagKey<Item> RAINBOW_GEMS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "gems/rainbow_gem"));
		public static final TagKey<Item> RUBY_GEMS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "gems/ruby"));
		public static final TagKey<Item> SAPPHIRE_GEMS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "gems/sapphire"));
		public static final TagKey<Item> TOPAZ_GEMS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "gems/topaz"));
		public static final TagKey<Item> ONYX_GEMS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "gems/onyx"));

		public static final TagKey<Item> MOLTEN_SUGAR_BUCKETS = createItemKey(Koratio.prefix("molten_sugar_buckets"));

		public static final TagKey<Item> TELEKINESIS_ENCHANTABLE = createItemKey(Koratio.prefix("telekinesis_enchantable"));

		//Block-Item Tags
		public static final TagKey<Item> PANGO_LOGS = createItemKey(Koratio.prefix("pango_logs"));
		public static final TagKey<Item> RUGONA_LOGS = createItemKey(Koratio.prefix("rugona_logs"));
		public static final TagKey<Item> VARESO_LOGS = createItemKey(Koratio.prefix("vareso_logs"));
		public static final TagKey<Item> CANDY_LOGS = createItemKey(Koratio.prefix("candy_logs"));
		public static final TagKey<Item> CHOCOLATE_OAK_LOGS = createItemKey(Koratio.prefix("chocolate_oak_logs"));
		public static final TagKey<Item> ELVEN_LOGS = createItemKey(Koratio.prefix("elven_logs"));
		
		public static final TagKey<Item> LOGS = createItemKey(Koratio.prefix("logs"));

		public static final TagKey<Item> LEAF_PANES = createItemKey(Koratio.prefix("leaf_panes"));

	    public static final TagKey<Item> FENCES = createItemKey(Koratio.prefix("fences"));
	    public static final TagKey<Item> FENCE_GATES = createItemKey(Koratio.prefix("fence_gates"));

		public static final TagKey<Item> TALL_WOODEN_DOORS = createItemKey(Koratio.prefix("tall_wooden_doors"));
		public static final TagKey<Item> TALL_DOORS = createItemKey(Koratio.prefix("tall_doors"));

		public static final TagKey<Item> ICINGS = createItemKey(Koratio.prefix("icings"));

		public static final TagKey<Item> RAINBOW_GEM_STORAGE_BLOCKS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/rainbow_gem"));
		public static final TagKey<Item> RUBY_STORAGE_BLOCKS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/ruby"));
		public static final TagKey<Item> SAPPHIRE_STORAGE_BLOCKS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/sapphire"));
		public static final TagKey<Item> TOPAZ_STORAGE_BLOCKS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/topaz"));
		public static final TagKey<Item> ONYX_STORAGE_BLOCKS = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/onyx"));

		public static final TagKey<Item> RUBY_ORES = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "ores/ruby"));
		public static final TagKey<Item> SAPPHIRE_ORES = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "ores/sapphire"));
		public static final TagKey<Item> TOPAZ_ORES = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "ores/topaz"));
		public static final TagKey<Item> ONYX_ORES = createItemKey(ResourceLocation.fromNamespaceAndPath("c", "ores/onyx"));
	}
	
	public class Blocks {
		public static final TagKey<Block> PANGO_LOGS = createBlockKey(Koratio.prefix("pango_logs"));
		public static final TagKey<Block> RUGONA_LOGS = createBlockKey(Koratio.prefix("rugona_logs"));
		public static final TagKey<Block> VARESO_LOGS = createBlockKey(Koratio.prefix("vareso_logs"));
		public static final TagKey<Block> CANDY_LOGS = createBlockKey(Koratio.prefix("candy_logs"));
		public static final TagKey<Block> CHOCOLATE_OAK_LOGS = createBlockKey(Koratio.prefix("chocolate_oak_logs"));
		public static final TagKey<Block> ELVEN_LOGS = createBlockKey(Koratio.prefix("elven_logs"));
		
		public static final TagKey<Block> LOGS = createBlockKey(Koratio.prefix("logs"));

		public static final TagKey<Block> LEAF_PANES = createBlockKey(Koratio.prefix("leaf_panes"));

	    public static final TagKey<Block> FENCES = createBlockKey(Koratio.prefix("fences"));
	    public static final TagKey<Block> FENCE_GATES = createBlockKey(Koratio.prefix("fence_gates"));

		public static final TagKey<Block> TALL_WOODEN_DOORS = createBlockKey(Koratio.prefix("tall_wooden_doors"));
		public static final TagKey<Block> TALL_DOORS = createBlockKey(Koratio.prefix("tall_doors"));

		public static final TagKey<Block> ICINGS = createBlockKey(Koratio.prefix("icings"));

		public static final TagKey<Block> MUSHROOMS = createBlockKey(Koratio.prefix("mushrooms"));
	    
	    public static final TagKey<Block> BASE_STONE_FANTASIA = createBlockKey(Koratio.prefix("base_stone_fantasia"));

	    public static final TagKey<Block> FANTASIA_CARVER_REPLACEABLES = createBlockKey(Koratio.prefix("fantasia_carver_replaceables"));
	    public static final TagKey<Block> CRYSTAL_CAVE_CRYSTALS = createBlockKey(Koratio.prefix("crystal_cave_crystals"));

	    public static final TagKey<Block> COOKIE_ORES = createBlockKey(Koratio.prefix("cookie_ores"));

		public static final TagKey<Block> RAINBOW_GEM_STORAGE_BLOCKS = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/rainbow_gem"));
		public static final TagKey<Block> RUBY_STORAGE_BLOCKS = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/ruby"));
		public static final TagKey<Block> SAPPHIRE_STORAGE_BLOCKS = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/sapphire"));
		public static final TagKey<Block> TOPAZ_STORAGE_BLOCKS = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/topaz"));
		public static final TagKey<Block> ONYX_STORAGE_BLOCKS = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/onyx"));

		public static final TagKey<Block> RUBY_ORES = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "ores/ruby"));
		public static final TagKey<Block> SAPPHIRE_ORES = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "ores/sapphire"));
		public static final TagKey<Block> TOPAZ_ORES = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "ores/topaz"));
		public static final TagKey<Block> ONYX_ORES = createBlockKey(ResourceLocation.fromNamespaceAndPath("c", "ores/onyx"));

		public static final TagKey<Block> SPATULA_EFFICIENT = createBlockKey(Koratio.prefix("spatula_efficient"));

		public static final TagKey<Block> GINGERBREAD_LABYRINTH_BLOCKS = createBlockKey(Koratio.prefix("gingerbread_labyrinth_blocks"));

		public static final TagKey<Block> NEEDS_BONE_TOOL = createBlockKey(Koratio.prefix("need_bone_tool"));
	    public static final TagKey<Block> NEEDS_WITHER_BONE_TOOL = createBlockKey(Koratio.prefix("need_wither_bone_tool"));
	}

	public class Fluids {
		public static final TagKey<Fluid> MOLTEN_SUGAR = createFluidKey(Koratio.prefix("molten_sugar"));
	}
	
	public class Entities {
		public static final TagKey<EntityType<?>> DEMONS = createEntityTypeKey(Koratio.prefix("demons"));
	}
	
	public class Biomes {
		public static final TagKey<Biome> HAS_OUTCAST = createBiomeKey(Koratio.prefix("has_outcast"));
		public static final TagKey<Biome> FANTASIA_BIOMES = createBiomeKey(Koratio.prefix("fantasia_biomes"));
		public static final TagKey<Biome> FOGGY_BIOMES = createBiomeKey(Koratio.prefix("foggy_biomes"));		
	}

	public static TagKey<Item> createItemKey(ResourceLocation name) {
		return TagKey.create(Registries.ITEM, name);
	}
	
	public static TagKey<Block> createBlockKey(ResourceLocation name) {
		return TagKey.create(Registries.BLOCK, name);
	}

	public static TagKey<Fluid> createFluidKey(ResourceLocation name) {
		return TagKey.create(Registries.FLUID, name);
	}
	
	public static TagKey<EntityType<?>> createEntityTypeKey(ResourceLocation name) {
		return TagKey.create(Registries.ENTITY_TYPE, name);
	}
	
	private static TagKey<Biome> createBiomeKey(ResourceLocation name) {
		return TagKey.create(Registries.BIOME, name);
	}
	
}