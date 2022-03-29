package al132.techemistry.blocks.reaction_chamber;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import al132.techemistry.capabilities.heat.IHeatStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.SlotItemHandler;

public class ReactionChamberContainer extends ABaseContainer {
    public ReactionChamberContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.reactionChamberContainer.get(), id, level, pos, playerInv, 6);
        ReactionChamberTile reactionTile = ((ReactionChamberTile) tile);
        addSlot(new SlotItemHandler(reactionTile.getInput(), 0, 44, 24));
        addSlot(new SlotItemHandler(reactionTile.getInput(), 1, 44, 42));
        addSlot(new SlotItemHandler(reactionTile.getInput(), 2, 44, 60));
        addSlot(new SlotItemHandler(reactionTile.getOutput(), 0, 124, 24));
        addSlot(new SlotItemHandler(reactionTile.getOutput(), 1, 124, 42));
        addSlot(new SlotItemHandler(reactionTile.getOutput(), 2, 124, 60));
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((ReactionChamberTile) tile).heat;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}