package al132.techemistry.capabilities.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;


public class PlayerDataDispatcher implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private PlayerData playerData = new PlayerData();

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability) {
        return capability == CapabilityPlayerData.PLAYER_DATA_CAP ? LazyOptional.of(() -> this.playerData).cast() : LazyOptional.empty();
    }
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction direction) {
        return getCapability(capability);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        this.playerData.saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.playerData.loadNBTData(nbt);
    }
}
