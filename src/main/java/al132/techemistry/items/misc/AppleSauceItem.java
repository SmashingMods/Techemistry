package al132.techemistry.items.misc;

import al132.techemistry.items.BaseItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class AppleSauceItem extends BaseItem {
    public static final Food APPLE_SAUCE = new Food.Builder().hunger(5).saturation(0.5f).build();

    public AppleSauceItem() {
        super("apple_sauce", new Item.Properties().food(APPLE_SAUCE));
    }
}