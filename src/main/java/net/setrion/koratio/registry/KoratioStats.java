package net.setrion.koratio.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.koratio.Koratio;

import java.util.ArrayList;
import java.util.List;

public class KoratioStats {

    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(Registries.CUSTOM_STAT, Koratio.MOD_ID);
    private static final List<Runnable> STAT_SETUP = new ArrayList<>();

    public static final DeferredHolder<ResourceLocation, ResourceLocation> INTERACT_WITH_DECRYPTING_TABLE = makeCustomStat("interact_with_decrypting_table");
    public static final DeferredHolder<ResourceLocation, ResourceLocation> DECRYPTED_SCROLLS = makeCustomStat("decrypted_scrolls");
    public static final DeferredHolder<ResourceLocation, ResourceLocation> INTERACT_WITH_WOODCUTTER = makeCustomStat("interact_with_woodcutter");
    public static final DeferredHolder<ResourceLocation, ResourceLocation> INTERACT_WITH_CANDY_SHAPER = makeCustomStat("interact_with_candy_shaper");
    public static final DeferredHolder<ResourceLocation, ResourceLocation> CANDYS_MADE = makeCustomStat("candys_made");
    public static final DeferredHolder<ResourceLocation, ResourceLocation> EAT_BLOCK_SLICE = makeCustomStat("eat_block_slice");

    private static DeferredHolder<ResourceLocation, ResourceLocation> makeCustomStat(String key) {
        ResourceLocation resourcelocation = Koratio.prefix(key);
        STAT_SETUP.add(() -> Stats.CUSTOM.get(resourcelocation, StatFormatter.DEFAULT));
        return STATS.register(key, () -> resourcelocation);
    }

    public static void init() {
        STAT_SETUP.forEach(Runnable::run);
    }
}