package net.setrion.koratio.data.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
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
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;

public class CandyShaperRecipeCategory implements IRecipeCategory<CandyShaperRecipe> {

    private final IDrawable background;
    private final IDrawable tank_overlay;
    private final IDrawable icon;
    private final int xSize = 110;
    private final int ySize = 58;

    public CandyShaperRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/gui/jei/candy_shaper.png"), 0, 0, xSize, ySize);
        tank_overlay = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/gui/jei/candy_shaper.png"), 110, 0, 9, 56);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(KoratioBlocks.CANDY_SHAPER.get()));
    }

    @Override
    public RecipeType<CandyShaperRecipe> getRecipeType() {
        return KoratioJeiPlugin.CANDY_SHAPER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("koratio.candy_shaper_category");
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
    public void setRecipe(IRecipeLayoutBuilder builder, CandyShaperRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.OUTPUT, 89, 20).addIngredient(VanillaTypes.ITEM_STACK, recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
        for (int i = 0; i < 5; i++) {
            if (recipe.getFluidIngredients().get(i).getFluids()[0].getAmount() > 0) {
                builder.addSlot(RecipeIngredientRole.INPUT, 4 + (i * 8), 2)
                        .addIngredient(NeoForgeTypes.FLUID_STACK, recipe.getFluidIngredients().get(i).getFluids()[0])
                        .setFluidRenderer(2000, false, 7, 54)
                        .setOverlay(tank_overlay, -1, -1)
                        .addRichTooltipCallback((recipeSlotView, tooltip) -> recipeSlotView.getDisplayedIngredient(NeoForgeTypes.FLUID_STACK));
            }
        }
    }
}