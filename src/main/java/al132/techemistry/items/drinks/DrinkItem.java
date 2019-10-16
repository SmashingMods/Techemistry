package al132.techemistry.items.drinks;

import al132.techemistry.items.BaseItem;

public abstract class DrinkItem extends BaseItem {

    public DrinkItem(String name) {
        super(name);
    }

    /*
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT tag = this.getShareTag(stack);
        if (tag == null) return;
        if (tag.contains("ethanol")) tooltip.add(new StringTextComponent("Ethanol: " + tag.getInt("ethanol") + "%"));
        if (tag.contains("methanol")) tooltip.add(new StringTextComponent("Methanol: " + tag.getInt("methanol") + "%"));
    }*/
}
