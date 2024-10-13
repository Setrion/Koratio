package net.setrion.koratio.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioTags;

import java.util.concurrent.CompletableFuture;

public class DataMapGenerator extends DataMapProvider {

    public DataMapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        builder(NeoForgeDataMaps.OXIDIZABLES)
                .add(KoratioBlocks.TALL_COPPER_DOOR.getKey(), new Oxidizable(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get()), false)
                .add(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.getKey(), new Oxidizable(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get()), false)
                .add(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.getKey(), new Oxidizable(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get()), false);

        builder(NeoForgeDataMaps.WAXABLES)
                .add(KoratioBlocks.TALL_COPPER_DOOR.getKey(), new Waxable(KoratioBlocks.TALL_WAXED_COPPER_DOOR.get()), false)
                .add(KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.getKey(), new Waxable(KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get()), false)
                .add(KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.getKey(), new Waxable(KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get()), false)
                .add(KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.getKey(), new Waxable(KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get()), false);

        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(Tags.Items.BOOKSHELVES, new FurnaceFuel(300), false)
                .add(Tags.Items.CHESTS_WOODEN, new FurnaceFuel(300), false)
                .add(KoratioTags.Items.TALL_WOODEN_DOORS, new FurnaceFuel(300), false);

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(KoratioItems.OAK_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.SPRUCE_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.BIRCH_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.JUNGLE_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.ACACIA_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.DARK_OAK_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.MANGROVE_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.AZALEA_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.FLOWERING_AZALEA_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.CHERRY_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.FANTASIA_GRASS, new Compostable(0.3F), false)
                .add(KoratioItems.TALL_FANTASIA_GRASS, new Compostable(0.5F), false)
                .add(KoratioItems.GILDED_VINES, new Compostable(0.5F), false)
                .add(KoratioItems.PURPLE_MUSHROOM, new Compostable(0.65F), false)
                .add(KoratioItems.GREEN_MUSHROOM, new Compostable(0.65F), false)
                .add(KoratioItems.RAINBOW_ALLIUM, new Compostable(0.3F), false)
                .add(KoratioItems.RAINBOW_ROSE, new Compostable(0.3F), false)
                .add(KoratioItems.RAINBOW_LILY_OF_THE_VALLEY, new Compostable(0.3F), false)
                .add(KoratioItems.PURPLE_MUSHROOM_BLOCK, new Compostable(0.85F), false)
                .add(KoratioItems.GREEN_MUSHROOM_BLOCK, new Compostable(0.85F), false)
                .add(KoratioItems.PANGO_LEAVES, new Compostable(0.3F), false)
                .add(KoratioItems.PANGO_SAPLING, new Compostable(0.3F), false)
                .add(KoratioItems.PANGO_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.RUGONA_LEAVES, new Compostable(0.3F), false)
                .add(KoratioItems.RUGONA_SAPLING, new Compostable(0.3F), false)
                .add(KoratioItems.RUGONA_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.VARESO_LEAVES, new Compostable(0.3F), false)
                .add(KoratioItems.VARESO_SAPLING, new Compostable(0.3F), false)
                .add(KoratioItems.VARESO_LEAF_PANE, new Compostable(0.05F), false)
                .add(KoratioItems.ELVEN_LEAVES, new Compostable(0.3F), false)
                .add(KoratioItems.ELVEN_SAPLING, new Compostable(0.3F), false)
                .add(KoratioItems.ELVEN_LEAF_PANE, new Compostable(0.05F), false);
    }
}