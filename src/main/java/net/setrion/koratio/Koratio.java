package net.setrion.koratio;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.setrion.koratio.client.gui.screens.inventory.CandyShaperScreen;
import net.setrion.koratio.client.gui.screens.inventory.DecryptingScreen;
import net.setrion.koratio.client.gui.screens.inventory.WoodcutterScreen;
import net.setrion.koratio.core.cauldron.CauldronInteraction;
import net.setrion.koratio.core.cauldron.EmptyCauldronInteraction;
import net.setrion.koratio.data.compat.KoratioCuriosCompat;
import net.setrion.koratio.events.RegistryEvents;
import net.setrion.koratio.registry.*;
import net.setrion.koratio.world.item.PipingBagItem;
import org.jetbrains.annotations.Nullable;

@Mod(Koratio.MOD_ID)
public class Koratio {
	
    public static final String MOD_ID = "koratio";

    public Koratio(IEventBus bus) {
        NeoForgeMod.enableMilkFluid();
        RegistryEvents.registerEverything(bus);

        bus.addListener(this::setupEntityModelLayers);
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientScreenSetup);
        bus.addListener(this::addBlockEntityTypes);
        
        KoratioBlocks.registerWoodTypes();

        if (ModList.get().isLoaded("curios")) {
            bus.addListener(KoratioCuriosCompat::registerCuriosCapabilities);
        }
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CauldronInteraction.MOLTEN_SUGAR.map().put(Items.BUCKET, EmptyCauldronInteraction.MOLTEN_SUGAR);
            net.minecraft.core.cauldron.CauldronInteraction.EMPTY.map().put(KoratioItems.MOLTEN_SUGAR_BUCKET.get(), CauldronInteraction.EMPTY);
            CauldronInteraction.MOLTEN_BLUE_SUGAR.map().put(Items.BUCKET, EmptyCauldronInteraction.MOLTEN_BLUE_SUGAR);
            net.minecraft.core.cauldron.CauldronInteraction.EMPTY.map().put(KoratioItems.MOLTEN_BLUE_SUGAR_BUCKET.get(), CauldronInteraction.EMPTY);
            CauldronInteraction.MOLTEN_GREEN_SUGAR.map().put(Items.BUCKET, EmptyCauldronInteraction.MOLTEN_GREEN_SUGAR);
            net.minecraft.core.cauldron.CauldronInteraction.EMPTY.map().put(KoratioItems.MOLTEN_GREEN_SUGAR_BUCKET.get(), CauldronInteraction.EMPTY);
            CauldronInteraction.MOLTEN_YELLOW_SUGAR.map().put(Items.BUCKET, EmptyCauldronInteraction.MOLTEN_YELLOW_SUGAR);
            net.minecraft.core.cauldron.CauldronInteraction.EMPTY.map().put(KoratioItems.MOLTEN_YELLOW_SUGAR_BUCKET.get(), CauldronInteraction.EMPTY);
            CauldronInteraction.MOLTEN_RED_SUGAR.map().put(Items.BUCKET, EmptyCauldronInteraction.MOLTEN_RED_SUGAR);
            net.minecraft.core.cauldron.CauldronInteraction.EMPTY.map().put(KoratioItems.MOLTEN_RED_SUGAR_BUCKET.get(), CauldronInteraction.EMPTY);
        });
        KoratioBlocks.registerFlammables();
    	KoratioBlocks.registerPots();
    	KoratioBlocks.registerStrippables();
        KoratioVillagerTypes.register();
    }

    public void clientScreenSetup(RegisterMenuScreensEvent event) {
        event.register(KoratioMenuTypes.DECRYPTING.get(), DecryptingScreen::new);
        event.register(KoratioMenuTypes.CANDY_SHAPER.get(), CandyShaperScreen::new);
        event.register(KoratioMenuTypes.WOODCUTTER.get(), WoodcutterScreen::new);
    }

	public void setupEntityModelLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModelLayers.register(event);
    }

    public void addBlockEntityTypes(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
                KoratioBlocks.PANGO_SIGN.get(), KoratioBlocks.PANGO_WALL_SIGN.get(),
                KoratioBlocks.RUGONA_SIGN.get(), KoratioBlocks.RUGONA_WALL_SIGN.get(),
                KoratioBlocks.ELVEN_SIGN.get(), KoratioBlocks.ELVEN_WALL_SIGN.get(),
                KoratioBlocks.BLUE_ELVEN_SIGN.get(), KoratioBlocks.BLUE_ELVEN_WALL_SIGN.get(),
                KoratioBlocks.CYAN_ELVEN_SIGN.get(), KoratioBlocks.CYAN_ELVEN_WALL_SIGN.get(),
                KoratioBlocks.GREEN_ELVEN_SIGN.get(), KoratioBlocks.GREEN_ELVEN_WALL_SIGN.get(),
                KoratioBlocks.VARESO_SIGN.get(), KoratioBlocks.VARESO_WALL_SIGN.get(),
                KoratioBlocks.CANDY_SIGN.get(), KoratioBlocks.CANDY_WALL_SIGN.get()
        );

        event.modify(BlockEntityType.HANGING_SIGN,
                KoratioBlocks.PANGO_HANGING_SIGN.get(), KoratioBlocks.PANGO_WALL_HANGING_SIGN.get(),
                KoratioBlocks.RUGONA_HANGING_SIGN.get(), KoratioBlocks.RUGONA_WALL_HANGING_SIGN.get(),
                KoratioBlocks.ELVEN_HANGING_SIGN.get(), KoratioBlocks.ELVEN_WALL_HANGING_SIGN.get(),
                KoratioBlocks.BLUE_ELVEN_HANGING_SIGN.get(), KoratioBlocks.BLUE_ELVEN_WALL_HANGING_SIGN.get(),
                KoratioBlocks.CYAN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.CYAN_ELVEN_WALL_HANGING_SIGN.get(),
                KoratioBlocks.GREEN_ELVEN_HANGING_SIGN.get(), KoratioBlocks.GREEN_ELVEN_WALL_HANGING_SIGN.get(),
                KoratioBlocks.VARESO_HANGING_SIGN.get(), KoratioBlocks.VARESO_WALL_HANGING_SIGN.get(),
                KoratioBlocks.CANDY_HANGING_SIGN.get(), KoratioBlocks.CANDY_WALL_HANGING_SIGN.get()
        );

        event.modify(BlockEntityType.FURNACE, KoratioBlocks.ANCIENT_FURNACE.get());
    }
	
	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
	}
}