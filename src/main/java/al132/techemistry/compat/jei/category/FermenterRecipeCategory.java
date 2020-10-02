package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.fermenter.FermenterRecipe;
import al132.techemistry.compat.jei.JEIIntegration;
import com.google.common.collect.Lists;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class FermenterRecipeCategory implements IRecipeCategory<FermenterRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 32;
    private final static int v = 19;

    public FermenterRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.FERMENTER_RESOURCE;
    }

    @Override
    public Class<? extends FermenterRecipe> getRecipeClass() {
        return FermenterRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.format("block.techemistry.fermenter");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.data.MODID, "textures/gui/fermenter_gui.png"),
                u, v, 133, 69);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Ref.fermenter));
    }

    @Override
    public void setIngredients(FermenterRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = Lists.newArrayList(Ingredient.fromItems(Ref.yeast), recipe.getIngredients().get(0), Ingredient.fromItems(Items.GLASS_BOTTLE));
        ingredients.setInputIngredients(inputs);//VanillaTypes.ITEM, new ItemStack(Ref.yeast));
        ingredients.setInput(VanillaTypes.FLUID, new FluidStack(Fluids.WATER, recipe.waterAmount));

        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput().copy());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FermenterRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();


        int x = 69 - u;
        int y = 26 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, new ItemStack(Ref.yeast));

        y = 44 - v;
        guiItemStacks.init(1, true, x, y);
        guiItemStacks.set(1, Lists.newArrayList(recipe.getIngredients().get(0).getMatchingStacks()));

        y = 62 - v;
        guiItemStacks.init(2, true, x, y);
        guiItemStacks.set(2, new ItemStack(Items.GLASS_BOTTLE));//recipe.output);

        x = 143 - u;
        y = 44 - v;
        guiItemStacks.init(3, false, x, y);
        guiItemStacks.set(3, recipe.getRecipeOutput().copy());

        x = 35 - u;
        y = 23 - v;
        guiFluidStacks.init(0, true, x, y);
        guiFluidStacks.set(0, new FluidStack(Fluids.WATER, recipe.waterAmount));
    }
}