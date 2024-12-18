package net.setrion.koratio.client.color.item;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.util.ConversionUtils;
import net.setrion.koratio.world.item.Convertible;

import javax.annotation.Nullable;

public record ConvertibleTransparency(int defaultColor) implements ItemTintSource {
    public static final MapCodec<ConvertibleTransparency> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(ExtraCodecs.RGB_COLOR_CODEC.fieldOf("default").forGetter(ConvertibleTransparency::defaultColor)).apply(instance, ConvertibleTransparency::new)
    );

    public ConvertibleTransparency() {
        this(-1);
    }

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        if (stack.getItem() instanceof Convertible convertible) {
            if (ConversionUtils.getConversionTime(stack) <= 0) {
                return defaultColor;
            } else {
                float alpha = 255f - (255f / convertible.getConversionTime()) * ConversionUtils.getConversionTime(stack);
                return ARGB.color((int) alpha, 255, 255, 255);
            }
        }
        return defaultColor;
    }

    @Override
    public MapCodec<ConvertibleTransparency> type() {
        return MAP_CODEC;
    }
}