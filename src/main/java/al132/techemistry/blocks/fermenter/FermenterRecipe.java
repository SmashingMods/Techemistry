package al132.techemistry.blocks.fermenter;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class FermenterRecipe extends ProcessingRecipe {


    public final int waterAmount;

    public FermenterRecipe(ResourceLocation id, String group, Ingredient input1, ItemStack output1, int waterAmount) {
        super(RecipeTypes.FERMENTER, id, group, input1, output1);
        this.waterAmount = waterAmount;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return Ref.FERMENTER_SERIALIZER;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> temp = NonNullList.create();
        temp.add(this.input1);
        return temp;
    }
}
