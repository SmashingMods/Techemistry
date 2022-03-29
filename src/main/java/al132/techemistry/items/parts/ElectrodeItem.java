package al132.techemistry.items.parts;

import al132.techemistry.items.BaseItem;
import net.minecraft.world.item.Item;

public class ElectrodeItem extends BaseItem {

    public ElectrodeItem(String name) {
        super(name, new Item.Properties().durability(10));
    }
}