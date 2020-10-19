package al132.techemistry.blocks.froth_flotation_chamber;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class FrothFlotationRecipe extends ProcessingRecipe {

    public final ItemStack output;
    public final ItemStack output2;
    public final Ingredient input;
    public final Ingredient input2;
    public final int water;

    public FrothFlotationRecipe(ResourceLocation id, String group, Ingredient input, Ingredient input2, int water, ItemStack output, ItemStack output2) {
        super(RecipeTypes.FROTH_FLOTATION_CHAMBER, id, group, input, output);
        this.output = output;
        this.output2 = output2;
        this.input = input;
        this.input2 = input2;
        this.water = water;
    }

    /*
    public FrothFlotationRecipe(ItemStack output, ItemStack output2, Ingredient input, int water) {
        this(output, output2, input, Ingredient.EMPTY, water);
    }
    */

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> temp = NonNullList.create();
        temp.add(this.input1);
        temp.add(this.input2);
        return temp;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return Ref.FROTH_FLOTATION_SERIALIZER;
    }
}
