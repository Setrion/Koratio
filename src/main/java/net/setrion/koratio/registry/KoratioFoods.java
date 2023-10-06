package net.setrion.koratio.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class KoratioFoods {

	public static final FoodProperties RAW_PANGO = new FoodProperties.Builder().nutrition(2).saturationMod(0.0F).build();
	public static final FoodProperties CRACKED_PANGO = new FoodProperties.Builder().nutrition(5).saturationMod(0.8F).build();
	
	public static final FoodProperties GOLDEN_BREAD = (new FoodProperties.Builder()).nutrition(10).saturationMod(1.2F).build();
	
	public static final FoodProperties SPIKED_PORKCHOP = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).meat().build();
	public static final FoodProperties COOKED_SPIKED_PORKCHOP = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).meat().build();
	
	public static final FoodProperties GOLDEN_CHICKEN = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 1200, 0), 0.3F).meat().build();
	public static final FoodProperties COOKED_GOLDEN_CHICKEN = (new FoodProperties.Builder()).nutrition(12).saturationMod(1.2F).meat().build();
}