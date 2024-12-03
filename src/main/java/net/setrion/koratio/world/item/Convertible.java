package net.setrion.koratio.world.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.dimension.DimensionType;
import net.setrion.koratio.util.ConversionUtils;

import java.util.Map;

public interface Convertible {

    Map<ResourceKey<DimensionType>, ConversionUtils.ConversionItem> getConvertibles();
    default int getConversionTime() {
        return 600;
    };
}