package al132.techemistry.blocks.reaction_chamber;

import al132.techemistry.data.Formula;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class ReactionChamberRecipe {

    public final ItemStack output0;
    public final ItemStack output1;
    public final ItemStack output2;

    public final Ingredient input0;
    public final Ingredient input1;
    public final Ingredient input2;
    public final double minimumHeat;

    public ReactionChamberRecipe(ItemStack output0, ItemStack output1, ItemStack output2, double minimumHeat, Ingredient input0,
                                 Ingredient input1, Ingredient input2) {
        this.output0 = output0;
        this.output1 = output1;
        this.output2 = output2;
        this.minimumHeat = minimumHeat;
        this.input0 = input0;
        this.input1 = input1;
        this.input2 = input2;
    }

    public ReactionChamberRecipe(Formula formula, double minimumHeat) {
        this.minimumHeat = minimumHeat;
        input0 = formula.inputs.size() >= 1 ? Ingredient.fromStacks(formula.inputs.get(0)) : Ingredient.EMPTY;
        input1 = formula.inputs.size() >= 2 ? Ingredient.fromStacks(formula.inputs.get(1)) : Ingredient.EMPTY;
        input2 = formula.inputs.size() >= 3 ? Ingredient.fromStacks(formula.inputs.get(2)) : Ingredient.EMPTY;
        output0 = formula.outputs.size() >= 1 ? formula.outputs.get(0).copy() : ItemStack.EMPTY;
        output1 = formula.outputs.size() >= 2 ? formula.outputs.get(1).copy() : ItemStack.EMPTY;
        output2 = formula.outputs.size() >= 3 ? formula.outputs.get(2).copy() : ItemStack.EMPTY;

    }

    public static ReactionChamberRecipe create(ItemStack output0, ItemStack output1, ItemStack output2, double minimumHeat, Ingredient... inputs) {
        Ingredient input0 = inputs[0];
        Ingredient input1 = Ingredient.EMPTY;
        Ingredient input2 = Ingredient.EMPTY;
        if (inputs.length >= 2) input1 = inputs[1];
        if (inputs.length >= 3) input2 = inputs[2];
        return new ReactionChamberRecipe(output0, output1, output2, minimumHeat, input0, input1, input2);
    }

    public Ingredient getInput(int slot) {
        switch (slot) {
            case 0:
                return input0;
            case 1:
                return input1;
            case 2:
                return input2;
            default:
                return Ingredient.EMPTY;
        }
    }

}
