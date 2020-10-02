package al132.techemistry.blocks.distillery;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class DistilleryRecipe extends ProcessingRecipe {

    public ItemStack output2;
    public double minimumHeat;

    public DistilleryRecipe(ResourceLocation id, String group, Ingredient input, double minimumHeat, ItemStack output, ItemStack output2) {
        super(RecipeTypes.DISTILLERY, id, group, input, output);
        this.output2 = output2;
        this.minimumHeat = minimumHeat;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return Ref.DISTILLERY_SERIALIZER;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> temp = NonNullList.create();
        temp.add(this.input1);
        return temp;
    }

    public ItemStack getRecipeOutput2() {
        return output2;
    }
}
