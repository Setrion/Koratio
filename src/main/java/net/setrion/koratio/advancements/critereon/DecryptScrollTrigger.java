package net.setrion.koratio.advancements.critereon;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.setrion.koratio.scroll.Scroll;

public class DecryptScrollTrigger extends SimpleCriterionTrigger<DecryptScrollTrigger.TriggerInstance> {
	static final ResourceLocation ID = new ResourceLocation("decrypted_scroll");

	public ResourceLocation getId() {
		return ID;
	}

	public DecryptScrollTrigger.TriggerInstance createInstance(JsonObject json, ContextAwarePredicate context, DeserializationContext deserialization) {
		String name = json.has("name") ? GsonHelper.getAsString(json, "name") : null;
		Scroll.ScrollType type = json.has("type") ? Scroll.ScrollType.valueOf(GsonHelper.getAsString(json, "type")) : null;
		return new DecryptScrollTrigger.TriggerInstance(context, name, type);
	}

	public void trigger(ServerPlayer player, String name, Scroll.ScrollType type) {
		this.trigger(player, (instance) -> {
			return instance.matches(name, type);
		});
	}

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		@Nullable
		private final String name;
		@Nullable
		private final Scroll.ScrollType type;

		public TriggerInstance(ContextAwarePredicate context, @Nullable String name, @Nullable Scroll.ScrollType type) {
			super(DecryptScrollTrigger.ID, context);
			this.name = name;
			this.type = type;
		}

		public static DecryptScrollTrigger.TriggerInstance decryptedScroll() {
			return new DecryptScrollTrigger.TriggerInstance(ContextAwarePredicate.ANY, (String)null, (Scroll.ScrollType)null);
		}

		public static DecryptScrollTrigger.TriggerInstance decryptedScroll(String name) {
			return new DecryptScrollTrigger.TriggerInstance(ContextAwarePredicate.ANY, name, null);
		}
		
		public static DecryptScrollTrigger.TriggerInstance decryptedScrollOfType(Scroll.ScrollType type) {
			return new DecryptScrollTrigger.TriggerInstance(ContextAwarePredicate.ANY, null, type);
		}
		
		public static DecryptScrollTrigger.TriggerInstance decryptedScrollOfType(String name, Scroll.ScrollType type) {
			return new DecryptScrollTrigger.TriggerInstance(ContextAwarePredicate.ANY, name, type);
		}

		public boolean matches(String name, Scroll.ScrollType type) {
			if (this.type != null && this.type != type) {
				return false;
			} else {
				return this.name == null || this.name.equals(name);
			}
		}

		public JsonObject serializeToJson(SerializationContext context) {
			JsonObject jsonobject = super.serializeToJson(context);
			if (this.name != null) {
				jsonobject.addProperty("name", this.name);
			}

			return jsonobject;
		}
	}
}