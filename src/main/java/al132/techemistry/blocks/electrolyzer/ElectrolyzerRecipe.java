package al132.techemistry.blocks.electrolyzer;

import al132.techemistry.RecipeTypes;
import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.utils.ProcessingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;


public class ElectrolyzerRecipe extends ProcessingRecipe {

    public Ingredient input2;
    public double minimumHeat;
    public Ingredient anode = Ingredient.of(Registration.PLATINUM_ELECTRODE_ITEM.get());
    public Ingredient cathode = Ingredient.of(Registration.PLATINUM_ELECTRODE_ITEM.get());
    public ItemStack output2;
    public ItemStack output3;


    public ElectrolyzerRecipe(ResourceLocation id, String group, Ingredient input1, Ingredient input2, double minimumHeat,
                              ItemStack output1, ItemStack output2, ItemStack output3)  {
        super(RecipeTypes.ELECTROLYZER, id, group, input1, output1);
        if(input2 == null) this.input2 = Ingredient.EMPTY;
        else this.input2 = input2;
        this.output2 = output2;
        this.output3 = output3;
        this.minimumHeat = minimumHeat;
    }

    public Ingredient getInput1() {
        return input1;
    }

    public ItemStack getOutput1(){
        return output1;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> temp = NonNullList.create();
        temp.add(this.input1);
        temp.add(this.input2);
        return temp;
    }

    public NonNullList<ItemStack> getOutputs(){
        NonNullList temp = NonNullList.create();
        temp.add(output1);
        temp.add(output2);
        temp.add(output3);
        return temp;
    }

    /*
    //https://en.wikipedia.org/wiki/Glasswort
    //https://en.wikipedia.org/wiki/Castner_process
    public ElectrolyzerRecipe(Ingredient input1, Ingredient input2, ItemStack anode, ItemStack cathode, double heat,
                              ItemStack output1, ItemStack output2, ItemStack output3) {
        this.input1 = input1;
        this.input2 = input2;
        this.heat = heat;
        this.anode = anode;
        this.cathode = cathode;
        this.output1 = output1;
        this.output2 = output2;
        this.output3 = output3;
    }

    public ElectrolyzerRecipe(Ingredient input1, Ingredient input2, ItemStack anode, ItemStack cathode, double heat, ItemStack output1, ItemStack output2) {
        this(input1, input2, anode, cathode, heat, output1, output2, ItemStack.EMPTY);
    }

    public ElectrolyzerRecipe(Ingredient input1, Ingredient input2, ItemStack anode, ItemStack cathode, double heat, ItemStack output1) {
        this(input1, input2, anode, cathode, heat, output1, ItemStack.EMPTY, ItemStack.EMPTY);
    }

    public ElectrolyzerRecipe(Formula formula, double heat, ItemStack anode, ItemStack cathode) {
        this.input1 = (formula.inputs.size() >= 1) ? Ingredient.fromStacks(formula.inputs.get(0).copy()) : Ingredient.EMPTY;
        this.input2 = (formula.inputs.size() >= 2) ? Ingredient.fromStacks(formula.inputs.get(1).copy()) : Ingredient.EMPTY;
        this.heat = heat;
        this.anode = anode;
        this.cathode = cathode;
        this.output1 = (formula.outputs.size() >= 1) ? formula.outputs.get(0).copy() : ItemStack.EMPTY;
        this.output2 = (formula.outputs.size() >= 2) ? formula.outputs.get(1).copy() : ItemStack.EMPTY;
        this.output3 = (formula.outputs.size() >= 3) ? formula.outputs.get(2).copy() : ItemStack.EMPTY;
    }
*/
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registration.ELECTROLYZER_SERIALIZER.get();
    }
}
