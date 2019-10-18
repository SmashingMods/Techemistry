package al132.techemistry.blocks.macerator;

import al132.techemistry.Ref;
import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static al132.techemistry.blocks.macerator.WeightedItemStack.weighted;
import static al132.techemistry.utils.Utils.toIngredient;
import static al132.techemistry.utils.Utils.toStack;

public class MaceratorRegistry {

    public static List<MaceratorRecipe> recipes = new ArrayList<>();

    public static void init() {
        addRecipe(Ref.crushedWheat, 1, Items.WHEAT, 0);
        addRecipe(Ref.appleSauce, 1, Items.APPLE, 0);
        addRecipe(toStack("silicon_dioxide", 4), Items.SAND, 1);
        addRecipe(toStack(Items.SAND), Items.GRAVEL, 1);
        addRecipe(toStack(Items.GRAVEL), Items.COBBLESTONE, 1);
        addRecipe(toStack(Items.COBBLESTONE), Items.STONE, 1);
        addRecipe(toStack("calcium_carbonate", 2), Items.EGG, 0);
        addRecipe(toStack("calcium_carbonate", 8), Items.TURTLE_EGG, 0);
        addRecipe(toStack("magnesium_carbonate"), toStack("calcium_carbonate"), new ItemStack(Ref.dolomite), 1);
        addRecipe(toStack("zinc_sulfide"), toStack(Ref.sphalerite), 1);
        addRecipe(toStack(Ref.crushedPyrite), toStack(Ref.pyrite), 1);
        addRecipe(toStack(Ref.crushedGalena), toStack(Ref.galena), 1);
        addRecipe(toStack("tin_oxide"), toStack(Ref.cassiterite), 1);
        addRecipe(toStack("manganese_oxide"), toStack(Ref.pyrolusite), 1);
        addRecipe(toStack("strontium_carbonate"), toStack(Ref.strontianite), 1);
        addRecipe(toStack("sulfur", 4), toStack(Ref.sulfurChunk), 1);
        addRecipe(toStack(Ref.crushedMelanterite), toStack(Ref.melanterite), 1);
        addRecipe(toStack("hydroxylapatite"), toStack(Items.BONE_MEAL), 0);
        addRecipe(toStack("ammonium_chloride"), toStack(Ref.salAmmoniac), 1);
        addRecipe(toStack("sodium_carbonate"), toStack(Ref.natron), 1);
        addRecipe(toStack("copper_i_sulfide"), toStack(Ref.chalcocite), 1);
        addRecipe(toStack("antimony_trisulfide"), toStack(Ref.stibnite), 1);
        addRecipe(toStack("nickel_sulfide"), toStack(Ref.millerite), 1);
        addRecipe(toStack("mercury_sulfide"), toStack(Ref.cinnabar), 1);
        addRecipe(toStack("iron_oxide", 8), toStack(Items.IRON_ORE), 1);

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.galena, 8, 1),
                weighted(Ref.pyrite, 8, 1),
                weighted(Ref.chalcocite, 8, 1),
                weighted(Ref.stibnite, 8, 1),
                weighted(Ref.millerite, 8, 1),
                weighted(Ref.cinnabar, 8, 1)),
                ItemStack.EMPTY, toIngredient(Ref.sulfideOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.cassiterite, 8, 1),
                weighted(Ref.pyrolusite, 8, 1),
                weighted(Ref.cuprite, 8, 1),
                weighted(Ref.magnetite, 8, 1),
                weighted(Ref.hematite, 8, 1),
                weighted(Ref.ilmenite, 8, 1),
                weighted(Ref.uraninite, 8, 1)),
                ItemStack.EMPTY, toIngredient(Ref.oxideOre), 2, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.strontianite, 8, 1),
                weighted(Ref.cerussite, 8, 1),
                weighted(Ref.spherocobaltite, 8, 1),
                weighted(Ref.siderite, 8, 1),
                weighted(Ref.rhodochrosite, 8, 1),
                weighted(Ref.natron, 2, 1)),
                ItemStack.EMPTY, toIngredient(Ref.carbonateOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.melanterite, 4, 1)),
                ItemStack.EMPTY, toIngredient(Ref.sulfateOre), 1, true));

        recipes.add(new MaceratorRecipe(Lists.newArrayList(
                weighted(Ref.vanadinite, 4, 1),
                weighted(Ref.pyromorphite, 4, 1)),
                ItemStack.EMPTY, toIngredient(Ref.phosphateOre), 1, true));
    }

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

    public static boolean hasRecipe(ItemStack input) {
        return recipes.stream().flatMap(x -> Arrays.stream(x.input.getMatchingStacks()).map(ItemStack::getItem))
                .anyMatch(item -> item == input.getItem());
    }

    public static Optional<MaceratorRecipe> getRecipeForInput(ItemStack input) {
        return recipes.stream().filter(recipe ->
                Arrays.stream(recipe.input.getMatchingStacks())
                        .anyMatch(x -> ItemStack.areItemsEqual(x, input)))
                .findFirst();
    }
}