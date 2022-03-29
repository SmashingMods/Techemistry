package al132.techemistry.blocks.calcination_chamber;

import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.blocks.gas_collector.GasCollectorTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.utils.TUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import java.util.Optional;

public class CalcinationTile extends BaseInventoryTile
        implements Nameable, HeatTile, GuiTile {

    protected Optional<CalcinationRecipe> currentRecipe = Optional.empty();
    protected IHeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    protected int progressTicks = 0;

    public static final int TICKS_PER_OPERATION = 100;

    public CalcinationTile(BlockPos pos, BlockState state) {
        super(Registration.CALCINATION_BE.get(), pos, state);
    }

    public void updateRecipe() {
        if (level != null) this.currentRecipe = CalcinationRegistry.getRecipeForInput(level, getInputStack());
    }

    public void tickServer() {
        if (level.isClientSide) return;
        updateRecipe();
        if (canProcess()) process();
        HeatHelper.balanceHeat(level, getBlockPos(), heat);
        setChanged();
        updateGUIEvery(5);

    }

    private boolean canProcess() {
        return currentRecipe.isPresent()
                && heat.getHeatStored() >= currentRecipe.get().minimumHeat
                && getInputStack().getCount() >= currentRecipe.get().getIngredients().get(0).getItems()[0].getCount()
                && TUtils.canStack(currentRecipe.get().getResultItem(), getOutputStack())
                && TUtils.canStack(currentRecipe.get().getRecipeOutput2(), getOutput2Stack());
    }

    private void process() {
        progressTicks++;
        if (progressTicks >= TICKS_PER_OPERATION) {
            BlockEntity temp = level.getBlockEntity(getBlockPos().above());
            if (temp instanceof GasCollectorTile) {
                GasCollectorTile collectorTile = (GasCollectorTile) temp;
                ItemStack s = collectorTile.getOutputStack();
                if (s.isEmpty() || s.getItem() == currentRecipe.get().getRecipeGas().getItem()) {
                    collectorTile.getOutput().setOrIncrement(0, currentRecipe.get().getRecipeGas().copy());
                }
            }
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().getResultItem().copy());
            getOutput().setOrIncrement(1, currentRecipe.get().getRecipeOutput2().copy());
            getInput().getStackInSlot(0).shrink(currentRecipe.get().getIngredients().get(0).getItems()[0].getCount());
        }
    }

    @Override
    public void load(CompoundTag compound) {
        this.progressTicks = compound.getInt("progressTicks");
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(level, getBlockPos()));
        }
        updateRecipe();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("progressTicks", progressTicks);
        compound.putDouble("heat", heat.getHeatStored());
    }

    public ItemStack getInputStack() {
        return this.getInput().getStackInSlot(0);
    }

    public ItemStack getOutputStack() {
        return this.getOutput().getStackInSlot(0);
    }

    public ItemStack getOutput2Stack() {
        return this.getOutput().getStackInSlot(1);
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return CalcinationRegistry.hasRecipe(level, stack);
            }
        };
    }

    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public int outputSlots() {
        return 2;
    }

    @Override
    public Component getName() {
        return null;
    }
}