package net.setrion.koratio.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.setrion.koratio.Koratio;

import java.util.List;

public class HugeCandyCaneFeature extends Feature<NoneFeatureConfiguration> {
	
	List<ResourceLocation> list = List.of(ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "candy_cane/red"), ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "candy_cane/blue"), ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "candy_cane/yellow"), ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "candy_cane/green"));

	public HugeCandyCaneFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		RandomSource randomsource = context.random();
		WorldGenLevel worldgenlevel = context.level();
		BlockPos blockpos = context.origin();
		Rotation rotation = Rotation.getRandom(randomsource);
		
		StructureTemplateManager structuretemplatemanager = worldgenlevel.getLevel().getServer().getStructureManager();
		StructureTemplate structuretemplate = structuretemplatemanager.getOrCreate(list.get(randomsource.nextInt(4)));
		ChunkPos chunkpos = new ChunkPos(blockpos);
		BoundingBox boundingbox = new BoundingBox(chunkpos.getMinBlockX() - 16, worldgenlevel.getMinBuildHeight(), chunkpos.getMinBlockZ() - 16, chunkpos.getMaxBlockX() + 16, worldgenlevel.getMaxBuildHeight(), chunkpos.getMaxBlockZ() + 16);
		StructurePlaceSettings structureplacesettings = (new StructurePlaceSettings()).setRotation(rotation).setBoundingBox(boundingbox).setRandom(randomsource);
		Vec3i vec3i = structuretemplate.getSize(rotation);
		BlockPos blockpos1 = blockpos.offset(-vec3i.getX() / 2, 0, -vec3i.getZ() / 2);
		int j = blockpos.getY();
	
		for(int k = 0; k < vec3i.getX(); ++k) {
			for(int l = 0; l < vec3i.getZ(); ++l) {
				j = Math.min(j, worldgenlevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockpos1.getX() + k, blockpos1.getZ() + l));
			}
		}
	
		BlockPos blockpos2 = structuretemplate.getZeroPositionWithTransform(blockpos1.atY(j), Mirror.NONE, rotation);
		if (worldgenlevel.getBlockState(blockpos2).isAir() && worldgenlevel.getBlockState(blockpos2.below()).is(Blocks.GRASS_BLOCK)) {
			structureplacesettings.clearProcessors();
			structuretemplate.placeInWorld(worldgenlevel, blockpos2, blockpos2, structureplacesettings, randomsource, 4);
			return true;
		}
		return false;
	}
}