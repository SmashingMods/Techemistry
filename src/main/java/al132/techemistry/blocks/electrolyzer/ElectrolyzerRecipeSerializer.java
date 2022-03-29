package al132.techemistry.blocks.electrolyzer;


import al132.techemistry.data.Formula;
import al132.techemistry.data.FormulaParser;
import al132.techemistry.utils.ProcessingRecipe;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ElectrolyzerRecipeSerializer<T extends ElectrolyzerRecipe>
        extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {

    private final int temp;
    private IFactory<T> factory;

    public ElectrolyzerRecipeSerializer(ElectrolyzerRecipeSerializer.IFactory<T> factory, int temp) {
        this.factory = factory;
        this.temp = temp;
    }

    @Override
    public T fromJson(ResourceLocation recipeId, JsonObject json) {
        String s = json.get("group").getAsString();//JSONUtils.getString(json, "group", "");
        String formulaStr = json.get("formula").getAsString();//JSONUtils.getString(json, "formula", "");
        Formula formula = FormulaParser.parse(formulaStr);

        Ingredient input1 = Ingredient.of(formula.inputs.get(0));
        Ingredient input2 = Ingredient.EMPTY;
        if (formula.inputs.size() >= 2) input2 = Ingredient.of(formula.inputs.get(1));
        /*
        JsonElement jsonelement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
        Ingredient input1 = Ingredient.deserialize(jsonelement);
        JsonElement jsonelement2 = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient2") ? JSONUtils.getJsonArray(json, "ingredient2") : JSONUtils.getJsonObject(json, "ingredient"));
        Ingredient input2 = Ingredient.deserialize(jsonelement2);
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
         */
        ItemStack output1 = formula.outputs.get(0);
        ItemStack output2 = ItemStack.EMPTY;
        //System.out.println("formula: " + formulaStr + "\toutputsize: " + formula.outputs.size());
        if (formula.outputs.size() >= 2) output2 = formula.outputs.get(1);
        ItemStack output3 = ItemStack.EMPTY;
        if (formula.outputs.size() >= 3) output3 = formula.outputs.get(2);
        int d = json.get("minimumTemp").getAsInt();//JSONUtils.getInt(json, "minimumTemp", this.temp);

        return this.factory.create(recipeId, s, input1, input2, d, output1, output2, output3);
    }

    @Nullable
    @Override
    public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient input1 = Ingredient.fromNetwork(buffer);
        Ingredient input2 = Ingredient.fromNetwork(buffer);
        double d = buffer.readDouble();
        ItemStack output1 = buffer.readItem();
        ItemStack output2 = buffer.readItem();
        ItemStack output3 = buffer.readItem();
        return this.factory.create(recipeId, s, input1, input2, d, output1, output2, output3);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.getGroup());
        recipe.getIngredients().get(0).toNetwork(buffer);
        recipe.getIngredients().get(1).toNetwork(buffer);
        buffer.writeDouble(recipe.minimumHeat);
        buffer.writeItem(recipe.getOutputs().get(0));
        buffer.writeItem(recipe.output2);
        buffer.writeItem(recipe.output3);
    }

    public interface IFactory<T extends ProcessingRecipe> {
        T create(ResourceLocation resource, String group, Ingredient input1, Ingredient input2, double minimumHeat,
                 ItemStack output1, ItemStack output2, ItemStack output3);
    }
}