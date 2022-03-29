package al132.techemistry.blocks.smeltery;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;


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
    public RecipeSerializer<?> getSerializer() {
        return Registration.SMELTERY_SERIALIZER.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> temp = NonNullList.create();
        temp.add(this.input1);
        return temp;
    }
}
