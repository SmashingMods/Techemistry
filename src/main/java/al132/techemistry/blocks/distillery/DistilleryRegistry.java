package al132.techemistry.blocks.distillery;

import al132.techemistry.Ref;
import al132.techemistry.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DistilleryRegistry {

    public static List<DistilleryRecipe> recipes = new ArrayList<>();


    public static void init() {
        addRecipe(Ref.sugarWine, Ref.rum, 350);
        addRecipe(Ref.rum, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
        addRecipe(Ref.potatoWine, Ref.vodka, 350);
        addRecipe(Ref.vodka, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
        addRecipe(Ref.beer, Ref.whiskey, 350);
        addRecipe(Ref.whiskey, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
    }

    public static void addRecipe(Item input, Item output, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Ingredient.fromItems(input), new ItemStack(output), minimumTemp));
    }

    public static void addRecipe(Item input, ItemStack output, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Ingredient.fromItems(input), output, minimumTemp));
    }

    public static void addRecipe(Item input, ItemStack output, ItemStack output2, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Ingredient.fromItems(input), output, output2, minimumTemp));
    }

    public static boolean inputHasRecipe(ItemStack input) {
        return recipes.stream().anyMatch(x -> matchesRecipe(x, input));
    }

    public static Optional<DistilleryRecipe> getRecipeForInput(ItemStack input) {
        return recipes.stream()
                .filter(recipe -> matchesRecipe(recipe, input))
                .findFirst();
    }

    public static boolean matchesRecipe(DistilleryRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.input.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);

    }
}
