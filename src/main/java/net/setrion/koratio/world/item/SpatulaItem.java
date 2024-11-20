package net.setrion.koratio.world.item;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;
import net.setrion.koratio.registry.KoratioTags;
import net.setrion.koratio.world.level.block.entity.GlazedBlockEntity;

public class SpatulaItem extends DiggerItem {

    public SpatulaItem(ToolMaterial tier, Item.Properties properties) {
        super(tier, KoratioTags.Blocks.SPATULA_EFFICIENT, 1.5F, 1.0F, properties);
    }

    public static ItemAttributeModifiers createAttributes(ToolMaterial tier, float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage + tier.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().getBlockEntity(context.getClickedPos()) instanceof GlazedBlockEntity gingerbread) {
            if (gingerbread.hasOverlay()) {
                int cleared = 0;
                if (context.getPlayer().isShiftKeyDown()) {
                    for (Direction direction : Direction.values()) {
                        if (context.getItemInHand().isEmpty()) {
                            break;
                        }
                        if (gingerbread.hasOverlayOnSide(direction)) {
                            cleared++;
                            gingerbread.clearOverlay(direction);
                            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), LivingEntity.getSlotForHand(context.getHand()));
                        }
                    }
                    if (cleared > 0) {
                        context.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), context.getLevel().getBlockState(context.getClickedPos())));
                        context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.SUCCESS;
                    }
                } else {
                    if (gingerbread.hasOverlayOnSide(context.getClickedFace())) {
                        gingerbread.clearOverlay(context.getClickedFace());
                        context.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), context.getLevel().getBlockState(context.getClickedPos())));
                        context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        context.getItemInHand().hurtAndBreak(1, context.getPlayer(), LivingEntity.getSlotForHand(context.getHand()));
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
    }
}