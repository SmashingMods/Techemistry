package al132.techemistry.blocks.distillery;

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
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class DistilleryTile extends BaseInventoryTile implements HeatTile, GuiTile {//}, FluidTile {

    protected IHeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    protected LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    public Optional<DistilleryRecipe> currentRecipe = Optional.empty();

    protected int progressTicks = 0;
    public static final int TICKS_PER_OPERATION = 200;

    public DistilleryTile(BlockPos pos, BlockState state) {
        super(Registration.DISTILLERY_BE.get(), pos, state);
    }

    public void tickServer() {
        if (level.isClientSide) return;
        updateRecipe();
        if (canProcess()) process();
        HeatHelper.balanceHeat(level, getBlockPos(), heat);
        setChanged();
        updateGUIEvery(5);

    }

    public ItemStack getInputStack() {
        return getInput().getStackInSlot(0);
    }

    public ItemStack getOutput1Stack() {
        return getOutput().getStackInSlot(0);
    }

    public ItemStack getOutput2Stack() {
        return getOutput().getStackInSlot(1);
    }

    private boolean canProcess() {
        return isStructureFormed()
                && currentRecipe.isPresent()
                && this.heat.getHeatStored() >= currentRecipe.get().minimumHeat
                && TUtils.canStack(currentRecipe.get().getResultItem(), getOutput1Stack())
                && TUtils.canStack(currentRecipe.get().output2, getOutput2Stack());
    }

    private void updateRecipe() {
        this.currentRecipe = DistilleryRegistry.getRecipeForInput(level, getInputStack());
    }

    private void process() {
        progressTicks++;
        if (progressTicks >= TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().getResultItem().copy());
            getOutput().setOrIncrement(1, currentRecipe.get().output2.copy());
            getInput().getStackInSlot(0).shrink(1);
        }
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.progressTicks = compound.getInt("progressTicks");
        if (compound.contains("heat")) {
            heat = new HeatStorage(compound.getDouble("heat"));
        } else {
            heat = new HeatStorage(HeatHelper.getBiomeHeat(level, getBlockPos()));
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("progressTicks", progressTicks);
        compound.putDouble("heat", heat.getHeatStored());
    }


    public boolean isStructureFormed() {
        for (int i = 1; i <= 3; i++) {
            if (level.getBlockState(getBlockPos().above(i)).getBlock() != Registration.DISTILLERY_COLUMN_BLOCK.get()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return DistilleryRegistry.inputHasRecipe(level, stack);
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