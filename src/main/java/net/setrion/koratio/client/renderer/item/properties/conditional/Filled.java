package net.setrion.koratio.client.renderer.item.properties.conditional;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.world.item.PipingBagItem;

import javax.annotation.Nullable;

public record Filled() implements ConditionalItemModelProperty {
    public static final MapCodec<Filled> MAP_CODEC = MapCodec.unit(new Filled());

    @Override
    public boolean get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int i, ItemDisplayContext context) {
        if (stack.getItem() instanceof PipingBagItem pipingBag) {
            return !pipingBag.isEmpty(stack);
        }
        return true;
    }

    @Override
    public MapCodec<Filled> type() {
        return MAP_CODEC;
    }
}
