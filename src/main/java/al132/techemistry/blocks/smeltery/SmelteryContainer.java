package al132.techemistry.blocks.smeltery;

import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseContainer;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class SmelteryContainer extends BaseContainer {

    public SmelteryContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
        super(Ref.smelteryContainer, id, world, pos, playerInv, player, 5);
        SmelteryTile smelteryTile = ((SmelteryTile) tile);
        addSlot(new SlotItemHandler(smelteryTile.getInput(), 0, 44, 24));
        addSlot(new SlotItemHandler(smelteryTile.getInput(), 1, 44, 42));
        addSlot(new SlotItemHandler(smelteryTile.getInput(), 2, 44, 60));
        addSlot(new SlotItemHandler(smelteryTile.getOutput(), 0, 124, 33));
        addSlot(new SlotItemHandler(smelteryTile.getOutput(), 1, 124, 51));
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((SmelteryTile) tile).heat;
    }

}