package al132.techemistry.blocks.electrolyzer;

import al132.techemistry.data.FormulaParser;
import al132.techemistry.Ref;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static al132.techemistry.utils.Utils.toIngredient;
import static al132.techemistry.utils.Utils.toStack;

public class ElectrolyzerRegistry {

    public static List<ElectrolyzerRecipe> recipes = new ArrayList<>();
    public static final ItemStack anode = new ItemStack(Ref.platinumElectrode);
    public static final ItemStack cathode = new ItemStack(Ref.platinumElectrode);


    public static void init() {

        //Group 1 Chlorides
        addRecipe("2LiCl -> 2Li + 2Cl", 880);
        addRecipe("2NaCl -> 2Na + 2Cl", 1074);
        addRecipe("2KCl -> 2K + 2Cl", 1040);
        addRecipe("2RbCl -> 2Rb + 2Cl", 990);
        addRecipe("2CsCl -> 2Cs + 2Cl", 920);

        //Group 2 Chlorides
        addRecipe("BeCl2 -> Be + 2Cl", 670);
        addRecipe("MgCl2 -> Mg + 2Cl", 990);
        addRecipe("CaCl2 -> Ca + 2Cl", 1045);
        addRecipe("SrCl2 -> Sr + 2Cl", 1520);
        addRecipe("BaCl2 -> Ba + 2Cl", 1235);

        //https://www.ausetute.com.au/chloralkali.html
        addRecipe(toIngredient("water", 2), toIngredient("sodium_chloride", 2), 273,
                toStack("hydrogen", 2), toStack("chlorine", 2), toStack("sodium_hydroxide", 2));
    }

    public static void addRecipe(String formula, double heat) {
        recipes.add(new ElectrolyzerRecipe(FormulaParser.parse(formula), heat, anode.copy(), cathode.copy()));
    }

    public static void addRecipe(Ingredient input1, Ingredient input2, double heat, ItemStack... outputs) {
        if (outputs.length == 3) {
            recipes.add(new ElectrolyzerRecipe(input1, input2, anode.copy(), cathode.copy(), heat, outputs[0], outputs[1], outputs[2]));
        } else if (outputs.length == 2) {
            recipes.add(new ElectrolyzerRecipe(input1, input2, anode.copy(), cathode.copy(), heat, outputs[0], outputs[1], ItemStack.EMPTY));
        } else if (outputs.length == 1) {
            recipes.add(new ElectrolyzerRecipe(input1, input2, anode.copy(), cathode.copy(), heat, outputs[0], ItemStack.EMPTY, ItemStack.EMPTY));
        } else throw new RuntimeException("Invalid electrolyzer recipe, requires 1-3 inputs");
    }

    public static boolean hasRecipe(ItemStack stack) {
        return recipes.stream().anyMatch(x -> matchesRecipe(x, stack));
    }

    public static boolean matchesRecipe(ElectrolyzerRecipe recipe, ItemStack targetStack) {
        Item targetItem = targetStack.getItem();
        return Arrays.stream(recipe.input1.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == targetItem);
    }

    public static Optional<ElectrolyzerRecipe> getRecipeForInput(ItemStack input1, ItemStack input2) {
        return recipes.stream()
                .filter(recipe -> matchesRecipe(recipe, input1))
                .findFirst();
    }
}