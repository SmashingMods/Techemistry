package al132.techemistry.blocks.macerator;

import al132.techemistry.utils.ProcessingRecipe;
import al132.techemistry.utils.RecipeUtils;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MaceratorRecipeSerializer<T extends MaceratorRecipe>
        extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {

    private final int tier;
    private IFactory<T> factory;

    public MaceratorRecipeSerializer(MaceratorRecipeSerializer.IFactory<T> factory, int temp) {
        this.factory = factory;
        this.tier = temp;
    }

    @Override
    public T fromJson(ResourceLocation recipeId, JsonObject json) {
        String s = json.get("group").getAsString();//JSONUtils.getString(json, "group", "");
        JsonElement jsonelement = (JsonElement) (json.get("ingredient").isJsonArray()
                ? json.getAsJsonArray("ingredient")
                : json.getAsJsonObject("ingredient"));
        Ingredient input = Ingredient.fromJson(jsonelement);
        //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
        if (!json.has("result")) {
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        }
        ItemStack output1 = ItemStack.EMPTY;
        List<WeightedItemStack> outputs = new ArrayList<>();
        //System.out.println(json.get("result").toString());
        if (json.get("result").isJsonObject()) {
            output1 = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));//.getJsonObject(json, "result"));
        } else if (json.get("result").isJsonArray()) {
            //System.out.println("array");
            JsonArray resultArray = json.getAsJsonArray("result");
            resultArray.forEach(entry -> {
                JsonObject temp = entry.getAsJsonObject();
                int weight = 1;
                ItemStack tempStack = ItemStack.EMPTY;
                if (temp.has("weight")) weight = temp.get("weight").getAsInt();
                String s1 = temp.get("item").getAsString();//JSONUtils.getString(temp, "item");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                tempStack = new ItemStack(Registry.ITEM.get(resourcelocation));

                if (temp.has("count")) tempStack.setCount(temp.get("count").getAsInt());
                outputs.add(new WeightedItemStack(tempStack, weight));
            });
        } else {
            String s1 = json.get("result").getAsString();// JSONUtils.getString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            output1 = new ItemStack(Registry.ITEM.get(resourcelocation));
        }
        ItemStack output2 = RecipeUtils.getOptionalStack(json, "result2");
        int tier = json.get("tier").getAsInt();//JSONUtils.getInt(json, "tier", this.tier);
        boolean useEfficiency = true; //TODO
        if (!output1.isEmpty()) outputs.add(new WeightedItemStack(output1, 1));
        return this.factory.create(recipeId, s, input, outputs, output2, tier, useEfficiency);
    }

    @Nullable
    @Override
    public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        int tier = buffer.readInt();
        boolean useEfficiency = buffer.readBoolean();
        ArrayList<WeightedItemStack> outputs = Lists.newArrayList();
        int size = buffer.readInt();
        for (int i = 0; i < size; i++) {
            ItemStack stack = buffer.readItem();
            int weight = buffer.readInt();
            outputs.add(new WeightedItemStack(stack,weight));
        }
        //WeightedItemStack output1 = new WeightedItemStack(buffer.readItemStack(), 1);
        ItemStack output2 = buffer.readItem();
        return this.factory.create(recipeId, s, ingredient, outputs, output2, tier, useEfficiency);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.getGroup());
        recipe.getIngredients().get(0).toNetwork(buffer);
        buffer.writeInt(recipe.tier);
        buffer.writeBoolean(recipe.useEfficiency);
        buffer.writeInt(recipe.output.size());
        for (WeightedItemStack stack : recipe.output) {
            buffer.writeItem(stack.stack);
            buffer.writeInt(stack.weight);
        }
        buffer.writeItem(recipe.output2);
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation id, String group, Ingredient input, List<WeightedItemStack> output, ItemStack output2, int tier, boolean useEfficiency);
    }
}