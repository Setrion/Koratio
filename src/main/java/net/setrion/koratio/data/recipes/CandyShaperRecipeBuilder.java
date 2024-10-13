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
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.setrion.koratio.fluids.capability.SizedFluidIngredient;
import net.setrion.koratio.registry.KoratioFluids;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CandyShaperRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final ItemStack stackResult;
    private SizedFluidIngredient ingredient;
    private SizedFluidIngredient ingredient1;
    private SizedFluidIngredient ingredient2;
    private SizedFluidIngredient ingredient3;
    private SizedFluidIngredient ingredient4;
    private final Map<String, Criterion<?>> criteria;

    private CandyShaperRecipeBuilder(ItemLike result, SizedFluidIngredient ingredient, SizedFluidIngredient ingredient1, SizedFluidIngredient ingredient2, SizedFluidIngredient ingredient3, SizedFluidIngredient ingredient4) {
        this(new ItemStack(result), ingredient, ingredient1, ingredient2, ingredient3, ingredient4);
    }

    private CandyShaperRecipeBuilder(ItemStack result, SizedFluidIngredient ingredient, SizedFluidIngredient ingredient1, SizedFluidIngredient ingredient2, SizedFluidIngredient ingredient3, SizedFluidIngredient ingredient4) {
        this.criteria = new LinkedHashMap();
        this.result = result.getItem();
        this.stackResult = result;
        this.ingredient = ingredient;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
        this.ingredient4 = ingredient4;
    }

    public static CandyShaperRecipeBuilder recipe(ItemLike result, SizedFluidIngredient ingredient, SizedFluidIngredient ingredient1, SizedFluidIngredient ingredient2, SizedFluidIngredient ingredient3, SizedFluidIngredient ingredient4) {
        return new CandyShaperRecipeBuilder(new ItemStack(result), ingredient, ingredient1, ingredient2, ingredient3, ingredient4);
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
        if (this.ingredient.ingredient() == FluidIngredient.empty()) {
            this.ingredient = new SizedFluidIngredient(FluidIngredient.of(KoratioFluids.MOLTEN_SUGAR.get()), 0);
        }
        if (this.ingredient1.ingredient() == FluidIngredient.empty()) {
            this.ingredient1 = new SizedFluidIngredient(FluidIngredient.of(KoratioFluids.MOLTEN_BLUE_SUGAR.get()), 0);
        }
        if (this.ingredient2.ingredient() == FluidIngredient.empty()) {
            this.ingredient2 = new SizedFluidIngredient(FluidIngredient.of(KoratioFluids.MOLTEN_GREEN_SUGAR.get()), 0);
        }
        if (this.ingredient3.ingredient() == FluidIngredient.empty()) {
            this.ingredient3 = new SizedFluidIngredient(FluidIngredient.of(KoratioFluids.MOLTEN_YELLOW_SUGAR.get()), 0);
        }
        if (this.ingredient4.ingredient() == FluidIngredient.empty()) {
            this.ingredient4 = new SizedFluidIngredient(FluidIngredient.of(KoratioFluids.MOLTEN_RED_SUGAR.get()), 0);
        }
        CandyShaperRecipe candyShaperRecipe = new CandyShaperRecipe(this.ingredient, this.ingredient1, this.ingredient2, this.ingredient3, this.ingredient4, this.stackResult);
        recipeOutput.accept(id, candyShaperRecipe, advancement$builder.build(id.withPrefix("recipes/")));

    }
}