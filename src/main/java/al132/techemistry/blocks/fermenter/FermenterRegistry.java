package al132.techemistry.blocks.fermenter;

import al132.techemistry.RecipeTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FermenterRegistry {

    private static List<FermenterRecipe> recipes = null;

    public static void init() {
        /*
        addRecipe(Items.SUGAR, Registration.sugarWine, 1000);
        addRecipe(Registration.crushedWheat, Registration.beer, 1000);
        addRecipe(Registration.appleSauce, Registration.cider, 1000);
        addRecipe(Items.POTATO, Registration.potatoWine, 1000);
         */
    }

    public static boolean inputHasRecipe(Level level, ItemStack input) {
        return getRecipes(level).stream().anyMatch(x -> matchesRecipe(x, input));
    }

    public static Optional<FermenterRecipe> getRecipeForInput(Level level, ItemStack input) {
        return getRecipes(level).stream()
                .filter(recipe -> matchesRecipe(recipe, input))
                .findFirst();
    }

    public static List<FermenterRecipe> getRecipes(Level level) {
        if (recipes == null) {
            recipes = level.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.FERMENTER)
                    .map(x -> (FermenterRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }

    public static boolean matchesRecipe(FermenterRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.getIngredients().get(0).getItems())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);

    }
}
