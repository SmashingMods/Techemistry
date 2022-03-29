package al132.techemistry.blocks.froth_flotation_chamber;

import al132.techemistry.utils.ProcessingRecipe;
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

public class FrothFlotationRecipeSerializer<T extends FrothFlotationRecipe>
        extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {

    private final int waterAmount;
    private IFactory<T> factory;

    public FrothFlotationRecipeSerializer(FrothFlotationRecipeSerializer.IFactory<T> factory, int waterAmount) {
        this.factory = factory;
        this.waterAmount = waterAmount;
    }

    @Override
    public T fromJson(ResourceLocation recipeId, JsonObject json) {
        String s = json.get("group").getAsString();//JSONUtils.getString(json, "group", "");
        JsonElement jsonelement = (JsonElement) (json.get("ingredient").isJsonArray()
                ? json.getAsJsonArray("ingredient")
                : json.getAsJsonObject("ingredient"));
        Ingredient ingredient = Ingredient.fromJson(jsonelement);
        //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
        if (!json.has("result"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack output1;
        if (json.get("result").isJsonObject())
            output1 = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));//.getJsonObject(json, "result"));
        else {
            String s1 = json.get("result").getAsString();//JSONUtils.getString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            output1 = new ItemStack(Registry.ITEM.get(resourcelocation));
        }
        int waterAmount = 1000;
        if(json.has("waterAmount")) waterAmount = json.get("waterAmount").getAsInt();
        //json.get("waterAmount").getAsInt();//JSONUtils.getInt(json, "waterAmount", this.waterAmount);
        Ingredient ingredient2 = Ingredient.EMPTY;
        ItemStack output2 = ItemStack.EMPTY;
        return this.factory.create(recipeId, s, ingredient, ingredient2, waterAmount, output1, output2);
    }

    @Nullable
    @Override
    public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        Ingredient ingredient2 = Ingredient.EMPTY;//Ingredient.read(buffer);
        ItemStack output1 = buffer.readItem();
        ItemStack output2 = ItemStack.EMPTY;//buffer.readItemStack();
        int waterAmount = buffer.readInt();
        return this.factory.create(recipeId, s, ingredient, ingredient2, waterAmount, output1, output2);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.getGroup());
        recipe.getIngredients().get(0).toNetwork(buffer);
        buffer.writeItem(recipe.getResultItem());
        buffer.writeInt(recipe.water);
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation id, String group, Ingredient input, Ingredient input2, int water, ItemStack output, ItemStack output2);
    }
}