package al132.techemistry.blocks.smeltery;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public class SmelteryScreen extends BaseScreen<SmelteryContainer> {
    public SmelteryScreen(SmelteryContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/smeltery_gui.png");
    }


    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        SmelteryTile tile = (SmelteryTile) menu.tile;
        IHeatStorage heat = menu.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(ps, Minecraft.getInstance().font, heatStr, getGuiLeft() + 35, getGuiTop() + 78, 0xffffff);
        this.minecraft.textureManager.bindForSetup(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, tile.TICKS_PER_OPERATION);
            this.drawRightArrow(ps, getGuiLeft() + 79, getGuiTop() + 46, k);
        }
        this.temperatureTypeButton.renderButton(ps, mouseX, mouseY, 0.0f);
    }
}
