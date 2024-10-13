package net.setrion.koratio.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.inventory.CandyShaperMenu;
import net.setrion.koratio.world.inventory.DecryptingMenu;
import net.setrion.koratio.world.inventory.WoodcutterMenu;

public class KoratioMenuTypes {
	
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, Koratio.MOD_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<DecryptingMenu>> DECRYPTING = MENU_TYPES.register("decrypting", () -> new MenuType<>(DecryptingMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final DeferredHolder<MenuType<?>, MenuType<CandyShaperMenu>> CANDY_SHAPER = MENU_TYPES.register("candy_shaper", () -> IMenuTypeExtension.create(CandyShaperMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<WoodcutterMenu>> WOODCUTTER = MENU_TYPES.register("woodcutter", () -> new MenuType<>(WoodcutterMenu::new, FeatureFlags.DEFAULT_FLAGS));

}