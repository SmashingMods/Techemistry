package al132.techemistry.blocks.distillery;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class DistilleryContainer extends BaseContainer {
    public DistilleryContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.distilleryContainer, id, world, pos, playerInv, player, 2);
        DistilleryTile distilleryTile = ((DistilleryTile) tile);
        addSlot(new SlotItemHandler(distilleryTile.getInput(), 0, 47, 44)); //input
        addSlot(new SlotItemHandler(distilleryTile.getOutput(), 0, 127, 35)); //output
        addSlot(new SlotItemHandler(distilleryTile.getOutput(), 1, 127, 53)); //output
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((DistilleryTile) tile).heat;
    }

}

