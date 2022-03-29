package al132.techemistry.blocks.calcination_chamber;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.SlotItemHandler;

public class CalcinationContainer extends ABaseContainer {

    public CalcinationContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.calcinationContainer.get(), id, level, pos, playerInv, 3);
        CalcinationTile calcinationTile = ((CalcinationTile) tile);
        addSlot(new SlotItemHandler(calcinationTile.getInput(), 0, 44, 51));
        addSlot(new SlotItemHandler(calcinationTile.getOutput(), 0, 124, 42));
        addSlot(new SlotItemHandler(calcinationTile.getOutput(),1,124,60));
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((CalcinationTile) tile).heat;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}