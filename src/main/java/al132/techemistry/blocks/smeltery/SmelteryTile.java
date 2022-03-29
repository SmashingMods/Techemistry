package al132.techemistry.blocks.smeltery;

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
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class SmelteryTile extends BaseInventoryTile
        implements Nameable, HeatTile, GuiTile {

    private Optional<SmelteryRecipe> currentRecipe = Optional.empty();
    protected IHeatStorage heat = new HeatStorage(HeatHelper.ROOM_TEMP);
    public LazyOptional<IHeatStorage> heatHolder = LazyOptional.of(() -> heat);

    protected int progressTicks = 0;

    public static final int TICKS_PER_OPERATION = 100;

    public SmelteryTile(BlockPos pos, BlockState state) {
        super(Registration.SMELTERY_BE.get(), pos, state);
    }

    public void updateRecipe() {
        if (level != null) this.currentRecipe = SmelteryRegistry.getRecipeForInput(level, getInputStack());
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
                && getInputStack().getCount() >= currentRecipe.get().inputCount//.get().getIngredients().get(0).getMatchingStacks()[0].getCount()
                && !getFluxStack().isEmpty()
                && !getFuelStack().isEmpty()
                && TUtils.canStack(currentRecipe.get().getResultItem(), getOutputStack());
    }

    private void process() {
        progressTicks++;
        level.addParticle(ParticleTypes.SMOKE, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 0, 1, 0);
        if (level.random.nextInt(10) == 0) {
            BlockEntity temp = level.getBlockEntity(getBlockPos().above());
            if (temp instanceof GasCollectorTile) {
                GasCollectorTile collectorTile = (GasCollectorTile) temp;
                ItemStack s = collectorTile.getOutputStack();
                if (s.isEmpty() || s.getItem() == currentRecipe.get().gasOutput.getItem()) {
                    collectorTile.getOutput().setOrIncrement(0, currentRecipe.get().gasOutput.copy());
                }
            }
        }
        if (progressTicks >= TICKS_PER_OPERATION) {
            progressTicks = 0;
            getOutput().setOrIncrement(0, currentRecipe.get().getResultItem().copy());
            getInputStack().shrink(currentRecipe.get().inputCount);//currentRecipe.get().getIngredients().get(0).getMatchingStacks()[0].getCount());
            getFluxStack().shrink(1);
            getFuelStack().shrink(1);
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

    public ItemStack getFuelStack() {
        return this.getInput().getStackInSlot(1);
    }

    public ItemStack getFluxStack() {
        return this.getInput().getStackInSlot(2);
    }

    public ItemStack getOutputStack() {
        return this.getOutput().getStackInSlot(0);
    }

    public ItemStack getSlagStack() {
        return this.getOutput().getStackInSlot(1);
    }

    @Override
    public CustomStackHandler initInput() {
        return new CustomStackHandler(this, 3) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0) {
                    if (SmelteryRegistry.hasRecipe(stack)) return true;
                } else if (slot == 1) {
                    if (stack.getItem() == Registration.COKE_ITEM.get()) return true;
                } else if (slot == 2) {
                    return FluxRegistry.isFlux(stack);
                }
                return false;
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