package al132.techemistry.capabilities.player;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

//https://wiki.mcjty.eu/modding/index.php?title=Tutorial_1.18_Episode_7
public class PlayerDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerData> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<>(){});
    private PlayerData playerData = null;
    private final LazyOptional<PlayerData> opt = LazyOptional.of(this::createPlayerData);

    @Nonnull
    private PlayerData createPlayerData() {
        if (playerData == null) {
            playerData = new PlayerData();
        }
        return playerData;
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability) {
        if(capability == PLAYER_DATA) return opt.cast();
        return LazyOptional.empty();
    }
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction direction) {
        return getCapability(capability);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        this.createPlayerData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.createPlayerData() .loadNBTData(nbt);
    }
}