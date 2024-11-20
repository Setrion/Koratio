package net.setrion.koratio.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.entity.animal.MagicalCatVariant;

public class KoratioRegistries {

    public static final ResourceKey<Registry<MagicalCatVariant>> MAGICAL_CAT_VARIANT = ResourceKey.createRegistryKey(Koratio.prefix("magical_cat_variant"));

}