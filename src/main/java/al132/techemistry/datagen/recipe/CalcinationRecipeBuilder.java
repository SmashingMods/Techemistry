package al132.techemistry.datagen.recipe;

import al132.techemistry.Ref;
import al132.techemistry.datagen.DatagenUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class CalcinationRecipeBuilder extends BaseRecipeBuilder {

    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
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


    public void build(Consumer<IFinishedRecipe> consumerIn) {
        String name = input.getMatchingStacks()[0].getItem().getRegistryName().getPath();
        this.build(consumerIn, new ResourceLocation("techemistry", "calcination_chamber/" + name));
        //        Registry.ITEM.getKey(this.result));
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        this.validate(id);
        this.advancementBuilder.withParentId(new ResourceLocation("recipes/root"))
                .withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id))
                .withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);

        consumerIn.accept(new CalcinationRecipeBuilder.Result
                (id, this.result, this.group == null ? "" : this.group, this.input, this.gas, this.minimumTemp,
                        this.advancementBuilder, new ResourceLocation(id.getNamespace(),
                        "recipes/" + this.result.getItem().getGroup().getPath() + "/" + id.getPath())));

    }

    @Override
    void validate(ResourceLocation id) {

    }

    public class Result implements IFinishedRecipe {
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
            this.input = input.getMatchingStacks()[0];
            this.gas = gas;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
            this.minimumTemp = minimumTemp;
        }

        public void serialize(JsonObject json) {
            if (!this.group.isEmpty()) json.addProperty("group", this.group);

            DatagenUtils.addStackToJson(json, "ingredient", input);
            DatagenUtils.addStackToJson(json, "result", result);
            DatagenUtils.addStackToJson(json, "gas", gas);
            json.add("minimumTemp", new JsonPrimitive(minimumTemp));
        }

        public ResourceLocation getID() {
            return this.id;
        }

        public IRecipeSerializer<?> getSerializer() {
            return Ref.CALCINATION_SERIALIZER;
        }

        @Nullable
        @Override
        public JsonObject getAdvancementJson() {
            return this.advancementBuilder.serialize();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementID() {
            return this.advancementId;
        }
    }
}