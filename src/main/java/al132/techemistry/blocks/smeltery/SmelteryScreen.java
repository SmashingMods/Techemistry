package al132.techemistry.blocks.smeltery;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SmelteryScreen extends BaseScreen<SmelteryContainer> {
    public SmelteryScreen(SmelteryContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/smeltery_gui.png");
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        SmelteryTile tile = (SmelteryTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(Minecraft.getInstance().fontRenderer, heatStr, 35, 78, 0xffffff);
        this.minecraft.textureManager.bindTexture(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, tile.TICKS_PER_OPERATION);
            this.drawRightArrow(79, 46, k);
        }
    }
}
