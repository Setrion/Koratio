package net.setrion.koratio.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.entity.animal.GoldenChicken;
import net.setrion.koratio.world.entity.animal.GoldenParrot;
import net.setrion.koratio.world.entity.animal.SpikyPig;
import net.setrion.koratio.world.entity.animal.UnicornCat;
import net.setrion.koratio.world.entity.monster.AmethystSpider;
import net.setrion.koratio.world.entity.monster.Crystallize;
import net.setrion.koratio.world.entity.monster.DemonicSoldier;
import net.setrion.koratio.world.entity.monster.FireBat;
import net.setrion.koratio.world.entity.monster.GingerbreadMan;
import net.setrion.koratio.world.entity.monster.IceBat;
import net.setrion.koratio.world.entity.monster.JumStem;
import net.setrion.koratio.world.entity.monster.SilverEnderMan;
import net.setrion.koratio.world.entity.monster.ThunderBat;
import net.setrion.koratio.world.entity.projectile.MushroomSpore;
import net.setrion.koratio.world.entity.vehicle.Boat;
import net.setrion.koratio.world.entity.vehicle.ChestBoat;

@Mod.EventBusSubscriber(modid = Koratio.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoratioEntityType {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Koratio.MOD_ID);
	
	public static final RegistryObject<EntityType<FireBat>> FIRE_BAT = ENTITY_TYPES.register("fire_bat", () -> EntityType.Builder.<FireBat>of(FireBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F).build("fire_bat"));
	public static final RegistryObject<EntityType<IceBat>> ICE_BAT = ENTITY_TYPES.register("ice_bat", () -> EntityType.Builder.<IceBat>of(IceBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F).build("ice_bat"));
	public static final RegistryObject<EntityType<ThunderBat>> THUNDER_BAT = ENTITY_TYPES.register("thunder_bat", () -> EntityType.Builder.<ThunderBat>of(ThunderBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F).build("thunder_bat"));
	public static final RegistryObject<EntityType<JumStem>> JUMSTEM = ENTITY_TYPES.register("jumstem", () -> EntityType.Builder.<JumStem>of(JumStem::new, MobCategory.MONSTER).sized(0.5F, 1.3F).build("jumstem"));
	public static final RegistryObject<EntityType<SilverEnderMan>> SILVER_ENDERMAN = ENTITY_TYPES.register("silver_enderman", () -> EntityType.Builder.<SilverEnderMan>of(SilverEnderMan::new, MobCategory.MONSTER).sized(0.6F, 2.9F).build("silver_enderman"));
	
	public static final RegistryObject<EntityType<GingerbreadMan>> GINGERBREAD_MAN = ENTITY_TYPES.register("gingerbread_man", () -> EntityType.Builder.<GingerbreadMan>of(GingerbreadMan::new, MobCategory.MONSTER).sized(0.5F, 1.9F).build("gingerbread_man"));
	
	public static final RegistryObject<EntityType<Crystallize>> CRYSTALLIZE = ENTITY_TYPES.register("crystallize", () -> EntityType.Builder.<Crystallize>of(Crystallize::new, MobCategory.MONSTER).sized(0.5F, 2.0F).build("crystallize"));
	
	public static final RegistryObject<EntityType<AmethystSpider>> AMETHYST_SPIDER = ENTITY_TYPES.register("amethyst_spider", () -> EntityType.Builder.<AmethystSpider>of(AmethystSpider::new, MobCategory.MONSTER).sized(0.45F, 0.45F).build("amethyst_spider"));
	
	public static final RegistryObject<EntityType<MushroomSpore>> MUSHROOM_SPORE = ENTITY_TYPES.register("mushroom_spore", () -> EntityType.Builder.<MushroomSpore>of(MushroomSpore::new, MobCategory.MISC).sized(0.2F, 0.2F).build("mushroom_spore"));
	
	public static final RegistryObject<EntityType<UnicornCat>> UNICORN_CAT = ENTITY_TYPES.register("unicorn_cat", () -> EntityType.Builder.<UnicornCat>of(UnicornCat::new, MobCategory.CREATURE).sized(0.75F, 0.5F).build("unicorn_cat"));
	public static final RegistryObject<EntityType<SpikyPig>> SPIKY_PIG = ENTITY_TYPES.register("spiky_pig", () -> EntityType.Builder.<SpikyPig>of(SpikyPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).build("spiky_pig"));
	public static final RegistryObject<EntityType<GoldenChicken>> GOLDEN_CHICKEN = ENTITY_TYPES.register("golden_chicken", () -> EntityType.Builder.<GoldenChicken>of(GoldenChicken::new, MobCategory.CREATURE).sized(0.4F, 0.7F).build("golden_chicken"));
	public static final RegistryObject<EntityType<GoldenParrot>> GOLDEN_PARROT = ENTITY_TYPES.register("golden_parrot", () -> EntityType.Builder.<GoldenParrot>of(GoldenParrot::new, MobCategory.CREATURE).sized(0.5F, 0.9F).build("golden_parrot"));
	//Demon-King Calortes, his 8 Mages (2 Healer, 2 Fire, 2 Ice, 2 Dark)
	
	//The 7 Ancient Demons: Time-Sorcerer Tergime; Space-Sorcerer Kergace; Ice- and Fire-Mage Arkire;
	
	//Demons
	public static final RegistryObject<EntityType<DemonicSoldier>> DEMONIC_SOLDIER = ENTITY_TYPES.register("demonic_soldier", () -> EntityType.Builder.<DemonicSoldier>of(DemonicSoldier::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("demonic_soldier"));
	
	//Misc
	public static final RegistryObject<EntityType<Boat>> BOAT = ENTITY_TYPES.register("boat", () -> EntityType.Builder.<Boat>of(Boat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
	public static final RegistryObject<EntityType<ChestBoat>> CHEST_BOAT = ENTITY_TYPES.register("chest_boat", () -> EntityType.Builder.<ChestBoat>of(ChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
	
	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(FIRE_BAT.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, FireBat::checkBatSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(ICE_BAT.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, IceBat::checkBatSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(THUNDER_BAT.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, ThunderBat::checkBatSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(JUMSTEM.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(AMETHYST_SPIDER.get(), Type.NO_RESTRICTIONS, Types.MOTION_BLOCKING_NO_LEAVES, AmethystSpider::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		
		event.register(UNICORN_CAT.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(SPIKY_PIG.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
	}
	
	@SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(KoratioEntityType.FIRE_BAT.get(), FireBat.createAttributes().build());
		event.put(KoratioEntityType.ICE_BAT.get(), IceBat.createAttributes().build());
		event.put(KoratioEntityType.THUNDER_BAT.get(), ThunderBat.createAttributes().build());
		event.put(KoratioEntityType.UNICORN_CAT.get(), UnicornCat.createAttributes().build());
		event.put(KoratioEntityType.SPIKY_PIG.get(), SpikyPig.createAttributes().build());
		event.put(KoratioEntityType.DEMONIC_SOLDIER.get(), DemonicSoldier.createAttributes().build());
		event.put(KoratioEntityType.GOLDEN_CHICKEN.get(), GoldenChicken.createAttributes().build());
		event.put(KoratioEntityType.GOLDEN_PARROT.get(), GoldenParrot.createAttributes().build());
		event.put(KoratioEntityType.JUMSTEM.get(), JumStem.createMonsterAttributes().build());
		event.put(KoratioEntityType.SILVER_ENDERMAN.get(), SilverEnderMan.createAttributes().build());
		event.put(KoratioEntityType.CRYSTALLIZE.get(), Crystallize.createAttributes().build());
		event.put(KoratioEntityType.AMETHYST_SPIDER.get(), AmethystSpider.createAmethystSpider().build());
		event.put(KoratioEntityType.GINGERBREAD_MAN.get(), GingerbreadMan.createMonsterAttributes().build());
	}
}