package net.setrion.koratio.registry;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.advancements.critereon.DecryptScrollTrigger;

public class KoratioCriteriaTriggers {

	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Koratio.MOD_ID);

	public static final DeferredHolder<CriterionTrigger<?>, DecryptScrollTrigger> DECRYPTED_SCROLL = TRIGGERS.register("decrypted_scroll", DecryptScrollTrigger::new);

}