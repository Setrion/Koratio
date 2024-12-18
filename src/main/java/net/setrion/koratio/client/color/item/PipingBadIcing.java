package net.setrion.koratio.client.color.item;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.world.item.PipingBagItem;

import javax.annotation.Nullable;

public record PipingBadIcing(int defaultColor) implements ItemTintSource {
    public static final MapCodec<PipingBadIcing> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(ExtraCodecs.RGB_COLOR_CODEC.fieldOf("default").forGetter(PipingBadIcing::defaultColor)).apply(instance, PipingBadIcing::new)
    );

    public PipingBadIcing() {
        this(-1);
    }

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        if (stack.getItem() instanceof PipingBagItem pipingBag) {
            if (pipingBag.isEmpty(stack)) {
                return defaultColor;
            } else {
                int color = pipingBag.getColor(stack).getColor();
                return ARGB.opaque(color);
            }
        }
        return defaultColor;
    }

    @Override
    public MapCodec<PipingBadIcing> type() {
        return MAP_CODEC;
    }
}