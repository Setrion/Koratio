package net.setrion.koratio.registry;

import net.minecraft.core.Holder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.entity.animal.MagicalCatVariant;

public class KoratioDataSerializers {

    public static final DeferredRegister<EntityDataSerializer<?>> DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, Koratio.MOD_ID);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<MagicalCatVariant>>> MAGICAL_CAT_VARIANT = DATA_SERIALIZERS.register("magical_cat_variant", () -> EntityDataSerializer.forValueType(ByteBufCodecs.holderRegistry(KoratioRegistries.MAGICAL_CAT_VARIANT)));

}