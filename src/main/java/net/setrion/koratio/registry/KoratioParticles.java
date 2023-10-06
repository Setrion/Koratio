package net.setrion.koratio.registry;

import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;

@Mod.EventBusSubscriber(modid = Koratio.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KoratioParticles {
	
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Koratio.MOD_ID);
	
	public static final RegistryObject<SimpleParticleType> AMETHYST_FIRE_FLAME = PARTICLES.register("amethyst_fire_flame", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> EMERALD_FIRE_FLAME = PARTICLES.register("emerald_fire_flame", () -> new SimpleParticleType(false));
	
	@SubscribeEvent
	public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(KoratioParticles.AMETHYST_FIRE_FLAME.get(), FlameParticle.Provider::new);
		event.registerSpriteSet(KoratioParticles.EMERALD_FIRE_FLAME.get(), FlameParticle.Provider::new);
	}
}