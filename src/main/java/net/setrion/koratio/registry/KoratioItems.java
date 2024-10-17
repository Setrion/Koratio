package net.setrion.koratio.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.entity.vehicle.Boat;
import net.setrion.koratio.world.item.*;
import net.setrion.koratio.world.item.BoatItem;

public class KoratioItems {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Koratio.MOD_ID);
	public static final DeferredRegister.Items SPAWN_EGGS = DeferredRegister.createItems(Koratio.MOD_ID);

	public static final DeferredItem<Item> DEV_STICK = ITEMS.register("dev_stick", () -> new DevToolItem(new Item.Properties()));
	public static final DeferredItem<BlockItem> MINIATURE_FANTASIA_PORTAL = ITEMS.register("miniature_fantasia_portal", () -> new BlockItem(KoratioBlocks.MINIATURE_FANTASIA_PORTAL.get(), new Item.Properties()));

	//Misc
	public static final DeferredItem<ScrollItem> SCROLL = ITEMS.register("scroll", () -> new ScrollItem(new Item.Properties()));
	public static final DeferredItem<Item> DECRYPTING_BOOK = ITEMS.register("decrypting_book", () -> new DecryptingBookItem(new Item.Properties(), 5));
	public static final DeferredItem<Item> BETTER_DECRYPTING_BOOK = ITEMS.register("better_decrypting_book", () -> new DecryptingBookItem(new Item.Properties().rarity(Rarity.UNCOMMON), 10));
	public static final DeferredItem<Item> FANTASTIC_DECRYPTING_BOOK = ITEMS.register("fantastic_decrypting_book", () -> new DecryptingBookItem(new Item.Properties(), 15));
	public static final DeferredItem<Item> WITHER_BONE = ITEMS.register("wither_bone", () -> new Item(new Item.Properties().fireResistant()));
	public static final DeferredItem<Item> RED_SUGAR = ITEMS.register("red_sugar", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> BLUE_SUGAR = ITEMS.register("blue_sugar", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> YELLOW_SUGAR = ITEMS.register("yellow_sugar", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> GREEN_SUGAR = ITEMS.register("green_sugar", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> STICKY_SUGAR_BUCKET = ITEMS.register("sticky_sugar_bucket", () -> new Item(new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> STICKY_BLUE_SUGAR_BUCKET = ITEMS.register("sticky_blue_sugar_bucket", () -> new Item(new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> STICKY_GREEN_SUGAR_BUCKET = ITEMS.register("sticky_green_sugar_bucket", () -> new Item(new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> STICKY_YELLOW_SUGAR_BUCKET = ITEMS.register("sticky_yellow_sugar_bucket", () -> new Item(new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> STICKY_RED_SUGAR_BUCKET = ITEMS.register("sticky_red_sugar_bucket", () -> new Item(new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BucketItem> MOLTEN_SUGAR_BUCKET = ITEMS.register("molten_sugar_bucket", () -> new BucketItem(KoratioFluids.MOLTEN_SUGAR.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredItem<BucketItem> MOLTEN_BLUE_SUGAR_BUCKET = ITEMS.register("molten_blue_sugar_bucket", () -> new BucketItem(KoratioFluids.MOLTEN_BLUE_SUGAR.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredItem<BucketItem> MOLTEN_GREEN_SUGAR_BUCKET = ITEMS.register("molten_green_sugar_bucket", () -> new BucketItem(KoratioFluids.MOLTEN_GREEN_SUGAR.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredItem<BucketItem> MOLTEN_YELLOW_SUGAR_BUCKET = ITEMS.register("molten_yellow_sugar_bucket", () -> new BucketItem(KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredItem<BucketItem> MOLTEN_RED_SUGAR_BUCKET = ITEMS.register("molten_red_sugar_bucket", () -> new BucketItem(KoratioFluids.MOLTEN_RED_SUGAR.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredItem<BucketItem> CHOCOLATE_MILK_BUCKET = ITEMS.register("chocolate_milk_bucket", () -> new BucketItem(KoratioFluids.CHOCOLATE_MILK.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredItem<BucketItem> BLOOD_BUCKET = ITEMS.register("blood_bucket", () -> new BucketItem(KoratioFluids.BLOOD.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredItem<Item> DEMONIC_HEART = ITEMS.register("demonic_heart", () -> new Item(new Item.Properties()));

	//Gems
	public static final DeferredItem<Item> RAINBOW_GEM = ITEMS.register("rainbow_gem", () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
	public static final DeferredItem<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> TOPAZ = ITEMS.register("topaz", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> ONYX = ITEMS.register("onyx", () -> new Item(new Item.Properties()));

	//Accessories

	//Weapons
	public static final DeferredItem<Item> BONE_SWORD = ITEMS.register("bone_sword", () -> new SwordItem(KoratioItemTier.BONE,new Item.Properties().attributes(SwordItem.createAttributes(KoratioItemTier.BONE, 3, -2.4F))));
	public static final DeferredItem<Item> WITHER_BONE_SWORD = ITEMS.register("wither_bone_sword", () -> new SwordItem(KoratioItemTier.WITHER_BONE,new Item.Properties().attributes(SwordItem.createAttributes(KoratioItemTier.WITHER_BONE, 3, -2.4F)).fireResistant()));
	
	//Tools
	public static final DeferredItem<PipingBagItem> PIPING_BAG = ITEMS.register("piping_bag", () -> new PipingBagItem(new Item.Properties().stacksTo(1).component(KoratioDataComponents.PIPING_BAG_DATA.get(), new KoratioDataComponents.PipingBagRecord("none", 0))));

	public static final DeferredItem<Item> WOODEN_ICING_SPATULA = ITEMS.register("wooden_icing_spatula", () -> new SpatulaItem(Tiers.WOOD, new Item.Properties().attributes(SpatulaItem.createAttributes(Tiers.WOOD, 0.5F, 0.0F))));
	public static final DeferredItem<Item> STONE_ICING_SPATULA = ITEMS.register("stone_icing_spatula", () -> new SpatulaItem(Tiers.STONE, new Item.Properties().attributes(SpatulaItem.createAttributes(Tiers.STONE, 0.5F, 0.0F))));
	public static final DeferredItem<Item> GOLDEN_ICING_SPATULA = ITEMS.register("golden_icing_spatula", () -> new SpatulaItem(Tiers.GOLD, new Item.Properties().attributes(SpatulaItem.createAttributes(Tiers.GOLD, 0.5F, 0.0F))));
	public static final DeferredItem<Item> IRON_ICING_SPATULA = ITEMS.register("iron_icing_spatula", () -> new SpatulaItem(Tiers.IRON, new Item.Properties().attributes(SpatulaItem.createAttributes(Tiers.IRON, 0.5F, 0.0F))));
	public static final DeferredItem<Item> DIAMOND_ICING_SPATULA = ITEMS.register("diamond_icing_spatula", () -> new SpatulaItem(Tiers.DIAMOND, new Item.Properties().attributes(SpatulaItem.createAttributes(Tiers.DIAMOND, 0.5F, 0.0F))));
	public static final DeferredItem<Item> NETHERITE_ICING_SPATULA = ITEMS.register("netherite_icing_spatula", () -> new SpatulaItem(Tiers.NETHERITE, new Item.Properties().attributes(SpatulaItem.createAttributes(Tiers.NETHERITE, 0.5F, 0.0F))));
	
	public static final DeferredItem<Item> BONE_SHOVEL = ITEMS.register("bone_shovel", () -> new ShovelItem(KoratioItemTier.BONE, new Item.Properties().attributes(ShovelItem.createAttributes(KoratioItemTier.BONE, 1.5F, -3.0F))));
	public static final DeferredItem<Item> BONE_PICKAXE = ITEMS.register("bone_pickaxe", () -> new PickaxeItem(KoratioItemTier.BONE, new Item.Properties().attributes(PickaxeItem.createAttributes(KoratioItemTier.BONE, 1.0F, -2.8F))));
	public static final DeferredItem<Item> BONE_AXE = ITEMS.register("bone_axe", () -> new AxeItem(KoratioItemTier.BONE, new Item.Properties().attributes(AxeItem.createAttributes(KoratioItemTier.BONE, 5.0F, -3.0F))));
	public static final DeferredItem<Item> BONE_HOE = ITEMS.register("bone_hoe", () -> new HoeItem(KoratioItemTier.BONE, new Item.Properties().attributes(HoeItem.createAttributes(KoratioItemTier.BONE, -4.0F, 0.0F))));
	public static final DeferredItem<Item> BONE_ICING_SPATULA = ITEMS.register("bone_icing_spatula", () -> new SpatulaItem(KoratioItemTier.BONE, new Item.Properties().attributes(SpatulaItem.createAttributes(KoratioItemTier.BONE, 0.5F, 0.0F))));

	public static final DeferredItem<Item> WITHER_BONE_SHOVEL = ITEMS.register("wither_bone_shovel", () -> new ShovelItem(KoratioItemTier.WITHER_BONE, new Item.Properties().attributes(ShovelItem.createAttributes(KoratioItemTier.WITHER_BONE, 1.5F, -3.0F)).fireResistant()));
	public static final DeferredItem<Item> WITHER_BONE_PICKAXE = ITEMS.register("wither_bone_pickaxe", () -> new PickaxeItem(KoratioItemTier.WITHER_BONE, new Item.Properties().attributes(PickaxeItem.createAttributes(KoratioItemTier.WITHER_BONE, 1.0F, -2.8F)).fireResistant()));
	public static final DeferredItem<Item> WITHER_BONE_AXE = ITEMS.register("wither_bone_axe", () -> new AxeItem(KoratioItemTier.WITHER_BONE, new Item.Properties().attributes(AxeItem.createAttributes(KoratioItemTier.WITHER_BONE, 5.0F, -3.0F)).fireResistant()));
	public static final DeferredItem<Item> WITHER_BONE_HOE = ITEMS.register("wither_bone_hoe", () -> new HoeItem(KoratioItemTier.WITHER_BONE, new Item.Properties().attributes(HoeItem.createAttributes(KoratioItemTier.WITHER_BONE, -4.0F, 0.0F)).fireResistant()));
	public static final DeferredItem<Item> WITHER_BONE_ICING_SPATULA = ITEMS.register("wither_bone_icing_spatula", () -> new SpatulaItem(KoratioItemTier.WITHER_BONE, new Item.Properties().attributes(SpatulaItem.createAttributes(KoratioItemTier.WITHER_BONE, 0.5F, 0.0F))));

	//Armor
	public static final DeferredItem<Item> BONE_HELMET = ITEMS.register("bone_helmet", () -> new ArmorItem(KoratioArmorMaterials.BONE, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final DeferredItem<Item> BONE_CHESTPLATE = ITEMS.register("bone_chestplate", () -> new ArmorItem(KoratioArmorMaterials.BONE, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final DeferredItem<Item> BONE_LEGGINGS = ITEMS.register("bone_leggings", () -> new ArmorItem(KoratioArmorMaterials.BONE, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final DeferredItem<Item> BONE_BOOTS = ITEMS.register("bone_boots", () -> new ArmorItem(KoratioArmorMaterials.BONE, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final DeferredItem<Item> WITHER_BONE_HELMET = ITEMS.register("wither_bone_helmet", () -> new ArmorItem(KoratioArmorMaterials.WITHER_BONE, ArmorItem.Type.HELMET,new Item.Properties().fireResistant()));
	public static final DeferredItem<Item> WITHER_BONE_CHESTPLATE = ITEMS.register("wither_bone_chestplate", () -> new ArmorItem(KoratioArmorMaterials.WITHER_BONE, ArmorItem.Type.CHESTPLATE,new Item.Properties().fireResistant()));
	public static final DeferredItem<Item> WITHER_BONE_LEGGINGS = ITEMS.register("wither_bone_leggings", () -> new ArmorItem(KoratioArmorMaterials.WITHER_BONE, ArmorItem.Type.LEGGINGS,new Item.Properties().fireResistant()));
	public static final DeferredItem<Item> WITHER_BONE_BOOTS = ITEMS.register("wither_bone_boots", () -> new ArmorItem(KoratioArmorMaterials.WITHER_BONE, ArmorItem.Type.BOOTS,new Item.Properties().fireResistant()));
	
	//FoodItems
	public static final DeferredItem<Item> RAW_PANGO = ITEMS.register("raw_pango", () -> new Item(new Item.Properties().food(KoratioFoods.RAW_PANGO)));
	public static final DeferredItem<Item> CRACKED_PANGO = ITEMS.register("cracked_pango", () -> new Item(new Item.Properties().food(KoratioFoods.CRACKED_PANGO)));
	public static final DeferredItem<Item> FLUFFER = ITEMS.register("fluffer", () -> new Item(new Item.Properties().food(KoratioFoods.FLUFFER)));
	public static final DeferredItem<Item> COOKED_FLUFFER = ITEMS.register("cooked_fluffer", () -> new Item(new Item.Properties().food(KoratioFoods.COOKED_FLUFFER)));
	public static final DeferredItem<Item> SPIKED_PORKCHOP = ITEMS.register("spiked_porkchop", () -> new Item(new Item.Properties().food(KoratioFoods.SPIKED_PORKCHOP)));
	public static final DeferredItem<Item> COOKED_SPIKED_PORKCHOP = ITEMS.register("cooked_spiked_porkchop", () -> new Item(new Item.Properties().food(KoratioFoods.COOKED_SPIKED_PORKCHOP)));

	//Candy Canes
	public static final DeferredItem<Item> WHITE_BLUE_CANDY_CANE = ITEMS.register("white_blue_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> WHITE_GREEN_CANDY_CANE = ITEMS.register("white_green_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> WHITE_YELLOW_CANDY_CANE = ITEMS.register("white_yellow_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> WHITE_RED_CANDY_CANE = ITEMS.register("white_red_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.RED.getColor()));

	public static final DeferredItem<Item> BLUE_CANDY_CANE = ITEMS.register("blue_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> BLUE_WHITE_CANDY_CANE = ITEMS.register("blue_white_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.WHITE.getColor()));
	public static final DeferredItem<Item> BLUE_GREEN_CANDY_CANE = ITEMS.register("blue_green_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> BLUE_YELLOW_CANDY_CANE = ITEMS.register("blue_yellow_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> BLUE_RED_CANDY_CANE = ITEMS.register("blue_red_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.RED.getColor()));
	public static final DeferredItem<Item> BLUE_RAINBOW_CANDY_CANE = ITEMS.register("blue_rainbow_candy_cane", () -> new RainbowCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.RED.getColor()));

	public static final DeferredItem<Item> GREEN_CANDY_CANE = ITEMS.register("green_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> GREEN_WHITE_CANDY_CANE = ITEMS.register("green_white_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.WHITE.getColor()));
	public static final DeferredItem<Item> GREEN_BLUE_CANDY_CANE = ITEMS.register("green_blue_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> GREEN_YELLOW_CANDY_CANE = ITEMS.register("green_yellow_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> GREEN_RED_CANDY_CANE = ITEMS.register("green_red_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.RED.getColor()));
	public static final DeferredItem<Item> GREEN_RAINBOW_CANDY_CANE = ITEMS.register("green_rainbow_candy_cane", () -> new RainbowCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.BLUE.getColor()));

	public static final DeferredItem<Item> YELLOW_CANDY_CANE = ITEMS.register("yellow_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> YELLOW_WHITE_CANDY_CANE = ITEMS.register("yellow_white_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.WHITE.getColor()));
	public static final DeferredItem<Item> YELLOW_BLUE_CANDY_CANE = ITEMS.register("yellow_blue_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> YELLOW_GREEN_CANDY_CANE = ITEMS.register("yellow_green_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> YELLOW_RED_CANDY_CANE = ITEMS.register("yellow_red_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.RED.getColor()));
	public static final DeferredItem<Item> YELLOW_RAINBOW_CANDY_CANE = ITEMS.register("yellow_rainbow_candy_cane", () -> new RainbowCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.BLUE.getColor()));

	public static final DeferredItem<Item> RED_CANDY_CANE = ITEMS.register("red_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.RED.getColor()));
	public static final DeferredItem<Item> RED_WHITE_CANDY_CANE = ITEMS.register("red_white_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.WHITE.getColor()));
	public static final DeferredItem<Item> RED_BLUE_CANDY_CANE = ITEMS.register("red_blue_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> RED_GREEN_CANDY_CANE = ITEMS.register("red_green_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> RED_YELLOW_CANDY_CANE = ITEMS.register("red_yellow_candy_cane", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> RED_RAINBOW_CANDY_CANE = ITEMS.register("red_rainbow_candy_cane", () -> new RainbowCandyItem(new Item.Properties().food(KoratioFoods.CANDY_CANE), CandyItem.CandyType.CANDY_CANE, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.YELLOW.getColor()));

	//LOLLIPOPS
	public static final DeferredItem<Item> WHITE_BLUE_LOLLIPOP = ITEMS.register("white_blue_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> WHITE_GREEN_LOLLIPOP = ITEMS.register("white_green_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> WHITE_YELLOW_LOLLIPOP = ITEMS.register("white_yellow_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> WHITE_RED_LOLLIPOP = ITEMS.register("white_red_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.RED.getColor()));

	public static final DeferredItem<Item> BLUE_LOLLIPOP = ITEMS.register("blue_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> BLUE_WHITE_LOLLIPOP = ITEMS.register("blue_white_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.WHITE.getColor()));
	public static final DeferredItem<Item> BLUE_GREEN_LOLLIPOP = ITEMS.register("blue_green_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> BLUE_YELLOW_LOLLIPOP = ITEMS.register("blue_yellow_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> BLUE_RED_LOLLIPOP = ITEMS.register("blue_red_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.RED.getColor()));
	public static final DeferredItem<Item> BLUE_RAINBOW_LOLLIPOP = ITEMS.register("blue_rainbow_lollipop", () -> new RainbowCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.RED.getColor()));

	public static final DeferredItem<Item> GREEN_LOLLIPOP = ITEMS.register("green_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> GREEN_WHITE_LOLLIPOP = ITEMS.register("green_white_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.WHITE.getColor()));
	public static final DeferredItem<Item> GREEN_BLUE_LOLLIPOP = ITEMS.register("green_blue_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> GREEN_YELLOW_LOLLIPOP = ITEMS.register("green_yellow_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> GREEN_RED_LOLLIPOP = ITEMS.register("green_red_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.RED.getColor()));
	public static final DeferredItem<Item> GREEN_RAINBOW_LOLLIPOP = ITEMS.register("green_rainbow_lollipop", () -> new RainbowCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.BLUE.getColor()));

	public static final DeferredItem<Item> YELLOW_LOLLIPOP = ITEMS.register("yellow_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> YELLOW_WHITE_LOLLIPOP = ITEMS.register("yellow_white_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.WHITE.getColor()));
	public static final DeferredItem<Item> YELLOW_BLUE_LOLLIPOP = ITEMS.register("yellow_blue_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> YELLOW_GREEN_LOLLIPOP = ITEMS.register("yellow_green_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> YELLOW_RED_LOLLIPOP = ITEMS.register("yellow_red_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.RED.getColor()));
	public static final DeferredItem<Item> YELLOW_RAINBOW_LOLLIPOP = ITEMS.register("yellow_rainbow_lollipop", () -> new RainbowCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.YELLOW.getColor(), CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.BLUE.getColor()));

	public static final DeferredItem<Item> RED_LOLLIPOP = ITEMS.register("red_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.RED.getColor()));
	public static final DeferredItem<Item> RED_WHITE_LOLLIPOP = ITEMS.register("red_white_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.WHITE.getColor()));
	public static final DeferredItem<Item> RED_BLUE_LOLLIPOP = ITEMS.register("red_blue_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.BLUE.getColor()));
	public static final DeferredItem<Item> RED_GREEN_LOLLIPOP = ITEMS.register("red_green_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.GREEN.getColor()));
	public static final DeferredItem<Item> RED_YELLOW_LOLLIPOP = ITEMS.register("red_yellow_lollipop", () -> new ColoredCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.YELLOW.getColor()));
	public static final DeferredItem<Item> RED_RAINBOW_LOLLIPOP = ITEMS.register("red_rainbow_lollipop", () -> new RainbowCandyItem(new Item.Properties().food(KoratioFoods.LOLLIPOP), CandyItem.CandyType.LOLLIPOP, CandyItem.CandyColor.WHITE.getColor(), CandyItem.CandyColor.RED.getColor(), CandyItem.CandyColor.BLUE.getColor(), CandyItem.CandyColor.GREEN.getColor(), CandyItem.CandyColor.YELLOW.getColor()));

	//Plants
	public static final DeferredItem<Item> RAINBOW_ROSE = ITEMS.register("rainbow_rose", () -> new BlockItem(KoratioBlocks.RAINBOW_ROSE.get(), new Item.Properties()));
	public static final DeferredItem<Item> RAINBOW_ALLIUM = ITEMS.register("rainbow_allium", () -> new BlockItem(KoratioBlocks.RAINBOW_ALLIUM.get(), new Item.Properties()));
	public static final DeferredItem<Item> RAINBOW_LILY_OF_THE_VALLEY = ITEMS.register("rainbow_lily_of_the_valley", () -> new BlockItem(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), new Item.Properties()));
	public static final DeferredItem<Item> FANTASIA_GRASS = ITEMS.register("fantasia_grass", () -> new BlockItem(KoratioBlocks.FANTASIA_GRASS.get(), new Item.Properties()));
	public static final DeferredItem<Item> TALL_FANTASIA_GRASS = ITEMS.register("tall_fantasia_grass", () -> new DoubleHighBlockItem(KoratioBlocks.TALL_FANTASIA_GRASS.get(), new Item.Properties()));
	public static final DeferredItem<Item> COTTON_CANDY_GRASS = ITEMS.register("cotton_candy_grass", () -> new BlockItem(KoratioBlocks.COTTON_CANDY_GRASS.get(), new Item.Properties()));
	public static final DeferredItem<Item> COOKIE_FLOWER = ITEMS.register("cookie_flower", () -> new BlockItem(KoratioBlocks.COOKIE_FLOWER.get(), new Item.Properties()));
	public static final DeferredItem<Item> WHITE_SUGARGLASS_FLOWER = ITEMS.register("white_sugarglass_flower", () -> new BlockItem(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final DeferredItem<Item> BLUE_SUGARGLASS_FLOWER = ITEMS.register("blue_sugarglass_flower", () -> new BlockItem(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final DeferredItem<Item> GREEN_SUGARGLASS_FLOWER = ITEMS.register("green_sugarglass_flower", () -> new BlockItem(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final DeferredItem<Item> YELLOW_SUGARGLASS_FLOWER = ITEMS.register("yellow_sugarglass_flower", () -> new BlockItem(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final DeferredItem<Item> RED_SUGARGLASS_FLOWER = ITEMS.register("red_sugarglass_flower", () -> new BlockItem(KoratioBlocks.RED_SUGARGLASS_FLOWER.get(), new Item.Properties()));
	public static final DeferredItem<Item> GILDED_VINES = ITEMS.register("gilded_vines", () -> new BlockItem(KoratioBlocks.GILDED_VINES.get(), new Item.Properties()));
	public static final DeferredItem<Item> GOLDEN_TULIP = ITEMS.register("golden_tulip", () -> new BlockItem(KoratioBlocks.GOLDEN_TULIP.get(), new Item.Properties()));
	public static final DeferredItem<Item> GOLD_ROSE_BUSH = ITEMS.register("gold_rose_bush", () -> new DoubleHighBlockItem(KoratioBlocks.GOLD_ROSE_BUSH.get(), new Item.Properties()));
	public static final DeferredItem<Item> CEINANA = ITEMS.register("ceinana", () -> new ItemNameBlockItem(KoratioBlocks.CEINANAS.get(), new Item.Properties()));
	public static final DeferredItem<Item> UPNIP = ITEMS.register("upnip", () -> new ItemNameBlockItem(KoratioBlocks.UPNIPS.get(), new Item.Properties()));
	public static final DeferredItem<Item> ICE_ROSE = ITEMS.register("ice_rose", () -> new BlockItem(KoratioBlocks.ICE_ROSE.get(), new Item.Properties()));
	
	//SpawnEggs
	public static final DeferredItem<Item> MAGICAL_CAT_SPAWN_EGG = SPAWN_EGGS.register("magical_cat_spawn_egg", () -> new DeferredSpawnEggItem(KoratioEntityType.MAGICAL_CAT, 0, 10682623, new Item.Properties()));
	public static final DeferredItem<Item> JUMSTEM_SPAWN_EGG = SPAWN_EGGS.register("jumstem_spawn_egg", () -> new DeferredSpawnEggItem(KoratioEntityType.JUMSTEM, 13485757, 14136960, new Item.Properties()));
	public static final DeferredItem<Item> SPIKY_PIG_SPAWN_EGG = SPAWN_EGGS.register("spiky_pig_spawn_egg", () -> new DeferredSpawnEggItem(KoratioEntityType.SPIKY_PIG, 7923315, 12955180, new Item.Properties()));
	public static final DeferredItem<Item> DEMONIC_ZOMBIE_SPAWN_EGG = SPAWN_EGGS.register("demonic_zombie_spawn_egg", () -> new DeferredSpawnEggItem(KoratioEntityType.DEMONIC_ZOMBIE, 44975, 16711776, new Item.Properties()));
	public static final DeferredItem<Item> DEMONIC_SKELETON_SPAWN_EGG = SPAWN_EGGS.register("demonic_skeleton_spawn_egg", () -> new DeferredSpawnEggItem(KoratioEntityType.DEMONIC_SKELETON, 12698049, 16711776, new Item.Properties()));
	public static final DeferredItem<Item> DEMONIC_SOLDIER_SPAWN_EGG = SPAWN_EGGS.register("demonic_soldier_spawn_egg", () -> new DeferredSpawnEggItem(KoratioEntityType.DEMONIC_SOLDIER, 5395026, 16711776, new Item.Properties()));

	//BlockItems
	public static final DeferredItem<BlockItem> FLIPPED_FARMLAND = ITEMS.register("flipped_farmland", () -> new BlockItem(KoratioBlocks.FLIPPED_FARMLAND.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> DECRYPTING_TABLE = ITEMS.register("decrypting_table", () -> new BlockItem(KoratioBlocks.DECRYPTING_TABLE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> WOODCUTTER = ITEMS.register("woodcutter", () -> new BlockItem(KoratioBlocks.WOODCUTTER.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_SHAPER = ITEMS.register("candy_shaper", () -> new BlockItem(KoratioBlocks.CANDY_SHAPER.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAINBOW_GEM_BLOCK = ITEMS.register("rainbow_gem_block", () -> new BlockItem(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> RUBY_BLOCK = ITEMS.register("ruby_block", () -> new BlockItem(KoratioBlocks.RUBY_BLOCK.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> SAPPHIRE_BLOCK = ITEMS.register("sapphire_block", () -> new BlockItem(KoratioBlocks.SAPPHIRE_BLOCK.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> TOPAZ_BLOCK = ITEMS.register("topaz_block", () -> new BlockItem(KoratioBlocks.TOPAZ_BLOCK.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> ONYX_BLOCK = ITEMS.register("onyx_block", () -> new BlockItem(KoratioBlocks.ONYX_BLOCK.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> RUBY_ORE = ITEMS.register("ruby_ore", () -> new BlockItem(KoratioBlocks.RUBY_ORE.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> SAPPHIRE_ORE = ITEMS.register("sapphire_ore", () -> new BlockItem(KoratioBlocks.SAPPHIRE_ORE.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> TOPAZ_ORE = ITEMS.register("topaz_ore", () -> new BlockItem(KoratioBlocks.TOPAZ_ORE.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> ONYX_ORE = ITEMS.register("onyx_ore", () -> new BlockItem(KoratioBlocks.ONYX_ORE.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> DEEPSLATE_RUBY_ORE = ITEMS.register("deepslate_ruby_ore", () -> new BlockItem(KoratioBlocks.DEEPSLATE_RUBY_ORE.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> DEEPSLATE_SAPPHIRE_ORE = ITEMS.register("deepslate_sapphire_ore", () -> new BlockItem(KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> DEEPSLATE_TOPAZ_ORE = ITEMS.register("deepslate_topaz_ore", () -> new BlockItem(KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> DEEPSLATE_ONYX_ORE = ITEMS.register("deepslate_onyx_ore", () -> new BlockItem(KoratioBlocks.DEEPSLATE_ONYX_ORE.get(), new Item.Properties().fireResistant()));
	public static final DeferredItem<BlockItem> COOKIE_ORE = ITEMS.register("cookie_ore", () -> new BlockItem(KoratioBlocks.COOKIE_ORE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> DEEPSLATE_COOKIE_ORE = ITEMS.register("deepslate_cookie_ore", () -> new BlockItem(KoratioBlocks.DEEPSLATE_COOKIE_ORE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> SUGAR_BLOCK = ITEMS.register("sugar_block", () -> new BlockItem(KoratioBlocks.SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STICKY_SUGAR_BLOCK = ITEMS.register("sticky_sugar_block", () -> new BlockItem(KoratioBlocks.STICKY_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> FROSTING_BLOCK = ITEMS.register("frosting_block", () -> new BlockItem(KoratioBlocks.FROSTING_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_SUGAR_BLOCK = ITEMS.register("blue_sugar_block", () -> new BlockItem(KoratioBlocks.BLUE_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STICKY_BLUE_SUGAR_BLOCK = ITEMS.register("sticky_blue_sugar_block", () -> new BlockItem(KoratioBlocks.STICKY_BLUE_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_FROSTING_BLOCK = ITEMS.register("blue_frosting_block", () -> new BlockItem(KoratioBlocks.BLUE_FROSTING_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_SUGAR_BLOCK = ITEMS.register("green_sugar_block", () -> new BlockItem(KoratioBlocks.GREEN_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STICKY_GREEN_SUGAR_BLOCK = ITEMS.register("sticky_green_sugar_block", () -> new BlockItem(KoratioBlocks.STICKY_GREEN_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_FROSTING_BLOCK = ITEMS.register("green_frosting_block", () -> new BlockItem(KoratioBlocks.GREEN_FROSTING_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> YELLOW_SUGAR_BLOCK = ITEMS.register("yellow_sugar_block", () -> new BlockItem(KoratioBlocks.YELLOW_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STICKY_YELLOW_SUGAR_BLOCK = ITEMS.register("sticky_yellow_sugar_block", () -> new BlockItem(KoratioBlocks.STICKY_YELLOW_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> YELLOW_FROSTING_BLOCK = ITEMS.register("yellow_frosting_block", () -> new BlockItem(KoratioBlocks.YELLOW_FROSTING_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RED_SUGAR_BLOCK = ITEMS.register("red_sugar_block", () -> new BlockItem(KoratioBlocks.RED_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STICKY_RED_SUGAR_BLOCK = ITEMS.register("sticky_red_sugar_block", () -> new BlockItem(KoratioBlocks.STICKY_RED_SUGAR_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RED_FROSTING_BLOCK = ITEMS.register("red_frosting_block", () -> new BlockItem(KoratioBlocks.RED_FROSTING_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> WHITE_CANDY_BLOCK = ITEMS.register("white_candy_block", () -> new BlockItem(KoratioBlocks.WHITE_CANDY_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_CANDY_BLOCK = ITEMS.register("blue_candy_block", () -> new BlockItem(KoratioBlocks.BLUE_CANDY_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_CANDY_BLOCK = ITEMS.register("green_candy_block", () -> new BlockItem(KoratioBlocks.GREEN_CANDY_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> YELLOW_CANDY_BLOCK = ITEMS.register("yellow_candy_block", () -> new BlockItem(KoratioBlocks.YELLOW_CANDY_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RED_CANDY_BLOCK = ITEMS.register("red_candy_block", () -> new BlockItem(KoratioBlocks.RED_CANDY_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> WHITE_LEVITATING_WOOL = ITEMS.register("white_levitating_wool", () -> new BlockItem(KoratioBlocks.WHITE_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ORANGE_LEVITATING_WOOL = ITEMS.register("orange_levitating_wool", () -> new BlockItem(KoratioBlocks.ORANGE_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> MAGENTA_LEVITATING_WOOL = ITEMS.register("magenta_levitating_wool", () -> new BlockItem(KoratioBlocks.MAGENTA_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> LIGHT_BLUE_LEVITATING_WOOL = ITEMS.register("light_blue_levitating_wool", () -> new BlockItem(KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> YELLOW_LEVITATING_WOOL = ITEMS.register("yellow_levitating_wool", () -> new BlockItem(KoratioBlocks.YELLOW_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> LIME_LEVITATING_WOOL = ITEMS.register("lime_levitating_wool", () -> new BlockItem(KoratioBlocks.LIME_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PINK_LEVITATING_WOOL = ITEMS.register("pink_levitating_wool", () -> new BlockItem(KoratioBlocks.PINK_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GRAY_LEVITATING_WOOL = ITEMS.register("gray_levitating_wool", () -> new BlockItem(KoratioBlocks.GRAY_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> LIGHT_GRAY_LEVITATING_WOOL = ITEMS.register("light_gray_levitating_wool", () -> new BlockItem(KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_LEVITATING_WOOL = ITEMS.register("cyan_levitating_wool", () -> new BlockItem(KoratioBlocks.CYAN_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PURPLE_LEVITATING_WOOL = ITEMS.register("purple_levitating_wool", () -> new BlockItem(KoratioBlocks.PURPLE_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_LEVITATING_WOOL = ITEMS.register("blue_levitating_wool", () -> new BlockItem(KoratioBlocks.BLUE_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BROWN_LEVITATING_WOOL = ITEMS.register("brown_levitating_wool", () -> new BlockItem(KoratioBlocks.BROWN_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_LEVITATING_WOOL = ITEMS.register("green_levitating_wool", () -> new BlockItem(KoratioBlocks.GREEN_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RED_LEVITATING_WOOL = ITEMS.register("red_levitating_wool", () -> new BlockItem(KoratioBlocks.RED_LEVITATING_WOOL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLACK_LEVITATING_WOOL = ITEMS.register("black_levitating_wool", () -> new BlockItem(KoratioBlocks.BLACK_LEVITATING_WOOL.get(), new Item.Properties()));

	public static final DeferredItem<Item> RAINBOW_CRYSTAL_SHARD = ITEMS.register("rainbow_crystal_shard", () -> new Item(new Item.Properties()));
	public static final DeferredItem<BlockItem> SMALL_RAINBOW_CRYSTAL_BUD = ITEMS.register("small_rainbow_crystal_bud", () -> new BlockItem(KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> MEDIUM_RAINBOW_CRYSTAL_BUD = ITEMS.register("medium_rainbow_crystal_bud", () -> new BlockItem(KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> LARGE_RAINBOW_CRYSTAL_BUD = ITEMS.register("large_rainbow_crystal_bud", () -> new BlockItem(KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAINBOW_CRYSTAL_CLUSTER = ITEMS.register("rainbow_crystal_cluster", () -> new BlockItem(KoratioBlocks.RAINBOW_CRYSTAL_CLUSTER.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAINBOW_CRYSTAL_BLOCK = ITEMS.register("rainbow_crystal_block", () -> new BlockItem(KoratioBlocks.RAINBOW_CRYSTAL_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BUDDING_RAINBOW_CRYSTAL = ITEMS.register("budding_rainbow_crystal", () -> new BlockItem(KoratioBlocks.BUDDING_RAINBOW_CRYSTAL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAINBOW_CRYSTAL_GLASS = ITEMS.register("rainbow_crystal_glass", () -> new BlockItem(KoratioBlocks.RAINBOW_CRYSTAL_GLASS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAINBOW_CRYSTAL_GLASS_PANE = ITEMS.register("rainbow_crystal_glass_pane", () -> new BlockItem(KoratioBlocks.RAINBOW_CRYSTAL_GLASS_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAINBOW_TORCH = ITEMS.register("rainbow_torch", () -> new StandingAndWallBlockItem(KoratioBlocks.RAINBOW_TORCH.get(), KoratioBlocks.RAINBOW_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
	public static final DeferredItem<BlockItem> RAINBOW_LANTERN = ITEMS.register("rainbow_lantern", () -> new BlockItem(KoratioBlocks.RAINBOW_LANTERN.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAINBOW_CAMPFIRE = ITEMS.register("rainbow_campfire", () -> new BlockItem(KoratioBlocks.RAINBOW_CAMPFIRE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAINBOW_CANDLE = ITEMS.register("rainbow_candle", () -> new BlockItem(KoratioBlocks.RAINBOW_CANDLE.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> RAW_GINGERBREAD_BLOCK = ITEMS.register("raw_gingerbread_block", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_GINGERBREAD_STAIRS = ITEMS.register("raw_gingerbread_stairs", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_GINGERBREAD_SLAB = ITEMS.register("raw_gingerbread_slab", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GINGERBREAD_BLOCK = ITEMS.register("gingerbread_block", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GINGERBREAD_STAIRS = ITEMS.register("gingerbread_stairs", () -> new BlockItem(KoratioBlocks.GINGERBREAD_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GINGERBREAD_SLAB = ITEMS.register("gingerbread_slab", () -> new BlockItem(KoratioBlocks.GINGERBREAD_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_GINGERBREAD_BRICKS = ITEMS.register("raw_gingerbread_bricks", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_GINGERBREAD_BRICK_STAIRS = ITEMS.register("raw_gingerbread_brick_stairs", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_GINGERBREAD_BRICK_SLAB = ITEMS.register("raw_gingerbread_brick_slab", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GINGERBREAD_BRICKS = ITEMS.register("gingerbread_bricks", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BRICKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GINGERBREAD_BRICK_STAIRS = ITEMS.register("gingerbread_brick_stairs", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GINGERBREAD_BRICK_SLAB = ITEMS.register("gingerbread_brick_slab", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_LARGE_GINGERBREAD_BRICKS = ITEMS.register("raw_large_gingerbread_bricks", () -> new BlockItem(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_LARGE_GINGERBREAD_BRICK_STAIRS = ITEMS.register("raw_large_gingerbread_brick_stairs", () -> new BlockItem(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_LARGE_GINGERBREAD_BRICK_SLAB = ITEMS.register("raw_large_gingerbread_brick_slab", () -> new BlockItem(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> LARGE_GINGERBREAD_BRICKS = ITEMS.register("large_gingerbread_bricks", () -> new BlockItem(KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> LARGE_GINGERBREAD_BRICK_STAIRS = ITEMS.register("large_gingerbread_brick_stairs", () -> new BlockItem(KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> LARGE_GINGERBREAD_BRICK_SLAB = ITEMS.register("large_gingerbread_brick_slab", () -> new BlockItem(KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_GINGERBREAD_WALL = ITEMS.register("raw_gingerbread_wall", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GINGERBREAD_WALL = ITEMS.register("gingerbread_wall", () -> new BlockItem(KoratioBlocks.GINGERBREAD_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_GINGERBREAD_BRICK_WALL = ITEMS.register("raw_gingerbread_brick_wall", () -> new BlockItem(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GINGERBREAD_BRICK_WALL = ITEMS.register("gingerbread_brick_wall", () -> new BlockItem(KoratioBlocks.GINGERBREAD_BRICK_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RAW_LARGE_GINGERBREAD_BRICK_WALL = ITEMS.register("raw_large_gingerbread_brick_wall", () -> new BlockItem(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> LARGE_GINGERBREAD_BRICK_WALL = ITEMS.register("large_gingerbread_brick_wall", () -> new BlockItem(KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> MARSHMALLOW_BLOCK = ITEMS.register("marshmallow_block", () -> new BlockItem(KoratioBlocks.MARSHMALLOW_BLOCK.get(), new Item.Properties()));

	//Environmental
	public static final DeferredItem<BlockItem> MAGIC_PATH = ITEMS.register("magic_path", () -> new BlockItem(KoratioBlocks.MAGIC_PATH.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> ANCIENT_COBBLESTONE = ITEMS.register("ancient_cobblestone", () -> new BlockItem(KoratioBlocks.ANCIENT_COBBLESTONE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_COBBLESTONE_STAIRS = ITEMS.register("ancient_cobblestone_stairs", () -> new BlockItem(KoratioBlocks.ANCIENT_COBBLESTONE_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_COBBLESTONE_SLAB = ITEMS.register("ancient_cobblestone_slab", () -> new BlockItem(KoratioBlocks.ANCIENT_COBBLESTONE_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_COBBLESTONE_WALL = ITEMS.register("ancient_cobblestone_wall", () -> new BlockItem(KoratioBlocks.ANCIENT_COBBLESTONE_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE = ITEMS.register("ancient_stone", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_STAIRS = ITEMS.register("ancient_stone_stairs", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_SLAB = ITEMS.register("ancient_stone_slab", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_BRICKS = ITEMS.register("ancient_stone_bricks", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_BRICKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_BRICK_STAIRS = ITEMS.register("ancient_stone_brick_stairs", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_BRICK_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_BRICK_SLAB = ITEMS.register("ancient_stone_brick_slab", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_BRICK_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CRACKED_ANCIENT_STONE_BRICKS = ITEMS.register("cracked_ancient_stone_bricks", () -> new BlockItem(KoratioBlocks.CRACKED_ANCIENT_STONE_BRICKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CHISELED_ANCIENT_STONE_BRICKS = ITEMS.register("chiseled_ancient_stone_bricks", () -> new BlockItem(KoratioBlocks.CHISELED_ANCIENT_STONE_BRICKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> POLISHED_ANCIENT_STONE = ITEMS.register("polished_ancient_stone", () -> new BlockItem(KoratioBlocks.POLISHED_ANCIENT_STONE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> POLISHED_ANCIENT_STONE_STAIRS = ITEMS.register("polished_ancient_stone_stairs", () -> new BlockItem(KoratioBlocks.POLISHED_ANCIENT_STONE_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> POLISHED_ANCIENT_STONE_SLAB = ITEMS.register("polished_ancient_stone_slab", () -> new BlockItem(KoratioBlocks.POLISHED_ANCIENT_STONE_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_TILES = ITEMS.register("ancient_stone_tiles", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_TILES.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_TILE_STAIRS = ITEMS.register("ancient_stone_tile_stairs", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_TILE_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_TILE_SLAB = ITEMS.register("ancient_stone_tile_slab", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_TILE_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_BRICK_WALL = ITEMS.register("ancient_stone_brick_wall", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_BRICK_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> POLISHED_ANCIENT_STONE_WALL = ITEMS.register("polished_ancient_stone_wall", () -> new BlockItem(KoratioBlocks.POLISHED_ANCIENT_STONE_WALL.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_STONE_TILE_WALL = ITEMS.register("ancient_stone_tile_wall", () -> new BlockItem(KoratioBlocks.ANCIENT_STONE_TILE_WALL.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> ANCIENT_FURNACE = ITEMS.register("ancient_furnace", () -> new BlockItem(KoratioBlocks.ANCIENT_FURNACE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ANCIENT_TELEPORTER = ITEMS.register("ancient_teleporter", () -> new BlockItem(KoratioBlocks.ANCIENT_TELEPORTER.get(), new Item.Properties()));

	// Vanilla Variants
	public static final DeferredItem<BlockItem> OAK_LEAF_PANE = ITEMS.register("oak_leaf_pane", () -> new BlockItem(KoratioBlocks.OAK_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> SPRUCE_LEAF_PANE = ITEMS.register("spruce_leaf_pane", () -> new BlockItem(KoratioBlocks.SPRUCE_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BIRCH_LEAF_PANE = ITEMS.register("birch_leaf_pane", () -> new BlockItem(KoratioBlocks.BIRCH_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> JUNGLE_LEAF_PANE = ITEMS.register("jungle_leaf_pane", () -> new BlockItem(KoratioBlocks.JUNGLE_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ACACIA_LEAF_PANE = ITEMS.register("acacia_leaf_pane", () -> new BlockItem(KoratioBlocks.ACACIA_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> DARK_OAK_LEAF_PANE = ITEMS.register("dark_oak_leaf_pane", () -> new BlockItem(KoratioBlocks.DARK_OAK_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> MANGROVE_LEAF_PANE = ITEMS.register("mangrove_leaf_pane", () -> new BlockItem(KoratioBlocks.MANGROVE_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> AZALEA_LEAF_PANE = ITEMS.register("azalea_leaf_pane", () -> new BlockItem(KoratioBlocks.AZALEA_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> FLOWERING_AZALEA_LEAF_PANE = ITEMS.register("flowering_azalea_leaf_pane", () -> new BlockItem(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CHERRY_LEAF_PANE = ITEMS.register("cherry_leaf_pane", () -> new BlockItem(KoratioBlocks.CHERRY_LEAF_PANE.get(), new Item.Properties()));
	//public static final DeferredItem<BlockItem> PALE_OAK_LEAF_PANE = ITEMS.register("pale_oak_leaf_pane", () -> new BlockItem(KoratioBlocks.PALE_OAK_LEAF_PANE.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> TALL_OAK_DOOR = ITEMS.register("tall_oak_door", () -> new BlockItem(KoratioBlocks.TALL_OAK_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_SPRUCE_DOOR = ITEMS.register("tall_spruce_door", () -> new BlockItem(KoratioBlocks.TALL_SPRUCE_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_BIRCH_DOOR = ITEMS.register("tall_birch_door", () -> new BlockItem(KoratioBlocks.TALL_BIRCH_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_JUNGLE_DOOR = ITEMS.register("tall_jungle_door", () -> new BlockItem(KoratioBlocks.TALL_JUNGLE_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_ACACIA_DOOR = ITEMS.register("tall_acacia_door", () -> new BlockItem(KoratioBlocks.TALL_ACACIA_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_DARK_OAK_DOOR = ITEMS.register("tall_dark_oak_door", () -> new BlockItem(KoratioBlocks.TALL_DARK_OAK_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_MANGROVE_DOOR = ITEMS.register("tall_mangrove_door", () -> new BlockItem(KoratioBlocks.TALL_MANGROVE_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_CHERRY_DOOR = ITEMS.register("tall_cherry_door", () -> new BlockItem(KoratioBlocks.TALL_CHERRY_DOOR.get(), new Item.Properties()));
	//public static final DeferredItem<BlockItem> TALL_PALE_OAK_DOOR = ITEMS.register("tall_pale_oak_door", () -> new BlockItem(KoratioBlocks.TALL_PALE_OAK_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_BAMBOO_DOOR = ITEMS.register("tall_bamboo_door", () -> new BlockItem(KoratioBlocks.TALL_BAMBOO_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_CRIMSON_DOOR = ITEMS.register("tall_crimson_door", () -> new BlockItem(KoratioBlocks.TALL_CRIMSON_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_WARPED_DOOR = ITEMS.register("tall_warped_door", () -> new BlockItem(KoratioBlocks.TALL_WARPED_DOOR.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> TALL_IRON_DOOR = ITEMS.register("tall_iron_door", () -> new BlockItem(KoratioBlocks.TALL_IRON_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_COPPER_DOOR = ITEMS.register("tall_copper_door", () -> new BlockItem(KoratioBlocks.TALL_COPPER_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_EXPOSED_COPPER_DOOR = ITEMS.register("tall_exposed_copper_door", () -> new BlockItem(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_OXIDIZED_COPPER_DOOR = ITEMS.register("tall_oxidized_copper_door", () -> new BlockItem(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_WEATHERED_COPPER_DOOR = ITEMS.register("tall_weathered_copper_door", () -> new BlockItem(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_WAXED_COPPER_DOOR = ITEMS.register("tall_waxed_copper_door", () -> new BlockItem(KoratioBlocks.TALL_WAXED_COPPER_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_WAXED_EXPOSED_COPPER_DOOR = ITEMS.register("tall_waxed_exposed_copper_door", () -> new BlockItem(KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_WAXED_OXIDIZED_COPPER_DOOR = ITEMS.register("tall_waxed_oxidized_copper_door", () -> new BlockItem(KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_WAXED_WEATHERED_COPPER_DOOR = ITEMS.register("tall_waxed_weathered_copper_door", () -> new BlockItem(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get(), new Item.Properties()));

	//Pango Wood
	public static final DeferredItem<BlockItem> PANGO_PLANKS = ITEMS.register("pango_planks", () -> new BlockItem(KoratioBlocks.PANGO_PLANKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_LOG = ITEMS.register("pango_log", () -> new BlockItem(KoratioBlocks.PANGO_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_PANGO_LOG = ITEMS.register("stripped_pango_log", () -> new BlockItem(KoratioBlocks.STRIPPED_PANGO_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_WOOD = ITEMS.register("pango_wood", () -> new BlockItem(KoratioBlocks.PANGO_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_PANGO_WOOD = ITEMS.register("stripped_pango_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_PANGO_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_LEAVES = ITEMS.register("pango_leaves", () -> new BlockItem(KoratioBlocks.PANGO_LEAVES.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_LEAF_PANE = ITEMS.register("pango_leaf_pane", () -> new BlockItem(KoratioBlocks.PANGO_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_SLAB = ITEMS.register("pango_slab", () -> new BlockItem(KoratioBlocks.PANGO_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_STAIRS = ITEMS.register("pango_stairs", () -> new BlockItem(KoratioBlocks.PANGO_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_SAPLING = ITEMS.register("pango_sapling", () -> new BlockItem(KoratioBlocks.PANGO_SAPLING.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_FENCE = ITEMS.register("pango_fence", () -> new BlockItem(KoratioBlocks.PANGO_FENCE.get(), new Item.Properties()));
	public static final DeferredItem<Item> PANGO_SIGN = ITEMS.register("pango_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_WALL_SIGN.get()));
	public static final DeferredItem<Item> PANGO_HANGING_SIGN = ITEMS.register("pango_hanging_sign",() -> new HangingSignItem(KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
	public static final DeferredItem<BlockItem> PANGO_BUTTON = ITEMS.register("pango_button", () -> new BlockItem(KoratioBlocks.PANGO_BUTTON.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_PRESSURE_PLATE = ITEMS.register("pango_pressure_plate", () -> new BlockItem(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_DOOR = ITEMS.register("pango_door", () -> new BlockItem(KoratioBlocks.PANGO_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_PANGO_DOOR = ITEMS.register("tall_pango_door", () -> new BlockItem(KoratioBlocks.TALL_PANGO_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_TRAPDOOR = ITEMS.register("pango_trapdoor", () -> new BlockItem(KoratioBlocks.PANGO_TRAPDOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_FENCE_GATE = ITEMS.register("pango_fence_gate", () -> new BlockItem(KoratioBlocks.PANGO_FENCE_GATE.get(), new Item.Properties()));
	
	public static final DeferredItem<BoatItem> PANGO_BOAT = ITEMS.register("pango_boat", () -> new BoatItem(false, Boat.Type.PANGO, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> PANGO_CHEST_BOAT = ITEMS.register("pango_chest_boat", () -> new BoatItem(true, Boat.Type.PANGO, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BlockItem> PANGO_BOOKSHELF = ITEMS.register("pango_bookshelf", () -> new BlockItem(KoratioBlocks.PANGO_BOOKSHELF.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PANGO_CHEST = ITEMS.register("pango_chest", () -> new BlockItem(KoratioBlocks.PANGO_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TRAPPED_PANGO_CHEST = ITEMS.register("trapped_pango_chest", () -> new BlockItem(KoratioBlocks.TRAPPED_PANGO_CHEST.get(), new Item.Properties()));
	
	//Rugona Wood
	public static final DeferredItem<BlockItem> RUGONA_PLANKS = ITEMS.register("rugona_planks", () -> new BlockItem(KoratioBlocks.RUGONA_PLANKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_LOG = ITEMS.register("rugona_log", () -> new BlockItem(KoratioBlocks.RUGONA_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_RUGONA_LOG = ITEMS.register("stripped_rugona_log", () -> new BlockItem(KoratioBlocks.STRIPPED_RUGONA_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_WOOD = ITEMS.register("rugona_wood", () -> new BlockItem(KoratioBlocks.RUGONA_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_RUGONA_WOOD = ITEMS.register("stripped_rugona_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_RUGONA_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_LEAVES = ITEMS.register("rugona_leaves", () -> new BlockItem(KoratioBlocks.RUGONA_LEAVES.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_LEAF_PANE = ITEMS.register("rugona_leaf_pane", () -> new BlockItem(KoratioBlocks.RUGONA_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_SLAB = ITEMS.register("rugona_slab", () -> new BlockItem(KoratioBlocks.RUGONA_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_STAIRS = ITEMS.register("rugona_stairs", () -> new BlockItem(KoratioBlocks.RUGONA_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_SAPLING = ITEMS.register("rugona_sapling", () -> new BlockItem(KoratioBlocks.RUGONA_SAPLING.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_FENCE = ITEMS.register("rugona_fence", () -> new BlockItem(KoratioBlocks.RUGONA_FENCE.get(), new Item.Properties()));
	public static final DeferredItem<Item> RUGONA_SIGN = ITEMS.register("rugona_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get()));
	public static final DeferredItem<Item> RUGONA_HANGING_SIGN = ITEMS.register("rugona_hanging_sign",() -> new HangingSignItem(KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
	public static final DeferredItem<BlockItem> RUGONA_BUTTON = ITEMS.register("rugona_button", () -> new BlockItem(KoratioBlocks.RUGONA_BUTTON.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_PRESSURE_PLATE = ITEMS.register("rugona_pressure_plate", () -> new BlockItem(KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_DOOR = ITEMS.register("rugona_door", () -> new BlockItem(KoratioBlocks.RUGONA_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_RUGONA_DOOR = ITEMS.register("tall_rugona_door", () -> new BlockItem(KoratioBlocks.TALL_RUGONA_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_TRAPDOOR = ITEMS.register("rugona_trapdoor", () -> new BlockItem(KoratioBlocks.RUGONA_TRAPDOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_FENCE_GATE = ITEMS.register("rugona_fence_gate", () -> new BlockItem(KoratioBlocks.RUGONA_FENCE_GATE.get(), new Item.Properties()));

	public static final DeferredItem<BoatItem> RUGONA_BOAT = ITEMS.register("rugona_boat", () -> new BoatItem(false, Boat.Type.RUGONA, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> RUGONA_CHEST_BOAT = ITEMS.register("rugona_chest_boat", () -> new BoatItem(true, Boat.Type.RUGONA, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BlockItem> RUGONA_BOOKSHELF = ITEMS.register("rugona_bookshelf", () -> new BlockItem(KoratioBlocks.RUGONA_BOOKSHELF.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> RUGONA_CHEST = ITEMS.register("rugona_chest", () -> new BlockItem(KoratioBlocks.RUGONA_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TRAPPED_RUGONA_CHEST = ITEMS.register("trapped_rugona_chest", () -> new BlockItem(KoratioBlocks.TRAPPED_RUGONA_CHEST.get(), new Item.Properties()));
	
	//Vareso Wood
	public static final DeferredItem<BlockItem> VARESO_PLANKS = ITEMS.register("vareso_planks", () -> new BlockItem(KoratioBlocks.VARESO_PLANKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_LOG = ITEMS.register("vareso_log", () -> new BlockItem(KoratioBlocks.VARESO_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_VARESO_LOG = ITEMS.register("stripped_vareso_log", () -> new BlockItem(KoratioBlocks.STRIPPED_VARESO_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_WOOD = ITEMS.register("vareso_wood", () -> new BlockItem(KoratioBlocks.VARESO_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_VARESO_WOOD = ITEMS.register("stripped_vareso_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_VARESO_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_LEAVES = ITEMS.register("vareso_leaves", () -> new BlockItem(KoratioBlocks.VARESO_LEAVES.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_LEAF_PANE = ITEMS.register("vareso_leaf_pane", () -> new BlockItem(KoratioBlocks.VARESO_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_SLAB = ITEMS.register("vareso_slab", () -> new BlockItem(KoratioBlocks.VARESO_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_STAIRS = ITEMS.register("vareso_stairs", () -> new BlockItem(KoratioBlocks.VARESO_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_SAPLING = ITEMS.register("vareso_sapling", () -> new BlockItem(KoratioBlocks.VARESO_SAPLING.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_FENCE = ITEMS.register("vareso_fence", () -> new BlockItem(KoratioBlocks.VARESO_FENCE.get(), new Item.Properties()));
	public static final DeferredItem<Item> VARESO_SIGN = ITEMS.register("vareso_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get()));
	public static final DeferredItem<Item> VARESO_HANGING_SIGN = ITEMS.register("vareso_hanging_sign",() -> new HangingSignItem(KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
	public static final DeferredItem<BlockItem> VARESO_BUTTON = ITEMS.register("vareso_button", () -> new BlockItem(KoratioBlocks.VARESO_BUTTON.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_PRESSURE_PLATE = ITEMS.register("vareso_pressure_plate", () -> new BlockItem(KoratioBlocks.VARESO_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_DOOR = ITEMS.register("vareso_door", () -> new BlockItem(KoratioBlocks.VARESO_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_VARESO_DOOR = ITEMS.register("tall_vareso_door", () -> new BlockItem(KoratioBlocks.TALL_VARESO_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_TRAPDOOR = ITEMS.register("vareso_trapdoor", () -> new BlockItem(KoratioBlocks.VARESO_TRAPDOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_FENCE_GATE = ITEMS.register("vareso_fence_gate", () -> new BlockItem(KoratioBlocks.VARESO_FENCE_GATE.get(), new Item.Properties()));
		
	public static final DeferredItem<BoatItem> VARESO_BOAT = ITEMS.register("vareso_boat", () -> new BoatItem(false, Boat.Type.VARESO, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> VARESO_CHEST_BOAT = ITEMS.register("vareso_chest_boat", () -> new BoatItem(true, Boat.Type.VARESO, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BlockItem> VARESO_BOOKSHELF = ITEMS.register("vareso_bookshelf", () -> new BlockItem(KoratioBlocks.VARESO_BOOKSHELF.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> VARESO_CHEST = ITEMS.register("vareso_chest", () -> new BlockItem(KoratioBlocks.VARESO_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TRAPPED_VARESO_CHEST = ITEMS.register("trapped_vareso_chest", () -> new BlockItem(KoratioBlocks.TRAPPED_VARESO_CHEST.get(), new Item.Properties()));

	//Candy Wood
	public static final DeferredItem<BlockItem> CANDY_PLANKS = ITEMS.register("candy_planks", () -> new BlockItem(KoratioBlocks.CANDY_PLANKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_LOG = ITEMS.register("candy_log", () -> new BlockItem(KoratioBlocks.CANDY_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_CANDY_LOG = ITEMS.register("stripped_candy_log", () -> new BlockItem(KoratioBlocks.STRIPPED_CANDY_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_WOOD = ITEMS.register("candy_wood", () -> new BlockItem(KoratioBlocks.CANDY_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_CANDY_WOOD = ITEMS.register("stripped_candy_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_CANDY_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> COTTON_CANDY_LEAVES = ITEMS.register("cotton_candy_leaves", () -> new BlockItem(KoratioBlocks.COTTON_CANDY_LEAVES.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> COTTON_CANDY_LEAF_PANE = ITEMS.register("cotton_candy_leaf_pane", () -> new BlockItem(KoratioBlocks.COTTON_CANDY_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_SLAB = ITEMS.register("candy_slab", () -> new BlockItem(KoratioBlocks.CANDY_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_STAIRS = ITEMS.register("candy_stairs", () -> new BlockItem(KoratioBlocks.CANDY_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_SAPLING = ITEMS.register("candy_sapling", () -> new BlockItem(KoratioBlocks.CANDY_SAPLING.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_FENCE = ITEMS.register("candy_fence", () -> new BlockItem(KoratioBlocks.CANDY_FENCE.get(), new Item.Properties()));
	public static final DeferredItem<Item> CANDY_SIGN = ITEMS.register("candy_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.CANDY_SIGN.get(), KoratioBlocks.CANDY_WALL_SIGN.get()));
	public static final DeferredItem<Item> CANDY_HANGING_SIGN = ITEMS.register("candy_hanging_sign",() -> new HangingSignItem(KoratioBlocks.CANDY_HANGING_SIGN.get(), KoratioBlocks.CANDY_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
	public static final DeferredItem<BlockItem> CANDY_BUTTON = ITEMS.register("candy_button", () -> new BlockItem(KoratioBlocks.CANDY_BUTTON.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_PRESSURE_PLATE = ITEMS.register("candy_pressure_plate", () -> new BlockItem(KoratioBlocks.CANDY_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_DOOR = ITEMS.register("candy_door", () -> new BlockItem(KoratioBlocks.CANDY_DOOR.get(), new Item.Properties()));
	//public static final DeferredItem<BlockItem> TALL_CANDY_DOOR = ITEMS.register("tall_candy_door", () -> new BlockItem(KoratioBlocks.TALL_CANDY_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_TRAPDOOR = ITEMS.register("candy_trapdoor", () -> new BlockItem(KoratioBlocks.CANDY_TRAPDOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_FENCE_GATE = ITEMS.register("candy_fence_gate", () -> new BlockItem(KoratioBlocks.CANDY_FENCE_GATE.get(), new Item.Properties()));

	public static final DeferredItem<BoatItem> CANDY_BOAT = ITEMS.register("candy_boat", () -> new BoatItem(false, Boat.Type.CANDY, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> CANDY_CHEST_BOAT = ITEMS.register("candy_chest_boat", () -> new BoatItem(true, Boat.Type.CANDY, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BlockItem> CANDY_BOOKSHELF = ITEMS.register("candy_bookshelf", () -> new BlockItem(KoratioBlocks.CANDY_BOOKSHELF.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CANDY_CHEST = ITEMS.register("candy_chest", () -> new BlockItem(KoratioBlocks.CANDY_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TRAPPED_CANDY_CHEST = ITEMS.register("trapped_candy_chest", () -> new BlockItem(KoratioBlocks.TRAPPED_CANDY_CHEST.get(), new Item.Properties()));

	//Elven Wood
	public static final DeferredItem<BlockItem> ELVEN_PLANKS = ITEMS.register("elven_planks", () -> new BlockItem(KoratioBlocks.ELVEN_PLANKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_PLANKS = ITEMS.register("blue_elven_planks", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_PLANKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_PLANKS = ITEMS.register("cyan_elven_planks", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_PLANKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_PLANKS = ITEMS.register("green_elven_planks", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_PLANKS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_LOG = ITEMS.register("elven_log", () -> new BlockItem(KoratioBlocks.ELVEN_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_ELVEN_LOG = ITEMS.register("stripped_elven_log", () -> new BlockItem(KoratioBlocks.STRIPPED_ELVEN_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_BLUE_ELVEN_LOG = ITEMS.register("stripped_blue_elven_log", () -> new BlockItem(KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_CYAN_ELVEN_LOG = ITEMS.register("stripped_cyan_elven_log", () -> new BlockItem(KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_GREEN_ELVEN_LOG = ITEMS.register("stripped_green_elven_log", () -> new BlockItem(KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_WOOD = ITEMS.register("elven_wood", () -> new BlockItem(KoratioBlocks.ELVEN_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_ELVEN_WOOD = ITEMS.register("stripped_elven_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_ELVEN_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_BLUE_ELVEN_WOOD = ITEMS.register("stripped_blue_elven_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_CYAN_ELVEN_WOOD = ITEMS.register("stripped_cyan_elven_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> STRIPPED_GREEN_ELVEN_WOOD = ITEMS.register("stripped_green_elven_wood", () -> new BlockItem(KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_LEAVES = ITEMS.register("elven_leaves", () -> new BlockItem(KoratioBlocks.ELVEN_LEAVES.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_LEAF_PANE = ITEMS.register("elven_leaf_pane", () -> new BlockItem(KoratioBlocks.ELVEN_LEAF_PANE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_SLAB = ITEMS.register("elven_slab", () -> new BlockItem(KoratioBlocks.ELVEN_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_SLAB = ITEMS.register("blue_elven_slab", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_SLAB = ITEMS.register("cyan_elven_slab", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_SLAB = ITEMS.register("green_elven_slab", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_SLAB.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_STAIRS = ITEMS.register("elven_stairs", () -> new BlockItem(KoratioBlocks.ELVEN_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_STAIRS = ITEMS.register("blue_elven_stairs", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_STAIRS = ITEMS.register("cyan_elven_stairs", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_STAIRS = ITEMS.register("green_elven_stairs", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_STAIRS.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_SAPLING = ITEMS.register("elven_sapling", () -> new BlockItem(KoratioBlocks.ELVEN_SAPLING.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_FENCE = ITEMS.register("elven_fence", () -> new BlockItem(KoratioBlocks.ELVEN_FENCE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_FENCE = ITEMS.register("blue_elven_fence", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_FENCE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_FENCE = ITEMS.register("cyan_elven_fence", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_FENCE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_FENCE = ITEMS.register("green_elven_fence", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_FENCE.get(), new Item.Properties()));
	public static final DeferredItem<Item> ELVEN_SIGN = ITEMS.register("elven_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.ELVEN_SIGN.get(), KoratioBlocks.ELVEN_WALL_SIGN.get()));
	public static final DeferredItem<Item> BLUE_ELVEN_SIGN = ITEMS.register("blue_elven_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.BLUE_ELVEN_SIGN.get(), KoratioBlocks.BLUE_ELVEN_WALL_SIGN.get()));
	public static final DeferredItem<Item> CYAN_ELVEN_SIGN = ITEMS.register("cyan_elven_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.CYAN_ELVEN_SIGN.get(), KoratioBlocks.CYAN_ELVEN_WALL_SIGN.get()));
	public static final DeferredItem<Item> GREEN_ELVEN_SIGN = ITEMS.register("green_elven_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), KoratioBlocks.GREEN_ELVEN_SIGN.get(), KoratioBlocks.GREEN_ELVEN_WALL_SIGN.get()));
	public static final DeferredItem<Item> ELVEN_HANGING_SIGN = ITEMS.register("elven_hanging_sign",() -> new HangingSignItem(KoratioBlocks.ELVEN_HANGING_SIGN.get(), KoratioBlocks.ELVEN_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> BLUE_ELVEN_HANGING_SIGN = ITEMS.register("blue_elven_hanging_sign",() -> new HangingSignItem(KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get(), KoratioBlocks.BLUE_ELVEN_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> CYAN_ELVEN_HANGING_SIGN = ITEMS.register("cyan_elven_hanging_sign",() -> new HangingSignItem(KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.CYAN_ELVEN_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> GREEN_ELVEN_HANGING_SIGN = ITEMS.register("green_elven_hanging_sign",() -> new HangingSignItem(KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.GREEN_ELVEN_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
	public static final DeferredItem<BlockItem> ELVEN_BUTTON = ITEMS.register("elven_button", () -> new BlockItem(KoratioBlocks.ELVEN_BUTTON.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_BUTTON = ITEMS.register("blue_elven_button", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_BUTTON.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_BUTTON = ITEMS.register("cyan_elven_button", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_BUTTON.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_BUTTON = ITEMS.register("green_elven_button", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_BUTTON.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_PRESSURE_PLATE = ITEMS.register("elven_pressure_plate", () -> new BlockItem(KoratioBlocks.ELVEN_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_PRESSURE_PLATE = ITEMS.register("blue_elven_pressure_plate", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_PRESSURE_PLATE = ITEMS.register("cyan_elven_pressure_plate", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_PRESSURE_PLATE = ITEMS.register("green_elven_pressure_plate", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_DOOR = ITEMS.register("elven_door", () -> new BlockItem(KoratioBlocks.ELVEN_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_DOOR = ITEMS.register("blue_elven_door", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_DOOR = ITEMS.register("cyan_elven_door", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_DOOR = ITEMS.register("green_elven_door", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_ELVEN_DOOR = ITEMS.register("tall_elven_door", () -> new BlockItem(KoratioBlocks.TALL_ELVEN_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_BLUE_ELVEN_DOOR = ITEMS.register("tall_blue_elven_door", () -> new BlockItem(KoratioBlocks.TALL_BLUE_ELVEN_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_CYAN_ELVEN_DOOR = ITEMS.register("tall_cyan_elven_door", () -> new BlockItem(KoratioBlocks.TALL_CYAN_ELVEN_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TALL_GREEN_ELVEN_DOOR = ITEMS.register("tall_green_elven_door", () -> new BlockItem(KoratioBlocks.TALL_GREEN_ELVEN_DOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_TRAPDOOR = ITEMS.register("elven_trapdoor", () -> new BlockItem(KoratioBlocks.ELVEN_TRAPDOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_TRAPDOOR = ITEMS.register("blue_elven_trapdoor", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_TRAPDOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_TRAPDOOR = ITEMS.register("cyan_elven_trapdoor", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_TRAPDOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_TRAPDOOR = ITEMS.register("green_elven_trapdoor", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_TRAPDOOR.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_FENCE_GATE = ITEMS.register("elven_fence_gate", () -> new BlockItem(KoratioBlocks.ELVEN_FENCE_GATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_FENCE_GATE = ITEMS.register("blue_elven_fence_gate", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_FENCE_GATE = ITEMS.register("cyan_elven_fence_gate", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_FENCE_GATE = ITEMS.register("green_elven_fence_gate", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get(), new Item.Properties()));

	public static final DeferredItem<BoatItem> ELVEN_BOAT = ITEMS.register("elven_boat", () -> new BoatItem(false, Boat.Type.ELVEN, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> BLUE_ELVEN_BOAT = ITEMS.register("blue_elven_boat", () -> new BoatItem(false, Boat.Type.BLUE_ELVEN, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> CYAN_ELVEN_BOAT = ITEMS.register("cyan_elven_boat", () -> new BoatItem(false, Boat.Type.CYAN_ELVEN, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> GREEN_ELVEN_BOAT = ITEMS.register("green_elven_boat", () -> new BoatItem(false, Boat.Type.GREEN_ELVEN, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> ELVEN_CHEST_BOAT = ITEMS.register("elven_chest_boat", () -> new BoatItem(true, Boat.Type.ELVEN, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> BLUE_ELVEN_CHEST_BOAT = ITEMS.register("blue_elven_chest_boat", () -> new BoatItem(true, Boat.Type.BLUE_ELVEN, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> CYAN_ELVEN_CHEST_BOAT = ITEMS.register("cyan_elven_chest_boat", () -> new BoatItem(true, Boat.Type.CYAN_ELVEN, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BoatItem> GREEN_ELVEN_CHEST_BOAT = ITEMS.register("green_elven_chest_boat", () -> new BoatItem(true, Boat.Type.GREEN_ELVEN, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<BlockItem> ELVEN_BOOKSHELF = ITEMS.register("elven_bookshelf", () -> new BlockItem(KoratioBlocks.ELVEN_BOOKSHELF.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_BOOKSHELF = ITEMS.register("blue_elven_bookshelf", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_BOOKSHELF = ITEMS.register("cyan_elven_bookshelf", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_BOOKSHELF = ITEMS.register("green_elven_bookshelf", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> ELVEN_CHEST = ITEMS.register("elven_chest", () -> new BlockItem(KoratioBlocks.ELVEN_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TRAPPED_ELVEN_CHEST = ITEMS.register("trapped_elven_chest", () -> new BlockItem(KoratioBlocks.TRAPPED_ELVEN_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> BLUE_ELVEN_CHEST = ITEMS.register("blue_elven_chest", () -> new BlockItem(KoratioBlocks.BLUE_ELVEN_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TRAPPED_BLUE_ELVEN_CHEST = ITEMS.register("trapped_blue_elven_chest", () -> new BlockItem(KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> CYAN_ELVEN_CHEST = ITEMS.register("cyan_elven_chest", () -> new BlockItem(KoratioBlocks.CYAN_ELVEN_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TRAPPED_CYAN_ELVEN_CHEST = ITEMS.register("trapped_cyan_elven_chest", () -> new BlockItem(KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_ELVEN_CHEST = ITEMS.register("green_elven_chest", () -> new BlockItem(KoratioBlocks.GREEN_ELVEN_CHEST.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> TRAPPED_GREEN_ELVEN_CHEST = ITEMS.register("trapped_green_elven_chest", () -> new BlockItem(KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get(), new Item.Properties()));
	
	public static final DeferredItem<BlockItem> PURPLE_MUSHROOM = ITEMS.register("purple_mushroom", () -> new BlockItem(KoratioBlocks.PURPLE_MUSHROOM.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> PURPLE_MUSHROOM_BLOCK = ITEMS.register("purple_mushroom_block", () -> new BlockItem(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_MUSHROOM = ITEMS.register("green_mushroom", () -> new BlockItem(KoratioBlocks.GREEN_MUSHROOM.get(), new Item.Properties()));
	public static final DeferredItem<BlockItem> GREEN_MUSHROOM_BLOCK = ITEMS.register("green_mushroom_block", () -> new BlockItem(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get(), new Item.Properties()));
}