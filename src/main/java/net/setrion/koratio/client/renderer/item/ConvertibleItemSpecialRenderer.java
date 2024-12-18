package net.setrion.koratio.client.renderer.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.setrion.koratio.util.ConversionUtils;
import net.setrion.koratio.world.item.Convertible;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class ConvertibleItemSpecialRenderer implements ItemModel {
    static final ItemModel INSTANCE = new ConvertibleItemSpecialRenderer();

    @Override
    public void update(ItemStackRenderState renderState, ItemStack stack, ItemModelResolver resolver, ItemDisplayContext context, @Nullable ClientLevel level, @Nullable LivingEntity entity, int i) {
        if (stack.getItem() instanceof Convertible convertible) {
            Item item = convertible.getConvertibles().get(ConversionUtils.getConversionDimension(stack));
            if (item != null) {
                ItemStack itemstack = new ItemStack(item);
                if (!itemstack.isEmpty()) {
                    resolver.appendItemLayers(renderState, itemstack, context, level, entity, i);
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static record Unbaked() implements ItemModel.Unbaked {
        public static final MapCodec<ConvertibleItemSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new ConvertibleItemSpecialRenderer.Unbaked());

        @Override
        public MapCodec<ConvertibleItemSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public ItemModel bake(ItemModel.BakingContext context) {
            return ConvertibleItemSpecialRenderer.INSTANCE;
        }

        @Override
        public void resolveDependencies(ResolvableModel.Resolver resolver) {
        }
    }
}