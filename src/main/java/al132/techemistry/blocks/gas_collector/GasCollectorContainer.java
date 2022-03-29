package al132.techemistry.blocks.gas_collector;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.SlotItemHandler;

public class GasCollectorContainer extends ABaseContainer {

    public GasCollectorContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.gasCollectorContainer.get(), id, level, pos, playerInv, 2);
        GasCollectorTile collectorTile = (GasCollectorTile) tile;
        addSlot(new SlotItemHandler(collectorTile.getOutput(), 0, 80, 13));
        addPlayerSlots();
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}