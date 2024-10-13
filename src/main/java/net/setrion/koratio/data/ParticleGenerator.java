package net.setrion.koratio.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioParticles;

public class ParticleGenerator extends ParticleDescriptionProvider {

    public ParticleGenerator(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    protected void addDescriptions() {
        sprite(KoratioParticles.GOLD_GLINT.get(), Koratio.prefix("gold_glint"));
        spriteSet(KoratioParticles.ELVEN_LEAVES.get(), Koratio.prefix("elven_leaves"), 12, false);
        sprite(KoratioParticles.DRIPPING_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.DRIPPING_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_BLUE_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.DRIPPING_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_GREEN_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.DRIPPING_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_YELLOW_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.DRIPPING_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.LANDING_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_land"));
        sprite(KoratioParticles.DRIPPING_DRIPSTONE_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_hang"));
        sprite(KoratioParticles.FALLING_DRIPSTONE_RED_SUGAR.get(), ResourceLocation.withDefaultNamespace("drip_fall"));
        sprite(KoratioParticles.RAINBOW_FIRE_FLAME.get(), Koratio.prefix("rainbow_fire_flame"));
        sprite(KoratioParticles.SMALL_RAINBOW_FIRE_FLAME.get(), Koratio.prefix("rainbow_fire_flame"));
        sprite(KoratioParticles.RAINBOW_LAVA.get(), Koratio.prefix("rainbow_lava"));
        sprite(KoratioParticles.DEMONIC_FIRE_FLAME.get(), Koratio.prefix("demonic_fire_flame"));
        sprite(KoratioParticles.TELEPORTER_ASCEND.get(), Koratio.prefix("teleport_ascend"));
        sprite(KoratioParticles.TELEPORTER_DESCEND.get(), Koratio.prefix("teleport_descend"));
    }
}