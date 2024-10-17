package net.setrion.koratio.world.entity.animal;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioLootTables;
import net.setrion.koratio.registry.KoratioTags;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Fluffer extends Animal implements Shearable {
    private static final EntityDataAccessor<Byte> DATA_WOOL_ID;
    private static final Map<DyeColor, ItemLike> ITEM_BY_DYE;
    private static final Map<DyeColor, Integer> COLOR_BY_DYE;

    private static int createWoolColor(DyeColor dyeColor) {
        if (dyeColor == DyeColor.WHITE) {
            return -1644826;
        } else {
            int i = dyeColor.getTextureDiffuseColor();
            float f = 0.75F;
            return FastColor.ARGB32.color(255, Mth.floor((float) FastColor.ARGB32.red(i) * 0.75F), Mth.floor((float) FastColor.ARGB32.green(i) * 0.75F), Mth.floor((float) FastColor.ARGB32.blue(i) * 0.75F));
        }
    }

    public static int getColor(DyeColor dyeColor) {
        return COLOR_BY_DYE.get(dyeColor);
    }

    public Fluffer(EntityType<? extends Fluffer> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(KoratioTags.Items.FLUFFER_FOOD);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        Vec3 vec3 = getDeltaMovement();
        if (!onGround() && vec3.y < 0.0) {
            setDeltaMovement(vec3.multiply(1.0, 0.6, 1.0));
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0).add(Attributes.MOVEMENT_SPEED, 0.23F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_WOOL_ID, (byte)0);
    }

    @Override
    public ResourceKey<LootTable> getDefaultLootTable() {
        if (isSheared()) {
            return getType().getDefaultLootTable();
        } else {
            return switch (getColor()) {
                case WHITE -> KoratioLootTables.FLUFFER_WHITE;
                case ORANGE -> KoratioLootTables.FLUFFER_ORANGE;
                case MAGENTA -> KoratioLootTables.FLUFFER_MAGENTA;
                case LIGHT_BLUE -> KoratioLootTables.FLUFFER_LIGHT_BLUE;
                case YELLOW -> KoratioLootTables.FLUFFER_YELLOW;
                case LIME -> KoratioLootTables.FLUFFER_LIME;
                case PINK -> KoratioLootTables.FLUFFER_PINK;
                case GRAY -> KoratioLootTables.FLUFFER_GRAY;
                case LIGHT_GRAY -> KoratioLootTables.FLUFFER_LIGHT_GRAY;
                case CYAN -> KoratioLootTables.FLUFFER_CYAN;
                case PURPLE -> KoratioLootTables.FLUFFER_PURPLE;
                case BLUE -> KoratioLootTables.FLUFFER_BLUE;
                case BROWN -> KoratioLootTables.FLUFFER_BROWN;
                case GREEN -> KoratioLootTables.FLUFFER_GREEN;
                case RED -> KoratioLootTables.FLUFFER_RED;
                case BLACK -> KoratioLootTables.FLUFFER_BLACK;
            };
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() instanceof DyeItem dye) {
            if (isAlive() && !isSheared() && getColor() != dye.getDyeColor()) {
                level().playSound(player, this, SoundEvents.DYE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!player.level().isClientSide) {
                    setColor(dye.getDyeColor());
                    itemstack.shrink(1);
                }

                return InteractionResult.sidedSuccess(player.level().isClientSide);
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void shear(SoundSource category) {
        level().playSound(null, this, SoundEvents.SHEEP_SHEAR, category, 1.0F, 1.0F);
        setSheared(true);
        int i = 1 + random.nextInt(3);

        for (int j = 0; j < i; j++) {
            ItemEntity itementity = spawnAtLocation(ITEM_BY_DYE.get(getColor()), 1);
            if (itementity != null) {
                itementity.setDeltaMovement(
                        itementity.getDeltaMovement()
                                .add(
                                        (random.nextFloat() - random.nextFloat()) * 0.1F,
                                        random.nextFloat() * 0.05F,
                                        (random.nextFloat() - random.nextFloat()) * 0.1F
                                )
                );
            }
        }
    }

    @Override
    public boolean readyForShearing() {
        return isAlive() && !isSheared() && !isBaby();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Sheared", isSheared());
        compound.putByte("Color", (byte)getColor().getId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setSheared(compound.getBoolean("Sheared"));
        setColor(DyeColor.byId(compound.getByte("Color")));
    }

    public DyeColor getColor() {
        return DyeColor.byId(entityData.get(DATA_WOOL_ID) & 15);
    }

    public void setColor(DyeColor dyeColor) {
        byte b0 = entityData.get(DATA_WOOL_ID);
        entityData.set(DATA_WOOL_ID, (byte)(b0 & 240 | dyeColor.getId() & 15));
    }

    public boolean isSheared() {
        return (entityData.get(DATA_WOOL_ID) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b0 = entityData.get(DATA_WOOL_ID);
        if (sheared) {
            entityData.set(DATA_WOOL_ID, (byte)(b0 | 16));
        } else {
            entityData.set(DATA_WOOL_ID, (byte)(b0 & -17));
        }
    }

    public static DyeColor getRandomFlufferCollor(RandomSource random) {
        int i = random.nextInt(100);
        if (i < 5) {
            return DyeColor.BLACK;
        } else if (i < 10) {
            return DyeColor.GRAY;
        } else if (i < 15) {
            return DyeColor.LIGHT_GRAY;
        } else if (i < 18) {
            return DyeColor.BROWN;
        } else {
            return random.nextInt(500) == 0 ? DyeColor.PINK : DyeColor.WHITE;
        }
    }

    @Nullable
    public Fluffer getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Fluffer fluffer = KoratioEntityType.FLUFFER.get().create(level);
        if (fluffer != null) {
            fluffer.setColor(getOffspringColor(this, (Sheep)otherParent));
        }

        return fluffer;
    }

    @Override
    public void ate() {
        super.ate();
        setSheared(false);
        if (isBaby()) {
            ageUp(60);
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        setColor(getRandomFlufferCollor(level.getRandom()));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    private DyeColor getOffspringColor(Animal father, Animal mother) {
        DyeColor dyecolor = ((Fluffer)father).getColor();
        DyeColor dyecolor1 = ((Fluffer)mother).getColor();
        CraftingInput craftinginput = makeCraftInput(dyecolor, dyecolor1);
        return level()
                .getRecipeManager()
                .getRecipeFor(RecipeType.CRAFTING, craftinginput, level())
                .map(recipe -> recipe.value().assemble(craftinginput, level().registryAccess()))
                .map(ItemStack::getItem)
                .filter(DyeItem.class::isInstance)
                .map(DyeItem.class::cast)
                .map(DyeItem::getDyeColor)
                .orElseGet(() -> level().random.nextBoolean() ? dyecolor : dyecolor1);
    }

    private static CraftingInput makeCraftInput(DyeColor color1, DyeColor color2) {
        return CraftingInput.of(2, 1, List.of(new ItemStack(DyeItem.byColor(color1)), new ItemStack(DyeItem.byColor(color2))));
    }

    static {
        DATA_WOOL_ID = SynchedEntityData.defineId(Fluffer.class, EntityDataSerializers.BYTE);
        ITEM_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), (colormap) -> {
            colormap.put(DyeColor.WHITE, KoratioBlocks.WHITE_LEVITATING_WOOL);
            colormap.put(DyeColor.ORANGE, KoratioBlocks.ORANGE_LEVITATING_WOOL);
            colormap.put(DyeColor.MAGENTA, KoratioBlocks.MAGENTA_LEVITATING_WOOL);
            colormap.put(DyeColor.LIGHT_BLUE, KoratioBlocks.LIGHT_BLUE_LEVITATING_WOOL);
            colormap.put(DyeColor.YELLOW, KoratioBlocks.YELLOW_LEVITATING_WOOL);
            colormap.put(DyeColor.LIME, KoratioBlocks.LIME_LEVITATING_WOOL);
            colormap.put(DyeColor.PINK, KoratioBlocks.PINK_LEVITATING_WOOL);
            colormap.put(DyeColor.GRAY, KoratioBlocks.GRAY_LEVITATING_WOOL);
            colormap.put(DyeColor.LIGHT_GRAY, KoratioBlocks.LIGHT_GRAY_LEVITATING_WOOL);
            colormap.put(DyeColor.CYAN, KoratioBlocks.CYAN_LEVITATING_WOOL);
            colormap.put(DyeColor.PURPLE, KoratioBlocks.PURPLE_LEVITATING_WOOL);
            colormap.put(DyeColor.BLUE, KoratioBlocks.BLUE_LEVITATING_WOOL);
            colormap.put(DyeColor.BROWN, KoratioBlocks.BROWN_LEVITATING_WOOL);
            colormap.put(DyeColor.GREEN, KoratioBlocks.GREEN_LEVITATING_WOOL);
            colormap.put(DyeColor.RED, KoratioBlocks.RED_LEVITATING_WOOL);
            colormap.put(DyeColor.BLACK, KoratioBlocks.BLACK_LEVITATING_WOOL);
        });
        COLOR_BY_DYE = Maps.newEnumMap((Map)Arrays.stream(DyeColor.values()).collect(Collectors.toMap((color) -> color, Fluffer::createWoolColor)));
    }
}