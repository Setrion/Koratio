package net.setrion.koratio.registry;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.inventory.DecryptingMenu;

public class KoratioMenuTypes {
	
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Koratio.MOD_ID);
	
	public static final RegistryObject<MenuType<DecryptingMenu>> DECRYPTING = MENU_TYPES.register("decrypting", () -> new MenuType<>(DecryptingMenu::new, FeatureFlags.DEFAULT_FLAGS));

}