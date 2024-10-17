package net.setrion.koratio.world.level.storage.loot.functions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.setrion.koratio.registry.KoratioLootItemFunctions;
import net.setrion.koratio.scroll.ScrollUtils;

import java.util.List;

public class SetScrollFunction extends LootItemConditionalFunction {

	public static final MapCodec<SetScrollFunction> CODEC = RecordCodecBuilder.mapCodec(
			instance -> commonFields(instance)
					.and(Codec.STRING.fieldOf("scroll").forGetter(function -> function.scroll))
					.and(Codec.BOOL.fieldOf("encrypted").forGetter(function -> function.encrypted))
					.apply(instance, SetScrollFunction::new)
	);
	final String scroll;
	final boolean encrypted;

	public SetScrollFunction(List<LootItemCondition> conditions, String scroll, boolean encrypted) {
		super(conditions);
		this.scroll = scroll;
		this.encrypted = encrypted;
	}

	@Override
	public LootItemFunctionType getType() {
		return KoratioLootItemFunctions.SET_SCROLL.get();
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		return ScrollUtils.addScrollToStack(stack, ScrollUtils.getScrollByName(scroll), encrypted);
	}
	
	public static LootItemConditionalFunction.Builder<?> setScroll(String scroll, boolean encrypted) {
		return simpleBuilder(condition -> {
			return new SetScrollFunction(condition, scroll, encrypted);
		});
	}
}