package al132.techemistry.blocks.macerator;

import al132.alib.client.CapabilityEnergyDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public class MaceratorScreen extends BaseScreen<MaceratorContainer> {
    public MaceratorScreen(MaceratorContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/macerator_gui.png");
        displayData.add(new CapabilityEnergyDisplayWrapper(8, 23, 16, 60, getMenu().tile));
    }


    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        MaceratorTile tile = (MaceratorTile) menu.tile;
        this.minecraft.textureManager.bindForSetup(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, (int) tile.progressTicks, MaceratorTile.BASE_TICKS_PER_OPERATION);
            drawRightArrow(ps, getGuiLeft() + 78, getGuiTop() + 46, k);
        }
    }
}