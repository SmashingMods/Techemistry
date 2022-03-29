package al132.techemistry.utils;

import al132.techemistry.blocks.macerator.WeightedItemStack;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ShapedRecipe;

import java.util.List;
import java.util.stream.Collectors;


public class RecipeUtils {

    public static ItemStack getOptionalStack(JsonObject json, String label) {
        ItemStack temp = ItemStack.EMPTY;
        if (json.has(label)) {
            if (json.get(label).isJsonObject())
                temp = ShapedRecipe.itemStackFromJson(json.getAsJsonObject(label));//JSONUtils.getJsonObject(json, label));
            else {
                String s1 = json.get(label).getAsString();//JSONUtils.getString(json, label);
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                temp = new ItemStack(Registry.ITEM.get(resourcelocation));
            }
        }
        return temp;
    }

    public static List<ItemStack> flatten(List<WeightedItemStack> list) {
        return list.stream().map(x -> x.stack).collect(Collectors.toList());
    }
}