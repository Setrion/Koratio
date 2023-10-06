package net.setrion.koratio.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioEntityType;

public class EntityTagGenerator extends EntityTypeTagsProvider {

	public EntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @org.jetbrains.annotations.Nullable net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
		super(output, provider, Koratio.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(KoratioEntityType.CRYSTALLIZE.get(), KoratioEntityType.ICE_BAT.get());
		tag(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES).add(KoratioEntityType.FIRE_BAT.get());
	}
}