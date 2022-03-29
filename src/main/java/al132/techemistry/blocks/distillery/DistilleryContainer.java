package al132.techemistry.blocks.distillery;

import al132.alib.container.ABaseContainer;
import al132.techemistry.Registration;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.SlotItemHandler;

public class DistilleryContainer extends ABaseContainer {
    public DistilleryContainer(int id, Level level, BlockPos pos, Inventory playerInv) {
        super(Registration.distilleryContainer.get(), id, level, pos, playerInv, 2);
        DistilleryTile distilleryTile = ((DistilleryTile) tile);
        addSlot(new SlotItemHandler(distilleryTile.getInput(), 0, 47, 44)); //input
        addSlot(new SlotItemHandler(distilleryTile.getOutput(), 0, 127, 35)); //output
        addSlot(new SlotItemHandler(distilleryTile.getOutput(), 1, 127, 53)); //output
        addPlayerSlots();
    }

    public IHeatStorage getHeat() {
        return ((DistilleryTile) tile).heat;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}

