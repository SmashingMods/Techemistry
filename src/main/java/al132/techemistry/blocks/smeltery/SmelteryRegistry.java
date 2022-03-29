package al132.techemistry.blocks.smeltery;

import al132.techemistry.RecipeTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class SmelteryRegistry {

    private static List<SmelteryRecipe> recipes = null;


    public static void init() {
        FluxRegistry.init();
        /*
        addRecipe(toStack(Items.IRON_INGOT), toIngredient("iron_oxide", 8), 2270);
        addRecipe(toStack(Items.IRON_INGOT), toIngredient(Registration.hematite.crushedItem, 8), 2270);
        addRecipe(toStack(Items.IRON_INGOT), toStack("iron"), toIngredient(Registration.hematite.slurryItem, 8), 2270);

        addRecipe(toStack(Items.IRON_INGOT), toIngredient("iron_ii_oxide", 16), 2270);
        addRecipe(toStack(Items.IRON_INGOT), toIngredient("iron", 16), 1810);
        addRecipe(toStack("iron", 15), toIngredient(Registration.magnetite.crushedItem, 5), 1870);
        addRecipe(toStack("iron", 20), toIngredient(Registration.magnetite.slurryItem, 5), 1870);
        addRecipe(toStack("chemlib:ingot_lead"), toIngredient("lead_oxide", 16), 1470);
        addRecipe(toStack("chemlib:ingot_lead"), toIngredient("lead", 16), 600);
        addRecipe(toStack("chemlib:ingot_zinc"), toIngredient("zinc_oxide", 16), 1220);
        addRecipe(toStack("chemlib:ingot_tin"), toIngredient("tin_oxide", 16), 1470);
        addRecipe(toStack("chemlib:ingot_tin"), toIngredient("tin", 16), 505);
        addRecipe(toStack("chemlib:ingot_tin"), toIngredient(Registration.cassiterite.slurryItem, 12), 1470);
        addRecipe(toStack("chemlib:ingot_tin"), toIngredient(Registration.cassiterite.crushedItem, 16), 1470);

        addRecipe(toStack("chemlib:ingot_manganese"), toIngredient(Registration.pyrolusite.crushedItem, 16), 800);
        addRecipe(toStack("chemlib:ingot_manganese"), toIngredient(Registration.pyrolusite.slurryItem, 12), 800);
        addRecipe(toStack("chemlib:ingot_manganese"), toIngredient("manganese_oxide", 16), 810);
        addRecipe(toStack("chemlib:ingot_manganese"), toIngredient("manganese", 16), 1520);

        addRecipe(toStack("chemlib:ingot_copper"), toIngredient("copper_i_oxide", 8), 1470);
        addRecipe(toStack("chemlib:ingot_copper"), toIngredient("copper", 16), 1360);
        addRecipe(toStack("chemlib:ingot_copper"), toIngredient(Registration.cuprite.crushedItem, 16), 1500);
        addRecipe(toStack("chemlib:ingot_copper"), toIngredient(Registration.cuprite.slurryItem, 8), 1500);

        //addRecipe(toStack("titanium_dioxide"), toIngredient(Registration.ilmenite.crushedItem), 2115);
        // addRecipe(toStack("titanium_dioxide"), toStack("iron"), toIngredient(Registration.ilmenite.slurryItem), 2115);
        // addRecipe(toStack("chemlib:ingot_titanium"), toIngredient("titanium_dioxide", 16), 2115);
        addRecipe(toStack("chemlib:ingot_titanium"), toIngredient("titanium", 16), 1940);

        addRecipe(toStack("chemlib:ingot_nickel"), toIngredient("nickel", 16), 1730);
        addRecipe(toStack("chemlib:ingot_nickel"), toIngredient("nickel_oxide", 16), 2230);

        addRecipe(toStack(Items.GOLD_INGOT), toIngredient("gold", 16), 1340);
        addRecipe(toStack("chemlib:ingot_silver"), toIngredient("silver", 16), 1235);
        addRecipe(toStack("chemlib:ingot_platinum"), toIngredient("platinum", 16), 2040);

        addRecipe(toStack("barium_sulfide"), toIngredient("barium_sulfate"), 1870); // && carbon monoxide?
        addRecipe(toStack("barium_sulfide"), toIngredient(Registration.barite.crushedItem), 1870);
        addRecipe(toStack("barium_sulfide"), toIngredient(Registration.barite.slurryItem), 1870);

         */
    }

    /*
    public static void addRecipe(ItemStack output, Ingredient input, double temp) {
        addRecipe(output, ItemStack.EMPTY, input, temp);
    }

    public static void addRecipe(ItemStack output, ItemStack output2, Ingredient input, double temp) {
        recipes.add(new SmelteryRecipe(output, output2, input, temp));
    }
*/

    public static List<SmelteryRecipe> getRecipes(Level level) {
        if (recipes == null) {
            recipes = level.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.SMELTERY)
                    .map(x -> (SmelteryRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }

    public static boolean hasRecipe(ItemStack stack) {
        return recipes.stream().anyMatch(x -> matchesRecipe(x, stack));
    }

    public static boolean matchesRecipe(SmelteryRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.getIngredients().get(0).getItems())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);
    }

    public static Optional<SmelteryRecipe> getRecipeForInput(Level level, ItemStack input1) {
        return getRecipes(level).stream()
                .filter(recipe -> matchesRecipe(recipe, input1))
                .findFirst();
    }
}