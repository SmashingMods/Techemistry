package al132.techemistry.blocks.world;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.Vec3;

public class SulfurOre extends WorldBlock {
    public SulfurOre() {
        super(18, 8);
    }

    @Override
    public void popExperience(ServerLevel p_49806_, BlockPos pos, int p_49808_) {
        if (p_49806_.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && !p_49806_.restoringBlockSnapshots) {
            ExperienceOrb.award(p_49806_, Vec3.atCenterOf(pos), p_49808_);
        }
    }

}