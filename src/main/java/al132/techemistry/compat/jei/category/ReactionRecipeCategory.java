package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRecipe;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.compat.jei.JEIIntegration;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;

import static al132.techemistry.capabilities.heat.HeatHelper.TempType.KELVIN;

@SuppressWarnings("removal")
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
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.reaction_chamber"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/reaction_chamber_gui.png"),
                u, v, 114, 73);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.REACTION_CHAMBER_BLOCK.get()));
    }
/*
    @Override
    public void setIngredients(ReactionChamberRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = Lists.newArrayList(recipe.input0);
        if (!recipe.input1.isEmpty()) inputs.add(recipe.input1);
        if (!recipe.input2.isEmpty()) inputs.add(recipe.input2);
        ingredients.setInputIngredients(inputs);

        List<ItemStack> outputs = Lists.newArrayList(recipe.output0.copy());
        if (!recipe.output1.isEmpty()) outputs.add(recipe.output1.copy());
        if (!recipe.output2.isEmpty()) outputs.add(recipe.output2.copy());
        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }
    */

    @Override
    public void draw(ReactionChamberRecipe recipe, PoseStack ps, double mouseX, double mouseY) {
        Minecraft.getInstance().font.draw(ps, "Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
                50 - u, 66, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ReactionChamberRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - u, 24 - v)
                .addIngredients(recipe.input0);
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - u, 24 + 18 - v)
                .addIngredients(recipe.input1);
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - u, 24 + 36 - v)
                .addIngredients(recipe.input2);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 24 - v)
                .addItemStack(recipe.output0);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 24 + 18 - v)
                .addItemStack(recipe.output1);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 24 + 36 - v)
                .addItemStack(recipe.output2);
    }
/*
    @Override
    public void setRecipe(RecipeLayout recipeLayout, ReactionChamberRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getLegacyAdapter().getItemStacks();

        int x = 43 - u;
        int y = 23 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.input0.getItems()));

        y += 18;
        if (!recipe.input1.isEmpty()) {
            guiItemStacks.init(1, true, x, y);
            guiItemStacks.set(1, Lists.newArrayList(recipe.input1.getItems()));
        }
        y += 18;
        if (!recipe.input2.isEmpty()) {
            guiItemStacks.init(2, true, x, y);
            guiItemStacks.set(2, Lists.newArrayList(recipe.input2.getItems()));
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
    }*/
}