package al132.techemistry.blocks.fermenter;

import al132.alib.client.CapabilityFluidDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.items.misc.YeastItem;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FermenterScreen extends BaseScreen<FermenterContainer> {

    public FermenterScreen(FermenterContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/fermenter_gui.png");
        this.displayData.add(new CapabilityFluidDisplayWrapper(35, 23, 16, 60, screenContainer::getFluid));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(ms, mouseX, mouseY);
        if (mouseX >= getGuiLeft() + 100 && mouseX < getGuiLeft() + 132) {
            if (mouseY >= getGuiTop() + 5 && mouseY < getGuiTop() + 37) {
                if (container.getHeat().getHeatStored() > YeastItem.MAX_TEMP) {
                    this.renderTooltip(ms, new StringTextComponent("Too hot! Yeast will slowly start dying"), mouseX, mouseY);
                }
            }
        }
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float f, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(ms, f, mouseX, mouseY);
        FermenterTile tile = (FermenterTile) container.tile;
        IHeatStorage heat = container.getHeat();
        String heatStr = "Heat: " + HeatHelper.format(heat, getTempType());
        drawString(ms, Minecraft.getInstance().fontRenderer, heatStr, guiLeft + 10, guiTop + 10, 0xffffff);
        //coord: 238,0 | size: 18,62
        int barSizeX = 18;
        int barSizeY = 62;
        this.minecraft.textureManager.bindTexture(this.GUI);
        if (heat.getHeatStored() > YeastItem.MAX_TEMP) {
            int pixels = 62 - (int) (Math.ceil((500.0 - YeastItem.MAX_TEMP) / 62.0));
            this.blit(ms, 7, 22 + barSizeY - pixels, 238, barSizeY - pixels, barSizeX, pixels);
            minecraft.textureManager.bindTexture(new ResourceLocation("minecraft", "textures/painting/skull_and_roses.png"));
            this.blit(ms, guiLeft + 100, guiTop + 5, 0, 0, 32, 32, 32, 32);
        } else if (heat.getHeatStored() < YeastItem.MIN_TEMP) {
            int pixels = 14 - (int) (Math.ceil((YeastItem.MIN_TEMP - heat.getHeatStored()) / 14.0));
            this.blit(ms, guiLeft + 7, guiTop + 22 + barSizeY - pixels, 238, barSizeY - pixels, barSizeX, pixels);
        } else {
            int pixels = 14 + 34 - (int) (34 * (YeastItem.MAX_TEMP - heat.getHeatStored()) / (YeastItem.MAX_TEMP - YeastItem.MIN_TEMP));
            this.blit(ms, guiLeft + 7, guiTop + 22 + barSizeY - pixels, 238, barSizeY - pixels, barSizeX, pixels);
        }

        if (tile.totalTempThisOperation > 0) {
            int k = this.getBarScaled(28, tile.totalTempThisOperation, tile.getTotalTempPerOperation());
            this.drawRightArrow(ms, guiLeft + 101, guiTop + 48, k);
        }
    }
}