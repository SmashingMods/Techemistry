package al132.techemistry.blocks.fermenter;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;


public class FermenterRecipe extends ProcessingRecipe {


    public final int waterAmount;

    public FermenterRecipe(ResourceLocation id, String group, Ingredient input1, ItemStack output1, int waterAmount) {
        super(RecipeTypes.FERMENTER, id, group, input1, output1);
        this.waterAmount = waterAmount;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registration.FERMENTER_SERIALIZER.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> temp = NonNullList.create();
        temp.add(this.input1);
        return temp;
    }
}
