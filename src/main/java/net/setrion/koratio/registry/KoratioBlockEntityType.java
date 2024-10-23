package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.block.entity.*;

public class KoratioBlockEntityType {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Koratio.MOD_ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CampfireBlockEntity>> CAMPFIRE = BLOCK_ENTITY_TYPES.register("campfire", () -> BlockEntityType.Builder.of(CampfireBlockEntity::new,
			KoratioBlocks.RAINBOW_CAMPFIRE.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CandyShaperBlockEntity>> CANDY_SHAPER = BLOCK_ENTITY_TYPES.register("candy_shaper", () -> BlockEntityType.Builder.of(CandyShaperBlockEntity::new,
			KoratioBlocks.CANDY_SHAPER.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChestBlockEntity>> CHEST = BLOCK_ENTITY_TYPES.register("chest", () -> BlockEntityType.Builder.of(ChestBlockEntity::new,
			KoratioBlocks.PANGO_CHEST.get(), KoratioBlocks.RUGONA_CHEST.get(), KoratioBlocks.VARESO_CHEST.get(), KoratioBlocks.CANDY_CHEST.get(), KoratioBlocks.CHOCOLATE_OAK_CHEST.get(), KoratioBlocks.ELVEN_CHEST.get(), KoratioBlocks.BLUE_ELVEN_CHEST.get(), KoratioBlocks.CYAN_ELVEN_CHEST.get(), KoratioBlocks.GREEN_ELVEN_CHEST.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrappedChestBlockEntity>> TRAPPED_CHEST = BLOCK_ENTITY_TYPES.register("trapped_chest", () -> BlockEntityType.Builder.of(TrappedChestBlockEntity::new,
			KoratioBlocks.TRAPPED_PANGO_CHEST.get(), KoratioBlocks.TRAPPED_RUGONA_CHEST.get(), KoratioBlocks.TRAPPED_VARESO_CHEST.get(), KoratioBlocks.TRAPPED_CANDY_CHEST.get(), KoratioBlocks.TRAPPED_CHOCOLATE_OAK_CHEST.get(), KoratioBlocks.TRAPPED_ELVEN_CHEST.get(), KoratioBlocks.TRAPPED_BLUE_ELVEN_CHEST.get(), KoratioBlocks.TRAPPED_CYAN_ELVEN_CHEST.get(), KoratioBlocks.TRAPPED_GREEN_ELVEN_CHEST.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GlazedBlockEntity>> GLAZED_BLOCK = BLOCK_ENTITY_TYPES.register("glazed_block", () -> BlockEntityType.Builder.of(GlazedBlockEntity::new,
			KoratioBlocks.RAW_GINGERBREAD_BLOCK.get(), KoratioBlocks.GINGERBREAD_BLOCK.get(), KoratioBlocks.RAW_GINGERBREAD_BRICKS.get(), KoratioBlocks.GINGERBREAD_BRICKS.get(), KoratioBlocks.RAW_LARGE_GINGERBREAD_BRICKS.get(), KoratioBlocks.LARGE_GINGERBREAD_BRICKS.get()).build(null));
}