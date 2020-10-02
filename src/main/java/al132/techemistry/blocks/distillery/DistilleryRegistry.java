package al132.techemistry.blocks.distillery;

import al132.techemistry.RecipeTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DistilleryRegistry {

    public static List<DistilleryRecipe> recipes = null;


    public static void init() {
    }
    /*
        addRecipe(Ref.sugarWine, Ref.rum, 350);
        addRecipe(Ref.rum, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
        addRecipe(Ref.potatoWine, Ref.vodka, 350);
        addRecipe(Ref.vodka, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
        addRecipe(Ref.beer, Ref.whiskey, 350);
        addRecipe(Ref.whiskey, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
        addRecipe(Ref.cider, Ref.appleBrandy, 350);
        addRecipe(Ref.appleBrandy, Utils.toStack("ethanol", 16), new ItemStack(Items.GLASS_BOTTLE), 350);
    }

    public static void addRecipe(Item input, Item output, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Ingredient.fromItems(input), new ItemStack(output), minimumTemp));
    }

    public static void addRecipe(Item input, ItemStack output, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Ingredient.fromItems(input), output, minimumTemp));
    }

    public static void addRecipe(Item input, ItemStack output, ItemStack output2, double minimumTemp) {
        recipes.add(new DistilleryRecipe(Ref.DISTILLERY_TYPE, Ingredient.fromItems(input), output, output2, minimumTemp));
    }

 */

    public static List<DistilleryRecipe> getRecipes(World world) {
        if(recipes == null) {
            recipes = world.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.DISTILLERY)
                    .map(x -> (DistilleryRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }

    public static boolean inputHasRecipe(World world, ItemStack input) {
        return getRecipes(world).stream().anyMatch(x -> matchesRecipe(x, input));
    }

    public static Optional<DistilleryRecipe> getRecipeForInput(World world, ItemStack input) {
        return getRecipes(world).stream()
                .filter(recipe -> matchesRecipe(recipe, input))
                .findFirst();
    }

    public static boolean matchesRecipe(DistilleryRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        if(recipe.getIngredients().size() < 1) return false;
        return Arrays.stream(recipe.getIngredients().get(0).getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);

    }
}
