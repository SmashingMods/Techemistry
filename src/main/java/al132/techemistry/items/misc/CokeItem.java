package al132.techemistry.items.misc;

import al132.techemistry.items.BaseItem;
import net.minecraft.item.ItemStack;

public class CokeItem extends BaseItem {
    public CokeItem() {
        super("coke");
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 1600;
    }
}