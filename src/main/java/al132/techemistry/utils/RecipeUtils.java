package al132.techemistry.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class RecipeUtils {

    public static ItemStack getOptionalStack(JsonObject json, String label) {
        ItemStack temp = ItemStack.EMPTY;
        if (json.has(label)) {
            if (json.get(label).isJsonObject())
                temp = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, label));
            else {
                String s1 = JSONUtils.getString(json, label);
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                temp = new ItemStack(Registry.ITEM.getOrDefault(resourcelocation));
            }
        }
        return temp;
    }

}
