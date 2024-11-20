package net.setrion.koratio.registry;

import net.minecraft.client.model.*;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.setrion.koratio.client.model.*;
import net.setrion.koratio.client.model.block.RemainsModel;

public class ModelLayers {
	public static final ModelLayerLocation MAGICAL_CAT = createLocation("magical_cat", "main");
	public static final ModelLayerLocation BABY_MAGICAL_CAT = createLocation("baby_magical_cat", "main");
	public static final ModelLayerLocation FLUFFER = createLocation("fluffer", "main");
	public static final ModelLayerLocation FLUFFER_WOOL = createLocation("fluffer", "wool");
	public static final ModelLayerLocation MOOSHROOM = createLocation("mooshroom", "main");
	public static final ModelLayerLocation BABY_MOOSHROOM = createLocation("baby_mooshroom", "main");
	public static final ModelLayerLocation SPIKY_PIG = createLocation("spiky_pig", "main");
	public static final ModelLayerLocation BABY_SPIKY_PIG = createLocation("baby_spiky_pig", "main");
	public static final ModelLayerLocation GOLDEN_FOX_SPIRIT = createLocation("golden_fox_spirit", "main");
	public static final ModelLayerLocation JUMSTEM = createLocation("jumstem", "main");
	public static final ModelLayerLocation CRYSTALLIZE = createLocation("crystallize", "main");
	
	public static final ModelLayerLocation MUSHROOM_SPORE = createLocation("mushroom_spore", "main");
	
	public static final ModelLayerLocation GINGERBREAD_MAN = createLocation("gingerbread_man", "main");

	public static final ModelLayerLocation NECROMANCER = createLocation("necromancer", "main");
	public static final ModelLayerLocation NECROMANCER_OUTER_LAYER = createLocation("necromancer", "outer");
	public static final ModelLayerLocation NECROMANCER_SKULL = createLocation("necromancer_skull", "main");

	public static final ModelLayerLocation SKELETON_REMAINS = createLocation("skeleton_remains", "main");
	public static final ModelLayerLocation WITHER_SKELETON_REMAINS = createLocation("wither_skeleton_remains", "main");
	public static final ModelLayerLocation STRAY_REMAINS = createLocation("stray_remains", "main");
	public static final ModelLayerLocation STRAY_REMAINS_OVERLAY = createLocation("stray_remains", "overlay");
	public static final ModelLayerLocation BOGGED_REMAINS = createLocation("bogged_remains", "main");
	public static final ModelLayerLocation BOGGED_REMAINS_OVERLAY = createLocation("bogged_remains", "overlay");
	public static final ModelLayerLocation DEMONIC_SKELETON_REMAINS = createLocation("demonic_skeleton_remains", "main");

	public static final ModelLayerLocation ZOMBIE_REMAINS = createLocation("zombie_remains", "main");
	public static final ModelLayerLocation HUSK_REMAINS = createLocation("husk_remains", "main");
	public static final ModelLayerLocation DROWNED_REMAINS = createLocation("drowned_remains", "main");
	public static final ModelLayerLocation DROWNED_REMAINS_OVERLAY = createLocation("drowned_remains", "overlay");
	public static final ModelLayerLocation DEMONIC_ZOMBIE_REMAINS = createLocation("demonic_zombie_remains", "main");
	public static final ModelLayerLocation ZOMBIE_VILLAGER_REMAINS = createLocation("zombie_villager_remains", "main");
	public static final ModelLayerLocation ZOMBIE_VILLAGER_REMAINS_OVERLAY = createLocation("zombie_villager_remains", "overlay");

	public static final ModelLayerLocation PHANTOM_REMAINS = createLocation("phantom_remains", "main");

	public static final ModelLayerLocation ZOMBIFIED_PIGLIN_REMAINS = createLocation("zombified_piglin_remains", "main");

	public static final ModelLayerLocation DEMONIC_ZOMBIE_HORSE = createLocation("demonic_zombie_horse", "main");
	public static final ModelLayerLocation DEMONIC_ZOMBIE_HORSE_BABY = createLocation("demonic_zombie_horse_baby", "main");
	public static final ModelLayerLocation DEMONIC_SKELETON_HORSE = createLocation("demonic_skeleton_horse", "main");
	public static final ModelLayerLocation DEMONIC_SKELETON_HORSE_BABY = createLocation("demonic_skeleton_horse_baby", "main");

	public static final ModelLayerLocation DEMONIC_ZOMBIE = createLocation("demonic_zombie", "main");
	public static final ModelLayerLocation DEMONIC_ZOMBIE_INNER_ARMOR = createInnerArmor("demonic_zombie");
	public static final ModelLayerLocation DEMONIC_ZOMBIE_OUTER_ARMOR = createOuterArmor("demonic_zombie");

	public static final ModelLayerLocation DEMONIC_SKELETON = createLocation("demonic_skeleton", "main");
	public static final ModelLayerLocation DEMONIC_SKELETON_INNER_ARMOR = createInnerArmor("demonic_skeleton");
	public static final ModelLayerLocation DEMONIC_SKELETON_OUTER_ARMOR = createOuterArmor("demonic_skeleton");

	public static final ModelLayerLocation DEMONIC_SOLDIER = createLocation("demonic_soldier", "main");
	public static final ModelLayerLocation DEMONIC_SOLDIER_INNER_ARMOR = createInnerArmor("demonic_soldier");
	public static final ModelLayerLocation DEMONIC_SOLDIER_OUTER_ARMOR = createOuterArmor("demonic_soldier");

	//Boats
	public static final ModelLayerLocation PANGO_BOAT = createLocation("boat/pango", "main");
	public static final ModelLayerLocation PANGO_CHEST_BOAT = createLocation("chest_boat/pango", "main");
	public static final ModelLayerLocation RUGONA_BOAT = createLocation("boat/rugona", "main");
	public static final ModelLayerLocation RUGONA_CHEST_BOAT = createLocation("chest_boat/rugona", "main");
	public static final ModelLayerLocation VARESO_BOAT = createLocation("boat/vareso", "main");
	public static final ModelLayerLocation VARESO_CHEST_BOAT = createLocation("chest_boat/vareso", "main");
	public static final ModelLayerLocation CANDY_BOAT = createLocation("boat/candy", "main");
	public static final ModelLayerLocation CANDY_CHEST_BOAT = createLocation("chest_boat/candy", "main");
	public static final ModelLayerLocation CHOCOLATE_OAK_BOAT = createLocation("boat/chocolate_oak", "main");
	public static final ModelLayerLocation CHOCOLATE_OAK_CHEST_BOAT = createLocation("chest_boat/chocolate_oak", "main");
	public static final ModelLayerLocation ELVEN_BOAT = createLocation("boat/elven", "main");
	public static final ModelLayerLocation ELVEN_CHEST_BOAT = createLocation("chest_boat/elven", "main");
	public static final ModelLayerLocation BLUE_ELVEN_BOAT = createLocation("boat/blue_elven", "main");
	public static final ModelLayerLocation BLUE_ELVEN_CHEST_BOAT = createLocation("chest_boat/blue_elven", "main");
	public static final ModelLayerLocation CYAN_ELVEN_BOAT = createLocation("boat/cyan_elven", "main");
	public static final ModelLayerLocation CYAN_ELVEN_CHEST_BOAT = createLocation("chest_boat/cyan_elven", "main");
	public static final ModelLayerLocation GREEN_ELVEN_BOAT = createLocation("boat/green_elven", "main");
	public static final ModelLayerLocation GREEN_ELVEN_CHEST_BOAT = createLocation("chest_boat/green_elven", "main");
	
	public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(MAGICAL_CAT, MagicalCatModel::createBodyLayer);
		event.registerLayerDefinition(BABY_MAGICAL_CAT, () -> MagicalCatModel.createBodyLayer().apply(MagicalCatModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(FLUFFER, FlufferModel::createBodyLayer);
		event.registerLayerDefinition(FLUFFER_WOOL, FlufferWoolModel::createWoolLayer);
		event.registerLayerDefinition(MOOSHROOM, CowModel::createBodyLayer);
		event.registerLayerDefinition(BABY_MOOSHROOM, () -> CowModel.createBodyLayer().apply(CowModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(SPIKY_PIG, SpikyPigModel::createBodyLayer);
		event.registerLayerDefinition(BABY_SPIKY_PIG, () -> SpikyPigModel.createBodyLayer().apply(SpikyPigModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(GOLDEN_FOX_SPIRIT, GoldenFoxSpiritModel::createBodyLayer);
		event.registerLayerDefinition(JUMSTEM, JumStemModel::createBodyLayer);
		event.registerLayerDefinition(CRYSTALLIZE, CrystallizeModel::createBodyLayer);
		event.registerLayerDefinition(MUSHROOM_SPORE, MushroomSporeModel::createBodyLayer);
		event.registerLayerDefinition(GINGERBREAD_MAN, GingerbreadManModel::createBodyLayer);
		event.registerLayerDefinition(NECROMANCER, () -> NecromancerModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(NECROMANCER_OUTER_LAYER, () -> NecromancerModel.createBodyLayer(new CubeDeformation(0.25F)));
		event.registerLayerDefinition(NECROMANCER_SKULL, NecromancerSkullModel::createBodyLayer);

		event.registerLayerDefinition(SKELETON_REMAINS, () -> RemainsModel.createSkeletonLayer(CubeDeformation.NONE, 64, 32));
		event.registerLayerDefinition(WITHER_SKELETON_REMAINS, () -> RemainsModel.createSkeletonLayer(CubeDeformation.NONE, 64, 32));
		event.registerLayerDefinition(STRAY_REMAINS, () -> RemainsModel.createSkeletonLayer(CubeDeformation.NONE, 64, 32));
		event.registerLayerDefinition(STRAY_REMAINS_OVERLAY, () -> RemainsModel.createSkeletonLayer(new CubeDeformation(0.25F), 64, 32));
		event.registerLayerDefinition(BOGGED_REMAINS, () -> RemainsModel.createSkeletonLayer(CubeDeformation.NONE, 64, 32));
		event.registerLayerDefinition(BOGGED_REMAINS_OVERLAY, () -> RemainsModel.createSkeletonLayer(new CubeDeformation(0.25F), 64, 32));
		event.registerLayerDefinition(DEMONIC_SKELETON_REMAINS, () -> RemainsModel.createSkeletonLayer(CubeDeformation.NONE, 64, 64));

		event.registerLayerDefinition(ZOMBIE_REMAINS, () -> RemainsModel.createZombieLayer(CubeDeformation.NONE, 64, 64));
		event.registerLayerDefinition(HUSK_REMAINS, () -> RemainsModel.createZombieLayer(CubeDeformation.NONE, 64, 64));
		event.registerLayerDefinition(DROWNED_REMAINS, () -> RemainsModel.createZombieLayer(CubeDeformation.NONE, 64, 64));
		event.registerLayerDefinition(DROWNED_REMAINS_OVERLAY, () -> RemainsModel.createZombieLayer(new CubeDeformation(0.25F), 64, 64));
		event.registerLayerDefinition(DEMONIC_ZOMBIE_REMAINS, () -> RemainsModel.createZombieLayer(CubeDeformation.NONE, 64, 64));

		event.registerLayerDefinition(ZOMBIE_VILLAGER_REMAINS, () -> RemainsModel.createZombieVillagerLayer(CubeDeformation.NONE, 64, 64));
		event.registerLayerDefinition(ZOMBIE_VILLAGER_REMAINS_OVERLAY, () -> RemainsModel.createZombieVillagerLayer(new CubeDeformation((0.25F)), 64, 64));

		event.registerLayerDefinition(PHANTOM_REMAINS, () -> RemainsModel.createPhantomLayer(CubeDeformation.NONE, 64, 64));

		event.registerLayerDefinition(ZOMBIFIED_PIGLIN_REMAINS, () -> RemainsModel.createZombifiedPiglinLayer(CubeDeformation.NONE, 64, 64));

		event.registerLayerDefinition(DEMONIC_ZOMBIE_HORSE, () -> LayerDefinition.create(AbstractEquineModel.createBodyMesh(CubeDeformation.NONE), 64, 64));
		event.registerLayerDefinition(DEMONIC_ZOMBIE_HORSE_BABY, () -> LayerDefinition.create(AbstractEquineModel.createBabyMesh(CubeDeformation.NONE), 64, 64));
		event.registerLayerDefinition(DEMONIC_ZOMBIE, DemonicZombieModel::createBodyLayer);
		event.registerLayerDefinition(DEMONIC_ZOMBIE_INNER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_ZOMBIE_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SKELETON_HORSE, () -> LayerDefinition.create(AbstractEquineModel.createBodyMesh(CubeDeformation.NONE), 64, 64));
		event.registerLayerDefinition(DEMONIC_SKELETON_HORSE_BABY, () -> LayerDefinition.create(AbstractEquineModel.createBabyMesh(CubeDeformation.NONE), 64, 64));
		event.registerLayerDefinition(DEMONIC_SKELETON, DemonicSkeletonModel::createBodyLayer);
		event.registerLayerDefinition(DEMONIC_SKELETON_INNER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SKELETON_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SOLDIER, () -> LayerDefinition.create(DemonicSoldierModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64));
		event.registerLayerDefinition(DEMONIC_SOLDIER_INNER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SOLDIER_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32));
		LayerDefinition boatDefinition = BoatModel.createBoatModel();
		LayerDefinition chestBoatDefinition = BoatModel.createChestBoatModel();
		event.registerLayerDefinition(PANGO_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(PANGO_CHEST_BOAT, () -> chestBoatDefinition);
		event.registerLayerDefinition(RUGONA_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(RUGONA_CHEST_BOAT, () -> chestBoatDefinition);
		event.registerLayerDefinition(VARESO_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(VARESO_CHEST_BOAT, () -> chestBoatDefinition);
		event.registerLayerDefinition(CANDY_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(CANDY_CHEST_BOAT, () -> chestBoatDefinition);
		event.registerLayerDefinition(CHOCOLATE_OAK_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(CHOCOLATE_OAK_CHEST_BOAT, () -> chestBoatDefinition);
		event.registerLayerDefinition(ELVEN_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(ELVEN_CHEST_BOAT, () -> chestBoatDefinition);
		event.registerLayerDefinition(BLUE_ELVEN_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(BLUE_ELVEN_CHEST_BOAT, () -> chestBoatDefinition);
		event.registerLayerDefinition(CYAN_ELVEN_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(CYAN_ELVEN_CHEST_BOAT, () -> chestBoatDefinition);
		event.registerLayerDefinition(GREEN_ELVEN_BOAT, () -> boatDefinition);
		event.registerLayerDefinition(GREEN_ELVEN_CHEST_BOAT, () -> chestBoatDefinition);
	}
	
	private static ModelLayerLocation createLocation(String model, String layer) {
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("koratio", model), layer);
	}
	
	private static ModelLayerLocation createInnerArmor(String name) {
		return createLocation(name, "inner_armor");
	}

	private static ModelLayerLocation createOuterArmor(String name) {
		return createLocation(name, "outer_armor");
	}
}