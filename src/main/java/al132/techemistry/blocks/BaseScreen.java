package al132.techemistry.blocks;

import al132.alib.client.ABaseScreen;
import al132.alib.container.ABaseContainer;
import al132.techemistry.Techemistry;
import al132.techemistry.capabilities.heat.HeatHelper.TempType;
import al132.techemistry.capabilities.player.PlayerDataProvider;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BaseScreen<T extends ABaseContainer> extends ABaseScreen<T> {

    public final ResourceLocation techemistryWidgets = new ResourceLocation(Techemistry.MODID, "textures/gui/widgets.png");
    protected Button temperatureTypeButton;//: GuiButton
    private Inventory inv;
    //protected TempType tempType = KELVIN;

    public BaseScreen(T screenContainer, Inventory inv, Component name, String path) {
        super(Techemistry.MODID, screenContainer, inv, name, path);
        this.inv = inv;
    }

    public TempType getTempType() {
        return inv.player.getCapability(PlayerDataProvider.PLAYER_DATA).map(x -> x.tempType).orElse(TempType.KELVIN);
    }

    @Override
    protected void init() {
        super.init();

        if (this.menu.tile instanceof HeatTile) {
            temperatureTypeButton = new Button(getGuiLeft(), getGuiTop() + getYSize(), 20, 20, new TextComponent(""), (Button x) -> {
                inv.player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                    data.tempType = data.tempType.next();
                    x.setMessage(new TextComponent(data.tempType.symbol));
                });
            });
            temperatureTypeButton.setMessage(new TextComponent(getTempType().symbol));
            //tempTypeButton.setMessage(tempType.symbol);
            this.addWidget(temperatureTypeButton);
        }
    }

    /*
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        if (this.menu.tile instanceof HeatTile) {
            this.minecraft.textureManager.bindTexture(widgets);
            blit(0, getYSize(), 0, 0, 20, 20);
            int textWidth = font.getStringWidth("K");
            drawString(font, "K", (20 - textWidth) / 2, getYSize() + 5, Ref.TEXT_COLOR);
        }
    }*/

}
