package al132.techemistry.blocks.froth_flotation_chamber;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.items.minerals.Mineral;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static al132.techemistry.utils.TUtils.toIngredient;
import static al132.techemistry.utils.TUtils.toStack;

public class FrothFlotationRegistry {

    private static List<FrothFlotationRecipe> recipes = null;


    public static void init() {
        for(Mineral mineral: Ref.minerals){
            addRecipe(toStack(mineral.slurryItem),toIngredient(mineral.crushedItem),1000);
        }
    }

    public static void addRecipe(ItemStack output1, ItemStack output2, Ingredient input1, int water) {
        //recipes.add(new FrothFlotationRecipe(output1, output2, input1, Ingredient.fromStacks(toStack("potassium_ethyl_xanthate")), water));
    }

    public static void addRecipe(ItemStack output1, Ingredient input1, int water) {
        //addRecipe(output1, ItemStack.EMPTY, input1, water);
    }

    public static List<FrothFlotationRecipe> getRecipes(World world) {
        if (recipes == null) {
            recipes = world.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.FROTH_FLOTATION_CHAMBER)
                    .map(x -> (FrothFlotationRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }

    public static boolean hasRecipe(World world, ItemStack stack) {
        return getRecipes(world).stream().anyMatch(x -> matchesRecipe(x, stack));
    }

    public static boolean matchesRecipe(FrothFlotationRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.input.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);
    }

    public static Optional<FrothFlotationRecipe> getRecipeForInput(World world, ItemStack input1) {
        return getRecipes(world).stream()
                .filter(recipe -> matchesRecipe(recipe, input1))
                .findFirst();
    }
}