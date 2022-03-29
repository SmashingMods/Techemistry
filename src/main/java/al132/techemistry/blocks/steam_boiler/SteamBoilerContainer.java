package al132.techemistry.blocks.steam_boiler;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import al132.techemistry.capabilities.heat.IHeatStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SteamBoilerContainer extends ABaseContainer {
    public SteamBoilerContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.steamBoilerContainer.get(), id, level, pos, playerInv, 0);
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((SteamBoilerTile) tile).heat;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}