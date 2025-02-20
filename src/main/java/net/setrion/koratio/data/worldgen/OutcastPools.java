package net.setrion.koratio.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class OutcastPools {
	public static final ResourceKey<StructureTemplatePool> START = Pools.createKey("outcast/houses");

	public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
		HolderGetter<StructureTemplatePool> templategetter = context.lookup(Registries.TEMPLATE_POOL);
		Holder<StructureTemplatePool> holder1 = templategetter.getOrThrow(Pools.EMPTY);
		context.register(START, new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.legacy("outcast/house_1"), 1)), StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "outcast/portals", new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.legacy("outcast/portal_1"), 1), Pair.of(StructurePoolElement.legacy("outcast/portal_2"), 1)), StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "outcast/features", new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.legacy("outcast/farm_1"), 1), Pair.of(StructurePoolElement.legacy("outcast/animal_pen_1"), 1), Pair.of(StructurePoolElement.legacy("outcast/lake_1"), 1), Pair.of(StructurePoolElement.legacy("outcast/log_1"), 1), Pair.of(StructurePoolElement.legacy("outcast/log_2"), 1)), StructureTemplatePool.Projection.RIGID));
	}
}