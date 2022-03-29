package al132.techemistry.blocks.distillery;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public class DistilleryScreen extends BaseScreen<DistilleryContainer> {

    public DistilleryScreen(DistilleryContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/distillery_gui.png");
    }

    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        IHeatStorage heat = menu.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        DistilleryTile tile = (DistilleryTile) menu.tile;
        drawString(ps, Minecraft.getInstance().font, heatStr, getGuiLeft() + 10, getGuiTop() + 10, 0xffffff);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, DistilleryTile.TICKS_PER_OPERATION);
            this.drawRightArrow(ps, getGuiLeft() + 81, getGuiTop() + 47, k);
        }
        this.temperatureTypeButton.renderButton(ps, mouseX, mouseY, 0.0f);
    }
}
