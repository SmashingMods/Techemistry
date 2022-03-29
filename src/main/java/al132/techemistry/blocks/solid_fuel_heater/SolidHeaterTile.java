package al132.techemistry.blocks.solid_fuel_heater;

import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.utils.TUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SolidHeaterTile extends BaseInventoryTile implements GuiTile, HeatTile {
    public SolidHeaterTile(BlockPos pos, BlockState state) {
        super(Registration.SOLID_HEATER_BE.get(), pos, state);
    }

    protected HeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public final LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    protected int fuelTicksRemaining = 0;
    protected int currentFuelMaxTicks = 0;

    public void tickServer() {
        if (level.isClientSide) return;
        if (this.fuelTicksRemaining > 0) {
            //https://www.wolframalpha.com/input/?i=plot+%283x%5E%28-.2%29%29%5E2+from+x+%3D+0+to+x+%3D+1000
            double toReceive = Math.pow(3 * Math.pow(heat.getHeatStored(), -.2), 5);
            this.heat.receiveHeat(toReceive, false);
            this.fuelTicksRemaining--;
            if (this.fuelTicksRemaining == 0) currentFuelMaxTicks = 0;
        } else {
            ItemStack input = getInput().getStackInSlot(0);
            if (!input.isEmpty()) {
                this.fuelTicksRemaining = ForgeHooks.getBurnTime(input, null);
                this.currentFuelMaxTicks = fuelTicksRemaining;
                input.shrink(1);
            }
        }
        List<IHeatStorage> tiles = TUtils.getSurroundingHeatTiles(level, getBlockPos());
        //System.out.println("tile.size = " + tiles.size());
        for (IHeatStorage tile : tiles) {
            if (tile.getHeatStored() + 2.0 < this.heat.getHeatStored()) {
                //System.out.println(Level.getGameTime());
                tile.receiveHeat(this.heat.extractHeat(2.0, false), false);
            }
        }
        HeatHelper.balanceHeat(level, getBlockPos(), heat);
        setChanged();
        updateGUIEvery(5);

    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.fuelTicksRemaining = compound.getInt("fuelTicks");
        this.currentFuelMaxTicks = compound.getInt("currentFuelMaxTicks");
        heat = new HeatStorage(compound.getDouble("heat"));
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("fuelTicks", this.fuelTicksRemaining);
        compound.putInt("currentFuelMaxTicks", this.currentFuelMaxTicks);
        compound.putDouble("heat", heat.getHeatStored());
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (ForgeHooks.getBurnTime(stack, null) > 0) return super.isItemValid(slot, stack);
                else return false;
            }
        };
    }

    @Override
    public int outputSlots() {
        return 0;
    }

    @Override
    public Component getName() {
        return null;
    }
}