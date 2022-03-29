package al132.techemistry.items.misc;

import al132.techemistry.items.BaseItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;


public class AppleSauceItem extends BaseItem {
    public static final FoodProperties APPLE_SAUCE = new FoodProperties.Builder().nutrition(5).saturationMod(0.5f).build();

    public AppleSauceItem() {
        super("apple_sauce", new Item.Properties().food(APPLE_SAUCE));
    }
}