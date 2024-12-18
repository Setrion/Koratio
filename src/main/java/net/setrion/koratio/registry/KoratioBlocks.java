package net.setrion.koratio.registry;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.core.cauldron.CauldronInteraction;
import net.setrion.koratio.world.item.CandyItem;
import net.setrion.koratio.world.level.block.CampfireBlock;
import net.setrion.koratio.world.level.block.ChestBlock;
import net.setrion.koratio.world.level.block.TallGrassBlock;
import net.setrion.koratio.world.level.block.TrappedChestBlock;
import net.setrion.koratio.world.level.block.*;

import java.util.function.Function;
import java.util.function.Supplier;

public class KoratioBlocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Koratio.MOD_ID);

	//BlockSetTypes
	public static final BlockSetType PANGO_SET = new BlockSetType("pango");
	public static final BlockSetType RUGONA_SET = new BlockSetType("rugona");
	public static final BlockSetType VARESO_SET = new BlockSetType("vareso");
	public static final BlockSetType CANDY_SET = new BlockSetType("candy");
	public static final BlockSetType CHOCOLATE_OAK_SET = new BlockSetType("chocolate_oak");
	public static final BlockSetType ELVEN_SET = new BlockSetType("elven");
	public static final BlockSetType BLUE_ELVEN_SET = new BlockSetType("blue_elven");
	public static final BlockSetType CYAN_ELVEN_SET = new BlockSetType("cyan_elven");
	public static final BlockSetType GREEN_ELVEN_SET = new BlockSetType("green_elven");

	public static final BlockSetType COOKIE_BLOCK_SET = new BlockSetType("cookie_block");

	//WoodTypes
	public static final WoodType PANGO_TYPE = new WoodType("pango", PANGO_SET);
	public static final WoodType RUGONA_TYPE = new WoodType("rugona", RUGONA_SET);
	public static final WoodType VARESO_TYPE = new WoodType("vareso", VARESO_SET);
	public static final WoodType CANDY_TYPE = new WoodType("candy", CANDY_SET);
	public static final WoodType CHOCOLATE_OAK_TYPE = new WoodType("chocolate_oak", CHOCOLATE_OAK_SET);
	public static final WoodType ELVEN_TYPE = new WoodType("elven", ELVEN_SET);
	public static final WoodType BLUE_ELVEN_TYPE = new WoodType("blue_elven", BLUE_ELVEN_SET);
	public static final WoodType CYAN_ELVEN_TYPE = new WoodType("cyan_elven", CYAN_ELVEN_SET);
	public static final WoodType GREEN_ELVEN_TYPE = new WoodType("green_elven", GREEN_ELVEN_SET);

	//Portal
	public static final DeferredBlock<Block> FANTASIA_PORTAL = registerBlock("fantasia_portal", FantasiaPortalBlock::new, () -> BlockBehaviour.Properties.of().noCollission().randomTicks().strength(-1.0F, 3600000.0F).sound(SoundType.GLASS).lightLevel((state) -> 11));

	//Miniature Blocks (mostly used for advancements)
	public static final DeferredBlock<Block> MINIATURE_FANTASIA_PORTAL = registerBlockWithItem("miniature_portal", Block::new, () -> BlockBehaviour.Properties.of().noLootTable().noOcclusion());

	//Fluids
	public static final DeferredBlock<Block> MOLTEN_WHITE_SUGAR = registerBlock("molten_white_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_WHITE_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_LIGHT_GRAY_SUGAR = registerBlock("molten_light_gray_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_GRAY_SUGAR = registerBlock("molten_gray_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_GRAY_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_BLACK_SUGAR = registerBlock("molten_black_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_BLACK_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_BROWN_SUGAR = registerBlock("molten_brown_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_BROWN_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_RED_SUGAR = registerBlock("molten_red_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_RED_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_ORANGE_SUGAR = registerBlock("molten_orange_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_ORANGE_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_YELLOW_SUGAR = registerBlock("molten_yellow_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_LIME_SUGAR = registerBlock("molten_lime_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_LIME_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_GREEN_SUGAR = registerBlock("molten_green_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_GREEN_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_CYAN_SUGAR = registerBlock("molten_cyan_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_CYAN_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_LIGHT_BLUE_SUGAR = registerBlock("molten_light_blue_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_BLUE_SUGAR = registerBlock("molten_blue_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_BLUE_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_PURPLE_SUGAR = registerBlock("molten_purple_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_PURPLE_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_MAGENTA_SUGAR = registerBlock("molten_magenta_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_MAGENTA_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> MOLTEN_PINK_SUGAR = registerBlock("molten_pink_sugar", properties -> new LiquidBlock(KoratioFluids.MOLTEN_PINK_SUGAR.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> CHOCOLATE_MILK = registerBlock("chocolate_milk", properties -> new LiquidBlock(KoratioFluids.CHOCOLATE_MILK.get(), properties), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().randomTicks().strength(100.0F).noLootTable());
	public static final DeferredBlock<Block> BLOOD = registerBlock("blood", properties -> new LiquidBlock(KoratioFluids.BLOOD.get(), properties), () -> BlockBehaviour.Properties.of().replaceable().noCollission().pushReaction(PushReaction.DESTROY).randomTicks().strength(100.0F).noLootTable());

	//Minerals / Ores
	public static final DeferredBlock<Block> RAINBOW_GEM_BLOCK = registerBlockWithItem("rainbow_gem_block", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK));
	public static final DeferredBlock<Block> RUBY_BLOCK = registerBlockWithItem("ruby_block", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK));
	public static final DeferredBlock<Block> SAPPHIRE_BLOCK = registerBlockWithItem("sapphire_block", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK));
	public static final DeferredBlock<Block> TOPAZ_BLOCK = registerBlockWithItem("topaz_block", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK));
	public static final DeferredBlock<Block> ONYX_BLOCK = registerBlockWithItem("onyx_block", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK));

	public static final DeferredBlock<Block> RUBY_ORE = registerBlockWithItem("ruby_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
	public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE = registerBlockWithItem("deepslate_ruby_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));
	public static final DeferredBlock<Block> SAPPHIRE_ORE = registerBlockWithItem("sapphire_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
	public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlockWithItem("deepslate_sapphire_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));
	public static final DeferredBlock<Block> TOPAZ_ORE = registerBlockWithItem("topaz_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
	public static final DeferredBlock<Block> DEEPSLATE_TOPAZ_ORE = registerBlockWithItem("deepslate_topaz_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));
	public static final DeferredBlock<Block> ONYX_ORE = registerBlockWithItem("onyx_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
	public static final DeferredBlock<Block> DEEPSLATE_ONYX_ORE = registerBlockWithItem("deepslate_onyx_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));

	//Cookie
	public static final DeferredBlock<Block> COOKIE_ORE = registerBlockWithItem("cookie_ore", properties -> new DropExperienceBlock(ConstantInt.of(1), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
	public static final DeferredBlock<Block> DEEPSLATE_COOKIE_ORE = registerBlockWithItem("deepslate_cookie_ore", properties -> new DropExperienceBlock(ConstantInt.of(1), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));

	//Misc
	public static final DeferredBlock<Block> FLIPPED_FARMLAND = registerBlockWithItem("flipped_farmland", FlippedFarmBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking(KoratioBlocks::always).isSuffocating(KoratioBlocks::always));
	public static final DeferredBlock<Block> DECRYPTING_TABLE = registerBlockWithItem("decrypting_table", DecryptingTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
	public static final DeferredBlock<Block> WOODCUTTER = registerBlockWithItem("woodcutter", WoodcutterBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.WOOD).mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F));
	public static final DeferredBlock<Block> CANDY_SHAPER = registerBlockWithItem("candy_shaper", CandyShaperBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));

	public static final DeferredBlock<Block> SKELETON_REMAINS = registerBlockWithItem("skeleton_remains", properties -> new RemainsBlock(RemainsBlock.Type.SKELETON, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK));
	public static final DeferredBlock<Block> WITHER_SKELETON_REMAINS = registerBlockWithItem("wither_skeleton_remains", properties -> new RemainsBlock(RemainsBlock.Type.WITHER_SKELETON, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK));
	public static final DeferredBlock<Block> STRAY_REMAINS = registerBlockWithItem("stray_remains", properties -> new RemainsBlock(RemainsBlock.Type.STRAY, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK));
	public static final DeferredBlock<Block> BOGGED_REMAINS = registerBlockWithItem("bogged_remains", properties -> new RemainsBlock(RemainsBlock.Type.BOGGED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK));
	public static final DeferredBlock<Block> DEMONIC_SKELETON_REMAINS = registerBlockWithItem("demonic_skeleton_remains", properties -> new RemainsBlock(RemainsBlock.Type.DEMONIC_SKELETON, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK));

	public static final DeferredBlock<Block> ZOMBIE_REMAINS = registerBlockWithItem("zombie_remains", properties -> new RemainsBlock(RemainsBlock.Type.ZOMBIE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
	public static final DeferredBlock<Block> HUSK_REMAINS = registerBlockWithItem("husk_remains", properties -> new RemainsBlock(RemainsBlock.Type.HUSK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
	public static final DeferredBlock<Block> DROWNED_REMAINS = registerBlockWithItem("drowned_remains", properties -> new RemainsBlock(RemainsBlock.Type.DROWNED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
	public static final DeferredBlock<Block> DEMONIC_ZOMBIE_REMAINS = registerBlockWithItem("demonic_zombie_remains", properties -> new RemainsBlock(RemainsBlock.Type.DEMONIC_ZOMBIE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
	public static final DeferredBlock<Block> ZOMBIE_VILLAGER_REMAINS = registerBlockWithItem("zombie_villager_remains", properties -> new RemainsBlock(RemainsBlock.Type.ZOMBIE_VILLAGER, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

	public static final DeferredBlock<Block> PHANTOM_REMAINS = registerBlockWithItem("phantom_remains", properties -> new RemainsBlock(RemainsBlock.Type.PHANTOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

	public static final DeferredBlock<Block> ZOMBIFIED_PIGLIN_REMAINS = registerBlockWithItem("zombified_piglin_remains", properties -> new RemainsBlock(RemainsBlock.Type.ZOMBIFIED_PIGLIN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

	public static final DeferredBlock<Block> MOLTEN_WHITE_SUGAR_CAULDRON = registerBlock("molten_white_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_WHITE_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_LIGHT_GRAY_SUGAR_CAULDRON = registerBlock("molten_light_gray_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_LIGHT_GRAY_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_GRAY_SUGAR_CAULDRON = registerBlock("molten_gray_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_GRAY_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_BLACK_SUGAR_CAULDRON = registerBlock("molten_black_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_BLACK_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_BROWN_SUGAR_CAULDRON = registerBlock("molten_brown_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_BROWN_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_RED_SUGAR_CAULDRON = registerBlock("molten_red_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_RED_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_ORANGE_SUGAR_CAULDRON = registerBlock("molten_orange_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_ORANGE_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_YELLOW_SUGAR_CAULDRON = registerBlock("molten_yellow_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_YELLOW_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_LIME_SUGAR_CAULDRON = registerBlock("molten_lime_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_LIME_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_GREEN_SUGAR_CAULDRON = registerBlock("molten_green_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_GREEN_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_CYAN_SUGAR_CAULDRON = registerBlock("molten_cyan_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_CYAN_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_LIGHT_BLUE_SUGAR_CAULDRON = registerBlock("molten_light_blue_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_LIGHT_BLUE_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_BLUE_SUGAR_CAULDRON = registerBlock("molten_blue_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_BLUE_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_PURPLE_SUGAR_CAULDRON = registerBlock("molten_purple_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_PURPLE_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_MAGENTA_SUGAR_CAULDRON = registerBlock("molten_magenta_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_MAGENTA_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
	public static final DeferredBlock<Block> MOLTEN_PINK_SUGAR_CAULDRON = registerBlock("molten_pink_sugar_cauldron", properties ->  new MoltenSugarCauldron(properties, CauldronInteraction.MOLTEN_PINK_SUGAR), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));

	public static final DeferredBlock<Block> WHITE_LEVITATING_WOOL = registerBlockWithItem("white_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
	public static final DeferredBlock<Block> LIGHT_GRAY_LEVITATING_WOOL = registerBlockWithItem("light_gray_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL));
	public static final DeferredBlock<Block> GRAY_LEVITATING_WOOL = registerBlockWithItem("gray_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL));
	public static final DeferredBlock<Block> BLACK_LEVITATING_WOOL = registerBlockWithItem("black_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL));
	public static final DeferredBlock<Block> BROWN_LEVITATING_WOOL = registerBlockWithItem("brown_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL));
	public static final DeferredBlock<Block> RED_LEVITATING_WOOL = registerBlockWithItem("red_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL));
	public static final DeferredBlock<Block> ORANGE_LEVITATING_WOOL = registerBlockWithItem("orange_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL));
	public static final DeferredBlock<Block> YELLOW_LEVITATING_WOOL = registerBlockWithItem("yellow_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL));
	public static final DeferredBlock<Block> LIME_LEVITATING_WOOL = registerBlockWithItem("lime_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL));
	public static final DeferredBlock<Block> GREEN_LEVITATING_WOOL = registerBlockWithItem("green_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL));
	public static final DeferredBlock<Block> CYAN_LEVITATING_WOOL = registerBlockWithItem("cyan_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL));
	public static final DeferredBlock<Block> LIGHT_BLUE_LEVITATING_WOOL = registerBlockWithItem("light_blue_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL));
	public static final DeferredBlock<Block> BLUE_LEVITATING_WOOL = registerBlockWithItem("blue_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL));
	public static final DeferredBlock<Block> PURPLE_LEVITATING_WOOL = registerBlockWithItem("purple_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL));
	public static final DeferredBlock<Block> MAGENTA_LEVITATING_WOOL = registerBlockWithItem("magenta_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL));
	public static final DeferredBlock<Block> PINK_LEVITATING_WOOL = registerBlockWithItem("pink_levitating_wool", properties -> new ColoredLevitatingBlock(new ColorRGBA(14406560), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL));

	public static final DeferredBlock<Block> RAINBOW_CRYSTAL_BLOCK = registerBlockWithItem("rainbow_crystal_block", AmethystBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> BUDDING_RAINBOW_CRYSTAL = registerBlockWithItem("budding_rainbow_crystal", BuddingRainbowCrystalBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<Block> RAINBOW_CRYSTAL_CLUSTER = registerBlockWithItem("rainbow_crystal_cluster", properties -> new AmethystClusterBlock(7.0F, 3.0F, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel(state -> 5).pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<Block> LARGE_RAINBOW_CRYSTAL_BUD = registerBlockWithItem("large_rainbow_crystal_bud", properties -> new AmethystClusterBlock(5.0F, 3.0F, properties), () -> BlockBehaviour.Properties.ofFullCopy(RAINBOW_CRYSTAL_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel(state -> 4));
	public static final DeferredBlock<Block> MEDIUM_RAINBOW_CRYSTAL_BUD = registerBlockWithItem("medium_rainbow_crystal_bud", properties -> new AmethystClusterBlock(4.0F, 3.0F, properties), () -> BlockBehaviour.Properties.ofFullCopy(RAINBOW_CRYSTAL_CLUSTER.get()).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel(state -> 2));
	public static final DeferredBlock<Block> SMALL_RAINBOW_CRYSTAL_BUD = registerBlockWithItem("small_rainbow_crystal_bud", properties -> new AmethystClusterBlock(3.0F, 4.0F, properties), () -> BlockBehaviour.Properties.ofFullCopy(RAINBOW_CRYSTAL_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel(state -> 1));

	public static final DeferredBlock<Block> RAINBOW_CRYSTAL_GLASS = registerBlockWithItem("rainbow_crystal_glass", TransparentBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));
	public static final DeferredBlock<Block> RAINBOW_CRYSTAL_GLASS_PANE = registerBlockWithItem("rainbow_crystal_glass_pane", IronBarsBlock::new, () -> BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion());

	public static final DeferredBlock<RainbowFireBlock> RAINBOW_FIRE = registerBlock("rainbow_fire", RainbowFireBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_FIRE));
	public static final DeferredBlock<RainbowTorchBlock> RAINBOW_TORCH = registerBlock("rainbow_torch", RainbowTorchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_TORCH));
	public static final DeferredBlock<RainbowWallTorchBlock> RAINBOW_WALL_TORCH = registerBlock("rainbow_wall_torch", RainbowWallTorchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_WALL_TORCH).overrideLootTable(RAINBOW_TORCH.get().getLootTable()));
	public static final DeferredBlock<LanternBlock> RAINBOW_LANTERN = registerBlockWithItem("rainbow_lantern", LanternBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN));
	public static final DeferredBlock<CampfireBlock> RAINBOW_CAMPFIRE = registerBlockWithItem("rainbow_campfire", properties -> new CampfireBlock(true, 4, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_CAMPFIRE));
	public static final DeferredBlock<RainbowCandleBlock> RAINBOW_CANDLE = registerBlockWithItem("rainbow_candle", RainbowCandleBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION).pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<RainbowCandleCakeBlock> RAINBOW_CANDLE_CAKE = registerBlock("rainbow_candle_cake", properties -> new RainbowCandleCakeBlock(RAINBOW_CANDLE.get(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE_CAKE));

	//Candy Blocks
	public static final DeferredBlock<Block> COTTON_CANDY_GRASS = registerBlockWithItem("cotton_candy_grass", TallGrassBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
	public static final DeferredBlock<Block> COOKIE_FLOWER = registerBlockWithItem("cookie_flower", properties -> new FlowerBlock(MobEffects.SATURATION, 8, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OXEYE_DAISY));

	public static final DeferredBlock<Block> WHITE_SUGARGLASS_FLOWER = registerBlockWithItem("white_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.WHITE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> LIGHT_GRAY_SUGARGLASS_FLOWER = registerBlockWithItem("light_gray_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.LIGHT_GRAY, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> GRAY_SUGARGLASS_FLOWER = registerBlockWithItem("gray_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.GRAY, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> BLACK_SUGARGLASS_FLOWER = registerBlockWithItem("black_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.BLACK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> BROWN_SUGARGLASS_FLOWER = registerBlockWithItem("brown_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.BROWN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> RED_SUGARGLASS_FLOWER = registerBlockWithItem("red_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.RED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> ORANGE_SUGARGLASS_FLOWER = registerBlockWithItem("orange_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.ORANGE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> YELLOW_SUGARGLASS_FLOWER = registerBlockWithItem("yellow_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.YELLOW, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> LIME_SUGARGLASS_FLOWER = registerBlockWithItem("lime_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.LIME, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> GREEN_SUGARGLASS_FLOWER = registerBlockWithItem("green_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> CYAN_SUGARGLASS_FLOWER = registerBlockWithItem("cyan_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.CYAN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> LIGHT_BLUE_SUGARGLASS_FLOWER = registerBlockWithItem("light_blue_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.LIGHT_BLUE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> BLUE_SUGARGLASS_FLOWER = registerBlockWithItem("blue_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.BLUE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> PURPLE_SUGARGLASS_FLOWER = registerBlockWithItem("purple_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.PURPLE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> MAGENTA_SUGARGLASS_FLOWER = registerBlockWithItem("magenta_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.MAGENTA, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> PINK_SUGARGLASS_FLOWER = registerBlockWithItem("pink_sugarglass_flower", properties -> new SugarglassFlowerBlock(CandyItem.CandyColor.PINK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

	public static final DeferredBlock<Block> STICKY_WHITE_SUGAR_BLOCK = registerBlockWithItem("sticky_white_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_LIGHT_GRAY_SUGAR_BLOCK = registerBlockWithItem("sticky_light_gray_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_GRAY_SUGAR_BLOCK = registerBlockWithItem("sticky_gray_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_BLACK_SUGAR_BLOCK = registerBlockWithItem("sticky_black_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_BROWN_SUGAR_BLOCK = registerBlockWithItem("sticky_brown_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_RED_SUGAR_BLOCK = registerBlockWithItem("sticky_red_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_ORANGE_SUGAR_BLOCK = registerBlockWithItem("sticky_orange_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_YELLOW_SUGAR_BLOCK = registerBlockWithItem("sticky_yellow_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_LIME_SUGAR_BLOCK = registerBlockWithItem("sticky_lime_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_GREEN_SUGAR_BLOCK = registerBlockWithItem("sticky_green_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_CYAN_SUGAR_BLOCK = registerBlockWithItem("sticky_cyan_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_LIGHT_BLUE_SUGAR_BLOCK = registerBlockWithItem("sticky_light_blue_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_BLUE_SUGAR_BLOCK = registerBlockWithItem("sticky_blue_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_PURPLE_SUGAR_BLOCK = registerBlockWithItem("sticky_purple_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_MAGENTA_SUGAR_BLOCK = registerBlockWithItem("sticky_magenta_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));
	public static final DeferredBlock<Block> STICKY_PINK_SUGAR_BLOCK = registerBlockWithItem("sticky_pink_sugar_block", StickySugarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD));

	public static final DeferredBlock<Block> WHITE_SUGAR_BLOCK = registerBlockWithItem("white_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_WHITE_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> LIGHT_GRAY_SUGAR_BLOCK = registerBlockWithItem("light_gray_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_LIGHT_GRAY_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> GRAY_SUGAR_BLOCK = registerBlockWithItem("gray_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_GRAY_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> BLACK_SUGAR_BLOCK = registerBlockWithItem("black_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_BLACK_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> BROWN_SUGAR_BLOCK = registerBlockWithItem("brown_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_BROWN_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> RED_SUGAR_BLOCK = registerBlockWithItem("red_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_RED_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> ORANGE_SUGAR_BLOCK = registerBlockWithItem("orange_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_ORANGE_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> YELLOW_SUGAR_BLOCK = registerBlockWithItem("yellow_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_YELLOW_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> LIME_SUGAR_BLOCK = registerBlockWithItem("lime_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_LIME_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> GREEN_SUGAR_BLOCK = registerBlockWithItem("green_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_GREEN_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> CYAN_SUGAR_BLOCK = registerBlockWithItem("cyan_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_CYAN_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> LIGHT_BLUE_SUGAR_BLOCK = registerBlockWithItem("light_blue_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_LIGHT_BLUE_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> BLUE_SUGAR_BLOCK = registerBlockWithItem("blue_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_BLUE_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> PURPLE_SUGAR_BLOCK = registerBlockWithItem("purple_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_PURPLE_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> MAGENTA_SUGAR_BLOCK = registerBlockWithItem("magenta_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_MAGENTA_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> PINK_SUGAR_BLOCK = registerBlockWithItem("pink_sugar_block", properties -> new SugarBlock(new ColorRGBA(16317178), STICKY_PINK_SUGAR_BLOCK.get(), properties), () -> BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND));

	public static final DeferredBlock<Block> WHITE_ICING_BLOCK = registerBlockWithItem("white_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> LIGHT_GRAY_ICING_BLOCK = registerBlockWithItem("light_gray_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> GRAY_ICING_BLOCK = registerBlockWithItem("gray_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> BLACK_ICING_BLOCK = registerBlockWithItem("black_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> BROWN_ICING_BLOCK = registerBlockWithItem("brown_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> RED_ICING_BLOCK = registerBlockWithItem("red_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> ORANGE_ICING_BLOCK = registerBlockWithItem("orange_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> YELLOW_ICING_BLOCK = registerBlockWithItem("yellow_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> LIME_ICING_BLOCK = registerBlockWithItem("lime_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> GREEN_ICING_BLOCK = registerBlockWithItem("green_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> CYAN_ICING_BLOCK = registerBlockWithItem("cyan_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> LIGHT_BLUE_ICING_BLOCK = registerBlockWithItem("light_blue_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> BLUE_ICING_BLOCK = registerBlockWithItem("blue_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> PURPLE_ICING_BLOCK = registerBlockWithItem("purple_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> MAGENTA_ICING_BLOCK = registerBlockWithItem("magenta_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));
	public static final DeferredBlock<Block> PINK_ICING_BLOCK = registerBlockWithItem("pink_icing_block", Block::new, () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE));

	public static final DeferredBlock<Block> WHITE_CANDY_BLOCK = registerBlockWithItem("white_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> LIGHT_GRAY_CANDY_BLOCK = registerBlockWithItem("light_gray_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> GRAY_CANDY_BLOCK = registerBlockWithItem("gray_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> BLACK_CANDY_BLOCK = registerBlockWithItem("black_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> BROWN_CANDY_BLOCK = registerBlockWithItem("brown_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> RED_CANDY_BLOCK = registerBlockWithItem("red_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> ORANGE_CANDY_BLOCK = registerBlockWithItem("orange_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> YELLOW_CANDY_BLOCK = registerBlockWithItem("yellow_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> LIME_CANDY_BLOCK = registerBlockWithItem("lime_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> GREEN_CANDY_BLOCK = registerBlockWithItem("green_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> CYAN_CANDY_BLOCK = registerBlockWithItem("cyan_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> LIGHT_BLUE_CANDY_BLOCK = registerBlockWithItem("light_blue_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> BLUE_CANDY_BLOCK = registerBlockWithItem("blue_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> PURPLE_CANDY_BLOCK = registerBlockWithItem("purple_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> MAGENTA_CANDY_BLOCK = registerBlockWithItem("magenta_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));
	public static final DeferredBlock<Block> PINK_CANDY_BLOCK = registerBlockWithItem("pink_candy_block", Block::new, () -> BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK));

	public static final DeferredBlock<Block> RAW_GINGERBREAD_BLOCK = registerBlockWithItem("raw_gingerbread_block", GlazedBlock::new, () -> BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.TUFF));
	public static final DeferredBlock<StairBlock> RAW_GINGERBREAD_STAIRS = registerBlockWithItem("raw_gingerbread_stairs", properties -> new StairBlock(RAW_GINGERBREAD_BLOCK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(RAW_GINGERBREAD_BLOCK.get()));
	public static final DeferredBlock<SlabBlock> RAW_GINGERBREAD_SLAB = registerBlockWithItem("raw_gingerbread_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(3.0F));
	public static final DeferredBlock<Block> GINGERBREAD_BLOCK = registerBlockWithItem("gingerbread_block", GlazedBlock::new, () -> BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.TUFF));
	public static final DeferredBlock<StairBlock> GINGERBREAD_STAIRS = registerBlockWithItem("gingerbread_stairs", properties -> new StairBlock(GINGERBREAD_BLOCK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(GINGERBREAD_BLOCK.get()));
	public static final DeferredBlock<SlabBlock> GINGERBREAD_SLAB = registerBlockWithItem("gingerbread_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(4.0F));
	public static final DeferredBlock<Block> RAW_GINGERBREAD_BRICKS = registerBlockWithItem("raw_gingerbread_bricks", GlazedBlock::new, () -> BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.TUFF));
	public static final DeferredBlock<StairBlock> RAW_GINGERBREAD_BRICK_STAIRS = registerBlockWithItem("raw_gingerbread_brick_stairs", properties -> new StairBlock(RAW_GINGERBREAD_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(RAW_GINGERBREAD_BRICKS.get()));
	public static final DeferredBlock<SlabBlock> RAW_GINGERBREAD_BRICK_SLAB = registerBlockWithItem("raw_gingerbread_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(3.0F));
	public static final DeferredBlock<Block> GINGERBREAD_BRICKS = registerBlockWithItem("gingerbread_bricks", GlazedBlock::new, () -> BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.TUFF));
	public static final DeferredBlock<StairBlock> GINGERBREAD_BRICK_STAIRS = registerBlockWithItem("gingerbread_brick_stairs", properties -> new StairBlock(GINGERBREAD_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(GINGERBREAD_BRICKS.get()));
	public static final DeferredBlock<SlabBlock> GINGERBREAD_BRICK_SLAB = registerBlockWithItem("gingerbread_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(4.0F));
	public static final DeferredBlock<Block> RAW_LARGE_GINGERBREAD_BRICKS = registerBlockWithItem("raw_large_gingerbread_bricks", GlazedBlock::new, () -> BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.TUFF));
	public static final DeferredBlock<StairBlock> RAW_LARGE_GINGERBREAD_BRICK_STAIRS = registerBlockWithItem("raw_large_gingerbread_brick_stairs", properties -> new StairBlock(RAW_LARGE_GINGERBREAD_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(RAW_LARGE_GINGERBREAD_BRICKS.get()));
	public static final DeferredBlock<SlabBlock> RAW_LARGE_GINGERBREAD_BRICK_SLAB = registerBlockWithItem("raw_large_gingerbread_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(3.0F));
	public static final DeferredBlock<Block> LARGE_GINGERBREAD_BRICKS = registerBlockWithItem("large_gingerbread_bricks", GlazedBlock::new, () -> BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.TUFF));
	public static final DeferredBlock<StairBlock> LARGE_GINGERBREAD_BRICK_STAIRS = registerBlockWithItem("large_gingerbread_brick_stairs", properties -> new StairBlock(LARGE_GINGERBREAD_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(LARGE_GINGERBREAD_BRICKS.get()));
	public static final DeferredBlock<SlabBlock> LARGE_GINGERBREAD_BRICK_SLAB = registerBlockWithItem("large_gingerbread_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(4.0F));
	public static final DeferredBlock<WallBlock> RAW_GINGERBREAD_WALL = registerBlockWithItem("raw_gingerbread_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(RAW_GINGERBREAD_BLOCK.get()));
	public static final DeferredBlock<WallBlock> GINGERBREAD_WALL = registerBlockWithItem("gingerbread_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GINGERBREAD_BLOCK.get()));
	public static final DeferredBlock<WallBlock> RAW_GINGERBREAD_BRICK_WALL = registerBlockWithItem("raw_gingerbread_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(RAW_GINGERBREAD_BRICKS.get()));
	public static final DeferredBlock<WallBlock> GINGERBREAD_BRICK_WALL = registerBlockWithItem("gingerbread_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GINGERBREAD_BRICKS.get()));
	public static final DeferredBlock<WallBlock> RAW_LARGE_GINGERBREAD_BRICK_WALL = registerBlockWithItem("raw_large_gingerbread_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(RAW_LARGE_GINGERBREAD_BRICKS.get()));
	public static final DeferredBlock<WallBlock> LARGE_GINGERBREAD_BRICK_WALL = registerBlockWithItem("large_gingerbread_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LARGE_GINGERBREAD_BRICKS.get()));
	public static final DeferredBlock<Block> COOKIE_BLOCK = registerBlockWithItem("cookie_block", Block::new, () -> BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F));
	public static final DeferredBlock<StairBlock> COOKIE_BLOCK_STAIRS = registerBlockWithItem("cookie_block_stairs", properties -> new StairBlock(COOKIE_BLOCK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(COOKIE_BLOCK.get()));
	public static final DeferredBlock<SlabBlock> COOKIE_BLOCK_SLAB = registerBlockWithItem("cookie_block_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(2.0F));
	public static final DeferredBlock<ButtonBlock> COOKIE_BLOCK_BUTTON = registerBlockWithItem("cookie_block_button", properties -> new ButtonBlock(COOKIE_BLOCK_SET, 20, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<PressurePlateBlock> COOKIE_BLOCK_PRESSURE_PLATE = registerBlockWithItem("cookie_block_pressure_plate", properties -> new PressurePlateBlock(COOKIE_BLOCK_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<EatableBlock> MARSHMALLOW_BLOCK = registerBlockWithItem("marshmallow_block", EatableBlock::new, () -> BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.WOOL));

	//Plants
	public static final DeferredBlock<Block> FANTASIA_GRASS = registerBlockWithItem("fantasia_grass", properties -> new net.minecraft.world.level.block.TallGrassBlock(properties) {
		@Override
		public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
			DoublePlantBlock doubleplantblock = KoratioBlocks.TALL_FANTASIA_GRASS.get();
			if (doubleplantblock.defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
				DoublePlantBlock.placeAt(level, doubleplantblock.defaultBlockState(), pos, 2);
			}
		}
	}, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
	public static final DeferredBlock<DoublePlantBlock> TALL_FANTASIA_GRASS = registerBlockWithItem("tall_fantasia_grass", DoublePlantBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
	public static final DeferredBlock<Block> RAINBOW_ROSE = registerBlockWithItem("rainbow_rose", properties -> new FlowerBlock(MobEffects.DIG_SPEED, 8, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WITHER_ROSE));
	public static final DeferredBlock<Block> RAINBOW_ALLIUM = registerBlockWithItem("rainbow_allium", properties -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 8, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM));
	public static final DeferredBlock<Block> RAINBOW_LILY_OF_THE_VALLEY = registerBlockWithItem("rainbow_lily_of_the_valley", properties -> new FlowerBlock(MobEffects.LUCK, 8, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_OF_THE_VALLEY));
	public static final DeferredBlock<Block> GOLDEN_TULIP = registerBlockWithItem("golden_tulip", properties -> new FlowerBlock(MobEffects.GLOWING, 8, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP));
	public static final DeferredBlock<Block> GOLD_ROSE_BUSH = registerBlockWithItem("gold_rose_bush", DoublePlantBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
	public static final DeferredBlock<Block> PURPLE_MUSHROOM = registerBlockWithItem("purple_mushroom", properties -> new MushroomBlock(KoratioConfiguredFeatures.HUGE_PURPLE_MUSHROOM, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(KoratioBlocks::always));
	public static final DeferredBlock<Block> PURPLE_MUSHROOM_BLOCK = registerBlockWithItem("purple_mushroom_block", HugeMushroomBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.2F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> GREEN_MUSHROOM = registerBlockWithItem("green_mushroom", properties -> new MushroomBlock(KoratioConfiguredFeatures.HUGE_GREEN_MUSHROOM, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(KoratioBlocks::always));
	public static final DeferredBlock<Block> GREEN_MUSHROOM_BLOCK = registerBlockWithItem("green_mushroom_block", HugeMushroomBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.2F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> GILDED_VINES = registerBlockWithItem("gilded_vines", VineBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.VINE));

	public static final DeferredBlock<Block> CEINANAS = registerBlock("ceinanas", properties -> new FlippedCropBlock(properties) {
		@Override
		protected ItemLike getBaseSeedId() {
			return KoratioItems.CEINANA.get();
		}
	}, () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<Block> UPNIPS = registerBlock("upnips", properties -> new FlippedCropBlock(properties) {
		@Override
		protected ItemLike getBaseSeedId() {
			return KoratioItems.UPNIP.get();
		}
	}, () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<Block> ICE_ROSE = registerBlockWithItem("ice_rose", properties -> new FlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 8, properties) {
		@Override
		protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
			return state.is(BlockTags.SNOW);
		}
	}, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WITHER_ROSE));

	//Environment
	public static final DeferredBlock<Block> MAGIC_PATH = registerBlockWithItem("magic_path", DirtPathBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH));

	public static final DeferredBlock<Block> ANCIENT_COBBLESTONE = registerBlockWithItem("ancient_cobblestone", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));
	public static final DeferredBlock<StairBlock> ANCIENT_COBBLESTONE_STAIRS = registerBlockWithItem("ancient_cobblestone_stairs", properties -> new StairBlock(ANCIENT_COBBLESTONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS));
	public static final DeferredBlock<SlabBlock> ANCIENT_COBBLESTONE_SLAB = registerBlockWithItem("ancient_cobblestone_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB));
	public static final DeferredBlock<WallBlock> ANCIENT_COBBLESTONE_WALL = registerBlockWithItem("ancient_cobblestone_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL));
	public static final DeferredBlock<Block> ANCIENT_STONE = registerBlockWithItem("ancient_stone", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
	public static final DeferredBlock<StairBlock> ANCIENT_STONE_STAIRS = registerBlockWithItem("ancient_stone_stairs", properties -> new StairBlock(ANCIENT_STONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
	public static final DeferredBlock<SlabBlock> ANCIENT_STONE_SLAB = registerBlockWithItem("ancient_stone_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
	public static final DeferredBlock<Block> ANCIENT_STONE_BRICKS = registerBlockWithItem("ancient_stone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));
	public static final DeferredBlock<StairBlock> ANCIENT_STONE_BRICK_STAIRS = registerBlockWithItem("ancient_stone_brick_stairs", properties -> new StairBlock(ANCIENT_STONE_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
	public static final DeferredBlock<SlabBlock> ANCIENT_STONE_BRICK_SLAB = registerBlockWithItem("ancient_stone_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
	public static final DeferredBlock<Block> CRACKED_ANCIENT_STONE_BRICKS = registerBlockWithItem("cracked_ancient_stone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS));
	public static final DeferredBlock<Block> CHISELED_ANCIENT_STONE_BRICKS = registerBlockWithItem("chiseled_ancient_stone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS));
	public static final DeferredBlock<Block> POLISHED_ANCIENT_STONE = registerBlockWithItem("polished_ancient_stone", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
	public static final DeferredBlock<StairBlock> POLISHED_ANCIENT_STONE_STAIRS = registerBlockWithItem("polished_ancient_stone_stairs", properties -> new StairBlock(POLISHED_ANCIENT_STONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
	public static final DeferredBlock<SlabBlock> POLISHED_ANCIENT_STONE_SLAB = registerBlockWithItem("polished_ancient_stone_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
	public static final DeferredBlock<Block> ANCIENT_STONE_TILES = registerBlockWithItem("ancient_stone_tiles", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
	public static final DeferredBlock<StairBlock> ANCIENT_STONE_TILE_STAIRS = registerBlockWithItem("ancient_stone_tile_stairs", properties -> new StairBlock(ANCIENT_STONE_TILES.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
	public static final DeferredBlock<SlabBlock> ANCIENT_STONE_TILE_SLAB = registerBlockWithItem("ancient_stone_tile_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
	public static final DeferredBlock<WallBlock> ANCIENT_STONE_BRICK_WALL = registerBlockWithItem("ancient_stone_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
	public static final DeferredBlock<WallBlock> POLISHED_ANCIENT_STONE_WALL = registerBlockWithItem("polished_ancient_stone_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_WALL));
	public static final DeferredBlock<WallBlock> ANCIENT_STONE_TILE_WALL = registerBlockWithItem("ancient_stone_tile_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILE_WALL));

	public static final DeferredBlock<Block> ANCIENT_FURNACE = registerBlockWithItem("ancient_furnace", FurnaceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE));
	public static final DeferredBlock<AncientDoorBlock> ANCIENT_DOOR_BLOCK = registerBlockWithItem("ancient_door", AncientDoorBlock::new, () -> BlockBehaviour.Properties.of().strength(1.0F));
	public static final DeferredBlock<AncientTeleporterBlock> ANCIENT_TELEPORTER = registerBlockWithItem("ancient_teleporter", AncientTeleporterBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(CHISELED_ANCIENT_STONE_BRICKS.get()));

	//Vanilla Variants
	public static final DeferredBlock<LeafPaneBlock> OAK_LEAF_PANE = registerBlockWithItem("oak_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> SPRUCE_LEAF_PANE = registerBlockWithItem("spruce_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> BIRCH_LEAF_PANE = registerBlockWithItem("birch_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> JUNGLE_LEAF_PANE = registerBlockWithItem("jungle_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> ACACIA_LEAF_PANE = registerBlockWithItem("acacia_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> DARK_OAK_LEAF_PANE = registerBlockWithItem("dark_oak_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> MANGROVE_LEAF_PANE = registerBlockWithItem("mangrove_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> AZALEA_LEAF_PANE = registerBlockWithItem("azalea_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> FLOWERING_AZALEA_LEAF_PANE = registerBlockWithItem("flowering_azalea_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWERING_AZALEA_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> CHERRY_LEAF_PANE = registerBlockWithItem("cherry_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES));
	public static final DeferredBlock<LeafPaneBlock> PALE_OAK_LEAF_PANE = registerBlockWithItem("pale_oak_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_LEAVES));

	public static final DeferredBlock<TallDoorBlock> TALL_OAK_DOOR = registerBlockWithItem("tall_oak_door", properties -> new TallDoorBlock(BlockSetType.OAK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_SPRUCE_DOOR = registerBlockWithItem("tall_spruce_door", properties -> new TallDoorBlock(BlockSetType.SPRUCE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_BIRCH_DOOR = registerBlockWithItem("tall_birch_door", properties -> new TallDoorBlock(BlockSetType.BIRCH, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_JUNGLE_DOOR = registerBlockWithItem("tall_jungle_door", properties -> new TallDoorBlock(BlockSetType.JUNGLE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_ACACIA_DOOR = registerBlockWithItem("tall_acacia_door", properties -> new TallDoorBlock(BlockSetType.ACACIA, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_DARK_OAK_DOOR = registerBlockWithItem("tall_dark_oak_door", properties -> new TallDoorBlock(BlockSetType.DARK_OAK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_MANGROVE_DOOR = registerBlockWithItem("tall_mangrove_door", properties -> new TallDoorBlock(BlockSetType.MANGROVE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_CHERRY_DOOR = registerBlockWithItem("tall_cherry_door", properties -> new TallDoorBlock(BlockSetType.CHERRY, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_PALE_OAK_DOOR = registerBlockWithItem("tall_pale_oak_door", properties -> new TallDoorBlock(BlockSetType.PALE_OAK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_BAMBOO_DOOR = registerBlockWithItem("tall_bamboo_door", properties -> new TallDoorBlock(BlockSetType.BAMBOO, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_CRIMSON_DOOR = registerBlockWithItem("tall_crimson_door", properties -> new TallDoorBlock(BlockSetType.CRIMSON, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_WARPED_DOOR = registerBlockWithItem("tall_warped_door", properties -> new TallDoorBlock(BlockSetType.WARPED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_DOOR));

	public static final DeferredBlock<TallDoorBlock> TALL_IRON_DOOR = registerBlockWithItem("tall_iron_door", properties -> new TallDoorBlock(BlockSetType.IRON, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_COPPER_DOOR = registerBlockWithItem("tall_copper_door", properties -> new TallWeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.UNAFFECTED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_EXPOSED_COPPER_DOOR = registerBlockWithItem("tall_exposed_copper_door", properties -> new TallWeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.EXPOSED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.EXPOSED_COPPER_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_OXIDIZED_COPPER_DOOR = registerBlockWithItem("tall_oxidized_copper_door", properties -> new TallWeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.OXIDIZED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OXIDIZED_COPPER_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_WEATHERED_COPPER_DOOR = registerBlockWithItem("tall_weathered_copper_door", properties -> new TallWeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.WEATHERED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WEATHERED_COPPER_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_WAXED_COPPER_DOOR = registerBlockWithItem("tall_waxed_copper_door", properties -> new TallDoorBlock(BlockSetType.COPPER, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_COPPER_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_WAXED_EXPOSED_COPPER_DOOR = registerBlockWithItem("tall_waxed_exposed_copper_door", properties -> new TallDoorBlock(BlockSetType.COPPER, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_COPPER_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_WAXED_OXIDIZED_COPPER_DOOR = registerBlockWithItem("tall_waxed_oxidized_copper_door", properties -> new TallDoorBlock(BlockSetType.COPPER, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_COPPER_DOOR));
	public static final DeferredBlock<TallDoorBlock> TALL_WAXED_WEATHERED_COPPER_DOOR = registerBlockWithItem("tall_waxed_weathered_copper_door", properties -> new TallDoorBlock(BlockSetType.COPPER, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_COPPER_DOOR));

	//Pango Wood
	public static final DeferredBlock<Block> PANGO_PLANKS = registerBlockWithItem("pango_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> PANGO_LOG = registerBlockWithItem("pango_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PANGO_LOG = registerBlockWithItem("stripped_pango_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PANGO_WOOD = registerBlockWithItem("stripped_pango_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> PANGO_WOOD = registerBlockWithItem("pango_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> PANGO_LEAVES = registerBlockWithItem("pango_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<LeafPaneBlock> PANGO_LEAF_PANE = registerBlockWithItem("pango_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(PANGO_LEAVES.get()));
	public static final DeferredBlock<SlabBlock> PANGO_SLAB = registerBlockWithItem("pango_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StairBlock> PANGO_STAIRS = registerBlockWithItem("pango_stairs", properties -> new StairBlock(PANGO_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(PANGO_PLANKS.get()));
	public static final DeferredBlock<Block> PANGO_SAPLING = registerBlockWithItem("pango_sapling", properties -> new SaplingBlock(KoratioTreeGrower.PANGO, properties), () -> BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static final DeferredBlock<FenceBlock> PANGO_FENCE = registerBlockWithItem("pango_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> PANGO_SIGN = registerBlock("pango_sign", properties -> new StandingSignBlock(PANGO_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<WallSignBlock> PANGO_WALL_SIGN = registerBlock("pango_wall_sign", properties -> new WallSignBlock(PANGO_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(PANGO_SIGN.get().getLootTable()));
	public static final DeferredBlock<CeilingHangingSignBlock> PANGO_HANGING_SIGN = registerBlock("pango_hanging_sign", properties -> new CeilingHangingSignBlock(PANGO_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(PANGO_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<WallHangingSignBlock> PANGO_WALL_HANGING_SIGN = registerBlock("pango_wall_hanging_sign", properties -> new WallHangingSignBlock(PANGO_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(PANGO_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(PANGO_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<ButtonBlock> PANGO_BUTTON = registerBlockWithItem("pango_button", properties -> new ButtonBlock(PANGO_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> PANGO_PRESSURE_PLATE = registerBlockWithItem("pango_pressure_plate", properties -> new PressurePlateBlock(PANGO_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<DoorBlock> PANGO_DOOR = registerBlockWithItem("pango_door", properties -> new DoorBlock(PANGO_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	public static final DeferredBlock<TallDoorBlock> TALL_PANGO_DOOR = registerBlockWithItem("tall_pango_door", properties -> new TallDoorBlock(PANGO_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(PANGO_DOOR.get()));
	public static final DeferredBlock<TrapDoorBlock> PANGO_TRAPDOOR = registerBlockWithItem("pango_trapdoor", properties -> new TrapDoorBlock(PANGO_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<FenceGateBlock> PANGO_FENCE_GATE = registerBlockWithItem("pango_fence_gate", properties -> new FenceGateBlock(PANGO_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> PANGO_BOOKSHELF = registerBlockWithItem("pango_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_PURPLE));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> PANGO_CHEST = registerBlockWithItem("pango_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_PANGO_CHEST = registerBlockWithItem("trapped_pango_chest", TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST));

	//Rugona Wood
	public static final DeferredBlock<Block> RUGONA_PLANKS = registerBlockWithItem("rugona_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> RUGONA_LOG = registerBlockWithItem("rugona_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_GREEN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_RUGONA_LOG = registerBlockWithItem("stripped_rugona_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_CYAN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_RUGONA_WOOD = registerBlockWithItem("stripped_rugona_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> RUGONA_WOOD = registerBlockWithItem("rugona_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> RUGONA_LEAVES = registerBlockWithItem("rugona_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<LeafPaneBlock> RUGONA_LEAF_PANE = registerBlockWithItem("rugona_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(RUGONA_LEAVES.get()));
	public static final DeferredBlock<SlabBlock> RUGONA_SLAB = registerBlockWithItem("rugona_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StairBlock> RUGONA_STAIRS = registerBlockWithItem("rugona_stairs", properties -> new StairBlock(RUGONA_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(RUGONA_PLANKS.get()));
	public static final DeferredBlock<Block> RUGONA_SAPLING = registerBlockWithItem("rugona_sapling", properties -> new SaplingBlock(KoratioTreeGrower.RUGONA, properties), () -> BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static final DeferredBlock<FenceBlock> RUGONA_FENCE = registerBlockWithItem("rugona_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> RUGONA_SIGN = registerBlock("rugona_sign", properties -> new StandingSignBlock(RUGONA_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<WallSignBlock> RUGONA_WALL_SIGN = registerBlock("rugona_wall_sign", properties -> new WallSignBlock(RUGONA_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(RUGONA_SIGN.get().getLootTable()));
	public static final DeferredBlock<CeilingHangingSignBlock> RUGONA_HANGING_SIGN = registerBlock("rugona_hanging_sign", properties -> new CeilingHangingSignBlock(RUGONA_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(RUGONA_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<WallHangingSignBlock> RUGONA_WALL_HANGING_SIGN = registerBlock("rugona_wall_hanging_sign", properties -> new WallHangingSignBlock(RUGONA_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(RUGONA_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(RUGONA_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<ButtonBlock> RUGONA_BUTTON = registerBlockWithItem("rugona_button", properties -> new ButtonBlock(RUGONA_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> RUGONA_PRESSURE_PLATE = registerBlockWithItem("rugona_pressure_plate", properties -> new PressurePlateBlock(RUGONA_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<DoorBlock> RUGONA_DOOR = registerBlockWithItem("rugona_door", properties -> new DoorBlock(RUGONA_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	public static final DeferredBlock<TallDoorBlock> TALL_RUGONA_DOOR = registerBlockWithItem("tall_rugona_door", properties -> new TallDoorBlock(RUGONA_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(RUGONA_DOOR.get()));
	public static final DeferredBlock<TrapDoorBlock> RUGONA_TRAPDOOR = registerBlockWithItem("rugona_trapdoor", properties -> new TrapDoorBlock(RUGONA_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<FenceGateBlock> RUGONA_FENCE_GATE = registerBlockWithItem("rugona_fence_gate", properties -> new FenceGateBlock(RUGONA_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> RUGONA_BOOKSHELF = registerBlockWithItem("rugona_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_CYAN));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> RUGONA_CHEST = registerBlockWithItem("rugona_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_RUGONA_CHEST = registerBlockWithItem("trapped_rugona_chest", TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST));

	//Vareso Wood
	public static final DeferredBlock<Block> VARESO_PLANKS = registerBlockWithItem("vareso_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> VARESO_LOG = registerBlockWithItem("vareso_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_BLACK, MapColor.COLOR_LIGHT_GRAY));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_VARESO_LOG = registerBlockWithItem("stripped_vareso_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_LIGHT_GRAY, MapColor.COLOR_LIGHT_GRAY));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_VARESO_WOOD = registerBlockWithItem("stripped_vareso_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> VARESO_WOOD = registerBlockWithItem("vareso_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> VARESO_LEAVES = registerBlockWithItem("vareso_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<LeafPaneBlock> VARESO_LEAF_PANE = registerBlockWithItem("vareso_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(VARESO_LEAVES.get()));
	public static final DeferredBlock<SlabBlock> VARESO_SLAB = registerBlockWithItem("vareso_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StairBlock> VARESO_STAIRS = registerBlockWithItem("vareso_stairs", properties -> new StairBlock(VARESO_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(VARESO_PLANKS.get()));
	public static final DeferredBlock<Block> VARESO_SAPLING = registerBlockWithItem("vareso_sapling", properties -> new SaplingBlock(KoratioTreeGrower.VARESO, properties), () -> BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static final DeferredBlock<FenceBlock> VARESO_FENCE = registerBlockWithItem("vareso_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> VARESO_SIGN = registerBlock("vareso_sign", properties -> new StandingSignBlock(VARESO_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<WallSignBlock> VARESO_WALL_SIGN = registerBlock("vareso_wall_sign", properties -> new WallSignBlock(VARESO_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(VARESO_SIGN.get().getLootTable()));
	public static final DeferredBlock<CeilingHangingSignBlock> VARESO_HANGING_SIGN = registerBlock("vareso_hanging_sign", properties -> new CeilingHangingSignBlock(VARESO_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(VARESO_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<WallHangingSignBlock> VARESO_WALL_HANGING_SIGN = registerBlock("vareso_wall_hanging_sign", properties -> new WallHangingSignBlock(VARESO_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(VARESO_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(VARESO_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<ButtonBlock> VARESO_BUTTON = registerBlockWithItem("vareso_button", properties -> new ButtonBlock(VARESO_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> VARESO_PRESSURE_PLATE = registerBlockWithItem("vareso_pressure_plate", properties -> new PressurePlateBlock(VARESO_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<DoorBlock> VARESO_DOOR = registerBlockWithItem("vareso_door", properties -> new DoorBlock(VARESO_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	public static final DeferredBlock<TallDoorBlock> TALL_VARESO_DOOR = registerBlockWithItem("tall_vareso_door", properties -> new TallDoorBlock(VARESO_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(VARESO_DOOR.get()));
	public static final DeferredBlock<TrapDoorBlock> VARESO_TRAPDOOR = registerBlockWithItem("vareso_trapdoor", properties -> new TrapDoorBlock(VARESO_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<FenceGateBlock> VARESO_FENCE_GATE = registerBlockWithItem("vareso_fence_gate", properties -> new FenceGateBlock(VARESO_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> VARESO_BOOKSHELF = registerBlockWithItem("vareso_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_LIGHT_GRAY));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> VARESO_CHEST = registerBlockWithItem("vareso_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_VARESO_CHEST = registerBlockWithItem("trapped_vareso_chest", TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST));

	//Candy Wood
	public static final DeferredBlock<Block> CANDY_PLANKS = registerBlockWithItem("candy_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> CANDY_LOG = registerBlockWithItem("candy_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_LIGHT_GRAY, MapColor.COLOR_PINK));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CANDY_LOG = registerBlockWithItem("stripped_candy_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_PINK, MapColor.COLOR_PINK));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CANDY_WOOD = registerBlockWithItem("stripped_candy_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> CANDY_WOOD = registerBlockWithItem("candy_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> PINK_COTTON_CANDY_LEAVES = registerBlockWithItem("pink_cotton_candy_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<Block> LIGHT_BLUE_COTTON_CANDY_LEAVES = registerBlockWithItem("light_blue_cotton_candy_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<Block> LIME_COTTON_CANDY_LEAVES = registerBlockWithItem("lime_cotton_candy_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<Block> YELLOW_COTTON_CANDY_LEAVES = registerBlockWithItem("yellow_cotton_candy_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<LeafPaneBlock> PINK_COTTON_CANDY_LEAF_PANE = registerBlockWithItem("pink_cotton_candy_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(PINK_COTTON_CANDY_LEAVES.get()));
	public static final DeferredBlock<LeafPaneBlock> LIGHT_BLUE_COTTON_CANDY_LEAF_PANE = registerBlockWithItem("light_blue_cotton_candy_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LIGHT_BLUE_COTTON_CANDY_LEAVES.get()));
	public static final DeferredBlock<LeafPaneBlock> LIME_COTTON_CANDY_LEAF_PANE = registerBlockWithItem("lime_cotton_candy_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LIME_COTTON_CANDY_LEAVES.get()));
	public static final DeferredBlock<LeafPaneBlock> YELLOW_COTTON_CANDY_LEAF_PANE = registerBlockWithItem("yellow_cotton_candy_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(YELLOW_COTTON_CANDY_LEAVES.get()));
	public static final DeferredBlock<SlabBlock> CANDY_SLAB = registerBlockWithItem("candy_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StairBlock> CANDY_STAIRS = registerBlockWithItem("candy_stairs", properties -> new StairBlock(CANDY_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(CANDY_PLANKS.get()));
	public static final DeferredBlock<Block> CANDY_SAPLING = registerBlockWithItem("candy_sapling", properties -> new SaplingBlock(KoratioTreeGrower.CANDY, properties), () -> BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static final DeferredBlock<FenceBlock> CANDY_FENCE = registerBlockWithItem("candy_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(CANDY_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> CANDY_SIGN = registerBlock("candy_sign", properties -> new StandingSignBlock(CANDY_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<WallSignBlock> CANDY_WALL_SIGN = registerBlock("candy_wall_sign", properties -> new WallSignBlock(CANDY_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(CANDY_SIGN.get().getLootTable()));
	public static final DeferredBlock<CeilingHangingSignBlock> CANDY_HANGING_SIGN = registerBlock("candy_hanging_sign", properties -> new CeilingHangingSignBlock(CANDY_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(CANDY_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<WallHangingSignBlock> CANDY_WALL_HANGING_SIGN = registerBlock("candy_wall_hanging_sign", properties -> new WallHangingSignBlock(CANDY_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(CANDY_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(CANDY_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<ButtonBlock> CANDY_BUTTON = registerBlockWithItem("candy_button", properties -> new ButtonBlock(CANDY_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> CANDY_PRESSURE_PLATE = registerBlockWithItem("candy_pressure_plate", properties -> new PressurePlateBlock(CANDY_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(CANDY_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<DoorBlock> CANDY_DOOR = registerBlockWithItem("candy_door", properties -> new DoorBlock(CANDY_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(CANDY_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	//public static final DeferredBlock<TallDoorBlock> TALL_CANDY_DOOR = registerBlockWithItem("tall_candy_door", properties -> new TallDoorBlock(CANDY_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(CANDY_DOOR.get()));
	public static final DeferredBlock<TrapDoorBlock> CANDY_TRAPDOOR = registerBlockWithItem("candy_trapdoor", properties -> new TrapDoorBlock(CANDY_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<FenceGateBlock> CANDY_FENCE_GATE = registerBlockWithItem("candy_fence_gate", properties -> new FenceGateBlock(CANDY_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(CANDY_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> CANDY_BOOKSHELF = registerBlockWithItem("candy_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_PINK));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> CANDY_CHEST = registerBlockWithItem("candy_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_CANDY_CHEST = registerBlockWithItem("trapped_candy_chest", TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST));

	//Pango Wood
	public static final DeferredBlock<Block> CHOCOLATE_OAK_PLANKS = registerBlockWithItem("chocolate_oak_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> CHOCOLATE_OAK_LOG = registerBlockWithItem("chocolate_oak_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHOCOLATE_OAK_LOG = registerBlockWithItem("stripped_chocolate_oak_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHOCOLATE_OAK_WOOD = registerBlockWithItem("stripped_chocolate_oak_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> CHOCOLATE_OAK_WOOD = registerBlockWithItem("chocolate_oak_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> CHOCOLATE_OAK_LEAVES = registerBlockWithItem("chocolate_oak_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<LeafPaneBlock> CHOCOLATE_OAK_LEAF_PANE = registerBlockWithItem("chocolate_oak_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(CHOCOLATE_OAK_LEAVES.get()));
	public static final DeferredBlock<SlabBlock> CHOCOLATE_OAK_SLAB = registerBlockWithItem("chocolate_oak_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StairBlock> CHOCOLATE_OAK_STAIRS = registerBlockWithItem("chocolate_oak_stairs", properties -> new StairBlock(CHOCOLATE_OAK_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(CHOCOLATE_OAK_PLANKS.get()));
	public static final DeferredBlock<Block> CHOCOLATE_OAK_SAPLING = registerBlockWithItem("chocolate_oak_sapling", properties -> new SaplingBlock(KoratioTreeGrower.CHOCOLATE_OAK, properties), () -> BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static final DeferredBlock<FenceBlock> CHOCOLATE_OAK_FENCE = registerBlockWithItem("chocolate_oak_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> CHOCOLATE_OAK_SIGN = registerBlock("chocolate_oak_sign", properties -> new StandingSignBlock(CHOCOLATE_OAK_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<WallSignBlock> CHOCOLATE_OAK_WALL_SIGN = registerBlock("chocolate_oak_wall_sign", properties -> new WallSignBlock(CHOCOLATE_OAK_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(CHOCOLATE_OAK_SIGN.get().getLootTable()));
	public static final DeferredBlock<CeilingHangingSignBlock> CHOCOLATE_OAK_HANGING_SIGN = registerBlock("chocolate_oak_hanging_sign", properties -> new CeilingHangingSignBlock(CHOCOLATE_OAK_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<WallHangingSignBlock> CHOCOLATE_OAK_WALL_HANGING_SIGN = registerBlock("chocolate_oak_wall_hanging_sign", properties -> new WallHangingSignBlock(CHOCOLATE_OAK_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(CHOCOLATE_OAK_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<ButtonBlock> CHOCOLATE_OAK_BUTTON = registerBlockWithItem("chocolate_oak_button", properties -> new ButtonBlock(CHOCOLATE_OAK_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> CHOCOLATE_OAK_PRESSURE_PLATE = registerBlockWithItem("chocolate_oak_pressure_plate", properties -> new PressurePlateBlock(CHOCOLATE_OAK_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<DoorBlock> CHOCOLATE_OAK_DOOR = registerBlockWithItem("chocolate_oak_door", properties -> new DoorBlock(CHOCOLATE_OAK_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	//public static final DeferredBlock<TallDoorBlock> TALL_CHOCOLATE_OAK_DOOR = registerBlockWithItem("tall_chocolate_oak_door", properties -> new TallDoorBlock(CHOCOLATE_OAK_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(CHOCOLATE_OAK_DOOR.get()));
	public static final DeferredBlock<TrapDoorBlock> CHOCOLATE_OAK_TRAPDOOR = registerBlockWithItem("chocolate_oak_trapdoor", properties -> new TrapDoorBlock(CHOCOLATE_OAK_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<FenceGateBlock> CHOCOLATE_OAK_FENCE_GATE = registerBlockWithItem("chocolate_oak_fence_gate", properties -> new FenceGateBlock(CHOCOLATE_OAK_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> CHOCOLATE_OAK_BOOKSHELF = registerBlockWithItem("chocolate_oak_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_BROWN));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> CHOCOLATE_OAK_CHEST = registerBlockWithItem("chocolate_oak_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_CHOCOLATE_OAK_CHEST = registerBlockWithItem("trapped_chocolate_oak_chest", TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST));

	//Elven Wood
	public static final DeferredBlock<Block> ELVEN_PLANKS = registerBlockWithItem("elven_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> BLUE_ELVEN_PLANKS = registerBlockWithItem("blue_elven_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> CYAN_ELVEN_PLANKS = registerBlockWithItem("cyan_elven_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> GREEN_ELVEN_PLANKS = registerBlockWithItem("green_elven_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> ELVEN_LOG = registerBlockWithItem("elven_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_CYAN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ELVEN_LOG = registerBlockWithItem("stripped_elven_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_CYAN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLUE_ELVEN_LOG = registerBlockWithItem("stripped_blue_elven_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_BLUE, MapColor.COLOR_BLUE));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CYAN_ELVEN_LOG = registerBlockWithItem("stripped_cyan_elven_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_CYAN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GREEN_ELVEN_LOG = registerBlockWithItem("stripped_green_elven_log", RotatedPillarBlock::new, () -> log(MapColor.COLOR_GREEN, MapColor.COLOR_GREEN));
	public static final DeferredBlock<RotatedPillarBlock> ELVEN_WOOD = registerBlockWithItem("elven_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ELVEN_WOOD = registerBlockWithItem("stripped_elven_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLUE_ELVEN_WOOD = registerBlockWithItem("stripped_blue_elven_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CYAN_ELVEN_WOOD = registerBlockWithItem("stripped_cyan_elven_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GREEN_ELVEN_WOOD = registerBlockWithItem("stripped_green_elven_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> ELVEN_LEAVES = registerBlockWithItem("elven_leaves", ElvenLeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never));
	public static final DeferredBlock<LeafPaneBlock> ELVEN_LEAF_PANE = registerBlockWithItem("elven_leaf_pane", LeafPaneBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ELVEN_LEAVES.get()));
	public static final DeferredBlock<SlabBlock> ELVEN_SLAB = registerBlockWithItem("elven_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<SlabBlock> BLUE_ELVEN_SLAB = registerBlockWithItem("blue_elven_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<SlabBlock> CYAN_ELVEN_SLAB = registerBlockWithItem("cyan_elven_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<SlabBlock> GREEN_ELVEN_SLAB = registerBlockWithItem("green_elven_slab", SlabBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StairBlock> ELVEN_STAIRS = registerBlockWithItem("elven_stairs", properties -> new StairBlock(ELVEN_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(ELVEN_PLANKS.get()));
	public static final DeferredBlock<StairBlock> BLUE_ELVEN_STAIRS = registerBlockWithItem("blue_elven_stairs", properties -> new StairBlock(BLUE_ELVEN_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(BLUE_ELVEN_PLANKS.get()));
	public static final DeferredBlock<StairBlock> CYAN_ELVEN_STAIRS = registerBlockWithItem("cyan_elven_stairs", properties -> new StairBlock(CYAN_ELVEN_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(CYAN_ELVEN_PLANKS.get()));
	public static final DeferredBlock<StairBlock> GREEN_ELVEN_STAIRS = registerBlockWithItem("green_elven_stairs", properties -> new StairBlock(GREEN_ELVEN_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(GREEN_ELVEN_PLANKS.get()));
	public static final DeferredBlock<Block> ELVEN_SAPLING = registerBlockWithItem("elven_sapling", properties -> new SaplingBlock(KoratioTreeGrower.ELVEN, properties), () -> BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static final DeferredBlock<FenceBlock> ELVEN_FENCE = registerBlockWithItem("elven_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<FenceBlock> BLUE_ELVEN_FENCE = registerBlockWithItem("blue_elven_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(BLUE_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<FenceBlock> CYAN_ELVEN_FENCE = registerBlockWithItem("cyan_elven_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(CYAN_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<FenceBlock> GREEN_ELVEN_FENCE = registerBlockWithItem("green_elven_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(GREEN_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> ELVEN_SIGN = registerBlock("elven_sign", properties -> new StandingSignBlock(ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> BLUE_ELVEN_SIGN = registerBlock("blue_elven_sign", properties -> new StandingSignBlock(BLUE_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> CYAN_ELVEN_SIGN = registerBlock("cyan_elven_sign", properties -> new StandingSignBlock(CYAN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StandingSignBlock> GREEN_ELVEN_SIGN = registerBlock("green_elven_sign", properties -> new StandingSignBlock(GREEN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<WallSignBlock> ELVEN_WALL_SIGN = registerBlock("elven_wall_sign", properties -> new WallSignBlock(ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(ELVEN_SIGN.get().getLootTable()));
	public static final DeferredBlock<WallSignBlock> BLUE_ELVEN_WALL_SIGN = registerBlock("blue_elven_wall_sign", properties -> new WallSignBlock(BLUE_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(BLUE_ELVEN_SIGN.get().getLootTable()));
	public static final DeferredBlock<WallSignBlock> CYAN_ELVEN_WALL_SIGN = registerBlock("cyan_elven_wall_sign", properties -> new WallSignBlock(CYAN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(CYAN_ELVEN_SIGN.get().getLootTable()));
	public static final DeferredBlock<WallSignBlock> GREEN_ELVEN_WALL_SIGN = registerBlock("green_elven_wall_sign", properties -> new WallSignBlock(GREEN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).overrideLootTable(GREEN_ELVEN_SIGN.get().getLootTable()));
	public static final DeferredBlock<CeilingHangingSignBlock> ELVEN_HANGING_SIGN = registerBlock("elven_hanging_sign", properties -> new CeilingHangingSignBlock(ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<CeilingHangingSignBlock> BLUE_ELVEN_HANGING_SIGN = registerBlock("blue_elven_hanging_sign", properties -> new CeilingHangingSignBlock(BLUE_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<CeilingHangingSignBlock> CYAN_ELVEN_HANGING_SIGN = registerBlock("cyan_elven_hanging_sign", properties -> new CeilingHangingSignBlock(CYAN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<CeilingHangingSignBlock> GREEN_ELVEN_HANGING_SIGN = registerBlock("green_elven_hanging_sign", properties -> new CeilingHangingSignBlock(GREEN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F));
	public static final DeferredBlock<WallHangingSignBlock> ELVEN_WALL_HANGING_SIGN = registerBlock("elven_wall_hanging_sign", properties -> new WallHangingSignBlock(ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(ELVEN_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<WallHangingSignBlock> BLUE_ELVEN_WALL_HANGING_SIGN = registerBlock("blue_elven_wall_hanging_sign", properties -> new WallHangingSignBlock(BLUE_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(BLUE_ELVEN_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<WallHangingSignBlock> CYAN_ELVEN_WALL_HANGING_SIGN = registerBlock("cyan_elven_wall_hanging_sign", properties -> new WallHangingSignBlock(CYAN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(CYAN_ELVEN_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<WallHangingSignBlock> GREEN_ELVEN_WALL_HANGING_SIGN = registerBlock("green_elven_wall_hanging_sign", properties -> new WallHangingSignBlock(GREEN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F).overrideLootTable(GREEN_ELVEN_HANGING_SIGN.get().getLootTable()));
	public static final DeferredBlock<ButtonBlock> ELVEN_BUTTON = registerBlockWithItem("elven_button", properties -> new ButtonBlock(ELVEN_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<ButtonBlock> BLUE_ELVEN_BUTTON = registerBlockWithItem("blue_elven_button", properties -> new ButtonBlock(BLUE_ELVEN_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<ButtonBlock> CYAN_ELVEN_BUTTON = registerBlockWithItem("cyan_elven_button", properties -> new ButtonBlock(CYAN_ELVEN_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<ButtonBlock> GREEN_ELVEN_BUTTON = registerBlockWithItem("green_elven_button", properties -> new ButtonBlock(GREEN_ELVEN_SET, 30, properties), () -> BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> ELVEN_PRESSURE_PLATE = registerBlockWithItem("elven_pressure_plate", properties -> new PressurePlateBlock(ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> BLUE_ELVEN_PRESSURE_PLATE = registerBlockWithItem("blue_elven_pressure_plate", properties -> new PressurePlateBlock(BLUE_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(BLUE_ELVEN_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> CYAN_ELVEN_PRESSURE_PLATE = registerBlockWithItem("cyan_elven_pressure_plate", properties -> new PressurePlateBlock(CYAN_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(CYAN_ELVEN_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<PressurePlateBlock> GREEN_ELVEN_PRESSURE_PLATE = registerBlockWithItem("green_elven_pressure_plate", properties -> new PressurePlateBlock(GREEN_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(GREEN_ELVEN_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD));
	public static final DeferredBlock<DoorBlock> ELVEN_DOOR = registerBlockWithItem("elven_door", properties -> new DoorBlock(ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	public static final DeferredBlock<DoorBlock> BLUE_ELVEN_DOOR = registerBlockWithItem("blue_elven_door", properties -> new DoorBlock(BLUE_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(BLUE_ELVEN_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	public static final DeferredBlock<DoorBlock> CYAN_ELVEN_DOOR = registerBlockWithItem("cyan_elven_door", properties -> new DoorBlock(CYAN_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(CYAN_ELVEN_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	public static final DeferredBlock<DoorBlock> GREEN_ELVEN_DOOR = registerBlockWithItem("green_elven_door", properties -> new DoorBlock(GREEN_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(GREEN_ELVEN_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
	public static final DeferredBlock<TallDoorBlock> TALL_ELVEN_DOOR = registerBlockWithItem("tall_elven_door", properties -> new TallDoorBlock(ELVEN_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(ELVEN_DOOR.get()));
	public static final DeferredBlock<TallDoorBlock> TALL_BLUE_ELVEN_DOOR = registerBlockWithItem("tall_blue_elven_door", properties -> new TallDoorBlock(BLUE_ELVEN_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(BLUE_ELVEN_DOOR.get()));
	public static final DeferredBlock<TallDoorBlock> TALL_CYAN_ELVEN_DOOR = registerBlockWithItem("tall_cyan_elven_door", properties -> new TallDoorBlock(CYAN_ELVEN_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(CYAN_ELVEN_DOOR.get()));
	public static final DeferredBlock<TallDoorBlock> TALL_GREEN_ELVEN_DOOR = registerBlockWithItem("tall_green_elven_door", properties -> new TallDoorBlock(GREEN_ELVEN_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(GREEN_ELVEN_DOOR.get()));
	public static final DeferredBlock<TrapDoorBlock> ELVEN_TRAPDOOR = registerBlockWithItem("elven_trapdoor", properties -> new TrapDoorBlock(ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<TrapDoorBlock> BLUE_ELVEN_TRAPDOOR = registerBlockWithItem("blue_elven_trapdoor", properties -> new TrapDoorBlock(BLUE_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<TrapDoorBlock> CYAN_ELVEN_TRAPDOOR = registerBlockWithItem("cyan_elven_trapdoor", properties -> new TrapDoorBlock(CYAN_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<TrapDoorBlock> GREEN_ELVEN_TRAPDOOR = registerBlockWithItem("green_elven_trapdoor", properties -> new TrapDoorBlock(GREEN_ELVEN_SET, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never));
	public static final DeferredBlock<FenceGateBlock> ELVEN_FENCE_GATE = registerBlockWithItem("elven_fence_gate", properties -> new FenceGateBlock(ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<FenceGateBlock> BLUE_ELVEN_FENCE_GATE = registerBlockWithItem("blue_elven_fence_gate", properties -> new FenceGateBlock(BLUE_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(BLUE_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<FenceGateBlock> CYAN_ELVEN_FENCE_GATE = registerBlockWithItem("cyan_elven_fence_gate", properties -> new FenceGateBlock(CYAN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(CYAN_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<FenceGateBlock> GREEN_ELVEN_FENCE_GATE = registerBlockWithItem("green_elven_fence_gate", properties -> new FenceGateBlock(GREEN_ELVEN_TYPE, properties), () -> BlockBehaviour.Properties.of().mapColor(GREEN_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> ELVEN_BOOKSHELF = registerBlockWithItem("elven_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_CYAN));
	public static final DeferredBlock<Block> BLUE_ELVEN_BOOKSHELF = registerBlockWithItem("blue_elven_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_BLUE));
	public static final DeferredBlock<Block> CYAN_ELVEN_BOOKSHELF = registerBlockWithItem("cyan_elven_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_CYAN));
	public static final DeferredBlock<Block> GREEN_ELVEN_BOOKSHELF = registerBlockWithItem("green_elven_bookshelf", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_GREEN));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> ELVEN_CHEST = registerBlockWithItem("elven_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ELVEN_PLANKS.get()).strength(2.5F));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> BLUE_ELVEN_CHEST = registerBlockWithItem("blue_elven_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BLUE_ELVEN_PLANKS.get()).strength(2.5F));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> CYAN_ELVEN_CHEST = registerBlockWithItem("cyan_elven_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(CYAN_ELVEN_PLANKS.get()).strength(2.5F));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> GREEN_ELVEN_CHEST = registerBlockWithItem("green_elven_chest", ChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GREEN_ELVEN_PLANKS.get()).strength(2.5F));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_ELVEN_CHEST = registerBlockWithItem("trapped_elven_chest", TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ELVEN_PLANKS.get()).strength(2.5F));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_BLUE_ELVEN_CHEST = registerBlockWithItem("trapped_blue_elven_chest",TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BLUE_ELVEN_PLANKS.get()).strength(2.5F));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_CYAN_ELVEN_CHEST = registerBlockWithItem("trapped_cyan_elven_chest", TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(CYAN_ELVEN_PLANKS.get()).strength(2.5F));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_GREEN_ELVEN_CHEST = registerBlockWithItem("trapped_green_elven_chest", TrappedChestBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GREEN_ELVEN_PLANKS.get()).strength(2.5F));

	//Potted Plants
	public static final DeferredBlock<FlowerPotBlock> POTTED_RAINBOW_ROSE = registerBlock("potted_rainbow_rose", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_ROSE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_RAINBOW_ALLIUM = registerBlock("potted_rainbow_allium", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_ALLIUM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_RAINBOW_LILY_OF_THE_VALLEY = registerBlock("potted_rainbow_lily_of_the_valley", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_LILY_OF_THE_VALLEY, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_COOKIE_FLOWER = registerBlock("potted_cookie_flower", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, COOKIE_FLOWER, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_PURPLE_MUSHROOM = registerBlock("potted_purple_mushroom", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PURPLE_MUSHROOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GREEN_MUSHROOM = registerBlock("potted_green_mushroom", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GREEN_MUSHROOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_PANGO_SAPLING = registerBlock("potted_pango_sapling", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PANGO_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_RUGONA_SAPLING = registerBlock("potted_rugona_sapling", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RUGONA_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VARESO_SAPLING = registerBlock("potted_vareso_sapling", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VARESO_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_CANDY_SAPLING = registerBlock("potted_candy_sapling", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CANDY_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_CHOCOLATE_OAK_SAPLING = registerBlock("potted_chocolate_oak_sapling", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CHOCOLATE_OAK_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_ELVEN_SAPLING = registerBlock("potted_elven_sapling", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ELVEN_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));

	private static BlockBehaviour.Properties log(MapColor topColor, MapColor sideColor) {
		return BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : sideColor);
	}

	public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		return BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, Koratio.prefix(name)))));
	}

	public static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		DeferredBlock<T> deferredBlock = BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, Koratio.prefix(name)))));
		KoratioItems.registerItem(name, itemProps -> new BlockItem(deferredBlock.get(), itemProps), new Item.Properties());
		return deferredBlock;
	}

	private static Boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
		return false;
	}

	@SuppressWarnings("unused")
	private static Boolean always(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
		return true;
	}

	private static Boolean ocelotOrParrot(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
		return type == EntityType.OCELOT || type == EntityType.PARROT;
	}

	private static boolean always(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	private static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
		return false;
	}

	public static void registerWoodTypes() {
		WoodType.register(PANGO_TYPE);
		WoodType.register(RUGONA_TYPE);
		WoodType.register(ELVEN_TYPE);
		WoodType.register(BLUE_ELVEN_TYPE);
		WoodType.register(CYAN_ELVEN_TYPE);
		WoodType.register(GREEN_ELVEN_TYPE);
		WoodType.register(VARESO_TYPE);
		WoodType.register(CANDY_TYPE);
		WoodType.register(CHOCOLATE_OAK_TYPE);
		BlockSetType.register(PANGO_SET);
		BlockSetType.register(RUGONA_SET);
		BlockSetType.register(ELVEN_SET);
		BlockSetType.register(BLUE_ELVEN_SET);
		BlockSetType.register(CYAN_ELVEN_SET);
		BlockSetType.register(GREEN_ELVEN_SET);
		BlockSetType.register(VARESO_SET);
		BlockSetType.register(CANDY_SET);
		BlockSetType.register(CHOCOLATE_OAK_SET);
	}

	public static void registerStrippables() {
		AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
		AxeItem.STRIPPABLES.put(KoratioBlocks.PANGO_LOG.get(), KoratioBlocks.STRIPPED_PANGO_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.PANGO_WOOD.get(), KoratioBlocks.STRIPPED_PANGO_WOOD.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.RUGONA_LOG.get(), KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.RUGONA_WOOD.get(), KoratioBlocks.STRIPPED_RUGONA_WOOD.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.ELVEN_LOG.get(), KoratioBlocks.STRIPPED_ELVEN_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_ELVEN_WOOD.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.VARESO_LOG.get(), KoratioBlocks.STRIPPED_VARESO_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.VARESO_WOOD.get(), KoratioBlocks.STRIPPED_VARESO_WOOD.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.CANDY_LOG.get(), KoratioBlocks.STRIPPED_CANDY_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.CANDY_WOOD.get(), KoratioBlocks.STRIPPED_CANDY_WOOD.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.CHOCOLATE_OAK_LOG.get(), KoratioBlocks.STRIPPED_CHOCOLATE_OAK_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.CHOCOLATE_OAK_WOOD.get(), KoratioBlocks.STRIPPED_CHOCOLATE_OAK_WOOD.get());
	}

	public static void registerPots() {
		FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

		pot.addPlant(PANGO_SAPLING.getId(), POTTED_PANGO_SAPLING);
		pot.addPlant(RUGONA_SAPLING.getId(), POTTED_RUGONA_SAPLING);
		pot.addPlant(ELVEN_SAPLING.getId(), POTTED_ELVEN_SAPLING);
		pot.addPlant(VARESO_SAPLING.getId(), POTTED_VARESO_SAPLING);
		pot.addPlant(CANDY_SAPLING.getId(), POTTED_CANDY_SAPLING);
		pot.addPlant(CHOCOLATE_OAK_SAPLING.getId(), POTTED_CHOCOLATE_OAK_SAPLING);
		pot.addPlant(RAINBOW_ROSE.getId(), POTTED_RAINBOW_ROSE);
		pot.addPlant(RAINBOW_ALLIUM.getId(), POTTED_RAINBOW_ALLIUM);
		pot.addPlant(RAINBOW_LILY_OF_THE_VALLEY.getId(), POTTED_RAINBOW_LILY_OF_THE_VALLEY);
		pot.addPlant(COOKIE_FLOWER.getId(), POTTED_COOKIE_FLOWER);
		pot.addPlant(PURPLE_MUSHROOM.getId(), POTTED_PURPLE_MUSHROOM);
		pot.addPlant(GREEN_MUSHROOM.getId(), POTTED_GREEN_MUSHROOM);
	}

	public static void registerFlammables() {
		FireBlock fireblock = (FireBlock) Blocks.FIRE;

		fireblock.setFlammable(KoratioBlocks.PANGO_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.PANGO_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_PANGO_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_PANGO_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.PANGO_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.PANGO_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.PANGO_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.PANGO_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.PANGO_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.PANGO_FENCE_GATE.get(), 5, 20);

		fireblock.setFlammable(KoratioBlocks.RUGONA_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.RUGONA_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_RUGONA_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_RUGONA_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.RUGONA_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.RUGONA_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.RUGONA_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.RUGONA_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.RUGONA_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.RUGONA_FENCE_GATE.get(), 5, 20);

		fireblock.setFlammable(KoratioBlocks.VARESO_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.VARESO_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_VARESO_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_VARESO_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.VARESO_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.VARESO_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.VARESO_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.VARESO_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.VARESO_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.VARESO_FENCE_GATE.get(), 5, 20);

		fireblock.setFlammable(KoratioBlocks.CANDY_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.CANDY_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_CANDY_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_CANDY_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.PINK_COTTON_CANDY_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.LIME_COTTON_CANDY_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.YELLOW_COTTON_CANDY_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.CANDY_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CANDY_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CANDY_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CANDY_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CANDY_FENCE_GATE.get(), 5, 20);

		fireblock.setFlammable(KoratioBlocks.CHOCOLATE_OAK_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.CHOCOLATE_OAK_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_CHOCOLATE_OAK_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_CHOCOLATE_OAK_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.CHOCOLATE_OAK_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.CHOCOLATE_OAK_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CHOCOLATE_OAK_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CHOCOLATE_OAK_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CHOCOLATE_OAK_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CHOCOLATE_OAK_FENCE_GATE.get(), 5, 20);

		fireblock.setFlammable(KoratioBlocks.ELVEN_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.ELVEN_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_ELVEN_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_ELVEN_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.get(), 5, 5);
		fireblock.setFlammable(KoratioBlocks.ELVEN_LEAVES.get(), 30, 60);
		fireblock.setFlammable(KoratioBlocks.ELVEN_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.BLUE_ELVEN_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CYAN_ELVEN_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.GREEN_ELVEN_PLANKS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.ELVEN_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.BLUE_ELVEN_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CYAN_ELVEN_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.GREEN_ELVEN_SLAB.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.ELVEN_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.BLUE_ELVEN_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CYAN_ELVEN_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.GREEN_ELVEN_STAIRS.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.ELVEN_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.BLUE_ELVEN_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CYAN_ELVEN_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.GREEN_ELVEN_FENCE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.ELVEN_FENCE_GATE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get(), 5, 20);
		fireblock.setFlammable(KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get(), 5, 20);
	}
}