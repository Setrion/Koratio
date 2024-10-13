package net.setrion.koratio.world.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.setrion.koratio.fluids.capability.SizedFluidIngredient;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioRecipeSerializer;
import net.setrion.koratio.registry.KoratioRecipeType;

public class CandyShaperRecipe implements Recipe<FakeInventory> {

    protected final SizedFluidIngredient moltenSugarInput;
    protected final SizedFluidIngredient moltenBlueSugarInput;
    protected final SizedFluidIngredient moltenGreenSugarInput;
    protected final SizedFluidIngredient moltenYellowSugarInput;
    protected final SizedFluidIngredient moltenRedSugarInput;
    protected final ItemStack outputItem;

    public CandyShaperRecipe(SizedFluidIngredient moltenSugarInput, SizedFluidIngredient moltenBlueSugarInput, SizedFluidIngredient moltenGreenSugarInput, SizedFluidIngredient moltenYellowSugarInput, SizedFluidIngredient moltenRedSugarInput, ItemStack output) {
        this.moltenSugarInput = moltenSugarInput;
        this.moltenBlueSugarInput = moltenBlueSugarInput;
        this.moltenGreenSugarInput = moltenGreenSugarInput;
        this.moltenYellowSugarInput = moltenYellowSugarInput;
        this.moltenRedSugarInput = moltenRedSugarInput;
        this.outputItem = output;
    }

    @Override
    public boolean matches(FakeInventory fakeInventory, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(FakeInventory fakeInventory, HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return outputItem;
    }

    public NonNullList<SizedFluidIngredient> getFluidIngredients() {
        NonNullList<SizedFluidIngredient> ingredients = NonNullList.create();
        ingredients.add(moltenSugarInput);
        ingredients.add(moltenBlueSugarInput);
        ingredients.add(moltenGreenSugarInput);
        ingredients.add(moltenYellowSugarInput);
        ingredients.add(moltenRedSugarInput);
        return ingredients;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(getType() == KoratioRecipeType.CANDY_SHAPING.get() ? KoratioBlocks.CANDY_SHAPER : KoratioBlocks.WOODCUTTER);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KoratioRecipeSerializer.CANDY_SHAPER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return KoratioRecipeType.CANDY_SHAPING.get();
    }

    public static class Serializer implements RecipeSerializer<CandyShaperRecipe> {

        private static final MapCodec<CandyShaperRecipe> CODEC = RecordCodecBuilder.mapCodec((recipeInstance) -> {
            return recipeInstance.group(SizedFluidIngredient.FLAT_CODEC.fieldOf("sugar").forGetter((candyShaperRecipe) -> {
                return candyShaperRecipe.moltenSugarInput;
            }), SizedFluidIngredient.FLAT_CODEC.fieldOf("blue_sugar").forGetter((candyShaperRecipe) -> {
                return candyShaperRecipe.moltenBlueSugarInput;
            }), SizedFluidIngredient.FLAT_CODEC.fieldOf("green_sugar").forGetter((candyShaperRecipe) -> {
                return candyShaperRecipe.moltenGreenSugarInput;
            }), SizedFluidIngredient.FLAT_CODEC.fieldOf("yellow_sugar").forGetter((candyShaperRecipe) -> {
                return candyShaperRecipe.moltenYellowSugarInput;
            }), SizedFluidIngredient.FLAT_CODEC.fieldOf("red_sugar").forGetter((candyShaperRecipe) -> {
                return candyShaperRecipe.moltenRedSugarInput;
            }), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((candyShaperRecipe) -> {
                return candyShaperRecipe.outputItem;
            })).apply(recipeInstance, CandyShaperRecipe::new);
        });

        public static final StreamCodec<RegistryFriendlyByteBuf, CandyShaperRecipe> STREAM_CODEC = StreamCodec.of(CandyShaperRecipe.Serializer::toNetwork, CandyShaperRecipe.Serializer::fromNetwork);


        @Override
        public MapCodec<CandyShaperRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CandyShaperRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static CandyShaperRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            SizedFluidIngredient ingredient = SizedFluidIngredient.STREAM_CODEC.decode(buffer);
            SizedFluidIngredient ingredient1 = SizedFluidIngredient.STREAM_CODEC.decode(buffer);
            SizedFluidIngredient ingredient2 = SizedFluidIngredient.STREAM_CODEC.decode(buffer);
            SizedFluidIngredient ingredient3 = SizedFluidIngredient.STREAM_CODEC.decode(buffer);
            SizedFluidIngredient ingredient4 = SizedFluidIngredient.STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new CandyShaperRecipe(ingredient, ingredient1, ingredient2, ingredient3, ingredient4, itemstack);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CandyShaperRecipe recipe) {
            SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.moltenSugarInput);
            SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.moltenBlueSugarInput);
            SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.moltenGreenSugarInput);
            SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.moltenYellowSugarInput);
            SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.moltenRedSugarInput);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.outputItem);
        }
    }
}