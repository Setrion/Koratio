package net.setrion.koratio.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioParticles;

public class ParticleGenerator extends ParticleDescriptionProvider {

    public ParticleGenerator(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        sprite(KoratioParticles.GOLD_GLINT.get(), Koratio.prefix("gold_glint"));
        spriteSet(KoratioParticles.ELVEN_LEAVES.get(), Koratio.prefix("elven_leaves"), 12, false);

        sprite(KoratioParticles.DRIPPING_WHITE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_WHITE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_WHITE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_WHITE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_WHITE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_LIGHT_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_LIGHT_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_LIGHT_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_LIGHT_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_LIGHT_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_GRAY_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_BLACK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_BLACK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_BLACK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_BLACK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_BLACK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_BROWN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_BROWN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_BROWN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_BROWN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_BROWN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_ORANGE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_ORANGE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_ORANGE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_ORANGE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_ORANGE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_LIME_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_LIME_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_LIME_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_LIME_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_LIME_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_CYAN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_CYAN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_CYAN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_CYAN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_CYAN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_LIGHT_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_LIGHT_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_LIGHT_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_LIGHT_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_LIGHT_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_PURPLE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_PURPLE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_PURPLE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_PURPLE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_PURPLE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_MAGENTA_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_MAGENTA_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_MAGENTA_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_MAGENTA_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_MAGENTA_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.DRIPPING_PINK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_PINK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_PINK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_PINK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_PINK_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));

        sprite(KoratioParticles.RAINBOW_FIRE_FLAME.get(), Koratio.prefix("rainbow_fire_flame"));
        sprite(KoratioParticles.SMALL_RAINBOW_FIRE_FLAME.get(), Koratio.prefix("rainbow_fire_flame"));
        sprite(KoratioParticles.RAINBOW_LAVA.get(), Koratio.prefix("rainbow_lava"));
        sprite(KoratioParticles.DEMONIC_FIRE_FLAME.get(), Koratio.prefix("demonic_fire_flame"));
        spriteSet(KoratioParticles.DEMONIC_SOUL.get(), Koratio.prefix("demonic_soul"), 11, false);
        sprite(KoratioParticles.TELEPORTER_ASCEND.get(), Koratio.prefix("teleport_ascend"));
        sprite(KoratioParticles.TELEPORTER_DESCEND.get(), Koratio.prefix("teleport_descend"));
    }
}