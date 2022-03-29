package al132.techemistry.blocks.macerator;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WeightedItemStack {

    public ItemStack stack;
    public int weight;

    public WeightedItemStack(ItemStack stack, int weight) {
        this.stack = stack;
        this.weight = weight;
    }

    public static WeightedItemStack weighted(ItemStack stack, int weight) {
        return new WeightedItemStack(stack, weight);
    }

    public static WeightedItemStack weighted(Item item, int count, int weight) {
        return new WeightedItemStack(new ItemStack(item, count), weight);
    }
}
