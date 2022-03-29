package al132.techemistry.blocks.steam_turbine;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SteamTurbineContainer extends ABaseContainer {
    public SteamTurbineContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.steamTurbineContainer.get(), id, level, pos, playerInv, 0);
        addPlayerSlots();
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}