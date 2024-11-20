package net.setrion.koratio.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioLootTables;

import java.util.HashSet;
import java.util.Set;

public class EntityLootTables extends EntityLootSubProvider {

	protected EntityLootTables(HolderLookup.Provider registries) {
		super(FeatureFlags.REGISTRY.allFlags(), registries);
	}

	private final Set<EntityType<?>> knownTypes = new HashSet<>();

	@Override
	protected void add(EntityType<?> type, LootTable.Builder builder) {
		super.add(type, builder);
		knownTypes.add(type);
	}

	protected void add(EntityType<?> type, ResourceKey<LootTable> key, LootTable.Builder builder) {
		super.add(type, key, builder);
		knownTypes.add(type);
	}
	
	@Override
	public void generate() {
		add(KoratioEntityType.MAGICAL_CAT.get(), LootTable.lootTable());
		add(KoratioEntityType.SPIKY_PIG.get(), LootTable.lootTable());
		add(KoratioEntityType.JUMSTEM.get(), LootTable.lootTable());
		add(KoratioEntityType.CRYSTALLIZE.get(), LootTable.lootTable());
		add(KoratioEntityType.GOLDEN_FOX_SPIRIT.get(), LootTable.lootTable());
		add(KoratioEntityType.DEMONIC_ZOMBIE.get(), LootTable.lootTable());
		add(KoratioEntityType.DEMONIC_SKELETON.get(), LootTable.lootTable());
		add(KoratioEntityType.DEMONIC_SOLDIER.get(), LootTable.lootTable());

		this.add(
				KoratioEntityType.FLUFFER.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ConstantValue.exactly(1.0F))
										.add(
												LootItem.lootTableItem(KoratioItems.FLUFFER.get())
														.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
														.apply(SmeltItemFunction.smelted().when(shouldSmeltLoot()))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))
										)
						)
		);
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_WHITE, flufferLoot(KoratioBlocks.WHITE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_ORANGE, flufferLoot(KoratioBlocks.ORANGE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_MAGENTA, flufferLoot(KoratioBlocks.MAGENTA_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_LIGHT_BLUE, flufferLoot(KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_YELLOW, flufferLoot(KoratioBlocks.YELLOW_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_LIME, flufferLoot(KoratioBlocks.LIME_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_PINK, flufferLoot(KoratioBlocks.PINK_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_GRAY, flufferLoot(KoratioBlocks.GRAY_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_LIGHT_GRAY, flufferLoot(KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_CYAN, flufferLoot(KoratioBlocks.CYAN_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_PURPLE, flufferLoot(KoratioBlocks.PURPLE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_BLUE, flufferLoot(KoratioBlocks.BLUE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_BROWN, flufferLoot(KoratioBlocks.BROWN_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_GREEN, flufferLoot(KoratioBlocks.GREEN_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_RED, flufferLoot(KoratioBlocks.RED_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_BLACK, flufferLoot(KoratioBlocks.BLACK_LEVITATING_WOOL.get()));
	}
	
	@SuppressWarnings("unused")
	private static LootTable.Builder flufferLoot(ItemLike wool) {
		return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(wool))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(NestedLootTable.lootTableReference(KoratioEntityType.FLUFFER.get().getDefaultLootTable().orElseThrow())));
	}
	
	@Override
	protected java.util.stream.Stream<EntityType<?>> getKnownEntityTypes() {
		return knownTypes.stream();//KoratioEntityType.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::value);
	}
}