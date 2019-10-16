package al132.techemistry.blocks.world;

import al132.techemistry.utils.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class OxideOre extends WorldBlock {
    public OxideOre() {
        super("oxide_ore", 18, 8);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        Utils.addBlockTooltip(tooltip, this);
    }
}
