package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.storage.loot.functions.SetScrollFunction;

public class KoratioLootItemFunctions {
	
	public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, Koratio.MOD_ID);
	
	public static final DeferredHolder<LootItemFunctionType<?>, LootItemFunctionType<?>> SET_SCROLL = LOOT_FUNCTION_TYPES.register("set_scroll", () -> new LootItemFunctionType<>(SetScrollFunction.CODEC));
	
}