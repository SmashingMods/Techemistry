package al132.techemistry.blocks.steam_boiler;

import al132.alib.client.CapabilityFluidDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public class SteamBoilerScreen extends BaseScreen<SteamBoilerContainer> {

    private SteamBoilerTile tile;
    private static String path = "textures/gui/steam_boiler_gui.png";

    public SteamBoilerScreen(SteamBoilerContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, path);
        this.tile = (SteamBoilerTile) getMenu().tile;
        displayData.add(new CapabilityFluidDisplayWrapper(8, 23, 16, 60, tile));
    }

    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        IHeatStorage heat = menu.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(ps, Minecraft.getInstance().font, heatStr, getGuiLeft() + 10, getGuiTop() + 10, 0xffffff);

        this.temperatureTypeButton.renderButton(ps, mouseX, mouseY, 0.0f);
    }
}