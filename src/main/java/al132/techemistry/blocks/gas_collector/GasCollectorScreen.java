package al132.techemistry.blocks.gas_collector;

import al132.techemistry.blocks.BaseScreen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GasCollectorScreen extends BaseScreen<GasCollectorContainer> {

    public GasCollectorScreen(GasCollectorContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, "textures/gui/gas_collector_gui.png");
    }
}