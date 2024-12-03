package net.setrion.koratio.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.RegistryBuilder;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.entity.animal.MagicalCatVariant;

public class KoratioRegistries {

    public static final ResourceKey<Registry<MagicalCatVariant>> MAGICAL_CAT_VARIANT_KEY = ResourceKey.createRegistryKey(Koratio.prefix("magical_cat_variant"));
    public static final Registry<MagicalCatVariant> MAGICAL_CAT_VARIANT_REGISTRY = new RegistryBuilder<>(MAGICAL_CAT_VARIANT_KEY).sync(true).create();

}