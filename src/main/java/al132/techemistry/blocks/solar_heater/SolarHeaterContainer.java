package al132.techemistry.blocks.solar_heater;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SolarHeaterContainer extends BaseContainer {
    public SolarHeaterContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.solarHeaterContainer, id, world, pos, playerInv, player, 0);
        //SolarHeaterTile sTile = ((SolarHeaterTile) tile);
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((SolarHeaterTile) tile).heat;
    }
}