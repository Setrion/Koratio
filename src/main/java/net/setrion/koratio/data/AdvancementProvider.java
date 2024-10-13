package net.setrion.koratio.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AdvancementProvider extends net.neoforged.neoforge.common.data.AdvancementProvider {
	
	public AdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
		super(output, registries, existingFileHelper, List.of(new net.setrion.koratio.data.AdvancementGenerator()));
	}
}