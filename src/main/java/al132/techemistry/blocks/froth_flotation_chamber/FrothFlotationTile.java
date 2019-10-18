package al132.techemistry.blocks.froth_flotation_chamber;

import al132.alib.tiles.*;
import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.utils.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class FrothFlotationTile extends BaseInventoryTile
        implements INamedContainerProvider, ITickableTileEntity, FluidTile, GuiTile, EnergyTile {

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

    public FrothFlotationTile() {
        super(Ref.frothFlotationTile);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInv, PlayerEntity player) {
        return new FrothFlotationContainer(i, world, pos, playerInv, player);
    }

    public void updateRecipe() {
        this.currentRecipe = FrothFlotationRegistry.getRecipeForInput(getInputStack());
    }

    @Override
    public void tick() {
        if (world.isRemote) return;
        updateRecipe();
        if (canProcess()) process();
        markDirtyGUI();
    }

    private boolean canProcess() {
        return currentRecipe.isPresent()
                && energy.getEnergyStored() >= ENERGY_PER_TICK
                && inputTank.getFluidAmount() >= currentRecipe.get().water
                && Utils.canStack(currentRecipe.get().output, getOutputStack())
                && Utils.canStack(currentRecipe.get().output2, getOutput2Stack())
                && Utils.isQuantityAdequate(getInputStack(),currentRecipe.get().input)
                && Utils.isQuantityAdequate(getInputStack(),currentRecipe.get().input2);
    }

    private void process() {
        progressTicks++;
        energy.extractEnergy(ENERGY_PER_TICK, false);
        if (progressTicks >= TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().output.copy());
            getOutput().setOrIncrement(1, currentRecipe.get().output2.copy());
            getInputStack().shrink(currentRecipe.get().input.getMatchingStacks()[0].getCount());
            getInputAdditive().shrink(currentRecipe.get().input.getMatchingStacks()[0].getCount());

        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.progressTicks = compound.getInt("progressTicks");
        inputTank.readFromNBT(compound.getCompound("inputTank"));
        updateRecipe();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inputTank", inputTank.writeToNBT(new CompoundNBT()));
        compound.putInt("progressTicks", progressTicks);
        return super.write(compound);
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
        return new CustomEnergyStorage(MAX_ENERGY) {
            @Override
            public void onEnergyChanged() {
                markDirtyGUI();
            }
        };
    }

    @Override
    public IEnergyStorage getEnergy() {
        return energy;
    }

}