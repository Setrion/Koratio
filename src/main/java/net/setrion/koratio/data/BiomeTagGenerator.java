package net.setrion.koratio.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.registry.KoratioTags;

public class BiomeTagGenerator extends BiomeTagsProvider {

	public BiomeTagGenerator(PackOutput pack, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
		super(pack, provider, Koratio.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(KoratioTags.Biomes.FANTASIA_BIOMES).add(
				KoratioBiomes.FANTASIA_FIELDLANDS, KoratioBiomes.AMETHYST_FIELDS, KoratioBiomes.MUSHROOM_FOREST, KoratioBiomes.CANDY_CANE_VALLEY, KoratioBiomes.GILDED_FOREST,
				KoratioBiomes.DEPTHS_OF_FANTASIA
			);
		tag(KoratioTags.Biomes.DEMONICIO_BIOMES).add(
				KoratioBiomes.NIGHTMARE_FOREST
			);
		tag(KoratioTags.Biomes.FOGGY_BIOMES).add(KoratioBiomes.MUSHROOM_FOREST);
	}
}