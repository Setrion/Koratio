package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import net.setrion.koratio.world.item.crafting.WoodcutterRecipe;

public class KoratioRecipeType {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Koratio.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<WoodcutterRecipe>> WOODCUTTING = RECIPE_TYPES.register("woodcutting", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "woodcutting")));
    public static final DeferredHolder<RecipeType<?>, RecipeType<CandyShaperRecipe>> CANDY_SHAPING = RECIPE_TYPES.register("candy_shaping", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "candy_shaping")));

}