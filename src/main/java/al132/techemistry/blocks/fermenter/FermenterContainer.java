package al132.techemistry.blocks.fermenter;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;

import al132.techemistry.capabilities.heat.HeatStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FermenterContainer extends ABaseContainer {

    public FermenterContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.fermenterContainer.get(), id, level, pos, playerInv, 4);
        FermenterTile fermenterTile = ((FermenterTile) tile);
        addSlot(new SlotItemHandler(fermenterTile.getInput(), 0, 70, 27)); //yeast
        addSlot(new SlotItemHandler(fermenterTile.getInput(), 1, 70, 45)); //food
        addSlot(new SlotItemHandler(fermenterTile.getInput(), 2, 70, 63)); //bottle
        addSlot(new SlotItemHandler(fermenterTile.getOutput(), 0, 144, 45)); //output
        addPlayerSlots();
    }

    public HeatStorage getHeat() {
        return ((FermenterTile) tile).heat;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}