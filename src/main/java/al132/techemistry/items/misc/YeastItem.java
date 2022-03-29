package al132.techemistry.items.misc;

import al132.techemistry.items.BaseItem;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

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
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, levelIn, tooltips, flagIn);
        //tooltips.add(new TextComponent(I18n.get("item.techemistry.yeast.tooltip")));
        tooltips.add(new TextComponent(I18n.get("item.techemistry.yeast.tooltip2", MAX_TEMP)));
    }
}