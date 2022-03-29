package al132.techemistry.blocks.fermenter;

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

public class FermenterRecipeSerializer<T extends FermenterRecipe>
        extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {

    private final int waterAmount;
    private IFactory<T> factory;

    public FermenterRecipeSerializer(FermenterRecipeSerializer.IFactory<T> factory, int waterAmount) {
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
            output1 = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
        else {
            String s1 = json.get("result").getAsString();//, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            output1 = new ItemStack(Registry.ITEM.get(resourcelocation));
        }
        int i = json.get("waterAmount").getAsInt();//JSONUtils.getInt(json, "waterAmount", this.waterAmount);
        return this.factory.create(recipeId, s, ingredient, output1, i);
    }

    @Nullable
    @Override
    public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack output1 = buffer.readItem();
        int i = buffer.readInt();
        return this.factory.create(recipeId, s, ingredient, output1, i);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.getGroup());
        recipe.getIngredients().get(0).toNetwork(buffer);
        buffer.writeItem(recipe.getResultItem());
        buffer.writeInt(recipe.waterAmount);
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation resource, String group, Ingredient input1, ItemStack output1, int waterAmount);
    }
}