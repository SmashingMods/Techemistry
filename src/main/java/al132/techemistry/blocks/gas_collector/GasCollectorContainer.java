package al132.techemistry.blocks.gas_collector;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class GasCollectorContainer extends BaseContainer {

    public GasCollectorContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.gasCollectorContainer, id, world, pos, playerInv, player, 2);
        GasCollectorTile collectorTile = (GasCollectorTile) tile;
        addSlot(new SlotItemHandler(collectorTile.getOutput(), 0, 80, 13));
        addPlayerSlots();
    }
}