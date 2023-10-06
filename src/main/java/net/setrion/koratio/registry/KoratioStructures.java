package net.setrion.koratio.registry;

import java.util.Map;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.data.worldgen.OutcastPools;

public class KoratioStructures {
	
	static ResourceKey<Structure> OUTCAST = createKey("outcast");

	private static Structure.StructureSettings structure(HolderSet<Biome> set, Map<MobCategory, StructureSpawnOverride> spawns, GenerationStep.Decoration decoration, TerrainAdjustment terrain) {
		return new Structure.StructureSettings(set, spawns, decoration, terrain);
	}
	
	public static void bootstrap(BootstapContext<Structure> context) {
		HolderGetter<Biome> holdergetter = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> holdergetter1 = context.lookup(Registries.TEMPLATE_POOL);
		context.register(KoratioStructures.OUTCAST, new JigsawStructure(structure(holdergetter.getOrThrow(BiomeTags.IS_HILL), Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN), holdergetter1.getOrThrow(OutcastPools.START), 7, ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG));
	}
	
	private static ResourceKey<Structure> createKey(String name) {
		return ResourceKey.create(Registries.STRUCTURE, Koratio.prefix(name));
	}
}
