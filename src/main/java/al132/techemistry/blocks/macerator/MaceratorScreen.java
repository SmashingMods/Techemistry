package al132.techemistry.blocks.macerator;

import al132.alib.client.CapabilityEnergyDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class MaceratorScreen extends BaseScreen<MaceratorContainer> {
    public MaceratorScreen(MaceratorContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/macerator_gui.png");
        displayData.add(new CapabilityEnergyDisplayWrapper(8, 23, 16, 60, screenContainer::getEnergy));
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float f, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(ms, f, mouseX, mouseY);
        MaceratorTile tile = (MaceratorTile) container.tile;
        this.minecraft.textureManager.bindTexture(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, (int) tile.progressTicks, MaceratorTile.BASE_TICKS_PER_OPERATION);
            drawRightArrow(ms, guiLeft + 78, guiTop + 46, k);
        }
    }
}