package al132.techemistry.blocks.solid_fuel_heater;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class SolidHeaterContainer extends BaseContainer {
    public SolidHeaterContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.solidHeaterContainer, id, world, pos, playerInv, player, 1);
        SolidHeaterTile sTile = ((SolidHeaterTile) tile);
        addSlot(new SlotItemHandler(sTile.getInput(), 0, 44, 35));
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((SolidHeaterTile) tile).heat;
    }
}