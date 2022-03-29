package al132.techemistry.blocks.distillery;

import al132.techemistry.RecipeTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DistilleryRegistry {

    public static List<DistilleryRecipe> recipes = null;


    public static void init() {
    }
    /*
        addRecipe(Registration.sugarWine, Registration.rum, 350);
        addRecipe(Registration.rum, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
        addRecipe(Registration.potatoWine, Registration.vodka, 350);
        addRecipe(Registration.vodka, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
        addRecipe(Registration.beer, Registration.whiskey, 350);
        addRecipe(Registration.whiskey, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
        addRecipe(Registration.cider, Registration.appleBrandy, 350);
        addRecipe(Registration.appleBrandy, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
    }

    public static void addRecipe(Item input, Item output, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Ingredient.fromItems(input), new ItemStack(output), minimumTemp));
    }

    public static void addRecipe(Item input, ItemStack output, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Ingredient.fromItems(input), output, minimumTemp));
    }

    public static void addRecipe(Item input, ItemStack output, ItemStack output2, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Registration.DISTILLERY_TYPE, Ingredient.fromItems(input), output, output2, minimumTemp));
    }

 */

    public static List<DistilleryRecipe> getRecipes(Level level) {
        if(recipes == null) {
            recipes = level.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.DISTILLERY)
                    .map(x -> (DistilleryRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }

    public static boolean inputHasRecipe(Level level, ItemStack input) {
        return getRecipes(level).stream().anyMatch(x -> matchesRecipe(x, input));
    }

    public static Optional<DistilleryRecipe> getRecipeForInput(Level level, ItemStack input) {
        return getRecipes(level).stream()
                .filter(recipe -> matchesRecipe(recipe, input))
                .findFirst();
    }

    public static boolean matchesRecipe(DistilleryRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        if(recipe.getIngredients().size() < 1) return false;
        return Arrays.stream(recipe.getIngredients().get(0).getItems())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);

    }
}
