package al132.techemistry.blocks.froth_flotation_chamber;

import al132.techemistry.Ref;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static al132.techemistry.utils.Utils.toIngredient;
import static al132.techemistry.utils.Utils.toStack;

public class FrothFlotationRegistry {

    public static List<FrothFlotationRecipe> recipes = new ArrayList<>();


    public static void init() {
        addRecipe(toStack(Ref.galenaSlurry), ItemStack.EMPTY, toIngredient(Ref.crushedGalena), 1000);
    }

    public static void addRecipe(ItemStack output1, ItemStack output2, Ingredient input1, int water) {
        recipes.add(new FrothFlotationRecipe(output1, output2, input1, Ingredient.fromStacks(toStack("potassium_ethyl_xanthate")), water));
    }


    public static boolean hasRecipe(ItemStack stack) {
        return recipes.stream().anyMatch(x -> matchesRecipe(x, stack));
    }

    public static boolean matchesRecipe(FrothFlotationRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.input.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);
    }

    public static Optional<FrothFlotationRecipe> getRecipeForInput(ItemStack input1) {
        return recipes.stream()
                .filter(recipe -> matchesRecipe(recipe, input1))
                .findFirst();
    }
}