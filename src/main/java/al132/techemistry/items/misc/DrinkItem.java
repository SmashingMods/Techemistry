package al132.techemistry.items.misc;

import al132.techemistry.items.BaseItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class DrinkItem extends BaseItem {

    public DrinkItem(String name, Food food) {
        super(name, new Item.Properties().food(food));
    }

    public DrinkItem(String name){
        super(name);
    }

    /*@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT tag = this.getShareTag(stack);
        if (tag == null) return;
        if (tag.contains("ethanol")) tooltip.add(new StringTextComponent("Ethanol: " + tag.getInt("ethanol") + "%"));
        if (tag.contains("methanol")) tooltip.add(new StringTextComponent("Methanol: " + tag.getInt("methanol") + "%"));
    }*/
}