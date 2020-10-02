package al132.techemistry.blocks.smeltery;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SmelteryScreen extends BaseScreen<SmelteryContainer> {
    public SmelteryScreen(SmelteryContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/smeltery_gui.png");
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float f, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(ms, f, mouseX, mouseY);
        SmelteryTile tile = (SmelteryTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(ms, Minecraft.getInstance().fontRenderer, heatStr, guiLeft + 35, guiTop + 78, 0xffffff);
        this.minecraft.textureManager.bindTexture(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, tile.TICKS_PER_OPERATION);
            this.drawRightArrow(ms, guiLeft + 79, guiTop + 46, k);
        }
    }
}
