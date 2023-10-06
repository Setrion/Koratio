package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.setrion.koratio.Koratio;

public class KoratioTags {
	
	public class Items {
		public static final TagKey<Item> SUGAR = createItemKey(new ResourceLocation("forge", "sugar"));
		
		public static final TagKey<Item> PANGO_LOGS = createItemKey(Koratio.prefix("pango_logs"));
		public static final TagKey<Item> RUGONA_LOGS = createItemKey(Koratio.prefix("rugona_logs"));
		public static final TagKey<Item> VARESO_LOGS = createItemKey(Koratio.prefix("vareso_logs"));
		public static final TagKey<Item> NIGHY_LOGS = createItemKey(Koratio.prefix("nighy_logs"));
		
		public static final TagKey<Item> KORATIO_LOGS = createItemKey(Koratio.prefix("logs"));

	    public static final TagKey<Item> KORATIO_FENCES = createItemKey(Koratio.prefix("fences"));
	    public static final TagKey<Item> KORATIO_FENCE_GATES = createItemKey(Koratio.prefix("fence_gates"));
	}
	
	public class Blocks {
		public static final TagKey<Block> PANGO_LOGS = createBlockKey(Koratio.prefix("pango_logs"));
		public static final TagKey<Block> RUGONA_LOGS = createBlockKey(Koratio.prefix("rugona_logs"));
		public static final TagKey<Block> VARESO_LOGS = createBlockKey(Koratio.prefix("vareso_logs"));
		public static final TagKey<Block> NIGHY_LOGS = createBlockKey(Koratio.prefix("nighy_logs"));
		
		public static final TagKey<Block> KORATIO_LOGS = createBlockKey(Koratio.prefix("logs"));

	    public static final TagKey<Block> KORATIO_FENCES = createBlockKey(Koratio.prefix("fences"));
	    public static final TagKey<Block> KORATIO_FENCE_GATES = createBlockKey(Koratio.prefix("fence_gates"));
	    
	    public static final TagKey<Block> MUSHROOMS = createBlockKey(Koratio.prefix("mushrooms"));
	    
	    public static final TagKey<Block> SOUL_STONE_BRICKS = createBlockKey(Koratio.prefix("soul_stone_bricks"));
	    
	    public static final TagKey<Block> MIDAS_ENDERMAN_HOLDABLE = createBlockKey(Koratio.prefix("midas_enderman_holdable"));
	    
	    public static final TagKey<Block> BASE_STONE_FANTASIA = createBlockKey(Koratio.prefix("base_stone_fantasia"));
	    public static final TagKey<Block> BASE_STONE_DEMONICIO = createBlockKey(Koratio.prefix("base_stone_demonicio"));
	    public static final TagKey<Block> BLOOD_STAINED_DEEPSLATE_ORE_REPLACEABLES = createBlockKey(Koratio.prefix("blood_stained_deepslate_ore_replaceables"));
	    
	    public static final TagKey<Block> FANTASIA_CARVER_REPLACEABLES = createBlockKey(Koratio.prefix("fantasia_carver_replaceables"));
	    public static final TagKey<Block> DEMONICIO_CARVER_REPLACEABLES = createBlockKey(Koratio.prefix("demonicio_carver_replaceables"));
	    public static final TagKey<Block> CRYSTAL_CAVE_CRYSTALS = createBlockKey(Koratio.prefix("crystal_cave_crystals"));
	    
	    public static final TagKey<Block> AMETHYST_FIRE_BASE_BLOCKS = createBlockKey(Koratio.prefix("amethyst_fire_base_blocks"));
	    public static final TagKey<Block> EMERALD_FIRE_BASE_BLOCKS = createBlockKey(Koratio.prefix("emerald_fire_base_blocks"));
	    
	    public static final TagKey<Block> ARSOY_ORES = createBlockKey(Koratio.prefix("arsoy_ores"));
	    public static final TagKey<Block> COOKIE_ORES = createBlockKey(Koratio.prefix("cookie_ores"));
	    
	    public static final TagKey<Block> NEEDS_RAINBOW_GEM_TOOL = createBlockKey(Koratio.prefix("need_rainbow_gem_tool"));
	    public static final TagKey<Block> NEEDS_ARSOY_TOOL = createBlockKey(Koratio.prefix("need_arsoy_tool"));
	    public static final TagKey<Block> NEEDS_BONE_TOOL = createBlockKey(Koratio.prefix("need_bone_tool"));
	    public static final TagKey<Block> NEEDS_WITHER_BONE_TOOL = createBlockKey(Koratio.prefix("need_wither_bone_tool"));
	}
	
	public class Entities {
		
	}
	
	public class Biomes {
		public static final TagKey<Biome> HAS_OUTCAST = createBiomeKey(Koratio.prefix("has_outcast"));
		public static final TagKey<Biome> FANTASIA_BIOMES = createBiomeKey(Koratio.prefix("fantasia_biomes"));
		public static final TagKey<Biome> DEMONICIO_BIOMES = createBiomeKey(Koratio.prefix("demonicio_biomes"));
		public static final TagKey<Biome> FOGGY_BIOMES = createBiomeKey(Koratio.prefix("foggy_biomes"));		
	}

	public static TagKey<Item> createItemKey(ResourceLocation name) {
		return TagKey.create(Registries.ITEM, name);
	}
	
	public static TagKey<Block> createBlockKey(ResourceLocation name) {
		return TagKey.create(Registries.BLOCK, name);
	}
	
	public static TagKey<EntityType<?>> createEntityTypeKey(ResourceLocation name) {
		return TagKey.create(Registries.ENTITY_TYPE, name);
	}
	
	private static TagKey<Biome> createBiomeKey(ResourceLocation name) {
		return TagKey.create(Registries.BIOME, name);
	}
	
}