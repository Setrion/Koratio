package net.setrion.koratio.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.setrion.koratio.registry.KoratioDataComponents;
import net.setrion.koratio.registry.KoratioDimensions;
import net.setrion.koratio.world.item.Convertible;

import java.awt.*;

public class ConversionUtils {

    public record ConversionItem(Item item, Color color) {}

    public static int getConversionTime(ItemStack stack) {
        if (!stack.getComponents().has(KoratioDataComponents.CONVERTIBLE_DATA.get())) return 0;
        return stack.getComponents().get(KoratioDataComponents.CONVERTIBLE_DATA.get()).convert_time();
    }

    public static void setConversionTime(ItemStack stack, int time) {
        if (stack.getComponents().has(KoratioDataComponents.CONVERTIBLE_DATA.get())) {
            stack.set(KoratioDataComponents.CONVERTIBLE_DATA.get(), new KoratioDataComponents.ConvertibleRecord(getConversionDimension(stack), time));
        }
    }

    public static ResourceKey<DimensionType> getConversionDimension(ItemStack stack) {
        if (!stack.getComponents().has(KoratioDataComponents.CONVERTIBLE_DATA.get())) return KoratioDimensions.FANTASIA_DIM_TYPE;
        return stack.getComponents().get(KoratioDataComponents.CONVERTIBLE_DATA.get()).dimension();
    }

    public static void setConversionDimension(ItemStack stack, ResourceKey<DimensionType> dimension) {
        if (stack.getComponents().has(KoratioDataComponents.CONVERTIBLE_DATA.get())) {
            stack.set(KoratioDataComponents.CONVERTIBLE_DATA.get(), new KoratioDataComponents.ConvertibleRecord(dimension, getConversionTime(stack)));
        }
    }

    public static void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId) {
        if (!level.isClientSide()) {
            if (stack.getItem() instanceof Convertible convertible) {
                ServerLevel serverLevel = (ServerLevel) level;
                ResourceKey<DimensionType> key = serverLevel.registryAccess().lookupOrThrow(Registries.DIMENSION_TYPE).getResourceKey(level.dimensionType()).orElseThrow();
                if (convertible.getConvertibles().containsKey(key)) {
                    if (!(ConversionUtils.getConversionDimension(stack) == KoratioDimensions.FANTASIA_DIM_TYPE)) {
                        if (ConversionUtils.getConversionTime(stack) >= convertible.getConversionTime()) {
                            Player player = (Player) entity;
                            player.getInventory().setItem(slotId, new ItemStack(convertible.getConvertibles().get(key).item(), stack.getCount()));
                        } else {
                            if (ConversionUtils.getConversionDimension(stack).equals(key)) {
                                ConversionUtils.setConversionTime(stack, ConversionUtils.getConversionTime(stack) + 1);
                            } else {
                                ConversionUtils.setConversionTime(stack, Math.max(0, ConversionUtils.getConversionTime(stack) - 1));
                            }
                        }
                    } else {
                        ConversionUtils.setConversionDimension(stack, key);
                    }
                }
            }
        }
    }

    public static void entityTick(ItemStack stack, ItemEntity entity) {
        Level level = entity.level();
        if (!level.isClientSide()) {
            if (stack.getItem() instanceof Convertible convertible) {
                ServerLevel serverLevel = (ServerLevel) level;
                ResourceKey<DimensionType> key = serverLevel.registryAccess().lookupOrThrow(Registries.DIMENSION_TYPE).getResourceKey(level.dimensionType()).orElseThrow();
                if (convertible.getConvertibles().containsKey(key)) {
                    if (!(ConversionUtils.getConversionDimension(stack) == KoratioDimensions.FANTASIA_DIM_TYPE)) {
                        if (ConversionUtils.getConversionTime(stack) >= convertible.getConversionTime()) {
                            entity.setItem(new ItemStack(convertible.getConvertibles().get(key).item()));
                        } else {
                            if (ConversionUtils.getConversionDimension(stack).equals(key)) {
                                ConversionUtils.setConversionTime(stack, ConversionUtils.getConversionTime(stack) + 1);
                            } else {
                                ConversionUtils.setConversionTime(stack, Math.max(0, ConversionUtils.getConversionTime(stack) - 1));
                            }
                        }
                    } else {
                        ConversionUtils.setConversionDimension(stack, key);
                    }
                }
            }
        }
    }
}