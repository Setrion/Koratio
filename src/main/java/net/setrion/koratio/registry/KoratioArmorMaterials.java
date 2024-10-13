package net.setrion.koratio.registry;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;

import java.util.EnumMap;
import java.util.List;

public class KoratioArmorMaterials {

	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Koratio.MOD_ID);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BONE = ARMOR_MATERIALS.register("bone", () -> new ArmorMaterial(
			Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 1);
				map.put(ArmorItem.Type.LEGGINGS, 4);
				map.put(ArmorItem.Type.CHESTPLATE, 6);
				map.put(ArmorItem.Type.HELMET, 2);
			}), 15, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(Items.BONE),
			List.of(
					new ArmorMaterial.Layer(
							ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "bone")
					)
			),0,0));

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> WITHER_BONE = ARMOR_MATERIALS.register("wither_bone", () -> new ArmorMaterial(
			Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 2);
				map.put(ArmorItem.Type.LEGGINGS, 5);
				map.put(ArmorItem.Type.CHESTPLATE, 7);
				map.put(ArmorItem.Type.HELMET, 3);
			}), 24, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(KoratioItems.WITHER_BONE.get()),
			List.of(
					new ArmorMaterial.Layer(
							ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "wither_bone")
					)
			),1,0));
}