package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.entity.animal.Fluffer;
import net.setrion.koratio.world.entity.animal.GoldenFoxSpirit;
import net.setrion.koratio.world.entity.animal.MagicalCat;
import net.setrion.koratio.world.entity.animal.SpikyPig;
import net.setrion.koratio.world.entity.demonic.*;
import net.setrion.koratio.world.entity.item.LevitatingBlockEntity;
import net.setrion.koratio.world.entity.monster.Crystallize;
import net.setrion.koratio.world.entity.monster.GingerbreadMan;
import net.setrion.koratio.world.entity.monster.JumStem;
import net.setrion.koratio.world.entity.projectile.MushroomSpore;
import net.setrion.koratio.world.entity.vehicle.Boat;
import net.setrion.koratio.world.entity.vehicle.ChestBoat;

@EventBusSubscriber(modid = Koratio.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class KoratioEntityType {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Koratio.MOD_ID);

	public static final DeferredHolder<EntityType<?>, EntityType<JumStem>> JUMSTEM = ENTITY_TYPES.register("jumstem", () -> EntityType.Builder.<JumStem>of(JumStem::new, MobCategory.MONSTER).sized(0.5F, 1.3F).build("jumstem"));

	public static final DeferredHolder<EntityType<?>, EntityType<GingerbreadMan>> GINGERBREAD_MAN = ENTITY_TYPES.register("gingerbread_man", () -> EntityType.Builder.<GingerbreadMan>of(GingerbreadMan::new, MobCategory.MONSTER).sized(0.5F, 1.9F).build("gingerbread_man"));
	
	public static final DeferredHolder<EntityType<?>, EntityType<Crystallize>> CRYSTALLIZE = ENTITY_TYPES.register("crystallize", () -> EntityType.Builder.<Crystallize>of(Crystallize::new, MobCategory.MONSTER).sized(0.5F, 2.0F).build("crystallize"));

	public static final DeferredHolder<EntityType<?>, EntityType<MushroomSpore>> MUSHROOM_SPORE = ENTITY_TYPES.register("mushroom_spore", () -> EntityType.Builder.<MushroomSpore>of(MushroomSpore::new, MobCategory.MISC).sized(0.2F, 0.2F).build("mushroom_spore"));
	
	public static final DeferredHolder<EntityType<?>, EntityType<MagicalCat>> MAGICAL_CAT = ENTITY_TYPES.register("magical_cat", () -> EntityType.Builder.<MagicalCat>of(MagicalCat::new, MobCategory.CREATURE).sized(0.75F, 0.5F).build("magical_cat"));
	public static final DeferredHolder<EntityType<?>, EntityType<Fluffer>> FLUFFER = ENTITY_TYPES.register("fluffer", () -> EntityType.Builder.<Fluffer>of(Fluffer::new, MobCategory.CREATURE).sized(0.85F, 0.85F).build("fluffer"));
	public static final DeferredHolder<EntityType<?>, EntityType<SpikyPig>> SPIKY_PIG = ENTITY_TYPES.register("spiky_pig", () -> EntityType.Builder.<SpikyPig>of(SpikyPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).build("spiky_pig"));
	public static final DeferredHolder<EntityType<?>, EntityType<GoldenFoxSpirit>> GOLDEN_FOX_SPIRIT = ENTITY_TYPES.register("golden_fox_spirit", () -> EntityType.Builder.<GoldenFoxSpirit>of(GoldenFoxSpirit::new, MobCategory.CREATURE).sized(0.5F, 1.6F).build("golden_fox_spirit"));
	
	//Demons
	//public static final DeferredHolder<EntityType<?>, EntityType<Necromancer>> NECROMANCER = ENTITY_TYPES.register("necromancer", () -> EntityType.Builder.<Necromancer>of(Necromancer::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("necromancer"));
	//public static final DeferredHolder<EntityType<?>, EntityType<NecromancerSkull>> NECROMANCER_SKULL = ENTITY_TYPES.register("necromancer_skull", () -> EntityType.Builder.<NecromancerSkull>of(NecromancerSkull::new, MobCategory.MONSTER).sized(0.5F, 0.5F).clientTrackingRange(8).build("necromancer_skull"));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonicZombie>> DEMONIC_ZOMBIE = ENTITY_TYPES.register("demonic_zombie", () -> EntityType.Builder.<DemonicZombie>of(DemonicZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("demonic_zombie"));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonicSkeleton>> DEMONIC_SKELETON = ENTITY_TYPES.register("demonic_skeleton", () -> EntityType.Builder.<DemonicSkeleton>of(DemonicSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("demonic_skeleton"));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonicSoldier>> DEMONIC_SOLDIER = ENTITY_TYPES.register("demonic_soldier", () -> EntityType.Builder.<DemonicSoldier>of(DemonicSoldier::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("demonic_soldier"));
	
	//Misc
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> BOAT = ENTITY_TYPES.register("boat", () -> EntityType.Builder.<Boat>of(Boat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> CHEST_BOAT = ENTITY_TYPES.register("chest_boat", () -> EntityType.Builder.<ChestBoat>of(ChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("chest_boat"));
	public static final DeferredHolder<EntityType<?>, EntityType<LevitatingBlockEntity>> LEVITATING_BLOCK = ENTITY_TYPES.register("levitating_block", () -> EntityType.Builder.<LevitatingBlockEntity>of(LevitatingBlockEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).build("levitating_block"));
	
	@SubscribeEvent
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(JUMSTEM.get(), SpawnPlacementTypes.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

		event.register(MAGICAL_CAT.get(), SpawnPlacementTypes.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SPIKY_PIG.get(), SpawnPlacementTypes.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}
	
	@SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(KoratioEntityType.MAGICAL_CAT.get(), MagicalCat.createAttributes().build());
		event.put(KoratioEntityType.FLUFFER.get(), Fluffer.createAttributes().build());
		event.put(KoratioEntityType.SPIKY_PIG.get(), SpikyPig.createAttributes().build());
		event.put(KoratioEntityType.GOLDEN_FOX_SPIRIT.get(), GoldenFoxSpirit.createAttributes().build());
		event.put(KoratioEntityType.DEMONIC_ZOMBIE.get(), Zombie.createAttributes().build());
		event.put(KoratioEntityType.DEMONIC_SKELETON.get(), Skeleton.createAttributes().build());
		event.put(KoratioEntityType.DEMONIC_SOLDIER.get(), DemonicSoldier.createAttributes().build());
		event.put(KoratioEntityType.JUMSTEM.get(), JumStem.createMonsterAttributes().build());
		event.put(KoratioEntityType.CRYSTALLIZE.get(), Crystallize.createAttributes().build());
		event.put(KoratioEntityType.GINGERBREAD_MAN.get(), GingerbreadMan.createMonsterAttributes().build());
	}
}