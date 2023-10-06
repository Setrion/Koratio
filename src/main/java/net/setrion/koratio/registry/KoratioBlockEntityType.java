package net.setrion.koratio.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.block.entity.CampfireBlockEntity;
import net.setrion.koratio.world.level.block.entity.ChestBlockEntity;
import net.setrion.koratio.world.level.block.entity.HangingSignBlockEntity;
import net.setrion.koratio.world.level.block.entity.SignBlockEntity;

public class KoratioBlockEntityType {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Koratio.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<SignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(SignBlockEntity::new, 
			KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_WALL_SIGN.get(), KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get(), KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get(), KoratioBlocks.NIGHY_SIGN.get(), KoratioBlocks.NIGHY_WALL_SIGN.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<HangingSignBlockEntity>> HANGING_SIGN = BLOCK_ENTITY_TYPES.register("hanging_sign", () -> BlockEntityType.Builder.of(HangingSignBlockEntity::new, 
			KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(), KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(), KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(), KoratioBlocks.NIGHY_HANGING_SIGN.get(), KoratioBlocks.NIGHY_WALL_HANGING_SIGN.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<ChestBlockEntity>> CHEST = BLOCK_ENTITY_TYPES.register("chest", () -> BlockEntityType.Builder.of(ChestBlockEntity::new,
			KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.NIGHY_CHEST.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<CampfireBlockEntity>> CAMPFIRE = BLOCK_ENTITY_TYPES.register("campfire", () -> BlockEntityType.Builder.of(CampfireBlockEntity::new,
			KoratioBlocks.AMETHYST_CAMPFIRE.get(), KoratioBlocks.EMERALD_CAMPFIRE.get()).build(null));

}