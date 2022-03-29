package al132.techemistry.blocks.macerator;

import al132.techemistry.RecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class MaceratorRegistry {

    private static List<MaceratorRecipe> recipes = null;

    public static void init() {
        /*
        addRecipe(Registration.crushedWheat, 1, Items.WHEAT, 0);
        addRecipe(Registration.appleSauce, 1, Items.APPLE, 0);
        addRecipe(toStack("silicon_dioxide", 4), Items.SAND, 1);
        addRecipe(toStack(Items.SAND), Items.GRAVEL, 1);
        addRecipe(toStack(Items.GRAVEL), Items.COBBLESTONE, 1);
        addRecipe(toStack(Items.COBBLESTONE), Items.STONE, 1);
        addRecipe(toStack("calcium_carbonate", 2), Items.EGG, 0);
        addRecipe(toStack("calcium_carbonate", 8), Items.TURTLE_EGG, 0);
        addRecipe(toStack("magnesium_carbonate"), toStack("calcium_carbonate"), new ItemStack(Registration.dolomite), 1);

        for (Mineral mineral : Registration.minerals) {
            addRecipe(toStack(mineral.crushedItem), toStack(mineral.mineralItem), 1);
        }
        //addRecipe(toStack(Registration.crushedCassiterite), toStack(Registration.cassiterite), 1);
       // addRecipe(toStack("manganese_oxide"), toStack(Registration.pyrolusite), 1);
        //addRecipe(toStack("strontium_carbonate"), toStack(Registration.strontianite), 1);
        addRecipe(toStack("sulfur", 4), toStack(Registration.sulfurChunk), 1);
        addRecipe(toStack("hydroxylapatite"), toStack(Items.BONE_MEAL), 0);
        addRecipe(toStack("ammonium_chloride"), toStack(Registration.salAmmoniac), 1);
        addRecipe(toStack("sodium_carbonate"), toStack(Registration.natron), 1);
        addRecipe(toStack("iron_oxide", 8), toStack(Items.IRON_ORE), 1);


        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Registration.galena.mineralItem, 8, 1),
                weighted(Registration.pyrite.mineralItem, 8, 1),
                weighted(Registration.chalcocite.mineralItem, 8, 1),
                weighted(Registration.stibnite.mineralItem, 8, 1),
                weighted(Registration.millerite.mineralItem, 8, 1),
                weighted(Registration.cinnabar.mineralItem, 8, 1),
                weighted(Registration.braggite.mineralItem,8,1)),
                ItemStack.EMPTY, toIngredient(Registration.sulfideOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Registration.cassiterite.mineralItem, 8, 1),
                weighted(Registration.pyrolusite.mineralItem, 8, 1),
                weighted(Registration.cuprite.mineralItem, 8, 1),
                weighted(Registration.magnetite.mineralItem, 8, 1),
                weighted(Registration.hematite.mineralItem, 8, 1),
                weighted(Registration.ilmenite.mineralItem, 8, 1),
                weighted(Registration.uraninite.mineralItem, 8, 1)),
                ItemStack.EMPTY, toIngredient(Registration.oxideOre), 2, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Registration.strontianite.mineralItem, 8, 1),
                weighted(Registration.cerussite.mineralItem, 8, 1),
                weighted(Registration.spherocobaltite.mineralItem, 8, 1),
                weighted(Registration.siderite.mineralItem, 8, 1),
                weighted(Registration.rhodochrosite.mineralItem, 8, 1),
                weighted(Registration.natron, 2, 1)),
                ItemStack.EMPTY, toIngredient(Registration.carbonateOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Registration.melanterite.mineralItem, 4, 1),
                weighted(Registration.barite.mineralItem, 4, 1),
                weighted(Registration.celestite.mineralItem, 4, 1),
                weighted(Registration.anglesite.mineralItem, 4, 1)),
                ItemStack.EMPTY, toIngredient(Registration.sulfateOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Registration.vanadinite.mineralItem, 4, 1),
                weighted(Registration.pyromorphite.mineralItem, 4, 1)),
                ItemStack.EMPTY, toIngredient(Registration.phosphateOre), 1, true));
                */

    }
/*
    public static void addRecipe(ItemStack output, ItemStack output2, ItemStack input, int tier) {
        recipes.add(new MaceratorRecipe(output, output2, Ingredient.fromStacks(input), tier, false));
    }

    public static void addRecipe(Item output, int count, Item input, int tier) {
        recipes.add(new MaceratorRecipe(output, count, ItemStack.EMPTY, Ingredient.fromStacks(new ItemStack(input)), tier, false));
    }

    public static void addRecipe(ItemStack output, Item input, int tier) {
        recipes.add(new MaceratorRecipe(output, ItemStack.EMPTY, Ingredient.fromStacks(new ItemStack(input)), tier, false));
    }

    public static void addRecipe(ItemStack output, ItemStack input, int tier) {
        recipes.add(new MaceratorRecipe(output, ItemStack.EMPTY, Ingredient.fromStacks(input), tier, false));
    }
*/

    public static List<MaceratorRecipe> getRecipes(Level level) {
        if (recipes == null) {
            recipes = level.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.MACERATOR)
                    .map(x -> (MaceratorRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }
    public static boolean hasRecipe(Level level, ItemStack input) {
        return getRecipes(level).stream().flatMap(x -> Arrays.stream(x.getIngredients().get(0).getItems()).map(ItemStack::getItem))
                .anyMatch(item -> item == input.getItem());
    }

    public static Optional<MaceratorRecipe> getRecipeForInput(Level level, ItemStack input) {
        return getRecipes(level).stream().filter(recipe ->
                Arrays.stream(recipe.getIngredients().get(0).getItems())
                        .anyMatch(x -> ItemStack.isSame(x, input)))
                .findFirst();
    }
}