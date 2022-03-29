package al132.techemistry.blocks.world;
import al132.techemistry.utils.TUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;


import javax.annotation.Nullable;
import java.util.List;

public class OxideOre extends WorldBlock {
    public OxideOre() {
        super(18, 8);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, getter, tooltips, flagIn);
        TUtils.addBlockTooltip(tooltips, this);
    }
}
