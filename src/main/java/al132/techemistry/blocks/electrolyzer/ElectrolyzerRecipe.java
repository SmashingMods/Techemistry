package al132.techemistry.blocks.electrolyzer;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class ElectrolyzerRecipe {

    public Ingredient input1;
    public Ingredient input2;
    public double heat;
    public ItemStack anode;
    public ItemStack cathode;
    public ItemStack output1;
    public ItemStack output2;
    public ItemStack output3;

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
}
