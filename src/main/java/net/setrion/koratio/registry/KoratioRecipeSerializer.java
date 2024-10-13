package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import net.setrion.koratio.world.item.crafting.WoodcutterRecipe;

public class KoratioRecipeSerializer {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Koratio.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WoodcutterRecipe>> WOODCUTTER = RECIPE_SERIALIZERS.register("woodcutter", WoodcutterRecipe.WoodcutterRecipeSerializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CandyShaperRecipe>> CANDY_SHAPER = RECIPE_SERIALIZERS.register("candy_shaper", CandyShaperRecipe.Serializer::new);

}