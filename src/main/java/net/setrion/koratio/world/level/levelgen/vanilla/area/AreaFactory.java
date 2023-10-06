package net.setrion.koratio.world.level.levelgen.vanilla.area;

public interface AreaFactory <A extends Area> {
	A make();
}