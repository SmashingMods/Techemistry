package al132.techemistry.items.misc;

import al132.chemlib.ChemLib;
import al132.techemistry.items.BaseItem;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CokeItem extends BaseItem {
    public CokeItem() {
        super("coke");
    }

   /*TODO @Override
    public int getBurnTime(ItemStack itemStack) {
        return 1600;
    }
*/
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, levelIn, tooltips, flagIn);
        tooltips.add(new TextComponent(I18n.get("item.techemistry.coke.tooltip")).withStyle(ChemLib.CHEM_TOOLTIP_COLOR));
    }
}