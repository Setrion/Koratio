package net.setrion.koratio.registry;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;

public class KoratioSoundEvents {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Koratio.MOD_ID);
	
	public static final RegistryObject<SoundEvent> SPIKY_PIG_AMBIENT = createEvent("entity.koratio.spiky_pig.ambient");
	public static final RegistryObject<SoundEvent> SPIKY_PIG_DEATH = createEvent("entity.koratio.spiky_pig.death");
	public static final RegistryObject<SoundEvent> SPIKY_PIG_HURT = createEvent("entity.koratio.spiky_pig.hurt");
	public static final RegistryObject<SoundEvent> SPIKY_PIG_STEP = createEvent("entity.koratio.spiky_pig.step");
	
	public static final RegistryObject<SoundEvent> CRYSTALLIZE_AMBIENT = createEvent("entity.koratio.crystallize.ambient");
	public static final RegistryObject<SoundEvent> CRYSTALLIZE_DEATH = createEvent("entity.koratio.crystallize.death");
	public static final RegistryObject<SoundEvent> CRYSTALLIZE_HURT = createEvent("entity.koratio.crystallize.hurt");
	public static final RegistryObject<SoundEvent> CRYSTALLIZE_STEP = createEvent("entity.koratio.crystallize.step");
	
	private static RegistryObject<SoundEvent> createEvent(String sound) {
		return SOUNDS.register(sound, () -> SoundEvent.createVariableRangeEvent(Koratio.prefix(sound)));
	}
}