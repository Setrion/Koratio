package net.setrion.koratio.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioSoundEvents;

public class SoundGenerator extends SoundDefinitionsProvider {

	public SoundGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, Koratio.MOD_ID, helper);
	}

	@Override
	public void registerSounds() {
		this.generateExistingSoundWithSubtitle(KoratioSoundEvents.SPIKY_PIG_AMBIENT, SoundEvents.PIG_AMBIENT);
		this.generateExistingSoundWithSubtitle(KoratioSoundEvents.SPIKY_PIG_DEATH, SoundEvents.PIG_DEATH);
		this.generateExistingSoundWithSubtitle(KoratioSoundEvents.SPIKY_PIG_HURT, SoundEvents.PIG_HURT);
		this.makeStepSound(KoratioSoundEvents.SPIKY_PIG_STEP, SoundEvents.PIG_STEP);
		
		this.generateNewSoundWithSubtitle(KoratioSoundEvents.CRYSTALLIZE_AMBIENT, "mob/crystallize/ambient", 1);
		this.generateNewSoundWithSubtitle(KoratioSoundEvents.CRYSTALLIZE_DEATH, "mob/crystallize/death", 1);
		this.generateNewSoundWithSubtitle(KoratioSoundEvents.CRYSTALLIZE_HURT, "mob/crystallize/hurt", 1);
		this.makeStepSound(KoratioSoundEvents.CRYSTALLIZE_STEP, SoundEvents.GLASS_STEP);
	}
	
	//Helpers
	
	public void generateNewSoundWithSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, String baseSoundDirectory, int numberOfSounds) {
		generateNewSound(event, baseSoundDirectory, numberOfSounds, true);
	}

	public void generateNewSound(DeferredHolder<SoundEvent, SoundEvent> event, String baseSoundDirectory, int numberOfSounds, boolean subtitle) {
		SoundDefinition definition = SoundDefinition.definition();
		if (subtitle) {
			String[] splitSoundName = event.getId().getPath().split("\\.", 3);
			definition.subtitle("subtitles.koratio." + splitSoundName[0] + "." + splitSoundName[2]);
		}
		for (int i = 1; i <= numberOfSounds; i++) {
			definition.with(SoundDefinition.Sound.sound(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, baseSoundDirectory + (numberOfSounds > 1 ? i : "")), SoundDefinition.SoundType.SOUND));
		}
		this.add(event, definition);
	}

	public void generateNewSoundMC(DeferredHolder<SoundEvent, SoundEvent> event, String baseSoundDirectory, int numberOfSounds, boolean subtitle) {
		SoundDefinition definition = SoundDefinition.definition();
		if (subtitle) {
			String[] splitSoundName = event.getId().getPath().split("\\.", 3);
			definition.subtitle("subtitles.koratio." + splitSoundName[0] + "." + splitSoundName[2]);
		}
		for (int i = 1; i <= numberOfSounds; i++) {
			definition.with(SoundDefinition.Sound.sound(ResourceLocation.withDefaultNamespace(baseSoundDirectory + (numberOfSounds > 1 ? i : "")), SoundDefinition.SoundType.SOUND));
		}
		this.add(event, definition);
	}

	public void generateExistingSoundWithSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound) {
		this.generateExistingSound(event, referencedSound, true);
	}

	public void generateSoundWithCustomSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, String subtitle) {
		this.add(event, SoundDefinition.definition()
				.subtitle(subtitle)
				.with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT)));
	}

	public void generateExistingSound(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, boolean subtitle) {
		SoundDefinition definition = SoundDefinition.definition();
		if (subtitle) {
			String[] splitSoundName = event.getId().getPath().split("\\.", 3);
			definition.subtitle("subtitles.koratio." + splitSoundName[0] + "." + splitSoundName[2]);
		}
		this.add(event, definition
				.with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT)));
	}

	public void makeStepSound(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound) {
		this.add(event, SoundDefinition.definition()
				.subtitle("subtitles.block.generic.footsteps")
				.with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT)));
	}

	public void makeMusicDisc(DeferredHolder<SoundEvent, SoundEvent> event, String discName) {
		this.add(event, SoundDefinition.definition()
				.with(SoundDefinition.Sound.sound(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "music/" + discName), SoundDefinition.SoundType.SOUND)
						.stream()));
	}

	public void generateParrotSound(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound) {
		SoundDefinition definition = SoundDefinition.definition();
		String[] splitSoundName = event.getId().getPath().split("\\.", 3);
		definition.subtitle("subtitles.koratio." + splitSoundName[0] + "." + splitSoundName[2]);

		this.add(event, definition
				.with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT).pitch(1.8F).volume(0.6F)));
	}
}