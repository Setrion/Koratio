package net.setrion.koratio.world.item.crafting;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioRecipeSerializer;
import net.setrion.koratio.registry.KoratioRecipeType;

import java.util.List;

public class CandyShaperRecipe implements Recipe<FakeInventory> {

    final NonNullList<SizedFluidIngredient> fluidIngredients;
    final ItemStack templateItem;
    final ItemStack outputItem;

    public CandyShaperRecipe(ItemStack output, ItemStack template, NonNullList<SizedFluidIngredient> ingredients) {
        fluidIngredients = ingredients;
        this.templateItem = template;
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

    public ItemStack getTemplateItem(HolderLookup.Provider provider) {
        return templateItem;
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

        private static final MapCodec<CandyShaperRecipe> CODEC = RecordCodecBuilder.mapCodec(
                builder -> builder.group(
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.outputItem),
                                ItemStack.STRICT_CODEC.fieldOf("template").forGetter(recipe -> recipe.templateItem),
                                SizedFluidIngredient.NESTED_CODEC
                                        .listOf()
                                        .fieldOf("fluid_ingredients")
                                        .flatXmap(
                                                list -> {
                                                    SizedFluidIngredient[] aingredient = list.toArray(SizedFluidIngredient[]::new); // Neo skip the empty check and immediately create the array.
                                                    if (aingredient.length == 0) {
                                                        return DataResult.error(() -> "No ingredients for shapeless recipe");
                                                    } else {
                                                        return aingredient.length > 2
                                                                ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: 2")
                                                                : DataResult.success(NonNullList.of(new SizedFluidIngredient(FluidIngredient.empty(), 1), aingredient));
                                                    }
                                                },
                                                DataResult::success
                                        )
                                        .forGetter(recipe -> recipe.fluidIngredients)
                        )
                        .apply(builder, CandyShaperRecipe::new)
        );

        //private static final MapCodec<CandyShaperRecipe> CODEC = RecordCodecBuilder.mapCodec((recipeInstance) -> recipeInstance.group(SizedFluidIngredient.FLAT_CODEC.fieldOf("main_fluid_ingredient").forGetter((candyShaperRecipe) -> candyShaperRecipe.fluidIngredients.get(0)), SizedFluidIngredient.FLAT_CODEC.optionalFieldOf("additional_fluid_ingredient").forGetter((recipe) -> java.util.Optional.ofNullable(recipe.fluidIngredients.size() > 1 ? recipe.fluidIngredients.get(1) : null)), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((candyShaperRecipe) -> candyShaperRecipe.outputItem)).apply(recipeInstance, CandyShaperRecipe::new));

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
            int i = buffer.readVarInt();
            NonNullList<SizedFluidIngredient> nonnulllist = NonNullList.withSize(i, new SizedFluidIngredient(FluidIngredient.empty(), 1));
            nonnulllist.replaceAll(ingredient -> SizedFluidIngredient.STREAM_CODEC.decode(buffer));
            ItemStack template = ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new CandyShaperRecipe(itemstack, template, nonnulllist);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CandyShaperRecipe recipe) {
            buffer.writeVarInt(recipe.fluidIngredients.size());

            for (SizedFluidIngredient ingredient : recipe.fluidIngredients) {
                SizedFluidIngredient.STREAM_CODEC.encode(buffer, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.templateItem);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.outputItem);
        }
    }
}