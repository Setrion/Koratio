package net.setrion.koratio.registry;

import java.util.function.ToIntFunction;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.block.FireBlock;
import net.setrion.koratio.world.level.block.CeilingHangingSignBlock;
import net.setrion.koratio.world.level.block.ChestBlock;
import net.setrion.koratio.world.level.block.DecryptingTableBlock;
import net.setrion.koratio.world.level.block.FantasiaPortalBlock;
import net.setrion.koratio.world.level.block.InfestedBlock;
import net.setrion.koratio.world.level.block.StandingSignBlock;
import net.setrion.koratio.world.level.block.TallGrassBlock;
import net.setrion.koratio.world.level.block.TorchBlock;
import net.setrion.koratio.world.level.block.WallHangingSignBlock;
import net.setrion.koratio.world.level.block.WallSignBlock;
import net.setrion.koratio.world.level.block.WallTorchBlock;
import net.setrion.koratio.world.level.block.entity.CampfireBlockEntity;
import net.setrion.koratio.world.level.block.grower.NighyTreeGrower;
import net.setrion.koratio.world.level.block.grower.PangoBushGrower;
import net.setrion.koratio.world.level.block.grower.RugonaTreeGrower;
import net.setrion.koratio.world.level.block.grower.VaresoTreeGrower;

public class KoratioBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Koratio.MOD_ID);
	
	//BlockSetTypes
	public static final BlockSetType PANGO_SET = new BlockSetType("pango");
	public static final BlockSetType RUGONA_SET = new BlockSetType("rugona");
	public static final BlockSetType VARESO_SET = new BlockSetType("vareso");
	public static final BlockSetType NIGHY_SET = new BlockSetType("nighy");
	public static final BlockSetType SOUL_STONE_SET = new BlockSetType("soul_stone", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
	
	//WoodTypes
	public static final WoodType PANGO = new WoodType("pango", PANGO_SET);
	public static final WoodType RUGONA = new WoodType("rugona", RUGONA_SET);
	public static final WoodType VARESO = new WoodType("vareso", VARESO_SET);
	public static final WoodType NIGHY = new WoodType("nighy", NIGHY_SET);
	
	//Portal
	public static final RegistryObject<Block> FANTASIA_PORTAL = BLOCKS.register("fantasia_portal", () -> new FantasiaPortalBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().strength(-1.0F).sound(SoundType.GLASS).lightLevel((state) -> {
		return 11;
	})));
	
	//Miniature Blocks (mostly used for advancements)
	public static final RegistryObject<Block> MINIATURE_FANTASIA_PORTAL = BLOCKS.register("miniature_portal", () -> new Block(BlockBehaviour.Properties.of().noLootTable().noOcclusion()));
	
	//Fluids
	public static final RegistryObject<Block> CHOCOLATE_MILK = BLOCKS.register("chocolate_milk", () -> new LiquidBlock(() -> KoratioFluids.CHOCOLATE_MILK.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	public static final RegistryObject<Block> BLOOD = BLOCKS.register("blood", () -> new LiquidBlock(() -> KoratioFluids.BLOOD.get(), BlockBehaviour.Properties.of().replaceable().noCollission().randomTicks().strength(100.0F).noLootTable()));
	
	//Minerals / Ores
	public static final RegistryObject<Block> RAINBOW_GEM_BLOCK = BLOCKS.register("rainbow_gem_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
	
	//Cookie
	public static final RegistryObject<Block> COOKIE_ORE = BLOCKS.register("cookie_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final RegistryObject<Block> DEEPSLATE_COOKIE_ORE = BLOCKS.register("deepslate_cookie_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(COOKIE_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<Block> BLOOD_STAINED_DEEPSLATE_COOKIE_ORE = BLOCKS.register("blood_stained_deepslate_cookie_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(COOKIE_ORE.get()).mapColor(MapColor.CRIMSON_NYLIUM).strength(9.0F, 6.0F).sound(SoundType.DEEPSLATE)));
		
	//Arsoy
	public static final RegistryObject<Block> ARSOY_ORE = BLOCKS.register("arsoy_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final RegistryObject<Block> DEEPSLATE_ARSOY_ORE = BLOCKS.register("deepslate_arsoy_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(ARSOY_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<Block> BLOOD_STAINED_DEEPSLATE_ARSOY_ORE = BLOCKS.register("blood_stained_deepslate_arsoy_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(ARSOY_ORE.get()).mapColor(MapColor.CRIMSON_NYLIUM).strength(9.0F, 6.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<Block> RAW_ARSOY_BLOCK = BLOCKS.register("raw_arsoy_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final RegistryObject<Block> ARSOY_BLOCK = BLOCKS.register("arsoy_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

	//Misc
	public static final RegistryObject<Block> AMETHYST_COBWEB = BLOCKS.register("amethyst_cobweb", () -> new WebBlock(BlockBehaviour.Properties.copy(Blocks.COBWEB)));
	public static final RegistryObject<Block> EMERALD_COBWEB = BLOCKS.register("emerald_cobweb", () -> new WebBlock(BlockBehaviour.Properties.copy(Blocks.COBWEB)));
	public static final RegistryObject<RotatedPillarBlock> GOLDEN_HAY_BLOCK = BLOCKS.register("golden_hay_block", () -> new HayBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));
	public static final RegistryObject<Block> DECRYPTING_TABLE = BLOCKS.register("decrypting_table", () -> new DecryptingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
	
	//Candy Blocks
	public static final RegistryObject<Block> COTTON_CANDY_GRASS = BLOCKS.register("cotton_candy_grass", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> COOKIE_FLOWER = BLOCKS.register("cookie_flower", () -> new FlowerBlock(() -> MobEffects.SATURATION, 8, BlockBehaviour.Properties.copy(Blocks.OXEYE_DAISY)));
	public static final RegistryObject<Block> WHITE_SUGARGLASS_FLOWER = BLOCKS.register("white_sugarglass_flower", () -> new FlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
	public static final RegistryObject<Block> BLUE_SUGARGLASS_FLOWER = BLOCKS.register("blue_sugarglass_flower", () -> new FlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
	public static final RegistryObject<Block> GREEN_SUGARGLASS_FLOWER = BLOCKS.register("green_sugarglass_flower", () -> new FlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
	public static final RegistryObject<Block> YELLOW_SUGARGLASS_FLOWER = BLOCKS.register("yellow_sugarglass_flower", () -> new FlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
	public static final RegistryObject<Block> RED_SUGARGLASS_FLOWER = BLOCKS.register("red_sugarglass_flower", () -> new FlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 8, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
	public static final RegistryObject<Block> SUGAR_BLOCK = BLOCKS.register("sugar_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final RegistryObject<Block> RED_SUGAR_BLOCK = BLOCKS.register("red_sugar_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final RegistryObject<Block> BLUE_SUGAR_BLOCK = BLOCKS.register("blue_sugar_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final RegistryObject<Block> YELLOW_SUGAR_BLOCK = BLOCKS.register("yellow_sugar_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final RegistryObject<Block> GREEN_SUGAR_BLOCK = BLOCKS.register("green_sugar_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.MUD)));
	public static final RegistryObject<Block> RAW_GINGERBREAD_BLOCK = BLOCKS.register("raw_gingerbread_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.TUFF)));
	public static final RegistryObject<StairBlock> RAW_GINGERBREAD_STAIRS = BLOCKS.register("raw_gingerbread_stairs", () -> new StairBlock(() -> RAW_GINGERBREAD_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(RAW_GINGERBREAD_BLOCK.get())));
	public static final RegistryObject<SlabBlock> RAW_GINGERBREAD_SLAB = BLOCKS.register("raw_gingerbread_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(3.0F)));
	public static final RegistryObject<Block> GINGERBREAD_BLOCK = BLOCKS.register("gingerbread_block", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.TUFF)));
	public static final RegistryObject<StairBlock> GINGERBREAD_STAIRS = BLOCKS.register("gingerbread_stairs", () -> new StairBlock(() -> GINGERBREAD_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(GINGERBREAD_BLOCK.get())));
	public static final RegistryObject<SlabBlock> GINGERBREAD_SLAB = BLOCKS.register("gingerbread_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(4.0F)));
	public static final RegistryObject<Block> RAW_GINGERBREAD_BRICKS = BLOCKS.register("raw_gingerbread_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.TUFF)));
	public static final RegistryObject<StairBlock> RAW_GINGERBREAD_BRICK_STAIRS = BLOCKS.register("raw_gingerbread_brick_stairs", () -> new StairBlock(() -> RAW_GINGERBREAD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(RAW_GINGERBREAD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> RAW_GINGERBREAD_BRICK_SLAB = BLOCKS.register("raw_gingerbread_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(3.0F)));
	public static final RegistryObject<Block> GINGERBREAD_BRICKS = BLOCKS.register("gingerbread_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F).sound(SoundType.TUFF)));
	public static final RegistryObject<StairBlock> GINGERBREAD_BRICK_STAIRS = BLOCKS.register("gingerbread_brick_stairs", () -> new StairBlock(() -> GINGERBREAD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(GINGERBREAD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> GINGERBREAD_BRICK_SLAB = BLOCKS.register("gingerbread_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.TUFF).mapColor(MapColor.COLOR_BROWN).strength(4.0F)));
	public static final RegistryObject<Block> MARSHMALLOW_BLOCK = BLOCKS.register("marshmallow_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.WOOL)));
	public static final RegistryObject<StairBlock> MARSHMALLOW_STAIRS = BLOCKS.register("marshmallow_stairs", () -> new StairBlock(() -> MARSHMALLOW_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(MARSHMALLOW_BLOCK.get())));
	public static final RegistryObject<SlabBlock> MARSHMALLOW_SLAB = BLOCKS.register("marshmallow_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).mapColor(MapColor.TERRACOTTA_WHITE).strength(1.5F)));
	
	//Plants
	public static final RegistryObject<Block> FANTASIA_GRASS = BLOCKS.register("fantasia_grass", () -> new net.minecraft.world.level.block.TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)) {
		@Override
		public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
			DoublePlantBlock doubleplantblock = KoratioBlocks.TALL_FANTASIA_GRASS.get();
			if (doubleplantblock.defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
				DoublePlantBlock.placeAt(level, doubleplantblock.defaultBlockState(), pos, 2);
			}
		}
	});
	public static final RegistryObject<DoublePlantBlock> TALL_FANTASIA_GRASS = BLOCKS.register("tall_fantasia_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
	public static final RegistryObject<Block> AMETHYST_GRASS = BLOCKS.register("amethyst_grass", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> EMERALD_GRASS = BLOCKS.register("emerald_grass", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> RAINBOW_ROSE = BLOCKS.register("rainbow_rose", () -> new FlowerBlock(() -> MobEffects.DIG_SPEED, 8, BlockBehaviour.Properties.copy(Blocks.WITHER_ROSE)));
	public static final RegistryObject<Block> RAINBOW_ALLIUM = BLOCKS.register("rainbow_allium", () -> new FlowerBlock(() -> MobEffects.FIRE_RESISTANCE, 8, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
	public static final RegistryObject<Block> RAINBOW_LILY_OF_THE_VALLEY = BLOCKS.register("rainbow_lily_of_the_valley", () -> new FlowerBlock(() -> MobEffects.LUCK, 8, BlockBehaviour.Properties.copy(Blocks.LILY_OF_THE_VALLEY)));
	public static final RegistryObject<Block> PURPLE_MUSHROOM = BLOCKS.register("purple_mushroom", () -> new MushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(KoratioBlocks::always), KoratioConfiguredFeatures.HUGE_PURPLE_MUSHROOM));
	public static final RegistryObject<Block> PURPLE_MUSHROOM_BLOCK = BLOCKS.register("purple_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> GREEN_MUSHROOM = BLOCKS.register("green_mushroom", () -> new MushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(KoratioBlocks::always), KoratioConfiguredFeatures.HUGE_GREEN_MUSHROOM));
	public static final RegistryObject<Block> GREEN_MUSHROOM_BLOCK = BLOCKS.register("green_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> GILDED_VINES = BLOCKS.register("gilded_vines", () -> new VineBlock(BlockBehaviour.Properties.copy(Blocks.VINE)));
	public static final RegistryObject<Block> GOLDEN_WHEAT = BLOCKS.register("golden_wheat", () -> new CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)) {
		@Override
		protected ItemLike getBaseSeedId() {
			return KoratioItems.GOLDEN_WHEAT_SEEDS.get();
		}
	});
	public static final RegistryObject<Block> GOLDEN_BABY_CARROTS = BLOCKS.register("golden_baby_carrots", () -> new CarrotBlock(BlockBehaviour.Properties.copy(Blocks.CARROTS)) {
		@Override
		protected ItemLike getBaseSeedId() {
			return KoratioItems.GOLDEN_BABY_CARROTS.get();
		}
	});
	public static final RegistryObject<Block> ICE_ROSE = BLOCKS.register("ice_rose", () -> new FlowerBlock(() -> MobEffects.MOVEMENT_SLOWDOWN, 8, BlockBehaviour.Properties.copy(Blocks.WITHER_ROSE)) {
		@Override
		protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
			return state.is(BlockTags.SNOW);
		}
	});
	
	//Potted Plants
	public static final RegistryObject<FlowerPotBlock> POTTED_RAINBOW_ROSE = BLOCKS.register("potted_rainbow_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_ROSE, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_RAINBOW_ALLIUM = BLOCKS.register("potted_rainbow_allium", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_ALLIUM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_RAINBOW_LILY_OF_THE_VALLEY = BLOCKS.register("potted_rainbow_lily_of_the_valley", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RAINBOW_LILY_OF_THE_VALLEY, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_COOKIE_FLOWER = BLOCKS.register("potted_cookie_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, COOKIE_FLOWER, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_PURPLE_MUSHROOM = BLOCKS.register("potted_purple_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PURPLE_MUSHROOM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_GREEN_MUSHROOM = BLOCKS.register("potted_green_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GREEN_MUSHROOM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	
	//Environment

	public static final RegistryObject<Block> AMETHYST_BRICKS = BLOCKS.register("amethyst_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
	public static final RegistryObject<StairBlock> AMETHYST_BRICK_STAIRS = BLOCKS.register("amethyst_brick_stairs", () -> new StairBlock(() -> AMETHYST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(AMETHYST_BRICKS.get())));
	public static final RegistryObject<SlabBlock> AMETHYST_BRICK_SLAB = BLOCKS.register("amethyst_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
	public static final RegistryObject<Block> CHISELED_AMETHYST_BLOCK = BLOCKS.register("chiseled_amethyst_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
	public static final RegistryObject<RotatedPillarBlock> AMETHYST_PILLAR = BLOCKS.register("amethyst_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
	public static final RegistryObject<Block> AMETHYST_FIRE = BLOCKS.register("amethyst_fire", () -> new FireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).replaceable().noCollission().instabreak().lightLevel((p_152605_) -> {
		return 10;
	}).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY), KoratioTags.Blocks.AMETHYST_FIRE_BASE_BLOCKS));
	public static final RegistryObject<Block> AMETHYST_TORCH = BLOCKS.register("amethyst_torch", () -> new TorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel((state) -> {
		return 10;
	}).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), () -> KoratioParticles.AMETHYST_FIRE_FLAME.get()));
	public static final RegistryObject<Block> AMETHYST_WALL_TORCH = BLOCKS.register("amethyst_wall_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel((state) -> {
		return 10;
	}).sound(SoundType.WOOD).lootFrom(() -> AMETHYST_TORCH.get()).pushReaction(PushReaction.DESTROY), () -> KoratioParticles.AMETHYST_FIRE_FLAME.get()));
	public static final RegistryObject<Block> AMETHYST_LANTERN = BLOCKS.register("amethyst_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((state) -> {
		return 10;
	}).noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> AMETHYST_CAMPFIRE = BLOCKS.register("amethyst_campfire", () -> new CampfireBlock(false, 2, BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).lightLevel(litBlockEmission(10)).noOcclusion().ignitedByLava()) {
		@Override
		public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
			return new CampfireBlockEntity(pos, state);
		}
		
		@Override
		@Nullable
		public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {			
			if (level.isClientSide) {
				return state.getValue(LIT) ? createTickerHelper(type, KoratioBlockEntityType.CAMPFIRE.get(), CampfireBlockEntity::particleTick) : null;
			} else {
				return state.getValue(LIT) ? createTickerHelper(type, KoratioBlockEntityType.CAMPFIRE.get(), CampfireBlockEntity::cookTick) : createTickerHelper(type, KoratioBlockEntityType.CAMPFIRE.get(), CampfireBlockEntity::cooldownTick);
			}
		}
	});
	
	public static final RegistryObject<Block> EMERALD_BRICKS = BLOCKS.register("emerald_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
	public static final RegistryObject<StairBlock> EMERALD_BRICK_STAIRS = BLOCKS.register("emerald_brick_stairs", () -> new StairBlock(() -> EMERALD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(EMERALD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> EMERALD_BRICK_SLAB = BLOCKS.register("emerald_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
	public static final RegistryObject<Block> CHISELED_EMERALD_BLOCK = BLOCKS.register("chiseled_emerald_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
	public static final RegistryObject<RotatedPillarBlock> EMERALD_PILLAR = BLOCKS.register("emerald_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
	public static final RegistryObject<Block> EMERALD_FIRE = BLOCKS.register("emerald_fire", () -> new FireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.EMERALD).replaceable().noCollission().instabreak().lightLevel((p_152605_) -> {
		return 10;
	}).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY), KoratioTags.Blocks.EMERALD_FIRE_BASE_BLOCKS));
	public static final RegistryObject<Block> EMERALD_TORCH = BLOCKS.register("emerald_torch", () -> new TorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel((state) -> {
		return 10;
	}).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), () -> KoratioParticles.EMERALD_FIRE_FLAME.get()));
	public static final RegistryObject<Block> EMERALD_WALL_TORCH = BLOCKS.register("emerald_wall_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel((state) -> {
		return 10;
	}).sound(SoundType.WOOD).lootFrom(() -> EMERALD_TORCH.get()).pushReaction(PushReaction.DESTROY), () -> KoratioParticles.EMERALD_FIRE_FLAME.get()));
	public static final RegistryObject<Block> EMERALD_LANTERN = BLOCKS.register("emerald_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((state) -> {
		return 10;
	}).noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> EMERALD_CAMPFIRE = BLOCKS.register("emerald_campfire", () -> new CampfireBlock(false, 2, BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).lightLevel(litBlockEmission(10)).noOcclusion().ignitedByLava()) {
		@Override
		public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
			return new CampfireBlockEntity(pos, state);
		}
		
		@Override
		@Nullable
		public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {			
			if (level.isClientSide) {
				return state.getValue(LIT) ? createTickerHelper(type, KoratioBlockEntityType.CAMPFIRE.get(), CampfireBlockEntity::particleTick) : null;
			} else {
				return state.getValue(LIT) ? createTickerHelper(type, KoratioBlockEntityType.CAMPFIRE.get(), CampfireBlockEntity::cookTick) : createTickerHelper(type, KoratioBlockEntityType.CAMPFIRE.get(), CampfireBlockEntity::cooldownTick);
			}
		}
	});
	
	public static final RegistryObject<Block> SOUL_STONE = BLOCKS.register("soul_stone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 12.0F)));
	public static final RegistryObject<StairBlock> SOUL_STONE_STAIRS = BLOCKS.register("soul_stone_stairs", () -> new StairBlock(() -> SOUL_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SOUL_STONE.get())));
	public static final RegistryObject<SlabBlock> SOUL_STONE_SLAB = BLOCKS.register("soul_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<PressurePlateBlock> SOUL_STONE_PRESSURE_PLATE = BLOCKS.register("soul_stone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().strength(1.0F).pushReaction(PushReaction.DESTROY), SOUL_STONE_SET));
	public static final RegistryObject<ButtonBlock> SOUL_STONE_BUTTON = BLOCKS.register("soul_stone_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(1.0F).pushReaction(PushReaction.DESTROY), SOUL_STONE_SET, 20, false));
	public static final RegistryObject<Block> INFESTED_SOUL_STONE = BLOCKS.register("infested_soul_stone", () -> new InfestedBlock(SOUL_STONE.get(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 12.0F)));
	public static final RegistryObject<StairBlock> INFESTED_SOUL_STONE_STAIRS = BLOCKS.register("infested_soul_stone_stairs", () -> new StairBlock(() -> INFESTED_SOUL_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(INFESTED_SOUL_STONE.get())));
	public static final RegistryObject<SlabBlock> INFESTED_SOUL_STONE_SLAB = BLOCKS.register("infested_soul_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<PressurePlateBlock> INFESTED_SOUL_STONE_PRESSURE_PLATE = BLOCKS.register("infested_soul_stone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().strength(1.0F).pushReaction(PushReaction.DESTROY), SOUL_STONE_SET));
	public static final RegistryObject<ButtonBlock> INFESTED_SOUL_STONE_BUTTON = BLOCKS.register("infested_soul_stone_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(1.0F).pushReaction(PushReaction.DESTROY), SOUL_STONE_SET, 20, false));
	public static final RegistryObject<Block> SOUL_COBBLESTONE = BLOCKS.register("soul_cobblestone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<StairBlock> SOUL_COBBLESTONE_STAIRS = BLOCKS.register("soul_cobblestone_stairs", () -> new StairBlock(() -> SOUL_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SOUL_COBBLESTONE.get())));
	public static final RegistryObject<SlabBlock> SOUL_COBBLESTONE_SLAB = BLOCKS.register("soul_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<WallBlock> SOUL_COBBLESTONE_WALL = BLOCKS.register("soul_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SOUL_COBBLESTONE.get()).forceSolidOn()));
	public static final RegistryObject<Block> INFESTED_SOUL_COBBLESTONE = BLOCKS.register("infested_soul_cobblestone", () -> new InfestedBlock(SOUL_COBBLESTONE.get(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<StairBlock> INFESTED_SOUL_COBBLESTONE_STAIRS = BLOCKS.register("infested_soul_cobblestone_stairs", () -> new StairBlock(() -> INFESTED_SOUL_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(INFESTED_SOUL_COBBLESTONE.get())));
	public static final RegistryObject<SlabBlock> INFESTED_SOUL_COBBLESTONE_SLAB = BLOCKS.register("infested_soul_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<WallBlock> INFESTED_SOUL_COBBLESTONE_WALL = BLOCKS.register("infested_soul_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(INFESTED_SOUL_COBBLESTONE.get()).forceSolidOn()));
	public static final RegistryObject<Block> SMOOTH_SOUL_STONE = BLOCKS.register("smooth_soul_stone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<SlabBlock> SMOOTH_SOUL_STONE_SLAB = BLOCKS.register("smooth_soul_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<Block> SOUL_STONE_BRICKS = BLOCKS.register("soul_stone_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 12.0F)));
	public static final RegistryObject<Block> CRACKED_SOUL_STONE_BRICKS = BLOCKS.register("cracked_soul_stone_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 12.0F)));
	public static final RegistryObject<StairBlock> SOUL_STONE_BRICK_STAIRS = BLOCKS.register("soul_stone_brick_stairs", () -> new StairBlock(() -> SOUL_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SOUL_STONE_BRICKS.get())));
	public static final RegistryObject<SlabBlock> SOUL_STONE_BRICK_SLAB = BLOCKS.register("soul_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<WallBlock> SOUL_STONE_BRICK_WALL = BLOCKS.register("soul_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SOUL_STONE_BRICKS.get()).forceSolidOn()));
	public static final RegistryObject<Block> CHISELED_SOUL_STONE_BRICKS = BLOCKS.register("chiseled_soul_stone_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 12.0F)));
	public static final RegistryObject<Block> INFESTED_SOUL_STONE_BRICKS = BLOCKS.register("infested_soul_stone_bricks", () -> new InfestedBlock(SOUL_STONE_BRICKS.get(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 12.0F)));
	public static final RegistryObject<Block> CRACKED_INFESTED_SOUL_STONE_BRICKS = BLOCKS.register("cracked_infested_soul_stone_bricks", () -> new InfestedBlock(CRACKED_SOUL_STONE_BRICKS.get(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 12.0F)));
	public static final RegistryObject<StairBlock> INFESTED_SOUL_STONE_BRICK_STAIRS = BLOCKS.register("infested_soul_stone_brick_stairs", () -> new StairBlock(() -> INFESTED_SOUL_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(INFESTED_SOUL_STONE_BRICKS.get())));
	public static final RegistryObject<SlabBlock> INFESTED_SOUL_STONE_BRICK_SLAB = BLOCKS.register("infested_soul_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
	public static final RegistryObject<WallBlock> INFESTED_SOUL_STONE_BRICK_WALL = BLOCKS.register("infested_soul_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(INFESTED_SOUL_STONE_BRICKS.get()).forceSolidOn()));
	public static final RegistryObject<Block> CHISELED_INFESTED_SOUL_STONE_BRICKS = BLOCKS.register("chiseled_infested_soul_stone_bricks", () -> new InfestedBlock(CHISELED_SOUL_STONE_BRICKS.get(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 12.0F)));
	
	public static final RegistryObject<RotatedPillarBlock> BLOOD_STAINED_DEEPSLATE = BLOCKS.register("blood_stained_deepslate", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_NYLIUM).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(6.0F, 12.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<Block> BLOOD_STAINED_COBBLED_DEEPSLATE = BLOCKS.register("blood_stained_cobbled_deepslate", () -> new Block(BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE.get()).strength(7.0F, 12.0F)));
	public static final RegistryObject<StairBlock> BLOOD_STAINED_COBBLED_DEEPSLATE_STAIRS = BLOCKS.register("blood_stained_cobbled_deepslate_stairs", () -> new StairBlock(() -> BLOOD_STAINED_COBBLED_DEEPSLATE.get().defaultBlockState(), BlockBehaviour.Properties.copy(BLOOD_STAINED_COBBLED_DEEPSLATE.get())));
	public static final RegistryObject<SlabBlock> BLOOD_STAINED_COBBLED_DEEPSLATE_SLAB = BLOCKS.register("blood_stained_cobbled_deepslate_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLOOD_STAINED_COBBLED_DEEPSLATE.get())));
	public static final RegistryObject<WallBlock> BLOOD_STAINED_COBBLED_DEEPSLATE_WALL = BLOCKS.register("blood_stained_cobbled_deepslate_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BLOOD_STAINED_COBBLED_DEEPSLATE.get()).forceSolidOn()));
	public static final RegistryObject<Block> POLISHED_BLOOD_STAINED_DEEPSLATE = BLOCKS.register("polished_blood_stained_deepslate", () -> new Block(BlockBehaviour.Properties.copy(BLOOD_STAINED_COBBLED_DEEPSLATE.get()).sound(SoundType.POLISHED_DEEPSLATE)));
	public static final RegistryObject<StairBlock> POLISHED_BLOOD_STAINED_DEEPSLATE_STAIRS = BLOCKS.register("polished_blood_stained_deepslate_stairs", () -> new StairBlock(() -> POLISHED_BLOOD_STAINED_DEEPSLATE.get().defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_BLOOD_STAINED_DEEPSLATE.get())));
	public static final RegistryObject<SlabBlock> POLISHED_BLOOD_STAINED_DEEPSLATE_SLAB = BLOCKS.register("polished_blood_stained_deepslate_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_BLOOD_STAINED_DEEPSLATE.get())));
	public static final RegistryObject<WallBlock> POLISHED_BLOOD_STAINED_DEEPSLATE_WALL = BLOCKS.register("polished_blood_stained_deepslate_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(POLISHED_BLOOD_STAINED_DEEPSLATE.get()).forceSolidOn()));
	public static final RegistryObject<Block> BLOOD_STAINED_DEEPSLATE_TILES = BLOCKS.register("blood_stained_deepslate_tiles", () -> new Block(BlockBehaviour.Properties.copy(BLOOD_STAINED_COBBLED_DEEPSLATE.get()).sound(SoundType.DEEPSLATE_TILES)));
	public static final RegistryObject<StairBlock> BLOOD_STAINED_DEEPSLATE_TILE_STAIRS = BLOCKS.register("blood_stained_deepslate_tile_stairs", () -> new StairBlock(() -> BLOOD_STAINED_DEEPSLATE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE_TILES.get())));
	public static final RegistryObject<SlabBlock> BLOOD_STAINED_DEEPSLATE_TILE_SLAB = BLOCKS.register("blood_stained_deepslate_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE_TILES.get())));
	public static final RegistryObject<WallBlock> BLOOD_STAINED_DEEPSLATE_TILE_WALL = BLOCKS.register("blood_stained_deepslate_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE_TILES.get()).forceSolidOn()));
	public static final RegistryObject<Block> BLOOD_STAINED_DEEPSLATE_BRICKS = BLOCKS.register("blood_stained_deepslate_bricks", () -> new Block(BlockBehaviour.Properties.copy(BLOOD_STAINED_COBBLED_DEEPSLATE.get()).sound(SoundType.DEEPSLATE_BRICKS)));
	public static final RegistryObject<StairBlock> BLOOD_STAINED_DEEPSLATE_BRICK_STAIRS = BLOCKS.register("blood_stained_deepslate_brick_stairs", () -> new StairBlock(() -> BLOOD_STAINED_DEEPSLATE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE_BRICKS.get())));
	public static final RegistryObject<SlabBlock> BLOOD_STAINED_DEEPSLATE_BRICK_SLAB = BLOCKS.register("blood_stained_deepslate_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE_BRICKS.get())));
	public static final RegistryObject<WallBlock> BLOOD_STAINED_DEEPSLATE_BRICK_WALL = BLOCKS.register("blood_stained_deepslate_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE_BRICKS.get()).forceSolidOn()));
	public static final RegistryObject<Block> CHISELED_BLOOD_STAINED_DEEPSLATE = BLOCKS.register("chiseled_blood_stained_deepslate", () -> new Block(BlockBehaviour.Properties.copy(BLOOD_STAINED_COBBLED_DEEPSLATE.get()).sound(SoundType.DEEPSLATE_BRICKS)));
	public static final RegistryObject<Block> CRACKED_BLOOD_STAINED_DEEPSLATE_BRICKS = BLOCKS.register("cracked_blood_stained_deepslate_bricks", () -> new Block(BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE_BRICKS.get())));
	public static final RegistryObject<Block> CRACKED_BLOOD_STAINED_DEEPSLATE_TILES = BLOCKS.register("cracked_blood_stained_deepslate_tiles", () -> new Block(BlockBehaviour.Properties.copy(BLOOD_STAINED_DEEPSLATE_TILES.get())));
	
	//Vanilla Variants

	//Pango Wood
	public static final RegistryObject<Block> PANGO_PLANKS = BLOCKS.register("pango_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> PANGO_LOG = BLOCKS.register("pango_log", () -> log(MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_PANGO_LOG = BLOCKS.register("stripped_pango_log", () -> log(MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_PANGO_WOOD = BLOCKS.register("stripped_pango_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> PANGO_WOOD = BLOCKS.register("pango_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> PANGO_LEAVES = BLOCKS.register("pango_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final RegistryObject<SlabBlock> PANGO_SLAB = BLOCKS.register("pango_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StairBlock> PANGO_STAIRS = BLOCKS.register("pango_stairs", () -> new StairBlock(() -> PANGO_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(PANGO_PLANKS.get())));
	public static final RegistryObject<Block> PANGO_SAPLING = BLOCKS.register("pango_sapling", () -> new SaplingBlock(new PangoBushGrower(), BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<FenceBlock> PANGO_FENCE = BLOCKS.register("pango_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StandingSignBlock> PANGO_SIGN = BLOCKS.register("pango_sign", () -> new StandingSignBlock(PANGO, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallSignBlock> PANGO_WALL_SIGN = BLOCKS.register("pango_wall_sign", () -> new WallSignBlock(PANGO, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(PANGO_SIGN)));
	public static final RegistryObject<CeilingHangingSignBlock> PANGO_HANGING_SIGN = BLOCKS.register("pango_hanging_sign",() -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(PANGO_LOG.get().defaultMapColor()).noCollission().strength(1.0F), PANGO));
	public static final RegistryObject<WallHangingSignBlock> PANGO_WALL_HANGING_SIGN = BLOCKS.register("pango_wall_hanging_sign",() -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(PANGO_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(() -> PANGO_HANGING_SIGN.get()), PANGO));
	public static final RegistryObject<ButtonBlock> PANGO_BUTTON = BLOCKS.register("pango_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD), PANGO_SET, 0, false));
	public static final RegistryObject<PressurePlateBlock> PANGO_PRESSURE_PLATE = BLOCKS.register("pango_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), PANGO_SET));
	public static final RegistryObject<DoorBlock> PANGO_DOOR = BLOCKS.register("pango_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), PANGO_SET));
	public static final RegistryObject<TrapDoorBlock> PANGO_TRAPDOOR = BLOCKS.register("pango_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never), PANGO_SET));
	public static final RegistryObject<FenceGateBlock> PANGO_FENCE_GATE = BLOCKS.register("pango_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(PANGO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), PANGO));
	public static final RegistryObject<FlowerPotBlock> POTTED_PANGO_SAPLING = BLOCKS.register("potted_pango_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PANGO_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<net.minecraft.world.level.block.ChestBlock> PANGO_CHEST = BLOCKS.register("pango_chest", () -> new ChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST)));
	
	//Rugona Wood
	public static final RegistryObject<Block> RUGONA_PLANKS = BLOCKS.register("rugona_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> RUGONA_LOG = BLOCKS.register("rugona_log", () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_GREEN));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_RUGONA_LOG = BLOCKS.register("stripped_rugona_log", () -> log(MapColor.COLOR_CYAN, MapColor.COLOR_CYAN));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_RUGONA_WOOD = BLOCKS.register("stripped_rugona_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> RUGONA_WOOD = BLOCKS.register("rugona_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> RUGONA_LEAVES = BLOCKS.register("rugona_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final RegistryObject<SlabBlock> RUGONA_SLAB = BLOCKS.register("rugona_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StairBlock> RUGONA_STAIRS = BLOCKS.register("rugona_stairs", () -> new StairBlock(() -> RUGONA_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(RUGONA_PLANKS.get())));
	public static final RegistryObject<Block> RUGONA_SAPLING = BLOCKS.register("rugona_sapling", () -> new SaplingBlock(new RugonaTreeGrower(), BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<FenceBlock> RUGONA_FENCE = BLOCKS.register("rugona_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StandingSignBlock> RUGONA_SIGN = BLOCKS.register("rugona_sign", () -> new StandingSignBlock(RUGONA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallSignBlock> RUGONA_WALL_SIGN = BLOCKS.register("rugona_wall_sign", () -> new WallSignBlock(RUGONA, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(RUGONA_SIGN)));
	public static final RegistryObject<CeilingHangingSignBlock> RUGONA_HANGING_SIGN = BLOCKS.register("rugona_hanging_sign",() -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(RUGONA_LOG.get().defaultMapColor()).noCollission().strength(1.0F), RUGONA));
	public static final RegistryObject<WallHangingSignBlock> RUGONA_WALL_HANGING_SIGN = BLOCKS.register("rugona_wall_hanging_sign",() -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(RUGONA_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(() -> RUGONA_HANGING_SIGN.get()), RUGONA));
	public static final RegistryObject<ButtonBlock> RUGONA_BUTTON = BLOCKS.register("rugona_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD), RUGONA_SET, 0, false));
	public static final RegistryObject<PressurePlateBlock> RUGONA_PRESSURE_PLATE = BLOCKS.register("rugona_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), RUGONA_SET));
	public static final RegistryObject<DoorBlock> RUGONA_DOOR = BLOCKS.register("rugona_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), RUGONA_SET));
	public static final RegistryObject<TrapDoorBlock> RUGONA_TRAPDOOR = BLOCKS.register("rugona_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never), RUGONA_SET));
	public static final RegistryObject<FenceGateBlock> RUGONA_FENCE_GATE = BLOCKS.register("rugona_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(RUGONA_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), RUGONA));
	public static final RegistryObject<FlowerPotBlock> POTTED_RUGONA_SAPLING = BLOCKS.register("potted_rugona_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RUGONA_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<net.minecraft.world.level.block.ChestBlock> RUGONA_CHEST = BLOCKS.register("rugona_chest", () -> new ChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST)));
	
	//Vareso Wood
	public static final RegistryObject<Block> VARESO_PLANKS = BLOCKS.register("vareso_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> VARESO_LOG = BLOCKS.register("vareso_log", () -> log(MapColor.COLOR_BLACK, MapColor.COLOR_LIGHT_GRAY));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_VARESO_LOG = BLOCKS.register("stripped_vareso_log", () -> log(MapColor.COLOR_LIGHT_GRAY, MapColor.COLOR_LIGHT_GRAY));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_VARESO_WOOD = BLOCKS.register("stripped_vareso_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> VARESO_WOOD = BLOCKS.register("vareso_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> VARESO_LEAVES = BLOCKS.register("vareso_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final RegistryObject<SlabBlock> VARESO_SLAB = BLOCKS.register("vareso_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StairBlock> VARESO_STAIRS = BLOCKS.register("vareso_stairs", () -> new StairBlock(() -> VARESO_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(VARESO_PLANKS.get())));
	public static final RegistryObject<Block> VARESO_SAPLING = BLOCKS.register("vareso_sapling", () -> new SaplingBlock(new VaresoTreeGrower(), BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<FenceBlock> VARESO_FENCE = BLOCKS.register("vareso_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StandingSignBlock> VARESO_SIGN = BLOCKS.register("vareso_sign", () -> new StandingSignBlock(VARESO, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallSignBlock> VARESO_WALL_SIGN = BLOCKS.register("vareso_wall_sign", () -> new WallSignBlock(VARESO, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(VARESO_SIGN)));
	public static final RegistryObject<CeilingHangingSignBlock> VARESO_HANGING_SIGN = BLOCKS.register("vareso_hanging_sign",() -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(VARESO_LOG.get().defaultMapColor()).noCollission().strength(1.0F), VARESO));
	public static final RegistryObject<WallHangingSignBlock> VARESO_WALL_HANGING_SIGN = BLOCKS.register("vareso_wall_hanging_sign",() -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(VARESO_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(() -> VARESO_HANGING_SIGN.get()), VARESO));
	public static final RegistryObject<ButtonBlock> VARESO_BUTTON = BLOCKS.register("vareso_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD), VARESO_SET, 0, false));
	public static final RegistryObject<PressurePlateBlock> VARESO_PRESSURE_PLATE = BLOCKS.register("vareso_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), VARESO_SET));
	public static final RegistryObject<DoorBlock> VARESO_DOOR = BLOCKS.register("vareso_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), VARESO_SET));
	public static final RegistryObject<TrapDoorBlock> VARESO_TRAPDOOR = BLOCKS.register("vareso_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never), VARESO_SET));
	public static final RegistryObject<FenceGateBlock> VARESO_FENCE_GATE = BLOCKS.register("vareso_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(VARESO_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), VARESO));
	public static final RegistryObject<FlowerPotBlock> POTTED_VARESO_SAPLING = BLOCKS.register("potted_vareso_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VARESO_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<net.minecraft.world.level.block.ChestBlock> VARESO_CHEST = BLOCKS.register("vareso_chest", () -> new ChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST)));
	
	//Nighy Wood
	public static final RegistryObject<Block> NIGHY_PLANKS = BLOCKS.register("nighy_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> NIGHY_LOG = BLOCKS.register("nighy_log", () -> log(MapColor.TERRACOTTA_CYAN, MapColor.TERRACOTTA_GREEN));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_NIGHY_LOG = BLOCKS.register("stripped_nighy_log", () -> log(MapColor.TERRACOTTA_LIGHT_BLUE, MapColor.TERRACOTTA_GREEN));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_NIGHY_WOOD = BLOCKS.register("stripped_nighy_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> NIGHY_WOOD = BLOCKS.register("nighy_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> NIGHY_LEAVES = BLOCKS.register("nighy_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(KoratioBlocks::ocelotOrParrot).isSuffocating(KoratioBlocks::never).isViewBlocking(KoratioBlocks::never)));
	public static final RegistryObject<SlabBlock> NIGHY_SLAB = BLOCKS.register("nighy_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StairBlock> NIGHY_STAIRS = BLOCKS.register("nighy_stairs", () -> new StairBlock(() -> NIGHY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(NIGHY_PLANKS.get())));
	public static final RegistryObject<Block> NIGHY_SAPLING = BLOCKS.register("nighy_sapling", () -> new SaplingBlock(new NighyTreeGrower(), BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<FenceBlock> NIGHY_FENCE = BLOCKS.register("nighy_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(NIGHY_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StandingSignBlock> NIGHY_SIGN = BLOCKS.register("nighy_sign", () -> new StandingSignBlock(NIGHY, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallSignBlock> NIGHY_WALL_SIGN = BLOCKS.register("nighy_wall_sign", () -> new WallSignBlock(NIGHY, BlockBehaviour.Properties.of().noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(NIGHY_SIGN)));
	public static final RegistryObject<CeilingHangingSignBlock> NIGHY_HANGING_SIGN = BLOCKS.register("nighy_hanging_sign",() -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(NIGHY_LOG.get().defaultMapColor()).noCollission().strength(1.0F), NIGHY));
	public static final RegistryObject<WallHangingSignBlock> NIGHY_WALL_HANGING_SIGN = BLOCKS.register("nighy_wall_hanging_sign",() -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(NIGHY_LOG.get().defaultMapColor()).noCollission().strength(1.0F).lootFrom(() -> NIGHY_HANGING_SIGN.get()), NIGHY));
	public static final RegistryObject<ButtonBlock> NIGHY_BUTTON = BLOCKS.register("nighy_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD), NIGHY_SET, 0, false));
	public static final RegistryObject<PressurePlateBlock> NIGHY_PRESSURE_PLATE = BLOCKS.register("nighy_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(NIGHY_PLANKS.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), NIGHY_SET));
	public static final RegistryObject<DoorBlock> NIGHY_DOOR = BLOCKS.register("nighy_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(NIGHY_PLANKS.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), NIGHY_SET));
	public static final RegistryObject<TrapDoorBlock> NIGHY_TRAPDOOR = BLOCKS.register("nighy_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(KoratioBlocks::never), NIGHY_SET));
	public static final RegistryObject<FenceGateBlock> NIGHY_FENCE_GATE = BLOCKS.register("nighy_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(NIGHY_PLANKS.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), NIGHY));
	public static final RegistryObject<FlowerPotBlock> POTTED_NIGHY_SAPLING = BLOCKS.register("potted_nighy_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, NIGHY_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<net.minecraft.world.level.block.ChestBlock> NIGHY_CHEST = BLOCKS.register("nighy_chest", () -> new ChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST)));
	
	private static RotatedPillarBlock log(MapColor color, MapColor color2) {
		return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor( (state) -> {
			return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? color : color2;
		}).strength(2.0F).sound(SoundType.WOOD));
	}

	private static Boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
		return (boolean)false;
	}

	@SuppressWarnings("unused")
	private static Boolean always(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
		return (boolean)true;
	}
	
	private static Boolean ocelotOrParrot(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
		return (boolean)(type == EntityType.OCELOT || type == EntityType.PARROT);
	}
	
	private static boolean always(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}
	
	private static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
		return false;
	}
	
	private static ToIntFunction<BlockState> litBlockEmission(int i) {
		return (state) -> {
			return state.getValue(BlockStateProperties.LIT) ? i : 0;
		};
	}
	
	public static void registerWoodTypes() {
		WoodType.register(PANGO);
		WoodType.register(RUGONA);
		WoodType.register(VARESO);
		WoodType.register(NIGHY);
		BlockSetType.register(PANGO_SET);
		BlockSetType.register(RUGONA_SET);
		BlockSetType.register(VARESO_SET);
		BlockSetType.register(NIGHY_SET);
		BlockSetType.register(SOUL_STONE_SET);
	}
	
	public static void registerStrippables() {
		AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
		AxeItem.STRIPPABLES.put(KoratioBlocks.PANGO_LOG.get(), KoratioBlocks.STRIPPED_PANGO_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.PANGO_WOOD.get(), KoratioBlocks.STRIPPED_PANGO_WOOD.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.RUGONA_LOG.get(), KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.RUGONA_WOOD.get(), KoratioBlocks.STRIPPED_RUGONA_WOOD.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.VARESO_LOG.get(), KoratioBlocks.STRIPPED_VARESO_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.VARESO_WOOD.get(), KoratioBlocks.STRIPPED_VARESO_WOOD.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.NIGHY_LOG.get(), KoratioBlocks.STRIPPED_NIGHY_LOG.get());
		AxeItem.STRIPPABLES.put(KoratioBlocks.NIGHY_WOOD.get(), KoratioBlocks.STRIPPED_NIGHY_WOOD.get());
	}
	
	public static void registerPots() {
		FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

		pot.addPlant(PANGO_SAPLING.getId(), POTTED_PANGO_SAPLING);
		pot.addPlant(RUGONA_SAPLING.getId(), POTTED_RUGONA_SAPLING);
		pot.addPlant(VARESO_SAPLING.getId(), POTTED_VARESO_SAPLING);
		pot.addPlant(NIGHY_SAPLING.getId(), POTTED_NIGHY_SAPLING);
		pot.addPlant(RAINBOW_ROSE.getId(), POTTED_RAINBOW_ROSE);
		pot.addPlant(RAINBOW_ALLIUM.getId(), POTTED_RAINBOW_ALLIUM);
		pot.addPlant(RAINBOW_LILY_OF_THE_VALLEY.getId(), POTTED_RAINBOW_LILY_OF_THE_VALLEY);
		pot.addPlant(COOKIE_FLOWER.getId(), POTTED_COOKIE_FLOWER);
		pot.addPlant(PURPLE_MUSHROOM.getId(), POTTED_PURPLE_MUSHROOM);
		pot.addPlant(GREEN_MUSHROOM.getId(), POTTED_GREEN_MUSHROOM);
	}
}