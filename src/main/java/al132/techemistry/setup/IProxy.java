package al132.techemistry.setup;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface IProxy {
    Level getClientLevel();
    Player getClientPlayer();
}