package net.setrion.koratio.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioTags;

import java.util.concurrent.CompletableFuture;

public class DataMapGenerator extends DataMapProvider {

    public DataMapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
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
                .add(KoratioBlocks.OAK_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.SPRUCE_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.BIRCH_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.JUNGLE_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.ACACIA_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.DARK_OAK_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.MANGROVE_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.AZALEA_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.CHERRY_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.FANTASIA_GRASS.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.TALL_FANTASIA_GRASS.asItem().builtInRegistryHolder(), new Compostable(0.5F), false)
                .add(KoratioBlocks.GILDED_VINES.asItem().builtInRegistryHolder(), new Compostable(0.5F), false)
                .add(KoratioBlocks.PURPLE_MUSHROOM.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(KoratioBlocks.GREEN_MUSHROOM.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(KoratioBlocks.RAINBOW_ALLIUM.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.RAINBOW_ROSE.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.asItem().builtInRegistryHolder(), new Compostable(0.85F), false)
                .add(KoratioBlocks.GREEN_MUSHROOM_BLOCK.asItem().builtInRegistryHolder(), new Compostable(0.85F), false)
                .add(KoratioBlocks.PANGO_LEAVES.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.PANGO_SAPLING.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.PANGO_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.RUGONA_LEAVES.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.RUGONA_SAPLING.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.RUGONA_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.VARESO_LEAVES.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.VARESO_SAPLING.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.VARESO_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false)
                .add(KoratioBlocks.ELVEN_LEAVES.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.ELVEN_SAPLING.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(KoratioBlocks.ELVEN_LEAF_PANE.asItem().builtInRegistryHolder(), new Compostable(0.05F), false);
    }
}