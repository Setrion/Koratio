package net.setrion.koratio.client.gui.screens.inventory;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.inventory.DecryptingMenu;
import net.setrion.koratio.world.item.DecryptingBookItem;

public class DecryptingScreen extends AbstractContainerScreen<DecryptingMenu> {
	
	private static final ResourceLocation DECRYPTING_TABLE_LOCATION = ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "textures/gui/container/decrypting_table.png");
	public static final WidgetSprites DECRYPTING_TABLE_SPRITES = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/decrypting_table/button"), ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/decrypting_table/button_disabled"), ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/decrypting_table/button_highlighted"), ResourceLocation.fromNamespaceAndPath(Koratio.MOD_ID, "container/decrypting_table/button_selected")
	);

	private float scrollOffs;
	private boolean scrolling;
	private int levelCost;
	private int chance;
	private Button button;
	
	public DecryptingScreen(DecryptingMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
		scrollOffs = 1.0F;
	}
	
	@Override
	protected void init() {
		super.init();
		this.addRenderableWidget(button = new ImageButton(leftPos + 20, height / 2 - 49, 20, 18, DECRYPTING_TABLE_SPRITES, (button) -> {
			if (chance > 0 || minecraft.player.getAbilities().instabuild) {
				minecraft.gameMode.handleInventoryButtonClick((menu).containerId, levelCost);
			}
		}));
		button.active = false;
	}
	
	@Override
	protected void containerTick() {
		super.containerTick();
		updateButtonState();
	}
	
	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float f) {
		renderBackground(graphics, mouseX, mouseY, f);
		super.render(graphics, mouseX, mouseY, f);
		renderTooltip(graphics, mouseX, mouseY);
		int i = leftPos;
		int k = topPos;
		int l = minecraft.player.experienceLevel;
		levelCost = (int)(10+scrollOffs*-10);
		menu.setCost(levelCost);
		graphics.drawCenteredString(font, Component.literal((int)levelCost+"").withStyle(l >= (int)levelCost || minecraft.player.getAbilities().instabuild ? ChatFormatting.GREEN : ChatFormatting.DARK_RED), i+150, k+40, 14737632);
		chance = (int)(5 * levelCost);
		if (menu.getSlot(1).getItem().getItem() instanceof DecryptingBookItem book) {
			chance+=book.getPower();
		}
		menu.setChance(chance);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float f, int mouseX, int mouseY) {
		int i = leftPos;
		int j = (height - imageHeight) / 2;
		graphics.blit(DECRYPTING_TABLE_LOCATION, i, j, 0, 0, imageWidth, imageHeight);
		int k = (int)(41.0F * scrollOffs);
		graphics.blit(DECRYPTING_TABLE_LOCATION, i + 119, j + 14 + k, 176, 0, 12, 15);
	}
	
	@Override
	protected void renderTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
		super.renderTooltip(graphics, mouseX, mouseY);
		int i = leftPos + 20;
		int j = height / 2 -49;
		int chance = menu.getChance();
		if (minecraft.player.getAbilities().instabuild) chance = 100;
		if (mouseX >= i && mouseX < i + 20 && mouseY >= j && mouseY < j+18) {

			graphics.renderTooltip(font, Component.translatable("decrypting.chance").withStyle(ChatFormatting.GRAY).append(Component.literal(""+chance+"%").withStyle(ChatFormatting.GREEN)), mouseX, mouseY+20);
		}
	}
	
	protected void updateButtonState() {
		boolean flag = minecraft.player.getAbilities().instabuild;
		if (flag || (menu.getSlot(0).hasItem() && menu.getSlot(2).getItem().getCount() >= 4 && chance > 0 && levelCost > 0 && minecraft.player.experienceLevel >= levelCost)) {
			button.active = true;
		} else {
			button.active = false;
		}
	}

	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		this.scrolling = false;
		if (mouseX >= (double)leftPos + 119 && mouseX < (double)(leftPos + 131) && mouseY >= (double)topPos + 14 && mouseY < (double)(topPos + 70)) {
			this.scrolling = true;
		}
		
		return super.mouseClicked(mouseX, mouseY, button);
	}

	public boolean mouseDragged(double mouseX, double mouseY, int k, double dragX, double dragY) {
		if (scrolling) {
			int i = topPos + 14;
			int j = i + 54;
			scrollOffs = ((float)mouseY - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
			scrollOffs = Mth.clamp(scrollOffs, 0.0F, 1.0F);
			return true;
		} else {
			return super.mouseDragged(mouseX, mouseY, k, dragX, dragY);
		}
	}

	public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
		float f = (float)scrollY / 10.0F;
		f = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
		f *= 10;
		f = Math.round(f);
		f /= 10;
		this.scrollOffs = f;

		return true;
	}
}