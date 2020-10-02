package al132.techemistry.blocks.steam_turbine;

import al132.alib.client.CapabilityEnergyDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SteamTurbineScreen extends BaseScreen<SteamTurbineContainer> {
    public SteamTurbineScreen(SteamTurbineContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/steam_turbine_gui.png");
        displayData.add(new CapabilityEnergyDisplayWrapper(8, 23, 16, 60, screenContainer::getEnergy));
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float f, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(ms, f, mouseX, mouseY);
        SteamTurbineTile tile = (SteamTurbineTile) container.tile;
        this.minecraft.textureManager.bindTexture(this.GUI);
        /* if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, tile.TICKS_PER_OPERATION);
            this.blit(78, 47, 175, 0, k, 9);
        }*/
    }
}