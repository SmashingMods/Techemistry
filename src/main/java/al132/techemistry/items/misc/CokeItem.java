package al132.techemistry.items.misc;

import al132.chemlib.ChemLib;
import al132.techemistry.items.BaseItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CokeItem extends BaseItem {
    public CokeItem() {
        super("coke");
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 1600;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
      tooltip.add(new StringTextComponent("item.techemistry.coke.tooltip").applyTextStyle(ChemLib.CHEM_TOOLTIP_COLOR));
    }
}