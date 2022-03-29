package al132.techemistry.blocks.gas_collector;


import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class CollectorRecipe {

    public Ingredient input;
    public ItemStack output;

    public CollectorRecipe(Ingredient input, ItemStack output) {
        this.input = input;
        this.output = output;
    }
}
