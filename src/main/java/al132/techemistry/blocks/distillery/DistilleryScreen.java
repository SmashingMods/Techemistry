package al132.techemistry.blocks.distillery;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class DistilleryScreen extends BaseScreen<DistilleryContainer> {

    public DistilleryScreen(DistilleryContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/distillery_gui.png");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        DistilleryTile tile = (DistilleryTile) container.tile;
        drawString(Minecraft.getInstance().fontRenderer, heatStr, 10, 10, 0xffffff);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, DistilleryTile.TICKS_PER_OPERATION);
            this.drawRightArrow(81, 47, k);
        }
    }
}
