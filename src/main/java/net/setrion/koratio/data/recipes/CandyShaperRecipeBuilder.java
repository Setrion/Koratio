package net.setrion.koratio.data.recipes;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CandyShaperRecipeBuilder implements RecipeBuilder {

    private final ItemStack result; // Neo: add stack result support
    private final ItemStack template;
    private final NonNullList<SizedFluidIngredient> ingredients = NonNullList.create();
    private final Map<String, Criterion<?>> criteria;

    private CandyShaperRecipeBuilder(ItemLike result, ItemLike template) {
        this(new ItemStack(result), new ItemStack(template));
    }

    private CandyShaperRecipeBuilder(ItemStack result, ItemStack template) {
        this.criteria = new LinkedHashMap<>();
        this.template = template;
        this.result = result;
    }

    public CandyShaperRecipeBuilder requires(FluidIngredient ingredient, int amount) {
        this.ingredients.add(SizedFluidIngredient.of(ingredient.fluids().get(0).value(), amount));
        return this;
    }

    public static CandyShaperRecipeBuilder recipe(ItemLike result, ItemLike template) {
        return new CandyShaperRecipeBuilder(new ItemStack(result), new ItemStack(template));
    }

    @Override
    public RecipeBuilder group(@Nullable String s) {
        return null;
    }

    @Override
    public Item getResult() {
        return result.getItem();
    }

    public CandyShaperRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey) {
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey)).rewards(AdvancementRewards.Builder.recipe(resourceKey)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancement$builder);
        this.criteria.forEach(advancement$builder::addCriterion);
        CandyShaperRecipe candyShaperRecipe = new CandyShaperRecipe(this.result, this.template, this.ingredients);
        recipeOutput.accept(resourceKey, candyShaperRecipe, advancement$builder.build(resourceKey.location().withPrefix("recipes/candy/")));
    }
}