package al132.techemistry.blocks.calcination_chamber;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class CalcinationRecipe {

    public final ItemStack output;
    public final ItemStack output2;
    public final Ingredient input;
    public final ItemStack gas;
    public final double minimumHeat;

    public CalcinationRecipe(ItemStack output, ItemStack output2, ItemStack gas, Ingredient input, double minimumHeat) {
        this.output = output;
        this.output2 = output2;
        if (output == null) output2 = ItemStack.EMPTY;
        this.input = input;
        this.gas = gas;
        if (gas == null) gas = ItemStack.EMPTY;
        this.minimumHeat = minimumHeat;
    }

    public CalcinationRecipe(ItemStack output, ItemStack gas, Ingredient input, double minimumHeat) {
        this(output, ItemStack.EMPTY, gas, input, minimumHeat);
    }
}
