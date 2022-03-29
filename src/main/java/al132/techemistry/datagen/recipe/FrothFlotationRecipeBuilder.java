package al132.techemistry.datagen.recipe;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.datagen.DatagenUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;


import javax.annotation.Nullable;
import java.util.function.Consumer;

public class FrothFlotationRecipeBuilder extends BaseRecipeBuilder {

    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group = "minecraft:misc";
    private Ingredient input;
    private Ingredient input2;
    private ItemStack result;
    private ItemStack result2;
    private int waterQuantity;

    public FrothFlotationRecipeBuilder(ItemStack result, ItemStack result2) {
        this.result = result;
        this.result2 = result2;
        this.waterQuantity = 1000;
    }

    public static FrothFlotationRecipeBuilder recipe(ItemStack result, ItemStack result2) {
        return new FrothFlotationRecipeBuilder(result, result2);
    }

    public static FrothFlotationRecipeBuilder recipe(ItemStack output) {
        return recipe(output, ItemStack.EMPTY);
    }

    public FrothFlotationRecipeBuilder setWaterQuantity(int amount) {
        this.waterQuantity = amount;
        return this;
    }

    public FrothFlotationRecipeBuilder setInputs(Ingredient input, Ingredient input2) {
        this.input = input;
        this.input2 = input2;
        return this;
    }

    public FrothFlotationRecipeBuilder setInputs(Ingredient input) {
        this.input = input;
        this.input2 = Ingredient.EMPTY;
        return this;
    }


    public void build(Consumer<FinishedRecipe> consumerIn) {
        String name = input.getItems()[0].getItem().getRegistryName().getPath();
        this.build(consumerIn, new ResourceLocation("techemistry", "froth_flotation_chamber/" + name));
    }

    @Override
    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        this.validate(id);
        this.advancementBuilder.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);

        consumerIn.accept(new FrothFlotationRecipeBuilder.Result
                (id, this.result, this.result2, this.group == null ? "" : this.group, this.input, this.input2, this.waterQuantity,
                        this.advancementBuilder, new ResourceLocation(id.getNamespace(),
                        "recipes/" + this.result.getItem().getItemCategory().getRecipeFolderName() + "/" + id.getPath())));

    }

    @Override
    void validate(ResourceLocation id) {

    }

    public class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final ItemStack result;
        private final ItemStack result2;
        private final String group;
        private final ItemStack input;
        private final ItemStack input2;
        private final int waterQuantity;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, ItemStack result, ItemStack result2, String groupIn, Ingredient input, Ingredient input2,
                      int waterQuantity, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.result = result;
            this.result2 = result2;
            this.group = groupIn;
            this.input = input.getItems()[0];
            this.input2 = input2.isEmpty() ? ItemStack.EMPTY : input2.getItems()[0];
            this.waterQuantity = waterQuantity;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) json.addProperty("group", this.group);

            DatagenUtils.addStackToJson(json, "ingredient", input);
            DatagenUtils.addStackToJson(json, "ingredient2", input2);
            DatagenUtils.addStackToJson(json, "result", result);
            DatagenUtils.addStackToJson(json, "result2", result2);

            json.add("waterQuantity", new JsonPrimitive(waterQuantity));
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return Registration.FROTH_FLOTATION_SERIALIZER.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancementBuilder.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}