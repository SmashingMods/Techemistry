package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.distillery.DistilleryRecipe;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.compat.jei.JEIIntegration;
import com.google.common.collect.Lists;
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
import net.minecraft.util.ResourceLocation;

import java.util.List;

import static al132.techemistry.capabilities.heat.HeatHelper.TempType.KELVIN;

public class DistilleryRecipeCategory implements IRecipeCategory<DistilleryRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 35;
    private final static int v = 20;

    public DistilleryRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.DISTILLERY_RESOURCE;
    }

    @Override
    public Class<? extends DistilleryRecipe> getRecipeClass() {
        return DistilleryRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.format("block.techemistry.distillery_controller");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.data.MODID, "textures/gui/distillery_gui.png"),
                u, v, 111, 54);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Ref.distilleryController));
    }

    @Override
    public void setIngredients(DistilleryRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        List<ItemStack> outputs = Lists.newArrayList(recipe.getRecipeOutput().copy());
        if (!recipe.output2.isEmpty()) outputs.add(recipe.output2.copy());
        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }

    @Override
    public void draw(DistilleryRecipe recipe, double mouseX, double mouseY) {
        Minecraft.getInstance().fontRenderer.drawString("Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
                34 - u, 35 - u, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DistilleryRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();


        int x = 46 - u;
        int y = 43 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.getIngredients().get(0).getMatchingStacks()));

        x = 126 - u;
        y = 34 - v;
        guiItemStacks.init(1, false, x, y);
        guiItemStacks.set(1, recipe.getRecipeOutput().copy());
        y += 18;
        if (!recipe.output2.isEmpty()) {
            guiItemStacks.init(2, false, x, y);
            guiItemStacks.set(2, recipe.output2.copy());
        }

    }
}