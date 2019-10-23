package al132.techemistry.blocks.calcination_chamber;

import al132.techemistry.Ref;
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

public class CalcinationRegistry {

    public static List<CalcinationRecipe> recipes = new ArrayList<>();


    public static void init() {
        //=====SULFIDE=====
        addRecipe(toStack("iron_oxide"), toStack("sulfur_dioxide"), toIngredient("iron_disulfide", 2), 820);
        addRecipe(toStack("iron_oxide"), toStack("sulfur_dioxide"), toIngredient(Ref.crushedPyrite, 2), 820);
        addRecipe(toStack("iron_oxide"), toStack("gold"), toStack("sulfur_dioxide"), toIngredient(Ref.pyriteSlurry, 2), 820);

        addRecipe(toStack("lead_oxide"), toStack("sulfur_dioxide"), toIngredient("lead_sulfide"), 820);
        addRecipe(toStack("lead_oxide"), toStack("sulfur_dioxide"), toIngredient(Ref.crushedGalena), 820);
        addRecipe(toStack("lead_oxide"), toStack("silver"), toStack("sulfur_dioxide"), toIngredient(Ref.galenaSlurry), 1235);

        addRecipe(toStack("zinc_oxide"), toStack("sulfur_dioxide"), toIngredient("zinc_sulfide"), 1460);
        addRecipe(toStack("zinc_oxide"), toStack("sulfur_dioxide"), toIngredient(Ref.crushedSphalerite), 1460);
        addRecipe(toStack("zinc_oxide"), toStack("cadmium"), toStack("sulfur_dioxide"), toIngredient(Ref.sphaleriteSlurry), 1460);

        addRecipe(toStack("copper_i_oxide", 2), toStack("sulfur_dioxide", 2), toIngredient("copper_i_sulfide", 2), 870);
        addRecipe(toStack("copper_i_oxide", 2), toStack("sulfur_dioxide", 2), toIngredient(Ref.crushedChalcocite, 2), 870);
        addRecipe(toStack("copper_i_oxide", 2), toStack("iron", 2), toStack("sulfur_dioxide", 2), toIngredient(Ref.chalcociteSlurry, 2), 870);

        addRecipe(toStack("mercury"), toStack("sulfur_dioxide"), toIngredient("mercury_sulfide"), 630);
        addRecipe(toStack("mercury"), toStack("sulfur_dioxide"), toIngredient(Ref.crushedCinnabar), 630);
        addRecipe(toStack("mercury", 2), toStack("sulfur_dioxide", 2), toIngredient(Ref.cinnabarSlurry), 630);

        addRecipe(toStack("antimony_trioxide"), toStack("sulfur_dioxide", 3), toIngredient("antimony_trisulfide"), 670);
        addRecipe(toStack("antimony_trioxide"), toStack("sulfur_dioxide", 3), toIngredient(Ref.crushedStibnite), 670);
        addRecipe(toStack("antimony_trioxide", 2), toStack("sulfur_dioxide", 6), toIngredient(Ref.stibniteSlurry), 670);

        addRecipe(toStack("nickel_oxide"), toStack("platinum"), toStack("sulfur_dioxide"), toIngredient(Ref.milleriteSlurry), 1600);
        //braggite?

        //=====OXIDE MINERAL=====

        //=====CARBONATE=====
        addRecipe(toStack("beryllium_oxide"), toStack("carbon_dioxide"), toIngredient("beryllium_carbonate"), 373);
        addRecipe(toStack("calcium_oxide"), toStack("carbon_dioxide"), toIngredient("calcium_carbonate"), 1098.0);
        addRecipe(toStack("magnesium_oxide"), toStack("carbon_dioxide"), toIngredient("magnesium_carbonate"), 1173.0);
        addRecipe(toStack("strontium_oxide"), toStack("carbon_dioxide"), toIngredient("strontium_carbonate"), 1400);
        addRecipe(toStack("barium_oxide"), toStack("carbon_dioxide"), toIngredient("barium_carbonate"), 1720);

        //=====OTHER=====
        // addRecipe(toStack("copper_i_oxide"), toStack("sulfur_dioxide"), toIngredient("copper_i_sulfide"), 1400);
        addRecipe(toStack("sulfuric_acid"), toStack("iron_ii_oxide"), toStack("water", 6), toIngredient(Ref.crushedMelanterite), 950);
        addRecipe(toStack(Ref.coke), ItemStack.EMPTY, toIngredient(Items.COAL), 1320.0);
        addRecipe(toStack("hydrochloric_acid"), toStack("ammonia"), toIngredient("ammonium_chloride"), 610);
        addRecipe(toStack("calcium_oxide"), toStack("water"), toIngredient("calcium_hydroxide"), 853.0);
        addRecipe(toStack("water", 2), toStack("oxygen", 2), toStack("nitrogen_dioxide", 4),
                toIngredient("nitric_acid", 4), 360);
    }

    public static void addRecipe(ItemStack output, ItemStack gas, Ingredient input, double heat) {
        recipes.add(new CalcinationRecipe(output, gas, input, heat));
    }

    public static void addRecipe(ItemStack output, ItemStack output2, ItemStack gas, Ingredient input, double heat) {
        recipes.add(new CalcinationRecipe(output, output2, gas, input, heat));
    }

    public static boolean hasRecipe(ItemStack stack) {
        return recipes.stream().anyMatch(x -> matchesRecipe(x, stack));
    }

    public static boolean matchesRecipe(CalcinationRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.input.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);
    }

    public static Optional<CalcinationRecipe> getRecipeForInput(ItemStack input1) {
        return recipes.stream()
                .filter(recipe -> matchesRecipe(recipe, input1))
                .findFirst();
    }
}