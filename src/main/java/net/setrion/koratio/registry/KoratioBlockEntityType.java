package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.block.entity.*;

public class KoratioBlockEntityType {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Koratio.MOD_ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CampfireBlockEntity>> CAMPFIRE = BLOCK_ENTITY_TYPES.register("campfire", () -> new BlockEntityType<>(CampfireBlockEntity::new,
			KoratioBlocks.RAINBOW_CAMPFIRE.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RemainsBlockEntity>> REMAINS = BLOCK_ENTITY_TYPES.register("remains", () -> new BlockEntityType<>(RemainsBlockEntity::new,
			KoratioBlocks.SKELETON_REMAINS.get(), KoratioBlocks.WITHER_SKELETON_REMAINS.get(), KoratioBlocks.STRAY_REMAINS.get(), KoratioBlocks.BOGGED_REMAINS.get(), KoratioBlocks.DEMONIC_SKELETON_REMAINS.get(), KoratioBlocks.ZOMBIE_REMAINS.get(), KoratioBlocks.HUSK_REMAINS.get(), KoratioBlocks.DROWNED_REMAINS.get(), KoratioBlocks.DEMONIC_ZOMBIE_REMAINS.get(), KoratioBlocks.ZOMBIE_VILLAGER_REMAINS.get(), KoratioBlocks.PHANTOM_REMAINS.get(), KoratioBlocks.ZOMBIFIED_PIGLIN_REMAINS.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CandyShaperBlockEntity>> CANDY_SHAPER = BLOCK_ENTITY_TYPES.register("candy_shaper", () -> new BlockEntityType<>(CandyShaperBlockEntity::new,
			KoratioBlocks.CANDY_SHAPER.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChestBlockEntity>> CHEST = BLOCK_ENTITY_TYPES.register("chest", () -> new BlockEntityType<>(ChestBlockEntity::new,
			KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.CANDY_CHEST.get(), KoratioBlocks.CHOCOLATE_OAK_CHEST.get(), KoratioBlocks.ELVEN_CHEST.get(), KoratioBlocks.BLUE_ELVEN_CHEST.get(), KoratioBlocks.CYAN_ELVEN_CHEST.get(), KoratioBlocks.GREEN_ELVEN_CHEST.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrappedChestBlockEntity>> TRAPPED_CHEST = BLOCK_ENTITY_TYPES.register("trapped_chest", () -> new BlockEntityType<>(TrappedChestBlockEntity::new,
			KoratioBlocks.TRAPPED_PANGO_CHEST.get(), KoratioBlocks.TRAPPED_RUGONA_CHEST.get(), KoratioBlocks.TRAPPED_VARESO_CHEST.get(), KoratioBlocks.TRAPPED_CANDY_CHEST.get(), KoratioBlocks.TRAPPED_CHOCOLATE_OAK_CHEST.get(), KoratioBlocks.TRAPPED_ELVEN_CHEST.get(), KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get(), KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get(), KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GlazedBlockEntity>> GLAZED_BLOCK = BLOCK_ENTITY_TYPES.register("glazed_block", () -> new BlockEntityType<>(GlazedBlockEntity::new,
			KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(), KoratioBlocks.GINGERBREAD_BLOCK.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(), KoratioBlocks.GINGERBREAD_BRICKS.get(), KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get(), KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get()));
}