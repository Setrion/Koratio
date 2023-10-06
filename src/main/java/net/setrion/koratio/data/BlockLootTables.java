package net.setrion.koratio.data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;

public class BlockLootTables extends net.minecraft.data.loot.BlockLootSubProvider {

	private final Set<Block> knownBlocks = new HashSet<>();

	private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
	@SuppressWarnings("unused")
	private static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
	private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
	private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
	private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
	private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.25F, 0.3125F, 0.416666680F, 0.5F};
	
	public BlockLootTables() {
		super(Stream.of(Items.AIR).map(ItemLike::asItem).collect(Collectors.toSet()), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void add(Block block, LootTable.Builder builder) {
		super.add(block, builder);
		knownBlocks.add(block);
	}
	
	@Override
	protected void generate() {
		dropSelf(KoratioBlocks.RAINBOW_GEM_BLOCK.get());
		dropSelf(KoratioBlocks.SUGAR_BLOCK.get());
		dropSelf(KoratioBlocks.RED_SUGAR_BLOCK.get());
		dropSelf(KoratioBlocks.BLUE_SUGAR_BLOCK.get());
		dropSelf(KoratioBlocks.YELLOW_SUGAR_BLOCK.get());
		dropSelf(KoratioBlocks.GREEN_SUGAR_BLOCK.get());
		dropSelf(KoratioBlocks.PANGO_SAPLING.get());
		dropSelf(KoratioBlocks.PANGO_LOG.get());
		dropSelf(KoratioBlocks.PANGO_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_PANGO_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_PANGO_WOOD.get());
		dropSelf(KoratioBlocks.PANGO_PLANKS.get());
		add(KoratioBlocks.PANGO_SLAB.get(), createSlabItemTable(KoratioBlocks.PANGO_SLAB.get()));
		dropSelf(KoratioBlocks.PANGO_STAIRS.get());
		add(KoratioBlocks.PANGO_LEAVES.get(), (block) -> {
			return createLeavesDrops(block, KoratioBlocks.PANGO_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionCondition(block, LootItem.lootTableItem(KoratioItems.RAW_PANGO.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.25F, 0.255555557F, 0.3125F, 0.08333334F, 1.25F))));
		});
		dropSelf(KoratioBlocks.PANGO_FENCE.get());
		dropSelf(KoratioBlocks.PANGO_FENCE_GATE.get());
		add(KoratioBlocks.PANGO_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.PANGO_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(KoratioBlocks.PANGO_TRAPDOOR.get());
		dropSelf(KoratioBlocks.PANGO_BUTTON.get());
		dropSelf(KoratioBlocks.PANGO_PRESSURE_PLATE.get());
		add(KoratioBlocks.PANGO_SIGN.get(), createSingleItemTable(KoratioBlocks.PANGO_SIGN.get().asItem()));
		add(KoratioBlocks.PANGO_WALL_SIGN.get(), createSingleItemTable(KoratioBlocks.PANGO_SIGN.get().asItem()));
		add(KoratioBlocks.PANGO_HANGING_SIGN.get(), createSingleItemTable(KoratioBlocks.PANGO_HANGING_SIGN.get().asItem()));
		add(KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(), createSingleItemTable(KoratioBlocks.PANGO_HANGING_SIGN.get().asItem()));
		add(KoratioBlocks.PANGO_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.PANGO_CHEST.get()));
		dropSelf(KoratioBlocks.RUGONA_SAPLING.get());
		dropSelf(KoratioBlocks.RUGONA_LOG.get());
		dropSelf(KoratioBlocks.RUGONA_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_RUGONA_WOOD.get());
		dropSelf(KoratioBlocks.RUGONA_PLANKS.get());
		add(KoratioBlocks.RUGONA_SLAB.get(), createSlabItemTable(KoratioBlocks.RUGONA_SLAB.get()));
		dropSelf(KoratioBlocks.RUGONA_STAIRS.get());
		add(KoratioBlocks.RUGONA_LEAVES.get(), (block) -> {
			return createLeavesDrops(block, KoratioBlocks.RUGONA_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
		});
		dropSelf(KoratioBlocks.RUGONA_FENCE.get());
		dropSelf(KoratioBlocks.RUGONA_FENCE_GATE.get());
		add(KoratioBlocks.RUGONA_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.RUGONA_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(KoratioBlocks.RUGONA_TRAPDOOR.get());
		dropSelf(KoratioBlocks.RUGONA_BUTTON.get());
		dropSelf(KoratioBlocks.RUGONA_PRESSURE_PLATE.get());
		add(KoratioBlocks.RUGONA_SIGN.get(), createSingleItemTable(KoratioBlocks.RUGONA_SIGN.get().asItem()));
		add(KoratioBlocks.RUGONA_WALL_SIGN.get(), createSingleItemTable(KoratioBlocks.RUGONA_SIGN.get().asItem()));
		add(KoratioBlocks.RUGONA_HANGING_SIGN.get(), createSingleItemTable(KoratioBlocks.RUGONA_HANGING_SIGN.get().asItem()));
		add(KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(), createSingleItemTable(KoratioBlocks.RUGONA_HANGING_SIGN.get().asItem()));
		add(KoratioBlocks.RUGONA_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.RUGONA_CHEST.get()));
		dropSelf(KoratioBlocks.VARESO_SAPLING.get());
		dropSelf(KoratioBlocks.VARESO_LOG.get());
		dropSelf(KoratioBlocks.VARESO_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_VARESO_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_VARESO_WOOD.get());
		dropSelf(KoratioBlocks.VARESO_PLANKS.get());
		add(KoratioBlocks.VARESO_SLAB.get(), createSlabItemTable(KoratioBlocks.VARESO_SLAB.get()));
		dropSelf(KoratioBlocks.VARESO_STAIRS.get());
		add(KoratioBlocks.VARESO_LEAVES.get(), (block) -> {
			return createLeavesDrops(block, KoratioBlocks.VARESO_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(99)).add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1)));
		});
		dropSelf(KoratioBlocks.VARESO_FENCE.get());
		dropSelf(KoratioBlocks.VARESO_FENCE_GATE.get());
		add(KoratioBlocks.VARESO_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.VARESO_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(KoratioBlocks.VARESO_TRAPDOOR.get());
		dropSelf(KoratioBlocks.VARESO_BUTTON.get());
		dropSelf(KoratioBlocks.VARESO_PRESSURE_PLATE.get());
		add(KoratioBlocks.VARESO_SIGN.get(), createSingleItemTable(KoratioBlocks.VARESO_SIGN.get().asItem()));
		add(KoratioBlocks.VARESO_WALL_SIGN.get(), createSingleItemTable(KoratioBlocks.VARESO_SIGN.get().asItem()));
		add(KoratioBlocks.VARESO_HANGING_SIGN.get(), createSingleItemTable(KoratioBlocks.VARESO_HANGING_SIGN.get().asItem()));
		add(KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(), createSingleItemTable(KoratioBlocks.VARESO_HANGING_SIGN.get().asItem()));
		add(KoratioBlocks.VARESO_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.VARESO_CHEST.get()));
		dropSelf(KoratioBlocks.NIGHY_PLANKS.get());
		add(KoratioBlocks.NIGHY_SLAB.get(), createSlabItemTable(KoratioBlocks.NIGHY_SLAB.get()));
		dropSelf(KoratioBlocks.NIGHY_STAIRS.get());
		add(KoratioBlocks.NIGHY_LEAVES.get(), (block) -> {
			return createLeavesDrops(block, KoratioBlocks.NIGHY_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
		});
		dropSelf(KoratioBlocks.NIGHY_FENCE.get());
		dropSelf(KoratioBlocks.NIGHY_FENCE_GATE.get());
		add(KoratioBlocks.NIGHY_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.NIGHY_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(KoratioBlocks.NIGHY_TRAPDOOR.get());
		dropSelf(KoratioBlocks.NIGHY_BUTTON.get());
		dropSelf(KoratioBlocks.NIGHY_PRESSURE_PLATE.get());
		add(KoratioBlocks.NIGHY_SIGN.get(), createSingleItemTable(KoratioBlocks.NIGHY_SIGN.get().asItem()));
		add(KoratioBlocks.NIGHY_WALL_SIGN.get(), createSingleItemTable(KoratioBlocks.NIGHY_SIGN.get().asItem()));
		add(KoratioBlocks.NIGHY_HANGING_SIGN.get(), createSingleItemTable(KoratioBlocks.NIGHY_HANGING_SIGN.get().asItem()));
		add(KoratioBlocks.NIGHY_WALL_HANGING_SIGN.get(), createSingleItemTable(KoratioBlocks.NIGHY_HANGING_SIGN.get().asItem()));
		add(KoratioBlocks.NIGHY_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.NIGHY_CHEST.get()));
		
		this.add(KoratioBlocks.ARSOY_ORE.get(), (block) -> {
			return this.createOreDrop(block, KoratioItems.RAW_ARSOY.get());
		});
		this.add(KoratioBlocks.DEEPSLATE_ARSOY_ORE.get(), (block) -> {
			return this.createOreDrop(block, KoratioItems.RAW_ARSOY.get());
		});
		this.add(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_ARSOY_ORE.get(), (block) -> {
			return this.createOreDrop(block, KoratioItems.RAW_ARSOY.get());
		});
		dropSelf(KoratioBlocks.RAW_ARSOY_BLOCK.get());
		dropSelf(KoratioBlocks.ARSOY_BLOCK.get());
		
		this.add(KoratioBlocks.COOKIE_ORE.get(), (block) -> {
			return this.createOreDrop(block, Items.COOKIE);
		});
		this.add(KoratioBlocks.DEEPSLATE_COOKIE_ORE.get(), (block) -> {
			return this.createOreDrop(block, Items.COOKIE);
		});
		this.add(KoratioBlocks.BLOOD_STAINED_DEEPSLATE_COOKIE_ORE.get(), (block) -> {
			return this.createOreDrop(block, Items.COOKIE);
		});
		
		dropSelf(KoratioBlocks.PURPLE_MUSHROOM.get());
		add(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get(), (block) -> {
			return createMushroomBlockDrop(block, KoratioBlocks.PURPLE_MUSHROOM.get());
		});
		dropSelf(KoratioBlocks.GREEN_MUSHROOM.get());
		add(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get(), (block) -> {
			return createMushroomBlockDrop(block, KoratioBlocks.GREEN_MUSHROOM.get());
		});
		dropSelf(KoratioBlocks.RAINBOW_ROSE.get());
		dropSelf(KoratioBlocks.RAINBOW_ALLIUM.get());
		dropSelf(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get());
		dropSelf(KoratioBlocks.COOKIE_FLOWER.get());
		dropSelf(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.RED_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.GOLDEN_HAY_BLOCK.get());
		LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(KoratioBlocks.GOLDEN_WHEAT.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
		this.add(KoratioBlocks.GOLDEN_WHEAT.get(), this.createCropDrops(KoratioBlocks.GOLDEN_WHEAT.get(), KoratioItems.GOLDEN_WHEAT.get(), KoratioItems.GOLDEN_WHEAT_SEEDS.get(), lootitemcondition$builder1));
		LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(KoratioBlocks.GOLDEN_BABY_CARROTS.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 7));
		this.add(KoratioBlocks.GOLDEN_BABY_CARROTS.get(), this.applyExplosionDecay(KoratioBlocks.GOLDEN_BABY_CARROTS.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(KoratioItems.GOLDEN_BABY_CARROTS.get()))).withPool(LootPool.lootPool().when(lootitemcondition$builder2).add(LootItem.lootTableItem(KoratioItems.GOLDEN_BABY_CARROTS.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));
		dropPottedContents(KoratioBlocks.POTTED_RAINBOW_ROSE.get());
		dropPottedContents(KoratioBlocks.POTTED_RAINBOW_ALLIUM.get());
		dropPottedContents(KoratioBlocks.POTTED_RAINBOW_LILY_OF_THE_VALLEY.get());
		dropPottedContents(KoratioBlocks.POTTED_COOKIE_FLOWER.get());
		dropPottedContents(KoratioBlocks.POTTED_PANGO_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_RUGONA_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_VARESO_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_PURPLE_MUSHROOM.get());
		dropPottedContents(KoratioBlocks.POTTED_GREEN_MUSHROOM.get());
	}
	
	protected LootTable.Builder createMultifaceBlockDrops(Block block) {
		return LootTable.lootTable().withPool(LootPool.lootPool().add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(Direction.values(), (p_251536_) -> {
			return SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty(p_251536_), true)));
		}).apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)))));
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return knownBlocks;
	}
}