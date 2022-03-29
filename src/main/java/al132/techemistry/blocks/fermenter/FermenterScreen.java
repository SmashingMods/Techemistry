package al132.techemistry.blocks.fermenter;

import al132.alib.client.CapabilityFluidDisplayWrapper;
import al132.techemistry.blocks.BaseScreen;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.items.misc.YeastItem;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static al132.techemistry.Techemistry.MODID;

@OnlyIn(Dist.CLIENT)
public class FermenterScreen extends BaseScreen<FermenterContainer> {

    private FermenterTile tile;

    public FermenterScreen(FermenterContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/fermenter_gui.png");
        this.tile = (FermenterTile) screenContainer.tile;
        this.displayData.add(new CapabilityFluidDisplayWrapper(35, 23, 16, 60, tile));
    }

    @Override
    public void render(PoseStack ps, int mouseX, int mouseY, float f) {
        super.render(ps, mouseX, mouseY, f);
        String heatStr = "Heat: " + HeatHelper.format(tile.heat, getTempType());
        drawString(ps, Minecraft.getInstance().font, heatStr, getGuiLeft() + 10, getGuiTop() + 10, 0xffffff);
        //coord: 238,0 | size: 18,62
        final int barSizeX = 18;
        final int barSizeY = 62;
        RenderSystem.setShaderTexture(0, new ResourceLocation(MODID, "textures/gui/template2.png"));

        if (tile.heat.getHeatStored() > YeastItem.MAX_TEMP) {
            int pixels = 62 - (int) (Math.ceil((500.0 - YeastItem.MAX_TEMP) / 62.0));
            blit(ps, getGuiLeft() + 7, getGuiTop() + 22 + barSizeY - pixels, 0, barSizeY - pixels, barSizeX, pixels);
            RenderSystem.setShaderTexture(0, new ResourceLocation("minecraft", "textures/painting/skull_and_roses.png"));
            blit(ps, getGuiLeft() + 100, getGuiTop() + 5, 0, 0, 32, 32, 32, 32);
        } else if (tile.heat.getHeatStored() < YeastItem.MIN_TEMP) {
            int pixels = 14 - (int) (Math.ceil((YeastItem.MIN_TEMP - tile.heat.getHeatStored()) / 14.0));
            blit(ps, getGuiLeft() + 7, getGuiTop() + 22 + barSizeY - pixels, 0, barSizeY - pixels, barSizeX, pixels);
        } else {
            int pixels = 14 + 34 - (int) (34 * (YeastItem.MAX_TEMP - tile.heat.getHeatStored()) / (YeastItem.MAX_TEMP - YeastItem.MIN_TEMP));
            blit(ps, getGuiLeft() + 7, getGuiTop() + 22 + barSizeY - pixels, 0, barSizeY - pixels, barSizeX, pixels);
        }

        if (tile.totalTempThisOperation > 0) {
            int k = this.getBarScaled(28, tile.totalTempThisOperation, tile.getTotalTempPerOperation());
            this.drawRightArrow(ps, getGuiLeft() + 101, getGuiTop() + 48, k);
        }

        if (mouseX >= getGuiLeft() + 100 && mouseX < getGuiLeft() + 132) {
            if (mouseY >= getGuiTop() + 5 && mouseY < getGuiTop() + 37) {
                if (tile.heat.getHeatStored() > YeastItem.MAX_TEMP) {
                    this.renderTooltip(ps, new TextComponent("Too hot! Yeast will slowly start dying"), mouseX, mouseY);
                }
            }
        }
        this.temperatureTypeButton.renderButton(ps, mouseX, mouseY, 0.0f);

    }
}