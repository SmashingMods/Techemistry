package al132.techemistry.data;


import net.minecraft.world.item.ItemStack;

import java.util.List;

public class Formula {

    public final List<ItemStack> inputs;
    public final List<ItemStack> outputs;

    public Formula(List<ItemStack> inputs, List<ItemStack> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return "inputs: " + inputs + "\noutputs: " + outputs;
    }
}