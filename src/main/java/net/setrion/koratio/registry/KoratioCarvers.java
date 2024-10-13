package net.setrion.koratio.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.carver.FantasiaCavesCarver;
import net.setrion.koratio.world.level.levelgen.carver.FantasiaCrystalCavesCarver;

import java.util.Objects;

@EventBusSubscriber(modid = Koratio.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class KoratioCarvers {
	public static final FantasiaCavesCarver FANTASIA_CAVES = new FantasiaCavesCarver(CaveCarverConfiguration.CODEC);
	public static final FantasiaCrystalCavesCarver FANTASIA_CRYSTAL_CAVES = new FantasiaCrystalCavesCarver(CaveCarverConfiguration.CODEC);

	@SubscribeEvent
	public static void register(RegisterEvent evt) {
		if (Objects.equals(evt.getRegistry(), BuiltInRegistries.CARVER)) {
			evt.register(BuiltInRegistries.CARVER.key(), helper -> helper.register(Koratio.prefix("fantasia_caves"), FANTASIA_CAVES));
			evt.register(BuiltInRegistries.CARVER.key(), helper -> helper.register(Koratio.prefix("fantasia_crystal_caves"), FANTASIA_CRYSTAL_CAVES));
		}
	}

	public static final ResourceKey<ConfiguredWorldCarver<?>> FANTASIA_CAVES_CONFIGURED = registerKey("fantasia_caves");
	public static final ResourceKey<ConfiguredWorldCarver<?>> FANTASIA_CRYSTAL_CAVES_CONFIGURED = registerKey("fantasia_crystal_caves");

	private static ResourceKey<ConfiguredWorldCarver<?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_CARVER, Koratio.prefix(name));
	}

	public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
		context.register(FANTASIA_CAVES_CONFIGURED, FANTASIA_CAVES.configured(new CaveCarverConfiguration(0.15F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), blocks.getOrThrow(KoratioTags.Blocks.FANTASIA_CARVER_REPLACEABLES), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
		context.register(FANTASIA_CRYSTAL_CAVES_CONFIGURED, FANTASIA_CRYSTAL_CAVES.configured(new CaveCarverConfiguration(0.01F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), blocks.getOrThrow(KoratioTags.Blocks.FANTASIA_CARVER_REPLACEABLES), UniformFloat.of(1.4F, 2.8F), UniformFloat.of(1.6F, 2.6F), UniformFloat.of(-1.0F, -0.4F))));
	}
}