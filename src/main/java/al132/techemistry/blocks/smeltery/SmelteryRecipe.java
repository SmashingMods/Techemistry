package al132.techemistry.blocks.smeltery;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class SmelteryRecipe {

    public final ItemStack output;
    public final Ingredient input;
    public final ItemStack gas;
    public final double minimumHeat;

    public SmelteryRecipe(ItemStack output, ItemStack gas, Ingredient input, double minimumHeat) {
        this.output = output;
        this.input = input;
        this.gas = gas;
        this.minimumHeat = minimumHeat;
    }
}
