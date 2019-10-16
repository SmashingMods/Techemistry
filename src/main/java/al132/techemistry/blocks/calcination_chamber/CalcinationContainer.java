package al132.techemistry.blocks.calcination_chamber;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class CalcinationContainer extends BaseContainer {

    public CalcinationContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.calcinationContainer, id, world, pos, playerInv, player, 3);
        CalcinationTile calcinationTile = ((CalcinationTile) tile);
        addSlot(new SlotItemHandler(calcinationTile.getInput(), 0, 44, 51));
        addSlot(new SlotItemHandler(calcinationTile.getOutput(), 0, 124, 42));
        addSlot(new SlotItemHandler(calcinationTile.getOutput(),1,124,60));
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((CalcinationTile) tile).heat;
    }
}