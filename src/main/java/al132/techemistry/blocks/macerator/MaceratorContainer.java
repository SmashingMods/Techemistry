package al132.techemistry.blocks.macerator;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.SlotItemHandler;

public class MaceratorContainer extends BaseContainer {
    public MaceratorContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.maceratorContainer, id, world, pos, playerInv, player, 4);
        MaceratorTile mTile = ((MaceratorTile) tile);
        addSlot(new SlotItemHandler(mTile.getInput(), 0, 44, 43));
        addSlot(new SlotItemHandler(mTile.getInput(), 1, 84, 16));
        addSlot(new SlotItemHandler(mTile.getOutput(), 0, 124, 43));
        addSlot(new SlotItemHandler(mTile.getOutput(), 1, 124, 61));
        addPlayerSlots();
    }

    public IEnergyStorage getEnergy() {
        return ((MaceratorTile) tile).energy;
    }

}