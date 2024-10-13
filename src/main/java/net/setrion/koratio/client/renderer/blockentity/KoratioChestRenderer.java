package net.setrion.koratio.client.renderer.blockentity;

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

import java.util.EnumMap;
import java.util.Map;

public class KoratioChestRenderer<T extends net.minecraft.world.level.block.entity.ChestBlockEntity> extends ChestRenderer<T> {
    public static final Map<Block, EnumMap<ChestType, Material>> MATERIALS;

    static {
        ImmutableMap.Builder<Block, EnumMap<ChestType, Material>> builder = ImmutableMap.builder();

        builder.put(KoratioBlocks.PANGO_CHEST.get(), chestMaterial("pango", false));
        builder.put(KoratioBlocks.RUGONA_CHEST.get(), chestMaterial("rugona", false));
        builder.put(KoratioBlocks.VARESO_CHEST.get(), chestMaterial("vareso", false));
        builder.put(KoratioBlocks.CANDY_CHEST.get(), chestMaterial("candy", false));
        builder.put(KoratioBlocks.ELVEN_CHEST.get(), chestMaterial("elven", false));
        builder.put(KoratioBlocks.BLUE_ELVEN_CHEST.get(), chestMaterial("blue_elven", false));
        builder.put(KoratioBlocks.CYAN_ELVEN_CHEST.get(), chestMaterial("cyan_elven", false));
        builder.put(KoratioBlocks.GREEN_ELVEN_CHEST.get(), chestMaterial("green_elven", false));

        builder.put(KoratioBlocks.TRAPPED_PANGO_CHEST.get(), chestMaterial("pango", true));
        builder.put(KoratioBlocks.TRAPPED_RUGONA_CHEST.get(), chestMaterial("rugona", true));
        builder.put(KoratioBlocks.TRAPPED_VARESO_CHEST.get(), chestMaterial("vareso", true));
        builder.put(KoratioBlocks.TRAPPED_CANDY_CHEST.get(), chestMaterial("candy", true));
        builder.put(KoratioBlocks.TRAPPED_ELVEN_CHEST.get(), chestMaterial("elven", true));
        builder.put(KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get(), chestMaterial("blue_elven", true));
        builder.put(KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get(), chestMaterial("cyan_elven", true));
        builder.put(KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get(), chestMaterial("green_elven", true));

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

    private static EnumMap<ChestType, Material> chestMaterial(String type, boolean trapped) {
        EnumMap<ChestType, Material> map = new EnumMap<>(ChestType.class);

        map.put(ChestType.SINGLE, new Material(Sheets.CHEST_SHEET, Koratio.prefix("entity/chest/" + type + "/" + (trapped ? "trapped" : "single"))));
        map.put(ChestType.LEFT, new Material(Sheets.CHEST_SHEET, Koratio.prefix("entity/chest/" + type + "/" + (trapped ? "trapped_left" : "left"))));
        map.put(ChestType.RIGHT, new Material(Sheets.CHEST_SHEET, Koratio.prefix("entity/chest/" + type + "/" + (trapped ? "trapped_right" : "right"))));

        return map;
    }
}