package al132.techemistry.blocks.fermenter;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FermenterContainer extends BaseContainer {

    public FermenterContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.fermenterContainer, id, world, pos, playerInv, player, 4);
        FermenterTile fermenterTile = ((FermenterTile) tile);
        addSlot(new SlotItemHandler(fermenterTile.getInput(), 0, 70, 27)); //yeast
        addSlot(new SlotItemHandler(fermenterTile.getInput(), 1, 70, 45)); //food
        addSlot(new SlotItemHandler(fermenterTile.getInput(), 2, 70, 63)); //bottle
        addSlot(new SlotItemHandler(fermenterTile.getOutput(), 0, 144, 45)); //output
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((FermenterTile) tile).heat;
    }

    public IFluidHandler getFluid() {
        return ((FermenterTile) tile).inputTank;
    }
}