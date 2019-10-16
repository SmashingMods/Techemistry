package al132.techemistry.blocks.fermenter;

import al132.techemistry.Ref;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FermenterRegistry {

    public static List<FermenterRecipe> recipes = new ArrayList<>();

    public static void init() {
        addRecipe(Items.SUGAR, Ref.sugarWine, 1000);
        addRecipe(Ref.crushedWheat, Ref.beer, 1000);
        addRecipe(Ref.appleSauce, Ref.cider, 1000);
        addRecipe(Items.POTATO, Ref.potatoWine, 1000);
    }

    public static void addRecipe(Item input, Item output, int waterAmount) {
        recipes.add(new FermenterRecipe(new ItemStack(output), Ingredient.fromStacks(new ItemStack(input)), waterAmount));
    }

    public static boolean inputHasRecipe(ItemStack input) {
        return recipes.stream().anyMatch(x -> matchesRecipe(x, input));
    }

    public static Optional<FermenterRecipe> getRecipeForInput(ItemStack input) {
        return recipes.stream()
                .filter(recipe -> matchesRecipe(recipe, input))
                .findFirst();
    }

    public static boolean matchesRecipe(FermenterRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.input.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);

    }
}
