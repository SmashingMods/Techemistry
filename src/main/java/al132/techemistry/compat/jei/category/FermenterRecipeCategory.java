package al132.techemistry.compat.jei.category;

import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.fermenter.FermenterRecipe;
import al132.techemistry.compat.jei.JEIIntegration;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;

@SuppressWarnings("removal")
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
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.fermenter"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/fermenter_gui.png"),
                u, v, 133, 69);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.FERMENTER_BLOCK.get()));
    }
/*
    @Override
    public void setIngredients(FermenterRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = Lists.newArrayList(Ingredient.of(Registration.yeast), recipe.getIngredients().get(0), Ingredient.of(Items.GLASS_BOTTLE));
        ingredients.setInputIngredients(inputs);//VanillaTypes.ITEM, new ItemStack(Registration.yeast));
        ingredients.setInput(VanillaTypes.FLUID, new FluidStack(Fluids.WATER, recipe.waterAmount));

        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem().copy());
    }*/

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FermenterRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 70 - u, 27 - v)
                .addItemStack(new ItemStack(Registration.YEAST_ITEM.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 70 - u, 45 - v)
                .addIngredientsUnsafe(Arrays.asList(recipe.getIngredients().get(0).getItems()));
        builder.addSlot(RecipeIngredientRole.INPUT, 70 - u, 63 - v)
                .addItemStack(new ItemStack(Items.GLASS_BOTTLE));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 144 - u, 45 - v)
                .addItemStack(recipe.getResultItem().copy());
        builder.addSlot(RecipeIngredientRole.INPUT, 36 - u, 24 - v)
                .setFluidRenderer(recipe.waterAmount, false,16,60);
    }
/*
    @Override
    public void setRecipe(RecipeLayout recipeLayout, FermenterRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getLegacyAdapter().getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getLegacyAdapter().getFluidStacks();


        int x = 69 - u;
        int y = 26 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, new ItemStack(Registration.yeast));

        y = 44 - v;
        guiItemStacks.init(1, true, x, y);
        guiItemStacks.set(1, Lists.newArrayList(recipe.getIngredients().get(0).getItems()));

        y = 62 - v;
        guiItemStacks.init(2, true, x, y);
        guiItemStacks.set(2, new ItemStack(Items.GLASS_BOTTLE));//recipe.output);

        x = 143 - u;
        y = 44 - v;
        guiItemStacks.init(3, false, x, y);
        guiItemStacks.set(3, recipe.getResultItem().copy());

        x = 35 - u;
        y = 23 - v;
        guiFluidStacks.init(0, true, x, y);
        guiFluidStacks.set(0, new FluidStack(Fluids.WATER, recipe.waterAmount));
    }*/
}