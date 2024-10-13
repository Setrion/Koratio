package net.setrion.koratio.registry;

import net.minecraft.world.level.block.grower.TreeGrower;
import org.antlr.v4.runtime.tree.Tree;

import java.util.Optional;

public class KoratioTreeGrower {
    public static final TreeGrower PANGO = new TreeGrower("pango", Optional.empty(), Optional.of(KoratioConfiguredFeatures.PANGO_BUSH), Optional.empty());
    public static final TreeGrower RUGONA = new TreeGrower("rugona", Optional.empty(), Optional.of(KoratioConfiguredFeatures.RUGONA), Optional.empty());
    public static final TreeGrower VARESO = new TreeGrower("vareso", Optional.of(KoratioConfiguredFeatures.VARESO), Optional.empty(), Optional.empty());
    public static final TreeGrower CANDY = new TreeGrower("candy", Optional.empty(), Optional.of(KoratioConfiguredFeatures.CANDY_TREE), Optional.empty());
    public static final TreeGrower ELVEN = new TreeGrower("elven", Optional.of(KoratioConfiguredFeatures.ELVEN), Optional.empty(), Optional.empty());
}