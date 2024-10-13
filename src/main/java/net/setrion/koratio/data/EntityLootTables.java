package net.setrion.koratio.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioLootTables;
import net.setrion.koratio.registry.KoratioScrolls;
import net.setrion.koratio.world.level.storage.loot.functions.SetScrollFunction;

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
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_WHITE, flufferLoot(KoratioItems.WHITE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_ORANGE, flufferLoot(KoratioItems.ORANGE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_MAGENTA, flufferLoot(KoratioItems.MAGENTA_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_LIGHT_BLUE, flufferLoot(KoratioItems.LIGHT_BLUE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_YELLOW, flufferLoot(KoratioItems.YELLOW_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_LIME, flufferLoot(KoratioItems.LIME_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_PINK, flufferLoot(KoratioItems.PINK_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_GRAY, flufferLoot(KoratioItems.GRAY_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_LIGHT_GRAY, flufferLoot(KoratioItems.LIGHT_GRAY_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_CYAN, flufferLoot(KoratioItems.CYAN_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_PURPLE, flufferLoot(KoratioItems.PURPLE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_BLUE, flufferLoot(KoratioItems.BLUE_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_BROWN, flufferLoot(KoratioItems.BROWN_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_GREEN, flufferLoot(KoratioItems.GREEN_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_RED, flufferLoot(KoratioItems.RED_LEVITATING_WOOL.get()));
		add(KoratioEntityType.FLUFFER.get(), KoratioLootTables.FLUFFER_BLACK, flufferLoot(KoratioItems.BLACK_LEVITATING_WOOL.get()));
	}
	
	@SuppressWarnings("unused")
	private static LootTable.Builder flufferLoot(ItemLike wool) {
		return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(wool))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(NestedLootTable.lootTableReference(KoratioEntityType.FLUFFER.get().getDefaultLootTable())));
	}
	
	@Override
	protected java.util.stream.Stream<EntityType<?>> getKnownEntityTypes() {
		return knownTypes.stream();//KoratioEntityType.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::value);
	}
}