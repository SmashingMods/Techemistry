package al132.techemistry.blocks.calcination_chamber;

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

public class CalcinationRecipeSerializer<T extends CalcinationRecipe>
        extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final int temp;
    private IFactory<T> factory;

    public CalcinationRecipeSerializer(CalcinationRecipeSerializer.IFactory<T> factory, int temp) {
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
            output1 = new ItemStack(Registry.ITEM.getValue(resourcelocation).orElseThrow(() -> {
                return new IllegalStateException("Item: " + s1 + " does not exist");
            }));
        }
        ItemStack output2 = RecipeUtils.getOptionalStack(json, "result2");
        ItemStack gas = RecipeUtils.getOptionalStack(json, "gas");
        int d = JSONUtils.getInt(json, "minimumTemp", this.temp);
        return this.factory.create(recipeId, s, ingredient, d, output1, output2, gas);
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readString(32767);
        Ingredient ingredient = Ingredient.read(buffer);
        ItemStack output1 = buffer.readItemStack();
        ItemStack output2 = buffer.readItemStack();
        ItemStack gas = buffer.readItemStack();
        double d = buffer.readDouble();
        return this.factory.create(recipeId, s, ingredient, d, output1, output2, gas);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeString(recipe.getGroup());
        recipe.getIngredients().get(0).write(buffer);
        buffer.writeDouble(recipe.minimumHeat);
        buffer.writeItemStack(recipe.getRecipeOutput());
        buffer.writeItemStack(recipe.getRecipeOutput2());
        buffer.writeItemStack(recipe.getRecipeGas());
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation resource, String group, Ingredient input,  double temp, ItemStack output1, ItemStack output2, ItemStack gas);
    }
}