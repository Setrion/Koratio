package net.setrion.koratio.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.*;
import net.setrion.koratio.world.entity.animal.MagicalCatVariant;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class KoratioTagsGenerator {

    public static class ItemTagGenerator extends ItemTagsProvider {

        public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> blockProvider, ExistingFileHelper existingFileHelper) {
            super(output, future, blockProvider, Koratio.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            copy(KoratioTags.Blocks.PANGO_LOGS, KoratioTags.Items.PANGO_LOGS);
            copy(KoratioTags.Blocks.RUGONA_LOGS, KoratioTags.Items.RUGONA_LOGS);
            copy(KoratioTags.Blocks.VARESO_LOGS, KoratioTags.Items.VARESO_LOGS);
            copy(KoratioTags.Blocks.ELVEN_LOGS, KoratioTags.Items.ELVEN_LOGS);
            copy(KoratioTags.Blocks.CANDY_LOGS, KoratioTags.Items.CANDY_LOGS);
            copy(KoratioTags.Blocks.CHOCOLATE_OAK_LOGS, KoratioTags.Items.CHOCOLATE_OAK_LOGS);
            copy(KoratioTags.Blocks.LOGS, KoratioTags.Items.LOGS);
            copy(KoratioTags.Blocks.LEAF_PANES, KoratioTags.Items.LEAF_PANES);
            tag(ItemTags.LOGS).addTag(KoratioTags.Items.LOGS);
            tag(ItemTags.LOGS_THAT_BURN).addTag(KoratioTags.Items.LOGS);
            copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
            copy(BlockTags.LEAVES, ItemTags.LEAVES);
            copy(BlockTags.PLANKS, ItemTags.PLANKS);
            copy(KoratioTags.Blocks.FENCES, KoratioTags.Items.FENCES);
            copy(KoratioTags.Blocks.FENCE_GATES, KoratioTags.Items.FENCE_GATES);
            copy(KoratioTags.Blocks.TALL_WOODEN_DOORS, KoratioTags.Items.TALL_WOODEN_DOORS);
            copy(KoratioTags.Blocks.TALL_DOORS, KoratioTags.Items.TALL_DOORS);
            copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
            copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
            copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
            copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
            copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
            copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
            copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
            copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
            copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
            copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
            copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
            copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
            copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
            copy(Tags.Blocks.BOOKSHELVES, Tags.Items.BOOKSHELVES);
            copy(KoratioTags.Blocks.ICINGS, KoratioTags.Items.ICINGS);
            copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE);
            copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE);
            tag(ItemTags.BOATS).add(KoratioItems.PANGO_BOAT.get(), KoratioItems.RUGONA_BOAT.get(), KoratioItems.VARESO_BOAT.get(), KoratioItems.CANDY_BOAT.get(), KoratioItems.ELVEN_BOAT.get(), KoratioItems.BLUE_ELVEN_BOAT.get(), KoratioItems.CYAN_ELVEN_BOAT.get(), KoratioItems.GREEN_ELVEN_BOAT.get());
            tag(ItemTags.CHEST_BOATS).add(KoratioItems.PANGO_CHEST_BOAT.get(), KoratioItems.RUGONA_CHEST_BOAT.get(), KoratioItems.VARESO_CHEST_BOAT.get(), KoratioItems.CANDY_CHEST_BOAT.get(), KoratioItems.ELVEN_CHEST_BOAT.get(), KoratioItems.BLUE_ELVEN_CHEST_BOAT.get(), KoratioItems.CYAN_ELVEN_CHEST_BOAT.get(), KoratioItems.GREEN_ELVEN_CHEST_BOAT.get());
            tag(Tags.Items.MUSHROOMS).add(KoratioBlocks.PURPLE_MUSHROOM.asItem(), KoratioBlocks.GREEN_MUSHROOM.asItem());
            tag(ItemTags.BOOKSHELF_BOOKS).add(KoratioItems.DECRYPTING_BOOK.get(), KoratioItems.BETTER_DECRYPTING_BOOK.get(), KoratioItems.FANTASTIC_DECRYPTING_BOOK.get());
            copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
            copy(BlockTags.CANDLES, ItemTags.CANDLES);
            copy(KoratioTags.Blocks.RAINBOW_GEM_STORAGE_BLOCKS, KoratioTags.Items.RAINBOW_GEM_STORAGE_BLOCKS);
            copy(KoratioTags.Blocks.RUBY_STORAGE_BLOCKS, KoratioTags.Items.RUBY_STORAGE_BLOCKS);
            copy(KoratioTags.Blocks.SAPPHIRE_STORAGE_BLOCKS, KoratioTags.Items.SAPPHIRE_STORAGE_BLOCKS);
            copy(KoratioTags.Blocks.TOPAZ_STORAGE_BLOCKS, KoratioTags.Items.TOPAZ_STORAGE_BLOCKS);
            copy(KoratioTags.Blocks.ONYX_STORAGE_BLOCKS, KoratioTags.Items.ONYX_STORAGE_BLOCKS);
            copy(KoratioTags.Blocks.RUBY_ORES, KoratioTags.Items.RUBY_ORES);
            copy(KoratioTags.Blocks.SAPPHIRE_ORES, KoratioTags.Items.SAPPHIRE_ORES);
            copy(KoratioTags.Blocks.TOPAZ_ORES, KoratioTags.Items.TOPAZ_ORES);
            copy(KoratioTags.Blocks.ONYX_ORES, KoratioTags.Items.ONYX_ORES);
            tag(KoratioTags.Items.GEMS).add(KoratioItems.RAINBOW_GEM.get(), KoratioItems.RUBY.get(), KoratioItems.SAPPHIRE.get(), KoratioItems.TOPAZ.get(), KoratioItems.ONYX.get());
            tag(KoratioTags.Items.RAINBOW_GEMS).add(KoratioItems.RAINBOW_GEM.get());
            tag(KoratioTags.Items.RUBY_GEMS).add(KoratioItems.RUBY.get());
            tag(KoratioTags.Items.SAPPHIRE_GEMS).add(KoratioItems.SAPPHIRE.get());
            tag(KoratioTags.Items.TOPAZ_GEMS).add(KoratioItems.TOPAZ.get());
            tag(KoratioTags.Items.ONYX_GEMS).add(KoratioItems.ONYX.get());
            //tag(ItemTags.TRIMMABLE_ARMOR).add();
            tag(ItemTags.TRIM_MATERIALS).add(KoratioItems.RUBY.get(), KoratioItems.SAPPHIRE.get(), KoratioItems.TOPAZ.get(), KoratioItems.ONYX.get());
            tag(KoratioTags.Items.SUGAR).add(Items.SUGAR, KoratioItems.RED_SUGAR.get(), KoratioItems.BLUE_SUGAR.get(), KoratioItems.YELLOW_SUGAR.get(), KoratioItems.GREEN_SUGAR.get());
            tag(ItemTags.MEAT).add(KoratioItems.SPIKED_PORKCHOP.get(), KoratioItems.COOKED_SPIKED_PORKCHOP.get(), KoratioItems.FLUFFER.get(), KoratioItems.COOKED_FLUFFER.get());
            tag(Tags.Items.FOODS_RAW_MEAT).add(KoratioItems.SPIKED_PORKCHOP.get(), KoratioItems.FLUFFER.get());
            tag(Tags.Items.FOODS_COOKED_MEAT).add(KoratioItems.COOKED_SPIKED_PORKCHOP.get(), KoratioItems.COOKED_FLUFFER.get());
            tag(KoratioTags.Items.MOLTEN_SUGAR_BUCKETS).add(KoratioItems.MOLTEN_WHITE_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_LIGHT_GRAY_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_GRAY_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_BLACK_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_BROWN_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_RED_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_ORANGE_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_LIME_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_CYAN_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_LIGHT_BLUE_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_PURPLE_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_MAGENTA_SUGAR_BUCKET.get(), KoratioItems.MOLTEN_PINK_SUGAR_BUCKET.get());

            tag(KoratioTags.Items.TELEKINESIS_ENCHANTABLE).addTag(ItemTags.BOW_ENCHANTABLE).addTag(ItemTags.WEAPON_ENCHANTABLE).addTag(ItemTags.FISHING_ENCHANTABLE).addTag(ItemTags.MINING_ENCHANTABLE);
        }

        @Override
        public String getName() {
            return "Koratio Item Tags";
        }
    }

    public static class EnchantmentTagGenerator extends EnchantmentTagsProvider {

        public EnchantmentTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, Koratio.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(EnchantmentTags.TREASURE).add(KoratioEnchantments.TELEKINESIS);
        }
    }

    public static class BlockTagGenerator extends IntrinsicHolderTagsProvider<Block> {

        @SuppressWarnings("deprecation")
        public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
            super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), Koratio.MOD_ID, helper);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(KoratioTags.Blocks.PANGO_LOGS).add(KoratioBlocks.PANGO_LOG.get(), KoratioBlocks.PANGO_WOOD.get(), KoratioBlocks.STRIPPED_PANGO_LOG.get(), KoratioBlocks.STRIPPED_PANGO_WOOD.get());
            tag(KoratioTags.Blocks.RUGONA_LOGS).add(KoratioBlocks.RUGONA_LOG.get(), KoratioBlocks.RUGONA_WOOD.get(), KoratioBlocks.STRIPPED_RUGONA_LOG.get(), KoratioBlocks.STRIPPED_RUGONA_WOOD.get());
            tag(KoratioTags.Blocks.VARESO_LOGS).add(KoratioBlocks.VARESO_LOG.get(), KoratioBlocks.VARESO_WOOD.get(), KoratioBlocks.STRIPPED_VARESO_LOG.get(), KoratioBlocks.STRIPPED_VARESO_WOOD.get());
            tag(KoratioTags.Blocks.ELVEN_LOGS).add(KoratioBlocks.ELVEN_LOG.get(), KoratioBlocks.ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_ELVEN_LOG.get(), KoratioBlocks.STRIPPED_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_BLUE_ELVEN_LOG.get(), KoratioBlocks.STRIPPED_BLUE_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_CYAN_ELVEN_LOG.get(), KoratioBlocks.STRIPPED_CYAN_ELVEN_WOOD.get(), KoratioBlocks.STRIPPED_GREEN_ELVEN_LOG.get(), KoratioBlocks.STRIPPED_GREEN_ELVEN_WOOD.get());
            tag(KoratioTags.Blocks.CANDY_LOGS).add(KoratioBlocks.CANDY_LOG.get(), KoratioBlocks.CANDY_WOOD.get(), KoratioBlocks.STRIPPED_CANDY_LOG.get(), KoratioBlocks.STRIPPED_CANDY_WOOD.get());
            tag(KoratioTags.Blocks.CHOCOLATE_OAK_LOGS).add(KoratioBlocks.CHOCOLATE_OAK_LOG.get(), KoratioBlocks.CHOCOLATE_OAK_WOOD.get(), KoratioBlocks.STRIPPED_CHOCOLATE_OAK_LOG.get(), KoratioBlocks.STRIPPED_CHOCOLATE_OAK_WOOD.get());
            tag(KoratioTags.Blocks.LOGS).addTags(KoratioTags.Blocks.PANGO_LOGS, KoratioTags.Blocks.RUGONA_LOGS, KoratioTags.Blocks.VARESO_LOGS, KoratioTags.Blocks.CANDY_LOGS, KoratioTags.Blocks.CHOCOLATE_OAK_LOGS, KoratioTags.Blocks.ELVEN_LOGS);
            tag(KoratioTags.Blocks.MUSHROOMS).add(Blocks.RED_MUSHROOM, Blocks.BROWN_MUSHROOM, KoratioBlocks.PURPLE_MUSHROOM.get(), KoratioBlocks.GREEN_MUSHROOM.get());
            tag(BlockTags.LOGS).addTag(KoratioTags.Blocks.LOGS);
            tag(BlockTags.LOGS_THAT_BURN).addTag(KoratioTags.Blocks.LOGS);
            tag(BlockTags.SAPLINGS).add(KoratioBlocks.PANGO_SAPLING.get(), KoratioBlocks.RUGONA_SAPLING.get(), KoratioBlocks.VARESO_SAPLING.get(), KoratioBlocks.CANDY_SAPLING.get(), KoratioBlocks.CHOCOLATE_OAK_SAPLING.get(), KoratioBlocks.ELVEN_SAPLING.get());
            tag(BlockTags.LEAVES).add(KoratioBlocks.PANGO_LEAVES.get(), KoratioBlocks.RUGONA_LEAVES.get(), KoratioBlocks.VARESO_LEAVES.get(), KoratioBlocks.PINK_COTTON_CANDY_LEAVES.get(), KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAVES.get(), KoratioBlocks.LIME_COTTON_CANDY_LEAVES.get(), KoratioBlocks.YELLOW_COTTON_CANDY_LEAVES.get(), KoratioBlocks.CHOCOLATE_OAK_LEAVES.get(), KoratioBlocks.ELVEN_LEAVES.get());
            tag(KoratioTags.Blocks.LEAF_PANES).add(KoratioBlocks.OAK_LEAF_PANE.get(), KoratioBlocks.SPRUCE_LEAF_PANE.get(), KoratioBlocks.BIRCH_LEAF_PANE.get(), KoratioBlocks.JUNGLE_LEAF_PANE.get(), KoratioBlocks.ACACIA_LEAF_PANE.get(), KoratioBlocks.DARK_OAK_LEAF_PANE.get(), KoratioBlocks.MANGROVE_LEAF_PANE.get(), KoratioBlocks.AZALEA_LEAF_PANE.get(), KoratioBlocks.FLOWERING_AZALEA_LEAF_PANE.get(), KoratioBlocks.CHERRY_LEAF_PANE.get(), KoratioBlocks.PALE_OAK_LEAF_PANE.get(), KoratioBlocks.PANGO_LEAF_PANE.get(), KoratioBlocks.RUGONA_LEAF_PANE.get(), KoratioBlocks.VARESO_LEAF_PANE.get(), KoratioBlocks.PINK_COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.LIGHT_BLUE_COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.LIME_COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.YELLOW_COTTON_CANDY_LEAF_PANE.get(), KoratioBlocks.CHOCOLATE_OAK_LEAF_PANE.get(), KoratioBlocks.ELVEN_LEAF_PANE.get());
            tag(BlockTags.PLANKS).add(KoratioBlocks.PANGO_PLANKS.get(), KoratioBlocks.RUGONA_PLANKS.get(), KoratioBlocks.VARESO_PLANKS.get(), KoratioBlocks.CANDY_PLANKS.get(), KoratioBlocks.CHOCOLATE_OAK_PLANKS.get(), KoratioBlocks.ELVEN_PLANKS.get(), KoratioBlocks.BLUE_ELVEN_PLANKS.get(), KoratioBlocks.CYAN_ELVEN_PLANKS.get(), KoratioBlocks.GREEN_ELVEN_PLANKS.get());
            tag(KoratioTags.Blocks.FENCES).add(KoratioBlocks.PANGO_FENCE.get(), KoratioBlocks.RUGONA_FENCE.get(), KoratioBlocks.VARESO_FENCE.get(), KoratioBlocks.CANDY_FENCE.get(), KoratioBlocks.CHOCOLATE_OAK_FENCE.get(), KoratioBlocks.ELVEN_FENCE.get(), KoratioBlocks.BLUE_ELVEN_FENCE.get(), KoratioBlocks.CYAN_ELVEN_FENCE.get(), KoratioBlocks.GREEN_ELVEN_FENCE.get());
            tag(KoratioTags.Blocks.FENCE_GATES).add(KoratioBlocks.PANGO_FENCE_GATE.get(), KoratioBlocks.RUGONA_FENCE_GATE.get(), KoratioBlocks.VARESO_FENCE_GATE.get(), KoratioBlocks.CANDY_FENCE_GATE.get(), KoratioBlocks.CHOCOLATE_OAK_FENCE_GATE.get(), KoratioBlocks.ELVEN_FENCE_GATE.get(), KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get(), KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get(), KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get());
            tag(BlockTags.WOODEN_FENCES).add(KoratioBlocks.PANGO_FENCE.get(), KoratioBlocks.RUGONA_FENCE.get(), KoratioBlocks.VARESO_FENCE.get(), KoratioBlocks.CANDY_FENCE.get(), KoratioBlocks.CHOCOLATE_OAK_FENCE.get(), KoratioBlocks.ELVEN_FENCE.get(), KoratioBlocks.BLUE_ELVEN_FENCE.get(), KoratioBlocks.CYAN_ELVEN_FENCE.get(), KoratioBlocks.GREEN_ELVEN_FENCE.get());
            tag(BlockTags.FENCE_GATES).add(KoratioBlocks.PANGO_FENCE_GATE.get(), KoratioBlocks.RUGONA_FENCE_GATE.get(), KoratioBlocks.VARESO_FENCE_GATE.get(), KoratioBlocks.CANDY_FENCE_GATE.get(), KoratioBlocks.CHOCOLATE_OAK_FENCE_GATE.get(), KoratioBlocks.ELVEN_FENCE_GATE.get(), KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get(), KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get(), KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get());
            tag(Tags.Blocks.FENCES_WOODEN).add(KoratioBlocks.PANGO_FENCE.get(), KoratioBlocks.RUGONA_FENCE.get(), KoratioBlocks.VARESO_FENCE.get(), KoratioBlocks.CANDY_FENCE.get(), KoratioBlocks.CHOCOLATE_OAK_FENCE.get(), KoratioBlocks.ELVEN_FENCE.get(), KoratioBlocks.BLUE_ELVEN_FENCE.get(), KoratioBlocks.CYAN_ELVEN_FENCE.get(), KoratioBlocks.GREEN_ELVEN_FENCE.get());
            tag(Tags.Blocks.FENCE_GATES_WOODEN).add(KoratioBlocks.PANGO_FENCE_GATE.get(), KoratioBlocks.RUGONA_FENCE_GATE.get(), KoratioBlocks.VARESO_FENCE_GATE.get(), KoratioBlocks.CANDY_FENCE_GATE.get(), KoratioBlocks.CHOCOLATE_OAK_FENCE_GATE.get(), KoratioBlocks.ELVEN_FENCE_GATE.get(), KoratioBlocks.BLUE_ELVEN_FENCE_GATE.get(), KoratioBlocks.CYAN_ELVEN_FENCE_GATE.get(), KoratioBlocks.GREEN_ELVEN_FENCE_GATE.get());
            tag(BlockTags.WOODEN_SLABS).add(KoratioBlocks.PANGO_SLAB.get(), KoratioBlocks.RUGONA_SLAB.get(), KoratioBlocks.VARESO_SLAB.get(), KoratioBlocks.CANDY_SLAB.get(), KoratioBlocks.CHOCOLATE_OAK_SLAB.get(), KoratioBlocks.ELVEN_SLAB.get(), KoratioBlocks.BLUE_ELVEN_SLAB.get(), KoratioBlocks.CYAN_ELVEN_SLAB.get(), KoratioBlocks.GREEN_ELVEN_SLAB.get());
            tag(BlockTags.WOODEN_STAIRS).add(KoratioBlocks.PANGO_STAIRS.get(), KoratioBlocks.RUGONA_STAIRS.get(), KoratioBlocks.VARESO_STAIRS.get(), KoratioBlocks.CANDY_STAIRS.get(), KoratioBlocks.CHOCOLATE_OAK_STAIRS.get(), KoratioBlocks.ELVEN_STAIRS.get(), KoratioBlocks.BLUE_ELVEN_STAIRS.get(), KoratioBlocks.CYAN_ELVEN_STAIRS.get(), KoratioBlocks.GREEN_ELVEN_STAIRS.get());
            tag(BlockTags.WOODEN_BUTTONS).add(KoratioBlocks.PANGO_BUTTON.get(), KoratioBlocks.RUGONA_BUTTON.get(), KoratioBlocks.VARESO_BUTTON.get(), KoratioBlocks.CANDY_BUTTON.get(), KoratioBlocks.CHOCOLATE_OAK_BUTTON.get(), KoratioBlocks.ELVEN_BUTTON.get(), KoratioBlocks.BLUE_ELVEN_BUTTON.get(), KoratioBlocks.CYAN_ELVEN_BUTTON.get(), KoratioBlocks.GREEN_ELVEN_BUTTON.get());
            tag(BlockTags.WOODEN_PRESSURE_PLATES).add(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), KoratioBlocks.VARESO_PRESSURE_PLATE.get(), KoratioBlocks.CHOCOLATE_OAK_PRESSURE_PLATE.get(), KoratioBlocks.CANDY_PRESSURE_PLATE.get(), KoratioBlocks.ELVEN_PRESSURE_PLATE.get(), KoratioBlocks.BLUE_ELVEN_PRESSURE_PLATE.get(), KoratioBlocks.CYAN_ELVEN_PRESSURE_PLATE.get(), KoratioBlocks.GREEN_ELVEN_PRESSURE_PLATE.get());
            tag(BlockTags.WOODEN_TRAPDOORS).add(KoratioBlocks.PANGO_TRAPDOOR.get(), KoratioBlocks.RUGONA_TRAPDOOR.get(), KoratioBlocks.VARESO_TRAPDOOR.get(), KoratioBlocks.CANDY_TRAPDOOR.get(), KoratioBlocks.CHOCOLATE_OAK_TRAPDOOR.get(), KoratioBlocks.ELVEN_TRAPDOOR.get(), KoratioBlocks.BLUE_ELVEN_TRAPDOOR.get(), KoratioBlocks.CYAN_ELVEN_TRAPDOOR.get(), KoratioBlocks.GREEN_ELVEN_TRAPDOOR.get());
            tag(BlockTags.WOODEN_DOORS).add(KoratioBlocks.PANGO_DOOR.get(), KoratioBlocks.RUGONA_DOOR.get(), KoratioBlocks.VARESO_DOOR.get(), KoratioBlocks.CANDY_DOOR.get(), KoratioBlocks.CHOCOLATE_OAK_DOOR.get(), KoratioBlocks.ELVEN_DOOR.get(), KoratioBlocks.BLUE_ELVEN_DOOR.get(), KoratioBlocks.CYAN_ELVEN_DOOR.get(), KoratioBlocks.GREEN_ELVEN_DOOR.get());
            tag(KoratioTags.Blocks.TALL_WOODEN_DOORS).add(KoratioBlocks.TALL_OAK_DOOR.get(), KoratioBlocks.TALL_SPRUCE_DOOR.get(), KoratioBlocks.TALL_BIRCH_DOOR.get(), KoratioBlocks.TALL_JUNGLE_DOOR.get(), KoratioBlocks.TALL_ACACIA_DOOR.get(), KoratioBlocks.TALL_DARK_OAK_DOOR.get(), KoratioBlocks.TALL_MANGROVE_DOOR.get(), KoratioBlocks.TALL_CHERRY_DOOR.get(), KoratioBlocks.TALL_PALE_OAK_DOOR.get(), KoratioBlocks.TALL_BAMBOO_DOOR.get(), KoratioBlocks.TALL_CRIMSON_DOOR.get(), KoratioBlocks.TALL_WARPED_DOOR.get(), KoratioBlocks.TALL_PANGO_DOOR.get(), KoratioBlocks.TALL_RUGONA_DOOR.get(), KoratioBlocks.TALL_VARESO_DOOR.get(), KoratioBlocks.TALL_ELVEN_DOOR.get(), KoratioBlocks.TALL_BLUE_ELVEN_DOOR.get(), KoratioBlocks.TALL_CYAN_ELVEN_DOOR.get(), KoratioBlocks.TALL_GREEN_ELVEN_DOOR.get());
            tag(KoratioTags.Blocks.TALL_DOORS).add(KoratioBlocks.TALL_IRON_DOOR.get(), KoratioBlocks.TALL_COPPER_DOOR.get(), KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get(), KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get(), KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get(), KoratioBlocks.TALL_WAXED_COPPER_DOOR.get(), KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get(), KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get(), KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get()).addTag(KoratioTags.Blocks.TALL_WOODEN_DOORS);
            tag(BlockTags.STANDING_SIGNS).add(KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.CANDY_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_SIGN.get(), KoratioBlocks.ELVEN_SIGN.get(), KoratioBlocks.BLUE_ELVEN_SIGN.get(), KoratioBlocks.CYAN_ELVEN_SIGN.get(), KoratioBlocks.GREEN_ELVEN_SIGN.get());
            tag(BlockTags.WALLS).add(KoratioBlocks.ANCIENT_STONE_BRICK_WALL.get(), KoratioBlocks.POLISHED_ANCIENT_STONE_WALL.get(), KoratioBlocks.ANCIENT_STONE_TILE_WALL.get());
            tag(BlockTags.WALL_SIGNS).add(KoratioBlocks.PANGO_WALL_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get(), KoratioBlocks.CANDY_WALL_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_WALL_SIGN.get(), KoratioBlocks.ELVEN_WALL_SIGN.get());
            tag(BlockTags.WALL_HANGING_SIGNS).add(KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(), KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(), KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(), KoratioBlocks.CANDY_WALL_HANGING_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_WALL_HANGING_SIGN.get(), KoratioBlocks.ELVEN_WALL_HANGING_SIGN.get());
            tag(BlockTags.CEILING_HANGING_SIGNS).add(KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.CANDY_HANGING_SIGN.get(), KoratioBlocks.CHOCOLATE_OAK_HANGING_SIGN.get(), KoratioBlocks.ELVEN_HANGING_SIGN.get());
            tag(Tags.Blocks.CHESTS_WOODEN).add(KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.CANDY_CHEST.get(), KoratioBlocks.CHOCOLATE_OAK_CHEST.get(), KoratioBlocks.ELVEN_CHEST.get(), KoratioBlocks.BLUE_ELVEN_CHEST.get(), KoratioBlocks.CYAN_ELVEN_CHEST.get(), KoratioBlocks.GREEN_ELVEN_CHEST.get());
            tag(Tags.Blocks.CHESTS).addTag(Tags.Blocks.CHESTS_WOODEN);
            tag(KoratioTags.Blocks.ICINGS).add(KoratioBlocks.WHITE_ICING_BLOCK.get(), KoratioBlocks.LIGHT_GRAY_ICING_BLOCK.get(), KoratioBlocks.GRAY_ICING_BLOCK.get(), KoratioBlocks.BLACK_ICING_BLOCK.get(), KoratioBlocks.BROWN_ICING_BLOCK.get(), KoratioBlocks.RED_ICING_BLOCK.get(), KoratioBlocks.ORANGE_ICING_BLOCK.get(), KoratioBlocks.YELLOW_ICING_BLOCK.get(), KoratioBlocks.LIME_ICING_BLOCK.get(), KoratioBlocks.GREEN_ICING_BLOCK.get(), KoratioBlocks.CYAN_ICING_BLOCK.get(), KoratioBlocks.LIGHT_BLUE_ICING_BLOCK.get(), KoratioBlocks.BLUE_ICING_BLOCK.get(), KoratioBlocks.PURPLE_ICING_BLOCK.get(), KoratioBlocks.MAGENTA_ICING_BLOCK.get(), KoratioBlocks.PINK_ICING_BLOCK.get());
            tag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add(KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.CANDY_CHEST.get(), KoratioBlocks.CHOCOLATE_OAK_CHEST.get(), KoratioBlocks.ELVEN_CHEST.get(), KoratioBlocks.BLUE_ELVEN_CHEST.get(), KoratioBlocks.CYAN_ELVEN_CHEST.get(), KoratioBlocks.GREEN_ELVEN_CHEST.get());
            tag(BlockTags.FEATURES_CANNOT_REPLACE).add(KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.CANDY_CHEST.get(), KoratioBlocks.CHOCOLATE_OAK_CHEST.get(), KoratioBlocks.ELVEN_CHEST.get(), KoratioBlocks.BLUE_ELVEN_CHEST.get(), KoratioBlocks.CYAN_ELVEN_CHEST.get(), KoratioBlocks.GREEN_ELVEN_CHEST.get());
            tag(BlockTags.PORTALS).add(KoratioBlocks.FANTASIA_PORTAL.get());
            tag(BlockTags.REPLACEABLE).add(KoratioBlocks.GILDED_VINES.get(), KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get());
            tag(BlockTags.SWORD_EFFICIENT).add(KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get());
            tag(BlockTags.REPLACEABLE_BY_TREES).add(KoratioBlocks.GILDED_VINES.get(), KoratioBlocks.FANTASIA_GRASS.get(), KoratioBlocks.TALL_FANTASIA_GRASS.get());
            tag(BlockTags.ENCHANTMENT_POWER_PROVIDER).add(KoratioBlocks.PANGO_BOOKSHELF.get(), KoratioBlocks.RUGONA_BOOKSHELF.get(), KoratioBlocks.VARESO_BOOKSHELF.get(), KoratioBlocks.CANDY_BOOKSHELF.get(), KoratioBlocks.CHOCOLATE_OAK_BOOKSHELF.get(), KoratioBlocks.ELVEN_BOOKSHELF.get(), KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get(), KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get(), KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get());
            tag(Tags.Blocks.BOOKSHELVES).add(KoratioBlocks.PANGO_BOOKSHELF.get(), KoratioBlocks.RUGONA_BOOKSHELF.get(), KoratioBlocks.VARESO_BOOKSHELF.get(), KoratioBlocks.CANDY_BOOKSHELF.get(), KoratioBlocks.CHOCOLATE_OAK_BOOKSHELF.get(), KoratioBlocks.ELVEN_BOOKSHELF.get(), KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get(), KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get(), KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get());
            tag(Tags.Blocks.STORAGE_BLOCKS).add(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), KoratioBlocks.RUBY_BLOCK.get(), KoratioBlocks.SAPPHIRE_BLOCK.get(), KoratioBlocks.TOPAZ_BLOCK.get(), KoratioBlocks.ONYX_BLOCK.get());
            tag(BlockTags.WALL_POST_OVERRIDE).add(KoratioBlocks.RAINBOW_TORCH.get());
            tag(BlockTags.CAMPFIRES).add(KoratioBlocks.RAINBOW_CAMPFIRE.get());
            tag(BlockTags.FIRE).add(KoratioBlocks.RAINBOW_FIRE.get());
            tag(BlockTags.REPLACEABLE).add(KoratioBlocks.RAINBOW_FIRE.get());
            tag(BlockTags.VIBRATION_RESONATORS).add(KoratioBlocks.RAINBOW_CRYSTAL_BLOCK.get());
            tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(KoratioBlocks.RAINBOW_CRYSTAL_BLOCK.get(), KoratioBlocks.BUDDING_RAINBOW_CRYSTAL.get());
            tag(Tags.Blocks.BUDDING_BLOCKS).add(KoratioBlocks.BUDDING_RAINBOW_CRYSTAL.get());
            tag(Tags.Blocks.BUDS).add(KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.get(), KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD.get(), KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD.get());
            tag(BlockTags.INSIDE_STEP_SOUND_BLOCKS).add(KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.get());
            tag(Tags.Blocks.CLUSTERS).add(KoratioBlocks.RAINBOW_CRYSTAL_CLUSTER.get());
            tag(BlockTags.CANDLES).add(KoratioBlocks.RAINBOW_CANDLE.get());
            tag(BlockTags.CANDLE_CAKES).add(KoratioBlocks.RAINBOW_CANDLE_CAKE.get());

            tag(Tags.Blocks.VILLAGER_JOB_SITES).add(KoratioBlocks.DECRYPTING_TABLE.get());

            tag(BlockTags.BUTTONS).add(KoratioBlocks.COOKIE_BLOCK_BUTTON.get());
            tag(BlockTags.PRESSURE_PLATES).add(KoratioBlocks.COOKIE_BLOCK_PRESSURE_PLATE.get());

            tag(KoratioTags.Blocks.GINGERBREAD_LABYRINTH_BLOCKS).add(KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(),
                    KoratioBlocks.RAW_GINGERBREAD_STAIRS.get(),
                    KoratioBlocks.RAW_GINGERBREAD_SLAB.get(),
                    KoratioBlocks.GINGERBREAD_BLOCK.get(),
                    KoratioBlocks.GINGERBREAD_STAIRS.get(),
                    KoratioBlocks.GINGERBREAD_SLAB.get(),
                    KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(),
                    KoratioBlocks.RAW_GINGERBREAD_BRICK_STAIRS.get(),
                    KoratioBlocks.RAW_GINGERBREAD_BRICK_SLAB.get(),
                    KoratioBlocks.GINGERBREAD_BRICKS.get(),
                    KoratioBlocks.GINGERBREAD_BRICK_STAIRS.get(),
                    KoratioBlocks.GINGERBREAD_BRICK_SLAB.get(),
                    KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get(),
                    KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_STAIRS.get(),
                    KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_SLAB.get(),
                    KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get(),
                    KoratioBlocks.LARGE_GINGERBREAD_BRICK_STAIRS.get(),
                    KoratioBlocks.LARGE_GINGERBREAD_BRICK_SLAB.get(),
                    KoratioBlocks.RAW_GINGERBREAD_WALL.get(),
                    KoratioBlocks.GINGERBREAD_WALL.get(),
                    KoratioBlocks.RAW_GINGERBREAD_BRICK_WALL.get(),
                    KoratioBlocks.GINGERBREAD_BRICK_WALL.get(),
                    KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICK_WALL.get(),
                    KoratioBlocks.LARGE_GINGERBREAD_BRICK_WALL.get(),
                    KoratioBlocks.COOKIE_BLOCK.get(),
                    KoratioBlocks.COOKIE_BLOCK_STAIRS.get(),
                    KoratioBlocks.COOKIE_BLOCK_SLAB.get(),
                    KoratioBlocks.COOKIE_BLOCK_BUTTON.get(),
                    KoratioBlocks.COOKIE_BLOCK_PRESSURE_PLATE.get());

            tag(KoratioTags.Blocks.RAINBOW_GEM_STORAGE_BLOCKS).add(KoratioBlocks.RAINBOW_GEM_BLOCK.get());
            tag(KoratioTags.Blocks.RUBY_STORAGE_BLOCKS).add(KoratioBlocks.RUBY_BLOCK.get());
            tag(KoratioTags.Blocks.SAPPHIRE_STORAGE_BLOCKS).add(KoratioBlocks.SAPPHIRE_BLOCK.get());
            tag(KoratioTags.Blocks.TOPAZ_STORAGE_BLOCKS).add(KoratioBlocks.TOPAZ_BLOCK.get());
            tag(KoratioTags.Blocks.ONYX_STORAGE_BLOCKS).add(KoratioBlocks.ONYX_BLOCK.get());

            tag(KoratioTags.Blocks.RUBY_ORES).add(KoratioBlocks.RUBY_ORE.get(), KoratioBlocks.DEEPSLATE_RUBY_ORE.get());
            tag(KoratioTags.Blocks.SAPPHIRE_ORES).add(KoratioBlocks.SAPPHIRE_ORE.get(), KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
            tag(KoratioTags.Blocks.TOPAZ_ORES).add(KoratioBlocks.TOPAZ_ORE.get(), KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get());
            tag(KoratioTags.Blocks.ONYX_ORES).add(KoratioBlocks.ONYX_ORE.get(), KoratioBlocks.DEEPSLATE_ONYX_ORE.get());

            tag(BlockTags.BEACON_BASE_BLOCKS).add(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), KoratioBlocks.RUBY_BLOCK.get(), KoratioBlocks.SAPPHIRE_BLOCK.get(), KoratioBlocks.TOPAZ_BLOCK.get(), KoratioBlocks.ONYX_BLOCK.get());

            tag(BlockTags.ENDERMAN_HOLDABLE).add(KoratioBlocks.GREEN_MUSHROOM.get(), KoratioBlocks.PURPLE_MUSHROOM.get());

            tag(KoratioTags.Blocks.CRYSTAL_CAVE_CRYSTALS).add(Blocks.AMETHYST_BLOCK, Blocks.BUDDING_AMETHYST, Blocks.SMALL_AMETHYST_BUD, Blocks.MEDIUM_AMETHYST_BUD, Blocks.LARGE_AMETHYST_BUD, Blocks.AMETHYST_CLUSTER);
            tag(KoratioTags.Blocks.FANTASIA_CARVER_REPLACEABLES).addTag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).addTag(KoratioTags.Blocks.CRYSTAL_CAVE_CRYSTALS);

            tag(BlockTags.SMALL_FLOWERS).add(KoratioBlocks.RAINBOW_ROSE.get(), KoratioBlocks.RAINBOW_ALLIUM.get(), KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), KoratioBlocks.COOKIE_FLOWER.get());
            tag(BlockTags.FLOWER_POTS).add(KoratioBlocks.POTTED_PANGO_SAPLING.get(), KoratioBlocks.POTTED_RUGONA_SAPLING.get(), KoratioBlocks.POTTED_VARESO_SAPLING.get(), KoratioBlocks.POTTED_ELVEN_SAPLING.get(), KoratioBlocks.POTTED_RAINBOW_ROSE.get(), KoratioBlocks.POTTED_RAINBOW_ALLIUM.get(), KoratioBlocks.POTTED_RAINBOW_LILY_OF_THE_VALLEY.get(), KoratioBlocks.POTTED_COOKIE_FLOWER.get(), KoratioBlocks.POTTED_PURPLE_MUSHROOM.get(), KoratioBlocks.POTTED_GREEN_MUSHROOM.get());

            tag(BlockTags.CLIMBABLE).add(KoratioBlocks.GILDED_VINES.get());

            tag(KoratioTags.Blocks.BASE_STONE_FANTASIA).add(Blocks.STONE, Blocks.DEEPSLATE);

            tag(KoratioTags.Blocks.UNDEAD_REMAINS).add(KoratioBlocks.SKELETON_REMAINS.get(), KoratioBlocks.WITHER_SKELETON_REMAINS.get(), KoratioBlocks.STRAY_REMAINS.get(), KoratioBlocks.BOGGED_REMAINS.get(), KoratioBlocks.DEMONIC_SKELETON_REMAINS.get(), KoratioBlocks.ZOMBIE_REMAINS.get(), KoratioBlocks.HUSK_REMAINS.get(), KoratioBlocks.DROWNED_REMAINS.get(), KoratioBlocks.DEMONIC_ZOMBIE_REMAINS.get(), KoratioBlocks.PHANTOM_REMAINS.get());

            tag(KoratioTags.Blocks.COOKIE_ORES).add(KoratioBlocks.COOKIE_ORE.get(), KoratioBlocks.DEEPSLATE_COOKIE_ORE.get());

            tag(BlockTags.SNAPS_GOAT_HORN).add(KoratioBlocks.RUBY_ORE.get(), KoratioBlocks.SAPPHIRE_ORE.get(), KoratioBlocks.TOPAZ_ORE.get(), KoratioBlocks.ONYX_ORE.get());

            tag(BlockTags.DRAGON_IMMUNE).add(KoratioBlocks.FANTASIA_PORTAL.get());
            tag(BlockTags.WITHER_IMMUNE).add(KoratioBlocks.FANTASIA_PORTAL.get());

            tag(BlockTags.SWORD_EFFICIENT).add(
                    KoratioBlocks.GILDED_VINES.get()
            ).addTag(KoratioTags.Blocks.LEAF_PANES);

            tag(BlockTags.MINEABLE_WITH_AXE).add(
                    KoratioBlocks.RAINBOW_CAMPFIRE.get(),
                    KoratioBlocks.GREEN_MUSHROOM.get(),
                    KoratioBlocks.PURPLE_MUSHROOM.get(),
                    KoratioBlocks.GREEN_MUSHROOM_BLOCK.get(),
                    KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get(),
                    KoratioBlocks.PANGO_CHEST.get(),
                    KoratioBlocks.RUGONA_CHEST.get(),
                    KoratioBlocks.VARESO_CHEST.get(),
                    KoratioBlocks.CANDY_CHEST.get(),
                    KoratioBlocks.ELVEN_CHEST.get(),
                    KoratioBlocks.BLUE_ELVEN_CHEST.get(),
                    KoratioBlocks.CYAN_ELVEN_CHEST.get(),
                    KoratioBlocks.GREEN_ELVEN_CHEST.get(),
                    KoratioBlocks.TRAPPED_PANGO_CHEST.get(),
                    KoratioBlocks.TRAPPED_RUGONA_CHEST.get(),
                    KoratioBlocks.TRAPPED_VARESO_CHEST.get(),
                    KoratioBlocks.TRAPPED_CANDY_CHEST.get(),
                    KoratioBlocks.TRAPPED_ELVEN_CHEST.get(),
                    KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get(),
                    KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get(),
                    KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get(),
                    KoratioBlocks.FANTASIA_GRASS.get(),
                    KoratioBlocks.TALL_FANTASIA_GRASS.get(),
                    KoratioBlocks.GILDED_VINES.get(),
                    KoratioBlocks.PANGO_BOOKSHELF.get(),
                    KoratioBlocks.RUGONA_BOOKSHELF.get(),
                    KoratioBlocks.VARESO_BOOKSHELF.get(),
                    KoratioBlocks.CANDY_BOOKSHELF.get(),
                    KoratioBlocks.CHOCOLATE_OAK_BOOKSHELF.get(),
                    KoratioBlocks.ELVEN_BOOKSHELF.get(),
                    KoratioBlocks.BLUE_ELVEN_BOOKSHELF.get(),
                    KoratioBlocks.CYAN_ELVEN_BOOKSHELF.get(),
                    KoratioBlocks.GREEN_ELVEN_BOOKSHELF.get()
            ).addTags(KoratioTags.Blocks.TALL_WOODEN_DOORS, KoratioTags.Blocks.GINGERBREAD_LABYRINTH_BLOCKS);
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    KoratioBlocks.COOKIE_ORE.get(),
                    KoratioBlocks.DEEPSLATE_COOKIE_ORE.get(),
                    KoratioBlocks.RAINBOW_GEM_BLOCK.get(),
                    KoratioBlocks.RUBY_BLOCK.get(),
                    KoratioBlocks.SAPPHIRE_BLOCK.get(),
                    KoratioBlocks.TOPAZ_BLOCK.get(),
                    KoratioBlocks.ONYX_BLOCK.get(),
                    KoratioBlocks.RUBY_ORE.get(),
                    KoratioBlocks.DEEPSLATE_RUBY_ORE.get(),
                    KoratioBlocks.SAPPHIRE_ORE.get(),
                    KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                    KoratioBlocks.TOPAZ_ORE.get(),
                    KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get(),
                    KoratioBlocks.ONYX_ORE.get(),
                    KoratioBlocks.DEEPSLATE_ONYX_ORE.get(),
                    KoratioBlocks.RAINBOW_LANTERN.get(),
                    KoratioBlocks.RAINBOW_CRYSTAL_BLOCK.get(),
                    KoratioBlocks.BUDDING_RAINBOW_CRYSTAL.get(),
                    KoratioBlocks.SMALL_RAINBOW_CRYSTAL_BUD.get(),
                    KoratioBlocks.MEDIUM_RAINBOW_CRYSTAL_BUD.get(),
                    KoratioBlocks.LARGE_RAINBOW_CRYSTAL_BUD.get(),
                    KoratioBlocks.RAINBOW_CRYSTAL_CLUSTER.get(),
                    KoratioBlocks.TALL_IRON_DOOR.get(),
                    KoratioBlocks.TALL_COPPER_DOOR.get(),
                    KoratioBlocks.TALL_EXPOSED_COPPER_DOOR.get(),
                    KoratioBlocks.TALL_WEATHERED_COPPER_DOOR.get(),
                    KoratioBlocks.TALL_OXIDIZED_COPPER_DOOR.get(),
                    KoratioBlocks.TALL_WAXED_COPPER_DOOR.get(),
                    KoratioBlocks.TALL_WAXED_EXPOSED_COPPER_DOOR.get(),
                    KoratioBlocks.TALL_WAXED_WEATHERED_COPPER_DOOR.get(),
                    KoratioBlocks.TALL_WAXED_OXIDIZED_COPPER_DOOR.get()
            );
            tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                    KoratioBlocks.FLIPPED_FARMLAND.get(),
                    KoratioBlocks.WHITE_SUGAR_BLOCK.get(),
                    KoratioBlocks.LIGHT_GRAY_SUGAR_BLOCK.get(),
                    KoratioBlocks.GRAY_SUGAR_BLOCK.get(),
                    KoratioBlocks.BLACK_SUGAR_BLOCK.get(),
                    KoratioBlocks.BROWN_SUGAR_BLOCK.get(),
                    KoratioBlocks.RED_SUGAR_BLOCK.get(),
                    KoratioBlocks.ORANGE_SUGAR_BLOCK.get(),
                    KoratioBlocks.YELLOW_SUGAR_BLOCK.get(),
                    KoratioBlocks.LIME_SUGAR_BLOCK.get(),
                    KoratioBlocks.GREEN_SUGAR_BLOCK.get(),
                    KoratioBlocks.CYAN_SUGAR_BLOCK.get(),
                    KoratioBlocks.LIGHT_BLUE_SUGAR_BLOCK.get(),
                    KoratioBlocks.BLUE_SUGAR_BLOCK.get(),
                    KoratioBlocks.PURPLE_SUGAR_BLOCK.get(),
                    KoratioBlocks.MAGENTA_SUGAR_BLOCK.get(),
                    KoratioBlocks.PINK_SUGAR_BLOCK.get()
            );
            tag(BlockTags.MINEABLE_WITH_HOE).addTag(
                    KoratioTags.Blocks.LEAF_PANES
            );
            tag(BlockTags.NEEDS_IRON_TOOL).add(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), KoratioBlocks.RUBY_BLOCK.get(), KoratioBlocks.SAPPHIRE_BLOCK.get(), KoratioBlocks.TOPAZ_BLOCK.get(), KoratioBlocks.ONYX_BLOCK.get(), KoratioBlocks.RUBY_ORE.get(), KoratioBlocks.SAPPHIRE_ORE.get(), KoratioBlocks.TOPAZ_ORE.get(), KoratioBlocks.ONYX_ORE.get(), KoratioBlocks.DEEPSLATE_RUBY_ORE.get(), KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get(), KoratioBlocks.DEEPSLATE_ONYX_ORE.get());
            tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(KoratioBlocks.RUBY_ORE.get(), KoratioBlocks.SAPPHIRE_ORE.get(), KoratioBlocks.TOPAZ_ORE.get(), KoratioBlocks.ONYX_ORE.get());
            tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(KoratioBlocks.DEEPSLATE_RUBY_ORE.get(), KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get(), KoratioBlocks.DEEPSLATE_ONYX_ORE.get());
            tag(Tags.Blocks.ORE_RATES_SINGULAR).add(KoratioBlocks.RUBY_ORE.get(), KoratioBlocks.SAPPHIRE_ORE.get(), KoratioBlocks.TOPAZ_ORE.get(), KoratioBlocks.ONYX_ORE.get(), KoratioBlocks.DEEPSLATE_RUBY_ORE.get(), KoratioBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), KoratioBlocks.DEEPSLATE_TOPAZ_ORE.get(), KoratioBlocks.DEEPSLATE_ONYX_ORE.get());
        }

        @Override
        public String getName() {
            return "Koratio Block Tags";
        }
    }

    public static class FluidTagGenerator extends FluidTagsProvider {

        public FluidTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
            super(output, provider, Koratio.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(KoratioTags.Fluids.MOLTEN_SUGAR).add(KoratioFluids.MOLTEN_WHITE_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_WHITE_SUGAR.get(),
                    KoratioFluids.MOLTEN_LIGHT_GRAY_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_LIGHT_GRAY_SUGAR.get(),
                    KoratioFluids.MOLTEN_GRAY_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_GRAY_SUGAR.get(),
                    KoratioFluids.MOLTEN_BLACK_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_BLACK_SUGAR.get(),
                    KoratioFluids.MOLTEN_BROWN_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_BROWN_SUGAR.get(),
                    KoratioFluids.MOLTEN_RED_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_RED_SUGAR.get(),
                    KoratioFluids.MOLTEN_ORANGE_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_ORANGE_SUGAR.get(),
                    KoratioFluids.MOLTEN_YELLOW_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_YELLOW_SUGAR.get(),
                    KoratioFluids.MOLTEN_LIME_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_LIME_SUGAR.get(),
                    KoratioFluids.MOLTEN_GREEN_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_GREEN_SUGAR.get(),
                    KoratioFluids.MOLTEN_CYAN_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_CYAN_SUGAR.get(),
                    KoratioFluids.MOLTEN_LIGHT_BLUE_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_LIGHT_BLUE_SUGAR.get(),
                    KoratioFluids.MOLTEN_BLUE_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_BLUE_SUGAR.get(),
                    KoratioFluids.MOLTEN_PURPLE_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_PURPLE_SUGAR.get(),
                    KoratioFluids.MOLTEN_MAGENTA_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_MAGENTA_SUGAR.get(),
                    KoratioFluids.MOLTEN_PINK_SUGAR.get(), KoratioFluids.FLOWING_MOLTEN_PINK_SUGAR.get());
        }
    }

    public static class EntityTagGenerator extends EntityTypeTagsProvider {

        public EntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
            super(output, provider, Koratio.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(EntityTypeTags.ZOMBIES).add(KoratioEntityType.DEMONIC_ZOMBIE.get(), KoratioEntityType.DEMONIC_ZOMBIE_HORSE.get());
            tag(EntityTypeTags.SKELETONS).add(KoratioEntityType.DEMONIC_SKELETON.get(), KoratioEntityType.DEMONIC_SKELETON_HORSE.get());
            tag(KoratioTags.Entities.DEMONS).add(KoratioEntityType.DEMONIC_ZOMBIE.get(), KoratioEntityType.DEMONIC_SKELETON.get(), KoratioEntityType.DEMONIC_SOLDIER.get());

            tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(KoratioEntityType.CRYSTALLIZE.get());
            tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(KoratioEntityType.FLUFFER.get());
        }
    }

    public static class MagicalCatTagGenerator extends TagsProvider<MagicalCatVariant> {

        public MagicalCatTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
            super(output, KoratioRegistries.MAGICAL_CAT_VARIANT_KEY, provider, Koratio.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(KoratioTags.MagicalCatVariants.DEFAULT_SPAWNS).add(MagicalCatVariants.AKUMA.getKey(), MagicalCatVariants.FUKU.getKey(), MagicalCatVariants.FUUN.getKey(), MagicalCatVariants.OJI.getKey(), MagicalCatVariants.GEKIDO.getKey(), MagicalCatVariants.GOMAN.getKey(), MagicalCatVariants.SENBO.getKey(), MagicalCatVariants.TAIDA.getKey());
        }
    }

    public static class PoiTypeTagGenerator extends PoiTypeTagsProvider {

        public PoiTypeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
            super(output, provider, Koratio.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(KoratioPoiTypes.OUTCAST.getKey());
        }
    }

    public static class BiomeTagGenerator extends BiomeTagsProvider {

        public BiomeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
            super(output, provider, Koratio.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(KoratioTags.Biomes.FANTASIA_BIOMES).add(
                    KoratioBiomes.FANTASIA_FIELDLANDS, KoratioBiomes.MUSHROOM_FOREST, KoratioBiomes.CANDY_CANE_VALLEY, KoratioBiomes.GILDED_FOREST,
                    KoratioBiomes.DEPTHS_OF_FANTASIA
            );
            tag(KoratioTags.Biomes.FOGGY_BIOMES).add(KoratioBiomes.MUSHROOM_FOREST);
        }
    }
}