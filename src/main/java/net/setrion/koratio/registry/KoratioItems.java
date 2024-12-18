package net.setrion.koratio.registry;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.util.ConversionUtils;
import net.setrion.koratio.world.item.*;

import java.awt.*;
import java.util.Map;
import java.util.function.Function;

public class KoratioItems {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Koratio.MOD_ID);
	public static final DeferredRegister.Items SPAWN_EGGS = DeferredRegister.createItems(Koratio.MOD_ID);

	public static final DeferredItem<Item> DEV_STICK = registerItem("dev_stick", DevToolItem::new, new Item.Properties());

	//Misc
	public static final DeferredItem<ScrollItem> SCROLL = registerItem("scroll", ScrollItem::new, new Item.Properties());
	public static final DeferredItem<Item> DECRYPTING_BOOK = registerItem("decrypting_book", properties -> new DecryptingBookItem(properties, 5), new Item.Properties());
	public static final DeferredItem<Item> BETTER_DECRYPTING_BOOK = registerItem("better_decrypting_book", properties -> new DecryptingBookItem(properties, 10), new Item.Properties());
	public static final DeferredItem<Item> FANTASTIC_DECRYPTING_BOOK = registerItem("fantastic_decrypting_book", properties -> new DecryptingBookItem(properties, 15), new Item.Properties());
	public static final DeferredItem<Item> WITHER_BONE = registerItem("wither_bone", Item::new, new Item.Properties().fireResistant());
	public static final DeferredItem<CandyTemplateItem> CANDY_CANE_CASTING_TEMPLATE = registerItem("candy_cane_casting_template", CandyTemplateItem::new, new Item.Properties());
	public static final DeferredItem<CandyTemplateItem> LOLLIPOP_CASTING_TEMPLATE = registerItem("lollipop_casting_template", CandyTemplateItem::new, new Item.Properties());
	public static final DeferredItem<CandyTemplateItem> BONBON_CASTING_TEMPLATE = registerItem("bonbon_casting_template", CandyTemplateItem::new, new Item.Properties());
	public static final DeferredItem<Item> LIGHT_GRAY_SUGAR = registerItem("light_gray_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> GRAY_SUGAR = registerItem("gray_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> BLACK_SUGAR = registerItem("black_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> BROWN_SUGAR = registerItem("brown_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> RED_SUGAR = registerItem("red_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> ORANGE_SUGAR = registerItem("orange_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> YELLOW_SUGAR = registerItem("yellow_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> LIME_SUGAR = registerItem("lime_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> GREEN_SUGAR = registerItem("green_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> CYAN_SUGAR = registerItem("cyan_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> LIGHT_BLUE_SUGAR = registerItem("light_blue_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> BLUE_SUGAR = registerItem("blue_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> PURPLE_SUGAR = registerItem("purple_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> MAGENTA_SUGAR = registerItem("magenta_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> PINK_SUGAR = registerItem("pink_sugar", Item::new, new Item.Properties());
	public static final DeferredItem<Item> STICKY_WHITE_SUGAR_BUCKET = registerItem("sticky_white_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_LIGHT_GRAY_SUGAR_BUCKET = registerItem("sticky_light_gray_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_GRAY_SUGAR_BUCKET = registerItem("sticky_gray_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_BLACK_SUGAR_BUCKET = registerItem("sticky_black_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_BROWN_SUGAR_BUCKET = registerItem("sticky_brown_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_RED_SUGAR_BUCKET = registerItem("sticky_red_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_ORANGE_SUGAR_BUCKET = registerItem("sticky_orange_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_YELLOW_SUGAR_BUCKET = registerItem("sticky_yellow_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_LIME_SUGAR_BUCKET = registerItem("sticky_lime_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_GREEN_SUGAR_BUCKET = registerItem("sticky_green_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_CYAN_SUGAR_BUCKET = registerItem("sticky_cyan_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_LIGHT_BLUE_SUGAR_BUCKET = registerItem("sticky_light_blue_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_BLUE_SUGAR_BUCKET = registerItem("sticky_blue_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_PURPLE_SUGAR_BUCKET = registerItem("sticky_purple_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_MAGENTA_SUGAR_BUCKET = registerItem("sticky_magenta_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> STICKY_PINK_SUGAR_BUCKET = registerItem("sticky_pink_sugar_bucket", Item::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_WHITE_SUGAR_BUCKET = registerItem("molten_white_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_WHITE_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_LIGHT_GRAY_SUGAR_BUCKET = registerItem("molten_light_gray_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_GRAY_SUGAR_BUCKET = registerItem("molten_gray_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_GRAY_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_BLACK_SUGAR_BUCKET = registerItem("molten_black_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_BLACK_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_BROWN_SUGAR_BUCKET = registerItem("molten_brown_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_BROWN_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_RED_SUGAR_BUCKET = registerItem("molten_red_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_RED_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_ORANGE_SUGAR_BUCKET = registerItem("molten_orange_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_ORANGE_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_YELLOW_SUGAR_BUCKET = registerItem("molten_yellow_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_LIME_SUGAR_BUCKET = registerItem("molten_lime_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_LIME_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_GREEN_SUGAR_BUCKET = registerItem("molten_green_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_GREEN_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_CYAN_SUGAR_BUCKET = registerItem("molten_cyan_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_CYAN_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_LIGHT_BLUE_SUGAR_BUCKET = registerItem("molten_light_blue_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_BLUE_SUGAR_BUCKET = registerItem("molten_blue_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_BLUE_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_PURPLE_SUGAR_BUCKET = registerItem("molten_purple_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_PURPLE_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_MAGENTA_SUGAR_BUCKET = registerItem("molten_magenta_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_MAGENTA_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> MOLTEN_PINK_SUGAR_BUCKET = registerItem("molten_pink_sugar_bucket", properties -> new BucketItem(KoratioFluids.MOLTEN_PINK_SUGAR.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> CHOCOLATE_MILK_BUCKET = registerItem("chocolate_milk_bucket", properties -> new BucketItem(KoratioFluids.CHOCOLATE_MILK.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<BucketItem> BLOOD_BUCKET = registerItem("blood_bucket", properties -> new BucketItem(KoratioFluids.BLOOD.get(), properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<Item> DEMONIC_HEART = registerItem("demonic_heart", Item::new, new Item.Properties());

	public static final DeferredItem<Item> VARYNIUM_INGOT = registerItem("varynium_ingot", properties -> new ConvertibleItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_INGOT, BuiltinDimensionTypes.NETHER, Items.GOLD_INGOT, BuiltinDimensionTypes.END, Items.DIAMOND), 600, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_NUGGET = registerItem("varynium_nugget", properties -> new ConvertibleItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, properties), new Item.Properties());
	public static final DeferredItem<Item> RAW_VARYNIUM = registerItem("raw_varynium", properties -> new ConvertibleItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, properties), new Item.Properties());

	//Gems
	public static final DeferredItem<Item> RAINBOW_CRYSTAL_SHARD = registerItem("rainbow_crystal_shard", Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAINBOW_GEM = registerItem("rainbow_gem", Item::new, new Item.Properties().fireResistant().rarity(Rarity.EPIC));
	public static final DeferredItem<Item> RUBY = registerItem("ruby", Item::new, new Item.Properties());
	public static final DeferredItem<Item> TOPAZ = registerItem("topaz", Item::new, new Item.Properties());
	public static final DeferredItem<Item> SAPPHIRE = registerItem("sapphire", Item::new, new Item.Properties());
	public static final DeferredItem<Item> ONYX = registerItem("onyx", Item::new, new Item.Properties());

	//Accessories

	//Weapons
	public static final DeferredItem<Item> BONE_SWORD = registerItem("bone_sword", properties -> new SwordItem(KoratioToolMaterials.BONE, 3, -2.4F, properties), new Item.Properties());
	public static final DeferredItem<Item> WITHER_BONE_SWORD = registerItem("wither_bone_sword", properties -> new SwordItem(KoratioToolMaterials.WITHER_BONE, 3, -2.4F, properties), new Item.Properties().fireResistant());
	public static final DeferredItem<Item> VARYNIUM_SWORD = registerItem("varynium_sword", properties -> new ConvertibleSwordItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioToolMaterials.VARYNIUM, 3, -2.4F, properties), new Item.Properties());
	
	//Tools
	public static final DeferredItem<PipingBagItem> PIPING_BAG = registerItem("piping_bag", PipingBagItem::new, new Item.Properties().stacksTo(1));

	public static final DeferredItem<Item> WOODEN_ICING_SPATULA = registerItem("wooden_icing_spatula", properties -> new SpatulaItem(ToolMaterial.WOOD, properties), new Item.Properties().attributes(SpatulaItem.createAttributes(ToolMaterial.WOOD, 0.5F, 0.0F)));
	public static final DeferredItem<Item> STONE_ICING_SPATULA = registerItem("stone_icing_spatula", properties -> new SpatulaItem(ToolMaterial.STONE, properties), new Item.Properties().attributes(SpatulaItem.createAttributes(ToolMaterial.STONE, 0.5F, 0.0F)));
	public static final DeferredItem<Item> GOLDEN_ICING_SPATULA = registerItem("golden_icing_spatula", properties -> new SpatulaItem(ToolMaterial.GOLD, properties), new Item.Properties().attributes(SpatulaItem.createAttributes(ToolMaterial.GOLD, 0.5F, 0.0F)));
	public static final DeferredItem<Item> IRON_ICING_SPATULA = registerItem("iron_icing_spatula", properties -> new SpatulaItem(ToolMaterial.IRON, properties), new Item.Properties().attributes(SpatulaItem.createAttributes(ToolMaterial.IRON, 0.5F, 0.0F)));
	public static final DeferredItem<Item> DIAMOND_ICING_SPATULA = registerItem("diamond_icing_spatula", properties -> new SpatulaItem(ToolMaterial.DIAMOND, properties), new Item.Properties().attributes(SpatulaItem.createAttributes(ToolMaterial.DIAMOND, 0.5F, 0.0F)));
	public static final DeferredItem<Item> NETHERITE_ICING_SPATULA = registerItem("netherite_icing_spatula", properties -> new SpatulaItem(ToolMaterial.NETHERITE, properties), new Item.Properties().attributes(SpatulaItem.createAttributes(ToolMaterial.NETHERITE, 0.5F, 0.0F)));
	
	public static final DeferredItem<Item> BONE_SHOVEL = registerItem("bone_shovel", properties -> new ShovelItem(KoratioToolMaterials.BONE, 1.5F, -3.0F, properties), new Item.Properties());
	public static final DeferredItem<Item> BONE_PICKAXE = registerItem("bone_pickaxe", properties -> new PickaxeItem(KoratioToolMaterials.BONE, 1.0F, -2.8F, properties), new Item.Properties());
	public static final DeferredItem<Item> BONE_AXE = registerItem("bone_axe", properties -> new AxeItem(KoratioToolMaterials.BONE, 5.0F, -3.0F, properties), new Item.Properties());
	public static final DeferredItem<Item> BONE_HOE = registerItem("bone_hoe", properties -> new HoeItem(KoratioToolMaterials.BONE, -4.0F, 0.0F, properties), new Item.Properties());
	public static final DeferredItem<Item> BONE_ICING_SPATULA = registerItem("bone_icing_spatula", properties -> new SpatulaItem(KoratioToolMaterials.BONE, properties), new Item.Properties().attributes(SpatulaItem.createAttributes(KoratioToolMaterials.BONE, 0.5F, 0.0F)));

	public static final DeferredItem<Item> WITHER_BONE_SHOVEL = registerItem("wither_bone_shovel", properties -> new ShovelItem(KoratioToolMaterials.WITHER_BONE, 1.5F, -3.0F, properties), new Item.Properties().fireResistant());
	public static final DeferredItem<Item> WITHER_BONE_PICKAXE = registerItem("wither_bone_pickaxe", properties -> new PickaxeItem(KoratioToolMaterials.WITHER_BONE, 1.0F, -2.8F, properties), new Item.Properties().fireResistant());
	public static final DeferredItem<Item> WITHER_BONE_AXE = registerItem("wither_bone_axe", properties -> new AxeItem(KoratioToolMaterials.WITHER_BONE, 5.0F, -3.0F, properties), new Item.Properties().fireResistant());
	public static final DeferredItem<Item> WITHER_BONE_HOE = registerItem("wither_bone_hoe", properties -> new HoeItem(KoratioToolMaterials.WITHER_BONE, -4.0F, 0.0F, properties), new Item.Properties().fireResistant());
	public static final DeferredItem<Item> WITHER_BONE_ICING_SPATULA = registerItem("wither_bone_icing_spatula", properties -> new SpatulaItem(KoratioToolMaterials.WITHER_BONE, properties), new Item.Properties().fireResistant().attributes(SpatulaItem.createAttributes(KoratioToolMaterials.WITHER_BONE, 0.5F, 0.0F)));

	public static final DeferredItem<Item> VARYNIUM_SHOVEL = registerItem("varynium_shovel", properties -> new ConvertibleShovelItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioToolMaterials.VARYNIUM, 1.5F, -3.0F, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_PICKAXE = registerItem("varynium_pickaxe", properties -> new ConvertiblePickaxeItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioToolMaterials.VARYNIUM, 1.0F, -2.8F, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_AXE = registerItem("varynium_axe", properties -> new ConvertibleAxeItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioToolMaterials.VARYNIUM, 5.0F, -3.0F, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_HOE = registerItem("varynium_hoe", properties -> new ConvertibleHoeItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioToolMaterials.VARYNIUM, -4.0F, 0.0F, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_ICING_SPATULA = registerItem("varynium_icing_spatula", properties -> new ConvertibleSpatulaItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioToolMaterials.VARYNIUM, properties), new Item.Properties().attributes(SpatulaItem.createAttributes(KoratioToolMaterials.VARYNIUM, 0.5F, 0.0F)));

	//Armor
	public static final DeferredItem<Item> BONE_HELMET = registerItem("bone_helmet", properties -> new ArmorItem(KoratioArmorMaterials.BONE, ArmorType.HELMET, properties), new Item.Properties());
	public static final DeferredItem<Item> BONE_CHESTPLATE = registerItem("bone_chestplate", properties -> new ArmorItem(KoratioArmorMaterials.BONE, ArmorType.CHESTPLATE, properties), new Item.Properties());
	public static final DeferredItem<Item> BONE_LEGGINGS = registerItem("bone_leggings", properties -> new ArmorItem(KoratioArmorMaterials.BONE, ArmorType.LEGGINGS, properties), new Item.Properties());
	public static final DeferredItem<Item> BONE_BOOTS = registerItem("bone_boots", properties -> new ArmorItem(KoratioArmorMaterials.BONE, ArmorType.BOOTS, properties), new Item.Properties());

	public static final DeferredItem<Item> WITHER_BONE_HELMET = registerItem("wither_bone_helmet", properties -> new ArmorItem(KoratioArmorMaterials.WITHER_BONE, ArmorType.HELMET, properties), new Item.Properties().fireResistant());
	public static final DeferredItem<Item> WITHER_BONE_CHESTPLATE = registerItem("wither_bone_chestplate", properties -> new ArmorItem(KoratioArmorMaterials.WITHER_BONE, ArmorType.CHESTPLATE, properties), new Item.Properties().fireResistant());
	public static final DeferredItem<Item> WITHER_BONE_LEGGINGS = registerItem("wither_bone_leggings", properties -> new ArmorItem(KoratioArmorMaterials.WITHER_BONE, ArmorType.LEGGINGS, properties), new Item.Properties().fireResistant());
	public static final DeferredItem<Item> WITHER_BONE_BOOTS = registerItem("wither_bone_boots", properties -> new ArmorItem(KoratioArmorMaterials.WITHER_BONE, ArmorType.BOOTS, properties), new Item.Properties().fireResistant());

	public static final DeferredItem<Item> VARYNIUM_HELMET = registerItem("varynium_helmet", properties -> new ConvertibleArmorItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioArmorMaterials.VARYNIUM, ArmorType.HELMET, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_CHESTPLATE = registerItem("varynium_chestplate", properties -> new ConvertibleArmorItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioArmorMaterials.VARYNIUM, ArmorType.CHESTPLATE, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_LEGGINGS = registerItem("varynium_leggings", properties -> new ConvertibleArmorItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioArmorMaterials.VARYNIUM, ArmorType.LEGGINGS, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_BOOTS = registerItem("varynium_boots", properties -> new ConvertibleArmorItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioArmorMaterials.VARYNIUM, ArmorType.BOOTS, properties), new Item.Properties());
	public static final DeferredItem<Item> VARYNIUM_HORSE_ARMOR = registerItem("varynium_horse_armor", properties -> new ConvertibleArmorItem(Map.of(BuiltinDimensionTypes.OVERWORLD, Items.IRON_HELMET, BuiltinDimensionTypes.NETHER, Items.GOLDEN_HELMET, BuiltinDimensionTypes.END, Items.DIAMOND_HELMET), 600, KoratioArmorMaterials.VARYNIUM, ArmorType.BODY, properties), new Item.Properties());

	//FoodItems
	public static final DeferredItem<Item> RAW_PANGO = registerItem("raw_pango", Item::new, new Item.Properties().food(KoratioFoods.RAW_PANGO));
	public static final DeferredItem<Item> CRACKED_PANGO = registerItem("cracked_pango", Item::new, new Item.Properties().food(KoratioFoods.CRACKED_PANGO));
	public static final DeferredItem<Item> FLUFFER = registerItem("fluffer", Item::new, new Item.Properties().food(KoratioFoods.FLUFFER));
	public static final DeferredItem<Item> COOKED_FLUFFER = registerItem("cooked_fluffer", Item::new, new Item.Properties().food(KoratioFoods.COOKED_FLUFFER));
	public static final DeferredItem<Item> SPIKED_PORKCHOP = registerItem("spiked_porkchop", Item::new, new Item.Properties().food(KoratioFoods.SPIKED_PORKCHOP));
	public static final DeferredItem<Item> COOKED_SPIKED_PORKCHOP = registerItem("cooked_spiked_porkchop", Item::new, new Item.Properties().food(KoratioFoods.COOKED_SPIKED_PORKCHOP));

	static {
		for (CandyItem.CandyType type : CandyItem.CandyType.values()) {
			for (CandyItem.CandyColor color : CandyItem.CandyColor.values()) {
				for (CandyItem.CandyColor color2 : CandyItem.CandyColor.values()) {
					String name;
					if (color == color2) {
						name = color.getSerializedName() + "_" + type.getSerializedName();
					} else {
						name = color.getSerializedName() + "_" + color2.getSerializedName() + "_" + type.getSerializedName();
					}
					registerItem(name, properties -> new ColoredCandyItem(properties, type, color, color2), new Item.Properties().food(type.getFood()));
				}
			}
		}
	}

	public static final DeferredItem<Item> CEINANA = registerItem("ceinana", properties -> new BlockItem(KoratioBlocks.CEINANAS.get(), properties), new Item.Properties());
	public static final DeferredItem<Item> UPNIP = registerItem("upnip", properties -> new BlockItem(KoratioBlocks.UPNIPS.get(), properties), new Item.Properties());

	//SpawnEggs
	public static final DeferredItem<Item> MAGICAL_CAT_SPAWN_EGG = registerSpawnEgg("magical_cat_spawn_egg", properties -> new SpawnEggItem(KoratioEntityType.MAGICAL_CAT.get(), properties), new Item.Properties()); //0, 10682623
	public static final DeferredItem<Item> JUMSTEM_SPAWN_EGG = registerSpawnEgg("jumstem_spawn_egg", properties -> new SpawnEggItem(KoratioEntityType.JUMSTEM.get(), properties), new Item.Properties()); //13485757, 14136960
	public static final DeferredItem<Item> MOOSHROOM_SPAWN_EGG = registerSpawnEgg("mooshroom_spawn_egg", properties -> new SpawnEggItem(KoratioEntityType.MOOSHROOM.get(), properties), new Item.Properties()); //8851360, 12040119
	public static final DeferredItem<Item> SPIKY_PIG_SPAWN_EGG = registerSpawnEgg("spiky_pig_spawn_egg", properties -> new SpawnEggItem(KoratioEntityType.SPIKY_PIG.get(), properties), new Item.Properties()); //7923315, 12955180
	public static final DeferredItem<Item> DEMONIC_ZOMBIE_SPAWN_EGG = registerSpawnEgg("demonic_zombie_spawn_egg", properties -> new SpawnEggItem(KoratioEntityType.DEMONIC_ZOMBIE.get(), properties), new Item.Properties()); //44975, 16711776
	public static final DeferredItem<Item> DEMONIC_SKELETON_SPAWN_EGG = registerSpawnEgg("demonic_skeleton_spawn_egg", properties -> new SpawnEggItem(KoratioEntityType.DEMONIC_SKELETON.get(), properties), new Item.Properties()); //12698049, 16711776
	public static final DeferredItem<Item> DEMONIC_SOLDIER_SPAWN_EGG = registerSpawnEgg("demonic_soldier_spawn_egg", properties -> new SpawnEggItem(KoratioEntityType.DEMONIC_SOLDIER.get(), properties), new Item.Properties()); //5395026, 16711776

	//BlockItems
	public static final DeferredItem<BlockItem> RAINBOW_TORCH = registerItem("rainbow_torch", properties -> new StandingAndWallBlockItem(KoratioBlocks.RAINBOW_TORCH.get(), KoratioBlocks.RAINBOW_WALL_TORCH.get(), Direction.DOWN, properties), new Item.Properties());

	public static final DeferredItem<Item> PANGO_SIGN = registerItem("pango_sign", properties -> new SignItem(KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> PANGO_HANGING_SIGN = registerItem("pango_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<BoatItem> PANGO_BOAT = registerItem("pango_boat", properties -> new BoatItem(KoratioEntityType.PANGO_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> PANGO_CHEST_BOAT = registerItem("pango_chest_boat", properties -> new BoatItem(KoratioEntityType.PANGO_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));

	public static final DeferredItem<Item> RUGONA_SIGN = registerItem("rugona_sign", properties -> new SignItem(KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> RUGONA_HANGING_SIGN = registerItem("rugona_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<BoatItem> RUGONA_BOAT = registerItem("rugona_boat", properties -> new BoatItem(KoratioEntityType.RUGONA_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> RUGONA_CHEST_BOAT = registerItem("rugona_chest_boat", properties -> new BoatItem(KoratioEntityType.RUGONA_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));

	public static final DeferredItem<Item> VARESO_SIGN = registerItem("vareso_sign", properties -> new SignItem(KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> VARESO_HANGING_SIGN = registerItem("vareso_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<BoatItem> VARESO_BOAT = registerItem("vareso_boat", properties -> new BoatItem(KoratioEntityType.VARESO_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> VARESO_CHEST_BOAT = registerItem("vareso_chest_boat", properties -> new BoatItem(KoratioEntityType.VARESO_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));

	public static final DeferredItem<Item> CANDY_SIGN = registerItem("candy_sign", properties -> new SignItem(KoratioBlocks.CANDY_SIGN.get(), KoratioBlocks.CANDY_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> CANDY_HANGING_SIGN = registerItem("candy_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.CANDY_HANGING_SIGN.get(), KoratioBlocks.CANDY_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<BoatItem> CANDY_BOAT = registerItem("candy_boat", properties -> new BoatItem(KoratioEntityType.CANDY_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> CANDY_CHEST_BOAT = registerItem("candy_chest_boat", properties -> new BoatItem(KoratioEntityType.CANDY_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));

	public static final DeferredItem<Item> CHOCOLATE_OAK_SIGN = registerItem("chocolate_oak_sign", properties -> new SignItem(KoratioBlocks.CHOCOLATE_OAK_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> CHOCOLATE_OAK_HANGING_SIGN = registerItem("chocolate_oak_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.CHOCOLATE_OAK_HANGING_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<BoatItem> CHOCOLATE_OAK_BOAT = registerItem("chocolate_oak_boat", properties -> new BoatItem(KoratioEntityType.CHOCOLATE_OAK_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> CHOCOLATE_OAK_CHEST_BOAT = registerItem("chocolate_oak_chest_boat", properties -> new BoatItem(KoratioEntityType.CHOCOLATE_OAK_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));

	public static final DeferredItem<Item> ELVEN_SIGN = registerItem("elven_sign", properties -> new SignItem(KoratioBlocks.ELVEN_SIGN.get(), KoratioBlocks.ELVEN_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> BLUE_ELVEN_SIGN = registerItem("blue_elven_sign", properties -> new SignItem(KoratioBlocks.BLUE_ELVEN_SIGN.get(), KoratioBlocks.BLUE_ELVEN_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> CYAN_ELVEN_SIGN = registerItem("cyan_elven_sign", properties -> new SignItem(KoratioBlocks.CYAN_ELVEN_SIGN.get(), KoratioBlocks.CYAN_ELVEN_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> GREEN_ELVEN_SIGN = registerItem("green_elven_sign", properties -> new SignItem(KoratioBlocks.GREEN_ELVEN_SIGN.get(), KoratioBlocks.GREEN_ELVEN_WALL_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> ELVEN_HANGING_SIGN = registerItem("elven_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.ELVEN_HANGING_SIGN.get(), KoratioBlocks.ELVEN_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> BLUE_ELVEN_HANGING_SIGN = registerItem("blue_elven_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get(), KoratioBlocks.BLUE_ELVEN_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> CYAN_ELVEN_HANGING_SIGN = registerItem("cyan_elven_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.CYAN_ELVEN_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> GREEN_ELVEN_HANGING_SIGN = registerItem("green_elven_hanging_sign", properties -> new HangingSignItem(KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.GREEN_ELVEN_WALL_HANGING_SIGN.get(), properties), new Item.Properties().stacksTo(16));
	public static final DeferredItem<BoatItem> ELVEN_BOAT = registerItem("elven_boat", properties -> new BoatItem(KoratioEntityType.ELVEN_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> BLUE_ELVEN_BOAT = registerItem("blue_elven_boat", properties -> new BoatItem(KoratioEntityType.BLUE_ELVEN_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> CYAN_ELVEN_BOAT = registerItem("cyan_elven_boat", properties -> new BoatItem(KoratioEntityType.CYAN_ELVEN_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> GREEN_ELVEN_BOAT = registerItem("green_elven_boat", properties -> new BoatItem(KoratioEntityType.GREEN_ELVEN_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> ELVEN_CHEST_BOAT = registerItem("elven_chest_boat", properties -> new BoatItem(KoratioEntityType.ELVEN_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> BLUE_ELVEN_CHEST_BOAT = registerItem("blue_elven_chest_boat", properties -> new BoatItem(KoratioEntityType.BLUE_ELVEN_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> CYAN_ELVEN_CHEST_BOAT = registerItem("cyan_elven_chest_boat", properties -> new BoatItem(KoratioEntityType.CYAN_ELVEN_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));
	public static final DeferredItem<BoatItem> GREEN_ELVEN_CHEST_BOAT = registerItem("green_elven_chest_boat", properties -> new BoatItem(KoratioEntityType.GREEN_ELVEN_CHEST_BOAT.get(), properties), new Item.Properties().stacksTo(1));

	public static <T extends Item> DeferredItem<T> registerItem(String name, Function<Item.Properties, T> item, Item.Properties properties) {
		return ITEMS.register(name, () -> item.apply(properties.setId(ResourceKey.create(Registries.ITEM, Koratio.prefix(name)))));
	}

	public static <T extends Item> DeferredItem<T> registerSpawnEgg(String name, Function<Item.Properties, T> item, Item.Properties properties) {
		return SPAWN_EGGS.register(name, () -> item.apply(properties.setId(ResourceKey.create(Registries.ITEM, Koratio.prefix(name)))));
	}
}