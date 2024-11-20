package net.setrion.koratio.world.entity.animal;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioRegistries;

public record MagicalCatVariant(ResourceLocation texture) {

    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<MagicalCatVariant>> STREAM_CODEC;
    public static final ResourceKey<MagicalCatVariant> GEKIDO;
    public static final ResourceKey<MagicalCatVariant> TAIDA;
    public static final ResourceKey<MagicalCatVariant> OJI;
    public static final ResourceKey<MagicalCatVariant> FUUN;
    public static final ResourceKey<MagicalCatVariant> AKUMA;
    public static final ResourceKey<MagicalCatVariant> FUKU;
    public static final ResourceKey<MagicalCatVariant> GOMAN;
    public static final ResourceKey<MagicalCatVariant> SENBO;

    public MagicalCatVariant(ResourceLocation texture) {
        this.texture = texture;
    }

    private static ResourceKey<MagicalCatVariant> createKey(String name) {
        return ResourceKey.create(KoratioRegistries.MAGICAL_CAT_VARIANT, Koratio.prefix(name));
    }

    public static MagicalCatVariant bootstrap(Registry<MagicalCatVariant> registry) {
        register(registry, GEKIDO, "textures/entity/magical_cat/gekido.png");
        register(registry, TAIDA, "textures/entity/magical_cat/taida.png");
        register(registry, OJI, "textures/entity/magical_cat/oji.png");
        register(registry, FUUN, "textures/entity/magical_cat/fuun.png");
        register(registry, AKUMA, "textures/entity/magical_cat/akuma.png");
        register(registry, FUKU, "textures/entity/magical_cat/fuku.png");
        register(registry, GOMAN, "textures/entity/magical_cat/goman.png");
        return register(registry, SENBO, "textures/entity/magical_cat/senbo.png");
    }

    private static MagicalCatVariant register(Registry<MagicalCatVariant> registry, ResourceKey<MagicalCatVariant> key, String texture) {
        return Registry.register(registry, key, new MagicalCatVariant(Koratio.prefix(texture)));
    }

    public ResourceLocation texture() {
        return this.texture;
    }

    static {
        STREAM_CODEC = ByteBufCodecs.holderRegistry(KoratioRegistries.MAGICAL_CAT_VARIANT);
        GEKIDO = createKey("gekido");
        TAIDA = createKey("taida");
        OJI = createKey("oji");
        FUUN = createKey("fuun");
        AKUMA = createKey("akuma");
        FUKU = createKey("fuku");
        GOMAN = createKey("goman");
        SENBO = createKey("senbo");
    }
}