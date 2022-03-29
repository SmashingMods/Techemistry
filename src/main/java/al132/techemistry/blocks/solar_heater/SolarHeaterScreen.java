package al132.techemistry.blocks.solar_heater;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public class SolarHeaterScreen extends BaseScreen<SolarHeaterContainer> {
    public SolarHeaterScreen(SolarHeaterContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/solar_heater_gui.png");
        //displayData.add(new CapabilityEnergyDisplayWrapper(8, 23, 16, 60, ((SolidHeaterContainer) screenmenu.tile).getEnergy()));
    }


    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        SolarHeaterTile tile = (SolarHeaterTile) menu.tile;
        IHeatStorage heat = menu.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(ps, Minecraft.getInstance().font, heatStr, getGuiLeft() + 10, getGuiTop() + 10, 0xffffff);
       /* if (tile.fuelTicksRemaining > 0) {
            int k = this.getBarScaled(28, tile.currentFuelMaxTicks - tile.fuelTicksRemaining, tile.currentFuelMaxTicks);
            this.drawRightArrow(78, 38, k);
        }*/
        this.temperatureTypeButton.renderButton(ps, mouseX, mouseY, 0.0f);
    }
}