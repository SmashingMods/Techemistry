package al132.techemistry.blocks.solar_heater;

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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.List;

public class SolarHeaterTile extends BaseInventoryTile implements GuiTile, HeatTile {
    public SolarHeaterTile(BlockPos pos, BlockState state) {
        super(Registration.SOLAR_HEATER_BE.get(), pos, state);
    }

    protected HeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public final LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    protected int fuelTicksRemaining = 0;
    protected int currentFuelMaxTicks = 0;


    public void tickServer() {
        if (level.getGameTime() % 20 == 0) {
            if (canGenerateHeat()) generateHeat();
            List<IHeatStorage> tiles = TUtils.getSurroundingHeatTiles(level, getBlockPos());
            for (IHeatStorage tile : tiles) {
                if (tile.getHeatStored() + 2.0 < this.heat.getHeatStored()) {
                    tile.receiveHeat(this.heat.extractHeat(2.0, false), false);
                }
            }
            HeatHelper.balanceHeat(level, getBlockPos(), heat);
            setChanged();
        }
        updateGUIEvery(5);

    }

    public boolean canGenerateHeat() {
        return level.isDay() && level.canSeeSky(getBlockPos().above()) && heat.getHeatStored() < 400;
    }

    public void generateHeat() {
        //https://www.wolframalpha.com/input/?i=%28-.25x%5E1.5+%2B+2000%29%2F1000+from+x+%3D+0+to+x+%3D+450
        double amount = (2000 - Math.pow(.25 * heat.getHeatStored(), 1.5)) / 1000;
        if (amount > 0) heat.receiveHeat(amount, false);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        heat = new HeatStorage(compound.getDouble("heat"));
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putDouble("heat", heat.getHeatStored());
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 0);
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