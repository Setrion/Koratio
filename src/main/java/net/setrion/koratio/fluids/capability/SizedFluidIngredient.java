package net.setrion.koratio.fluids.capability;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.util.NeoForgeExtraCodecs;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public class SizedFluidIngredient {
    public static final Codec<SizedFluidIngredient> FLAT_CODEC = RecordCodecBuilder.create((instance) -> instance.group(FluidIngredient.MAP_CODEC_NONEMPTY.forGetter(SizedFluidIngredient::ingredient), NeoForgeExtraCodecs.optionalFieldAlwaysWrite(ExtraCodecs.NON_NEGATIVE_INT, "amount", 1000).forGetter(SizedFluidIngredient::amount)).apply(instance, SizedFluidIngredient::new));
    public static final Codec<SizedFluidIngredient> NESTED_CODEC = RecordCodecBuilder.create((instance) -> instance.group(FluidIngredient.CODEC_NON_EMPTY.fieldOf("ingredient").forGetter(SizedFluidIngredient::ingredient), NeoForgeExtraCodecs.optionalFieldAlwaysWrite(ExtraCodecs.NON_NEGATIVE_INT, "amount", 1000).forGetter(SizedFluidIngredient::amount)).apply(instance, SizedFluidIngredient::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SizedFluidIngredient> STREAM_CODEC;
    private final FluidIngredient ingredient;
    private final int amount;
    private @Nullable FluidStack[] cachedStacks;

    public static SizedFluidIngredient of(Fluid fluid, int amount) {
        return new SizedFluidIngredient(FluidIngredient.of(fluid), amount);
    }

    public static SizedFluidIngredient of(FluidStack stack) {
        return new SizedFluidIngredient(FluidIngredient.single(stack), stack.getAmount());
    }

    public static SizedFluidIngredient of(TagKey<Fluid> tag, int amount) {
        return new SizedFluidIngredient(FluidIngredient.tag(tag), amount);
    }

    public SizedFluidIngredient(FluidIngredient ingredient, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Size must be non-negative!");
        } else {
            this.ingredient = ingredient;
            this.amount = amount;
        }
    }

    public FluidIngredient ingredient() {
        return this.ingredient;
    }

    public int amount() {
        return this.amount;
    }

    public boolean test(FluidStack stack) {
        return this.ingredient.test(stack) && stack.getAmount() >= this.amount;
    }

    public FluidStack[] getFluids() {
        if (this.cachedStacks == null) {
            this.cachedStacks = Stream.of(this.ingredient.getStacks()).map((s) -> s.copyWithAmount(this.amount)).toArray((x$0) -> new FluidStack[x$0]);
        }

        return this.cachedStacks;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof SizedFluidIngredient)) {
            return false;
        } else {
            SizedFluidIngredient other = (SizedFluidIngredient)o;
            return this.amount == other.amount && this.ingredient.equals(other.ingredient);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.ingredient, this.amount});
    }

    public String toString() {
        int var10000 = this.amount;
        return "" + var10000 + "x " + String.valueOf(this.ingredient);
    }

    static {
        STREAM_CODEC = StreamCodec.composite(FluidIngredient.STREAM_CODEC, SizedFluidIngredient::ingredient, ByteBufCodecs.VAR_INT, SizedFluidIngredient::amount, SizedFluidIngredient::new);
    }
}