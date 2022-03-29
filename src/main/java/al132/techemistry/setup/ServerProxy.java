package al132.techemistry.setup;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ServerProxy implements IProxy {

    @Override
    public Level getClientLevel() {
        throw new IllegalStateException("Only run on the client");
    }

    @Override
    public Player getClientPlayer() {
        throw new IllegalStateException("Only run on the client");
    }
}
