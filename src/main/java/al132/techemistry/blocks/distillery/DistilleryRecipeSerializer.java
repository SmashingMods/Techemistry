package al132.techemistry.blocks.distillery;

import al132.techemistry.utils.ProcessingRecipe;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class DistilleryRecipeSerializer<T extends DistilleryRecipe>
        extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final int temp;
    private IFactory<T> factory;

    public DistilleryRecipeSerializer(DistilleryRecipeSerializer.IFactory<T> factory, int temp) {
        this.factory = factory;
        this.temp = temp;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {
        String s = JSONUtils.getString(json, "group", "");
        JsonElement jsonelement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
        Ingredient ingredient = Ingredient.deserialize(jsonelement);
        //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
        if (!json.has("result"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack output1;
        if (json.get("result").isJsonObject())
            output1 = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
        else {
            String s1 = JSONUtils.getString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            output1 = new ItemStack(Registry.ITEM.getOrDefault(resourcelocation));
        }
        ItemStack output2 = ItemStack.EMPTY;
        if (json.has("result2")) {
            if (json.get("result2").isJsonObject())
                output2 = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result2"));
            else {
                String s1 = JSONUtils.getString(json, "result2");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                output2 = new ItemStack(Registry.ITEM.getOrDefault(resourcelocation));
            }
        }
        int d = JSONUtils.getInt(json, "minimumTemp", this.temp);
        return this.factory.create(recipeId, s, ingredient,d, output1, output2);
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readString(32767);
        Ingredient ingredient = Ingredient.read(buffer);
        ItemStack output1 = buffer.readItemStack();
        ItemStack output2 = buffer.readItemStack();
        double d = buffer.readDouble();
        return this.factory.create(recipeId, s, ingredient, d, output1, output2);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeString(recipe.getGroup());
        recipe.getIngredients().get(0).write(buffer);
        buffer.writeItemStack(recipe.getRecipeOutput());
        buffer.writeItemStack(recipe.getRecipeOutput2());
        buffer.writeDouble(recipe.minimumHeat);
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation resource, String group, Ingredient input, double temp, ItemStack output1, ItemStack output2);
    }
}