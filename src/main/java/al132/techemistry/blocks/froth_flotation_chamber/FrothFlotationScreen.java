package al132.techemistry.blocks.froth_flotation_chamber;

import al132.alib.client.CapabilityEnergyDisplayWrapper;
import al132.alib.client.CapabilityFluidDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class FrothFlotationScreen extends BaseScreen<FrothFlotationContainer> {
    public FrothFlotationScreen(FrothFlotationContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/froth_flotation_gui.png");
        displayData.add(new CapabilityEnergyDisplayWrapper(8, 23, 16, 60, screenContainer::getEnergy));
        this.displayData.add(new CapabilityFluidDisplayWrapper(33, 23, 16, 60, screenContainer::getFluid));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        FrothFlotationTile tile = (FrothFlotationTile) container.tile;
        this.minecraft.textureManager.bindTexture(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, FrothFlotationTile.TICKS_PER_OPERATION);
            this.drawRightArrow(78, 54, k);
        }
    }
}
