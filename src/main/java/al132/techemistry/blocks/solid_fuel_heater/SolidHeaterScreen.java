package al132.techemistry.blocks.solid_fuel_heater;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SolidHeaterScreen extends BaseScreen<SolidHeaterContainer> {
    public SolidHeaterScreen(SolidHeaterContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/solid_fuel_heater_gui.png");
        //displayData.add(new CapabilityEnergyDisplayWrapper(8, 23, 16, 60, ((SolidHeaterContainer) screenContainer.tile).getEnergy()));
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        SolidHeaterTile tile = (SolidHeaterTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(Minecraft.getInstance().fontRenderer, heatStr, 10, 10, 0xffffff);
        if (tile.fuelTicksRemaining > 0) {
            int k = this.getBarScaled(28, tile.currentFuelMaxTicks - tile.fuelTicksRemaining, tile.currentFuelMaxTicks);
            this.drawRightArrow(78, 38, k);
        }
    }
}