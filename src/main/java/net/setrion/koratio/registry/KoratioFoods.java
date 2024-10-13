package net.setrion.koratio.registry;

import net.minecraft.world.food.FoodProperties;

public class KoratioFoods {

	public static final FoodProperties RAW_PANGO = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
	public static final FoodProperties CRACKED_PANGO = new FoodProperties.Builder().nutrition(5).saturationModifier(0.8F).build();

	public static final FoodProperties FLUFFER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).build();
	public static final FoodProperties COOKED_FLUFFER = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();

	public static final FoodProperties SPIKED_PORKCHOP = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).build();
	public static final FoodProperties COOKED_SPIKED_PORKCHOP = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build();

	public static final FoodProperties CANDY_CANE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.15F).alwaysEdible().fast().build();
	public static final FoodProperties LOLLIPOP = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).alwaysEdible().fast().build();
	public static final FoodProperties BONBON = new FoodProperties.Builder().nutrition(1).saturationModifier(0.05F).alwaysEdible().fast().build();

	public static final FoodProperties UPNIP = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).build();
	public static final FoodProperties CEINANA = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).build();
}