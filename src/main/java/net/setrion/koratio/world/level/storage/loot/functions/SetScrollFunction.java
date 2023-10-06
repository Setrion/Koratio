package net.setrion.koratio.world.level.storage.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.setrion.koratio.registry.KoratioLootItemFunctions;
import net.setrion.koratio.scroll.Scroll;
import net.setrion.koratio.scroll.ScrollUtils;

public class SetScrollFunction extends LootItemConditionalFunction {
	
	final Scroll scroll;
	final boolean encrypted;

	public SetScrollFunction(LootItemCondition[] conditions, Scroll scroll, boolean encrypted) {
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
		return ScrollUtils.addScrollToStack(stack, scroll, encrypted);
	}
	
	public static LootItemConditionalFunction.Builder<?> setScroll(Scroll scroll, boolean encrypted) {
		return simpleBuilder((condition) -> {
			return new SetScrollFunction(condition, scroll, encrypted);
		});
	}
	
	public static class Serializer extends LootItemConditionalFunction.Serializer<SetScrollFunction> {
		public void serialize(JsonObject json, SetScrollFunction function, JsonSerializationContext context) {
			super.serialize(json, function, context);
			json.addProperty("name", function.scroll.getName());
			json.addProperty("encrypted", function.encrypted);
		}

		public SetScrollFunction deserialize(JsonObject json, JsonDeserializationContext context, LootItemCondition[] conditions) {
			String name = GsonHelper.getAsString(json, "name");
			boolean encrypted = GsonHelper.getAsBoolean(json, "encrypted");
			return new SetScrollFunction(conditions, ScrollUtils.getScrollByName(name), encrypted);

		}
	}
}