package net.setrion.koratio.data;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.block.FantasiaPortalBlock;

import java.util.stream.Stream;

public class ModelGenerator extends ModelProvider {

    public ModelGenerator(PackOutput output) {
        super(output, Koratio.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(KoratioBlocks.FANTASIA_PORTAL.get()).with(PropertyDispatch.property(FantasiaPortalBlock.FACING).select(Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(KoratioBlocks.FANTASIA_PORTAL.get())).with(VariantProperties.X_ROT, VariantProperties.Rotation.R0)).select(Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(KoratioBlocks.FANTASIA_PORTAL.get())).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)).select(Direction.EAST, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(KoratioBlocks.FANTASIA_PORTAL.get())).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)).select(Direction.WEST, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(KoratioBlocks.FANTASIA_PORTAL.get())).with(VariantProperties.X_ROT, VariantProperties.Rotation.R270))));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(KoratioBlocks.MINIATURE_FANTASIA_PORTAL.get(), Koratio.prefix("block/miniature/fantasia_portal")));

        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(KoratioBlocks.DECRYPTING_TABLE.get(), ModelTemplates.CUBE.create(KoratioBlocks.DECRYPTING_TABLE.get(), new TextureMapping().put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(KoratioBlocks.DECRYPTING_TABLE.get(), "_front")).put(TextureSlot.DOWN, TextureMapping.getBlockTexture(KoratioBlocks.DECRYPTING_TABLE.get(), "_bottom")).put(TextureSlot.UP, TextureMapping.getBlockTexture(KoratioBlocks.DECRYPTING_TABLE.get(), "_top")).put(TextureSlot.NORTH, TextureMapping.getBlockTexture(KoratioBlocks.DECRYPTING_TABLE.get(), "_front")).put(TextureSlot.EAST, TextureMapping.getBlockTexture(KoratioBlocks.DECRYPTING_TABLE.get(), "_side")).put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(KoratioBlocks.DECRYPTING_TABLE.get(), "_side")).put(TextureSlot.WEST, TextureMapping.getBlockTexture(KoratioBlocks.DECRYPTING_TABLE.get(), "_front")), blockModels.modelOutput)));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(KoratioBlocks.CANDY_SHAPER.get(), ModelTemplates.CUBE.create(KoratioBlocks.CANDY_SHAPER.get(), new TextureMapping().put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(KoratioBlocks.CANDY_SHAPER.get(), "_front")).put(TextureSlot.DOWN, TextureMapping.getBlockTexture(KoratioBlocks.CANDY_SHAPER.get(), "_bottom")).put(TextureSlot.UP, TextureMapping.getBlockTexture(KoratioBlocks.CANDY_SHAPER.get(), "_top")).put(TextureSlot.NORTH, TextureMapping.getBlockTexture(KoratioBlocks.CANDY_SHAPER.get(), "_front")).put(TextureSlot.EAST, TextureMapping.getBlockTexture(KoratioBlocks.CANDY_SHAPER.get(), "_side")).put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(KoratioBlocks.CANDY_SHAPER.get(), "_side")).put(TextureSlot.WEST, TextureMapping.getBlockTexture(KoratioBlocks.CANDY_SHAPER.get(), "_front")), blockModels.modelOutput)));
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.listElements().filter((holder) -> {
            return false;
        });
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return BuiltInRegistries.ITEM.listElements().filter((holder) -> {
            return false;
        });
    }
}