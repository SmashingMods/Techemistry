package al132.techemistry.blocks.macerator;

import al132.techemistry.RecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static al132.techemistry.blocks.macerator.WeightedItemStack.weighted;
import static al132.techemistry.utils.TUtils.toIngredient;
import static al132.techemistry.utils.TUtils.toStack;

public class MaceratorRegistry {

    private static List<MaceratorRecipe> recipes = null;

    public static void init() {
        /*
        addRecipe(Ref.crushedWheat, 1, Items.WHEAT, 0);
        addRecipe(Ref.appleSauce, 1, Items.APPLE, 0);
        addRecipe(toStack("silicon_dioxide", 4), Items.SAND, 1);
        addRecipe(toStack(Items.SAND), Items.GRAVEL, 1);
        addRecipe(toStack(Items.GRAVEL), Items.COBBLESTONE, 1);
        addRecipe(toStack(Items.COBBLESTONE), Items.STONE, 1);
        addRecipe(toStack("calcium_carbonate", 2), Items.EGG, 0);
        addRecipe(toStack("calcium_carbonate", 8), Items.TURTLE_EGG, 0);
        addRecipe(toStack("magnesium_carbonate"), toStack("calcium_carbonate"), new ItemStack(Ref.dolomite), 1);

        for (Mineral mineral : Ref.minerals) {
            addRecipe(toStack(mineral.crushedItem), toStack(mineral.mineralItem), 1);
        }
        //addRecipe(toStack(Ref.crushedCassiterite), toStack(Ref.cassiterite), 1);
       // addRecipe(toStack("manganese_oxide"), toStack(Ref.pyrolusite), 1);
        //addRecipe(toStack("strontium_carbonate"), toStack(Ref.strontianite), 1);
        addRecipe(toStack("sulfur", 4), toStack(Ref.sulfurChunk), 1);
        addRecipe(toStack("hydroxylapatite"), toStack(Items.BONE_MEAL), 0);
        addRecipe(toStack("ammonium_chloride"), toStack(Ref.salAmmoniac), 1);
        addRecipe(toStack("sodium_carbonate"), toStack(Ref.natron), 1);
        addRecipe(toStack("iron_oxide", 8), toStack(Items.IRON_ORE), 1);


        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.galena.mineralItem, 8, 1),
                weighted(Ref.pyrite.mineralItem, 8, 1),
                weighted(Ref.chalcocite.mineralItem, 8, 1),
                weighted(Ref.stibnite.mineralItem, 8, 1),
                weighted(Ref.millerite.mineralItem, 8, 1),
                weighted(Ref.cinnabar.mineralItem, 8, 1),
                weighted(Ref.braggite.mineralItem,8,1)),
                ItemStack.EMPTY, toIngredient(Ref.sulfideOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.cassiterite.mineralItem, 8, 1),
                weighted(Ref.pyrolusite.mineralItem, 8, 1),
                weighted(Ref.cuprite.mineralItem, 8, 1),
                weighted(Ref.magnetite.mineralItem, 8, 1),
                weighted(Ref.hematite.mineralItem, 8, 1),
                weighted(Ref.ilmenite.mineralItem, 8, 1),
                weighted(Ref.uraninite.mineralItem, 8, 1)),
                ItemStack.EMPTY, toIngredient(Ref.oxideOre), 2, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.strontianite.mineralItem, 8, 1),
                weighted(Ref.cerussite.mineralItem, 8, 1),
                weighted(Ref.spherocobaltite.mineralItem, 8, 1),
                weighted(Ref.siderite.mineralItem, 8, 1),
                weighted(Ref.rhodochrosite.mineralItem, 8, 1),
                weighted(Ref.natron, 2, 1)),
                ItemStack.EMPTY, toIngredient(Ref.carbonateOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.melanterite.mineralItem, 4, 1),
                weighted(Ref.barite.mineralItem, 4, 1),
                weighted(Ref.celestite.mineralItem, 4, 1),
                weighted(Ref.anglesite.mineralItem, 4, 1)),
                ItemStack.EMPTY, toIngredient(Ref.sulfateOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.vanadinite.mineralItem, 4, 1),
                weighted(Ref.pyromorphite.mineralItem, 4, 1)),
                ItemStack.EMPTY, toIngredient(Ref.phosphateOre), 1, true));
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

    public static List<MaceratorRecipe> getRecipes(World world) {
        if (recipes == null) {
            recipes = world.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.MACERATOR)
                    .map(x -> (MaceratorRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }
    public static boolean hasRecipe(World world, ItemStack input) {
        return getRecipes(world).stream().flatMap(x -> Arrays.stream(x.getIngredients().get(0).getMatchingStacks()).map(ItemStack::getItem))
                .anyMatch(item -> item == input.getItem());
    }

    public static Optional<MaceratorRecipe> getRecipeForInput(World world, ItemStack input) {
        return getRecipes(world).stream().filter(recipe ->
                Arrays.stream(recipe.getIngredients().get(0).getMatchingStacks())
                        .anyMatch(x -> ItemStack.areItemsEqual(x, input)))
                .findFirst();
    }
}