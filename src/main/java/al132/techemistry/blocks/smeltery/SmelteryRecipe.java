package al132.techemistry.blocks.smeltery;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class SmelteryRecipe extends ProcessingRecipe {

    public final double minimumHeat;
    public final ItemStack gasOutput;
    public final int inputCount;

    public SmelteryRecipe(ResourceLocation id, String group, Ingredient input, int inputCount,
                          ItemStack output, ItemStack gasOutput, double minimumHeat) {
        super(RecipeTypes.SMELTERY, id, group, input, output);

        this.minimumHeat = minimumHeat;
        this.gasOutput = gasOutput;
        this.inputCount = inputCount;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return Ref.SMELTERY_SERIALIZER;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> temp = NonNullList.create();
        temp.add(this.input1);
        return temp;
    }
}
