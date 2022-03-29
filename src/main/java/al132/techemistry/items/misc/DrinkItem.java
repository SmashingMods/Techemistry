package al132.techemistry.items.misc;

import al132.techemistry.items.BaseItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class DrinkItem extends BaseItem {

    public DrinkItem(String name, FoodProperties food) {
        super(name, new Item.Properties().food(food));
    }

    public DrinkItem(String name){
        super(name);
    }

    /*@Override
    public void addInformation(ItemStack stack, @Nullable Level levelIn, List<Component> tooltip, ITooltipFlag flagIn) {
        CompoundTag tag = this.getShareTag(stack);
        if (tag == null) return;
        if (tag.contains("ethanol")) tooltip.add(new TextComponent("Ethanol: " + tag.getInt("ethanol") + "%"));
        if (tag.contains("methanol")) tooltip.add(new TextComponent("Methanol: " + tag.getInt("methanol") + "%"));
    }*/
}