package net.setrion.koratio.data.compat;

/*import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioItems;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;

public class KoratioCuriosCompat {

    public static void registerCuriosCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(CuriosCapability.ITEM, (stack, context) -> new ICurio() {
                    @Override
                    public ItemStack getStack() {
                        return stack;
                    }

                    @Nonnull
                    @Override
                    public SoundInfo getEquipSound(SlotContext slotContext) {
                        return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC.value(), 1.0F, 1.0F);
                    }

                    @Override
                    public boolean canEquipFromUse(SlotContext slotContext) {
                        return true;
                    }

                    @Override
                    public void curioTick(SlotContext slotContext) {
                        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20, 0, false, false, false));
                    }
                },
                KoratioItems.RAINBOW_GEM
        );
    }

    public static class KoratioCuriosProvider extends CuriosDataProvider {

        public KoratioCuriosProvider(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
            super(Koratio.MOD_ID, output, fileHelper, registries);
        }

        @Override
        public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
            createEntities("player")
                    .addPlayer()
                    .addSlots("ring", "necklace", "charm");
        }
    }
}*/