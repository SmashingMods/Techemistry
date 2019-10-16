package al132.techemistry.blocks.steam_boiler;

import al132.alib.client.CapabilityFluidDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SteamBoilerScreen extends BaseScreen<SteamBoilerContainer> {
    public SteamBoilerScreen(SteamBoilerContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super( screenContainer, inv, name, "textures/gui/steam_boiler_gui.png");
        displayData.add(new CapabilityFluidDisplayWrapper(8, 23, 16, 60, screenContainer::getFluid));
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        SteamBoilerTile tile = (SteamBoilerTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(Minecraft.getInstance().fontRenderer, heatStr, 10, 10, 0xffffff);
        this.minecraft.textureManager.bindTexture(this.GUI);
        /*if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, tile.TICKS_PER_OPERATION);
            this.blit(78, 47, 175, 0, k, 9);
        }*/
    }
}