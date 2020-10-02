package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRecipe;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.compat.jei.JEIIntegration;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.List;

import static al132.techemistry.capabilities.heat.HeatHelper.TempType.KELVIN;

public class ReactionRecipeCategory implements IRecipeCategory<ReactionChamberRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 35;
    private final static int v = 16;

    public ReactionRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.REACTION_RESOURCE;
    }

    @Override
    public Class<? extends ReactionChamberRecipe> getRecipeClass() {
        return ReactionChamberRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.format("block.techemistry.reaction_chamber");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.data.MODID, "textures/gui/reaction_chamber_gui.png"),
                u, v, 114, 73);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Ref.reactionChamber));
    }

    @Override
    public void setIngredients(ReactionChamberRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = Lists.newArrayList(recipe.input0);
        if (!recipe.input1.hasNoMatchingItems()) inputs.add(recipe.input1);
        if (!recipe.input2.hasNoMatchingItems()) inputs.add(recipe.input2);
        ingredients.setInputIngredients(inputs);

        List<ItemStack> outputs = Lists.newArrayList(recipe.output0.copy());
        if (!recipe.output1.isEmpty()) outputs.add(recipe.output1.copy());
        if (!recipe.output2.isEmpty()) outputs.add(recipe.output2.copy());
        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }

    @Override
    public void draw(ReactionChamberRecipe recipe, MatrixStack ms, double mouseX, double mouseY) {
        Minecraft.getInstance().fontRenderer.drawString(ms, "Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
                50 - u, 66, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ReactionChamberRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        int x = 43 - u;
        int y = 23 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.input0.getMatchingStacks()));

        y += 18;
        if (!recipe.input1.hasNoMatchingItems()) {
            guiItemStacks.init(1, true, x, y);
            guiItemStacks.set(1, Lists.newArrayList(recipe.input1.getMatchingStacks()));
        }
        y += 18;
        if (!recipe.input2.hasNoMatchingItems()) {
            guiItemStacks.init(2, true, x, y);
            guiItemStacks.set(2, Lists.newArrayList(recipe.input2.getMatchingStacks()));
        }

        x = 123 - u;
        y = 23 - v;
        guiItemStacks.init(3, false, x, y);
        guiItemStacks.set(3, recipe.output0.copy());

        y += 18;
        if (!recipe.output1.isEmpty()) {
            guiItemStacks.init(4, false, x, y);
            guiItemStacks.set(4, recipe.output1.copy());
        }

        y += 18;
        if (!recipe.output2.isEmpty()) {
            guiItemStacks.init(5, false, x, y);
            guiItemStacks.set(5, recipe.output2.copy());
        }
    }
}