package net.setrion.koratio.registry;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.entity.animal.MagicalCatVariant;

public class MagicalCatVariants {

    public static final DeferredRegister<MagicalCatVariant> MAGICAL_CAT_VARIANTS = DeferredRegister.create(KoratioRegistries.MAGICAL_CAT_VARIANT_KEY, Koratio.MOD_ID);

    public static final DeferredHolder<MagicalCatVariant, MagicalCatVariant> GEKIDO = MAGICAL_CAT_VARIANTS.register("gekido", () -> new MagicalCatVariant(Koratio.prefix("textures/entity/magical_cat/gekido.png")));
    public static final DeferredHolder<MagicalCatVariant, MagicalCatVariant> TAIDA = MAGICAL_CAT_VARIANTS.register("taida", () -> new MagicalCatVariant(Koratio.prefix("textures/entity/magical_cat/taida.png")));
    public static final DeferredHolder<MagicalCatVariant, MagicalCatVariant> OJI = MAGICAL_CAT_VARIANTS.register("oji", () -> new MagicalCatVariant(Koratio.prefix("textures/entity/magical_cat/oji.png")));
    public static final DeferredHolder<MagicalCatVariant, MagicalCatVariant> FUUN = MAGICAL_CAT_VARIANTS.register("fuun", () -> new MagicalCatVariant(Koratio.prefix("textures/entity/magical_cat/fuun.png")));
    public static final DeferredHolder<MagicalCatVariant, MagicalCatVariant> AKUMA = MAGICAL_CAT_VARIANTS.register("akuma", () -> new MagicalCatVariant(Koratio.prefix("textures/entity/magical_cat/akuma.png")));
    public static final DeferredHolder<MagicalCatVariant, MagicalCatVariant> FUKU = MAGICAL_CAT_VARIANTS.register("fuku", () -> new MagicalCatVariant(Koratio.prefix("textures/entity/magical_cat/fuku.png")));
    public static final DeferredHolder<MagicalCatVariant, MagicalCatVariant> GOMAN = MAGICAL_CAT_VARIANTS.register("goman", () -> new MagicalCatVariant(Koratio.prefix("textures/entity/magical_cat/goman.png")));
    public static final DeferredHolder<MagicalCatVariant, MagicalCatVariant> SENBO = MAGICAL_CAT_VARIANTS.register("senbo", () -> new MagicalCatVariant(Koratio.prefix("textures/entity/magical_cat/senbo.png")));

}