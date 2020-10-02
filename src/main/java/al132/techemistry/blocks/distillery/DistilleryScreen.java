package al132.techemistry.blocks.distillery;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class DistilleryScreen extends BaseScreen<DistilleryContainer> {

    public DistilleryScreen(DistilleryContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/distillery_gui.png");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float f, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(ms, f, mouseX, mouseY);
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        DistilleryTile tile = (DistilleryTile) container.tile;
        drawString(ms, Minecraft.getInstance().fontRenderer, heatStr, guiLeft + 10, guiTop + 10, 0xffffff);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, DistilleryTile.TICKS_PER_OPERATION);
            this.drawRightArrow(ms, guiLeft + 81, guiTop + 47, k);
        }
    }
}
