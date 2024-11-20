package net.setrion.koratio.data.compat;

/*import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioRecipeType;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import net.setrion.koratio.world.item.crafting.WoodcutterRecipe;

import javax.annotation.Nullable;

@JeiPlugin
public class KoratioJeiPlugin implements IModPlugin {

    public static final RecipeType<WoodcutterRecipe> WOODCUTTER_RECIPE_TYPE = RecipeType.create(Koratio.MOD_ID, "woodcutter", WoodcutterRecipe.class);
    public static final RecipeType<CandyShaperRecipe> CANDY_SHAPER_RECIPE_TYPE = RecipeType.create(Koratio.MOD_ID, "candy_shaper", CandyShaperRecipe.class);
    public static IJeiHelpers jeiHelpers = null;

    @Nullable
    private WoodcutterRecipeCategory woodcutterRecipeCategory;
    private CandyShaperRecipeCategory candyShaperRecipeCategory;

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(KoratioItems.RAINBOW_CAMPFIRE.get()), RecipeTypes.CAMPFIRE_COOKING);
        registration.addRecipeCatalyst(new ItemStack(KoratioItems.ANCIENT_FURNACE.get()), RecipeTypes.SMELTING, RecipeTypes.FUELING);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(KoratioItems.SCROLL.get(), ScrollSubtypeInterpreter.INSTANCE);
        registration.registerSubtypeInterpreter(KoratioItems.PIPING_BAG.get(), PipingBagSubtypeInterpreter.INSTANCE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registration.addRecipeCategories(woodcutterRecipeCategory = new WoodcutterRecipeCategory(guiHelper));
        registration.addRecipeCategories(candyShaperRecipeCategory = new CandyShaperRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        jeiHelpers = registration.getJeiHelpers();

        ClientLevel world = Minecraft.getInstance().level;
        if (world == null) return;
        registration.addRecipes(WOODCUTTER_RECIPE_TYPE, world.getRecipeManager().getAllRecipesFor(KoratioRecipeType.WOODCUTTING.get()).stream().map(RecipeHolder::value).toList());
        registration.addRecipes(CANDY_SHAPER_RECIPE_TYPE, world.getRecipeManager().getAllRecipesFor(KoratioRecipeType.CANDY_SHAPING.get()).stream().map(RecipeHolder::value).toList());
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "jei_plugin");
    }
}*/