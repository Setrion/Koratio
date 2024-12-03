package net.setrion.koratio.world.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.setrion.koratio.registry.KoratioDataComponents;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.util.ConversionUtils;

import java.util.Map;

public class ConvertibleItem extends Item implements Convertible {

    Map<ResourceKey<DimensionType>, ConversionUtils.ConversionItem> CONVERT_MAP;
    int conversionTime;

    public ConvertibleItem(Map<ResourceKey<DimensionType>, ConversionUtils.ConversionItem> convertibles, int convertTime, Properties properties) {
        super(properties.component(KoratioDataComponents.CONVERTIBLE_DATA, new KoratioDataComponents.ConvertibleRecord(KoratioDimensions.FANTASIA_DIM_TYPE, 0)));
        CONVERT_MAP = convertibles;
        this.conversionTime = convertTime;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        ConversionUtils.inventoryTick(stack, level, entity, slotId);
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        ConversionUtils.entityTick(stack, entity);
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public Map<ResourceKey<DimensionType>, ConversionUtils.ConversionItem> getConvertibles() {
        return CONVERT_MAP;
    }

    @Override
    public int getConversionTime() {
        return conversionTime;
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        return shouldCauseReequipAnimation(oldStack, newStack, false);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (oldStack == newStack) {
            return false;
        }

        if (ConversionUtils.getConversionTime(newStack) != ConversionUtils.getConversionTime(oldStack)) {
            return false;
        } else {
            return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
        }
    }
}