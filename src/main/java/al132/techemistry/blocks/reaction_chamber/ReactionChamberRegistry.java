package al132.techemistry.blocks.reaction_chamber;

import al132.techemistry.RecipeTypes;
import al132.techemistry.utils.TUtils;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static al132.techemistry.utils.TUtils.toIngredient;
import static al132.techemistry.utils.TUtils.toStack;

public class ReactionChamberRegistry {

    private static List<ReactionChamberRecipe> recipes = null;


    public static void init() {
        /*
        addRecipe("2H2SO4 + S -> 3SO2 + 2H2O", 370);
        addRecipe("H2SO4 + NaCl -> HCl + NaHSO4", 273);
        addRecipe("2HCl + CaCO3 -> CaCl2 + CO2 + H2O", 273);
        addRecipe("Cr2O3 + 2Al -> 2Cr + Al2O3", 800);
        addRecipe("5H2SO4 + Ca5PO46OH2 -> 3H3PO4 + 5CaSO4 + H2O", 273);
        addRecipe("NH4Cl + NaOH -> NH3 + NaCl + H2O", 273);
        addRecipe("CaOH2 + Na2CO3 -> CaCO3 + 2NaOH", 273);
        addRecipe("NiS + 2H + 4CO -> Ni + H2O + 4CO", 520); //This .. isn't totally balanced
        addRecipe("2HCl + Ni -> NiCl2 + 2H", 273);
        addRecipe("2HCl + Ca -> CaCl2 + 2H", 273);
        //https://en.wikipedia.org/wiki/Claus_process#Catalytic_step
        addRecipe("H2S + SO2 + Al2O3 -> 3S + 2H2O + Al2O3", 1320);

        addRecipe("2CH4 + 8S + Al2O3 -> 2CS2 + 4H2S + Al2O3", 870);
        addRecipe("C2H5OH + CS2 + KOH -> C3H5KOS2 + H2O", 273);

        //https://en.wikipedia.org/wiki/Ostwald_process
        addRecipe("4NH3 + 10O + Pt -> 4NO + 6H2O + Pt", 500);
        addRecipe("2NO + 2O -> 2NO2", 500);
        addRecipe("3NO2 + H2O -> 2HNO3 + NO", 273);

        //TODO addRecipe(toStack("carbon_dioxide"), toStack("sulfur_dioxide", 2), toStack("water", 2),
                370, toIngredient("sulfuric_acid", 2), toIngredient(Items.COAL, Ref.coke));


        Lists.newArrayList("Li", "Na", "K", "Rb", "Cs")
                .forEach(ele -> {
                    addRecipe("2" + ele + " + 2H2O -> 2" + ele + "OH + 2H", 273);
                    addRecipe("HCl + " + ele + "OH -> " + ele + "Cl + H2O", 273);
                    addRecipe("HNO3 + " + ele + "OH -> " + ele + "NO3 + H2O", 273);
                    addRecipe("H2SO4 + 2" + ele + "OH -> " + ele + "2SO4 + 2H2O", 273);

                    addRecipe("2HCl + " + ele + "2CO3 -> 2" + ele + "Cl + H2O + CO2", 273);
                    addRecipe("2HNO3 + " + ele + "2CO3 -> 2" + ele + "NO3 + H2O + CO2", 273);
                    addRecipe("H2SO4 + " + ele + "2CO3 -> " + ele + "2SO4 + H2O + CO2", 273);
                });
        Lists.newArrayList("Be", "Mg", "Ca", "Sr", "Ba")
                .forEach(ele -> {
                    addRecipe(ele + "O + H2O -> " + ele + "OH2", 273);
                    addRecipe("2HCl + " + ele + "OH2 -> " + ele + "Cl2 + 2H2O", 273);
                    addRecipe("2HNO3 + " + ele + "OH2 -> " + ele + "NO32 + 2H2O", 273);
                    addRecipe("H2SO4 + " + ele + "OH2 -> " + ele + "SO4 + 2H2O", 273);

                    addRecipe("2HCl + " + ele + "CO3 -> " + ele + "Cl2 + H2O + CO2", 273);
                    addRecipe("2HNO3 + " + ele + "CO3 -> " + ele + "NO32 + H2O + CO2", 273);
                    addRecipe("H2SO4 + " + ele + "CO3 -> " + ele + "SO4 + H2O + CO2", 273);
                });

*/
    }

/*
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
*/

    public static List<ReactionChamberRecipe> getRecipes(World world) {
        if (recipes == null) {
            recipes = world.getRecipeManager().getRecipes().stream()
                    .filter(x -> x.getType() == RecipeTypes.REACTION_CHAMBER)
                    .map(x -> (ReactionChamberRecipe) x)
                    .collect(Collectors.toList());
        }
        return recipes;
    }

    public static boolean hasRecipe(World world, ItemStack stack, int slot) {
        return getRecipes(world).stream().map(x -> Lists.newArrayList(x.getInput(slot).getMatchingStacks()))
                .anyMatch(x -> x.stream().anyMatch(y -> y.getItem() == stack.getItem()));
    }

    public static boolean matchesRecipe(ReactionChamberRecipe recipe, IItemHandler inputHandler) {
        ItemStack stack0 = inputHandler.getStackInSlot(0);
        ItemStack stack1 = inputHandler.getStackInSlot(1);
        ItemStack stack2 = inputHandler.getStackInSlot(2);

        return ((stack0.isEmpty() && TUtils.isIngredientEmpty(recipe.input0)) || Arrays.stream(recipe.input0.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == stack0.getItem()))
                && ((stack1.isEmpty() && TUtils.isIngredientEmpty(recipe.input1)) || Arrays.stream(recipe.input1.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == stack1.getItem()))
                && ((stack2.isEmpty() && TUtils.isIngredientEmpty(recipe.input2)) || Arrays.stream(recipe.input2.getMatchingStacks())
                .map(ItemStack::getItem)
                .anyMatch(item -> item == stack2.getItem()));
    }

    public static Optional<ReactionChamberRecipe> getRecipeForInput(World world, IItemHandler input) {
        return getRecipes(world).stream()
                .filter(recipe -> matchesRecipe(recipe, input))
                .findFirst();
    }
}