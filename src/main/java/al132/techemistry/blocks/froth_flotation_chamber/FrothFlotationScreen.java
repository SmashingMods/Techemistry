package al132.techemistry.blocks.froth_flotation_chamber;

import al132.alib.client.CapabilityEnergyDisplayWrapper;
import al132.alib.client.CapabilityFluidDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public class FrothFlotationScreen extends BaseScreen<FrothFlotationContainer> {
    public FrothFlotationScreen(FrothFlotationContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/froth_flotation_gui.png");
        displayData.add(new CapabilityEnergyDisplayWrapper(8, 23, 16, 60, getMenu().tile));
        this.displayData.add(new CapabilityFluidDisplayWrapper(33, 23, 16, 60, getMenu().tile));
    }

    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        FrothFlotationTile tile = (FrothFlotationTile) menu.tile;
        this.minecraft.textureManager.bindForSetup(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, FrothFlotationTile.TICKS_PER_OPERATION);
            this.drawRightArrow(ps, getGuiLeft() + 98, getGuiTop() + 54, k);
        }
        this.temperatureTypeButton.renderButton(ps, mouseX, mouseY, 0.0f);
    }
}
