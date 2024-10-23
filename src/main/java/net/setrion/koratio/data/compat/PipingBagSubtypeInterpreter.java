package net.setrion.koratio.data.compat;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import net.setrion.koratio.registry.KoratioDataComponents;
import org.jetbrains.annotations.Nullable;

public class PipingBagSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final PipingBagSubtypeInterpreter INSTANCE = new PipingBagSubtypeInterpreter();

    private PipingBagSubtypeInterpreter() {

    }

    @Override
    @Nullable
    public Object getSubtypeData(ItemStack ingredient, UidContext context) {
        KoratioDataComponents.PipingBagRecord contents = ingredient.get(KoratioDataComponents.PIPING_BAG_DATA);
        return contents;
    }

    @Override
    public String getLegacyStringSubtypeInfo(ItemStack ingredient, UidContext context) {
        return getStringName(ingredient);
    }

    public String getStringName(ItemStack itemStack) {
        return itemStack.getItem().getDescriptionId();
    }
}