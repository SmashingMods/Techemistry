package al132.techemistry.blocks.calcination_chamber;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class CalcinationScreen extends BaseScreen<CalcinationContainer> {
    //private CalcinationContainer container;
    public CalcinationScreen(CalcinationContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/calcination_gui.png");
    }

    @Override
    public List<String> getTooltipFromItem(ItemStack stack) {
        List<String> strings = super.getTooltipFromItem(stack);

        if (this.container.getSlot(0).getStack() == stack) {
            ((CalcinationTile) this.container.tile).currentRecipe.ifPresent(x -> {
                strings.add("Minimum heat: " + HeatHelper.format(x.minimumHeat, getTempType()));
            });
        }
        return strings;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        CalcinationTile tile = (CalcinationTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(Minecraft.getInstance().fontRenderer, heatStr, 35, 78, 0xffffff);
        this.minecraft.textureManager.bindTexture(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, CalcinationTile.TICKS_PER_OPERATION);
            this.drawRightArrow(78, 54, k);
        }
    }
}
