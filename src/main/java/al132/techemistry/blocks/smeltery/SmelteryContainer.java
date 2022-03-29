package al132.techemistry.blocks.smeltery;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import al132.techemistry.capabilities.heat.IHeatStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.SlotItemHandler;

public class SmelteryContainer extends ABaseContainer {

    public SmelteryContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.smelteryContainer.get(), id, level, pos, playerInv, 5);
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

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}