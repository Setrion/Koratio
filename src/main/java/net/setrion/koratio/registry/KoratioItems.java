package net.setrion.koratio.registry;

import java.util.function.Consumer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.client.ItemStackBlockEntityRenderer;
import net.setrion.koratio.world.entity.vehicle.Boat;
import net.setrion.koratio.world.item.BoatItem;
import net.setrion.koratio.world.item.DecryptingBookItem;
import net.setrion.koratio.world.item.DevStick;
import net.setrion.koratio.world.item.ScrollItem;

public class KoratioItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Koratio.MOD_ID);
	public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, Koratio.MOD_ID);
	
	public static final RegistryObject<Item> DEV_STICK = ITEMS.register("dev_stick", () -> new DevStick(new Item.Properties()));
	public static final RegistryObject<BlockItem> MINIATURE_FANTASIA_PORTAL = ITEMS.register("miniature_fantasia_portal", () -> new BlockItem(KoratioBlocks.MINIATURE_FANTASIA_PORTAL.get(), new Item.Properties()));
	
	//Misc
	public static final RegistryObject<Item> SCROLL = ITEMS.register("scroll", () -> new ScrollItem(new Item.Properties()));
	public static final RegistryObject<Item> DECRYPTING_BOOK = ITEMS.register("decrypting_book", () -> new DecryptingBookItem(new Item.Properties(), 5));
	public static final RegistryObject<Item> BETTER_DECRYPTING_BOOK = ITEMS.register("better_decrypting_book", () -> new DecryptingBookItem(new Item.Properties().rarity(Rarity.UNCOMMON), 10));
	public static final RegistryObject<Item> FANTASTIC_DECRYPTING_BOOK = ITEMS.register("fantastic_decrypting_book", () -> new DecryptingBookItem(new Item.Properties().rarity(Koratio.getFantasyRarity()), 15));
	public static final RegistryObject<Item> RAINBOW_GEM = ITEMS.register("rainbow_gem", () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> RAINBOW_GEM_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("rainbow_gem_upgrade_smithing_template", () -> KoratioTemplates.createRainbowGemUpgradeTemplate());
	public static final RegistryObject<Item> RAW_ARSOY = ITEMS.register("raw_arsoy", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ARSOY_INGOT = ITEMS.register("arsoy_ingot", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ARSOY_NUGGET = ITEMS.register("arsoy_nugget", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> WITHER_BONE = ITEMS.register("wither_bone", () -> new Item(new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> RED_SUGAR = ITEMS.register("red_sugar", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLUE_SUGAR = ITEMS.register("blue_sugar", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> YELLOW_SUGAR = ITEMS.register("yellow_sugar", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GREEN_SUGAR = ITEMS.register("green_sugar", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CHOCOLATE_MILK_BUCKET = ITEMS.register("chocolate_milk_bucket", () -> new BucketItem(() -> KoratioFluids.CHOCOLATE_MILK.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final RegistryObject<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket", () -> new BucketItem(() -> KoratioFluids.BLOOD.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final RegistryObject<Item> GOLDEN_EGG = ITEMS.register("golden_egg", () -> new Item(new Item.Properties()));
	
	//Weapons
	public static final RegistryObject<Item> RAINBOW_GEM_SWORD = ITEMS.register("rainbow_gem_sword", () -> new SwordItem(KoratioItemTier.RAINBOW_GEM, 3, -2.4F, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> ARSOY_SWORD = ITEMS.register("arsoy_sword", () -> new SwordItem(KoratioItemTier.ARSOY, 3, -2.4F, new Item.Properties()));
	public static final RegistryObject<Item> BONE_SWORD = ITEMS.register("bone_sword", () -> new SwordItem(KoratioItemTier.BONE, 3, -2.4F, (new Item.Properties())));
	public static final RegistryObject<Item> WITHER_BONE_SWORD = ITEMS.register("wither_bone_sword", () -> new SwordItem(KoratioItemTier.WITHER_BONE, 3, -2.4F, (new Item.Properties()).fireResistant()));
	
	//Tools
	public static final RegistryObject<Item> RAINBOW_GEM_SHOVEL = ITEMS.register("rainbow_gem_shovel", () -> new ShovelItem(KoratioItemTier.RAINBOW_GEM, 1.5F, -3.0F, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> RAINBOW_GEM_PICKAXE = ITEMS.register("rainbow_gem_pickaxe", () -> new PickaxeItem(KoratioItemTier.RAINBOW_GEM, 1, -2.8F, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> RAINBOW_GEM_AXE = ITEMS.register("rainbow_gem_axe", () -> new AxeItem(KoratioItemTier.RAINBOW_GEM, 5.0F, -3.0F, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> RAINBOW_GEM_HOE = ITEMS.register("rainbow_gem_hoe", () -> new HoeItem(KoratioItemTier.RAINBOW_GEM, -5, 0.0F, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> ARSOY_SHOVEL = ITEMS.register("arsoy_shovel", () -> new ShovelItem(KoratioItemTier.ARSOY, 1.5F, -3.0F, new Item.Properties()));
	public static final RegistryObject<Item> ARSOY_PICKAXE = ITEMS.register("arsoy_pickaxe", () -> new PickaxeItem(KoratioItemTier.ARSOY, 1, -2.8F, new Item.Properties()));
	public static final RegistryObject<Item> ARSOY_AXE = ITEMS.register("arsoy_axe", () -> new AxeItem(KoratioItemTier.ARSOY, 6.0F, -3.1F, new Item.Properties()));
	public static final RegistryObject<Item> ARSOY_HOE = ITEMS.register("arsoy_hoe", () -> new HoeItem(KoratioItemTier.ARSOY, -2, -1.0F, new Item.Properties()));
	public static final RegistryObject<Item> BONE_SHOVEL = ITEMS.register("bone_shovel", () -> new ShovelItem(KoratioItemTier.BONE, 1.5F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> BONE_PICKAXE = ITEMS.register("bone_pickaxe", () -> new PickaxeItem(KoratioItemTier.BONE, 1, -2.8F, (new Item.Properties())));
	public static final RegistryObject<Item> BONE_AXE = ITEMS.register("bone_axe", () -> new AxeItem(KoratioItemTier.BONE, 5.0F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> BONE_HOE = ITEMS.register("bone_hoe", () -> new HoeItem(KoratioItemTier.BONE, -5, 0.0F, (new Item.Properties())));
	public static final RegistryObject<Item> WITHER_BONE_SHOVEL = ITEMS.register("wither_bone_shovel", () -> new ShovelItem(KoratioItemTier.BONE, 1.5F, -3.0F, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> WITHER_BONE_PICKAXE = ITEMS.register("wither_bone_pickaxe", () -> new PickaxeItem(KoratioItemTier.WITHER_BONE, 1, -2.8F, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> WITHER_BONE_AXE = ITEMS.register("wither_bone_axe", () -> new AxeItem(KoratioItemTier.WITHER_BONE, 5.0F, -3.0F, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> WITHER_BONE_HOE = ITEMS.register("wither_bone_hoe", () -> new HoeItem(KoratioItemTier.WITHER_BONE, -5, 0.0F, (new Item.Properties()).fireResistant()));
	
	//Armor
	public static final RegistryObject<Item> RAINBOW_GEM_HELMET = ITEMS.register("rainbow_gem_helmet", () -> new ArmorItem(KoratioArmorMaterial.RAINBOW_GEM, ArmorItem.Type.HELMET, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> RAINBOW_GEM_CHESTPLATE = ITEMS.register("rainbow_gem_chestplate", () -> new ArmorItem(KoratioArmorMaterial.RAINBOW_GEM, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> RAINBOW_GEM_LEGGINGS = ITEMS.register("rainbow_gem_leggings", () -> new ArmorItem(KoratioArmorMaterial.RAINBOW_GEM, ArmorItem.Type.LEGGINGS, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> RAINBOW_GEM_BOOTS = ITEMS.register("rainbow_gem_boots", () -> new ArmorItem(KoratioArmorMaterial.RAINBOW_GEM, ArmorItem.Type.BOOTS, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> ARSOY_HELMET = ITEMS.register("arsoy_helmet", () -> new ArmorItem(KoratioArmorMaterial.ARSOY, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final RegistryObject<Item> ARSOY_CHESTPLATE = ITEMS.register("arsoy_chestplate", () -> new ArmorItem(KoratioArmorMaterial.ARSOY, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final RegistryObject<Item> ARSOY_LEGGINGS = ITEMS.register("arsoy_leggings", () -> new ArmorItem(KoratioArmorMaterial.ARSOY, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final RegistryObject<Item> ARSOY_BOOTS = ITEMS.register("arsoy_boots", () -> new ArmorItem(KoratioArmorMaterial.ARSOY, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final RegistryObject<Item> BONE_HELMET = ITEMS.register("bone_helmet", () -> new ArmorItem(KoratioArmorMaterial.BONE, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final RegistryObject<Item> BONE_CHESTPLATE = ITEMS.register("bone_chestplate", () -> new ArmorItem(KoratioArmorMaterial.BONE, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final RegistryObject<Item> BONE_LEGGINGS = ITEMS.register("bone_leggings", () -> new ArmorItem(KoratioArmorMaterial.BONE, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final RegistryObject<Item> BONE_BOOTS = ITEMS.register("bone_boots", () -> new ArmorItem(KoratioArmorMaterial.BONE, ArmorItem.Type.BOOTS, (new Item.Properties())));
	public static final RegistryObject<Item> WITHER_BONE_HELMET = ITEMS.register("wither_bone_helmet", () -> new ArmorItem(KoratioArmorMaterial.WITHER_BONE, ArmorItem.Type.HELMET, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> WITHER_BONE_CHESTPLATE = ITEMS.register("wither_bone_chestplate", () -> new ArmorItem(KoratioArmorMaterial.WITHER_BONE, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> WITHER_BONE_LEGGINGS = ITEMS.register("wither_bone_leggings", () -> new ArmorItem(KoratioArmorMaterial.WITHER_BONE, ArmorItem.Type.LEGGINGS, (new Item.Properties()).fireResistant()));
	public static final RegistryObject<Item> WITHER_BONE_BOOTS = ITEMS.register("wither_bone_boots", () -> new ArmorItem(KoratioArmorMaterial.WITHER_BONE, ArmorItem.Type.BOOTS, (new Item.Properties()).fireResistant()));
	
	//FoodItems
	public static final RegistryObject<Item> RAW_PANGO = ITEMS.register("raw_pango", () -> new Item(new Item.Properties().food(KoratioFoods.RAW_PANGO)));
	public static final RegistryObject<Item> CRACKED_PANGO = ITEMS.register("cracked_pango", () -> new Item(new Item.Properties().food(KoratioFoods.CRACKED_PANGO)));
	public static final RegistryObject<Item> SPIKED_PORKCHOP = ITEMS.register("spiked_porkchop", () -> new Item(new Item.Properties().food(KoratioFoods.SPIKED_PORKCHOP)));
	public static final RegistryObject<Item> COOKED_SPIKED_PORKCHOP = ITEMS.register("cooked_spiked_porkchop", () -> new Item(new Item.Properties().food(KoratioFoods.COOKED_SPIKED_PORKCHOP)));
	public static final RegistryObject<Item> GOLDEN_BREAD = ITEMS.register("golden_bread", () -> new Item(new Item.Properties().food(KoratioFoods.GOLDEN_BREAD)));
	public static final RegistryObject<Item> GOLDEN_CHICKEN = ITEMS.register("golden_chicken", () -> new Item(new Item.Properties().food(KoratioFoods.GOLDEN_CHICKEN)));
	public static final RegistryObject<Item> COOKED_GOLDEN_CHICKEN = ITEMS.register("cooked_golden_chicken", () -> new Item(new Item.Properties().food(KoratioFoods.COOKED_GOLDEN_CHICKEN)));
	
	//Plants
	public static final RegistryObject<Item> RAINBOW_ROSE = ITEMS.register("rainbow_rose", () -> new BlockItem(KoratioBlocks.RAINBOW_ROSE.get(), new Item.Properties()));
	public static final RegistryObject<Item> RAINBOW_ALLIUM = ITEMS.register("rainbow_allium", () -> new BlockItem(KoratioBlocks.RAINBOW_ALLIUM.get(), new Item.Properties()));
	public static final RegistryObject<Item> RAINBOW_LILY_OF_THE_VALLEY = ITEMS.register("rainbow_lily_of_the_valley", () -> new BlockItem(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), new Item.Properties()));
	public static final RegistryObject<Item> FANTASIA_GRASS = ITEMS.register("fantasia_grass", () -> new BlockItem(KoratioBlocks.FANTASIA_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> TALL_FANTASIA_GRASS = ITEMS.register("tall_fantasia_grass", () -> new DoubleHighBlockItem(KoratioBlocks.TALL_FANTASIA_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> COTTON_CANDY_GRASS = ITEMS.register("cotton_candy_grass", () -> new BlockItem(KoratioBlocks.COTTON_CANDY_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> AMETHYST_GRASS = ITEMS.register("amethyst_grass", () -> new BlockItem(KoratioBlocks.AMETHYST_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> EMERALD_GRASS = ITEMS.register("emerald_grass", () -> new BlockItem(KoratioBlocks.EMERALD_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> COOKIE_FLOWER = ITEMS.register("cookie_flower", () -> new BlockItem(KoratioBlocks.COOKIE_FLOWER.get(), new Item.Properties()));
	public static final RegistryObject<Item> WHITE_SUGARGLASS_FLOWER = ITEMS.register("white_sugarglass_flower", () -> new BlockItem(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final RegistryObject<Item> BLUE_SUGARGLASS_FLOWER = ITEMS.register("blue_sugarglass_flower", () -> new BlockItem(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final RegistryObject<Item> GREEN_SUGARGLASS_FLOWER = ITEMS.register("green_sugarglass_flower", () -> new BlockItem(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final RegistryObject<Item> YELLOW_SUGARGLASS_FLOWER = ITEMS.register("yellow_sugarglass_flower", () -> new BlockItem(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final RegistryObject<Item> RED_SUGARGLASS_FLOWER = ITEMS.register("red_sugarglass_flower", () -> new BlockItem(KoratioBlocks.RED_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final RegistryObject<Item> GILDED_VINES = ITEMS.register("gilded_vines", () -> new BlockItem(KoratioBlocks.GILDED_VINES.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_WHEAT = ITEMS.register("golden_wheat", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_WHEAT_SEEDS = ITEMS.register("golden_wheat_seeds", () -> new ItemNameBlockItem(KoratioBlocks.GOLDEN_WHEAT.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_BABY_CARROTS = ITEMS.register("golden_baby_carrots", () -> new ItemNameBlockItem(KoratioBlocks.GOLDEN_BABY_CARROTS.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICE_ROSE = ITEMS.register("ice_rose", () -> new BlockItem(KoratioBlocks.ICE_ROSE.get(), new Item.Properties()));
	
	//SpawnEggs
	public static final RegistryObject<Item> FIRE_BAT_SPAWN_EGG = SPAWN_EGGS.register("fire_bat_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.FIRE_BAT.get(), 4996656, 16711680, new Item.Properties()));
	public static final RegistryObject<Item> ICE_BAT_SPAWN_EGG = SPAWN_EGGS.register("ice_bat_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.ICE_BAT.get(), 4996656, 65535, new Item.Properties()));
	public static final RegistryObject<Item> THUNDER_BAT_SPAWN_EGG = SPAWN_EGGS.register("thunder_bat_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.THUNDER_BAT.get(), 4996656, 16766976, new Item.Properties()));
	public static final RegistryObject<Item> UNICORN_CAT_SPAWN_EGG = SPAWN_EGGS.register("unicorn_cat_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.UNICORN_CAT.get(), 0, 10682623, new Item.Properties()));
	public static final RegistryObject<Item> JUMSTEM_SPAWN_EGG = SPAWN_EGGS.register("jumstem_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.JUMSTEM.get(), 13485757, 14136960, new Item.Properties()));
	public static final RegistryObject<Item> SPIKY_PIG_SPAWN_EGG = SPAWN_EGGS.register("spiky_pig_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.SPIKY_PIG.get(), 7923315, 1815732, new Item.Properties()));
	public static final RegistryObject<Item> DEMONIC_SOLDIER_SPAWN_EGG = SPAWN_EGGS.register("demonic_soldier_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.DEMONIC_SOLDIER.get(), 5395026, 11450206, new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_CHICKEN_SPAWN_EGG = SPAWN_EGGS.register("golden_chicken_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.GOLDEN_CHICKEN.get(), 10592673, 16766976, new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_PARROT_SPAWN_EGG = SPAWN_EGGS.register("golden_parrot_spawn_egg", () -> new ForgeSpawnEggItem(() -> KoratioEntityType.GOLDEN_PARROT.get(), 894731, 16766976, new Item.Properties()));
	
	//BlockItems
	public static final RegistryObject<BlockItem> DECRYPTING_TABLE = ITEMS.register("decrypting_table", () -> new BlockItem(KoratioBlocks.DECRYPTING_TABLE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RAINBOW_GEM_BLOCK = ITEMS.register("rainbow_gem_block", () -> new BlockItem(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), new Item.Properties().fireResistant()));
	public static final RegistryObject<BlockItem> AMETHYST_COBWEB = ITEMS.register("amethyst_cobweb", () -> new BlockItem(KoratioBlocks.AMETHYST_COBWEB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> EMERALD_COBWEB = ITEMS.register("emerald_cobweb", () -> new BlockItem(KoratioBlocks.EMERALD_COBWEB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> ARSOY_ORE = ITEMS.register("arsoy_ore", () -> new BlockItem(KoratioBlocks.ARSOY_ORE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> DEEPSLATE_ARSOY_ORE = ITEMS.register("deepslate_arsoy_ore", () -> new BlockItem(KoratioBlocks.DEEPSLATE_ARSOY_ORE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_ARSOY_ORE = ITEMS.register("blood_stained_deepslate_arsoy_ore", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RAW_ARSOY_BLOCK = ITEMS.register("raw_arsoy_block", () -> new BlockItem(KoratioBlocks.RAW_ARSOY_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> ARSOY_BLOCK = ITEMS.register("arsoy_block", () -> new BlockItem(KoratioBlocks.ARSOY_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> COOKIE_ORE = ITEMS.register("cookie_ore", () -> new BlockItem(KoratioBlocks.COOKIE_ORE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> DEEPSLATE_COOKIE_ORE = ITEMS.register("deepslate_cookie_ore", () -> new BlockItem(KoratioBlocks.DEEPSLATE_COOKIE_ORE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_COOKIE_ORE = ITEMS.register("blood_stained_deepslate_cookie_ore", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_COOKIE_ORE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SUGAR_BLOCK = ITEMS.register("sugar_block", () -> new BlockItem(KoratioBlocks.SUGAR_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RED_SUGAR_BLOCK = ITEMS.register("red_sugar_block", () -> new BlockItem(KoratioBlocks.RED_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLUE_SUGAR_BLOCK = ITEMS.register("blue_sugar_block", () -> new BlockItem(KoratioBlocks.BLUE_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> YELLOW_SUGAR_BLOCK = ITEMS.register("yellow_sugar_block", () -> new BlockItem(KoratioBlocks.YELLOW_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GREEN_SUGAR_BLOCK = ITEMS.register("green_sugar_block", () -> new BlockItem(KoratioBlocks.GREEN_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GOLDEN_HAY_BLOCK = ITEMS.register("golden_hay_block", () -> new BlockItem(KoratioBlocks.GOLDEN_HAY_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> RAW_GINGERBREAD_BLOCK = ITEMS.register("raw_gingerbread_block", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RAW_GINGERBREAD_STAIRS = ITEMS.register("raw_gingerbread_stairs", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RAW_GINGERBREAD_SLAB = ITEMS.register("raw_gingerbread_slab", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GINGERBREAD_BLOCK = ITEMS.register("gingerbread_block", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GINGERBREAD_STAIRS = ITEMS.register("gingerbread_stairs", () -> new BlockItem(KoratioBlocks.GINGERBREAD_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GINGERBREAD_SLAB = ITEMS.register("gingerbread_slab", () -> new BlockItem(KoratioBlocks.GINGERBREAD_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RAW_GINGERBREAD_BRICKS = ITEMS.register("raw_gingerbread_bricks", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RAW_GINGERBREAD_BRICK_STAIRS = ITEMS.register("raw_gingerbread_brick_stairs", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RAW_GINGERBREAD_BRICK_SLAB = ITEMS.register("raw_gingerbread_brick_slab", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GINGERBREAD_BRICKS = ITEMS.register("gingerbread_bricks", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GINGERBREAD_BRICK_STAIRS = ITEMS.register("gingerbread_brick_stairs", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GINGERBREAD_BRICK_SLAB = ITEMS.register("gingerbread_brick_slab", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> MARSHMALLOW_BLOCK = ITEMS.register("marshmallow_block", () -> new BlockItem(KoratioBlocks.MARSHMALLOW_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> MARSHMALLOW_STAIRS = ITEMS.register("marshmallow_stairs", () -> new BlockItem(KoratioBlocks.MARSHMALLOW_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> MARSHMALLOW_SLAB = ITEMS.register("marshmallow_slab", () -> new BlockItem(KoratioBlocks.MARSHMALLOW_SLAB.get(), new Item.Properties()));
	
	//Environmental
	public static final RegistryObject<BlockItem> AMETHYST_BRICKS = ITEMS.register("amethyst_bricks", () -> new BlockItem(KoratioBlocks.AMETHYST_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> AMETHYST_BRICK_STAIRS = ITEMS.register("amethyst_brick_stairs", () -> new BlockItem(KoratioBlocks.AMETHYST_BRICK_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> AMETHYST_BRICK_SLAB = ITEMS.register("amethyst_brick_slab", () -> new BlockItem(KoratioBlocks.AMETHYST_BRICK_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> AMETHYST_TORCH = ITEMS.register("amethyst_torch", () -> new StandingAndWallBlockItem(KoratioBlocks.AMETHYST_TORCH.get(), KoratioBlocks.AMETHYST_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
	public static final RegistryObject<BlockItem> AMETHYST_LANTERN = ITEMS.register("amethyst_lantern", () -> new BlockItem(KoratioBlocks.AMETHYST_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> AMETHYST_CAMPFIRE = ITEMS.register("amethyst_campfire", () -> new BlockItem(KoratioBlocks.AMETHYST_CAMPFIRE.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> EMERALD_BRICKS = ITEMS.register("emerald_bricks", () -> new BlockItem(KoratioBlocks.EMERALD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> EMERALD_BRICK_STAIRS = ITEMS.register("emerald_brick_stairs", () -> new BlockItem(KoratioBlocks.EMERALD_BRICK_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> EMERALD_BRICK_SLAB = ITEMS.register("emerald_brick_slab", () -> new BlockItem(KoratioBlocks.EMERALD_BRICK_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> EMERALD_TORCH = ITEMS.register("emerald_torch", () -> new StandingAndWallBlockItem(KoratioBlocks.EMERALD_TORCH.get(), KoratioBlocks.EMERALD_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
	public static final RegistryObject<BlockItem> EMERALD_LANTERN = ITEMS.register("emerald_lantern", () -> new BlockItem(KoratioBlocks.EMERALD_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> EMERALD_CAMPFIRE = ITEMS.register("emerald_campfire", () -> new BlockItem(KoratioBlocks.EMERALD_CAMPFIRE.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> CHISELED_AMETHYST_BLOCK = ITEMS.register("chiseled_amethyst_block", () -> new BlockItem(KoratioBlocks.CHISELED_AMETHYST_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> AMETHYST_PILLAR = ITEMS.register("amethyst_pillar", () -> new BlockItem(KoratioBlocks.AMETHYST_PILLAR.get(), new Item.Properties()));	
	public static final RegistryObject<BlockItem> CHISELED_EMERALD_BLOCK = ITEMS.register("chiseled_emerald_block", () -> new BlockItem(KoratioBlocks.CHISELED_EMERALD_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> EMERALD_PILLAR = ITEMS.register("emerald_pillar", () -> new BlockItem(KoratioBlocks.EMERALD_PILLAR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE = ITEMS.register("soul_stone", () -> new BlockItem(KoratioBlocks.SOUL_STONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE_STAIRS = ITEMS.register("soul_stone_stairs", () -> new BlockItem(KoratioBlocks.SOUL_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE_SLAB = ITEMS.register("soul_stone_slab", () -> new BlockItem(KoratioBlocks.SOUL_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE_PRESSURE_PLATE = ITEMS.register("soul_stone_pressure_plate", () -> new BlockItem(KoratioBlocks.SOUL_STONE_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE_BUTTON = ITEMS.register("soul_stone_button", () -> new BlockItem(KoratioBlocks.SOUL_STONE_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE = ITEMS.register("infested_soul_stone", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE_STAIRS = ITEMS.register("infested_soul_stone_stairs", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE_SLAB = ITEMS.register("infested_soul_stone_slab", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE_PRESSURE_PLATE = ITEMS.register("infested_soul_stone_pressure_plate", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE_BUTTON = ITEMS.register("infested_soul_stone_button", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_COBBLESTONE = ITEMS.register("soul_cobblestone", () -> new BlockItem(KoratioBlocks.SOUL_COBBLESTONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_COBBLESTONE_STAIRS = ITEMS.register("soul_cobblestone_stairs", () -> new BlockItem(KoratioBlocks.SOUL_COBBLESTONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_COBBLESTONE_SLAB = ITEMS.register("soul_cobblestone_slab", () -> new BlockItem(KoratioBlocks.SOUL_COBBLESTONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_COBBLESTONE_WALL = ITEMS.register("soul_cobblestone_wall", () -> new BlockItem(KoratioBlocks.SOUL_COBBLESTONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_COBBLESTONE = ITEMS.register("infested_soul_cobblestone", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_COBBLESTONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_COBBLESTONE_STAIRS = ITEMS.register("infested_soul_cobblestone_stairs", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_COBBLESTONE_SLAB = ITEMS.register("infested_soul_cobblestone_slab", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_COBBLESTONE_WALL = ITEMS.register("infested_soul_cobblestone_wall", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_COBBLESTONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SMOOTH_SOUL_STONE = ITEMS.register("smooth_soul_stone", () -> new BlockItem(KoratioBlocks.SMOOTH_SOUL_STONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SMOOTH_SOUL_STONE_SLAB = ITEMS.register("smooth_soul_stone_slab", () -> new BlockItem(KoratioBlocks.SMOOTH_SOUL_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE_BRICKS = ITEMS.register("soul_stone_bricks", () -> new BlockItem(KoratioBlocks.SOUL_STONE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> CRACKED_SOUL_STONE_BRICKS = ITEMS.register("cracked_soul_stone_bricks", () -> new BlockItem(KoratioBlocks.CRACKED_SOUL_STONE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE_BRICK_STAIRS = ITEMS.register("soul_stone_brick_stairs", () -> new BlockItem(KoratioBlocks.SOUL_STONE_BRICK_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE_BRICK_SLAB = ITEMS.register("soul_stone_brick_slab", () -> new BlockItem(KoratioBlocks.SOUL_STONE_BRICK_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> SOUL_STONE_BRICK_WALL = ITEMS.register("soul_stone_brick_wall", () -> new BlockItem(KoratioBlocks.SOUL_STONE_BRICK_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> CHISELED_SOUL_STONE_BRICKS = ITEMS.register("chiseled_soul_stone_bricks", () -> new BlockItem(KoratioBlocks.CHISELED_SOUL_STONE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE_BRICKS = ITEMS.register("infested_soul_stone_bricks", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> CRACKED_INFESTED_SOUL_STONE_BRICKS = ITEMS.register("cracked_infested_soul_stone_bricks", () -> new BlockItem(KoratioBlocks.CRACKED_INFESTED_SOUL_STONE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE_BRICK_STAIRS = ITEMS.register("infested_soul_stone_brick_stairs", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE_BRICK_SLAB = ITEMS.register("infested_soul_stone_brick_slab", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> INFESTED_SOUL_STONE_BRICK_WALL = ITEMS.register("infested_soul_stone_brick_wall", () -> new BlockItem(KoratioBlocks.INFESTED_SOUL_STONE_BRICK_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> CHISELED_INFESTED_SOUL_STONE_BRICKS = ITEMS.register("chiseled_infested_soul_stone_bricks", () -> new BlockItem(KoratioBlocks.CHISELED_INFESTED_SOUL_STONE_BRICKS.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE = ITEMS.register("blood_stained_deepslate", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_COBBLED_DEEPSLATE = ITEMS.register("blood_stained_cobbled_deepslate", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS = ITEMS.register("blood_stained_cobbled_deepslate_stairs", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB = ITEMS.register("blood_stained_cobbled_deepslate_slab", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_COBBLED_DEEPSLATE_WALL = ITEMS.register("blood_stained_cobbled_deepslate_wall", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_COBBLED_DEEPSLATE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> POLISHED_BLOOD_STAINED_DEEPSLATE = ITEMS.register("polished_blood_stained_deepslate", () -> new BlockItem(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS = ITEMS.register("polished_blood_stained_deepslate_stairs", () -> new BlockItem(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB = ITEMS.register("polished_blood_stained_deepslate_slab", () -> new BlockItem(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> POLISHED_BLOOD_STAINED_DEEPSLATE_WALL = ITEMS.register("polished_blood_stained_deepslate_wall", () -> new BlockItem(KoratioBlocks.POLISHED_BLOOD_STAINED_DEEPSLATE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_TILES = ITEMS.register("blood_stained_deepslate_tiles", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILES.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_TILE_STAIRS = ITEMS.register("blood_stained_deepslate_tile_stairs", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_TILE_SLAB = ITEMS.register("blood_stained_deepslate_tile_slab", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_TILE_WALL = ITEMS.register("blood_stained_deepslate_tile_wall", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_TILE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_BRICKS = ITEMS.register("blood_stained_deepslate_bricks", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS = ITEMS.register("blood_stained_deepslate_brick_stairs", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_BRICK_SLAB = ITEMS.register("blood_stained_deepslate_brick_slab", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> BLOOD_STAINED_DEEPSLATE_BRICK_WALL = ITEMS.register("blood_stained_deepslate_brick_wall", () -> new BlockItem(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_BRICK_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> CHISELED_BLOOD_STAINED_DEEPSLATE = ITEMS.register("chiseled_blood_stained_deepslate", () -> new BlockItem(KoratioBlocks.CHISELED_BLOOD_STAINED_DEEPSLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> CRACKED_BLOOD_STAINED_DEEPSLATE_BRICKS = ITEMS.register("cracked_blood_stained_deepslate_bricks", () -> new BlockItem(KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> CRACKED_BLOOD_STAINED_DEEPSLATE_TILES = ITEMS.register("cracked_blood_stained_deepslate_tiles", () -> new BlockItem(KoratioBlocks.CRACKED_BLOOD_STAINED_DEEPSLATE_TILES.get(), new Item.Properties()));
	
	//Vanilla Variants
	
	//Pango Wood
	public static final RegistryObject<BlockItem> PANGO_PLANKS = ITEMS.register("pango_planks", () -> new BlockItem(KoratioBlocks.PANGO_PLANKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_LOG = ITEMS.register("pango_log", () -> new BlockItem(KoratioBlocks.PANGO_LOG.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> STRIPPED_PANGO_LOG = ITEMS.register("stripped_pango_log", () -> new BlockItem(KoratioBlocks.STRIPPED_PANGO_LOG.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_WOOD = ITEMS.register("pango_wood", () -> new BlockItem(KoratioBlocks.PANGO_WOOD.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> STRIPPED_PANGO_WOOD = ITEMS.register("stripped_pango_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_PANGO_WOOD.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_LEAVES = ITEMS.register("pango_leaves", () -> new BlockItem(KoratioBlocks.PANGO_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_SLAB = ITEMS.register("pango_slab", () -> new BlockItem(KoratioBlocks.PANGO_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_STAIRS = ITEMS.register("pango_stairs", () -> new BlockItem(KoratioBlocks.PANGO_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_SAPLING = ITEMS.register("pango_sapling", () -> new BlockItem(KoratioBlocks.PANGO_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_FENCE = ITEMS.register("pango_fence", () -> new BlockItem(KoratioBlocks.PANGO_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<Item> PANGO_SIGN = ITEMS.register("pango_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_WALL_SIGN.get()));
	public static final RegistryObject<Item> PANGO_HANGING_SIGN = ITEMS.register("pango_hanging_sign",() -> new HangingSignItem(KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<BlockItem> PANGO_BUTTON = ITEMS.register("pango_button", () -> new BlockItem(KoratioBlocks.PANGO_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_PRESSURE_PLATE = ITEMS.register("pango_pressure_plate", () -> new BlockItem(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_DOOR = ITEMS.register("pango_door", () -> new BlockItem(KoratioBlocks.PANGO_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_TRAPDOOR = ITEMS.register("pango_trapdoor", () -> new BlockItem(KoratioBlocks.PANGO_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PANGO_FENCE_GATE = ITEMS.register("pango_fence_gate", () -> new BlockItem(KoratioBlocks.PANGO_FENCE_GATE.get(), new Item.Properties()));
	
	public static final RegistryObject<BoatItem> PANGO_BOAT = ITEMS.register("pango_boat", () -> new BoatItem(false, Boat.Type.PANGO, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<BoatItem> PANGO_CHEST_BOAT = ITEMS.register("pango_chest_boat", () -> new BoatItem(true, Boat.Type.PANGO, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<BlockItem> PANGO_CHEST = chestItem("pango_chest", KoratioBlocks.PANGO_CHEST);
	
	//Rugona Wood
	public static final RegistryObject<BlockItem> RUGONA_PLANKS = ITEMS.register("rugona_planks", () -> new BlockItem(KoratioBlocks.RUGONA_PLANKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_LOG = ITEMS.register("rugona_log", () -> new BlockItem(KoratioBlocks.RUGONA_LOG.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> STRIPPED_RUGONA_LOG = ITEMS.register("stripped_rugona_log", () -> new BlockItem(KoratioBlocks.STRIPPED_RUGONA_LOG.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_WOOD = ITEMS.register("rugona_wood", () -> new BlockItem(KoratioBlocks.RUGONA_WOOD.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> STRIPPED_RUGONA_WOOD = ITEMS.register("stripped_rugona_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_RUGONA_WOOD.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_LEAVES = ITEMS.register("rugona_leaves", () -> new BlockItem(KoratioBlocks.RUGONA_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_SLAB = ITEMS.register("rugona_slab", () -> new BlockItem(KoratioBlocks.RUGONA_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_STAIRS = ITEMS.register("rugona_stairs", () -> new BlockItem(KoratioBlocks.RUGONA_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_SAPLING = ITEMS.register("rugona_sapling", () -> new BlockItem(KoratioBlocks.RUGONA_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_FENCE = ITEMS.register("rugona_fence", () -> new BlockItem(KoratioBlocks.RUGONA_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<Item> RUGONA_SIGN = ITEMS.register("rugona_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get()));
	public static final RegistryObject<Item> RUGONA_HANGING_SIGN = ITEMS.register("rugona_hanging_sign",() -> new HangingSignItem(KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<BlockItem> RUGONA_BUTTON = ITEMS.register("rugona_button", () -> new BlockItem(KoratioBlocks.RUGONA_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_PRESSURE_PLATE = ITEMS.register("rugona_pressure_plate", () -> new BlockItem(KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_DOOR = ITEMS.register("rugona_door", () -> new BlockItem(KoratioBlocks.RUGONA_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_TRAPDOOR = ITEMS.register("rugona_trapdoor", () -> new BlockItem(KoratioBlocks.RUGONA_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> RUGONA_FENCE_GATE = ITEMS.register("rugona_fence_gate", () -> new BlockItem(KoratioBlocks.RUGONA_FENCE_GATE.get(), new Item.Properties()));

	public static final RegistryObject<BoatItem> RUGONA_BOAT = ITEMS.register("rugona_boat", () -> new BoatItem(false, Boat.Type.RUGONA, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<BoatItem> RUGONA_CHEST_BOAT = ITEMS.register("rugona_chest_boat", () -> new BoatItem(true, Boat.Type.RUGONA, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<BlockItem> RUGONA_CHEST = chestItem("rugona_chest", KoratioBlocks.RUGONA_CHEST);
	
	//Vareso Wood
	public static final RegistryObject<BlockItem> VARESO_PLANKS = ITEMS.register("vareso_planks", () -> new BlockItem(KoratioBlocks.VARESO_PLANKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_LOG = ITEMS.register("vareso_log", () -> new BlockItem(KoratioBlocks.VARESO_LOG.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> STRIPPED_VARESO_LOG = ITEMS.register("stripped_vareso_log", () -> new BlockItem(KoratioBlocks.STRIPPED_VARESO_LOG.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_WOOD = ITEMS.register("vareso_wood", () -> new BlockItem(KoratioBlocks.VARESO_WOOD.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> STRIPPED_VARESO_WOOD = ITEMS.register("stripped_vareso_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_VARESO_WOOD.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_LEAVES = ITEMS.register("vareso_leaves", () -> new BlockItem(KoratioBlocks.VARESO_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_SLAB = ITEMS.register("vareso_slab", () -> new BlockItem(KoratioBlocks.VARESO_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_STAIRS = ITEMS.register("vareso_stairs", () -> new BlockItem(KoratioBlocks.VARESO_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_SAPLING = ITEMS.register("vareso_sapling", () -> new BlockItem(KoratioBlocks.VARESO_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_FENCE = ITEMS.register("vareso_fence", () -> new BlockItem(KoratioBlocks.VARESO_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<Item> VARESO_SIGN = ITEMS.register("vareso_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get()));
	public static final RegistryObject<Item> VARESO_HANGING_SIGN = ITEMS.register("vareso_hanging_sign",() -> new HangingSignItem(KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<BlockItem> VARESO_BUTTON = ITEMS.register("vareso_button", () -> new BlockItem(KoratioBlocks.VARESO_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_PRESSURE_PLATE = ITEMS.register("vareso_pressure_plate", () -> new BlockItem(KoratioBlocks.VARESO_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_DOOR = ITEMS.register("vareso_door", () -> new BlockItem(KoratioBlocks.VARESO_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_TRAPDOOR = ITEMS.register("vareso_trapdoor", () -> new BlockItem(KoratioBlocks.VARESO_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> VARESO_FENCE_GATE = ITEMS.register("vareso_fence_gate", () -> new BlockItem(KoratioBlocks.VARESO_FENCE_GATE.get(), new Item.Properties()));
		
	public static final RegistryObject<BoatItem> VARESO_BOAT = ITEMS.register("vareso_boat", () -> new BoatItem(false, Boat.Type.VARESO, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<BoatItem> VARESO_CHEST_BOAT = ITEMS.register("vareso_chest_boat", () -> new BoatItem(true, Boat.Type.VARESO, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<BlockItem> VARESO_CHEST = chestItem("vareso_chest", KoratioBlocks.VARESO_CHEST);
	
	//Nighy Wood
	public static final RegistryObject<BlockItem> NIGHY_PLANKS = ITEMS.register("nighy_planks", () -> new BlockItem(KoratioBlocks.NIGHY_PLANKS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_LOG = ITEMS.register("nighy_log", () -> new BlockItem(KoratioBlocks.NIGHY_LOG.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> STRIPPED_NIGHY_LOG = ITEMS.register("stripped_nighy_log", () -> new BlockItem(KoratioBlocks.STRIPPED_NIGHY_LOG.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_WOOD = ITEMS.register("nighy_wood", () -> new BlockItem(KoratioBlocks.NIGHY_WOOD.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> STRIPPED_NIGHY_WOOD = ITEMS.register("stripped_nighy_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_NIGHY_WOOD.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_LEAVES = ITEMS.register("nighy_leaves", () -> new BlockItem(KoratioBlocks.NIGHY_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_SLAB = ITEMS.register("nighy_slab", () -> new BlockItem(KoratioBlocks.NIGHY_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_STAIRS = ITEMS.register("nighy_stairs", () -> new BlockItem(KoratioBlocks.NIGHY_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_SAPLING = ITEMS.register("nighy_sapling", () -> new BlockItem(KoratioBlocks.NIGHY_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_FENCE = ITEMS.register("nighy_fence", () -> new BlockItem(KoratioBlocks.NIGHY_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<Item> NIGHY_SIGN = ITEMS.register("nighy_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.NIGHY_SIGN.get(), KoratioBlocks.NIGHY_WALL_SIGN.get()));
	public static final RegistryObject<Item> NIGHY_HANGING_SIGN = ITEMS.register("nighy_hanging_sign",() -> new HangingSignItem(KoratioBlocks.NIGHY_HANGING_SIGN.get(), KoratioBlocks.NIGHY_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<BlockItem> NIGHY_BUTTON = ITEMS.register("nighy_button", () -> new BlockItem(KoratioBlocks.NIGHY_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_PRESSURE_PLATE = ITEMS.register("nighy_pressure_plate", () -> new BlockItem(KoratioBlocks.NIGHY_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_DOOR = ITEMS.register("nighy_door", () -> new BlockItem(KoratioBlocks.NIGHY_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_TRAPDOOR = ITEMS.register("nighy_trapdoor", () -> new BlockItem(KoratioBlocks.NIGHY_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHY_FENCE_GATE = ITEMS.register("nighy_fence_gate", () -> new BlockItem(KoratioBlocks.NIGHY_FENCE_GATE.get(), new Item.Properties()));
	
	public static final RegistryObject<BoatItem> NIGHY_BOAT = ITEMS.register("nighy_boat", () -> new BoatItem(false, Boat.Type.NIGHY, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<BoatItem> NIGHY_CHEST_BOAT = ITEMS.register("nighy_chest_boat", () -> new BoatItem(true, Boat.Type.NIGHY, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<BlockItem> NIGHY_CHEST = chestItem("nighy_chest", KoratioBlocks.NIGHY_CHEST);
	
	public static final RegistryObject<BlockItem> PURPLE_MUSHROOM = ITEMS.register("purple_mushroom", () -> new BlockItem(KoratioBlocks.PURPLE_MUSHROOM.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> PURPLE_MUSHROOM_BLOCK = ITEMS.register("purple_mushroom_block", () -> new BlockItem(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GREEN_MUSHROOM = ITEMS.register("green_mushroom", () -> new BlockItem(KoratioBlocks.GREEN_MUSHROOM.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> GREEN_MUSHROOM_BLOCK = ITEMS.register("green_mushroom_block", () -> new BlockItem(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get(), new Item.Properties()));
	
	private static RegistryObject<BlockItem> chestItem(String name, RegistryObject<? extends Block> block) {
		return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()) {
			@Override
			public void initializeClient(Consumer<IClientItemExtensions> consumer) {
				consumer.accept(new IClientItemExtensions() {
					@Override
					public BlockEntityWithoutLevelRenderer getCustomRenderer() {
						return new ItemStackBlockEntityRenderer();
					}
				});
			}
		});
	}
}