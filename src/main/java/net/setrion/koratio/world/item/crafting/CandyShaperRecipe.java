package net.setrion.koratio.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioRecipeSerializer;
import net.setrion.koratio.registry.KoratioRecipeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandyShaperRecipe implements Recipe<FakeInventory> {

    protected final List<SizedFluidIngredient> fluidIngredients = new ArrayList<>();
    protected final ItemStack outputItem;

    public CandyShaperRecipe(SizedFluidIngredient mainFluidInput, Optional<SizedFluidIngredient> secondFluidInput, ItemStack output) {
        fluidIngredients.add(mainFluidInput);
        secondFluidInput.ifPresent(fluidIngredients::add);
        this.outputItem = output;
    }

    @Override
    public boolean matches(FakeInventory fakeInventory, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(FakeInventory fakeInventory, HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return outputItem;
    }

    public List<SizedFluidIngredient> getFluidIngredients() {
        return fluidIngredients;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(KoratioBlocks.CANDY_SHAPER);
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

        private static final MapCodec<CandyShaperRecipe> CODEC = RecordCodecBuilder.mapCodec((recipeInstance) -> recipeInstance.group(SizedFluidIngredient.FLAT_CODEC.fieldOf("main_fluid_ingredient").forGetter((candyShaperRecipe) -> candyShaperRecipe.fluidIngredients.get(0)), SizedFluidIngredient.FLAT_CODEC.optionalFieldOf("additional_fluid_ingredient").forGetter((recipe) -> java.util.Optional.ofNullable(recipe.fluidIngredients.size() > 1 ? recipe.fluidIngredients.get(1) : null)), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((candyShaperRecipe) -> candyShaperRecipe.outputItem)).apply(recipeInstance, CandyShaperRecipe::new));

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
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new CandyShaperRecipe(ingredient, Optional.of(ingredient1), itemstack);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CandyShaperRecipe recipe) {
            SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.fluidIngredients.get(0));
            if (recipe.fluidIngredients.size() > 1) {
                SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.fluidIngredients.get(1));
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.outputItem);
        }
    }
}