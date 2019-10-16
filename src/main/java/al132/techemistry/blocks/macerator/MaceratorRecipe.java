package al132.techemistry.blocks.macerator;

import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static al132.techemistry.blocks.macerator.WeightedItemStack.weighted;

public class MaceratorRecipe {

    public final List<WeightedItemStack> output;
    public final ItemStack output2;
    private final List<ItemStack> allPossibleOutputs;
    public final Ingredient input;
    public final int tier;
    public final boolean useEfficiency;
    private final Random rand = new Random();


    public MaceratorRecipe(List<WeightedItemStack> output, ItemStack output2, Ingredient input, int tier, boolean useEfficiency) {
        this.output = output;
        this.output2 = output2;
        this.input = input;
        this.tier = tier;
        this.useEfficiency = useEfficiency;
        this.allPossibleOutputs = output.stream().map(x -> x.stack).map(ItemStack::copy).collect(Collectors.toList());
    }

    public MaceratorRecipe(ItemStack output, ItemStack output2, Ingredient input, int tier, boolean useEfficiency) {
        this(Lists.newArrayList(weighted(output, 1)), output2, input, tier, useEfficiency);
    }

    public MaceratorRecipe(Item output, int count, ItemStack output2, Ingredient input, int tier, boolean useEfficiency) {
        this(Lists.newArrayList(weighted(output, count, 1)), output2, input, tier, useEfficiency);
    }

    public List<ItemStack> getAllSlot1Stacks() {
        return allPossibleOutputs;
    }

    public int calculateCount(ItemStack stack, double efficiency) {
        return Math.max(1, Math.round(stack.getCount() * ((float) efficiency)));

    }

    public ItemStack calculateOutput(double efficiency) {
        final int totalWeight = output.stream().map(x -> x.weight).reduce(Integer::sum).get();
        final int target = rand.nextInt(totalWeight) + 1;

        int tracking = 0;
        for (WeightedItemStack stack : output) {
            tracking += stack.weight;
            if (tracking >= target) {
                ItemStack output = stack.stack.copy();
                if (this.useEfficiency) {
                    output.setCount(calculateCount(output, efficiency));
                }
                return output;
            }
        }
        throw new RuntimeException("Wait what");
    }
}
