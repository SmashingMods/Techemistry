package al132.techemistry.blocks;

import al132.alib.client.ABaseScreen;
import al132.techemistry.Techemistry;
import al132.techemistry.capabilities.heat.HeatHelper.TempType;
import al132.techemistry.capabilities.player.CapabilityPlayerData;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BaseScreen<T extends BaseContainer> extends ABaseScreen<T> {

    public final ResourceLocation widgets = new ResourceLocation(Techemistry.data.MODID, "textures/gui/widgets.png");
    private Button tempTypeButton;//: GuiButton
    //protected TempType tempType = KELVIN;

    public BaseScreen(T screenContainer, PlayerInventory inv, ITextComponent name, String path) {
        super(Techemistry.data, screenContainer, inv, name, path);
    }

    public TempType getTempType() {
        return container.player.getCapability(CapabilityPlayerData.PLAYER_DATA_CAP).map(x -> x.tempType).orElse(TempType.KELVIN);
    }

    @Override
    protected void init() {
        super.init();
        if (this.container.tile instanceof HeatTile) {
            tempTypeButton = new Button(guiLeft, guiTop + ySize, 20, 20, "", (Button x) -> {
                container.player.getCapability(CapabilityPlayerData.PLAYER_DATA_CAP).ifPresent(data -> {
                    data.tempType = data.tempType.next();
                    x.setMessage(data.tempType.symbol);
                });
            });
            tempTypeButton.setMessage(getTempType().symbol);
            //tempTypeButton.setMessage(tempType.symbol);
            this.addButton(tempTypeButton);
        }
    }

    /*
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        if (this.container.tile instanceof HeatTile) {
            this.minecraft.textureManager.bindTexture(widgets);
            blit(0, getYSize(), 0, 0, 20, 20);
            int textWidth = font.getStringWidth("K");
            drawString(font, "K", (20 - textWidth) / 2, getYSize() + 5, Ref.TEXT_COLOR);
        }
    }*/

}
