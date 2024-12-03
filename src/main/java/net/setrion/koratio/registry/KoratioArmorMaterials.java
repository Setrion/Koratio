package net.setrion.koratio.registry;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentModels;
import net.setrion.koratio.data.KoratioEquipmentModels;

import java.util.EnumMap;

public class KoratioArmorMaterials {

	public static final ArmorMaterial BONE =  new ArmorMaterial(15, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
		map.put(ArmorType.LEGGINGS, 4);
		map.put(ArmorType.CHESTPLATE, 6);
		map.put(ArmorType.HELMET, 2);
		map.put(ArmorType.BODY, 5);
	}), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0F, 0F, KoratioTags.Items.REPAIRS_BONE_ARMOR, KoratioEquipmentModels.BONE);

	public static final ArmorMaterial WITHER_BONE = new ArmorMaterial(24, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 5);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 3);
		map.put(ArmorType.BODY, 6);
	}), 24, SoundEvents.ARMOR_EQUIP_GENERIC, 0F, 0F, KoratioTags.Items.REPAIRS_WITHER_BONE_ARMOR, KoratioEquipmentModels.WITHER_BONE);

	public static final ArmorMaterial VARYNIUM = new ArmorMaterial(24, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 5);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 3);
		map.put(ArmorType.BODY, 6);
	}), 24, SoundEvents.ARMOR_EQUIP_GENERIC, 0F, 0F, KoratioTags.Items.REPAIRS_VARYNIUM_ARMOR, KoratioEquipmentModels.VARYNIUM);
}