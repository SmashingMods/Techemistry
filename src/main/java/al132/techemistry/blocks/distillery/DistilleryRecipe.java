package al132.techemistry.blocks.distillery;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class DistilleryRecipe {

    public Ingredient input;
    public ItemStack output;
    public ItemStack output2;
    public double minimumHeat;

    public DistilleryRecipe(Ingredient input, ItemStack output, double minimumTemp) {
        this(input, output, ItemStack.EMPTY, minimumTemp);
    }

    public DistilleryRecipe(Ingredient input, ItemStack output, ItemStack output2, double minimumHeat) {
        this.input = input;
        this.output = output;
        this.output2 = output2;
        this.minimumHeat = minimumHeat;
    }
}
