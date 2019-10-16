package al132.techemistry.blocks.steam_boiler;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class SteamBoilerContainer extends BaseContainer {
    public SteamBoilerContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.steamBoilerContainer, id, world, pos, playerInv, player, 0);
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((SteamBoilerTile) tile).heat;
    }

    public IFluidHandler getFluid() {
        return ((SteamBoilerTile) tile).inputTank;
    }
}