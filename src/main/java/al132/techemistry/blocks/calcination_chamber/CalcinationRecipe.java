package al132.techemistry.blocks.calcination_chamber;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;

public class CalcinationRecipe extends ProcessingRecipe {

    private ItemStack output2;
    private ItemStack gas;
    public final double minimumHeat;

    public CalcinationRecipe(ResourceLocation id, String group, Ingredient input, double minimumHeat, ItemStack output, ItemStack output2, ItemStack gas) {
        super(RecipeTypes.CALCINATION_CHAMBER, id, group, input, output);
        if (output2 == null) output2 = ItemStack.EMPTY;
        else this.output2 = output2;
        if (gas == null) gas = ItemStack.EMPTY;
        else this.gas = gas;
        this.minimumHeat = minimumHeat;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return Ref.CALCINATION_SERIALIZER;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> temp = NonNullList.create();
        temp.add(this.input1);
        return temp;
    }

    public ItemStack getRecipeOutput2() {
        return this.output2;
    }

    public ItemStack getRecipeGas() {
        return this.gas;
    }

    @Override
    public String toString() {
        return "Input1: " + Arrays.toString(input1.getMatchingStacks()) + '\n' +
                "Output1: " + output1 + '\n' +
                "Output2: " + output2 + '\n' +
                "Gas: " + gas + '\n' +
                "Minimum Heat: " + minimumHeat;
    }

    /*
    public CalcinationRecipe(ItemStack output, ItemStack gas, Ingredient input, double minimumHeat) {
        this(output, ItemStack.EMPTY, gas, input, minimumHeat);
    }
     */
}
