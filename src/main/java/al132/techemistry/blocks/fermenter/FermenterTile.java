package al132.techemistry.blocks.fermenter;

import al132.alib.tiles.CustomStackHandler;
import al132.alib.tiles.FluidTile;
import al132.alib.tiles.GuiTile;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.BaseInventoryTile;
import al132.techemistry.blocks.HeatTile;
import al132.techemistry.blocks.gas_collector.GasCollectorTile;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.capabilities.heat.HeatStorage;
import al132.techemistry.capabilities.heat.IHeatStorage;
import al132.techemistry.items.misc.YeastItem;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

import static al132.techemistry.utils.TUtils.toStack;

public class FermenterTile extends BaseInventoryTile
        implements GuiTile, HeatTile, FluidTile {

    protected FluidTank inputTank = new FluidTank(10000) {
        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() instanceof WaterFluid;
        }
    };

    protected final LazyOptional<IFluidHandler> fluidHolder = LazyOptional.of(() -> inputTank);
    protected HeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    protected final LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);


    public final int minimumTicksPerOperation = 160;
    protected int progressTicks = 0;
    protected int totalTempThisOperation = 0;

    private Optional<FermenterRecipe> currentRecipe = Optional.empty();

    public FermenterTile(BlockPos pos, BlockState state) {
        super(Registration.FERMENTER_BE.get(), pos, state);
    }

    public void updateRecipe() {
        this.currentRecipe = FermenterRegistry.getRecipeForInput(level, getFood());
    }

    public void tickServer() {
        HeatHelper.balanceHeat(level, getBlockPos(), heat);// updateHeat(); //TODO not every tick!
        if (this.heat.getHeatStored() > YeastItem.MAX_TEMP && !getYeast().isEmpty()) {
            if (level.random.nextInt((int) (20000 / heat.getHeatStored())) == 0) getYeast().shrink(1);
        }
        if (canProcess()) process();
        setChanged(); //TODO not every tick!
        updateGUIEvery(5);
    }

    public ItemStack getYeast() {
        return this.getInput().getStackInSlot(0);
    }

    public ItemStack getFood() {
        return this.getInput().getStackInSlot(1);
    }

    public ItemStack getBottles() {
        return this.getInput().getStackInSlot(2);
    }

    private boolean canProcess() {
        return currentRecipe.isPresent()
                && this.heat.getHeatStored() > YeastItem.MIN_TEMP
                && this.heat.getHeatStored() < YeastItem.MAX_TEMP
                && getYeast().getItem() == Registration.YEAST_ITEM.get()
                && !getBottles().isEmpty()
                && this.inputTank.getFluidAmount() >= currentRecipe.get().waterAmount
                && getOutput().getStackInSlot(0).isEmpty();
    }
/*
    private ItemStack createOutput(ItemStack outputStack, int ethanol, int methanol) {
        //ItemStack outputStack = new ItemStack(outputItem);
        CompoundTag tag = new CompoundTag();
        tag.putInt("ethanol", ethanol);
        tag.putInt("methanol", methanol);
        outputStack.setTag(tag);
        return outputStack;
    }*/

    public int getTotalTempPerOperation() {
        return minimumTicksPerOperation * YeastItem.MAX_TEMP;
    }

    private void process() {
        progressTicks++;
        this.totalTempThisOperation += heat.getHeatStored();
        if (totalTempThisOperation >= getTotalTempPerOperation()) {
            double avg = totalTempThisOperation / progressTicks;
            //int methanol = 20 - (int) (20 * (YeastItem.MAX_TEMP - avg) / YeastItem.getTempRange());
            this.totalTempThisOperation = 0;
            this.progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().getResultItem().copy());//, 100 - methanol, methanol));//new ItemStack(ModItems.sugarWine));
            getYeast().shrink(1);
            getFood().shrink(1);
            getBottles().shrink(1);
            inputTank.drain(currentRecipe.get().waterAmount, FluidAction.EXECUTE);
            BlockEntity aboveTile = level.getBlockEntity(getBlockPos().above());
            if (aboveTile instanceof GasCollectorTile) {
                ((GasCollectorTile) aboveTile).getOutput().setOrIncrement(0, toStack("carbon_dioxide"));
            }
        }
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        inputTank.readFromNBT(compound.getCompound("inputTank"));
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(level, getBlockPos()));
        }
        progressTicks = compound.getInt("progressTicks");
        totalTempThisOperation = compound.getInt("totalTemp");
        updateRecipe();
        //setChanged();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inputTank", inputTank.writeToNBT(new CompoundTag()));
        compound.putDouble("heat", heat.getHeatStored());
        compound.putInt("progressTicks", progressTicks);
        compound.putInt("totalTemp", totalTempThisOperation);
    }

    /*  public void updateHeat() {
        double base = Utils.getSurroundingBlocks(Level, pos).stream().mapToDouble(x -> HeatHelper.getBlockHeat(Level, pos, x)).sum() / 6.0;
        if (base > heat.getHeatStored() + 1) {
            heat.receiveHeat(0.15f, false);
        } else if (base + 1 < heat.getHeatStored()) {
            heat.extractHeat(0.15f, false);
        }
    }*/


    @Override
    public LazyOptional<IHeatStorage> getHeat() {
        return heatHolder;
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 3) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0) return stack.getItem() == Registration.YEAST_ITEM.get();
                else if (slot == 1) return FermenterRegistry.inputHasRecipe(level, stack);
                else if (slot == 2) return stack.getItem() == Items.GLASS_BOTTLE;
                else return false;
            }

            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                updateRecipe();
            }
        };
    }

    @Override
    public LazyOptional<IFluidHandler> getFluidHandler() {
        return fluidHolder;
    }

    @Override
    public int outputSlots() {
        return 1;
    }

    @Override
    public Component getName() {
        return null;
    }
}