package net.setrion.koratio.world.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.Map;

public interface Convertible {

    Map<ResourceKey<DimensionType>, Item> getConvertibles();
    default int getConversionTime() {
        return 600;
    };
}