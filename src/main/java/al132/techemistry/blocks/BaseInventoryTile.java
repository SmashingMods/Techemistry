package al132.techemistry.blocks;

import al132.alib.tiles.ABaseInventoryTile;
import al132.alib.tiles.AutomationStackHandler;
import al132.alib.tiles.CustomStackHandler;
import al132.techemistry.capabilities.heat.CapabilityHeat;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BaseInventoryTile extends ABaseInventoryTile {
    public BaseInventoryTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityHeat.HEAT_CAP && this instanceof HeatTile) {
            return ((HeatTile) this).getHeat().cast();
        } else return super.getCapability(cap, side);
    }

    @Override
    public CustomStackHandler initOutput() {
        return new CustomStackHandler(this,outputSlots()){
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return false;
            }
        };
    }

    @Override
    public AutomationStackHandler initAutomationInput(IItemHandlerModifiable handler) {
        return new AutomationStackHandler(handler) {
            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return ItemStack.EMPTY;
            }
        };
    }

    @Override
    public AutomationStackHandler initAutomationOutput(IItemHandlerModifiable handler) {
        return new AutomationStackHandler(handler);
    }

    public abstract int outputSlots();
}
