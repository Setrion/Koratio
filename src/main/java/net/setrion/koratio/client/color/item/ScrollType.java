package net.setrion.koratio.client.color.item;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.scroll.ScrollUtils;
import net.setrion.koratio.world.item.ScrollItem;

import javax.annotation.Nullable;

public record ScrollType(int defaultColor) implements ItemTintSource {
    public static final MapCodec<ScrollType> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(ExtraCodecs.RGB_COLOR_CODEC.fieldOf("default").forGetter(ScrollType::defaultColor)).apply(instance, ScrollType::new)
    );

    public ScrollType() {
        this(-1);
    }

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        if (stack.getItem() instanceof ScrollItem) {
            if (!ScrollUtils.hasScrollData(stack)) {
                return defaultColor;
            } else {
                return ARGB.opaque(ScrollUtils.getScroll(stack).type().getColor());
            }
        }
        return defaultColor;
    }

    @Override
    public MapCodec<ScrollType> type() {
        return MAP_CODEC;
    }
}