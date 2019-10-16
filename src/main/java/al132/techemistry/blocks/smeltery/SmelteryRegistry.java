package al132.techemistry.blocks.smeltery;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static al132.techemistry.utils.Utils.toIngredient;
import static al132.techemistry.utils.Utils.toStack;

public class SmelteryRegistry {

    public static List<SmelteryRecipe> recipes = new ArrayList<>();


    public static void init() {
        FluxRegistry.init();
        addRecipe(toStack(Items.IRON_INGOT), toIngredient("iron_oxide", 8), 2270);
        addRecipe(toStack(Items.IRON_INGOT), toIngredient("iron_ii_oxide", 16), 2270);
        addRecipe(toStack("chemlib:ingot_lead"), toIngredient("lead_oxide", 16), 1470);
        addRecipe(toStack("chemlib:ingot_zinc"), toIngredient("zinc_oxide", 16), 1220);
        addRecipe(toStack("chemlib:ingot_tin"), toIngredient("tin_oxide", 16), 1470);
        addRecipe(toStack("chemlib:ingot_manganese"), toIngredient("manganese_oxide", 16), 810);
        addRecipe(toStack("chemlib:ingot_copper"), toIngredient("copper_i_oxide", 8), 1470);
        addRecipe(toStack("chemlib:ingot_nickel"), toIngredient("nickel", 16), 1730);
        addRecipe(toStack(Items.IRON_INGOT), toIngredient("iron", 16), 1810);
    }

    public static void addRecipe(ItemStack output, Ingredient input, double temp) {
        recipes.add(new SmelteryRecipe(output, ItemStack.EMPTY, input, temp));
    }

    public static boolean hasRecipe(ItemStack stack) {
        return recipes.stream().anyMatch(x -> matchesRecipe(x, stack));
    }

    public static boolean matchesRecipe(SmelteryRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.input.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);
    }

    public static Optional<SmelteryRecipe> getRecipeForInput(ItemStack input1) {
        return recipes.stream()
                .filter(recipe -> matchesRecipe(recipe, input1))
                .findFirst();
    }
}