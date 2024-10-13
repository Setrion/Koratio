package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;

public class KoratioSoundEvents {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Koratio.MOD_ID);
	
	public static final DeferredHolder<SoundEvent, SoundEvent> SPIKY_PIG_AMBIENT = createEvent("entity.koratio.spiky_pig.ambient");
	public static final DeferredHolder<SoundEvent, SoundEvent> SPIKY_PIG_DEATH = createEvent("entity.koratio.spiky_pig.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> SPIKY_PIG_HURT = createEvent("entity.koratio.spiky_pig.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> SPIKY_PIG_STEP = createEvent("entity.koratio.spiky_pig.step");
	
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLIZE_AMBIENT = createEvent("entity.koratio.crystallize.ambient");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLIZE_DEATH = createEvent("entity.koratio.crystallize.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLIZE_HURT = createEvent("entity.koratio.crystallize.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLIZE_STEP = createEvent("entity.koratio.crystallize.step");
	
	private static DeferredHolder<SoundEvent, SoundEvent> createEvent(String sound) {
		return SOUNDS.register(sound, () -> SoundEvent.createVariableRangeEvent(Koratio.prefix(sound)));
	}
}