package net.setrion.koratio.registry;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.setrion.koratio.client.model.*;
import net.setrion.koratio.world.entity.vehicle.Boat;

public class ModelLayers {
	public static final ModelLayerLocation MAGICAL_CAT = createLocation("magical_cat", "main");
	public static final ModelLayerLocation FLUFFER = createLocation("fluffer", "main");
	public static final ModelLayerLocation FLUFFER_WOOL = createLocation("fluffer", "wool");
	public static final ModelLayerLocation SPIKY_PIG = createLocation("spiky_pig", "main");
	public static final ModelLayerLocation GOLDEN_FOX_SPIRIT = createLocation("golden_fox_spirit", "main");
	public static final ModelLayerLocation JUMSTEM = createLocation("jumstem", "main");
	public static final ModelLayerLocation CRYSTALLIZE = createLocation("crystallize", "main");
	
	public static final ModelLayerLocation MUSHROOM_SPORE = createLocation("mushroom_spore", "main");
	
	public static final ModelLayerLocation GINGERBREAD_MAN = createLocation("gingerbread_man", "main");

	public static final ModelLayerLocation NECROMANCER = createLocation("necromancer", "main");
	public static final ModelLayerLocation NECROMANCER_SKULL = createLocation("necromancer_skull", "main");

	public static final ModelLayerLocation DEMONIC_ZOMBIE = createLocation("demonic_zombie", "main");
	public static final ModelLayerLocation DEMONIC_ZOMBIE_INNER_ARMOR = createInnerArmor("demonic_zombie");
	public static final ModelLayerLocation DEMONIC_ZOMBIE_OUTER_ARMOR = createOuterArmor("demonic_zombie");

	public static final ModelLayerLocation DEMONIC_SKELETON = createLocation("demonic_skeleton", "main");
	public static final ModelLayerLocation DEMONIC_SKELETON_INNER_ARMOR = createInnerArmor("demonic_skeleton");
	public static final ModelLayerLocation DEMONIC_SKELETON_OUTER_ARMOR = createOuterArmor("demonic_skeleton");

	public static final ModelLayerLocation DEMONIC_SOLDIER = createLocation("demonic_soldier", "main");
	public static final ModelLayerLocation DEMONIC_SOLDIER_INNER_ARMOR = createInnerArmor("demonic_soldier");
	public static final ModelLayerLocation DEMONIC_SOLDIER_OUTER_ARMOR = createOuterArmor("demonic_soldier");
	
	public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(MAGICAL_CAT, MagicalCatModel::createBodyLayer);
		event.registerLayerDefinition(FLUFFER, FlufferModel::createBodyLayer);
		event.registerLayerDefinition(FLUFFER_WOOL, FlufferWoolModel::createWoolLayer);
		event.registerLayerDefinition(SPIKY_PIG, () -> SpikyPigModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(GOLDEN_FOX_SPIRIT, GoldenFoxSpiritModel::createBodyLayer);
		event.registerLayerDefinition(JUMSTEM, JumStemModel::createBodyLayer);
		event.registerLayerDefinition(CRYSTALLIZE, CrystallizeModel::createBodyLayer);
		event.registerLayerDefinition(MUSHROOM_SPORE, MushroomSporeModel::createBodyLayer);
		event.registerLayerDefinition(GINGERBREAD_MAN, GingerbreadManModel::createBodyLayer);
		//event.registerLayerDefinition(NECROMANCER, NecromancerModel::createBodyLayer);
		//event.registerLayerDefinition(NECROMANCER_SKULL, NecromancerSkullModel::createBodyLayer);
		event.registerLayerDefinition(DEMONIC_ZOMBIE, DemonicZombieModel::createBodyLayer);
		event.registerLayerDefinition(DEMONIC_ZOMBIE_INNER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_ZOMBIE_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SKELETON, DemonicSkeletonModel::createBodyLayer);
		event.registerLayerDefinition(DEMONIC_SKELETON_INNER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SKELETON_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SOLDIER, () -> LayerDefinition.create(DemonicSoldierModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64));
		event.registerLayerDefinition(DEMONIC_SOLDIER_INNER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SOLDIER_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32));
		for(Boat.Type boat$type : Boat.Type.values()) {
			event.registerLayerDefinition(ModelLayers.createBoatModelName(boat$type), BoatModel::createBodyModel);
			event.registerLayerDefinition(ModelLayers.createChestBoatModelName(boat$type), ChestBoatModel::createBodyModel);
		}
	}
	
	private static ModelLayerLocation createLocation(String model, String layer) {
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("koratio", model), layer);
	}
	
	public static ModelLayerLocation createBoatModelName(Boat.Type type) {
		return createLocation("boat/" + type.getName(), "main");
	}

	public static ModelLayerLocation createChestBoatModelName(Boat.Type type) {
		return createLocation("chest_boat/" + type.getName(), "main");
	}
	
	private static ModelLayerLocation createInnerArmor(String name) {
		return createLocation(name, "inner_armor");
	}

	private static ModelLayerLocation createOuterArmor(String name) {
		return createLocation(name, "outer_armor");
	}
}