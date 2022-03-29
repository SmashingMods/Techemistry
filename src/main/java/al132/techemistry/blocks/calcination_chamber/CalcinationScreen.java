package al132.techemistry.blocks.calcination_chamber;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.List;


public class CalcinationScreen extends BaseScreen<CalcinationContainer> {
    //private CalcinationContainer container;
    public CalcinationScreen(CalcinationContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/calcination_gui.png");
    }

    @Override
    public List<Component> getTooltipFromItem(ItemStack stack) {
        List<Component> strings = super.getTooltipFromItem(stack);

        if (this.menu.getSlot(0).getItem() == stack) {
            ((CalcinationTile) this.menu.tile).currentRecipe.ifPresent(x -> {
                strings.add(new TextComponent("Minimum heat: " + HeatHelper.format(x.minimumHeat, getTempType())));
            });
        }
        return strings;
    }


    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        CalcinationTile tile = (CalcinationTile) menu.tile;
        IHeatStorage heat = tile.heat;
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(ps, Minecraft.getInstance().font, heatStr, 35, 78, 0xffffff);
        this.minecraft.textureManager.bindForSetup(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, CalcinationTile.TICKS_PER_OPERATION);
            this.drawRightArrow(ps, getGuiLeft() + 78, getGuiTop() + 54, k);
        }
        this.temperatureTypeButton.renderButton(ps, mouseX, mouseY, 0.0f);
    }
}
