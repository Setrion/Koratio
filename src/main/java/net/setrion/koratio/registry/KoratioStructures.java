package net.setrion.koratio.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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

import java.util.Map;

public class KoratioStructures {
	
	public static ResourceKey<Structure> OUTCAST = createKey("outcast");

	private static Structure.StructureSettings structure(HolderSet<Biome> set, Map<MobCategory, StructureSpawnOverride> spawns, GenerationStep.Decoration decoration, TerrainAdjustment terrain) {
		return new Structure.StructureSettings.Builder(set).generationStep(decoration).terrainAdapation(terrain).spawnOverrides(spawns).build();
	}
	
	public static void bootstrap(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);
		context.register(OUTCAST, new JigsawStructure(structure(biomes.getOrThrow(BiomeTags.IS_HILL), Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN), pools.getOrThrow(OutcastPools.START), 7, ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG));
	}
	
	private static ResourceKey<Structure> createKey(String name) {
		return ResourceKey.create(Registries.STRUCTURE, Koratio.prefix(name));
	}
}
