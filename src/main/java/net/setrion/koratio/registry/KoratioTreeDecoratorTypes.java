package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.levelgen.feature.treedecorators.LeaveGildedVineDecorator;

public class KoratioTreeDecoratorTypes {
	
	public static final DeferredRegister<TreeDecoratorType<?>> DECORATOR_TYPES = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Koratio.MOD_ID);
	
	public static final RegistryObject<TreeDecoratorType<?>> GILDED_VINE_LEAVE_DECORATOR = DECORATOR_TYPES.register("gilded_vine_leave_decorator", () -> new TreeDecoratorType<>(LeaveGildedVineDecorator.CODEC));

}