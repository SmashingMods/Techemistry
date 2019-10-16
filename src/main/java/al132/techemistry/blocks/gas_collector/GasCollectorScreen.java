package al132.techemistry.blocks.gas_collector;

import al132.techemistry.blocks.BaseScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GasCollectorScreen extends BaseScreen<GasCollectorContainer> {

    public GasCollectorScreen(GasCollectorContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name, "textures/gui/gas_collector_gui.png");
    }
}