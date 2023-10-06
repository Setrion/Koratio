package net.setrion.koratio.data;

import java.util.List;
import java.util.Map;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class LootGenerator extends LootTableProvider {
	public LootGenerator(PackOutput output) {
		super(output, BuiltInLootTables.all(), List.of(
				new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK), 
				new LootTableProvider.SubProviderEntry(ChestLootTables::new, LootContextParamSets.CHEST), 
				new LootTableProvider.SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY)));
	}
	
	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {

	}
}