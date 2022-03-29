package al132.techemistry.blocks.solar_heater;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


public class SolarHeaterContainer extends ABaseContainer {
    public SolarHeaterContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.solarHeaterContainer.get(), id, level, pos, playerInv, 0);
        //SolarHeaterTile sTile = ((SolarHeaterTile) tile);
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((SolarHeaterTile) tile).heat;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}