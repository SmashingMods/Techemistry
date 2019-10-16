package al132.techemistry.capabilities.player;


import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityPlayerData {

    @CapabilityInject(PlayerData.class)
    public static Capability<PlayerData> PLAYER_DATA_CAP = null;


    public static void register(){
        CapabilityManager.INSTANCE.register(PlayerData.class, new Capability.IStorage<PlayerData>() {

            @Override
            public void readNBT(Capability<PlayerData> capability, PlayerData playerGotNote, Direction direction, INBT inbt) {
                throw new UnsupportedOperationException();
            }

            @Override
            public INBT writeNBT(Capability<PlayerData> capability, PlayerData instance, Direction side) {
                throw new UnsupportedOperationException();
            }

        }, () -> {
            throw new UnsupportedOperationException();
        });
    }

}