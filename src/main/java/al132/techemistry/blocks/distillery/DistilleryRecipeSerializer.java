package al132.techemistry.blocks.distillery;

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

public class DistilleryRecipeSerializer<T extends DistilleryRecipe>
        extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {

    private final int temp;
    private IFactory<T> factory;

    public DistilleryRecipeSerializer(DistilleryRecipeSerializer.IFactory<T> factory, int temp) {
        this.factory = factory;
        this.temp = temp;
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
            String s1 = json.get("result").getAsString();//JSONUtils.getString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            output1 = new ItemStack(Registry.ITEM.get(resourcelocation));
        }
        ItemStack output2 = ItemStack.EMPTY;
        if (json.has("result2")) {
            if (json.get("result2").isJsonObject())
                output2 = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result2"));
            else {
                String s1 = json.get("result2").getAsString();//JSONUtils.getString(json, "result2");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                output2 = new ItemStack(Registry.ITEM.get(resourcelocation));
            }
        }
        int d = json.get("minimumTemp").getAsInt();//JSONUtils.getInt(json, "minimumTemp", this.temp);
        return this.factory.create(recipeId, s, ingredient, d, output1, output2);
    }

    @Nullable
    @Override
    public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack output1 = buffer.readItem();
        ItemStack output2 = buffer.readItem();
        double d = buffer.readDouble();
        return this.factory.create(recipeId, s, ingredient, d, output1, output2);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.getGroup());
        recipe.getIngredients().get(0).toNetwork(buffer);
        buffer.writeItem(recipe.getResultItem());
        buffer.writeItem(recipe.getRecipeOutput2());
        buffer.writeDouble(recipe.minimumHeat);
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation resource, String group, Ingredient input, double temp, ItemStack output1, ItemStack output2);
    }
}