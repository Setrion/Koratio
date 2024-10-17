package net.setrion.koratio.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
                if (d0 < 0.2 && d1 < 0.8) {
                    if (direction == Direction.NORTH) return GlazedBlockEntity.Part.LEFT;
                    return GlazedBlockEntity.Part.RIGHT;
                } else if (d1 < 0.2) {
                    return GlazedBlockEntity.Part.BOTTOM;
                } else if (d1 > 0.8) {
                    return GlazedBlockEntity.Part.TOP;
                } else if (d0 > 0.8 && d1 < 0.8) {
                    if (direction == Direction.NORTH) return GlazedBlockEntity.Part.RIGHT;
                    return GlazedBlockEntity.Part.LEFT;
                } else {
                    return GlazedBlockEntity.Part.MIDDLE;
                }
            } else if (direction == Direction.EAST || direction == Direction.WEST) {
                System.out.println(d2 + ", " + d1);
                if (d2 < 0.2 && d1 < 0.8) {
                    if (direction == Direction.EAST) return GlazedBlockEntity.Part.LEFT;
                    return GlazedBlockEntity.Part.RIGHT;
                } else if (d1 < 0.2) {
                    return GlazedBlockEntity.Part.BOTTOM;
                } else if (d1 > 0.8) {
                    return GlazedBlockEntity.Part.TOP;
                } else if (d2 > 0.8 && d1 < 0.8) {
                    if (direction == Direction.EAST) return GlazedBlockEntity.Part.RIGHT;
                    return GlazedBlockEntity.Part.LEFT;
                } else {
                    return GlazedBlockEntity.Part.MIDDLE;
                }
            } else if (direction == Direction.UP || direction == Direction.DOWN) {
                if (d0 < 0.2 && d2 < 0.8) {
                    return GlazedBlockEntity.Part.RIGHT;
                } else if (d2 < 0.2) {
                    if (direction == Direction.UP) return GlazedBlockEntity.Part.TOP;
                    return GlazedBlockEntity.Part.BOTTOM;
                } else if (d2 > 0.8) {
                    if (direction == Direction.UP) return GlazedBlockEntity.Part.BOTTOM;
                    return GlazedBlockEntity.Part.TOP;
                } else if (d0 > 0.8 && d2 < 0.8) {
                    return GlazedBlockEntity.Part.LEFT;
                } else {
                    return GlazedBlockEntity.Part.MIDDLE;
                }
            }
        }
        return null;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (usedHand == InteractionHand.MAIN_HAND) {
            if (player.getItemInHand(InteractionHand.OFF_HAND).is(KoratioTags.Items.FROSTINGS)) {
                ItemStack frosting = player.getItemInHand(InteractionHand.OFF_HAND);
                if (isEmpty(player.getItemInHand(usedHand))) {
                    ItemStack stack = player.getItemInHand(usedHand);
                    if (frosting.is(KoratioItems.FROSTING_BLOCK.get())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.WHITE, 69);
                    } else if (frosting.is(KoratioItems.BLUE_FROSTING_BLOCK.get())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.BLUE, 69);
                    } else if (frosting.is(KoratioItems.GREEN_FROSTING_BLOCK.get())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.GREEN, 69);
                    } else if (frosting.is(KoratioItems.YELLOW_FROSTING_BLOCK.get())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.YELLOW, 69);
                    } else if (frosting.is(KoratioItems.RED_FROSTING_BLOCK.get())) {
                        setColorAndAmount(stack, GlazedBlockEntity.PartColor.RED, 69);
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
        } else {
            GlazedBlockEntity.PartColor color = getColor(stack);
            ChatFormatting chatFormatting = ChatFormatting.GRAY;
            if (color == GlazedBlockEntity.PartColor.WHITE) {
                chatFormatting = ChatFormatting.WHITE;
            } else if (color == GlazedBlockEntity.PartColor.BLUE) {
                chatFormatting = ChatFormatting.BLUE;
            } else if (color == GlazedBlockEntity.PartColor.GREEN) {
                chatFormatting = ChatFormatting.GREEN;
            } else if (color == GlazedBlockEntity.PartColor.YELLOW) {
                chatFormatting = ChatFormatting.YELLOW;
            } else if (color == GlazedBlockEntity.PartColor.RED) {
                chatFormatting = ChatFormatting.RED;
            }
            int amount = getAmount(stack);
            tooltipComponents.add(Component.translatable("tooltip.koratio.piping_bag.filled", amount).withStyle(chatFormatting));
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