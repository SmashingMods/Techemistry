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

public class CalcinationRecipeBuilder extends BaseRecipeBuilder {

    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group = "minecraft:misc";
    private Ingredient input;
    private ItemStack result;
    private ItemStack gas;
    private int minimumTemp = 273;

    public CalcinationRecipeBuilder(ItemStack result) {
        this.result = result;
    }

    public static CalcinationRecipeBuilder recipe(ItemStack output) {
        return new CalcinationRecipeBuilder(output);
    }

    /*
        public CalcinationRecipeBuilder setGroup(String groupIn) {
            this.group = groupIn;

            return this;
        }
    */
    public CalcinationRecipeBuilder setInput(Ingredient input) {
        this.input = input;
        return this;
    }

    public CalcinationRecipeBuilder setGas(ItemStack gas) {
        this.gas = gas;
        return this;
    }

    public CalcinationRecipeBuilder setMinimumTemp(int minimumTemp) {
        this.minimumTemp = minimumTemp;
        return this;
    }


    public void build(Consumer<FinishedRecipe> consumerIn) {
        String name = input.getItems()[0].getItem().getRegistryName().getPath();
        this.build(consumerIn, new ResourceLocation("techemistry", "calcination_chamber/" + name));
        //        Registry.ITEM.getKey(this.result));
    }

    @Override
    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        this.validate(id);
        this.advancementBuilder.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);

        consumerIn.accept(new CalcinationRecipeBuilder.Result
                (id, this.result, this.group == null ? "" : this.group, this.input, this.gas, this.minimumTemp,
                        this.advancementBuilder, new ResourceLocation(id.getNamespace(),
                        "recipes/" + this.result.getItem().getItemCategory().getRecipeFolderName() + "/" + id.getPath())));

    }

    @Override
    void validate(ResourceLocation id) {

    }

    public class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final ItemStack result;
        private final int minimumTemp;
        private final ItemStack gas;
        private final String group;
        private final ItemStack input;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, ItemStack result, String groupIn, Ingredient input, ItemStack gas,
                      int minimumTemp, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.result = result;
            this.group = groupIn;
            this.input = input.getItems()[0];
            this.gas = gas;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
            this.minimumTemp = minimumTemp;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) json.addProperty("group", this.group);

            DatagenUtils.addStackToJson(json, "ingredient", input);
            DatagenUtils.addStackToJson(json, "result", result);
            DatagenUtils.addStackToJson(json, "gas", gas);
            json.add("minimumTemp", new JsonPrimitive(minimumTemp));
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return Registration.CALCINATION_SERIALIZER.get();
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