package net.setrion.koratio.registry;

import java.util.Objects;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.carver.AmethystSpiderCavesCarver;
import net.setrion.koratio.world.level.levelgen.carver.DemonicioCavesCarver;
import net.setrion.koratio.world.level.levelgen.carver.FantasiaCavesCarver;
import net.setrion.koratio.world.level.levelgen.carver.FantasiaCrystalCavesCarver;

@Mod.EventBusSubscriber(modid = Koratio.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoratioCarvers {
	public static final FantasiaCavesCarver FANTASIA_CAVES = new FantasiaCavesCarver(CaveCarverConfiguration.CODEC);
	public static final FantasiaCrystalCavesCarver FANTASIA_CRYSTAL_CAVES = new FantasiaCrystalCavesCarver(CaveCarverConfiguration.CODEC);
	public static final AmethystSpiderCavesCarver AMETHYST_SPIDER_CAVES = new AmethystSpiderCavesCarver(CaveCarverConfiguration.CODEC);
	
	public static final DemonicioCavesCarver DEMONICIO_CAVES = new DemonicioCavesCarver(CaveCarverConfiguration.CODEC);

	@SubscribeEvent
	public static void register(RegisterEvent evt) {
		if (Objects.equals(evt.getForgeRegistry(), ForgeRegistries.WORLD_CARVERS)) {
			evt.register(ForgeRegistries.Keys.WORLD_CARVERS, helper -> helper.register(Koratio.prefix("fantasia_caves"), FANTASIA_CAVES));
			evt.register(ForgeRegistries.Keys.WORLD_CARVERS, helper -> helper.register(Koratio.prefix("fantasia_crystal_caves"), FANTASIA_CRYSTAL_CAVES));
			evt.register(ForgeRegistries.Keys.WORLD_CARVERS, helper -> helper.register(Koratio.prefix("amethyst_spider_caves"), AMETHYST_SPIDER_CAVES));
			
			evt.register(ForgeRegistries.Keys.WORLD_CARVERS, helper -> helper.register(Koratio.prefix("demonicio_caves"), DEMONICIO_CAVES));
		}
	}

	public static final ResourceKey<ConfiguredWorldCarver<?>> FANTASIA_CAVES_CONFIGURED = registerKey("fantasia_caves");
	public static final ResourceKey<ConfiguredWorldCarver<?>> FANTASIA_CRYSTAL_CAVES_CONFIGURED = registerKey("fantasia_crystal_caves");
	public static final ResourceKey<ConfiguredWorldCarver<?>> AMETHYST_SPIDER_CAVES_CONFIGURED = registerKey("amethyst_spider_caves");
	
	public static final ResourceKey<ConfiguredWorldCarver<?>> DEMONICIO_CAVES_CONFIGURED = registerKey("demonicio_caves");

	private static ResourceKey<ConfiguredWorldCarver<?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_CARVER, Koratio.prefix(name));
	}

	public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> context) {
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
		context.register(FANTASIA_CAVES_CONFIGURED, FANTASIA_CAVES.configured(new CaveCarverConfiguration(0.15F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), blocks.getOrThrow(KoratioTags.Blocks.FANTASIA_CARVER_REPLACEABLES), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
		context.register(FANTASIA_CRYSTAL_CAVES_CONFIGURED, FANTASIA_CRYSTAL_CAVES.configured(new CaveCarverConfiguration(0.01F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), blocks.getOrThrow(KoratioTags.Blocks.FANTASIA_CARVER_REPLACEABLES), UniformFloat.of(1.4F, 2.8F), UniformFloat.of(1.6F, 2.6F), UniformFloat.of(-1.0F, -0.4F))));
		context.register(AMETHYST_SPIDER_CAVES_CONFIGURED, AMETHYST_SPIDER_CAVES.configured(new CaveCarverConfiguration(0.01F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), blocks.getOrThrow(KoratioTags.Blocks.FANTASIA_CARVER_REPLACEABLES), UniformFloat.of(1.4F, 2.8F), UniformFloat.of(1.6F, 2.6F), UniformFloat.of(-1.0F, -0.4F))));
		
		context.register(DEMONICIO_CAVES_CONFIGURED, DEMONICIO_CAVES.configured(new CaveCarverConfiguration(0.15F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), blocks.getOrThrow(KoratioTags.Blocks.DEMONICIO_CARVER_REPLACEABLES), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
	}
}