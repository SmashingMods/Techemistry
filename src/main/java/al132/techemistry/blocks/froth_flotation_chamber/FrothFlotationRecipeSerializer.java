package al132.techemistry.blocks.froth_flotation_chamber;

import al132.techemistry.utils.ProcessingRecipe;
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

public class FrothFlotationRecipeSerializer<T extends FrothFlotationRecipe>
        extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final int waterAmount;
    private IFactory<T> factory;

    public FrothFlotationRecipeSerializer(FrothFlotationRecipeSerializer.IFactory<T> factory, int waterAmount) {
        this.factory = factory;
        this.waterAmount = waterAmount;
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
        int waterAmount = JSONUtils.getInt(json, "waterAmount", this.waterAmount);
        Ingredient ingredient2 = Ingredient.EMPTY;
        ItemStack output2 = ItemStack.EMPTY;
        return this.factory.create(recipeId, s, ingredient, ingredient2, waterAmount, output1, output2);
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readString(32767);
        Ingredient ingredient = Ingredient.read(buffer);
        Ingredient ingredient2 = Ingredient.EMPTY;//Ingredient.read(buffer);
        ItemStack output1 = buffer.readItemStack();
        ItemStack output2 = ItemStack.EMPTY;//buffer.readItemStack();
        int waterAmount = buffer.readInt();
        return this.factory.create(recipeId, s, ingredient, ingredient2, waterAmount, output1, output2);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeString(recipe.getGroup());
        recipe.getIngredients().get(0).write(buffer);
        buffer.writeItemStack(recipe.getRecipeOutput());
        buffer.writeInt(recipe.water);
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation id, String group, Ingredient input, Ingredient input2, int water, ItemStack output, ItemStack output2);
    }
}