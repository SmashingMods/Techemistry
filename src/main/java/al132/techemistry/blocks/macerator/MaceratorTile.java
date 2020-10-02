package al132.techemistry.blocks.macerator;

import al132.alib.tiles.CustomEnergyStorage;
import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.EnergyTile;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.items.parts.GearItem;
import al132.techemistry.utils.TUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class MaceratorTile extends BaseInventoryTile implements ITickableTileEntity, EnergyTile, GuiTile {

    public final static int MAX_ENERGY = 10000;
    public double progressTicks = 0;
    public final static int BASE_TICKS_PER_OPERATION = 240;
    public final static int ENERGY_PER_TICK = 100;
    private Optional<MaceratorRecipe> currentRecipe = Optional.empty();

    public MaceratorTile() {
        super(Ref.maceratorTile);
    }

    public Optional<GearItem> getGear() {
        Item item = getGearStack().getItem();
        if (item instanceof GearItem) {
            return Optional.ofNullable((GearItem) item.getItem());
        } else return Optional.empty();
    }

    public void updateRecipe() {
        if (!getInputStack().isEmpty()) {
            currentRecipe = MaceratorRegistry.getRecipeForInput(world, getInputStack());
        }
    }

    @Override
    public void tick() {
        if (world.isRemote) return;
        if (canProcess()) process();
        markDirtyGUI();
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        this.progressTicks = compound.getDouble("progressTicks");
        this.energy = new EnergyStorage(MAX_ENERGY, MAX_ENERGY, MAX_ENERGY, compound.getInt("energy"));
        updateRecipe();
        //markDirtyGUI();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putDouble("progressTicks", progressTicks);
        compound.putInt("energy", this.energy.getEnergyStored());
        return super.write(compound);
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
                && (getOutput1Stack().isEmpty() || (currentRecipe.get().output.size() == 1 && ItemStack.areItemsEqual(currentRecipe.get().output.get(0).stack, getOutput1Stack())))
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
            gearStack.attemptDamageItem(1, world.rand, null);//getItem().damageItem(gearStack, 1, null, null);
            if (gearStack.getDamage() >= gearStack.getMaxDamage()) {
                getInput().setStackInSlot(1, ItemStack.EMPTY);
            }
            updateRecipe();
        }
        //markDirtyGUI();
    }


    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 2) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0) return MaceratorRegistry.hasRecipe(world, stack);
                else return stack.getItem() instanceof GearItem;
            }


            @Override
            public void onContentsChanged(int slot) {
                if (slot == 1) progressTicks = 0;
                updateRecipe();
                //markDirtyGUI();
            }
        };
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInv, PlayerEntity player) {
        return new MaceratorContainer(i, world, pos, playerInv, player);
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
}