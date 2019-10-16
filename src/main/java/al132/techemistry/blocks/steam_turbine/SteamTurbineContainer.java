package al132.techemistry.blocks.steam_turbine;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

public class SteamTurbineContainer extends BaseContainer {
    public SteamTurbineContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.steamTurbineContainer, id, world, pos, playerInv, player, 0);
        addPlayerSlots();
    }

    public IEnergyStorage getEnergy() {
        return ((SteamTurbineTile) tile).energy;
    }

}