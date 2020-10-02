package al132.techemistry.blocks.calcination_chamber;

import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class CalcinationScreen extends BaseScreen<CalcinationContainer> {
    //private CalcinationContainer container;
    public CalcinationScreen(CalcinationContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/calcination_gui.png");
    }

    @Override
    public List<ITextComponent> getTooltipFromItem(ItemStack stack) {
        List<ITextComponent> strings = super.getTooltipFromItem(stack);

        if (this.container.getSlot(0).getStack() == stack) {
            ((CalcinationTile) this.container.tile).currentRecipe.ifPresent(x -> {
                strings.add(new StringTextComponent("Minimum heat: " + HeatHelper.format(x.minimumHeat, getTempType())));
            });
        }
        return strings;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float f, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(ms, f, mouseX, mouseY);
        CalcinationTile tile = (CalcinationTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(ms, Minecraft.getInstance().fontRenderer, heatStr, 35, 78, 0xffffff);
        this.minecraft.textureManager.bindTexture(this.GUI);
        if (tile.progressTicks > 0) {
            int k = this.getBarScaled(28, tile.progressTicks, CalcinationTile.TICKS_PER_OPERATION);
            this.drawRightArrow(ms, guiLeft + 78, guiTop + 54, k);
        }
    }
}
