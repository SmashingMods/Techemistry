package al132.techemistry.blocks.macerator;

import al132.techemistry.utils.ProcessingRecipe;
import al132.techemistry.utils.RecipeUtils;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MaceratorRecipeSerializer<T extends MaceratorRecipe>
        extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final int tier;
    private IFactory<T> factory;

    public MaceratorRecipeSerializer(MaceratorRecipeSerializer.IFactory<T> factory, int temp) {
        this.factory = factory;
        this.tier = temp;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {
        String s = JSONUtils.getString(json, "group", "");
        JsonElement jsonelement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
        Ingredient input = Ingredient.deserialize(jsonelement);
        //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
        if (!json.has("result")) {
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        }
        ItemStack output1 = ItemStack.EMPTY;
        List<WeightedItemStack> outputs = new ArrayList<>();
        //System.out.println(json.get("result").toString());
        if (json.get("result").isJsonObject()) {
            output1 = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
        } else if (json.get("result").isJsonArray()) {
            //System.out.println("array");
            JsonArray resultArray = json.getAsJsonArray("result");
            resultArray.forEach(entry -> {
                JsonObject temp = entry.getAsJsonObject();
                int weight = 1;
                ItemStack tempStack = ItemStack.EMPTY;
                if (temp.has("weight")) weight = temp.get("weight").getAsInt();
                String s1 = JSONUtils.getString(temp, "item");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                tempStack = new ItemStack(Registry.ITEM.getOrDefault(resourcelocation));

                if (temp.has("count")) tempStack.setCount(temp.get("count").getAsInt());
                outputs.add(new WeightedItemStack(tempStack, weight));
            });
        } else {
            String s1 = JSONUtils.getString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            output1 = new ItemStack(Registry.ITEM.getOrDefault(resourcelocation));
        }
        ItemStack output2 = RecipeUtils.getOptionalStack(json, "result2");
        int tier = JSONUtils.getInt(json, "tier", this.tier);
        boolean useEfficiency = true; //TODO
        if (!output1.isEmpty()) outputs.add(new WeightedItemStack(output1, 1));
        return this.factory.create(recipeId, s, input, outputs, output2, tier, useEfficiency);
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readString(32767);
        Ingredient ingredient = Ingredient.read(buffer);
        int tier = buffer.readInt();
        boolean useEfficiency = buffer.readBoolean();
        WeightedItemStack output1 = new WeightedItemStack(buffer.readItemStack(), 1);
        ItemStack output2 = buffer.readItemStack();
        return this.factory.create(recipeId, s, ingredient, Lists.newArrayList(output1), output2, tier, useEfficiency);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeString(recipe.getGroup());
        recipe.getIngredients().get(0).write(buffer);
        buffer.writeInt(recipe.tier);
        buffer.writeBoolean(recipe.useEfficiency);
        buffer.writeItemStack(recipe.output2);
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation id, String group, Ingredient input, List<WeightedItemStack> output, ItemStack output2, int tier, boolean useEfficiency);
    }
}