package net.setrion.koratio.client.renderer.blockentity;

import java.util.EnumMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.level.block.entity.ChestBlockEntity;

public class KoratioChestRenderer<T extends ChestBlockEntity> extends ChestRenderer<T> {
    public static final Map<Block, EnumMap<ChestType, Material>> MATERIALS;

    static {
        ImmutableMap.Builder<Block, EnumMap<ChestType, Material>> builder = ImmutableMap.builder();

        builder.put(KoratioBlocks.PANGO_CHEST.get(), chestMaterial("pango"));
        builder.put(KoratioBlocks.RUGONA_CHEST.get(), chestMaterial("rugona"));
        builder.put(KoratioBlocks.VARESO_CHEST.get(), chestMaterial("vareso"));
        builder.put(KoratioBlocks.NIGHY_CHEST.get(), chestMaterial("nighy"));

        MATERIALS = builder.build();
    }

    public KoratioChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(T blockEntity, ChestType chestType) {
        EnumMap<ChestType, Material> b = MATERIALS.get(blockEntity.getBlockState().getBlock());

        if (b == null) return super.getMaterial(blockEntity, chestType);

        Material material = b.get(chestType);

        return material != null ? material : super.getMaterial(blockEntity, chestType);
    }

    private static EnumMap<ChestType, Material> chestMaterial(String type) {
        EnumMap<ChestType, Material> map = new EnumMap<>(ChestType.class);

        map.put(ChestType.SINGLE, new Material(Sheets.CHEST_SHEET, Koratio.prefix("entity/chest/" + type)));
        map.put(ChestType.LEFT, new Material(Sheets.CHEST_SHEET, Koratio.prefix("entity/chest/" + type + "_left")));
        map.put(ChestType.RIGHT, new Material(Sheets.CHEST_SHEET, Koratio.prefix("entity/chest/" + type + "_right")));

        return map;
    }
}