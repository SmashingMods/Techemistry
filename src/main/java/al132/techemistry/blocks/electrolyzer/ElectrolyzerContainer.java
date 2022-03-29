package al132.techemistry.blocks.electrolyzer;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import al132.techemistry.capabilities.heat.IHeatStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.SlotItemHandler;

public class ElectrolyzerContainer extends ABaseContainer {

    public ElectrolyzerContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.electrolyzerContainer.get(), id, level, pos, playerInv, 7);
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

    public IHeatStorage getHeat() {
        return ((ElectrolyzerTile) tile).heat;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}