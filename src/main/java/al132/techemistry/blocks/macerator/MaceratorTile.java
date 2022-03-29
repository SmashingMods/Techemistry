package al132.techemistry.blocks.macerator;

import al132.alib.tiles.CustomEnergyStorage;
import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.EnergyTile;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.items.parts.GearItem;
import al132.techemistry.utils.TUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class MaceratorTile extends BaseInventoryTile implements EnergyTile, GuiTile {

    public final static int MAX_ENERGY = 10000;
    public double progressTicks = 0;
    public final static int BASE_TICKS_PER_OPERATION = 240;
    public final static int ENERGY_PER_TICK = 100;
    private Optional<MaceratorRecipe> currentRecipe = Optional.empty();

    public MaceratorTile(BlockPos pos, BlockState state) {
        super(Registration.MACERATOR_BE.get(), pos, state);
    }

    public Optional<GearItem> getGear() {
        Item item = getGearStack().getItem();
        if (item instanceof GearItem) {
            return Optional.ofNullable((GearItem) item);
        } else return Optional.empty();
    }

    public void updateRecipe() {
        if (!getInputStack().isEmpty()) {
            currentRecipe = MaceratorRegistry.getRecipeForInput(level, getInputStack());
        }
    }

    public void tickServer() {
        if (level.isClientSide) return;
        if (canProcess()) process();
        setChanged();
        updateGUIEvery(5);

    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.progressTicks = compound.getDouble("progressTicks");
        this.energy = new EnergyStorage(MAX_ENERGY, MAX_ENERGY, MAX_ENERGY, compound.getInt("energy"));
        updateRecipe();
        //setChanged();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putDouble("progressTicks", progressTicks);
        compound.putInt("energy", this.energy.getEnergyStored());
    }

    public ItemStack getInputStack() {
        return getInput().getStackInSlot(0);
    }

    public ItemStack getGearStack() {
        return getInput().getStackInSlot(1);
    }


    public ItemStack getOutput1Stack() {
        return getOutput().getStackInSlot(0);
    }

    public ItemStack getOutput2Stack() {
        return getOutput().getStackInSlot(1);
    }


    private boolean canProcess() {
        return currentRecipe.isPresent()
                && getGear().isPresent()
                && getGear().get().material.tier >= currentRecipe.get().tier
                && energy.getEnergyStored() >= ENERGY_PER_TICK
                && !getInputStack().isEmpty()
                && (getOutput1Stack().isEmpty() || (currentRecipe.get().output.size() == 1 && ItemStack.isSame(currentRecipe.get().output.get(0).stack, getOutput1Stack())))
                && TUtils.canStack(currentRecipe.get().output2, getOutput2Stack());
    }

    private void process() {
        progressTicks += getGear().get().material.speed;
        energy.extractEnergy(ENERGY_PER_TICK, false);
        if (progressTicks >= BASE_TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().calculateOutput(getGear().get().material.efficiency));
            getInputStack().shrink(1);
            ItemStack gearStack = getGearStack();
            gearStack.hurt(1, level.random, null);//getItem().damageItem(gearStack, 1, null, null);
            if (gearStack.getDamageValue() >= gearStack.getMaxDamage()) {
                getInput().setStackInSlot(1, ItemStack.EMPTY);
            }
            updateRecipe();
        }
        //setChanged();
    }


    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 2) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0) return MaceratorRegistry.hasRecipe(level, stack);
                else return stack.getItem() instanceof GearItem;
            }


            @Override
            public void onContentsChanged(int slot) {
                if (slot == 1) progressTicks = 0;
                updateRecipe();
                //setChanged();
            }
        };
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
    public int outputSlots() {
        return 2;
    }

    @Override
    public Component getName() {
        return null;
    }
}