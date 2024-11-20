package net.setrion.koratio.data.compat;

/*import mezz.jei.api.constants.VanillaTypes;
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
    private final int xSize = 88;
    private final int ySize = 46;

    public CandyShaperRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/gui/jei/candy_shaper.png"), 0, 0, xSize, ySize);
        tank_overlay = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/gui/jei/candy_shaper.png"), 88, 0, 18, 46);
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
        builder.addSlot(RecipeIngredientRole.OUTPUT, 67, 15).addIngredient(VanillaTypes.ITEM_STACK, recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
        builder.addSlot(RecipeIngredientRole.CATALYST, 42, 24).addIngredient(VanillaTypes.ITEM_STACK, recipe.getTemplateItem(Minecraft.getInstance().level.registryAccess()));
        for (int i = 0; i < recipe.getFluidIngredients().size(); i++) {
            if (recipe.getFluidIngredients().get(i).getFluids()[0].getAmount() > 0) {
                builder.addSlot(RecipeIngredientRole.INPUT, 1 + (i * 19), 1)
                        .addIngredient(NeoForgeTypes.FLUID_STACK, recipe.getFluidIngredients().get(i).getFluids()[0])
                        .setFluidRenderer(2000, false, 16, 44)
                        .setOverlay(tank_overlay, -1, -1)
                        .addRichTooltipCallback((recipeSlotView, tooltip) -> recipeSlotView.getDisplayedIngredient(NeoForgeTypes.FLUID_STACK));
            }
        }
    }
}*/