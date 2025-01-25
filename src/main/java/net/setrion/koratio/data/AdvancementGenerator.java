package net.setrion.koratio.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.advancements.critereon.DecryptScrollTrigger;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioScrolls;
import net.setrion.koratio.registry.KoratioStructures;
import net.setrion.koratio.scroll.ScrollUtils;

import java.util.function.Consumer;

public class AdvancementGenerator implements AdvancementSubProvider {

	public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer) {
		HolderGetter<Structure> structures = registries.lookupOrThrow(Registries.STRUCTURE);

		AdvancementHolder koratio_root = Advancement.Builder.advancement()
				.display(
						new ItemStack(KoratioBlocks.DECRYPTING_TABLE.get()),
						Component.translatable("advancements.koratio.root.title"),
						Component.translatable("advancements.koratio.root.desc"),
						ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/block/pango_planks.png"),
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("outcast", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(KoratioStructures.OUTCAST))))
				.save(consumer, "koratio:koratio/root");

		AdvancementHolder decrypting = Advancement.Builder.advancement().parent(koratio_root).display(ScrollUtils.createForScroll(KoratioScrolls.A_NEW_WORLD, true),
				Component.translatable("advancements.koratio.decrypting.title"),
				Component.translatable("advancements.koratio.decrypting.desc"),
				null,
				AdvancementType.TASK,
				true, true, false)
				.requirements(AdvancementRequirements.Strategy.OR)
				.addCriterion("decrypted_scroll", DecryptScrollTrigger.TriggerInstance.decryptedItem())
				.save(consumer, "koratio:koratio/decrypting");

		AdvancementHolder enter_fantasia = Advancement.Builder.advancement().parent(decrypting).display(new ItemStack(KoratioBlocks.MINIATURE_FANTASIA_PORTAL.get()),
						Component.translatable("advancements.koratio.enter_fantasia.title"),
						Component.translatable("advancements.koratio.enter_fantasia.desc"),
						null,
						AdvancementType.TASK,
						true, true, false)
				.requirements(AdvancementRequirements.Strategy.OR)
				.addCriterion("entered_fantasia", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ResourceKey.create(Registries.DIMENSION,
						ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "fantasia"))))
				.save(consumer, "koratio:koratio/enter_fantasia");

		//Skytastic - Visit Fantasia's world above the clouds
		//not named yet - Kill one of every mob that's new to Fantasia
		//not named yet - Visit every single one of Fantasia's Biomes
		//Fantastic Gourmet - Eat every Fantasia Food
		//not named yet - Tame a golden Fox Spirit
	}
}