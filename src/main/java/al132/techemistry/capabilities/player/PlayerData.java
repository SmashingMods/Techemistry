package al132.techemistry.capabilities.player;

import al132.techemistry.capabilities.heat.HeatHelper;
import net.minecraft.nbt.CompoundTag;

//Peeking off https://github.com/McJtyMods/TheOneProbe/blob/1.12/src/main/java/mcjty/theoneprobe/playerdata/PlayerGotNote.java
public class PlayerData {

    public HeatHelper.TempType tempType = HeatHelper.TempType.KELVIN;

    public PlayerData() {
    }

    public void saveNBTData(CompoundTag compound) {
        compound.putInt("tempType", tempType.ordinal());
    }

    public void loadNBTData(CompoundTag compound) {
        tempType = HeatHelper.TempType.values()[compound.getInt("tempType")];
    }

    public void copyFrom(PlayerData oldStore) {
        this.tempType = oldStore.tempType;
    }
}