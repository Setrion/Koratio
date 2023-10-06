package net.setrion.koratio.registry;

import net.minecraft.client.model.BatModel;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.setrion.koratio.client.model.CrystallizeModel;
import net.setrion.koratio.client.model.GingerbreadManModel;
import net.setrion.koratio.client.model.JumStemModel;
import net.setrion.koratio.client.model.MushroomSlimeModel;
import net.setrion.koratio.client.model.MushroomSporeModel;
import net.setrion.koratio.client.model.SpikyPigModel;
import net.setrion.koratio.client.model.UnicornCatModel;
import net.setrion.koratio.world.entity.vehicle.Boat;

public class ModelLayers {
	public static final ModelLayerLocation FIRE_BAT = createLocation("fire_bat", "main");
	public static final ModelLayerLocation ICE_BAT = createLocation("ice_bat", "main");
	public static final ModelLayerLocation THUNDER_BAT = createLocation("thunder_bat", "main");
	public static final ModelLayerLocation UNICORN_CAT = createLocation("unicorn_cat", "main");
	public static final ModelLayerLocation SPIKY_PIG = createLocation("spiky_pig", "main");
	public static final ModelLayerLocation MUSHROOM_SLIME_INNER = createLocation("mushroom_slime", "main");
	public static final ModelLayerLocation MUSHROOM_SLIME_OUTER = createLocation("mushroom_slime", "outer");
	public static final ModelLayerLocation JUMSTEM = createLocation("jumstem", "main");
	public static final ModelLayerLocation CRYSTALLIZE = createLocation("crystallize", "main");
	public static final ModelLayerLocation AMETHYST_SPIDER = createLocation("amethyst_spider", "main");
	
	public static final ModelLayerLocation GOLDEN_CHICKEN = createLocation("golden_chicken", "main");
	public static final ModelLayerLocation GOLDEN_PARROT = createLocation("golden_parrot", "main");
	
	public static final ModelLayerLocation MUSHROOM_SPORE = createLocation("mushroom_spore", "main");
	
	public static final ModelLayerLocation GINGERBREAD_MAN = createLocation("gingerbread_man", "main");
	
	public static final ModelLayerLocation DEMONIC_SOLDIER = createLocation("demonic_soldier", "main");
	public static final ModelLayerLocation DEMONIC_SOLDIER_INNER_ARMOR = createInnerArmor("demonic_soldier");
	public static final ModelLayerLocation DEMONIC_SOLDIER_OUTER_ARMOR = createOuterArmor("demonic_soldier");
	
	public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(FIRE_BAT, () -> BatModel.createBodyLayer());
		event.registerLayerDefinition(ICE_BAT, () -> BatModel.createBodyLayer());
		event.registerLayerDefinition(THUNDER_BAT, () -> BatModel.createBodyLayer());
		event.registerLayerDefinition(UNICORN_CAT, () -> UnicornCatModel.createBodyLayer());
		event.registerLayerDefinition(SPIKY_PIG, () -> SpikyPigModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(MUSHROOM_SLIME_INNER, () -> MushroomSlimeModel.createInnerBodyLayer());
		event.registerLayerDefinition(MUSHROOM_SLIME_OUTER, () -> MushroomSlimeModel.createOuterBodyLayer());
		event.registerLayerDefinition(JUMSTEM, () -> JumStemModel.createBodyLayer());
		event.registerLayerDefinition(CRYSTALLIZE, () -> CrystallizeModel.createBodyLayer());
		event.registerLayerDefinition(AMETHYST_SPIDER, () -> SpiderModel.createSpiderBodyLayer());
		event.registerLayerDefinition(GOLDEN_CHICKEN, () -> ChickenModel.createBodyLayer());
		event.registerLayerDefinition(GOLDEN_PARROT, () -> ParrotModel.createBodyLayer());
		event.registerLayerDefinition(MUSHROOM_SPORE, () -> MushroomSporeModel.createBodyLayer());
		event.registerLayerDefinition(GINGERBREAD_MAN, () -> GingerbreadManModel.createBodyLayer());
		event.registerLayerDefinition(DEMONIC_SOLDIER, () -> LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64));
		event.registerLayerDefinition(DEMONIC_SOLDIER_INNER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32));
		event.registerLayerDefinition(DEMONIC_SOLDIER_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32));
		for(Boat.Type boat$type : Boat.Type.values()) {
			event.registerLayerDefinition(ModelLayers.createBoatModelName(boat$type), () -> BoatModel.createBodyModel());
			event.registerLayerDefinition(ModelLayers.createChestBoatModelName(boat$type), () -> ChestBoatModel.createBodyModel());
		}
	}
	
	private static ModelLayerLocation createLocation(String model, String layer) {
		return new ModelLayerLocation(new ResourceLocation("koratio", model), layer);
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