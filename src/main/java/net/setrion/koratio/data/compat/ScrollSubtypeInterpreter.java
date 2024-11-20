package net.setrion.koratio.data.compat;

/*import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.registry.KoratioDataComponents;
import org.jetbrains.annotations.Nullable;

public class ScrollSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final ScrollSubtypeInterpreter INSTANCE = new ScrollSubtypeInterpreter();

    private ScrollSubtypeInterpreter() {

    }

    @Override
    @Nullable
    public Object getSubtypeData(ItemStack ingredient, UidContext context) {
        KoratioDataComponents.ScrollRecord contents = ingredient.get(KoratioDataComponents.SCROLL_DATA);
        return contents;
    }

    @Override
    public String getLegacyStringSubtypeInfo(ItemStack ingredient, UidContext context) {
        return getStringName(ingredient);
    }

    public String getStringName(ItemStack itemStack) {
        return itemStack.getItem().getDescriptionId();
    }
}*/