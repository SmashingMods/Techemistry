package al132.techemistry.blocks.fermenter;

import al132.alib.client.CapabilityFluidDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.items.misc.YeastItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FermenterScreen extends BaseScreen<FermenterContainer> {

    public FermenterScreen(FermenterContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/fermenter_gui.png");
        this.displayData.add(new CapabilityFluidDisplayWrapper(35, 23, 16, 60, screenContainer::getFluid));
    }

    @Override
    protected void renderHoveredToolTip(int mouseX, int mouseY) {
        super.renderHoveredToolTip(mouseX, mouseY);
        if (mouseX >= getGuiLeft() + 100 && mouseX < getGuiLeft() + 132) {
            if (mouseY >= getGuiTop() + 5 && mouseY < getGuiTop() + 37) {
                if (container.getHeat().getHeatStored() > YeastItem.MAX_TEMP) {
                    this.renderTooltip("Too hot! Yeast will slowly start dying", mouseX, mouseY);
                }
            }
        }
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        FermenterTile tile = (FermenterTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(Minecraft.getInstance().fontRenderer, heatStr, 10, 10, 0xffffff);
        //coord: 238,0 | size: 18,62
        int barSizeX = 18;
        int barSizeY = 62;
        this.minecraft.textureManager.bindTexture(this.GUI);
        if (heat.getHeatStored() > YeastItem.MAX_TEMP) {
            int pixels = 62 - (int) (Math.ceil((500.0 - YeastItem.MAX_TEMP) / 62.0));
            this.blit(7, 22 + barSizeY - pixels, 238, barSizeY - pixels, barSizeX, pixels);
            minecraft.textureManager.bindTexture(new ResourceLocation("minecraft", "textures/painting/skull_and_roses.png"));
            this.blit(100, 5, 0, 0, 32, 32, 32, 32);
        } else if (heat.getHeatStored() < YeastItem.MIN_TEMP) {
            int pixels = 14 - (int) (Math.ceil((YeastItem.MIN_TEMP - heat.getHeatStored()) / 14.0));
            this.blit(7, 22 + barSizeY - pixels, 238, barSizeY - pixels, barSizeX, pixels);
        } else {
            int pixels = 14 + 34 - (int) (34 * (YeastItem.MAX_TEMP - heat.getHeatStored()) / (YeastItem.MAX_TEMP - YeastItem.MIN_TEMP));
            this.blit(7, 22 + barSizeY - pixels, 238, barSizeY - pixels, barSizeX, pixels);
        }

        if (tile.totalTempThisOperation > 0) {
            int k = this.getBarScaled(28, tile.totalTempThisOperation, tile.getTotalTempPerOperation());
            this.drawRightArrow(101, 48, k);
        }
    }
}