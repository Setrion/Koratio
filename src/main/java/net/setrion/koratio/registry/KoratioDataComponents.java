package net.setrion.koratio.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;

public class KoratioDataComponents {

    public record ScrollRecord(String name, boolean isEncrypted) {}
    public record PipingBagRecord(String color, int amount) {}
    public record ConvertibleRecord(ResourceKey<DimensionType> dimension, int convert_time) {}

    public static final Codec<ScrollRecord> SCROLL_CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.STRING.fieldOf("name").forGetter(ScrollRecord::name), Codec.BOOL.fieldOf("isEncrypted").forGetter(ScrollRecord::isEncrypted)).apply(instance, ScrollRecord::new));
    public static final StreamCodec<ByteBuf, ScrollRecord> SCROLL_STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, ScrollRecord::name, ByteBufCodecs.BOOL, ScrollRecord::isEncrypted, ScrollRecord::new);

    public static final Codec<PipingBagRecord> PIPING_BAG_CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.STRING.fieldOf("color").forGetter(PipingBagRecord::color), Codec.INT.fieldOf("amount").forGetter(PipingBagRecord::amount)).apply(instance, PipingBagRecord::new));
    public static final StreamCodec<ByteBuf, PipingBagRecord> PIPING_BAG_STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, PipingBagRecord::color, ByteBufCodecs.INT, PipingBagRecord::amount, PipingBagRecord::new);

    public static final Codec<ConvertibleRecord> CONVERTIBLE_CODEC = RecordCodecBuilder.create(instance -> instance.group(ResourceKey.codec(Registries.DIMENSION_TYPE).fieldOf("dimension").forGetter(ConvertibleRecord::dimension), Codec.INT.fieldOf("convert_time").forGetter(ConvertibleRecord::convert_time)).apply(instance, ConvertibleRecord::new));
    public static final StreamCodec<ByteBuf, ConvertibleRecord> CONVERTIBLE_STREAM_CODEC = StreamCodec.composite(ResourceKey.streamCodec(Registries.DIMENSION_TYPE), ConvertibleRecord::dimension, ByteBufCodecs.INT, ConvertibleRecord::convert_time, ConvertibleRecord::new);

    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Koratio.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ScrollRecord>> SCROLL_DATA = DATA_COMPONENTS.register("scroll_data", () -> DataComponentType.<ScrollRecord>builder().persistent(SCROLL_CODEC).networkSynchronized(SCROLL_STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PipingBagRecord>> PIPING_BAG_DATA = DATA_COMPONENTS.register("piping_bag_data", () -> DataComponentType.<PipingBagRecord>builder().persistent(PIPING_BAG_CODEC).networkSynchronized(PIPING_BAG_STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ConvertibleRecord>> CONVERTIBLE_DATA = DATA_COMPONENTS.register("convertible_bag_data", () -> DataComponentType.<ConvertibleRecord>builder().persistent(CONVERTIBLE_CODEC).networkSynchronized(CONVERTIBLE_STREAM_CODEC).build());

}