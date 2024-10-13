package net.setrion.koratio.world.item.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioRecipeSerializer;
import net.setrion.koratio.registry.KoratioRecipeType;

public class WoodcutterRecipe extends SingleItemRecipe {
    public WoodcutterRecipe(String group, Ingredient ingredient, ItemStack stack) {
        super(KoratioRecipeType.WOODCUTTING.get(), KoratioRecipeSerializer.WOODCUTTER.get(), group, ingredient, stack);
    }

    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.item());
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(KoratioBlocks.WOODCUTTER.get());
    }

    public static class WoodcutterRecipeSerializer extends SingleItemRecipe.Serializer<WoodcutterRecipe> {
        public WoodcutterRecipeSerializer() {
            super(WoodcutterRecipe::new);
        }
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}