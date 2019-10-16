package al132.techemistry.blocks.fermenter;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class FermenterRecipe {

    public final ItemStack output;
    public final Ingredient input;
    public final int waterAmount;

    public FermenterRecipe(ItemStack output, Ingredient input, int waterAmount) {
        this.output = output;
        this.input = input;
        this.waterAmount = waterAmount;
    }
}
