package net.setrion.koratio.registry;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.setrion.koratio.data.worldgen.OutcastPools;

public class KoratioPools {
	public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
		HolderGetter<StructureTemplatePool> holdergetter = context.lookup(Registries.TEMPLATE_POOL);
		Holder<StructureTemplatePool> holder = holdergetter.getOrThrow(Pools.EMPTY);
		context.register(Pools.EMPTY, new StructureTemplatePool(holder, ImmutableList.of(), StructureTemplatePool.Projection.RIGID));
		OutcastPools.bootstrap(context);
	}
}