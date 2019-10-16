package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.gas_collector.CollectorRecipe;
import al132.techemistry.compat.jei.JEIIntegration;
import com.google.common.collect.Lists;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CollectorRecipeCategory implements IRecipeCategory<CollectorRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 60;
    private final static int v = 7;

    public CollectorRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.COLLECTOR_RESOURCE;
    }

    @Override
    public Class<? extends CollectorRecipe> getRecipeClass() {
        return CollectorRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.format("block.techemistry.gas_collector");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.data.MODID, "textures/gui/gas_collector_gui.png"),
                u, v, 54, 89);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Ref.gasCollector));
    }

    @Override
    public void setIngredients(CollectorRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(Lists.newArrayList(recipe.input));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.output.copy());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CollectorRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();


        int x = 79 - u;
        int y = 74 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.input.getMatchingStacks()));

        y = 12 - v;
        guiItemStacks.init(1, false, x, y);
        guiItemStacks.set(1, recipe.output.copy());
    }
}