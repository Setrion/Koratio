package net.setrion.koratio.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.setrion.koratio.registry.KoratioCriteriaTriggers;

import java.util.Optional;

public class DecryptScrollTrigger extends SimpleCriterionTrigger<DecryptScrollTrigger.TriggerInstance> {

	@Override
	public Codec<DecryptScrollTrigger.TriggerInstance> codec() {
		return DecryptScrollTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, String name, String type) {
		this.trigger(player, (instance) -> {
			return instance.matches(name, type);
		});
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<String> name, Optional<String> type) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<DecryptScrollTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
								EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(DecryptScrollTrigger.TriggerInstance::player),
								Codec.STRING.optionalFieldOf("name").forGetter(DecryptScrollTrigger.TriggerInstance::name),
								Codec.STRING.optionalFieldOf("type").forGetter(TriggerInstance::type)
						)
						.apply(instance, DecryptScrollTrigger.TriggerInstance::new)
		);

		public static Criterion<DecryptScrollTrigger.TriggerInstance> decryptedItem() {
			return KoratioCriteriaTriggers.DECRYPTED_SCROLL.get()
					.createCriterion(new DecryptScrollTrigger.TriggerInstance(Optional.empty(), Optional.empty(), Optional.empty()));
		}

		public boolean matches(String name, String type) {
			if (this.type.isPresent() && !this.type.get().equals(type)) {
				return false;
			} else {
				return this.name.isEmpty() || this.name.get().equals(name);
			}
		}
	}
}