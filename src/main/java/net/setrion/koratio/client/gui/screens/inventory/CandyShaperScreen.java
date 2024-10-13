package net.setrion.koratio.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.world.inventory.CandyShaperMenu;
import net.setrion.koratio.world.item.crafting.CandyShaperRecipe;
import net.setrion.koratio.world.level.block.entity.CandyShaperBlockEntity;
import org.joml.Matrix4f;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CandyShaperScreen extends AbstractContainerScreen<CandyShaperMenu> {
    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/candy_shaper/scroller");
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/candy_shaper/scroller_disabled");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/candy_shaper/recipe_selected");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/candy_shaper/recipe_highlighted");
    private static final ResourceLocation RECIPE_SPRITE = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/candy_shaper/recipe");
    private static final ResourceLocation BG_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/gui/container/candy_shaper.png");

    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;
    private final CandyShaperBlockEntity blockEntity;

    public CandyShaperScreen(CandyShaperMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.blockEntity = (CandyShaperBlockEntity) menu.getBlockEntity();
        menu.registerUpdateListener(this::containerChanged);
        --titleLabelY;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float tick) {
        super.render(graphics, mouseX, mouseY, tick);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mouseX, int mouseY) {
        int i = leftPos;
        int j = topPos;
        graphics.blit(BG_LOCATION, i, j, 0, 0, imageWidth, imageHeight);
        int k = (int)(41.0F * scrollOffs);
        ResourceLocation resourcelocation = isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
        graphics.blitSprite(resourcelocation, i + 118, j + 15 + k, 12, 15);
        int l = leftPos + 51;
        int i1 = topPos + 14;
        int j1 = startIndex + 12;
        renderButtons(graphics, mouseX, mouseY, l, i1, j1);
        renderRecipes(graphics, l, i1, j1);

        drawFluid(graphics, 7, 54, 0, KoratioBlocks.MOLTEN_SUGAR.get(), i+7, j+15);
        drawFluid(graphics, 7, 54, 1, KoratioBlocks.MOLTEN_BLUE_SUGAR.get(), i+15, j+15);
        drawFluid(graphics, 7, 54, 2, KoratioBlocks.MOLTEN_GREEN_SUGAR.get(), i+23, j+15);
        drawFluid(graphics, 7, 54, 3, KoratioBlocks.MOLTEN_YELLOW_SUGAR.get(), i+31, j+15);
        drawFluid(graphics, 7, 54, 4, KoratioBlocks.MOLTEN_RED_SUGAR.get(), i+39, j+15);
        for (int s = 0; s < 5; s++) {
            graphics.blit(BG_LOCATION, i+6+(s*8), j+14, 176, 0, 9, 56);
            //drawTankOverlay(graphics, 9, 56, i+(s*8), j);
        }
    }

    @Override
    protected void renderTooltip(GuiGraphics graphics, int x, int y) {
        super.renderTooltip(graphics, x, y);
        int x1 = leftPos+6;
        int y1 = topPos+14;
        if (y > y1 &&  y < y1 + 55) {
            if (x > x1 && x < x1 + 8) {
                graphics.renderComponentTooltip(font, List.of(Component.translatable(KoratioBlocks.MOLTEN_SUGAR.get().getDescriptionId()), Component.literal(menu.fluidSlots.get(0).getFluid().getAmount() + " / 2000 mB")), x, y);
            } else if (x > x1 + 8 && x < x1 + 16) {
                graphics.renderComponentTooltip(font, List.of(Component.translatable(KoratioBlocks.MOLTEN_BLUE_SUGAR.get().getDescriptionId()), Component.literal(menu.fluidSlots.get(1).getFluid().getAmount() + " / 2000 mB")), x, y);
            } else if (x > x1 + 16 && x < x1 + 24) {
                graphics.renderComponentTooltip(font, List.of(Component.translatable(KoratioBlocks.MOLTEN_GREEN_SUGAR.get().getDescriptionId()), Component.literal(menu.fluidSlots.get(2).getFluid().getAmount() + " / 2000 mB")), x, y);
            } else if (x > x1 + 24 && x < x1 + 32) {
                graphics.renderComponentTooltip(font, List.of(Component.translatable(KoratioBlocks.MOLTEN_YELLOW_SUGAR.get().getDescriptionId()), Component.literal(menu.fluidSlots.get(3).getFluid().getAmount() + " / 2000 mB")), x, y);
            } else if (x > x1 + 32 && x < x1 + 40) {
                graphics.renderComponentTooltip(font, List.of(Component.translatable(KoratioBlocks.MOLTEN_RED_SUGAR.get().getDescriptionId()), Component.literal(menu.fluidSlots.get(4).getFluid().getAmount() + " / 2000 mB")), x, y);
            }
        }

        if (displayRecipes) {
            int i = leftPos + 52;
            int j = topPos + 14;
            int k = startIndex + 12;
            List<RecipeHolder<CandyShaperRecipe>> list = menu.getRecipes();

            for(int l = startIndex; l < k && l < menu.getNumRecipes(); ++l) {
                int i1 = l - startIndex;
                int j1 = i + i1 % 4 * 16;
                int k1 = j + i1 / 4 * 18 + 2;
                if (x >= j1 && x < j1 + 16 && y >= k1 && y < k1 + 18) {
                    ItemStack stack = list.get(l).value().getResultItem(minecraft.level.registryAccess());
                    List<Component> tooltip = Screen.getTooltipFromItem(this.minecraft, stack);
                    for (int z = 0; z < 5; z++) {
                        if (list.get(l).value().getFluidIngredients().get(z).amount() > 0) {
                            tooltip.add(Component.translatable("block." + NeoForgeRegistries.FLUID_TYPES.getResourceKey(list.get(l).value().getFluidIngredients().get(z).getFluids()[0].getFluid().getFluidType()).get().location().toLanguageKey()).append(": ").append(Component.literal(list.get(l).value().getFluidIngredients().get(z).amount() + " mB").withStyle(ChatFormatting.GOLD)));
                        }
                    }
                    graphics.renderTooltip(font, tooltip, stack.getTooltipImage(), x, y);
                }
            }
        }
    }

    private void renderButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y, int lastVisibleElementIndex) {
        for (int i = startIndex; i < lastVisibleElementIndex && i < menu.getNumRecipes(); i++) {
            int j = i - startIndex;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int i1 = y + l * 18 + 2;
            ResourceLocation resourcelocation;
            if (i == menu.getSelectedRecipeIndex()) {
                resourcelocation = RECIPE_SELECTED_SPRITE;
            } else if (mouseX >= k && mouseY >= i1 && mouseX < k + 16 && mouseY < i1 + 18) {
                resourcelocation = RECIPE_HIGHLIGHTED_SPRITE;
            } else {
                resourcelocation = RECIPE_SPRITE;
            }

            guiGraphics.blitSprite(resourcelocation, k, i1 - 1, 16, 18);
        }
    }

    @Override
    protected void init() {
        super.init();
        containerChanged();
    }

    private void renderRecipes(GuiGraphics guiGraphics, int x, int y, int startIndex) {
        List<RecipeHolder<CandyShaperRecipe>> list = menu.getRecipes();

        for (int i = this.startIndex; i < startIndex && i < list.size(); i++) {
            int j = i - this.startIndex;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int i1 = y + l * 18 + 2;
            guiGraphics.renderItem(list.get(i).value().getResultItem(minecraft.level.registryAccess()), k, i1);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        if (displayRecipes) {
            int i = leftPos + 51;
            int j = topPos + 14;
            int k = startIndex + 12;

            for(int l = startIndex; l < k; ++l) {
                int i1 = l - startIndex;
                double d0 = mouseX - (double)(i + i1 % 4 * 16);
                double d1 = mouseY - (double)(j + i1 / 4 * 18);
                if (d0 >= 0.0 && d1 >= 0.0 && d0 < 16.0 && d1 < 18.0 && menu.clickMenuButton(minecraft.player, l)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    minecraft.gameMode.handleInventoryButtonClick(menu.containerId, l);
                    return true;
                }
            }

            i = leftPos + 119;
            j = topPos + 9;
            if (mouseX >= (double)i && mouseX < (double)(i + 12) && mouseY >= (double)j && mouseY < (double)(j + 54)) {
                scrolling = true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (scrolling && isScrollBarActive()) {
            int i = topPos + 14;
            int j = i + 54;
            scrollOffs = ((float)mouseY - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
            scrollOffs = Mth.clamp(scrollOffs, 0.0F, 1.0F);
            startIndex = (int)((double)(scrollOffs * (float)getOffscreenRows()) + 0.5) * 4;
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (isScrollBarActive()) {
            int i = getOffscreenRows();
            float f = (float)scrollY / (float)i;
            scrollOffs = Mth.clamp(scrollOffs - f, 0.0F, 1.0F);
            startIndex = (int)((double)(scrollOffs * (float)i) + 0.5) * 4;
        }

        return true;
    }

    private boolean isScrollBarActive() {
        return displayRecipes && menu.getNumRecipes() > 12;
    }

    protected int getOffscreenRows() {
        return (menu.getNumRecipes() + 4 - 1) / 4 - 3;
    }

    private void containerChanged() {
        displayRecipes = menu.getFluidSlot(0).getFluid().getAmount() > 0 || menu.getFluidSlot(1).getFluid().getAmount() > 0 || menu.getFluidSlot(2).getFluid().getAmount() > 0 || menu.getFluidSlot(3).getFluid().getAmount() > 0 || menu.getFluidSlot(4).getFluid().getAmount() > 0 ;
        if (!displayRecipes) {
            scrollOffs = 0.0F;
            startIndex = 0;
        }
    }

    protected int getFluidStoredScaled(int slot) {
        int i = menu.getFluidSlot(slot).getFluid().getAmount();
        return i != 0 ? i * 54 / 2000 : 0;
    }

    private void drawFluid(GuiGraphics guiGraphics, final int width, final int height, int slot, Block fluidBlock, int posX, int posY) {
        int scale = getFluidStoredScaled(slot);
        long amount = menu.getFluidSlot(slot).getFluid().getAmount();
        if (amount > 0 && scale < 1) {
            scale = 1;
        }
        if (scale > height) {
            scale = height;
        }

        drawTiledSprite(guiGraphics, width, height, scale, Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(TextureMapping.getBlockTexture(fluidBlock, "_still")), posX, posY);
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