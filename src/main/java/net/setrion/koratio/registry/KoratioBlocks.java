package net.setrion.koratio.registry;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.AxeItem;
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
import net.setrion.koratio.world.level.block.CampfireBlock;
import net.setrion.koratio.world.level.block.ChestBlock;
import net.setrion.koratio.world.level.block.TallGrassBlock;
import net.setrion.koratio.world.level.block.TrappedChestBlock;
import net.setrion.koratio.world.level.block.*;

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
	public static final DeferredBlock<Block> FANTASIA_PORTAL = BLOCKS.register("fantasia_portal", () -> new FantasiaPortalBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().strength(-1.0F, 3600000.0F).sound(SoundType.GLASS).lightLevel((state) -> 11)));

	//Miniature Blocks (mostly used for advancements)
	public static final DeferredBlock<Block> MINIATURE_FANTASIA_PORTAL = BLOCKS.register("miniature_portal", () -> new Block(BlockBehaviour.Properties.of().noLootTable().noOcclusion()));

	//Fluids
	public static final DeferredBlock<Block> MOLTEN_WHITE_SUGAR = BLOCKS.register("molten_white_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_WHITE_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_LIGHT_GRAY_SUGAR = BLOCKS.register("molten_light_gray_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_GRAY_SUGAR = BLOCKS.register("molten_gray_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_GRAY_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_BLACK_SUGAR = BLOCKS.register("molten_black_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_BLACK_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_BROWN_SUGAR = BLOCKS.register("molten_brown_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_BROWN_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_RED_SUGAR = BLOCKS.register("molten_red_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_RED_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_ORANGE_SUGAR = BLOCKS.register("molten_orange_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_ORANGE_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_YELLOW_SUGAR = BLOCKS.register("molten_yellow_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_LIME_SUGAR = BLOCKS.register("molten_lime_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_LIME_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_GREEN_SUGAR = BLOCKS.register("molten_green_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_GREEN_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_CYAN_SUGAR = BLOCKS.register("molten_cyan_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_CYAN_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_LIGHT_BLUE_SUGAR = BLOCKS.register("molten_light_blue_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_BLUE_SUGAR = BLOCKS.register("molten_blue_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_BLUE_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_PURPLE_SUGAR = BLOCKS.register("molten_purple_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_PURPLE_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_MAGENTA_SUGAR = BLOCKS.register("molten_magenta_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_MAGENTA_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> MOLTEN_PINK_SUGAR = BLOCKS.register("molten_pink_sugar", () -> new LiquidBlock(KoratioFluids.MOLTEN_PINK_SUGAR.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> CHOCOLATE_MILK = BLOCKS.register("chocolate_milk", () -> new LiquidBlock(KoratioFluids.CHOCOLATE_MILK.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final DeferredBlock<Block> BLOOD = BLOCKS.register("blood", () -> new LiquidBlock(KoratioFluids.BLOOD.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));

	//Minerals / Ores
	public static final DeferredBlock<Block> RAINBOW_GEM_BLOCK = BLOCKS.register("rainbow_gem_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
	public static final DeferredBlock<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
	public static final DeferredBlock<Block> SAPPHIRE_BLOCK = BLOCKS.register("sapphire_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
	public static final DeferredBlock<Block> TOPAZ_BLOCK = BLOCKS.register("topaz_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
	public static final DeferredBlock<Block> ONYX_BLOCK = BLOCKS.register("onyx_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));

	public static final DeferredBlock<Block> RUBY_ORE = BLOCKS.register("ruby_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE = BLOCKS.register("deepslate_ruby_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(RUBY_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
	public static final DeferredBlock<Block> SAPPHIRE_ORE = BLOCKS.register("sapphire_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE = BLOCKS.register("deepslate_sapphire_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(SAPPHIRE_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
	public static final DeferredBlock<Block> TOPAZ_ORE = BLOCKS.register("topaz_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final DeferredBlock<Block> DEEPSLATE_TOPAZ_ORE = BLOCKS.register("deepslate_topaz_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(TOPAZ_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
	public static final DeferredBlock<Block> ONYX_ORE = BLOCKS.register("onyx_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final DeferredBlock<Block> DEEPSLATE_ONYX_ORE = BLOCKS.register("deepslate_onyx_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(ONYX_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));

	//Cookie
	public static final DeferredBlock<Block> COOKIE_ORE = BLOCKS.register("cookie_ore", () -> new DropExperienceBlock(ConstantInt.of(1), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final DeferredBlock<Block> DEEPSLATE_COOKIE_ORE = BLOCKS.register("deepslate_cookie_ore", () -> new DropExperienceBlock(ConstantInt.of(1), BlockBehaviour.Properties.ofFullCopy(COOKIE_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));

	//Misc
	public static final DeferredBlock<Block> FLIPPED_FARMLAND = BLOCKS.register("flipped_farmland", () -> new FlippedFarmBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking(KoratioBlocks::always).isSuffocating(KoratioBlocks::always)));
	public static final DeferredBlock<Block> DECRYPTING_TABLE = BLOCKS.register("decrypting_table", () -> new DecryptingTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));
	public static final DeferredBlock<Block> WOODCUTTER = BLOCKS.register("woodcutter", () -> new WoodcutterBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F)));
	public static final DeferredBlock<Block> CANDY_SHAPER = BLOCKS.register("candy_shaper", () -> new CandyShaperBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));

	public static final DeferredBlock<Block> MOLTEN_WHITE_SUGAR_CAULDRON = BLOCKS.register("molten_white_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_WHITE_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_LIGHT_GRAY_SUGAR_CAULDRON = BLOCKS.register("molten_light_gray_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_LIGHT_GRAY_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_GRAY_SUGAR_CAULDRON = BLOCKS.register("molten_gray_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_GRAY_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_BLACK_SUGAR_CAULDRON = BLOCKS.register("molten_black_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_BLACK_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_BROWN_SUGAR_CAULDRON = BLOCKS.register("molten_brown_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_BROWN_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_RED_SUGAR_CAULDRON = BLOCKS.register("molten_red_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_RED_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_ORANGE_SUGAR_CAULDRON = BLOCKS.register("molten_orange_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_ORANGE_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_YELLOW_SUGAR_CAULDRON = BLOCKS.register("molten_yellow_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_YELLOW_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_LIME_SUGAR_CAULDRON = BLOCKS.register("molten_lime_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_LIME_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_GREEN_SUGAR_CAULDRON = BLOCKS.register("molten_green_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_GREEN_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_CYAN_SUGAR_CAULDRON = BLOCKS.register("molten_cyan_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_CYAN_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_LIGHT_BLUE_SUGAR_CAULDRON = BLOCKS.register("molten_light_blue_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_LIGHT_BLUE_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_BLUE_SUGAR_CAULDRON = BLOCKS.register("molten_blue_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_BLUE_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_PURPLE_SUGAR_CAULDRON = BLOCKS.register("molten_purple_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_PURPLE_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_MAGENTA_SUGAR_CAULDRON = BLOCKS.register("molten_magenta_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_MAGENTA_SUGAR));
	public static final DeferredBlock<Block> MOLTEN_PINK_SUGAR_CAULDRON = BLOCKS.register("molten_pink_sugar_cauldron", () ->  new MoltenSugarCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON), CauldronInteraction.MOLTEN_PINK_SUGAR));

	public static final DeferredBlock<Block> WHITE_LEVITATING_WOOL = BLOCKS.register("white_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
	public static final DeferredBlock<Block> LIGHT_GRAY_LEVITATING_WOOL = BLOCKS.register("light_gray_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL)));
	public static final DeferredBlock<Block> GRAY_LEVITATING_WOOL = BLOCKS.register("gray_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL)));
	public static final DeferredBlock<Block> BLACK_LEVITATING_WOOL = BLOCKS.register("black_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL)));
	public static final DeferredBlock<Block> BROWN_LEVITATING_WOOL = BLOCKS.register("brown_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL)));
	public static final DeferredBlock<Block> RED_LEVITATING_WOOL = BLOCKS.register("red_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)));
	public static final DeferredBlock<Block> ORANGE_LEVITATING_WOOL = BLOCKS.register("orange_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL)));
	public static final DeferredBlock<Block> YELLOW_LEVITATING_WOOL = BLOCKS.register("yellow_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
	public static final DeferredBlock<Block> LIME_LEVITATING_WOOL = BLOCKS.register("lime_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL)));
	public static final DeferredBlock<Block> GREEN_LEVITATING_WOOL = BLOCKS.register("green_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL)));
	public static final DeferredBlock<Block> CYAN_LEVITATING_WOOL = BLOCKS.register("cyan_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL)));
	public static final DeferredBlock<Block> LIGHT_BLUE_LEVITATING_WOOL = BLOCKS.register("light_blue_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL)));
	public static final DeferredBlock<Block> BLUE_LEVITATING_WOOL = BLOCKS.register("blue_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL)));
	public static final DeferredBlock<Block> PURPLE_LEVITATING_WOOL = BLOCKS.register("purple_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL)));
	public static final DeferredBlock<Block> MAGENTA_LEVITATING_WOOL = BLOCKS.register("magenta_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL)));
	public static final DeferredBlock<Block> PINK_LEVITATING_WOOL = BLOCKS.register("pink_levitating_wool", () -> new ColoredLevitatingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL)));

	public static final DeferredBlock<Block> RAINBOW_CRYSTAL_BLOCK = BLOCKS.register("rainbow_crystal_block", () -> new AmethystBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> BUDDING_RAINBOW_CRYSTAL = BLOCKS.register("budding_rainbow_crystal", () -> new BuddingRainbowCrystalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> RAINBOW_CRYSTAL_CLUSTER = BLOCKS.register("rainbow_crystal_cluster", () -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel(state -> 5).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> LARGE_RAINBOW_CRYSTAL_BUD = BLOCKS.register("large_rainbow_crystal_bud", () -> new AmethystClusterBlock(5.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(RAINBOW_CRYSTAL_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel(state -> 4)));
	public static final DeferredBlock<Block> MEDIUM_RAINBOW_CRYSTAL_BUD = BLOCKS.register("medium_rainbow_crystal_bud", () -> new AmethystClusterBlock(4.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(RAINBOW_CRYSTAL_CLUSTER.get()).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel(state -> 2)));
	public static final DeferredBlock<Block> SMALL_RAINBOW_CRYSTAL_BUD = BLOCKS.register("small_rainbow_crystal_bud", () -> new AmethystClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy(RAINBOW_CRYSTAL_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel(state -> 1)));

	public static final DeferredBlock<Block> RAINBOW_CRYSTAL_GLASS = BLOCKS.register("rainbow_crystal_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
	public static final DeferredBlock<Block> RAINBOW_CRYSTAL_GLASS_PANE = BLOCKS.register("rainbow_crystal_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final DeferredBlock<RainbowFireBlock> RAINBOW_FIRE = BLOCKS.register("rainbow_fire", () -> new RainbowFireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_FIRE)));
	public static final DeferredBlock<RainbowTorchBlock> RAINBOW_TORCH = BLOCKS.register("rainbow_torch", () -> new RainbowTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_TORCH)));
	public static final DeferredBlock<RainbowWallTorchBlock> RAINBOW_WALL_TORCH = BLOCKS.register("rainbow_wall_torch", () -> new RainbowWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_WALL_TORCH).lootFrom(RAINBOW_TORCH)));
	public static final DeferredBlock<LanternBlock> RAINBOW_LANTERN = BLOCKS.register("rainbow_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));
	public static final DeferredBlock<CampfireBlock> RAINBOW_CAMPFIRE = BLOCKS.register("rainbow_campfire", () -> new CampfireBlock(true, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_CAMPFIRE)));
	public static final DeferredBlock<RainbowCandleBlock> RAINBOW_CANDLE = BLOCKS.register("rainbow_candle", () -> new RainbowCandleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<RainbowCandleCakeBlock> RAINBOW_CANDLE_CAKE = BLOCKS.register("rainbow_candle_cake", () -> new RainbowCandleCakeBlock(RAINBOW_CANDLE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE_CAKE)));

	//Candy Blocks
	public static final DeferredBlock<Block> COTTON_CANDY_GRASS = BLOCKS.register("cotton_candy_grass", () -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredBlock<Block> COOKIE_FLOWER = BLOCKS.register("cookie_flower", () -> new FlowerBlock(MobEffects.SATURATION, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.OXEYE_DAISY)));

	public static final DeferredBlock<Block> WHITE_SUGARGLASS_FLOWER = BLOCKS.register("white_sugarglass_flower", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final DeferredBlock<Block> BLUE_SUGARGLASS_FLOWER = BLOCKS.register("blue_sugarglass_flower", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final DeferredBlock<Block> GREEN_SUGARGLASS_FLOWER = BLOCKS.register("green_sugarglass_flower", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final DeferredBlock<Block> YELLOW_SUGARGLASS_FLOWER = BLOCKS.register("yellow_sugarglass_flower", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final DeferredBlock<Block> RED_SUGARGLASS_FLOWER = BLOCKS.register("red_sugarglass_flower", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

	public static final DeferredBlock<Block> STICKY_WHITE_SUGAR_BLOCK = BLOCKS.register("sticky_white_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_LIGHT_GRAY_SUGAR_BLOCK = BLOCKS.register("sticky_light_gray_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_GRAY_SUGAR_BLOCK = BLOCKS.register("sticky_gray_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_BLACK_SUGAR_BLOCK = BLOCKS.register("sticky_black_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_BROWN_SUGAR_BLOCK = BLOCKS.register("sticky_brown_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_RED_SUGAR_BLOCK = BLOCKS.register("sticky_red_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_ORANGE_SUGAR_BLOCK = BLOCKS.register("sticky_orange_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_YELLOW_SUGAR_BLOCK = BLOCKS.register("sticky_yellow_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_LIME_SUGAR_BLOCK = BLOCKS.register("sticky_lime_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_GREEN_SUGAR_BLOCK = BLOCKS.register("sticky_green_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_CYAN_SUGAR_BLOCK = BLOCKS.register("sticky_cyan_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_LIGHT_BLUE_SUGAR_BLOCK = BLOCKS.register("sticky_light_blue_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_BLUE_SUGAR_BLOCK = BLOCKS.register("sticky_blue_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_PURPLE_SUGAR_BLOCK = BLOCKS.register("sticky_purple_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_MAGENTA_SUGAR_BLOCK = BLOCKS.register("sticky_magenta_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final DeferredBlock<Block> STICKY_PINK_SUGAR_BLOCK = BLOCKS.register("sticky_pink_sugar_block", () -> new StickySugarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));

	public static final DeferredBlock<Block> WHITE_SUGAR_BLOCK = BLOCKS.register("white_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_WHITE_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> LIGHT_GRAY_SUGAR_BLOCK = BLOCKS.register("light_gray_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_LIGHT_GRAY_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> GRAY_SUGAR_BLOCK = BLOCKS.register("gray_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_GRAY_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> BLACK_SUGAR_BLOCK = BLOCKS.register("black_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_BLACK_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> BROWN_SUGAR_BLOCK = BLOCKS.register("brown_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_BROWN_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> RED_SUGAR_BLOCK = BLOCKS.register("red_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_RED_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> ORANGE_SUGAR_BLOCK = BLOCKS.register("orange_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_ORANGE_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> YELLOW_SUGAR_BLOCK = BLOCKS.register("yellow_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_YELLOW_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> LIME_SUGAR_BLOCK = BLOCKS.register("lime_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_LIME_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> GREEN_SUGAR_BLOCK = BLOCKS.register("green_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_GREEN_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> CYAN_SUGAR_BLOCK = BLOCKS.register("cyan_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_CYAN_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> LIGHT_BLUE_SUGAR_BLOCK = BLOCKS.register("light_blue_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_LIGHT_BLUE_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> BLUE_SUGAR_BLOCK = BLOCKS.register("blue_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_BLUE_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> PURPLE_SUGAR_BLOCK = BLOCKS.register("purple_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_PURPLE_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> MAGENTA_SUGAR_BLOCK = BLOCKS.register("magenta_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_MAGENTA_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> PINK_SUGAR_BLOCK = BLOCKS.register("pink_sugar_block", () -> new SugarBlock(new ColorRGBA(16317178), STICKY_PINK_SUGAR_BLOCK.get(), BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND)));

	public static final DeferredBlock<Block> WHITE_ICING_BLOCK = BLOCKS.register("white_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> LIGHT_GRAY_ICING_BLOCK = BLOCKS.register("light_gray_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> GRAY_ICING_BLOCK = BLOCKS.register("gray_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> BLACK_ICING_BLOCK = BLOCKS.register("black_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> BROWN_ICING_BLOCK = BLOCKS.register("brown_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> RED_ICING_BLOCK = BLOCKS.register("red_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> ORANGE_ICING_BLOCK = BLOCKS.register("orange_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> YELLOW_ICING_BLOCK = BLOCKS.register("yellow_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> LIME_ICING_BLOCK = BLOCKS.register("lime_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> GREEN_ICING_BLOCK = BLOCKS.register("green_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> CYAN_ICING_BLOCK = BLOCKS.register("cyan_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> LIGHT_BLUE_ICING_BLOCK = BLOCKS.register("light_blue_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> BLUE_ICING_BLOCK = BLOCKS.register("blue_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> PURPLE_ICING_BLOCK = BLOCKS.register("purple_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> MAGENTA_ICING_BLOCK = BLOCKS.register("magenta_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));
	public static final DeferredBlock<Block> PINK_ICING_BLOCK = BLOCKS.register("pink_icing_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WET_SPONGE)));

	public static final DeferredBlock<Block> WHITE_CANDY_BLOCK = BLOCKS.register("white_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> LIGHT_GRAY_CANDY_BLOCK = BLOCKS.register("light_gray_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> GRAY_CANDY_BLOCK = BLOCKS.register("gray_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> BLACK_CANDY_BLOCK = BLOCKS.register("black_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> BROWN_CANDY_BLOCK = BLOCKS.register("brown_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> RED_CANDY_BLOCK = BLOCKS.register("red_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> ORANGE_CANDY_BLOCK = BLOCKS.register("orange_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> YELLOW_CANDY_BLOCK = BLOCKS.register("yellow_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> LIME_CANDY_BLOCK = BLOCKS.register("lime_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> GREEN_CANDY_BLOCK = BLOCKS.register("green_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> CYAN_CANDY_BLOCK = BLOCKS.register("cyan_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> LIGHT_BLUE_CANDY_BLOCK = BLOCKS.register("light_blue_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> BLUE_CANDY_BLOCK = BLOCKS.register("blue_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> PURPLE_CANDY_BLOCK = BLOCKS.register("purple_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> MAGENTA_CANDY_BLOCK = BLOCKS.register("magenta_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> PINK_CANDY_BLOCK = BLOCKS.register("pink_candy_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.BONE_BLOCK)));

	public static final DeferredBlock<Block> RAW_GINGERBREAD_BLOCK = BLOCKS.register("raw_gingerbread_block", () -> new GlazedBlock(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.TUFF)));
	public static final DeferredBlock<StairBlock> RAW_GINGERBREAD_STAIRS = BLOCKS.register("raw_gingerbread_stairs", () -> new StairBlock(RAW_GINGERBREAD_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RAW_GINGERBREAD_BLOCK.get())));
	public static final DeferredBlock<SlabBlock> RAW_GINGERBREAD_SLAB = BLOCKS.register("raw_gingerbread_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(3.0F)));
	public static final DeferredBlock<Block> GINGERBREAD_BLOCK = BLOCKS.register("gingerbread_block", () -> new GlazedBlock(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.TUFF)));
	public static final DeferredBlock<StairBlock> GINGERBREAD_STAIRS = BLOCKS.register("gingerbread_stairs", () -> new StairBlock(GINGERBREAD_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GINGERBREAD_BLOCK.get())));
	public static final DeferredBlock<SlabBlock> GINGERBREAD_SLAB = BLOCKS.register("gingerbread_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(4.0F)));
	public static final DeferredBlock<Block> RAW_GINGERBREAD_BRICKS = BLOCKS.register("raw_gingerbread_bricks", () -> new GlazedBlock(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.TUFF)));
	public static final DeferredBlock<StairBlock> RAW_GINGERBREAD_BRICK_STAIRS = BLOCKS.register("raw_gingerbread_brick_stairs", () -> new StairBlock(RAW_GINGERBREAD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RAW_GINGERBREAD_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> RAW_GINGERBREAD_BRICK_SLAB = BLOCKS.register("raw_gingerbread_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(3.0F)));
	public static final DeferredBlock<Block> GINGERBREAD_BRICKS = BLOCKS.register("gingerbread_bricks", () -> new GlazedBlock(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.TUFF)));
	public static final DeferredBlock<StairBlock> GINGERBREAD_BRICK_STAIRS = BLOCKS.register("gingerbread_brick_stairs", () -> new StairBlock(GINGERBREAD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GINGERBREAD_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> GINGERBREAD_BRICK_SLAB = BLOCKS.register("gingerbread_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(4.0F)));
	public static final DeferredBlock<Block> RAW_LARGE_GINGERBREAD_BRICKS = BLOCKS.register("raw_large_gingerbread_bricks", () -> new GlazedBlock(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.TUFF)));
	public static final DeferredBlock<StairBlock> RAW_LARGE_GINGERBREAD_BRICK_STAIRS = BLOCKS.register("raw_large_gingerbread_brick_stairs", () -> new StairBlock(RAW_LARGE_GINGERBREAD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RAW_LARGE_GINGERBREAD_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> RAW_LARGE_GINGERBREAD_BRICK_SLAB = BLOCKS.register("raw_large_gingerbread_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(3.0F)));
	public static final DeferredBlock<Block> LARGE_GINGERBREAD_BRICKS = BLOCKS.register("large_gingerbread_bricks", () -> new GlazedBlock(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.TUFF)));
	public static final DeferredBlock<StairBlock> LARGE_GINGERBREAD_BRICK_STAIRS = BLOCKS.register("large_gingerbread_brick_stairs", () -> new StairBlock(LARGE_GINGERBREAD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LARGE_GINGERBREAD_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> LARGE_GINGERBREAD_BRICK_SLAB = BLOCKS.register("large_gingerbread_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(4.0F)));
	public static final DeferredBlock<WallBlock> RAW_GINGERBREAD_WALL = BLOCKS.register("raw_gingerbread_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(RAW_GINGERBREAD_BLOCK.get())));
	public static final DeferredBlock<WallBlock> GINGERBREAD_WALL = BLOCKS.register("gingerbread_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(GINGERBREAD_BLOCK.get())));
	public static final DeferredBlock<WallBlock> RAW_GINGERBREAD_BRICK_WALL = BLOCKS.register("raw_gingerbread_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(RAW_GINGERBREAD_BRICKS.get())));
	public static final DeferredBlock<WallBlock> GINGERBREAD_BRICK_WALL = BLOCKS.register("gingerbread_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(GINGERBREAD_BRICKS.get())));
	public static final DeferredBlock<WallBlock> RAW_LARGE_GINGERBREAD_BRICK_WALL = BLOCKS.register("raw_large_gingerbread_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(RAW_LARGE_GINGERBREAD_BRICKS.get())));
	public static final DeferredBlock<WallBlock> LARGE_GINGERBREAD_BRICK_WALL = BLOCKS.register("large_gingerbread_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LARGE_GINGERBREAD_BRICKS.get())));
	public static final DeferredBlock<Block> COOKIE_BLOCK = BLOCKS.register("cookie_block", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final DeferredBlock<StairBlock> COOKIE_BLOCK_STAIRS = BLOCKS.register("cookie_block_stairs", () -> new StairBlock(COOKIE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(COOKIE_BLOCK.get())));
	public static final DeferredBlock<SlabBlock> COOKIE_BLOCK_SLAB = BLOCKS.register("cookie_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(3.0F)));
	public static final DeferredBlock<ButtonBlock> COOKIE_BLOCK_BUTTON = BLOCKS.register("cookie_block_button", () -> new ButtonBlock(COOKIE_BLOCK_SET, 20, BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<PressurePlateBlock> COOKIE_BLOCK_PRESSURE_PLATE = BLOCKS.register("cookie_block_pressure_plate", () -> new PressurePlateBlock(COOKIE_BLOCK_SET, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<EatableBlock> MARSHMALLOW_BLOCK = BLOCKS.register("marshmallow_block", () -> new EatableBlock(BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.WOOL)));

	//Plants
	public static final DeferredBlock<Block> FANTASIA_GRASS = BLOCKS.register("fantasia_grass", () -> new net.minecraft.world.level.block.TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)) {
		@Override
		public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
			DoublePlantBlock doubleplantblock = KoratioBlocks.TALL_FANTASIA_GRASS.get();
			if (doubleplantblock.defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
				DoublePlantBlock.placeAt(level, doubleplantblock.defaultBlockState(), pos, 2);
			}
		}
	});
	public static final DeferredBlock<DoublePlantBlock> TALL_FANTASIA_GRASS = BLOCKS.register("tall_fantasia_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
	public static final DeferredBlock<Block> RAINBOW_ROSE = BLOCKS.register("rainbow_rose", () -> new FlowerBlock(MobEffects.DIG_SPEED, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.WITHER_ROSE)));
	public static final DeferredBlock<Block> RAINBOW_ALLIUM = BLOCKS.register("rainbow_allium", () -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)));
	public static final DeferredBlock<Block> RAINBOW_LILY_OF_THE_VALLEY = BLOCKS.register("rainbow_lily_of_the_valley", () -> new FlowerBlock(MobEffects.LUCK, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_OF_THE_VALLEY)));
	public static final DeferredBlock<Block> GOLDEN_TULIP = BLOCKS.register("golden_tulip", () -> new FlowerBlock(MobEffects.GLOWING, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
	public static final DeferredBlock<Block> GOLD_ROSE_BUSH = BLOCKS.register("gold_rose_bush", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
	public static final DeferredBlock<Block> PURPLE_MUSHROOM = BLOCKS.register("purple_mushroom", () -> new MushroomBlock(KoratioConfiguredFeatures.HUGE_PURPLE_MUSHROOM, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(KoratioBlocks::always)));
	public static final DeferredBlock<Block> PURPLE_MUSHROOM_BLOCK = BLOCKS.register("purple_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> GREEN_MUSHROOM = BLOCKS.register("green_mushroom", () -> new MushroomBlock(KoratioConfiguredFeatures.HUGE_GREEN_MUSHROOM, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(KoratioBlocks::always)));
	public static final DeferredBlock<Block> GREEN_MUSHROOM_BLOCK = BLOCKS.register("green_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> GILDED_VINES = BLOCKS.register("gilded_vines", () -> new VineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE)));

	public static final DeferredBlock<Block> CEINANAS = BLOCKS.register("ceinanas", () -> new FlippedCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)) {
		@Override
		protected ItemLike getBaseSeedId() {
			return KoratioItems.CEINANA.get();
		}
	});
	public static final DeferredBlock<Block> UPNIPS = BLOCKS.register("upnips", () -> new FlippedCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)) {
		@Override
		protected ItemLike getBaseSeedId() {
			return KoratioItems.UPNIP.get();
		}
	});
	public static final DeferredBlock<Block> ICE_ROSE = BLOCKS.register("ice_rose", () -> new FlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.WITHER_ROSE)) {
		@Override
		protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
			return state.is(BlockTags.SNOW);
		}
	});

	//Environment
	public static final DeferredBlock<Block> MAGIC_PATH = BLOCKS.register("magic_path", () -> new DirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH)));

	public static final DeferredBlock<Block> ANCIENT_COBBLESTONE = BLOCKS.register("ancient_cobblestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<StairBlock> ANCIENT_COBBLESTONE_STAIRS = BLOCKS.register("ancient_cobblestone_stairs", () -> new StairBlock(ANCIENT_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS)));
	public static final DeferredBlock<SlabBlock> ANCIENT_COBBLESTONE_SLAB = BLOCKS.register("ancient_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB)));
	public static final DeferredBlock<WallBlock> ANCIENT_COBBLESTONE_WALL = BLOCKS.register("ancient_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)));
	public static final DeferredBlock<Block> ANCIENT_STONE = BLOCKS.register("ancient_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<StairBlock> ANCIENT_STONE_STAIRS = BLOCKS.register("ancient_stone_stairs", () -> new StairBlock(ANCIENT_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)));
	public static final DeferredBlock<SlabBlock> ANCIENT_STONE_SLAB = BLOCKS.register("ancient_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
	public static final DeferredBlock<Block> ANCIENT_STONE_BRICKS = BLOCKS.register("ancient_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
	public static final DeferredBlock<StairBlock> ANCIENT_STONE_BRICK_STAIRS = BLOCKS.register("ancient_stone_brick_stairs", () -> new StairBlock(ANCIENT_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)));
	public static final DeferredBlock<SlabBlock> ANCIENT_STONE_BRICK_SLAB = BLOCKS.register("ancient_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
	public static final DeferredBlock<Block> CRACKED_ANCIENT_STONE_BRICKS = BLOCKS.register("cracked_ancient_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS)));
	public static final DeferredBlock<Block> CHISELED_ANCIENT_STONE_BRICKS = BLOCKS.register("chiseled_ancient_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS)));
	public static final DeferredBlock<Block> POLISHED_ANCIENT_STONE = BLOCKS.register("polished_ancient_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<StairBlock> POLISHED_ANCIENT_STONE_STAIRS = BLOCKS.register("polished_ancient_stone_stairs", () -> new StairBlock(POLISHED_ANCIENT_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)));
	public static final DeferredBlock<SlabBlock> POLISHED_ANCIENT_STONE_SLAB = BLOCKS.register("polished_ancient_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
	public static final DeferredBlock<Block> ANCIENT_STONE_TILES = BLOCKS.register("ancient_stone_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<StairBlock> ANCIENT_STONE_TILE_STAIRS = BLOCKS.register("ancient_stone_tile_stairs", () -> new StairBlock(ANCIENT_STONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)));
	public static final DeferredBlock<SlabBlock> ANCIENT_STONE_TILE_SLAB = BLOCKS.register("ancient_stone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
	public static final DeferredBlock<WallBlock> ANCIENT_STONE_BRICK_WALL = BLOCKS.register("ancient_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
	public static final DeferredBlock<WallBlock> POLISHED_ANCIENT_STONE_WALL = BLOCKS.register("polished_ancient_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_WALL)));
	public static final DeferredBlock<WallBlock> ANCIENT_STONE_TILE_WALL = BLOCKS.register("ancient_stone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILE_WALL)));

	public static final DeferredBlock<Block> ANCIENT_FURNACE = BLOCKS.register("ancient_furnace", () -> new FurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE)));
	public static final DeferredBlock<AncientDoorBlock> ANCIENT_DOOR_BLOCK = BLOCKS.register("ancient_door", () -> new AncientDoorBlock(BlockBehaviour.Properties.of().strength(1.0F)));
	public static final DeferredBlock<AncientTeleporterBlock> ANCIENT_TELEPORTER = BLOCKS.register("ancient_teleporter", () -> new AncientTeleporterBlock(BlockBehaviour.Properties.ofFullCopy(CHISELED_ANCIENT_STONE_BRICKS.get())));

	//Vanilla Variants
	public static final DeferredBlock<LeafPaneBlock> OAK_LEAF_PANE = BLOCKS.register("oak_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> SPRUCE_LEAF_PANE = BLOCKS.register("spruce_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> BIRCH_LEAF_PANE = BLOCKS.register("birch_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> JUNGLE_LEAF_PANE = BLOCKS.register("jungle_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> ACACIA_LEAF_PANE = BLOCKS.register("acacia_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> DARK_OAK_LEAF_PANE = BLOCKS.register("dark_oak_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> MANGROVE_LEAF_PANE = BLOCKS.register("mangrove_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> AZALEA_LEAF_PANE = BLOCKS.register("azalea_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> FLOWERING_AZALEA_LEAF_PANE = BLOCKS.register("flowering_azalea_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWERING_AZALEA_LEAVES)));
	public static final DeferredBlock<LeafPaneBlock> CHERRY_LEAF_PANE = BLOCKS.register("cherry_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES)));
	//public static final DeferredBlock<LeafPaneBlock> PALE_OAK_LEAF_PANE = BLOCKS.register("pale_oak_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_LEAVES)));

	public static final DeferredBlock<TallDoorBlock> TALL_OAK_DOOR = BLOCKS.register("tall_oak_door", () -> new TallDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_SPRUCE_DOOR = BLOCKS.register("tall_spruce_door", () -> new TallDoorBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_BIRCH_DOOR = BLOCKS.register("tall_birch_door", () -> new TallDoorBlock(BlockSetType.BIRCH, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_JUNGLE_DOOR = BLOCKS.register("tall_jungle_door", () -> new TallDoorBlock(BlockSetType.JUNGLE, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_ACACIA_DOOR = BLOCKS.register("tall_acacia_door", () -> new TallDoorBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_DARK_OAK_DOOR = BLOCKS.register("tall_dark_oak_door", () -> new TallDoorBlock(BlockSetType.DARK_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_MANGROVE_DOOR = BLOCKS.register("tall_mangrove_door", () -> new TallDoorBlock(BlockSetType.MANGROVE, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_CHERRY_DOOR = BLOCKS.register("tall_cherry_door", () -> new TallDoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_DOOR)));
	//public static final DeferredBlock<TallDoorBlock> TALL_PALE_OAK_DOOR = BLOCKS.register("tall_pale_oak_door", () -> new TallDoorBlock(BlockSetType.PALE_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_BAMBOO_DOOR = BLOCKS.register("tall_bamboo_door", () -> new TallDoorBlock(BlockSetType.BAMBOO, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_CRIMSON_DOOR = BLOCKS.register("tall_crimson_door", () -> new TallDoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_WARPED_DOOR = BLOCKS.register("tall_warped_door", () -> new TallDoorBlock(BlockSetType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_DOOR)));

	public static final DeferredBlock<TallDoorBlock> TALL_IRON_DOOR = BLOCKS.register("tall_iron_door", () -> new TallDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_COPPER_DOOR = BLOCKS.register("tall_copper_door", () -> new TallWeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_EXPOSED_COPPER_DOOR = BLOCKS.register("tall_exposed_copper_door", () -> new TallWeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.EXPOSED_COPPER_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_OXIDIZED_COPPER_DOOR = BLOCKS.register("tall_oxidized_copper_door", () -> new TallWeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.OXIDIZED_COPPER_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_WEATHERED_COPPER_DOOR = BLOCKS.register("tall_weathered_copper_door", () -> new TallWeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.WEATHERED_COPPER_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_WAXED_COPPER_DOOR = BLOCKS.register("tall_waxed_copper_door", () -> new TallDoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_COPPER_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_WAXED_EXPOSED_COPPER_DOOR = BLOCKS.register("tall_waxed_exposed_copper_door", () -> new TallDoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_COPPER_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_WAXED_OXIDIZED_COPPER_DOOR = BLOCKS.register("tall_waxed_oxidized_copper_door", () -> new TallDoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_COPPER_DOOR)));
	public static final DeferredBlock<TallDoorBlock> TALL_WAXED_WEATHERED_COPPER_DOOR = BLOCKS.register("tall_waxed_weathered_copper_door", () -> new TallDoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_COPPER_DOOR)));

	//Pango Wood
	public static final DeferredBlock<Block> PANGO_PLANKS = BLOCKS.register("pango_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> PANGO_LOG = BLOCKS.register("pango_log", () -> log(MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PANGO_LOG = BLOCKS.register("stripped_pango_log", () -> log(MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PANGO_WOOD = BLOCKS.register("stripped_pango_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> PANGO_WOOD = BLOCKS.register("pango_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> PANGO_LEAVES = BLOCKS.register("pango_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<LeafPaneBlock> PANGO_LEAF_PANE = BLOCKS.register("pango_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(PANGO_LEAVES.get())));
	public static final DeferredBlock<SlabBlock> PANGO_SLAB = BLOCKS.register("pango_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StairBlock> PANGO_STAIRS = BLOCKS.register("pango_stairs", () -> new StairBlock(PANGO_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(PANGO_PLANKS.get())));
	public static final DeferredBlock<Block> PANGO_SAPLING = BLOCKS.register("pango_sapling", () -> new SaplingBlock(KoratioTreeGrower.PANGO, BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<FenceBlock> PANGO_FENCE = BLOCKS.register("pango_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> PANGO_SIGN = BLOCKS.register("pango_sign", () -> new StandingSignBlock(PANGO_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallSignBlock> PANGO_WALL_SIGN = BLOCKS.register("pango_wall_sign", () -> new WallSignBlock(PANGO_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(PANGO_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> PANGO_HANGING_SIGN = BLOCKS.register("pango_hanging_sign",() -> new CeilingHangingSignBlock(PANGO_TYPE, BlockBehaviour.Properties.of().mapColor(PANGO_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<WallHangingSignBlock> PANGO_WALL_HANGING_SIGN = BLOCKS.register("pango_wall_hanging_sign",() -> new WallHangingSignBlock(PANGO_TYPE, BlockBehaviour.Properties.of().mapColor(PANGO_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(PANGO_HANGING_SIGN)));
	public static final DeferredBlock<ButtonBlock> PANGO_BUTTON = BLOCKS.register("pango_button", () -> new ButtonBlock(PANGO_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> PANGO_PRESSURE_PLATE = BLOCKS.register("pango_pressure_plate", () -> new PressurePlateBlock(PANGO_SET, BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<DoorBlock> PANGO_DOOR = BLOCKS.register("pango_door", () -> new DoorBlock(PANGO_SET, BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<TallDoorBlock> TALL_PANGO_DOOR = BLOCKS.register("tall_pango_door", () -> new TallDoorBlock(PANGO_SET, BlockBehaviour.Properties.ofFullCopy(PANGO_DOOR.get())));
	public static final DeferredBlock<TrapDoorBlock> PANGO_TRAPDOOR = BLOCKS.register("pango_trapdoor", () -> new TrapDoorBlock(PANGO_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<FenceGateBlock> PANGO_FENCE_GATE = BLOCKS.register("pango_fence_gate", () -> new FenceGateBlock(PANGO_TYPE, BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> PANGO_BOOKSHELF = BLOCKS.register("pango_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_PURPLE)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> PANGO_CHEST = BLOCKS.register("pango_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_PANGO_CHEST = BLOCKS.register("trapped_pango_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));

	//Rugona Wood
	public static final DeferredBlock<Block> RUGONA_PLANKS = BLOCKS.register("rugona_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> RUGONA_LOG = BLOCKS.register("rugona_log", () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_GREEN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_RUGONA_LOG = BLOCKS.register("stripped_rugona_log", () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_CYAN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_RUGONA_WOOD = BLOCKS.register("stripped_rugona_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> RUGONA_WOOD = BLOCKS.register("rugona_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> RUGONA_LEAVES = BLOCKS.register("rugona_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<LeafPaneBlock> RUGONA_LEAF_PANE = BLOCKS.register("rugona_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(RUGONA_LEAVES.get())));
	public static final DeferredBlock<SlabBlock> RUGONA_SLAB = BLOCKS.register("rugona_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StairBlock> RUGONA_STAIRS = BLOCKS.register("rugona_stairs", () -> new StairBlock(RUGONA_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RUGONA_PLANKS.get())));
	public static final DeferredBlock<Block> RUGONA_SAPLING = BLOCKS.register("rugona_sapling", () -> new SaplingBlock(KoratioTreeGrower.RUGONA, BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<FenceBlock> RUGONA_FENCE = BLOCKS.register("rugona_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> RUGONA_SIGN = BLOCKS.register("rugona_sign", () -> new StandingSignBlock(RUGONA_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallSignBlock> RUGONA_WALL_SIGN = BLOCKS.register("rugona_wall_sign", () -> new WallSignBlock(RUGONA_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(RUGONA_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> RUGONA_HANGING_SIGN = BLOCKS.register("rugona_hanging_sign",() -> new CeilingHangingSignBlock(RUGONA_TYPE, BlockBehaviour.Properties.of().mapColor(RUGONA_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<WallHangingSignBlock> RUGONA_WALL_HANGING_SIGN = BLOCKS.register("rugona_wall_hanging_sign",() -> new WallHangingSignBlock(RUGONA_TYPE, BlockBehaviour.Properties.of().mapColor(RUGONA_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(RUGONA_HANGING_SIGN)));
	public static final DeferredBlock<ButtonBlock> RUGONA_BUTTON = BLOCKS.register("rugona_button", () -> new ButtonBlock(RUGONA_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> RUGONA_PRESSURE_PLATE = BLOCKS.register("rugona_pressure_plate", () -> new PressurePlateBlock(RUGONA_SET, BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<DoorBlock> RUGONA_DOOR = BLOCKS.register("rugona_door", () -> new DoorBlock(RUGONA_SET, BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<TallDoorBlock> TALL_RUGONA_DOOR = BLOCKS.register("tall_rugona_door", () -> new TallDoorBlock(RUGONA_SET, BlockBehaviour.Properties.ofFullCopy(RUGONA_DOOR.get())));
	public static final DeferredBlock<TrapDoorBlock> RUGONA_TRAPDOOR = BLOCKS.register("rugona_trapdoor", () -> new TrapDoorBlock(RUGONA_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<FenceGateBlock> RUGONA_FENCE_GATE = BLOCKS.register("rugona_fence_gate", () -> new FenceGateBlock(RUGONA_TYPE, BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> RUGONA_BOOKSHELF = BLOCKS.register("rugona_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_CYAN)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> RUGONA_CHEST = BLOCKS.register("rugona_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_RUGONA_CHEST = BLOCKS.register("trapped_rugona_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));

	//Vareso Wood
	public static final DeferredBlock<Block> VARESO_PLANKS = BLOCKS.register("vareso_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> VARESO_LOG = BLOCKS.register("vareso_log", () -> log(MapColor.COLOR_BLACK, MapColor.COLOR_LIGHT_GRAY));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_VARESO_LOG = BLOCKS.register("stripped_vareso_log", () -> log(MapColor.COLOR_LIGHT_GRAY, MapColor.COLOR_LIGHT_GRAY));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_VARESO_WOOD = BLOCKS.register("stripped_vareso_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> VARESO_WOOD = BLOCKS.register("vareso_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> VARESO_LEAVES = BLOCKS.register("vareso_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<LeafPaneBlock> VARESO_LEAF_PANE = BLOCKS.register("vareso_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(VARESO_LEAVES.get())));
	public static final DeferredBlock<SlabBlock> VARESO_SLAB = BLOCKS.register("vareso_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StairBlock> VARESO_STAIRS = BLOCKS.register("vareso_stairs", () -> new StairBlock(VARESO_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VARESO_PLANKS.get())));
	public static final DeferredBlock<Block> VARESO_SAPLING = BLOCKS.register("vareso_sapling", () -> new SaplingBlock(KoratioTreeGrower.VARESO, BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<FenceBlock> VARESO_FENCE = BLOCKS.register("vareso_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> VARESO_SIGN = BLOCKS.register("vareso_sign", () -> new StandingSignBlock(VARESO_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallSignBlock> VARESO_WALL_SIGN = BLOCKS.register("vareso_wall_sign", () -> new WallSignBlock(VARESO_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(VARESO_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> VARESO_HANGING_SIGN = BLOCKS.register("vareso_hanging_sign",() -> new CeilingHangingSignBlock(VARESO_TYPE, BlockBehaviour.Properties.of().mapColor(VARESO_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<WallHangingSignBlock> VARESO_WALL_HANGING_SIGN = BLOCKS.register("vareso_wall_hanging_sign",() -> new WallHangingSignBlock(VARESO_TYPE, BlockBehaviour.Properties.of().mapColor(VARESO_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(VARESO_HANGING_SIGN)));
	public static final DeferredBlock<ButtonBlock> VARESO_BUTTON = BLOCKS.register("vareso_button", () -> new ButtonBlock(VARESO_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> VARESO_PRESSURE_PLATE = BLOCKS.register("vareso_pressure_plate", () -> new PressurePlateBlock(VARESO_SET, BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<DoorBlock> VARESO_DOOR = BLOCKS.register("vareso_door", () -> new DoorBlock(VARESO_SET, BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<TallDoorBlock> TALL_VARESO_DOOR = BLOCKS.register("tall_vareso_door", () -> new TallDoorBlock(VARESO_SET, BlockBehaviour.Properties.ofFullCopy(VARESO_DOOR.get())));
	public static final DeferredBlock<TrapDoorBlock> VARESO_TRAPDOOR = BLOCKS.register("vareso_trapdoor", () -> new TrapDoorBlock(VARESO_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<FenceGateBlock> VARESO_FENCE_GATE = BLOCKS.register("vareso_fence_gate", () -> new FenceGateBlock(VARESO_TYPE, BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> VARESO_BOOKSHELF = BLOCKS.register("vareso_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_LIGHT_GRAY)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> VARESO_CHEST = BLOCKS.register("vareso_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_VARESO_CHEST = BLOCKS.register("trapped_vareso_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));

	//Candy Wood
	public static final DeferredBlock<Block> CANDY_PLANKS = BLOCKS.register("candy_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> CANDY_LOG = BLOCKS.register("candy_log", () -> log(MapColor.COLOR_LIGHT_GRAY, MapColor.COLOR_PINK));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CANDY_LOG = BLOCKS.register("stripped_candy_log", () -> log(MapColor.COLOR_PINK, MapColor.COLOR_PINK));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CANDY_WOOD = BLOCKS.register("stripped_candy_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> CANDY_WOOD = BLOCKS.register("candy_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> PINK_COTTON_CANDY_LEAVES = BLOCKS.register("pink_cotton_candy_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<Block> LIGHT_BLUE_COTTON_CANDY_LEAVES = BLOCKS.register("light_blue_cotton_candy_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<Block> LIME_COTTON_CANDY_LEAVES = BLOCKS.register("lime_cotton_candy_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<Block> YELLOW_COTTON_CANDY_LEAVES = BLOCKS.register("yellow_cotton_candy_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<LeafPaneBlock> PINK_COTTON_CANDY_LEAF_PANE = BLOCKS.register("pink_cotton_candy_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(PINK_COTTON_CANDY_LEAVES.get())));
	public static final DeferredBlock<LeafPaneBlock> LIGHT_BLUE_COTTON_CANDY_LEAF_PANE = BLOCKS.register("light_blue_cotton_candy_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(LIGHT_BLUE_COTTON_CANDY_LEAVES.get())));
	public static final DeferredBlock<LeafPaneBlock> LIME_COTTON_CANDY_LEAF_PANE = BLOCKS.register("lime_cotton_candy_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(LIME_COTTON_CANDY_LEAVES.get())));
	public static final DeferredBlock<LeafPaneBlock> YELLOW_COTTON_CANDY_LEAF_PANE = BLOCKS.register("yellow_cotton_candy_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(YELLOW_COTTON_CANDY_LEAVES.get())));
	public static final DeferredBlock<SlabBlock> CANDY_SLAB = BLOCKS.register("candy_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StairBlock> CANDY_STAIRS = BLOCKS.register("candy_stairs", () -> new StairBlock(CANDY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CANDY_PLANKS.get())));
	public static final DeferredBlock<Block> CANDY_SAPLING = BLOCKS.register("candy_sapling", () -> new SaplingBlock(KoratioTreeGrower.CANDY, BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<FenceBlock> CANDY_FENCE = BLOCKS.register("candy_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(CANDY_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> CANDY_SIGN = BLOCKS.register("candy_sign", () -> new StandingSignBlock(CANDY_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallSignBlock> CANDY_WALL_SIGN = BLOCKS.register("candy_wall_sign", () -> new WallSignBlock(CANDY_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(CANDY_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> CANDY_HANGING_SIGN = BLOCKS.register("candy_hanging_sign",() -> new CeilingHangingSignBlock(CANDY_TYPE, BlockBehaviour.Properties.of().mapColor(CANDY_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<WallHangingSignBlock> CANDY_WALL_HANGING_SIGN = BLOCKS.register("candy_wall_hanging_sign",() -> new WallHangingSignBlock(CANDY_TYPE, BlockBehaviour.Properties.of().mapColor(CANDY_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(CANDY_HANGING_SIGN)));
	public static final DeferredBlock<ButtonBlock> CANDY_BUTTON = BLOCKS.register("candy_button", () -> new ButtonBlock(CANDY_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> CANDY_PRESSURE_PLATE = BLOCKS.register("candy_pressure_plate", () -> new PressurePlateBlock(CANDY_SET, BlockBehaviour.Properties.of().mapColor(CANDY_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<DoorBlock> CANDY_DOOR = BLOCKS.register("candy_door", () -> new DoorBlock(CANDY_SET, BlockBehaviour.Properties.of().mapColor(CANDY_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	//public static final DeferredBlock<TallDoorBlock> TALL_CANDY_DOOR = BLOCKS.register("tall_candy_door", () -> new TallDoorBlock(CANDY_SET, BlockBehaviour.Properties.ofFullCopy(CANDY_DOOR.get())));
	public static final DeferredBlock<TrapDoorBlock> CANDY_TRAPDOOR = BLOCKS.register("candy_trapdoor", () -> new TrapDoorBlock(CANDY_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<FenceGateBlock> CANDY_FENCE_GATE = BLOCKS.register("candy_fence_gate", () -> new FenceGateBlock(CANDY_TYPE, BlockBehaviour.Properties.of().mapColor(CANDY_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> CANDY_BOOKSHELF = BLOCKS.register("candy_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_PINK)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> CANDY_CHEST = BLOCKS.register("candy_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_CANDY_CHEST = BLOCKS.register("trapped_candy_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));

	//Pango Wood
	public static final DeferredBlock<Block> CHOCOLATE_OAK_PLANKS = BLOCKS.register("chocolate_oak_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> CHOCOLATE_OAK_LOG = BLOCKS.register("chocolate_oak_log", () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHOCOLATE_OAK_LOG = BLOCKS.register("stripped_chocolate_oak_log", () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHOCOLATE_OAK_WOOD = BLOCKS.register("stripped_chocolate_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> CHOCOLATE_OAK_WOOD = BLOCKS.register("chocolate_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> CHOCOLATE_OAK_LEAVES = BLOCKS.register("chocolate_oak_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<LeafPaneBlock> CHOCOLATE_OAK_LEAF_PANE = BLOCKS.register("chocolate_oak_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(CHOCOLATE_OAK_LEAVES.get())));
	public static final DeferredBlock<SlabBlock> CHOCOLATE_OAK_SLAB = BLOCKS.register("chocolate_oak_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StairBlock> CHOCOLATE_OAK_STAIRS = BLOCKS.register("chocolate_oak_stairs", () -> new StairBlock(CHOCOLATE_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CHOCOLATE_OAK_PLANKS.get())));
	public static final DeferredBlock<Block> CHOCOLATE_OAK_SAPLING = BLOCKS.register("chocolate_oak_sapling", () -> new SaplingBlock(KoratioTreeGrower.CHOCOLATE_OAK, BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<FenceBlock> CHOCOLATE_OAK_FENCE = BLOCKS.register("chocolate_oak_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> CHOCOLATE_OAK_SIGN = BLOCKS.register("chocolate_oak_sign", () -> new StandingSignBlock(CHOCOLATE_OAK_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallSignBlock> CHOCOLATE_OAK_WALL_SIGN = BLOCKS.register("chocolate_oak_wall_sign", () -> new WallSignBlock(CHOCOLATE_OAK_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(CHOCOLATE_OAK_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> CHOCOLATE_OAK_HANGING_SIGN = BLOCKS.register("chocolate_oak_hanging_sign",() -> new CeilingHangingSignBlock(CHOCOLATE_OAK_TYPE, BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<WallHangingSignBlock> CHOCOLATE_OAK_WALL_HANGING_SIGN = BLOCKS.register("chocolate_oak_wall_hanging_sign",() -> new WallHangingSignBlock(CHOCOLATE_OAK_TYPE, BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(CHOCOLATE_OAK_HANGING_SIGN)));
	public static final DeferredBlock<ButtonBlock> CHOCOLATE_OAK_BUTTON = BLOCKS.register("chocolate_oak_button", () -> new ButtonBlock(CHOCOLATE_OAK_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> CHOCOLATE_OAK_PRESSURE_PLATE = BLOCKS.register("chocolate_oak_pressure_plate", () -> new PressurePlateBlock(CHOCOLATE_OAK_SET, BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<DoorBlock> CHOCOLATE_OAK_DOOR = BLOCKS.register("chocolate_oak_door", () -> new DoorBlock(CHOCOLATE_OAK_SET, BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	//public static final DeferredBlock<TallDoorBlock> TALL_CHOCOLATE_OAK_DOOR = BLOCKS.register("tall_chocolate_oak_door", () -> new TallDoorBlock(CHOCOLATE_OAK_SET, BlockBehaviour.Properties.ofFullCopy(CHOCOLATE_OAK_DOOR.get())));
	public static final DeferredBlock<TrapDoorBlock> CHOCOLATE_OAK_TRAPDOOR = BLOCKS.register("chocolate_oak_trapdoor", () -> new TrapDoorBlock(CHOCOLATE_OAK_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<FenceGateBlock> CHOCOLATE_OAK_FENCE_GATE = BLOCKS.register("chocolate_oak_fence_gate", () -> new FenceGateBlock(CHOCOLATE_OAK_TYPE, BlockBehaviour.Properties.of().mapColor(CHOCOLATE_OAK_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> CHOCOLATE_OAK_BOOKSHELF = BLOCKS.register("chocolate_oak_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> CHOCOLATE_OAK_CHEST = BLOCKS.register("chocolate_oak_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_CHOCOLATE_OAK_CHEST = BLOCKS.register("trapped_chocolate_oak_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));

	//Elven Wood
	public static final DeferredBlock<Block> ELVEN_PLANKS = BLOCKS.register("elven_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> BLUE_ELVEN_PLANKS = BLOCKS.register("blue_elven_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> CYAN_ELVEN_PLANKS = BLOCKS.register("cyan_elven_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> GREEN_ELVEN_PLANKS = BLOCKS.register("green_elven_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> ELVEN_LOG = BLOCKS.register("elven_log", () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_CYAN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ELVEN_LOG = BLOCKS.register("stripped_elven_log", () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_CYAN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLUE_ELVEN_LOG = BLOCKS.register("stripped_blue_elven_log", () -> log(MapColor.COLOR_BLUE, MapColor.COLOR_BLUE));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CYAN_ELVEN_LOG = BLOCKS.register("stripped_cyan_elven_log", () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_CYAN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GREEN_ELVEN_LOG = BLOCKS.register("stripped_green_elven_log", () -> log(MapColor.COLOR_GREEN, MapColor.COLOR_GREEN));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ELVEN_WOOD = BLOCKS.register("stripped_elven_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLUE_ELVEN_WOOD = BLOCKS.register("stripped_blue_elven_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CYAN_ELVEN_WOOD = BLOCKS.register("stripped_cyan_elven_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GREEN_ELVEN_WOOD = BLOCKS.register("stripped_green_elven_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> ELVEN_WOOD = BLOCKS.register("elven_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> ELVEN_LEAVES = BLOCKS.register("elven_leaves", () -> new ElvenLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final DeferredBlock<LeafPaneBlock> ELVEN_LEAF_PANE = BLOCKS.register("elven_leaf_pane", () -> new LeafPaneBlock(BlockBehaviour.Properties.ofFullCopy(ELVEN_LEAVES.get())));
	public static final DeferredBlock<SlabBlock> ELVEN_SLAB = BLOCKS.register("elven_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<SlabBlock> BLUE_ELVEN_SLAB = BLOCKS.register("blue_elven_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<SlabBlock> CYAN_ELVEN_SLAB = BLOCKS.register("cyan_elven_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<SlabBlock> GREEN_ELVEN_SLAB = BLOCKS.register("green_elven_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StairBlock> ELVEN_STAIRS = BLOCKS.register("elven_stairs", () -> new StairBlock(ELVEN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ELVEN_PLANKS.get())));
	public static final DeferredBlock<StairBlock> BLUE_ELVEN_STAIRS = BLOCKS.register("blue_elven_stairs", () -> new StairBlock(BLUE_ELVEN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ELVEN_PLANKS.get())));
	public static final DeferredBlock<StairBlock> CYAN_ELVEN_STAIRS = BLOCKS.register("cyan_elven_stairs", () -> new StairBlock(CYAN_ELVEN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CYAN_ELVEN_PLANKS.get())));
	public static final DeferredBlock<StairBlock> GREEN_ELVEN_STAIRS = BLOCKS.register("green_elven_stairs", () -> new StairBlock(GREEN_ELVEN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GREEN_ELVEN_PLANKS.get())));
	public static final DeferredBlock<Block> ELVEN_SAPLING = BLOCKS.register("elven_sapling", () -> new SaplingBlock(KoratioTreeGrower.ELVEN, BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<FenceBlock> ELVEN_FENCE = BLOCKS.register("elven_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<FenceBlock> BLUE_ELVEN_FENCE = BLOCKS.register("blue_elven_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(BLUE_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<FenceBlock> CYAN_ELVEN_FENCE = BLOCKS.register("cyan_elven_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(CYAN_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<FenceBlock> GREEN_ELVEN_FENCE = BLOCKS.register("green_elven_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(GREEN_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> ELVEN_SIGN = BLOCKS.register("elven_sign", () -> new StandingSignBlock(ELVEN_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> BLUE_ELVEN_SIGN = BLOCKS.register("blue_elven_sign", () -> new StandingSignBlock(BLUE_ELVEN_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> CYAN_ELVEN_SIGN = BLOCKS.register("cyan_elven_sign", () -> new StandingSignBlock(CYAN_ELVEN_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<StandingSignBlock> GREEN_ELVEN_SIGN = BLOCKS.register("green_elven_sign", () -> new StandingSignBlock(GREEN_ELVEN_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallSignBlock> ELVEN_WALL_SIGN = BLOCKS.register("elven_wall_sign", () -> new WallSignBlock(ELVEN_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(ELVEN_SIGN)));
	public static final DeferredBlock<WallSignBlock> BLUE_ELVEN_WALL_SIGN = BLOCKS.register("blue_elven_wall_sign", () -> new WallSignBlock(BLUE_ELVEN_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(BLUE_ELVEN_SIGN)));
	public static final DeferredBlock<WallSignBlock> CYAN_ELVEN_WALL_SIGN = BLOCKS.register("cyan_elven_wall_sign", () -> new WallSignBlock(CYAN_ELVEN_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(CYAN_ELVEN_SIGN)));
	public static final DeferredBlock<WallSignBlock> GREEN_ELVEN_WALL_SIGN = BLOCKS.register("green_elven_wall_sign", () -> new WallSignBlock(GREEN_ELVEN_TYPE, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(GREEN_ELVEN_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> ELVEN_HANGING_SIGN = BLOCKS.register("elven_hanging_sign",() -> new CeilingHangingSignBlock(ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<CeilingHangingSignBlock> BLUE_ELVEN_HANGING_SIGN = BLOCKS.register("blue_elven_hanging_sign",() -> new CeilingHangingSignBlock(BLUE_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<CeilingHangingSignBlock> CYAN_ELVEN_HANGING_SIGN = BLOCKS.register("cyan_elven_hanging_sign",() -> new CeilingHangingSignBlock(CYAN_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<CeilingHangingSignBlock> GREEN_ELVEN_HANGING_SIGN = BLOCKS.register("green_elven_hanging_sign",() -> new CeilingHangingSignBlock(GREEN_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F)));
	public static final DeferredBlock<WallHangingSignBlock> ELVEN_WALL_HANGING_SIGN = BLOCKS.register("elven_wall_hanging_sign",() -> new WallHangingSignBlock(ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(ELVEN_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> BLUE_ELVEN_WALL_HANGING_SIGN = BLOCKS.register("blue_elven_wall_hanging_sign",() -> new WallHangingSignBlock(BLUE_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(BLUE_ELVEN_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> CYAN_ELVEN_WALL_HANGING_SIGN = BLOCKS.register("cyan_elven_wall_hanging_sign",() -> new WallHangingSignBlock(CYAN_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(CYAN_ELVEN_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> GREEN_ELVEN_WALL_HANGING_SIGN = BLOCKS.register("green_elven_wall_hanging_sign",() -> new WallHangingSignBlock(GREEN_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(GREEN_ELVEN_HANGING_SIGN)));
	public static final DeferredBlock<ButtonBlock> ELVEN_BUTTON = BLOCKS.register("elven_button", () -> new ButtonBlock(ELVEN_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<ButtonBlock> BLUE_ELVEN_BUTTON = BLOCKS.register("blue_elven_button", () -> new ButtonBlock(BLUE_ELVEN_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<ButtonBlock> CYAN_ELVEN_BUTTON = BLOCKS.register("cyan_elven_button", () -> new ButtonBlock(CYAN_ELVEN_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<ButtonBlock> GREEN_ELVEN_BUTTON = BLOCKS.register("green_elven_button", () -> new ButtonBlock(GREEN_ELVEN_SET, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> ELVEN_PRESSURE_PLATE = BLOCKS.register("elven_pressure_plate", () -> new PressurePlateBlock(ELVEN_SET, BlockBehaviour.Properties.of().mapColor(ELVEN_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> BLUE_ELVEN_PRESSURE_PLATE = BLOCKS.register("blue_elven_pressure_plate", () -> new PressurePlateBlock(BLUE_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(BLUE_ELVEN_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> CYAN_ELVEN_PRESSURE_PLATE = BLOCKS.register("cyan_elven_pressure_plate", () -> new PressurePlateBlock(CYAN_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(CYAN_ELVEN_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<PressurePlateBlock> GREEN_ELVEN_PRESSURE_PLATE = BLOCKS.register("green_elven_pressure_plate", () -> new PressurePlateBlock(GREEN_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(GREEN_ELVEN_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
	public static final DeferredBlock<DoorBlock> ELVEN_DOOR = BLOCKS.register("elven_door", () -> new DoorBlock(ELVEN_SET, BlockBehaviour.Properties.of().mapColor(ELVEN_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<DoorBlock> BLUE_ELVEN_DOOR = BLOCKS.register("blue_elven_door", () -> new DoorBlock(BLUE_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(BLUE_ELVEN_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<DoorBlock> CYAN_ELVEN_DOOR = BLOCKS.register("cyan_elven_door", () -> new DoorBlock(CYAN_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(CYAN_ELVEN_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<DoorBlock> GREEN_ELVEN_DOOR = BLOCKS.register("green_elven_door", () -> new DoorBlock(GREEN_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(GREEN_ELVEN_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<TallDoorBlock> TALL_ELVEN_DOOR = BLOCKS.register("tall_elven_door", () -> new TallDoorBlock(ELVEN_SET, BlockBehaviour.Properties.ofFullCopy(ELVEN_DOOR.get())));
	public static final DeferredBlock<TallDoorBlock> TALL_BLUE_ELVEN_DOOR = BLOCKS.register("tall_blue_elven_door", () -> new TallDoorBlock(BLUE_ELVEN_SET, BlockBehaviour.Properties.ofFullCopy(BLUE_ELVEN_DOOR.get())));
	public static final DeferredBlock<TallDoorBlock> TALL_CYAN_ELVEN_DOOR = BLOCKS.register("tall_cyan_elven_door", () -> new TallDoorBlock(CYAN_ELVEN_SET, BlockBehaviour.Properties.ofFullCopy(CYAN_ELVEN_DOOR.get())));
	public static final DeferredBlock<TallDoorBlock> TALL_GREEN_ELVEN_DOOR = BLOCKS.register("tall_green_elven_door", () -> new TallDoorBlock(GREEN_ELVEN_SET, BlockBehaviour.Properties.ofFullCopy(GREEN_ELVEN_DOOR.get())));
	public static final DeferredBlock<TrapDoorBlock> ELVEN_TRAPDOOR = BLOCKS.register("elven_trapdoor", () -> new TrapDoorBlock(ELVEN_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<TrapDoorBlock> BLUE_ELVEN_TRAPDOOR = BLOCKS.register("blue_elven_trapdoor", () -> new TrapDoorBlock(BLUE_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<TrapDoorBlock> CYAN_ELVEN_TRAPDOOR = BLOCKS.register("cyan_elven_trapdoor", () -> new TrapDoorBlock(CYAN_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<TrapDoorBlock> GREEN_ELVEN_TRAPDOOR = BLOCKS.register("green_elven_trapdoor", () -> new TrapDoorBlock(GREEN_ELVEN_SET, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never)));
	public static final DeferredBlock<FenceGateBlock> ELVEN_FENCE_GATE = BLOCKS.register("elven_fence_gate", () -> new FenceGateBlock(ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<FenceGateBlock> BLUE_ELVEN_FENCE_GATE = BLOCKS.register("blue_elven_fence_gate", () -> new FenceGateBlock(BLUE_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(BLUE_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<FenceGateBlock> CYAN_ELVEN_FENCE_GATE = BLOCKS.register("cyan_elven_fence_gate", () -> new FenceGateBlock(CYAN_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(CYAN_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<FenceGateBlock> GREEN_ELVEN_FENCE_GATE = BLOCKS.register("green_elven_fence_gate", () -> new FenceGateBlock(GREEN_ELVEN_TYPE, BlockBehaviour.Properties.of().mapColor(GREEN_ELVEN_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> ELVEN_BOOKSHELF = BLOCKS.register("elven_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_CYAN)));
	public static final DeferredBlock<Block> BLUE_ELVEN_BOOKSHELF = BLOCKS.register("blue_elven_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_BLUE)));
	public static final DeferredBlock<Block> CYAN_ELVEN_BOOKSHELF = BLOCKS.register("cyan_elven_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_CYAN)));
	public static final DeferredBlock<Block> GREEN_ELVEN_BOOKSHELF = BLOCKS.register("green_elven_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF).mapColor(MapColor.COLOR_GREEN)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> ELVEN_CHEST = BLOCKS.register("elven_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> BLUE_ELVEN_CHEST = BLOCKS.register("blue_elven_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> CYAN_ELVEN_CHEST = BLOCKS.register("cyan_elven_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<net.minecraft.world.level.block.ChestBlock> GREEN_ELVEN_CHEST = BLOCKS.register("green_elven_chest", () -> new ChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_ELVEN_CHEST = BLOCKS.register("trapped_elven_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_BLUE_ELVEN_CHEST = BLOCKS.register("trapped_blue_elven_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_CYAN_ELVEN_CHEST = BLOCKS.register("trapped_cyan_elven_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));
	public static final DeferredBlock<TrappedChestBlock> TRAPPED_GREEN_ELVEN_CHEST = BLOCKS.register("trapped_green_elven_chest", () -> new TrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST)));

	//Potted Plants
	public static final DeferredBlock<FlowerPotBlock> POTTED_RAINBOW_ROSE = BLOCKS.register("potted_rainbow_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_ROSE, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_RAINBOW_ALLIUM = BLOCKS.register("potted_rainbow_allium", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_ALLIUM, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_RAINBOW_LILY_OF_THE_VALLEY = BLOCKS.register("potted_rainbow_lily_of_the_valley", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_LILY_OF_THE_VALLEY, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_COOKIE_FLOWER = BLOCKS.register("potted_cookie_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, COOKIE_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_PURPLE_MUSHROOM = BLOCKS.register("potted_purple_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PURPLE_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GREEN_MUSHROOM = BLOCKS.register("potted_green_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GREEN_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_PANGO_SAPLING = BLOCKS.register("potted_pango_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PANGO_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_RUGONA_SAPLING = BLOCKS.register("potted_rugona_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RUGONA_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VARESO_SAPLING = BLOCKS.register("potted_vareso_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VARESO_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_CANDY_SAPLING = BLOCKS.register("potted_candy_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CANDY_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_CHOCOLATE_OAK_SAPLING = BLOCKS.register("potted_chocolate_oak_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CHOCOLATE_OAK_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_ELVEN_SAPLING = BLOCKS.register("potted_elven_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ELVEN_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));


	private static RotatedPillarBlock log(MapColor color, MapColor color2) {
		return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor( (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? color : color2).strength(2.0F).sound(SoundType.WOOD));
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