package al132.techemistry.blocks.electrolyzer;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.SlotItemHandler;

public class ElectrolyzerContainer extends BaseContainer {

    public ElectrolyzerContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.electrolyzerContainer, id, world, pos, playerInv, player, 7);
        ElectrolyzerTile elecTile = ((ElectrolyzerTile) tile);
        addSlot(new SlotItemHandler(elecTile.getInput(), 0, 40, 50)); //input
        addSlot(new SlotItemHandler(elecTile.getInput(), 1, 81, 50)); //secondary input
        addSlot(new SlotItemHandler(elecTile.getInput(), 2, 39, 11)); //anode
        addSlot(new SlotItemHandler(elecTile.getInput(), 3, 81, 11)); //cathode
        addSlot(new SlotItemHandler(elecTile.getOutput(), 0, 143, 34)); //output
        addSlot(new SlotItemHandler(elecTile.getOutput(), 1, 143, 52)); //output
        addSlot(new SlotItemHandler(elecTile.getOutput(), 2, 143, 70)); //output
        addPlayerSlots();
    }

    public IEnergyStorage getEnergy() {
        return ((ElectrolyzerTile) tile).energy;
    }

    public IHeatStorage getHeat() {
        return ((ElectrolyzerTile) tile).heat;
    }

}