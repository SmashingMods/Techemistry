package al132.techemistry.blocks.steam_boiler;

import al132.alib.client.CapabilityFluidDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SteamBoilerScreen extends BaseScreen<SteamBoilerContainer> {
    public SteamBoilerScreen(SteamBoilerContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/steam_boiler_gui.png");
        displayData.add(new CapabilityFluidDisplayWrapper(8, 23, 16, 60, screenContainer::getFluid));
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float f, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(ms, f, mouseX, mouseY);
        SteamBoilerTile tile = (SteamBoilerTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(ms, Minecraft.getInstance().fontRenderer, heatStr, guiLeft + 10, guiTop + 10, 0xffffff);
        this.minecraft.textureManager.bindTexture(this.GUI);
        /*if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, tile.TICKS_PER_OPERATION);
            this.blit(78, 47, 175, 0, k, 9);
        }*/
    }
}