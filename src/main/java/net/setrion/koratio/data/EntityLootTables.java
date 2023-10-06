package net.setrion.koratio.data;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioScrolls;
import net.setrion.koratio.world.level.storage.loot.functions.SetScrollFunction;

public class EntityLootTables extends EntityLootSubProvider {

	protected EntityLootTables() {
		super(FeatureFlags.REGISTRY.allFlags());
	}

	private final Set<EntityType<?>> knownTypes = new HashSet<>();

	@Override
	protected void add(EntityType<?> type, LootTable.Builder builder) {
		super.add(type, builder);
		knownTypes.add(type);
	}

	@Override
	protected void add(EntityType<?> type, ResourceLocation location, LootTable.Builder builder) {
		super.add(type, location, builder);
		knownTypes.add(type);
	}
	
	@Override
	public void generate() {
		add(KoratioEntityType.SPIKY_PIG.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(KoratioItems.SPIKED_PORKCHOP.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));
		add(KoratioEntityType.CRYSTALLIZE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(KoratioItems.SCROLL.get()).apply(SetScrollFunction.setScroll(KoratioScrolls.DEMONS, false)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))));
	}
	
	@SuppressWarnings("unused")
	private static LootTable.Builder ceepLoot(ItemLike wool) {
		return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(wool)).apply(null)).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootTableReference.lootTableReference(EntityType.SHEEP.getDefaultLootTable())));
	}
	
	@Override
	protected java.util.stream.Stream<EntityType<?>> getKnownEntityTypes() {
		return knownTypes.stream();
	}
}