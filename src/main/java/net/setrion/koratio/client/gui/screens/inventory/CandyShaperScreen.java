package net.setrion.koratio.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.inventory.CandyShaperMenu;
import net.setrion.koratio.world.item.CandyTemplateItem;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import net.setrion.koratio.world.level.block.entity.CandyShaperBlockEntity;
import org.joml.Matrix4f;

import java.util.List;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class CandyShaperScreen extends AbstractContainerScreen<CandyShaperMenu> {private static final ResourceLocation BG_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/gui/container/candy_shaper.png");

    private final CandyShaperBlockEntity blockEntity;
    private static final Component MISSING_TEMPLATE_TOOLTIP = Component.translatable("container.candy_shaper.missing_template_tooltip");
    private static final ResourceLocation EMPTY_SLOT_CANDY_CANE = Koratio.prefix("item/empty_slot_candy_cane");
    private static final ResourceLocation EMPTY_SLOT_LOLLIPOP = Koratio.prefix("item/empty_slot_lollipop");
    private static final ResourceLocation EMPTY_SLOT_BONBON = Koratio.prefix("item/empty_slot_bonbon");
    private static final List<ResourceLocation> EMPTY_TEMPLATE_SLOT = List.of(
            EMPTY_SLOT_CANDY_CANE, EMPTY_SLOT_LOLLIPOP, EMPTY_SLOT_BONBON
    );
    private final CyclingSlotBackground templateIcon = new CyclingSlotBackground(0);

    public CandyShaperScreen(CandyShaperMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.blockEntity = (CandyShaperBlockEntity) menu.getBlockEntity();
        menu.registerUpdateListener(this::containerChanged);
        --titleLabelY;
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.templateIcon.tick(EMPTY_TEMPLATE_SLOT);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float tick) {
        super.render(graphics, mouseX, mouseY, tick);
        this.renderOnboardingTooltips(graphics, mouseX, mouseY);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mouseX, int mouseY) {
        int i = leftPos;
        int j = topPos;
        graphics.blit(BG_LOCATION, i, j, 0, 0, imageWidth, imageHeight);

        if (!this.blockEntity.getFluidHandler().getFluidInTank(0).isEmpty()) {
            drawFluid(graphics, 16, 44, 0, this.blockEntity.getFluidHandler().getFluidInTank(0).getFluid(), i+8, j+19);
        }
        if (!this.blockEntity.getFluidHandler().getFluidInTank(1).isEmpty()) {
            drawFluid(graphics, 16, 44, 1, this.blockEntity.getFluidHandler().getFluidInTank(1).getFluid(), i+44, j+19);
        }
        graphics.blit(BG_LOCATION, i+7, j+18, 176, 0, 18, 46);
        graphics.blit(BG_LOCATION, i+43, j+18, 176, 0, 18, 46);
        this.templateIcon.render(this.menu, graphics, tick, this.leftPos, this.topPos);
    }

    private void renderOnboardingTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        Optional<Component> optional = Optional.empty();

        if (this.hoveredSlot != null) {
            ItemStack itemstack = this.menu.getSlot(0).getItem();
            if (itemstack.isEmpty()) {
                if (this.hoveredSlot.index == 0) {
                    optional = Optional.of(MISSING_TEMPLATE_TOOLTIP);
                }
            }
        }

        optional.ifPresent(p_280863_ -> guiGraphics.renderTooltip(this.font, this.font.split(p_280863_, 115), mouseX, mouseY));
    }

    @Override
    protected void renderTooltip(GuiGraphics graphics, int x, int y) {
        super.renderTooltip(graphics, x, y);
        int x1 = leftPos+7;
        int y1 = topPos+18;
        if (y > y1 &&  y < y1 + 45) {
            if (x > x1 && x < x1 + 17) {
                if (!menu.fluidSlots.get(0).getFluid().isEmpty()) {
                    graphics.renderComponentTooltip(font, List.of(Component.translatable(menu.fluidSlots.get(0).getFluid().getDescriptionId()), Component.literal(menu.fluidSlots.get(0).getFluid().getAmount() + " / 2000 mB")), x, y);
                }
            } else if (x > x1 + 36 && x < x1 + 53) {
                if (!menu.fluidSlots.get(0).getFluid().isEmpty()) {
                    graphics.renderComponentTooltip(font, List.of(Component.translatable(menu.fluidSlots.get(1).getFluid().getDescriptionId()), Component.literal(menu.fluidSlots.get(1).getFluid().getAmount() + " / 2000 mB")), x, y);
                }
            }
        }
    }

    @Override
    protected void init() {
        super.init();
        containerChanged();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    private void containerChanged() {

    }

    protected int getFluidStoredScaled(int slot) {
        int i = menu.getFluidSlot(slot).getFluid().getAmount();
        return i != 0 ? i * 44 / 2000 : 0;
    }

    private void drawFluid(GuiGraphics guiGraphics, final int width, final int height, int slot, Fluid fluid, int posX, int posY) {
        int scale = getFluidStoredScaled(slot);
        long amount = menu.getFluidSlot(slot).getFluid().getAmount();
        if (amount > 0 && scale < 1) {
            scale = 1;
        }
        if (scale > height) {
            scale = height;
        }
        TextureAtlasSprite still = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS).getSprite(IClientFluidTypeExtensions.of(fluid).getStillTexture());

        drawTiledSprite(guiGraphics, width, height, scale, still, posX, posY);
    }

    private static void drawTiledSprite(GuiGraphics guiGraphics, final int tiledWidth, final int tiledHeight, long scaledAmount, TextureAtlasSprite sprite, int posX, int posY) {
        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        Matrix4f matrix = guiGraphics.pose().last().pose();

        final int xTileCount = tiledWidth / 16;
        final int xRemainder = tiledWidth - (xTileCount * 16);
        final long yTileCount = scaledAmount / 16;
        final long yRemainder = scaledAmount - (yTileCount * 16);

        final int yStart = tiledHeight + posY;

        for (int xTile = 0; xTile <= xTileCount; xTile++) {
            for (int yTile = 0; yTile <= yTileCount; yTile++) {
                int width = (xTile == xTileCount) ? xRemainder : 16;
                long height = (yTile == yTileCount) ? yRemainder : 16;
                int x = posX + (xTile * 16);
                int y = yStart - ((yTile + 1) * 16);
                if (width > 0 && height > 0) {
                    long maskTop = 16 - height;
                    int maskRight = 16 - width;
                    float uMin = sprite.getU0();
                    float uMax = sprite.getU1();
                    float vMin = sprite.getV0();
                    float vMax = sprite.getV1();
                    uMax = uMax - (maskRight / 16F * (uMax - uMin));
                    vMax = vMax - (maskTop / 16F * (vMax - vMin));

                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.disableDepthTest();
                    Tesselator tesselator = Tesselator.getInstance();
                    BufferBuilder bufferBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferBuilder.addVertex(matrix, x, y + 16, 16).setUv(uMin, vMax);
                    bufferBuilder.addVertex(matrix, x + 16 - maskRight, y + 16, 16).setUv(uMax, vMax);
                    bufferBuilder.addVertex(matrix, x + 16 - maskRight, y + maskTop, 16).setUv(uMax, vMin);
                    bufferBuilder.addVertex(matrix, x, y + maskTop, 16).setUv(uMin, vMin);
                    BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
                }
            }
        }
    }
}