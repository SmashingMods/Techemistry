package al132.techemistry.blocks.distillery;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;


public class DistilleryRecipe extends ProcessingRecipe {

    public ItemStack output2;
    public double minimumHeat;

    public DistilleryRecipe(ResourceLocation id, String group, Ingredient input, double minimumHeat, ItemStack output, ItemStack output2) {
        super(RecipeTypes.DISTILLERY, id, group, input, output);
        this.output2 = output2;
        this.minimumHeat = minimumHeat;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registration.DISTILLERY_SERIALIZER.get();
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
