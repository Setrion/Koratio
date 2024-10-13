package net.setrion.koratio.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;

public class KoratioParticles {
	
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Koratio.MOD_ID);
	
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GOLD_GLINT = PARTICLES.register("gold_glint", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ELVEN_LEAVES = PARTICLES.register("elven_leaves", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_SUGAR = PARTICLES.register("dripping_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_SUGAR = PARTICLES.register("falling_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_SUGAR = PARTICLES.register("landing_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_DRIPSTONE_SUGAR = PARTICLES.register("dripping_dripstone_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_DRIPSTONE_SUGAR = PARTICLES.register("falling_dripstone_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_BLUE_SUGAR = PARTICLES.register("dripping_blue_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_BLUE_SUGAR = PARTICLES.register("falling_blue_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_BLUE_SUGAR = PARTICLES.register("landing_blue_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_DRIPSTONE_BLUE_SUGAR = PARTICLES.register("dripping_dripstone_blue_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_DRIPSTONE_BLUE_SUGAR = PARTICLES.register("falling_dripstone_blue_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_GREEN_SUGAR = PARTICLES.register("dripping_green_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_GREEN_SUGAR = PARTICLES.register("falling_green_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_GREEN_SUGAR = PARTICLES.register("landing_green_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_DRIPSTONE_GREEN_SUGAR = PARTICLES.register("dripping_dripstone_green_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_DRIPSTONE_GREEN_SUGAR = PARTICLES.register("falling_dripstone_green_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_YELLOW_SUGAR = PARTICLES.register("dripping_yellow_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_YELLOW_SUGAR = PARTICLES.register("falling_yellow_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_YELLOW_SUGAR = PARTICLES.register("landing_yellow_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_DRIPSTONE_YELLOW_SUGAR = PARTICLES.register("dripping_dripstone_yellow_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_DRIPSTONE_YELLOW_SUGAR = PARTICLES.register("falling_dripstone_yellow_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_RED_SUGAR = PARTICLES.register("dripping_red_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_RED_SUGAR = PARTICLES.register("falling_red_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_RED_SUGAR = PARTICLES.register("landing_red_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_DRIPSTONE_RED_SUGAR = PARTICLES.register("dripping_dripstone_red_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_DRIPSTONE_RED_SUGAR = PARTICLES.register("falling_dripstone_red_sugar", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RAINBOW_FIRE_FLAME = PARTICLES.register("rainbow_fire_flame", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SMALL_RAINBOW_FIRE_FLAME = PARTICLES.register("small_rainbow_fire_flame", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RAINBOW_LAVA = PARTICLES.register("rainbow_lava", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DEMONIC_FIRE_FLAME = PARTICLES.register("demonic_fire_flame", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TELEPORTER_ASCEND = PARTICLES.register("teleporter_ascend", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TELEPORTER_DESCEND = PARTICLES.register("teleporter_descend", () -> new SimpleParticleType(false));
}