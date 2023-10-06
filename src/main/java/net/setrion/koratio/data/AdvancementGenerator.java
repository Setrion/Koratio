package net.setrion.koratio.data;

import java.util.function.Consumer;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.advancements.critereon.DecryptScrollTrigger;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;

public class AdvancementGenerator implements net.minecraftforge.common.data.ForgeAdvancementProvider.AdvancementGenerator {

	public void generate(HolderLookup.Provider registries, Consumer<Advancement> consumer, ExistingFileHelper helper) {
		//Fantasia
		Advancement fantasia_root = Advancement.Builder.advancement().display(new ItemStack(KoratioItems.MINIATURE_FANTASIA_PORTAL.get()), 
				Component.translatable("advancements.fantasia.root.title"), 
				Component.translatable("advancements.fantasia.root.desc"), 
				new ResourceLocation(Koratio.MOD_ID, "textures/block/pango_planks.png"), 
				FrameType.TASK, 
				true, false, false)
				.requirements(RequirementsStrategy.OR)
				.addCriterion("entered_fantasia", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ResourceKey.create(Registries.DIMENSION,
						new ResourceLocation("koratio:fantasia"))))
				.save(consumer, "koratio:fantasia/root");
		
		@SuppressWarnings("unused")
		Advancement decrypting = Advancement.Builder.advancement().parent(fantasia_root).display(new ItemStack(KoratioBlocks.DECRYPTING_TABLE.get()),
				Component.translatable("advancements.koratio.decrypting.title"),
				Component.translatable("advancements.koratio.decrypting.desc"),
				null,
				FrameType.TASK,
				true, false, false)
				.requirements(RequirementsStrategy.OR)
				.addCriterion("decrypted_scroll", DecryptScrollTrigger.TriggerInstance.decryptedScroll())
				.save(consumer, "koratio:fantasia/decrypting");
		
		//Skytastic - Visit Fantasia's world above the clouds
		//Not Named Yet - Visit every single one of Fantasia's Biomes
		//Fantastic Gourmet - Eat every Fantasia Food
		//Not Named Yet - Find a golden Egg
		//Not Named Yet - Tame a golden Parrot
		
		//Demonicio
		@SuppressWarnings("unused")
		Advancement demonicio_root = Advancement.Builder.advancement().display(new ItemStack(KoratioItems.MINIATURE_FANTASIA_PORTAL.get()), 
				Component.translatable("advancements.demonicio.root.title"), 
				Component.translatable("advancements.demonicio.root.desc"), 
				new ResourceLocation(Koratio.MOD_ID, "textures/block/nighy_planks.png"), 
				FrameType.TASK, 
				true, false, false)
				.requirements(RequirementsStrategy.OR)
				.addCriterion("entered_demonicio", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ResourceKey.create(Registries.DIMENSION,
						new ResourceLocation("koratio:demonicio"))))
				.save(consumer, "koratio:demonicio/root");
		
		//Not Named Yet - Visit every single one of Demonicio's Biomes
	}
}