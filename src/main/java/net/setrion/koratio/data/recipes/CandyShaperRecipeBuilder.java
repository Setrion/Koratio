package net.setrion.koratio.data.recipes;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CandyShaperRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final ItemStack stackResult;
    private SizedFluidIngredient mainFluid;
    private SizedFluidIngredient secondFluid;
    private final Map<String, Criterion<?>> criteria;

    private CandyShaperRecipeBuilder(ItemLike result, SizedFluidIngredient mainFluid, SizedFluidIngredient secondFluid) {
        this(new ItemStack(result), mainFluid, secondFluid);
    }

    private CandyShaperRecipeBuilder(ItemStack result, SizedFluidIngredient mainFluid, SizedFluidIngredient secondFluid) {
        this.criteria = new LinkedHashMap<>();
        this.result = result.getItem();
        this.stackResult = result;
        this.mainFluid = mainFluid;
        this.secondFluid = secondFluid;
    }

    public static CandyShaperRecipeBuilder recipe(ItemLike result, SizedFluidIngredient mainFluid, SizedFluidIngredient secondFluid) {
        return new CandyShaperRecipeBuilder(new ItemStack(result), mainFluid, secondFluid);
    }

    @Override
    public RecipeBuilder group(@Nullable String s) {
        return null;
    }

    @Override
    public Item getResult() {
        return result;
    }

    public CandyShaperRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancement$builder);
        this.criteria.forEach(advancement$builder::addCriterion);
        CandyShaperRecipe candyShaperRecipe = new CandyShaperRecipe(this.mainFluid, Optional.ofNullable(this.secondFluid), this.stackResult);
        recipeOutput.accept(id, candyShaperRecipe, advancement$builder.build(id.withPrefix("recipes/")));
    }
}