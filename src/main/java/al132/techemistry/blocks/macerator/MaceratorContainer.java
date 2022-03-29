package al132.techemistry.blocks.macerator;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.SlotItemHandler;

public class MaceratorContainer extends ABaseContainer {
    public MaceratorContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.maceratorContainer.get(), id, level, pos, playerInv, 4);
        MaceratorTile mTile = ((MaceratorTile) tile);
        addSlot(new SlotItemHandler(mTile.getInput(), 0, 44, 43));
        addSlot(new SlotItemHandler(mTile.getInput(), 1, 84, 16));
        addSlot(new SlotItemHandler(mTile.getOutput(), 0, 124, 43));
        addSlot(new SlotItemHandler(mTile.getOutput(), 1, 124, 61));
        addPlayerSlots();
    }


    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}