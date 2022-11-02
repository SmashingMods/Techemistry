package com.smashingmods.techemistry.common.block.heatpipe;

import com.smashingmods.alchemylib.api.storage.FluidStorageHandler;
import com.smashingmods.techemistry.common.capability.Capabilities;
import com.smashingmods.techemistry.common.capability.HeatCapability;
import com.smashingmods.techemistry.common.capability.HeatHandler;
import com.smashingmods.techemistry.registration.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;

public class HeatPipeBlockEntity extends BlockEntity {

    private final HeatCapability heatHandler = new HeatHandler(1000);
    private final LazyOptional<HeatCapability> lazyHeatHandler = LazyOptional.of(() -> heatHandler);

    private final FluidStorageHandler fluidHandler = initializeFluidStorage();
    private final LazyOptional<FluidStorageHandler> lazyFluidHandler = LazyOptional.of(() -> fluidHandler);

    public HeatPipeBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegistry.HEAT_PIPE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void onDataPacket(Connection pConnection, ClientboundBlockEntityDataPacket pPacket) {
        Objects.requireNonNull(pPacket.getTag());
        this.load(pPacket.getTag());
        super.onDataPacket(pConnection, pPacket);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void tick() {

    }

    public FluidStorageHandler initializeFluidStorage() {
        return new FluidStorageHandler(1000, FluidStack.EMPTY);
    }

    public FluidStorageHandler getFluidHandler() {
        return fluidHandler;
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(Capability<T> pCapability, @Nullable Direction pDirection) {
        if (pCapability == Capabilities.HEAT_HANDLER) {
            return lazyHeatHandler.cast();
        }
        if (pCapability == ForgeCapabilities.FLUID_HANDLER) {
            return lazyFluidHandler.cast();
        }
        return super.getCapability(pCapability, pDirection);
    }

    @Override
    public void invalidateCaps() {
        lazyFluidHandler.invalidate();
        super.invalidateCaps();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
    }
}
