package al132.techemistry.blocks.froth_flotation_chamber;

import al132.alib.tiles.*;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.utils.TUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class FrothFlotationTile extends BaseInventoryTile
        implements Nameable, FluidTile, GuiTile, EnergyTile {

    protected Optional<FrothFlotationRecipe> currentRecipe = Optional.empty();
    protected FluidTank inputTank = new FluidTank(10000) {
        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() instanceof WaterFluid;
        }
    };
    protected final LazyOptional<IFluidHandler> fluidHolder = LazyOptional.of(() -> inputTank);
    public final static int MAX_ENERGY = 10000;
    public int progressTicks = 0;
    public final static int ENERGY_PER_TICK = 0;
    public static final int TICKS_PER_OPERATION = 100;

    public FrothFlotationTile(BlockPos pos, BlockState state) {
        super(Registration.FROTH_FLOTATION_BE.get(), pos, state);
    }

    public void updateRecipe() {
        this.currentRecipe = FrothFlotationRegistry.getRecipeForInput(level, getInputStack());
    }

    public void tickServer() {
        if (level.isClientSide) return;
        updateRecipe();
        if (canProcess()) process();
        setChanged();
        updateGUIEvery(5);

    }

    private boolean canProcess() {
        return currentRecipe.isPresent()
                && energy.getEnergyStored() >= ENERGY_PER_TICK
                && inputTank.getFluidAmount() >= currentRecipe.get().water
                && TUtils.canStack(currentRecipe.get().output, getOutputStack())
                && TUtils.canStack(currentRecipe.get().output2, getOutput2Stack())
                && TUtils.isQuantityAdequate(getInputStack(), currentRecipe.get().input)
                && TUtils.isQuantityAdequate(getInputStack(), currentRecipe.get().input2);
    }

    private void process() {
        progressTicks++;
        energy.extractEnergy(ENERGY_PER_TICK, false);
        if (progressTicks >= TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().output.copy());
            getOutput().setOrIncrement(1, currentRecipe.get().output2.copy());
            getInputStack().shrink(currentRecipe.get().input.getItems()[0].getCount());
            getInputAdditive().shrink(currentRecipe.get().input.getItems()[0].getCount());

        }
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.progressTicks = compound.getInt("progressTicks");
        inputTank.readFromNBT(compound.getCompound("inputTank"));
        updateRecipe();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inputTank", inputTank.writeToNBT(new CompoundTag()));
        compound.putInt("progressTicks", progressTicks);
    }

    public ItemStack getInputStack() {
        return this.getInput().getStackInSlot(0);
    }

    public ItemStack getInputAdditive() {
        return this.getInput().getStackInSlot(1);
    }

    public ItemStack getOutputStack() {
        return this.getOutput().getStackInSlot(0);
    }

    public ItemStack getOutput2Stack() {
        return this.getOutput().getStackInSlot(1);
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 2) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                //TODO
                return true;//FrothFlotationRegistry.hasRecipe(stack);
            }
        };
    }

    @Override
    public int outputSlots() {
        return 2;
    }

    @Override
    public LazyOptional<IFluidHandler> getFluidHandler() {
        return fluidHolder;
    }


    @Override
    public IEnergyStorage initEnergy() {
        return new CustomEnergyStorage(MAX_ENERGY);
    }

    @Override
    public IEnergyStorage getEnergy() {
        return energy;
    }

    @Override
    public Component getName() {
        return null;
    }
}