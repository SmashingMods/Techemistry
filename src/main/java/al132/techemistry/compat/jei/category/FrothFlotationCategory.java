package al132.techemistry.compat.jei.category;

import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationRecipe;
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


@SuppressWarnings("removal")
public class FrothFlotationCategory implements IRecipeCategory<FrothFlotationRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 31;
    private final static int v = 20;

    public FrothFlotationCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.FROTH_RESOURCE;
    }

    @Override
    public Class<? extends FrothFlotationRecipe> getRecipeClass() {
        return FrothFlotationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.froth_flotation_chamber"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/froth_flotation_gui.png"),
                u, v, 126, 66);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.FROTH_FLOTATION_BLOCK.get()));
    }

    /*
        @Override
        public void setIngredients(FrothFlotationRecipe recipe, IIngredients ingredients) {
            List<Ingredient> inputs = Lists.newArrayList(recipe.input);
            if (!recipe.input2.isEmpty()) inputs.add(recipe.input2);
            ingredients.setInput(VanillaTypes.FLUID, new FluidStack(Fluids.WATER, recipe.water));
            ingredients.setInputIngredients(inputs);

            List<ItemStack> outputs = Lists.newArrayList(recipe.output.copy());
            if (!recipe.output2.isEmpty()) outputs.add(recipe.output2.copy());
            ingredients.setOutputs(VanillaTypes.ITEM, outputs);
        }
    */
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FrothFlotationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 32 - u, 22 - v)
                .setFluidRenderer(1000, false, 16, 60);
        builder.addSlot(RecipeIngredientRole.INPUT, 72 - u, 41 - v)
                .addIngredients(recipe.input);
        builder.addSlot(RecipeIngredientRole.INPUT, 72 - u, 18 + 41 - v)
                .addIngredients(recipe.input2);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 138 - u, 41 - v)
                .addItemStack(recipe.output);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 123 - u, 59 - v)
                .addItemStack(recipe.output2);
    }
/*
    @Override
    public void setRecipe(RecipeLayout recipeLayout, FrothFlotationRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiFluidStacks.init(0,true,32-u,22-v);
        guiFluidStacks.set(0,new FluidStack(Fluids.WATER,recipe.water));

        int x = 72 - u;
        int y = 41 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.input.getItems()));
        y += 18;
        if (!recipe.input2.hasNoMatchingItems()) {
            guiItemStacks.init(1, true, x, y);
            guiItemStacks.set(1, Lists.newArrayList(recipe.input2.getItems()));
        }

        x = 138 - u;
        y = 41 - v;
        guiItemStacks.init(2, false, x, y);
        guiItemStacks.set(2, recipe.output.copy());

        if (!recipe.output2.isEmpty()) {
            x = 123 - u;
            y = 59 - v;
            guiItemStacks.init(3, false, x, y);
            guiItemStacks.set(3, recipe.output2.copy());
        }
    }*/
}