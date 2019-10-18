package al132.techemistry.blocks.froth_flotation_chamber;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FrothFlotationContainer extends BaseContainer {

    public FrothFlotationContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.frothFlotationContainer, id, world, pos, playerInv, player, 4);
        FrothFlotationTile flotationTile = ((FrothFlotationTile) tile);
        addSlot(new SlotItemHandler(flotationTile.getInput(), 0, 73, 42));
        addSlot(new SlotItemHandler(flotationTile.getInput(), 1, 73, 60));
        addSlot(new SlotItemHandler(flotationTile.getOutput(), 0, 139, 42));
        addSlot(new SlotItemHandler(flotationTile.getOutput(), 1, 139, 60));
        addPlayerSlots();
    }
    public IEnergyStorage getEnergy() {
        return ((FrothFlotationTile) tile).energy;
    }

    public IFluidHandler getFluid() {
        return ((FrothFlotationTile) tile).inputTank;
    }
}