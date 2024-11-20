package net.setrion.koratio.world.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioDataComponents;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioTags;
import net.setrion.koratio.world.level.block.entity.GlazedBlockEntity;

import java.util.List;

public class PipingBagItem extends Item {

    public PipingBagItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.set(KoratioDataComponents.PIPING_BAG_DATA.get(), new KoratioDataComponents.PipingBagRecord("none", 0));
        return stack;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (isBarVisible(stack)) {
            return getColor(stack).getColor();
        }
        return 0;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof GlazedBlockEntity gingerbread) {
            ItemStack stack = context.getItemInHand();
            Direction facing = context.getClickedFace();
            int dir = 0;
            if (stack.is(KoratioItems.PIPING_BAG)) {
                if (!isEmpty(stack)) {
                    GlazedBlockEntity.PartColor color = getColor(stack);
                    if (facing == Direction.DOWN) {
                        dir = 5;
                    } else if (facing == Direction.UP) {
                        dir = 4;
                    } else if (facing == Direction.NORTH) {
                        dir = 0;
                    } else if (facing == Direction.EAST) {
                        dir = 1;
                    } else if (facing == Direction.SOUTH) {
                        dir = 2;
                    } else if (facing == Direction.WEST) {
                        dir = 3;
                    }
                    GlazedBlockEntity.Part part = getHitPart(context, context.getClickedFace());
                    if (part != null && (!gingerbread.getPart(context.getClickedFace(), part) || context.getPlayer().isShiftKeyDown())) {
                        if (gingerbread.getColor(dir) == GlazedBlockEntity.PartColor.NONE) {
                            gingerbread.setColor(dir, color);
                        }
                        if (getColor(stack) == gingerbread.getColor(dir)) {
                            if (context.getPlayer().isShiftKeyDown()) {
                                int cost = 0;
                                for (GlazedBlockEntity.Part p : GlazedBlockEntity.Part.values()) {
                                    if (!gingerbread.getPart(context.getClickedFace(), p)) {
                                        cost++;
                                    }
                                }
                                if (cost > 0 && getAmount(stack) >= cost) {
                                    for (GlazedBlockEntity.Part p : GlazedBlockEntity.Part.values()) {
                                        if (!gingerbread.getPart(context.getClickedFace(), p)) {
                                            gingerbread.setPart(context.getClickedFace(), p, true);
                                        }
                                    }
                                    if (!context.getPlayer().isCreative()) {
                                        setAmount(stack, getAmount(stack) - cost);
                                    }
                                    context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.CANDLE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                                    return InteractionResult.SUCCESS;
                                }
                                return InteractionResult.PASS;
                            } else {
                                gingerbread.setPart(context.getClickedFace(), part, true);
                                if (!context.getPlayer().isCreative()) {
                                    setAmount(stack, getAmount(stack) - 1);
                                }
                                context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.CANDLE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                            }
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    private static GlazedBlockEntity.Part getHitPart(UseOnContext context, Direction face) {
        Direction direction = context.getClickedFace();
        if (face != direction) {
            return null;
        } else {
            BlockPos blockpos = context.getClickedPos().relative(direction);
            Vec3 vec3 = context.getClickLocation().subtract(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            double d0 = vec3.x();
            double d1 = vec3.y();
            double d2 = vec3.z();

            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                if (d1 > 0.8) {
                    if (d0 < 0.2) {
                        return direction == Direction.NORTH ? GlazedBlockEntity.Part.TOP_RIGHT : GlazedBlockEntity.Part.TOP_LEFT;
                    } else if (d0 > 0.8) {
                        return direction == Direction.NORTH ? GlazedBlockEntity.Part.TOP_LEFT : GlazedBlockEntity.Part.TOP_RIGHT;
                    } else {
                        return GlazedBlockEntity.Part.TOP_MIDDLE;
                    }
                } else if (d1 < 0.2) {
                    if (d0 < 0.2) {
                        return direction == Direction.NORTH ? GlazedBlockEntity.Part.BOTTOM_RIGHT : GlazedBlockEntity.Part.BOTTOM_LEFT;
                    } else if (d0 > 0.8) {
                        return direction == Direction.NORTH ? GlazedBlockEntity.Part.BOTTOM_LEFT : GlazedBlockEntity.Part.BOTTOM_RIGHT;
                    } else {
                        return GlazedBlockEntity.Part.BOTTOM_MIDDLE;
                    }
                } else {
                    if (d0 < 0.2) {
                        return direction == Direction.NORTH ? GlazedBlockEntity.Part.RIGHT : GlazedBlockEntity.Part.LEFT;
                    } else if (d0 > 0.8) {
                        return direction == Direction.NORTH ? GlazedBlockEntity.Part.LEFT : GlazedBlockEntity.Part.RIGHT;
                    } else {
                        return GlazedBlockEntity.Part.MIDDLE;
                    }
                }
            } else if (direction == Direction.EAST || direction == Direction.WEST) {
                if (d1 > 0.8) {
                    if (d2 < 0.2) {
                        return direction == Direction.EAST ? GlazedBlockEntity.Part.TOP_RIGHT : GlazedBlockEntity.Part.TOP_LEFT;
                    } else if (d2 > 0.8) {
                        return direction == Direction.EAST ? GlazedBlockEntity.Part.TOP_LEFT : GlazedBlockEntity.Part.TOP_RIGHT;
                    } else {
                        return GlazedBlockEntity.Part.TOP_MIDDLE;
                    }
                } else if (d1 < 0.2) {
                    if (d2 < 0.2) {
                        return direction == Direction.EAST ? GlazedBlockEntity.Part.BOTTOM_RIGHT : GlazedBlockEntity.Part.BOTTOM_LEFT;
                    } else if (d2 > 0.8) {
                        return direction == Direction.EAST ? GlazedBlockEntity.Part.BOTTOM_LEFT : GlazedBlockEntity.Part.BOTTOM_RIGHT;
                    } else {
                        return GlazedBlockEntity.Part.BOTTOM_MIDDLE;
                    }
                } else {
                    if (d2 < 0.2) {
                        return direction == Direction.EAST ? GlazedBlockEntity.Part.RIGHT : GlazedBlockEntity.Part.LEFT;
                    } else if (d2 > 0.8) {
                        return direction == Direction.EAST ? GlazedBlockEntity.Part.LEFT : GlazedBlockEntity.Part.RIGHT;
                    } else {
                        return GlazedBlockEntity.Part.MIDDLE;
                    }
                }
            } else if (direction == Direction.UP || direction == Direction.DOWN) {
                if (d2 < 0.2) {
                    if (direction == Direction.UP) {
                        if (d0 < 0.2) {
                            return GlazedBlockEntity.Part.TOP_LEFT;
                        } else if (d0 > 0.8) {
                            return GlazedBlockEntity.Part.TOP_RIGHT;
                        } else {
                            return GlazedBlockEntity.Part.TOP_MIDDLE;
                        }
                    } else {
                        if (d0 < 0.2) {
                            return GlazedBlockEntity.Part.BOTTOM_LEFT;
                        } else if (d0 > 0.8) {
                            return GlazedBlockEntity.Part.BOTTOM_RIGHT;
                        } else {
                            return GlazedBlockEntity.Part.BOTTOM_MIDDLE;
                        }
                    }
                } else if (d2 > 0.8) {
                    if (direction == Direction.UP) {
                        if (d0 < 0.2) {
                            return GlazedBlockEntity.Part.BOTTOM_LEFT;
                        } else if (d0 > 0.8) {
                            return GlazedBlockEntity.Part.BOTTOM_RIGHT;
                        } else {
                            return GlazedBlockEntity.Part.BOTTOM_MIDDLE;
                        }
                    } else {
                        if (d0 < 0.2) {
                            return GlazedBlockEntity.Part.TOP_LEFT;
                        } else if (d0 > 0.8) {
                            return GlazedBlockEntity.Part.TOP_RIGHT;
                        } else {
                            return GlazedBlockEntity.Part.TOP_MIDDLE;
                        }
                    }
                } else {
                    if (d0 < 0.2) {
                        return GlazedBlockEntity.Part.LEFT;
                    } else if (d0 > 0.8) {
                        return GlazedBlockEntity.Part.RIGHT;
                    } else {
                        return GlazedBlockEntity.Part.MIDDLE;
                    }
                }
            }
        }
        return null;
    }

    public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
        if (usedHand == InteractionHand.MAIN_HAND) {
            if (player.getItemInHand(InteractionHand.OFF_HAND).is(KoratioTags.Items.ICINGS)) {
                ItemStack frosting = player.getItemInHand(InteractionHand.OFF_HAND);
                if (isEmpty(player.getItemInHand(usedHand))) {
                    ItemStack stack = player.getItemInHand(usedHand);
                    if (frosting.is(KoratioBlocks.WHITE_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.WHITE, 69);
                    } else if (frosting.is(KoratioBlocks.LIGHT_GRAY_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.LIGHT_GRAY, 69);
                    } else if (frosting.is(KoratioBlocks.GRAY_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.GRAY, 69);
                    } else if (frosting.is(KoratioBlocks.BLACK_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.BLACK, 69);
                    } else if (frosting.is(KoratioBlocks.BROWN_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.BROWN, 69);
                    } else if (frosting.is(KoratioBlocks.RED_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.RED, 69);
                    } else if (frosting.is(KoratioBlocks.ORANGE_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.ORANGE, 69);
                    } else if (frosting.is(KoratioBlocks.YELLOW_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.YELLOW, 69);
                    } else if (frosting.is(KoratioBlocks.LIME_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.LIME, 69);
                    } else if (frosting.is(KoratioBlocks.GREEN_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.GREEN, 69);
                    } else if (frosting.is(KoratioBlocks.CYAN_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.CYAN, 69);
                    } else if (frosting.is(KoratioBlocks.LIGHT_BLUE_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.LIGHT_BLUE, 69);
                    } else if (frosting.is(KoratioBlocks.BLUE_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.BLUE, 69);
                    } else if (frosting.is(KoratioBlocks.PURPLE_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.PURPLE, 69);
                    } else if (frosting.is(KoratioBlocks.MAGENTA_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.MAGENTA, 69);
                    } else if (frosting.is(KoratioBlocks.PINK_ICING_BLOCK.asItem())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.PINK, 69);
                    }
                    player.getItemInHand(InteractionHand.OFF_HAND).shrink(1);
                }
            }
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (isEmpty(stack)) {
            tooltipComponents.add(Component.translatable("tooltip.koratio.piping_bag.empty"));
            if (Screen.hasShiftDown()) {
                tooltipComponents.add(Component.translatable("tooltip.koratio.piping_bag.empty_fill"));
            } else {
                tooltipComponents.add(Component.translatable("tooltip.koratio.hold_shift"));
            }
        } else {
            GlazedBlockEntity.PartColor color = getColor(stack);
            int amount = getAmount(stack);
            tooltipComponents.add(Component.translatable("tooltip.koratio.piping_bag.filled", amount).withColor(color.getColor()));
        }
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F / 69 * (float)getAmount(stack));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getColor(stack) != GlazedBlockEntity.PartColor.NONE && !isEmpty(stack);
    }

    public int getAmount(ItemStack stack) {
        if (!stack.getComponents().has(KoratioDataComponents.PIPING_BAG_DATA.get())) return 0;
        return stack.getComponents().get(KoratioDataComponents.PIPING_BAG_DATA.get()).amount();
    }

    public void setAmount(ItemStack stack, int amount) {
        GlazedBlockEntity.PartColor color = getColor(stack);
        if (stack.getComponents().has(KoratioDataComponents.PIPING_BAG_DATA.get())) {
            amount = Mth.clamp(amount, 0, 69);
            if (amount <= 0) {
                color = GlazedBlockEntity.PartColor.NONE;
            }
            stack.set(KoratioDataComponents.PIPING_BAG_DATA.get(), new KoratioDataComponents.PipingBagRecord(color.getSerializedName(), amount));
        }
    }

    public GlazedBlockEntity.PartColor getColor(ItemStack stack) {
        if (!stack.getComponents().has(KoratioDataComponents.PIPING_BAG_DATA.get())) return GlazedBlockEntity.PartColor.NONE;
        return GlazedBlockEntity.PartColor.byName(stack.getComponents().get(KoratioDataComponents.PIPING_BAG_DATA.get()).color());
    }

    public void setColor(ItemStack stack, GlazedBlockEntity.PartColor color) {
        stack.set(KoratioDataComponents.PIPING_BAG_DATA.get(), new KoratioDataComponents.PipingBagRecord(color.getSerializedName(), 0));
    }

    public void setColorAndAmount(ItemStack stack, GlazedBlockEntity.PartColor color, int amount) {
        stack.set(KoratioDataComponents.PIPING_BAG_DATA.get(), new KoratioDataComponents.PipingBagRecord(color.getSerializedName(), amount));
    }

    public boolean isEmpty(ItemStack stack) {
        if (!stack.getComponents().has(KoratioDataComponents.PIPING_BAG_DATA.get())) return true;
        return stack.getComponents().get(KoratioDataComponents.PIPING_BAG_DATA.get()).amount() <= 0;
    }
}