package al132.techemistry.blocks.fermenter;

import al132.techemistry.RecipeTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FermenterRegistry {

    private static List<FermenterRecipe> recipes = null;

    public static void init() {
        /*
        addRecipe(Items.SUGAR, Ref.sugarWine, 1000);
        addRecipe(Ref.crushedWheat, Ref.beer, 1000);
        addRecipe(Ref.appleSauce, Ref.cider, 1000);
        addRecipe(Items.POTATO, Ref.potatoWine, 1000);
         */
    }

    public static boolean inputHasRecipe(World world, ItemStack input) {
        return getRecipes(world).stream().anyMatch(x -> matchesRecipe(x, input));
    }

    public static Optional<FermenterRecipe> getRecipeForInput(World world, ItemStack input) {
        return getRecipes(world).stream()
                .filter(recipe -> matchesRecipe(recipe, input))
                .findFirst();
    }

    public static List<FermenterRecipe> getRecipes(World world) {
        if (recipes == null) {
            recipes = world.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.FERMENTER)
                    .map(x -> (FermenterRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }

    public static boolean matchesRecipe(FermenterRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.getIngredients().get(0).getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);

    }
}
