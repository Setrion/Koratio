package net.setrion.koratio.data;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
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
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.loot.CanItemPerformAbility;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.world.level.block.FlippedCropBlock;
import net.setrion.koratio.world.level.block.TallDoorBlock;
import net.setrion.koratio.world.level.block.state.properties.TripleBlockPart;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BlockLootTables extends net.minecraft.data.loot.BlockLootSubProvider {

	private final Set<Block> knownBlocks = new HashSet<>();

	private static final LootItemCondition.Builder HAS_SHEARS = CanItemPerformAbility.canItemPerformAbility(ItemAbilities.SHEARS_DIG);
	private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.25F, 0.3125F, 0.416666680F, 0.5F};

	protected LootItemCondition.Builder hasSilkTouch() {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
		return MatchTool.toolMatches(
				ItemPredicate.Builder.item()
						.withSubPredicate(
								ItemSubPredicates.ENCHANTMENTS,
								ItemEnchantmentsPredicate.enchantments(
										List.of(new EnchantmentPredicate(registrylookup.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1)))
								)
						)
		);
	}

	protected LootItemCondition.Builder doesNotHaveSilkTouch() {
		return hasSilkTouch().invert();
	}

	private LootItemCondition.Builder hasShearsOrSilkTouch() {
		return HAS_SHEARS.or(hasSilkTouch());
	}

	private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
		return hasShearsOrSilkTouch().invert();
	}

	public BlockLootTables(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	protected void add(Block block, LootTable.Builder builder) {
		super.add(block, builder);
		knownBlocks.add(block);
	}
	
	@Override
	protected void generate() {
		HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
		HolderLookup.RegistryLookup<Item> itemRegistryLookup = registries.lookupOrThrow(Registries.ITEM);
		dropOther(KoratioBlocks.FLIPPED_FARMLAND.get(), Blocks.DIRT);
		dropSelf(KoratioBlocks.DECRYPTING_TABLE.get());
		dropSelf(KoratioBlocks.WOODCUTTER.get());
		dropSelf(KoratioBlocks.CANDY_SHAPER.get());
		dropSelf(KoratioBlocks.WHITE_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.ORANGE_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.MAGENTA_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.YELLOW_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.LIME_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.PINK_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.GRAY_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.CYAN_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.PURPLE_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.BLUE_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.BROWN_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.GREEN_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.RED_LEVITATING_WOOL.get());
		dropSelf(KoratioBlocks.BLACK_LEVITATING_WOOL.get());

		dropWhenSilkTouch(KoratioBlocks.SKELETON_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.WITHER_SKELETON_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.STRAY_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.BOGGED_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.DEMONIC_SKELETON_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.ZOMBIE_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.HUSK_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.DROWNED_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.DEMONIC_ZOMBIE_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.ZOMBIE_VILLAGER_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.PHANTOM_REMAINS.get());
		dropWhenSilkTouch(KoratioBlocks.ZOMBIFIED_PIGLIN_REMAINS.get());

		dropSelf(KoratioBlocks.RAINBOW_CRYSTAL_BLOCK.get());
		add(KoratioBlocks.BUDDING_RAINBOW_CRYSTAL.get(), noDrop());
		add(
				KoratioBlocks.RAINBOW_CRYSTAL_CLUSTER.get(),
				block -> createSilkTouchDispatchTable(
						block,
						LootItem.lootTableItem(KoratioItems.RAINBOW_CRYSTAL_SHARD.get())
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
								.apply(ApplyBonusCount.addOreBonusCount(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE)))
								.when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(itemRegistryLookup, ItemTags.CLUSTER_MAX_HARVESTABLES)))
								.otherwise(
                                        applyExplosionDecay(
												block, LootItem.lootTableItem(KoratioItems.RAINBOW_CRYSTAL_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                        )
								)
				)
		);
		dropWhenSilkTouch(KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.get());
		dropWhenSilkTouch(KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD.get());
		dropWhenSilkTouch(KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD.get());

		dropWhenSilkTouch(KoratioBlocks.RAINBOW_CRYSTAL_GLASS.get());
		dropWhenSilkTouch(KoratioBlocks.RAINBOW_CRYSTAL_GLASS_PANE.get());

		add(KoratioBlocks.RAINBOW_FIRE.get(), noDrop());
		dropSelf(KoratioBlocks.RAINBOW_TORCH.get());
		add(KoratioBlocks.RAINBOW_LANTERN.get(), this::createSingleItemTable);
		add(
				KoratioBlocks.RAINBOW_CAMPFIRE.get(),
				block -> createSilkTouchDispatchTable(
						block,
                        applyExplosionCondition(
								block, LootItem.lootTableItem(Items.CHARCOAL).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                        )
				)
		);
		add(KoratioBlocks.RAINBOW_CANDLE.get(), this::createCandleDrops);
		add(KoratioBlocks.RAINBOW_CANDLE_CAKE.get(), createCandleCakeDrops(KoratioBlocks.RAINBOW_CANDLE.get()));

		add(KoratioBlocks.FANTASIA_GRASS.get(), this::createGrassDrops);
		add(KoratioBlocks.TALL_FANTASIA_GRASS.get(), this::createGrassDrops);
		add(KoratioBlocks.COTTON_CANDY_GRASS.get(), this::createGrassDrops);
		add(
				KoratioBlocks.COOKIE_FLOWER.get(),
				block -> createSilkTouchDispatchTable(
						block,
						applyExplosionCondition(
								block, LootItem.lootTableItem(Items.COOKIE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
						)
				)
		);
		add(
				KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get(),
				block -> createSilkTouchDispatchTable(
						block,
						applyExplosionCondition(
								block, LootItem.lootTableItem(Items.SUGAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
						)
				)
		);
		add(
				KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get(),
				block -> createSilkTouchDispatchTable(
						block,
						applyExplosionCondition(
								block, LootItem.lootTableItem(KoratioItems.BLUE_SUGAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
						)
				)
		);
		add(
				KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get(),
				block -> createSilkTouchDispatchTable(
						block,
						applyExplosionCondition(
								block, LootItem.lootTableItem(KoratioItems.GREEN_SUGAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
						)
				)
		);
		add(
				KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get(),
				block -> createSilkTouchDispatchTable(
						block,
						applyExplosionCondition(
								block, LootItem.lootTableItem(KoratioItems.YELLOW_SUGAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
						)
				)
		);
		add(
				KoratioBlocks.RED_SUGARGLASS_FLOWER.get(),
				block -> createSilkTouchDispatchTable(
						block,
						applyExplosionCondition(
								block, LootItem.lootTableItem(KoratioItems.RED_SUGAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
						)
				)
		);

		add(KoratioBlocks.WHITE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, Items.SUGAR, ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.LIGHT_GRAY_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIGHT_GRAY_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.GRAY_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.GRAY_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.BLACK_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BLACK_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.BROWN_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BROWN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.RED_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.RED_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.ORANGE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.ORANGE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.YELLOW_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.YELLOW_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.LIME_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIME_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.GREEN_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.GREEN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.CYAN_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.CYAN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.LIGHT_BLUE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIGHT_BLUE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.BLUE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BLUE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.PURPLE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.PURPLE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.MAGENTA_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.MAGENTA_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.PINK_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.PINK_SUGAR.get(), ConstantValue.exactly(9.0F)));

		add(KoratioBlocks.STICKY_WHITE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, Items.SUGAR, ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_LIGHT_GRAY_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIGHT_GRAY_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_GRAY_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.GRAY_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_BLACK_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BLACK_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_BROWN_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BROWN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_RED_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.RED_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_ORANGE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.ORANGE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_YELLOW_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.YELLOW_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_LIME_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIME_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_GREEN_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.GREEN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_CYAN_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.CYAN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_LIGHT_BLUE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIGHT_BLUE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_BLUE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BLUE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_PURPLE_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.PURPLE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_MAGENTA_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.MAGENTA_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.STICKY_PINK_SUGAR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.PINK_SUGAR.get(), ConstantValue.exactly(9.0F)));

		add(KoratioBlocks.WHITE_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, Items.SUGAR, ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.LIGHT_GRAY_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIGHT_GRAY_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.GRAY_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.GRAY_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.BLACK_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BLACK_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.BROWN_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BROWN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.RED_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.RED_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.ORANGE_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.ORANGE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.YELLOW_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.YELLOW_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.LIME_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIME_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.GREEN_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.GREEN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.CYAN_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.CYAN_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.LIGHT_BLUE_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.LIGHT_BLUE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.BLUE_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.BLUE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.PURPLE_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.PURPLE_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.MAGENTA_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.MAGENTA_SUGAR.get(), ConstantValue.exactly(9.0F)));
		add(KoratioBlocks.PINK_ICING_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, KoratioItems.PINK_SUGAR.get(), ConstantValue.exactly(9.0F)));

		dropOther(KoratioBlocks.MOLTEN_WHITE_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_LIGHT_GRAY_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_GRAY_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_BLACK_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_BROWN_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_RED_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_ORANGE_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_YELLOW_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_LIME_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_GREEN_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_CYAN_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_LIGHT_BLUE_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_BLUE_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_PURPLE_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_MAGENTA_SUGAR_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(KoratioBlocks.MOLTEN_PINK_SUGAR_CAULDRON.get(), Blocks.CAULDRON);

		dropSelf(KoratioBlocks.WHITE_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.LIGHT_GRAY_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.GRAY_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.BLACK_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.BROWN_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.RED_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.ORANGE_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.YELLOW_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.LIME_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.GREEN_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.CYAN_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.LIGHT_BLUE_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.BLUE_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.PURPLE_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.MAGENTA_CANDY_BLOCK.get());
		dropSelf(KoratioBlocks.PINK_CANDY_BLOCK.get());

		dropSelf(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get());
		dropSelf(KoratioBlocks.RAW_GINGERBREAD_STAIRS.get());
		add(KoratioBlocks.RAW_GINGERBREAD_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.GINGERBREAD_BLOCK.get());
		dropSelf(KoratioBlocks.GINGERBREAD_STAIRS.get());
		add(KoratioBlocks.GINGERBREAD_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.RAW_GINGERBREAD_BRICKS.get());
		dropSelf(KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get());
		add(KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.GINGERBREAD_BRICKS.get());
		dropSelf(KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get());
		add(KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get());
		dropSelf(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.get());
		add(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get());
		dropSelf(KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.get());
		add(KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.RAW_GINGERBREAD_WALL.get());
		dropSelf(KoratioBlocks.GINGERBREAD_WALL.get());
		dropSelf(KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.get());
		dropSelf(KoratioBlocks.GINGERBREAD_BRICK_WALL.get());
		dropSelf(KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.get());
		dropSelf(KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.get());

		dropSelf(KoratioBlocks.COOKIE_BLOCK.get());
		dropSelf(KoratioBlocks.COOKIE_BLOCK_STAIRS.get());
		add(KoratioBlocks.COOKIE_BLOCK_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.COOKIE_BLOCK_BUTTON.get());
		dropSelf(KoratioBlocks.COOKIE_BLOCK_PRESSURE_PLATE.get());

		dropSelf(KoratioBlocks.MARSHMALLOW_BLOCK.get());

		add(KoratioBlocks.GILDED_VINES.get(), this::createSilkTouchOnlyTable);

		LootItemCondition.Builder lootitemcondition$ceinanas = LootItemBlockStatePropertyCondition.hasBlockStateProperties(KoratioBlocks.CEINANAS.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FlippedCropBlock.AGE, 7));
		add(
				KoratioBlocks.CEINANAS.get(),
				applyExplosionDecay(
						KoratioBlocks.CEINANAS,
						LootTable.lootTable()
								.withPool(LootPool.lootPool().add(LootItem.lootTableItem(KoratioItems.CEINANA)))
								.withPool(
										LootPool.lootPool()
												.when(lootitemcondition$ceinanas)
												.add(
														LootItem.lootTableItem(KoratioItems.CEINANA)
																.apply(ApplyBonusCount.addBonusBinomialDistributionCount(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
												)
								)
				)
		);
		LootItemCondition.Builder lootitemcondition$upnips = LootItemBlockStatePropertyCondition.hasBlockStateProperties(KoratioBlocks.UPNIPS.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FlippedCropBlock.AGE, 7));
		add(
				KoratioBlocks.UPNIPS.get(),
				applyExplosionDecay(
						KoratioBlocks.UPNIPS,
						LootTable.lootTable()
								.withPool(LootPool.lootPool().add(LootItem.lootTableItem(KoratioItems.UPNIP)))
								.withPool(
										LootPool.lootPool()
												.when(lootitemcondition$upnips)
												.add(
														LootItem.lootTableItem(KoratioItems.UPNIP)
																.apply(ApplyBonusCount.addBonusBinomialDistributionCount(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
												)
								)
				)
		);

		dropSelf(KoratioBlocks.RAINBOW_GEM_BLOCK.get());
		dropSelf(KoratioBlocks.RUBY_BLOCK.get());
		dropSelf(KoratioBlocks.SAPPHIRE_BLOCK.get());
		dropSelf(KoratioBlocks.TOPAZ_BLOCK.get());
		dropSelf(KoratioBlocks.ONYX_BLOCK.get());

		dropOther(KoratioBlocks.MAGIC_PATH.get(), Blocks.DIRT);

		dropSelf(KoratioBlocks.ANCIENT_COBBLESTONE.get());
		dropSelf(KoratioBlocks.ANCIENT_COBBLESTONE_STAIRS.get());
		add(KoratioBlocks.ANCIENT_COBBLESTONE_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.ANCIENT_COBBLESTONE_WALL.get());
		add(KoratioBlocks.ANCIENT_STONE.get(), createSilkTouchDispatchTable(KoratioBlocks.ANCIENT_STONE.get(), applyExplosionCondition(KoratioBlocks.ANCIENT_STONE, LootItem.lootTableItem(KoratioBlocks.ANCIENT_COBBLESTONE.get()))));
		dropSelf(KoratioBlocks.ANCIENT_STONE_STAIRS.get());
		add(KoratioBlocks.ANCIENT_STONE_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.ANCIENT_STONE_BRICKS.get());
		dropSelf(KoratioBlocks.ANCIENT_STONE_BRICK_STAIRS.get());
		add(KoratioBlocks.ANCIENT_STONE_BRICK_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.CRACKED_ANCIENT_STONE_BRICKS.get());
		dropSelf(KoratioBlocks.CHISELED_ANCIENT_STONE_BRICKS.get());
		dropSelf(KoratioBlocks.POLISHED_ANCIENT_STONE.get());
		dropSelf(KoratioBlocks.POLISHED_ANCIENT_STONE_STAIRS.get());
		add(KoratioBlocks.POLISHED_ANCIENT_STONE_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.ANCIENT_STONE_TILES.get());
		dropSelf(KoratioBlocks.ANCIENT_STONE_TILE_STAIRS.get());
		add(KoratioBlocks.ANCIENT_STONE_TILE_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.ANCIENT_STONE_BRICK_WALL.get());
		dropSelf(KoratioBlocks.POLISHED_ANCIENT_STONE_WALL.get());
		dropSelf(KoratioBlocks.ANCIENT_STONE_TILE_WALL.get());
		add(KoratioBlocks.ANCIENT_DOOR_BLOCK.get(), noDrop());
		dropSelf(KoratioBlocks.ANCIENT_FURNACE.get());
		dropSelf(KoratioBlocks.ANCIENT_TELEPORTER.get());

		dropSelf(KoratioBlocks.PANGO_SAPLING.get());
		dropSelf(KoratioBlocks.PANGO_LOG.get());
		dropSelf(KoratioBlocks.PANGO_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_PANGO_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_PANGO_WOOD.get());
		dropSelf(KoratioBlocks.PANGO_PLANKS.get());
		add(KoratioBlocks.PANGO_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.PANGO_STAIRS.get());
		add(KoratioBlocks.PANGO_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.PANGO_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionCondition(block, LootItem.lootTableItem(KoratioItems.RAW_PANGO.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.25F, 0.255555557F, 0.3125F, 0.08333334F, 1.25F)))));
		add(KoratioBlocks.PANGO_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.PANGO_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.PANGO_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		dropSelf(KoratioBlocks.PANGO_FENCE.get());
		dropSelf(KoratioBlocks.PANGO_FENCE_GATE.get());
		add(KoratioBlocks.PANGO_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.PANGO_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		add(KoratioBlocks.TALL_PANGO_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_PANGO_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		dropSelf(KoratioBlocks.PANGO_TRAPDOOR.get());
		dropSelf(KoratioBlocks.PANGO_BUTTON.get());
		dropSelf(KoratioBlocks.PANGO_PRESSURE_PLATE.get());
		dropOther(KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_SIGN.get().asItem());
		dropOther(KoratioBlocks.PANGO_WALL_SIGN.get(), KoratioBlocks.PANGO_SIGN.get().asItem());
		dropOther(KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.PANGO_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(), KoratioBlocks.PANGO_HANGING_SIGN.get().asItem());
		add(KoratioBlocks.PANGO_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.PANGO_CHEST.get()));
		add(KoratioBlocks.TRAPPED_PANGO_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_PANGO_CHEST.get()));
		add(KoratioBlocks.PANGO_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.PANGO_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));

		dropSelf(KoratioBlocks.RUGONA_SAPLING.get());
		dropSelf(KoratioBlocks.RUGONA_LOG.get());
		dropSelf(KoratioBlocks.RUGONA_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_RUGONA_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_RUGONA_WOOD.get());
		dropSelf(KoratioBlocks.RUGONA_PLANKS.get());
		add(KoratioBlocks.RUGONA_SLAB.get(), createSlabItemTable(KoratioBlocks.RUGONA_SLAB.get()));
		dropSelf(KoratioBlocks.RUGONA_STAIRS.get());
		add(KoratioBlocks.RUGONA_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.RUGONA_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
		add(KoratioBlocks.RUGONA_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.RUGONA_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.RUGONA_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		dropSelf(KoratioBlocks.RUGONA_FENCE.get());
		dropSelf(KoratioBlocks.RUGONA_FENCE_GATE.get());
		add(KoratioBlocks.RUGONA_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.RUGONA_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		add(KoratioBlocks.TALL_RUGONA_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_RUGONA_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		dropSelf(KoratioBlocks.RUGONA_TRAPDOOR.get());
		dropSelf(KoratioBlocks.RUGONA_BUTTON.get());
		dropSelf(KoratioBlocks.RUGONA_PRESSURE_PLATE.get());
		dropOther(KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_SIGN.get().asItem());
		dropOther(KoratioBlocks.RUGONA_WALL_SIGN.get(), KoratioBlocks.RUGONA_SIGN.get().asItem());
		dropOther(KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.RUGONA_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(), KoratioBlocks.RUGONA_HANGING_SIGN.get().asItem());
		add(KoratioBlocks.RUGONA_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.RUGONA_CHEST.get()));
		add(KoratioBlocks.TRAPPED_RUGONA_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_RUGONA_CHEST.get()));
		add(KoratioBlocks.RUGONA_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.RUGONA_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));

		dropSelf(KoratioBlocks.VARESO_SAPLING.get());
		dropSelf(KoratioBlocks.VARESO_LOG.get());
		dropSelf(KoratioBlocks.VARESO_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_VARESO_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_VARESO_WOOD.get());
		dropSelf(KoratioBlocks.VARESO_PLANKS.get());
		add(KoratioBlocks.VARESO_SLAB.get(), createSlabItemTable(KoratioBlocks.VARESO_SLAB.get()));
		dropSelf(KoratioBlocks.VARESO_STAIRS.get());
		add(KoratioBlocks.VARESO_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.VARESO_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(doesNotHaveShearsOrSilkTouch()).add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(99)).add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1))));
		add(KoratioBlocks.VARESO_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.VARESO_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.VARESO_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		dropSelf(KoratioBlocks.VARESO_FENCE.get());
		dropSelf(KoratioBlocks.VARESO_FENCE_GATE.get());
		add(KoratioBlocks.VARESO_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.VARESO_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		add(KoratioBlocks.TALL_VARESO_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_VARESO_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		dropSelf(KoratioBlocks.VARESO_TRAPDOOR.get());
		dropSelf(KoratioBlocks.VARESO_BUTTON.get());
		dropSelf(KoratioBlocks.VARESO_PRESSURE_PLATE.get());
		dropOther(KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_SIGN.get().asItem());
		dropOther(KoratioBlocks.VARESO_WALL_SIGN.get(), KoratioBlocks.VARESO_SIGN.get().asItem());
		dropOther(KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.VARESO_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(), KoratioBlocks.VARESO_HANGING_SIGN.get().asItem());
		add(KoratioBlocks.VARESO_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.VARESO_CHEST.get()));
		add(KoratioBlocks.TRAPPED_VARESO_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_VARESO_CHEST.get()));
		add(KoratioBlocks.VARESO_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.VARESO_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));

		dropSelf(KoratioBlocks.CANDY_SAPLING.get());
		dropSelf(KoratioBlocks.CANDY_LOG.get());
		dropSelf(KoratioBlocks.CANDY_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_CANDY_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_CANDY_WOOD.get());
		dropSelf(KoratioBlocks.CANDY_PLANKS.get());
		add(KoratioBlocks.CANDY_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.CANDY_STAIRS.get());
		add(KoratioBlocks.PINK_COTTON_CANDY_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.CANDY_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionCondition(block, LootItem.lootTableItem(KoratioItems.PINK_SUGAR.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.25F, 0.255555557F, 0.3125F, 0.08333334F, 1.25F)))));
		add(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.CANDY_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionCondition(block, LootItem.lootTableItem(KoratioItems.LIGHT_BLUE_SUGAR.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.25F, 0.255555557F, 0.3125F, 0.08333334F, 1.25F)))));
		add(KoratioBlocks.LIME_COTTON_CANDY_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.CANDY_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionCondition(block, LootItem.lootTableItem(KoratioItems.LIME_SUGAR.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.25F, 0.255555557F, 0.3125F, 0.08333334F, 1.25F)))));
		add(KoratioBlocks.YELLOW_COTTON_CANDY_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.CANDY_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionCondition(block, LootItem.lootTableItem(KoratioItems.YELLOW_SUGAR.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.25F, 0.255555557F, 0.3125F, 0.08333334F, 1.25F)))));
		add(KoratioBlocks.PINK_COTTON_CANDY_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.PINK_COTTON_CANDY_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.PINK_COTTON_CANDY_LEAF_PANE, LootItem.lootTableItem(KoratioItems.PINK_SUGAR.get()))));
		add(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAF_PANE, LootItem.lootTableItem(KoratioItems.LIGHT_BLUE_SUGAR.get()))));
		add(KoratioBlocks.LIME_COTTON_CANDY_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.LIME_COTTON_CANDY_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.LIME_COTTON_CANDY_LEAF_PANE, LootItem.lootTableItem(KoratioItems.LIME_SUGAR.get()))));
		add(KoratioBlocks.YELLOW_COTTON_CANDY_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.YELLOW_COTTON_CANDY_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.YELLOW_COTTON_CANDY_LEAF_PANE, LootItem.lootTableItem(KoratioItems.YELLOW_SUGAR.get()))));
		dropSelf(KoratioBlocks.CANDY_FENCE.get());
		dropSelf(KoratioBlocks.CANDY_FENCE_GATE.get());
		add(KoratioBlocks.CANDY_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.CANDY_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		//add(KoratioBlocks.TALL_CANDY_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_CANDY_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		dropSelf(KoratioBlocks.CANDY_TRAPDOOR.get());
		dropSelf(KoratioBlocks.CANDY_BUTTON.get());
		dropSelf(KoratioBlocks.CANDY_PRESSURE_PLATE.get());
		dropOther(KoratioBlocks.CANDY_SIGN.get(), KoratioBlocks.CANDY_SIGN.get().asItem());
		dropOther(KoratioBlocks.CANDY_WALL_SIGN.get(), KoratioBlocks.CANDY_SIGN.get().asItem());
		dropOther(KoratioBlocks.CANDY_HANGING_SIGN.get(), KoratioBlocks.CANDY_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.CANDY_WALL_HANGING_SIGN.get(), KoratioBlocks.CANDY_HANGING_SIGN.get().asItem());
		add(KoratioBlocks.CANDY_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.CANDY_CHEST.get()));
		add(KoratioBlocks.TRAPPED_CANDY_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_CANDY_CHEST.get()));
		add(KoratioBlocks.CANDY_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.CANDY_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));

		dropSelf(KoratioBlocks.CHOCOLATE_OAK_SAPLING.get());
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_LOG.get());
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_CHOCOLATE_OAK_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_CHOCOLATE_OAK_WOOD.get());
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_PLANKS.get());
		add(KoratioBlocks.CHOCOLATE_OAK_SLAB.get(), this::createSlabItemTable);
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_STAIRS.get());
		add(KoratioBlocks.CHOCOLATE_OAK_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.CHOCOLATE_OAK_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionCondition(block, LootItem.lootTableItem(Items.COCOA_BEANS)).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.25F, 0.255555557F, 0.3125F, 0.08333334F, 1.25F)))));
		add(KoratioBlocks.CHOCOLATE_OAK_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.CHOCOLATE_OAK_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.CHOCOLATE_OAK_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_FENCE.get());
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_FENCE_GATE.get());
		add(KoratioBlocks.CHOCOLATE_OAK_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.CHOCOLATE_OAK_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		//add(KoratioBlocks.TALL_CHOCOLATE_OAK_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_CHOCOLATE_OAK_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_TRAPDOOR.get());
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_BUTTON.get());
		dropSelf(KoratioBlocks.CHOCOLATE_OAK_PRESSURE_PLATE.get());
		dropOther(KoratioBlocks.CHOCOLATE_OAK_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_SIGN.get().asItem());
		dropOther(KoratioBlocks.CHOCOLATE_OAK_WALL_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_SIGN.get().asItem());
		dropOther(KoratioBlocks.CHOCOLATE_OAK_HANGING_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.CHOCOLATE_OAK_WALL_HANGING_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_HANGING_SIGN.get().asItem());
		add(KoratioBlocks.CHOCOLATE_OAK_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.CHOCOLATE_OAK_CHEST.get()));
		add(KoratioBlocks.TRAPPED_CHOCOLATE_OAK_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_CHOCOLATE_OAK_CHEST.get()));
		add(KoratioBlocks.CHOCOLATE_OAK_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.CHOCOLATE_OAK_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));

		dropSelf(KoratioBlocks.ELVEN_SAPLING.get());
		dropSelf(KoratioBlocks.ELVEN_LOG.get());
		dropSelf(KoratioBlocks.ELVEN_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_ELVEN_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_ELVEN_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.get());
		dropSelf(KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get());
		dropSelf(KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.get());
		dropSelf(KoratioBlocks.ELVEN_PLANKS.get());
		dropSelf(KoratioBlocks.BLUE_ELVEN_PLANKS.get());
		dropSelf(KoratioBlocks.CYAN_ELVEN_PLANKS.get());
		dropSelf(KoratioBlocks.GREEN_ELVEN_PLANKS.get());
		add(KoratioBlocks.ELVEN_SLAB.get(), createSlabItemTable(KoratioBlocks.ELVEN_SLAB.get()));
		add(KoratioBlocks.BLUE_ELVEN_SLAB.get(), createSlabItemTable(KoratioBlocks.BLUE_ELVEN_SLAB.get()));
		add(KoratioBlocks.CYAN_ELVEN_SLAB.get(), createSlabItemTable(KoratioBlocks.CYAN_ELVEN_SLAB.get()));
		add(KoratioBlocks.GREEN_ELVEN_SLAB.get(), createSlabItemTable(KoratioBlocks.GREEN_ELVEN_SLAB.get()));
		dropSelf(KoratioBlocks.ELVEN_STAIRS.get());
		dropSelf(KoratioBlocks.BLUE_ELVEN_STAIRS.get());
		dropSelf(KoratioBlocks.CYAN_ELVEN_STAIRS.get());
		dropSelf(KoratioBlocks.GREEN_ELVEN_STAIRS.get());
		add(KoratioBlocks.ELVEN_LEAVES.get(), (block) -> createLeavesDrops(block, KoratioBlocks.ELVEN_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
		add(KoratioBlocks.ELVEN_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.ELVEN_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.ELVEN_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		dropSelf(KoratioBlocks.ELVEN_FENCE.get());
		dropSelf(KoratioBlocks.BLUE_ELVEN_FENCE.get());
		dropSelf(KoratioBlocks.CYAN_ELVEN_FENCE.get());
		dropSelf(KoratioBlocks.GREEN_ELVEN_FENCE.get());
		dropSelf(KoratioBlocks.ELVEN_FENCE_GATE.get());
		dropSelf(KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get());
		dropSelf(KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get());
		dropSelf(KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get());
		add(KoratioBlocks.ELVEN_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.ELVEN_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		add(KoratioBlocks.BLUE_ELVEN_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.BLUE_ELVEN_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		add(KoratioBlocks.CYAN_ELVEN_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.CYAN_ELVEN_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		add(KoratioBlocks.GREEN_ELVEN_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.GREEN_ELVEN_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		add(KoratioBlocks.TALL_ELVEN_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_ELVEN_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		add(KoratioBlocks.TALL_BLUE_ELVEN_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_BLUE_ELVEN_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		add(KoratioBlocks.TALL_CYAN_ELVEN_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_CYAN_ELVEN_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		add(KoratioBlocks.TALL_GREEN_ELVEN_DOOR.get(), createSinglePropConditionTable(KoratioBlocks.TALL_GREEN_ELVEN_DOOR.get(), TallDoorBlock.PART, TripleBlockPart.LOWER));
		dropSelf(KoratioBlocks.ELVEN_TRAPDOOR.get());
		dropSelf(KoratioBlocks.BLUE_ELVEN_TRAPDOOR.get());
		dropSelf(KoratioBlocks.CYAN_ELVEN_TRAPDOOR.get());
		dropSelf(KoratioBlocks.GREEN_ELVEN_TRAPDOOR.get());
		dropSelf(KoratioBlocks.ELVEN_BUTTON.get());
		dropSelf(KoratioBlocks.BLUE_ELVEN_BUTTON.get());
		dropSelf(KoratioBlocks.CYAN_ELVEN_BUTTON.get());
		dropSelf(KoratioBlocks.GREEN_ELVEN_BUTTON.get());
		dropSelf(KoratioBlocks.ELVEN_PRESSURE_PLATE.get());
		dropSelf(KoratioBlocks.BLUE_ELVEN_PRESSURE_PLATE.get());
		dropSelf(KoratioBlocks.CYAN_ELVEN_PRESSURE_PLATE.get());
		dropSelf(KoratioBlocks.GREEN_ELVEN_PRESSURE_PLATE.get());
		dropOther(KoratioBlocks.ELVEN_SIGN.get(), KoratioBlocks.ELVEN_SIGN.get().asItem());
		dropOther(KoratioBlocks.BLUE_ELVEN_SIGN.get(), KoratioBlocks.BLUE_ELVEN_SIGN.get().asItem());
		dropOther(KoratioBlocks.CYAN_ELVEN_SIGN.get(), KoratioBlocks.CYAN_ELVEN_SIGN.get().asItem());
		dropOther(KoratioBlocks.GREEN_ELVEN_SIGN.get(), KoratioBlocks.GREEN_ELVEN_SIGN.get().asItem());
		dropOther(KoratioBlocks.ELVEN_WALL_SIGN.get(), KoratioBlocks.ELVEN_SIGN.get().asItem());
		dropOther(KoratioBlocks.BLUE_ELVEN_WALL_SIGN.get(), KoratioBlocks.BLUE_ELVEN_SIGN.get().asItem());
		dropOther(KoratioBlocks.CYAN_ELVEN_WALL_SIGN.get(), KoratioBlocks.CYAN_ELVEN_SIGN.get().asItem());
		dropOther(KoratioBlocks.GREEN_ELVEN_WALL_SIGN.get(), KoratioBlocks.GREEN_ELVEN_SIGN.get().asItem());
		dropOther(KoratioBlocks.ELVEN_HANGING_SIGN.get(), KoratioBlocks.ELVEN_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.ELVEN_WALL_HANGING_SIGN.get(), KoratioBlocks.ELVEN_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get(), KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.BLUE_ELVEN_WALL_HANGING_SIGN.get(), KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.CYAN_ELVEN_WALL_HANGING_SIGN.get(), KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get().asItem());
		dropOther(KoratioBlocks.GREEN_ELVEN_WALL_HANGING_SIGN.get(), KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get().asItem());
		add(KoratioBlocks.ELVEN_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.ELVEN_CHEST.get()));
		add(KoratioBlocks.TRAPPED_ELVEN_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_ELVEN_CHEST.get()));
		add(KoratioBlocks.BLUE_ELVEN_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.BLUE_ELVEN_CHEST.get()));
		add(KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get()));
		add(KoratioBlocks.CYAN_ELVEN_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.CYAN_ELVEN_CHEST.get()));
		add(KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get()));
		add(KoratioBlocks.GREEN_ELVEN_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.GREEN_ELVEN_CHEST.get()));
		add(KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get(), createNameableBlockEntityTable(KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get()));
		add(KoratioBlocks.ELVEN_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.ELVEN_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));
		add(KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));
		add(KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));
		add(KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get(), createSilkTouchDispatchTable(KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get(), LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));

		add(KoratioBlocks.RUBY_ORE.get(), block -> createOreDrop(block, KoratioItems.RUBY.get()));
		add(KoratioBlocks.DEEPSLATE_RUBY_ORE.get(), block -> createOreDrop(block, KoratioItems.RUBY.get()));
		add(KoratioBlocks.SAPPHIRE_ORE.get(), block -> createOreDrop(block, KoratioItems.SAPPHIRE.get()));
		add(KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), block -> createOreDrop(block, KoratioItems.SAPPHIRE.get()));
		add(KoratioBlocks.TOPAZ_ORE.get(), block -> createOreDrop(block, KoratioItems.TOPAZ.get()));
		add(KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get(), block -> createOreDrop(block, KoratioItems.TOPAZ.get()));
		add(KoratioBlocks.ONYX_ORE.get(), block -> createOreDrop(block, KoratioItems.ONYX.get()));
		add(KoratioBlocks.DEEPSLATE_ONYX_ORE.get(), block -> createOreDrop(block, KoratioItems.ONYX.get()));
		add(KoratioBlocks.COOKIE_ORE.get(), (block) -> createOreDrop(block, Items.COOKIE));
		add(KoratioBlocks.DEEPSLATE_COOKIE_ORE.get(), (block) -> createOreDrop(block, Items.COOKIE));
		
		dropSelf(KoratioBlocks.PURPLE_MUSHROOM.get());
		add(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get(), (block) -> createMushroomBlockDrop(block, KoratioBlocks.PURPLE_MUSHROOM.get()));
		dropSelf(KoratioBlocks.GREEN_MUSHROOM.get());
		add(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get(), (block) -> createMushroomBlockDrop(block, KoratioBlocks.GREEN_MUSHROOM.get()));
		dropSelf(KoratioBlocks.RAINBOW_ROSE.get());
		dropSelf(KoratioBlocks.RAINBOW_ALLIUM.get());
		dropSelf(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get());
		dropSelf(KoratioBlocks.COOKIE_FLOWER.get());
		dropSelf(KoratioBlocks.ICE_ROSE.get());
		dropSelf(KoratioBlocks.GOLDEN_TULIP.get());
		add(KoratioBlocks.GOLD_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.LIGHT_GRAY_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.GRAY_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.BLACK_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.BROWN_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.RED_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.ORANGE_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.LIME_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.CYAN_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.LIGHT_BLUE_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.PURPLE_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.MAGENTA_SUGARGLASS_FLOWER.get());
		dropSelf(KoratioBlocks.PINK_SUGARGLASS_FLOWER.get());
		dropPottedContents(KoratioBlocks.POTTED_RAINBOW_ROSE.get());
		dropPottedContents(KoratioBlocks.POTTED_RAINBOW_ALLIUM.get());
		dropPottedContents(KoratioBlocks.POTTED_RAINBOW_LILY_OF_THE_VALLEY.get());
		dropPottedContents(KoratioBlocks.POTTED_COOKIE_FLOWER.get());
		dropPottedContents(KoratioBlocks.POTTED_PANGO_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_RUGONA_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_VARESO_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_CANDY_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_CHOCOLATE_OAK_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_ELVEN_SAPLING.get());
		dropPottedContents(KoratioBlocks.POTTED_PURPLE_MUSHROOM.get());
		dropPottedContents(KoratioBlocks.POTTED_GREEN_MUSHROOM.get());

		add(KoratioBlocks.FANTASIA_PORTAL.get(), noDrop());

		//Vanilla Variants
		add(KoratioBlocks.OAK_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.OAK_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.OAK_LEAF_PANE.get(), LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.SPRUCE_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.SPRUCE_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.SPRUCE_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.BIRCH_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.BIRCH_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.BIRCH_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.JUNGLE_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.JUNGLE_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.JUNGLE_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.ACACIA_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.ACACIA_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.ACACIA_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.DARK_OAK_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.DARK_OAK_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.DARK_OAK_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.MANGROVE_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.MANGROVE_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.MANGROVE_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.AZALEA_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.AZALEA_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.AZALEA_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));
		add(KoratioBlocks.CHERRY_LEAF_PANE.get(), createSilkTouchOrShearsDispatchTable(KoratioBlocks.CHERRY_LEAF_PANE.get(), applyExplosionCondition(KoratioBlocks.CHERRY_LEAF_PANE, LootItem.lootTableItem(Items.AIR))));

		add(KoratioBlocks.TALL_OAK_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_SPRUCE_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_BIRCH_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_JUNGLE_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_ACACIA_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_DARK_OAK_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_MANGROVE_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_CHERRY_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_BAMBOO_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_CRIMSON_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_WARPED_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_IRON_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_COPPER_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_WAXED_COPPER_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get(), this::createTallDoorTable);
		add(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get(), this::createTallDoorTable);

	}
	
	protected LootTable.Builder createMultifaceBlockDrops(Block block) {
		return LootTable.lootTable().withPool(LootPool.lootPool().add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(Direction.values(), (direction) -> SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty(direction), true)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)))));
	}

	protected LootTable.Builder createTallDoorTable(Block doorBlock) {
		return this.createSinglePropConditionTable(doorBlock, TallDoorBlock.PART, TripleBlockPart.LOWER);
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return KoratioBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::value).collect(Collectors.toList());
	}
}