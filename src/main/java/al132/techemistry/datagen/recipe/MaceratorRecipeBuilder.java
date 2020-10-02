package al132.techemistry.datagen.recipe;

import al132.techemistry.Ref;
import al132.techemistry.blocks.macerator.WeightedItemStack;
import al132.techemistry.datagen.DatagenUtils;
import com.google.gson.JsonArray;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MaceratorRecipeBuilder extends BaseRecipeBuilder {

    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
    private String group = "minecraft:misc";
    private Ingredient input;
    private List<WeightedItemStack> result = new ArrayList<>();
    private ItemStack result2 = ItemStack.EMPTY;
    private int tier;
    private int minimumTemp = 273;

    public MaceratorRecipeBuilder(Ingredient input) {//List<WeightedItemStack> result, ItemStack result2) {
        this.input = input;
        //this.result = result;
        //this.result2 = result2;
    }

    public static MaceratorRecipeBuilder recipe(Ingredient input) {
        return new MaceratorRecipeBuilder(input);
    }

    public static MaceratorRecipeBuilder recipe(Item input) {
        return new MaceratorRecipeBuilder(Ingredient.fromItems(input));
    }

    public MaceratorRecipeBuilder addWeightedResult(ItemStack stack, int weight) {
        this.result.add(new WeightedItemStack(stack, weight));
        return this;
    }

    public MaceratorRecipeBuilder setResult2(ItemStack stack){
        this.result2 = stack;
        return this;
    }

    public MaceratorRecipeBuilder addWeightedResult(Item item, int weight) {
        this.result.add(new WeightedItemStack(new ItemStack(item), weight));
        return this;
    }

/*
    public static MaceratorRecipeBuilder recipe(List<WeightedItemStack> result) {
        return new MaceratorRecipeBuilder(result, ItemStack.EMPTY);

    }

    public static MaceratorRecipeBuilder recipe(ItemStack output) {
        return recipe(output, ItemStack.EMPTY);
    }


    public static MaceratorRecipeBuilder recipe(ItemStack output, ItemStack output2) {
        List<WeightedItemStack> temp = new ArrayList<>();
        temp.add(new WeightedItemStack(output, 1));
        return new MaceratorRecipeBuilder(temp, output2);
    }

    public MaceratorRecipeBuilder setInput(Ingredient input) {
        this.input = input;
        return this;
    }*/

    public MaceratorRecipeBuilder setTier(int tier) {
        this.tier = tier;
        return this;
    }


    public void build(Consumer<IFinishedRecipe> consumerIn) {
        String name = input.getMatchingStacks()[0].getItem().getRegistryName().getPath();
        this.build(consumerIn, new ResourceLocation("techemistry", "macerator/" + name));
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        this.validate(id);
        this.advancementBuilder.withParentId(new ResourceLocation("recipes/root"))
                .withCriterion("has_the_recipe", new RecipeUnlockedTrigger.Instance(id))
                .withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);

        consumerIn.accept(new MaceratorRecipeBuilder.Result
                (id, this.result, this.result2, this.group == null ? "" : this.group, this.input, this.tier,
                        this.advancementBuilder, new ResourceLocation(id.getNamespace(),
                        "recipes/" + this.input.getMatchingStacks()[0].getItem().getGroup().getPath() + "/" + id.getPath())));

    }

    @Override
    void validate(ResourceLocation id) {

    }

    public class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final List<WeightedItemStack> result;
        private final ItemStack result2;
        private final int tier;
        private final String group;
        private final ItemStack input;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, List<WeightedItemStack> result, ItemStack result2, String groupIn, Ingredient input, int tier,
                      Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.result = result;
            this.result2 = result2;
            this.group = groupIn;
            this.input = input.getMatchingStacks()[0];
            this.tier = tier;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        public void serialize(JsonObject json) {
            if (!this.group.isEmpty()) json.addProperty("group", this.group);

            DatagenUtils.addStackToJson(json, "ingredient", input);
            JsonArray temp = new JsonArray();
            for (WeightedItemStack wStack : result) {
                JsonObject entry = new JsonObject();
                entry.addProperty("item", Registry.ITEM.getKey(wStack.stack.getItem()).toString());
                if (wStack.stack.getCount() > 1) entry.addProperty("count", wStack.stack.getCount());
                entry.addProperty("weight", wStack.weight);
                temp.add(entry);
            }
            json.add("result", temp);
            DatagenUtils.addStackToJson(json, "result2", result2);
            json.add("tier", new JsonPrimitive(tier));
        }

        public ResourceLocation getID() {
            return this.id;
        }

        public IRecipeSerializer<?> getSerializer() {
            return Ref.MACERATOR_SERIALIZER;
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