package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.entity.animal.*;
import net.setrion.koratio.world.entity.demonic.*;
import net.setrion.koratio.world.entity.item.LevitatingBlockEntity;
import net.setrion.koratio.world.entity.monster.Crystallize;
import net.setrion.koratio.world.entity.monster.GingerbreadMan;
import net.setrion.koratio.world.entity.monster.JumStem;
import net.setrion.koratio.world.entity.projectile.MushroomSpore;
import net.setrion.koratio.world.entity.projectile.RevivingSoul;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Koratio.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class KoratioEntityType {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Koratio.MOD_ID);

	public static final DeferredHolder<EntityType<?>, EntityType<JumStem>> JUMSTEM = ENTITY_TYPES.register("jumstem", () -> EntityType.Builder.of(JumStem::new, MobCategory.MONSTER).sized(0.5F, 1.3F).build(entityId("jumstem")));

	public static final DeferredHolder<EntityType<?>, EntityType<GingerbreadMan>> GINGERBREAD_MAN = ENTITY_TYPES.register("gingerbread_man", () -> EntityType.Builder.of(GingerbreadMan::new, MobCategory.MONSTER).sized(0.5F, 1.9F).build(entityId("gingerbread_man")));
	
	public static final DeferredHolder<EntityType<?>, EntityType<Crystallize>> CRYSTALLIZE = ENTITY_TYPES.register("crystallize", () -> EntityType.Builder.of(Crystallize::new, MobCategory.MONSTER).sized(0.5F, 2.0F).build(entityId("crystallize")));

	public static final DeferredHolder<EntityType<?>, EntityType<MagicalCat>> MAGICAL_CAT = ENTITY_TYPES.register("magical_cat", () -> EntityType.Builder.of(MagicalCat::new, MobCategory.CREATURE).sized(0.75F, 0.5F).build(entityId("magical_cat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Fluffer>> FLUFFER = ENTITY_TYPES.register("fluffer", () -> EntityType.Builder.of(Fluffer::new, MobCategory.CREATURE).sized(0.85F, 0.85F).build(entityId("fluffer")));
	public static final DeferredHolder<EntityType<?>, EntityType<Mooshroom>> MOOSHROOM = ENTITY_TYPES.register("mooshroom", () -> EntityType.Builder.of(Mooshroom::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build(entityId("mooshroom")));
	public static final DeferredHolder<EntityType<?>, EntityType<SpikyPig>> SPIKY_PIG = ENTITY_TYPES.register("spiky_pig", () -> EntityType.Builder.of(SpikyPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).build(entityId("spiky_pig")));
	public static final DeferredHolder<EntityType<?>, EntityType<GoldenFoxSpirit>> GOLDEN_FOX_SPIRIT = ENTITY_TYPES.register("golden_fox_spirit", () -> EntityType.Builder.of(GoldenFoxSpirit::new, MobCategory.CREATURE).sized(0.5F, 1.6F).build(entityId("golden_fox_spirit")));
	
	//Demons
	public static final DeferredHolder<EntityType<?>, EntityType<Necromancer>> NECROMANCER = ENTITY_TYPES.register("necromancer", () -> EntityType.Builder.of(Necromancer::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(entityId("necromancer")));
	public static final DeferredHolder<EntityType<?>, EntityType<NecromancerSkull>> NECROMANCER_SKULL = ENTITY_TYPES.register("necromancer_skull", () -> EntityType.Builder.of(NecromancerSkull::new, MobCategory.MONSTER).sized(0.5F, 0.5F).noSummon().clientTrackingRange(8).build(entityId("necromancer_skull")));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonicZombieHorse>> DEMONIC_ZOMBIE_HORSE = ENTITY_TYPES.register("demonic_zombie_horse", () -> EntityType.Builder.of(DemonicZombieHorse::new, MobCategory.CREATURE).sized(1.3964844F, 1.6F).eyeHeight(1.52F).passengerAttachments(1.31875F).clientTrackingRange(10).build(entityId("demonic_zombie_horse")));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonicSkeletonHorse>> DEMONIC_SKELETON_HORSE = ENTITY_TYPES.register("demonic_skeleton_horse", () -> EntityType.Builder.of(DemonicSkeletonHorse::new, MobCategory.CREATURE).sized(1.3964844F, 1.6F).eyeHeight(1.52F).passengerAttachments(1.31875F).clientTrackingRange(10).build(entityId("demonic_skeleton_horse")));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonicZombie>> DEMONIC_ZOMBIE = ENTITY_TYPES.register("demonic_zombie", () -> EntityType.Builder.of(DemonicZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).ridingOffset(-0.7F).passengerAttachments(2.0125F).build(entityId("demonic_zombie")));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonicSkeleton>> DEMONIC_SKELETON = ENTITY_TYPES.register("demonic_skeleton", () -> EntityType.Builder.of(DemonicSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).ridingOffset(-0.7F).passengerAttachments(2.0125F).build(entityId("demonic_skeleton")));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonicSoldier>> DEMONIC_SOLDIER = ENTITY_TYPES.register("demonic_soldier", () -> EntityType.Builder.of(DemonicSoldier::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(entityId("demonic_soldier")));

	//Projectiles
	public static final DeferredHolder<EntityType<?>, EntityType<MushroomSpore>> MUSHROOM_SPORE = ENTITY_TYPES.register("mushroom_spore", () -> EntityType.Builder.<MushroomSpore>of(MushroomSpore::new, MobCategory.MISC).sized(0.2F, 0.2F).build(entityId("mushroom_spore")));
	public static final DeferredHolder<EntityType<?>, EntityType<RevivingSoul>> REVIVING_SOUL = ENTITY_TYPES.register("reviving_soul", () -> EntityType.Builder.<RevivingSoul>of(RevivingSoul::new, MobCategory.MISC).sized(0.2F, 0.2F).build(entityId("reviving_soul")));

	//Misc
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> PANGO_BOAT = ENTITY_TYPES.register("pango_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.PANGO_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("pango_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> PANGO_CHEST_BOAT = ENTITY_TYPES.register("pango_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.PANGO_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("pango_chest_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> RUGONA_BOAT = ENTITY_TYPES.register("rugona_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.RUGONA_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("rugona_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> RUGONA_CHEST_BOAT = ENTITY_TYPES.register("rugona_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.RUGONA_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("rugona_chest_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> VARESO_BOAT = ENTITY_TYPES.register("vareso_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.VARESO_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("vareso_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> VARESO_CHEST_BOAT = ENTITY_TYPES.register("vareso_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.VARESO_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("vareso_chest_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> CANDY_BOAT = ENTITY_TYPES.register("candy_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.CANDY_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("candy_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> CANDY_CHEST_BOAT = ENTITY_TYPES.register("candy_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.CANDY_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("candy_chest_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> CHOCOLATE_OAK_BOAT = ENTITY_TYPES.register("chocolate_oak_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.CHOCOLATE_OAK_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("chocolate_oak_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> CHOCOLATE_OAK_CHEST_BOAT = ENTITY_TYPES.register("chocolate_oak_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.CHOCOLATE_OAK_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("chocolate_oak_chest_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> ELVEN_BOAT = ENTITY_TYPES.register("elven_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.ELVEN_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("elven_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> ELVEN_CHEST_BOAT = ENTITY_TYPES.register("elven_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.ELVEN_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("elven_chest_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> BLUE_ELVEN_BOAT = ENTITY_TYPES.register("blue_elven_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.BLUE_ELVEN_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("blue_elven_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> BLUE_ELVEN_CHEST_BOAT = ENTITY_TYPES.register("blue_elven_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.BLUE_ELVEN_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("blue_elven_chest_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> CYAN_ELVEN_BOAT = ENTITY_TYPES.register("cyan_elven_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.CYAN_ELVEN_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("cyan_elven_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> CYAN_ELVEN_CHEST_BOAT = ENTITY_TYPES.register("cyan_elven_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.CYAN_ELVEN_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("cyan_elven_chest_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> GREEN_ELVEN_BOAT = ENTITY_TYPES.register("green_elven_boat", () -> EntityType.Builder.of(boatFactory(KoratioItems.GREEN_ELVEN_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("green_elven_boat")));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> GREEN_ELVEN_CHEST_BOAT = ENTITY_TYPES.register("green_elven_chest_boat", () -> EntityType.Builder.of(chestBoatFactory(KoratioItems.GREEN_ELVEN_CHEST_BOAT::get), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(entityId("green_elven_chest_boat")));

	public static final DeferredHolder<EntityType<?>, EntityType<LevitatingBlockEntity>> LEVITATING_BLOCK = ENTITY_TYPES.register("levitating_block", () -> EntityType.Builder.of(LevitatingBlockEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).build(entityId("levitating_block")));
	
	@SubscribeEvent
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(JUMSTEM.get(), SpawnPlacementTypes.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

		event.register(MAGICAL_CAT.get(), SpawnPlacementTypes.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(MOOSHROOM.get(), SpawnPlacementTypes.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Mooshroom::checkMushroomSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SPIKY_PIG.get(), SpawnPlacementTypes.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}
	
	@SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(MAGICAL_CAT.get(), MagicalCat.createAttributes().build());
		event.put(FLUFFER.get(), Fluffer.createAttributes().build());
		event.put(MOOSHROOM.get(), Cow.createAttributes().build());
		event.put(SPIKY_PIG.get(), SpikyPig.createAttributes().build());
		event.put(GOLDEN_FOX_SPIRIT.get(), GoldenFoxSpirit.createAttributes().build());
		event.put(NECROMANCER.get(), Necromancer.createAttributes().build());
		event.put(NECROMANCER_SKULL.get(), NecromancerSkull.createAttributes().build());
		event.put(DEMONIC_ZOMBIE_HORSE.get(), DemonicZombieHorse.createAttributes().build());
		event.put(DEMONIC_ZOMBIE.get(), Zombie.createAttributes().build());
		event.put(DEMONIC_SKELETON_HORSE.get(), DemonicSkeletonHorse.createAttributes().build());
		event.put(DEMONIC_SKELETON.get(), Skeleton.createAttributes().build());
		event.put(DEMONIC_SOLDIER.get(), DemonicSoldier.createAttributes().build());
		event.put(JUMSTEM.get(), JumStem.createMonsterAttributes().build());
		event.put(CRYSTALLIZE.get(), Crystallize.createAttributes().build());
		event.put(GINGERBREAD_MAN.get(), GingerbreadMan.createMonsterAttributes().build());
	}

	private static ResourceKey<EntityType<?>> entityId(String name) {
		return ResourceKey.create(Registries.ENTITY_TYPE, Koratio.prefix(name));
	}

	private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> item) {
		return (boat, level) -> new Boat(boat, level, item);
	}

	private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Supplier<Item> item) {
		return (boat, level) -> new ChestBoat(boat, level, item);
	}
}