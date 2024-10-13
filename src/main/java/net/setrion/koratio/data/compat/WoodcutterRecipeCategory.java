package net.setrion.koratio.data.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.item.crafting.WoodcutterRecipe;

public class WoodcutterRecipeCategory implements IRecipeCategory<WoodcutterRecipe> {

    private final IDrawable background;
    private final IDrawable icon;
    private final int xSize = 82;
    private final int ySize = 34;

    public WoodcutterRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/gui/jei/woodcutter.png"), 0, 0, xSize, ySize);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(KoratioBlocks.WOODCUTTER.get()));
    }

    @Override
    public RecipeType getRecipeType() {
        return KoratioJeiPlugin.WOODCUTTER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("koratio.woodcutter_category");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WoodcutterRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredient(VanillaTypes.ITEM_STACK, recipe.getIngredients().get(0).getItems()[0]);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addIngredient(VanillaTypes.ITEM_STACK, recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }
}