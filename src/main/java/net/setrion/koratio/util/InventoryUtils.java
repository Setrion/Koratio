package net.setrion.koratio.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {

    public static List<Integer> getSlotsForItem(ItemStack stack, Inventory inventory) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < inventory.items.size(); ++i) {
            if (!inventory.items.get(i).isEmpty() && ItemStack.isSameItemSameComponents(stack, inventory.items.get(i))) {
                list.add(i);
            }
        }

        return list;
    }
}