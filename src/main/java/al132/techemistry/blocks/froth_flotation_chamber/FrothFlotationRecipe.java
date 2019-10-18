package al132.techemistry.blocks.froth_flotation_chamber;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class FrothFlotationRecipe {

    public final ItemStack output;
    public final ItemStack output2;
    public final Ingredient input;
    public final Ingredient input2;
    public final int water;

    public FrothFlotationRecipe(ItemStack output, ItemStack output2, Ingredient input, Ingredient input2, int water) {
        this.output = output;
        this.output2 = output2;
        this.input = input;
        this.input2 = input2;
        this.water = water;
    }

    public FrothFlotationRecipe(ItemStack output, ItemStack output2, Ingredient input, int water) {
        this(output, output2, input, Ingredient.EMPTY, water);
    }
}
