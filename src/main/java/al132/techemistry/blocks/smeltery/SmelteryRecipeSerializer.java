package al132.techemistry.blocks.smeltery;

import al132.techemistry.utils.ProcessingRecipe;
import al132.techemistry.utils.RecipeUtils;
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

public class SmelteryRecipeSerializer<T extends SmelteryRecipe>
        extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final int temp;
    private IFactory<T> factory;

    public SmelteryRecipeSerializer(SmelteryRecipeSerializer.IFactory<T> factory, int temp) {
        this.factory = factory;
        this.temp = temp;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {
        String s = JSONUtils.getString(json, "group", "");
        JsonElement jsonelement = null;
        int inputCount = 1;
        if (JSONUtils.isJsonArray(json, "ingredient")) {
            jsonelement = JSONUtils.getJsonArray(json, "ingredient");
        } else {
            JsonObject jobj = JSONUtils.getJsonObject(json, "ingredient");
            inputCount = jobj.get("count").getAsInt();
            jsonelement = jobj;
        }
        Ingredient input = Ingredient.deserialize(jsonelement);
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
        ItemStack output2 = RecipeUtils.getOptionalStack(json, "result2");
        ItemStack gas = RecipeUtils.getOptionalStack(json, "gas");
        int minimumTemp = JSONUtils.getInt(json, "minimumTemp", this.temp);
        return this.factory.create(recipeId, s, input, inputCount, output1, gas, minimumTemp);
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        String group = buffer.readString(32767);
        Ingredient ingredient = Ingredient.read(buffer);
        int inputCount = buffer.readInt();
        ItemStack output1 = buffer.readItemStack();
        //ItemStack output2 = buffer.readItemStack();
        ItemStack gas = buffer.readItemStack();
        double minimumTemp = buffer.readDouble();
        return this.factory.create(recipeId, group, ingredient, inputCount, output1, gas, minimumTemp);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeString(recipe.getGroup());
        recipe.getIngredients().get(0).write(buffer);
        buffer.writeDouble(recipe.minimumHeat);
        buffer.writeItemStack(recipe.getRecipeOutput());

    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation id, String group, Ingredient input, int inputCount,
                 ItemStack output, ItemStack gasOutput, double minimumHeat);
    }
}