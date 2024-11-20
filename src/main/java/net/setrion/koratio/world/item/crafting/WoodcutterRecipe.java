package net.setrion.koratio.world.item.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioRecipeSerializer;
import net.setrion.koratio.registry.KoratioRecipeType;

public class WoodcutterRecipe extends SingleItemRecipe {
    public WoodcutterRecipe(String group, Ingredient ingredient, ItemStack stack) {
        super(group, ingredient, stack);
    }

    @Override
    public RecipeType<WoodcutterRecipe> getType() {
        return KoratioRecipeType.WOODCUTTING.get();
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.STONECUTTER;
    }

    @Override
    public RecipeSerializer<WoodcutterRecipe> getSerializer() {
        return KoratioRecipeSerializer.WOODCUTTER.get();
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