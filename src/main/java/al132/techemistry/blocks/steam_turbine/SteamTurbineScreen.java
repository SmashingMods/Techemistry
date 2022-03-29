package al132.techemistry.blocks.steam_turbine;

import al132.alib.client.CapabilityEnergyDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public class SteamTurbineScreen extends BaseScreen<SteamTurbineContainer> {
    public SteamTurbineScreen(SteamTurbineContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/steam_turbine_gui.png");
        displayData.add(new CapabilityEnergyDisplayWrapper(8, 23, 16, 60, getMenu().tile));
    }


    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        SteamTurbineTile tile = (SteamTurbineTile) menu.tile;
        this.minecraft.textureManager.bindForSetup(this.GUI);
        /* if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, tile.TICKS_PER_OPERATION);
            this.blit(78, 47, 175, 0, k, 9);
        }*/
    }
}