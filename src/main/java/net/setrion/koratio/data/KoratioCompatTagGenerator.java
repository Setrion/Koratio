package net.setrion.koratio.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioItems;

import java.util.concurrent.CompletableFuture;

public class KoratioCompatTagGenerator extends ItemTagsProvider {

    public static final TagKey<Item> CURIOS_CHARM = createTagFor("curios", "charm");
    public static final TagKey<Item> CURIOS_RING = createTagFor("curios", "ring");
    public static final TagKey<Item> CURIOS_BELT = createTagFor("curios", "belt");
    public static final TagKey<Item> CURIOS_BRACELET = createTagFor("curios", "bracelet");
    public static final TagKey<Item> CURIOS_NECKLACE = createTagFor("curios", "necklace");
    public static final TagKey<Item> CURIOS_HEAD = createTagFor("curios", "head");

    public KoratioCompatTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper helper) {
        super(output, provider, blockTags, Koratio.MOD_ID, helper);
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CURIOS_CHARM);
        tag(CURIOS_RING).add(KoratioItems.RAINBOW_GEM.get());
        tag(CURIOS_BELT);
        tag(CURIOS_BRACELET);
        tag(CURIOS_NECKLACE);
        tag(CURIOS_HEAD);
    }

    private static TagKey<Item> createTagFor(String modid, String tagName) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(modid, tagName));
    }
}