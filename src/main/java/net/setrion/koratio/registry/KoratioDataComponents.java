package net.setrion.koratio.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;


public class KoratioDataComponents {

    public record ScrollRecord(String name, boolean isEncrypted) {}

    public static final Codec<ScrollRecord> SCROLL_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("name").forGetter(ScrollRecord::name),
                    Codec.BOOL.fieldOf("isEncrypted").forGetter(ScrollRecord::isEncrypted)
            ).apply(instance, ScrollRecord::new)
    );
    public static final StreamCodec<ByteBuf, ScrollRecord> SCROLL_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ScrollRecord::name,
            ByteBufCodecs.BOOL, ScrollRecord::isEncrypted,
            ScrollRecord::new
    );

    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Koratio.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ScrollRecord>> SCROLL_DATA = DATA_COMPONENTS.registerComponentType(
            "scroll_data",
            builder -> builder
                    .persistent(SCROLL_CODEC)
                    .networkSynchronized(SCROLL_STREAM_CODEC)
    );
}