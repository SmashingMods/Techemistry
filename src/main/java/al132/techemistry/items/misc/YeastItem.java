package al132.techemistry.items.misc;

import al132.techemistry.items.BaseItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class YeastItem extends BaseItem {

    public static final int MAX_TEMP = 315;
    public static final int MIN_TEMP = 278;

    public static int getTempRange() {
        return MAX_TEMP - MIN_TEMP;
    }

    public YeastItem() {
        super("yeast");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(I18n.format("item.techemistry.yeast.tooltip")));
        tooltip.add(new StringTextComponent(I18n.format("item.techemistry.yeast.tooltip2",MAX_TEMP)));
    }
}