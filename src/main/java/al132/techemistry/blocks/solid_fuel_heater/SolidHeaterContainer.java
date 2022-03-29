package al132.techemistry.blocks.solid_fuel_heater;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import al132.techemistry.capabilities.heat.IHeatStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.SlotItemHandler;

public class SolidHeaterContainer extends ABaseContainer {
    public SolidHeaterContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.solidHeaterContainer.get(), id, level, pos, playerInv, 1);
        SolidHeaterTile sTile = ((SolidHeaterTile) tile);
        addSlot(new SlotItemHandler(sTile.getInput(), 0, 44, 35));
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((SolidHeaterTile) tile).heat;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}