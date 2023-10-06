package net.setrion.koratio.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioTags;

public class ItemTagGenerator extends ItemTagsProvider {
    
    public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> blockprovider, ExistingFileHelper existingFileHelper) {
    	super(output, future, blockprovider, Koratio.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
    	copy(KoratioTags.Blocks.PANGO_LOGS, KoratioTags.Items.PANGO_LOGS);
    	copy(KoratioTags.Blocks.RUGONA_LOGS, KoratioTags.Items.RUGONA_LOGS);
    	copy(KoratioTags.Blocks.VARESO_LOGS, KoratioTags.Items.VARESO_LOGS);
    	copy(KoratioTags.Blocks.NIGHY_LOGS, KoratioTags.Items.NIGHY_LOGS);
    	copy(KoratioTags.Blocks.KORATIO_LOGS, KoratioTags.Items.KORATIO_LOGS);
    	tag(ItemTags.LOGS).addTag(KoratioTags.Items.KORATIO_LOGS);
    	tag(ItemTags.LOGS_THAT_BURN).addTag(KoratioTags.Items.KORATIO_LOGS);
    	copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
		copy(BlockTags.LEAVES, ItemTags.LEAVES);
		copy(BlockTags.PLANKS, ItemTags.PLANKS);
		copy(KoratioTags.Blocks.KORATIO_FENCES, KoratioTags.Items.KORATIO_FENCES);
		copy(KoratioTags.Blocks.KORATIO_FENCE_GATES, KoratioTags.Items.KORATIO_FENCE_GATES);
		copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
		copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
		copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
		copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
		copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
		copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
		copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
		copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
		copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
		tag(ItemTags.SIGNS).add(KoratioBlocks.PANGO_SIGN.get().asItem());
		copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
		tag(Tags.Items.MUSHROOMS).add(KoratioItems.PURPLE_MUSHROOM.get(), KoratioItems.GREEN_MUSHROOM.get());
		tag(ItemTags.BOOKSHELF_BOOKS).add(KoratioItems.DECRYPTING_BOOK.get(), KoratioItems.BETTER_DECRYPTING_BOOK.get(), KoratioItems.FANTASTIC_DECRYPTING_BOOK.get());
		copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		//tag(ItemTags.TRIM_MATERIALS).add(KoratioItems.RAINBOW_GEM.get(), KoratioItems.ARSOY_INGOT.get());
		//tag(ItemTags.TRIMMABLE_ARMOR).add(KoratioItems.RAINBOW_GEM_HELMET.get(), KoratioItems.RAINBOW_GEM_CHESTPLATE.get(), KoratioItems.RAINBOW_GEM_LEGGINGS.get(), KoratioItems.RAINBOW_GEM_BOOTS.get(), KoratioItems.ARSOY_HELMET.get(), KoratioItems.ARSOY_CHESTPLATE.get(), KoratioItems.ARSOY_LEGGINGS.get(), KoratioItems.ARSOY_BOOTS.get());
		tag(KoratioTags.Items.SUGAR).add(Items.SUGAR, KoratioItems.RED_SUGAR.get(), KoratioItems.BLUE_SUGAR.get(), KoratioItems.YELLOW_SUGAR.get(), KoratioItems.GREEN_SUGAR.get());
    }
    
    @Override
	public String getName() {
		return "Koratio Item Tags";
	}
}