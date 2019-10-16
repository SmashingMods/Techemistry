package al132.techemistry.blocks.reaction_chamber;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.SlotItemHandler;

public class ReactionChamberContainer extends BaseContainer {
    public ReactionChamberContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.reactionChamberContainer, id, world, pos, playerInv, player, 6);
        ReactionChamberTile reactionTile = ((ReactionChamberTile) tile);
        addSlot(new SlotItemHandler(reactionTile.getInput(), 0, 44, 24));
        addSlot(new SlotItemHandler(reactionTile.getInput(), 1, 44, 42));
        addSlot(new SlotItemHandler(reactionTile.getInput(), 2, 44, 60));
        addSlot(new SlotItemHandler(reactionTile.getOutput(), 0, 124, 24));
        addSlot(new SlotItemHandler(reactionTile.getOutput(), 1, 124, 42));
        addSlot(new SlotItemHandler(reactionTile.getOutput(), 2, 124, 60));
        addPlayerSlots();
    }

    public IEnergyStorage getEnergy() {
        return ((ReactionChamberTile) tile).energy;
    }

    public IHeatStorage getHeat() {
        return ((ReactionChamberTile) tile).heat;
    }
}