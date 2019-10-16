package al132.techemistry.blocks.reaction_chamber;

import al132.techemistry.FormulaParser;
import al132.techemistry.Ref;
import al132.techemistry.utils.Utils;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static al132.techemistry.utils.Utils.toIngredient;
import static al132.techemistry.utils.Utils.toStack;

public class ReactionChamberRegistry {

    public static List<ReactionChamberRecipe> recipes = new ArrayList<>();


    public static void init() {
        addRecipe("2H2SO4 + S -> 3SO2 + 2H2O", 370);
        addRecipe("H2SO4 + NaCl -> HCl + NaHSO4", 273);
        addRecipe("CaCO3 + 2HCl -> CaCl2 + CO2 + H2O", 273);
        addRecipe("Cr2O3 + 2Al -> 2Cr + Al2O3", 800);
        addRecipe("Ca5PO46OH2 + 5H2SO4 -> 3H3PO4 + 5CaSO4 + H2O", 273);
        addRecipe("NH4Cl + NaOH -> NH3 + NaCl + H2O", 273);
        addRecipe("HCl + NaOH -> NaCl + H2O", 273);
        addRecipe("CaOH2 + Na2CO3 -> CaCO3 + 2NaOH", 273);
        addRecipe("NiS + 2H + 4CO -> Ni + H2O + 4CO", 520); //This .. isn't totally balanced
        addRecipe("Ni + 2HCl -> NiCl2 + 2H", 273);
        addRecipe("Ca + 2HCl -> CaCl2 + 2H", 273);

        //https://en.wikipedia.org/wiki/Claus_process#Catalytic_step
        addRecipe("H2S + SO2 + Al2O3 -> 3S + 2H2O + Al2O3", 1320);

        addRecipe(toStack("carbon_dioxide"), toStack("sulfur_dioxide", 2), toStack("water", 2),
                370, toIngredient("sulfuric_acid", 2), toIngredient(Items.COAL, Ref.coke));


        Lists.newArrayList("Li", "Na", "K", "Rb", "Cs")
                .forEach(ele -> {
                    addRecipe("2" + ele + " + 2H2O -> 2" + ele + "OH + 2H", 273);
                });
        Lists.newArrayList("Be", "Mg", "Ca", "Sr", "Ba")
                .forEach(ele -> {
                    addRecipe(ele + "O + H2O -> " + ele + "OH2", 273);
                });


    }


    public static void addRecipe(String formula, double minimumHeat) {
        recipes.add(new ReactionChamberRecipe(FormulaParser.parse(formula), minimumHeat));
    }

    public static void addRecipe(ItemStack output1, ItemStack output2, ItemStack output3, double minimumHeat, Ingredient... inputs) {
        recipes.add(ReactionChamberRecipe.create(output1, output2, output3, minimumHeat, inputs));
    }

    public static void addRecipe(ItemStack output1, ItemStack output2, double minimumHeat, Ingredient... inputs) {
        recipes.add(ReactionChamberRecipe.create(output1, output2, ItemStack.EMPTY, minimumHeat, inputs));
    }

    public static void addRecipe(ItemStack output1, double minimumHeat, Ingredient... inputs) {
        recipes.add(ReactionChamberRecipe.create(output1, ItemStack.EMPTY, ItemStack.EMPTY, minimumHeat, inputs));
    }

    public static boolean hasRecipe(ItemStack stack, int slot) {
        return recipes.stream().map(x -> Lists.newArrayList(x.getInput(slot).getMatchingStacks()))
                .anyMatch(x -> x.stream().anyMatch(y -> y.getItem() == stack.getItem()));
    }

    public static boolean matchesRecipe(ReactionChamberRecipe recipe, IItemHandler inputHandler) {
        ItemStack stack0 = inputHandler.getStackInSlot(0);
        ItemStack stack1 = inputHandler.getStackInSlot(1);
        ItemStack stack2 = inputHandler.getStackInSlot(2);

        return ((stack0.isEmpty() && Utils.isIngredientEmpty(recipe.input0)) || Arrays.stream(recipe.input0.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == stack0.getItem()))
                && ((stack1.isEmpty() && Utils.isIngredientEmpty(recipe.input1)) || Arrays.stream(recipe.input1.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == stack1.getItem()))
                && ((stack2.isEmpty() && Utils.isIngredientEmpty(recipe.input2)) || Arrays.stream(recipe.input2.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == stack2.getItem()));
    }

    public static Optional<ReactionChamberRecipe> getRecipeForInput(IItemHandler input) {
        return recipes.stream()
                .filter(recipe -> matchesRecipe(recipe, input))
                .findFirst();
    }
}