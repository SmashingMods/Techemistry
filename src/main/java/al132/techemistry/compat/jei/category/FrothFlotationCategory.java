package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationRecipe;
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
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

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
    public String getTitle() {
        return I18n.format("block.techemistry.froth_flotation_chamber");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.data.MODID, "textures/gui/froth_flotation_gui.png"),
                u, v, 126, 66);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Ref.frothFlotationChamber));
    }

    @Override
    public void setIngredients(FrothFlotationRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = Lists.newArrayList(recipe.input);
        if (!recipe.input2.hasNoMatchingItems()) inputs.add(recipe.input2);
        ingredients.setInput(VanillaTypes.FLUID, new FluidStack(Fluids.WATER, recipe.water));
        ingredients.setInputIngredients(inputs);

        List<ItemStack> outputs = Lists.newArrayList(recipe.output.copy());
        if (!recipe.output2.isEmpty()) outputs.add(recipe.output2.copy());
        ingredients.setOutputs(VanillaTypes.ITEM, outputs);

    }

    @Override
    public void draw(FrothFlotationRecipe recipe, double mouseX, double mouseY) {
        //Minecraft.getInstance().fontRenderer.drawString("Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
        //        46 - u, 70, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FrothFlotationRecipe recipe, IIngredients ingredients) {

        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiFluidStacks.init(0,true,32-u,22-v);
        guiFluidStacks.set(0,new FluidStack(Fluids.WATER,recipe.water));

        int x = 72 - u;
        int y = 41 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.input.getMatchingStacks()));
        y += 18;
        if (!recipe.input2.hasNoMatchingItems()) {
            guiItemStacks.init(1, true, x, y);
            guiItemStacks.set(1, Lists.newArrayList(recipe.input2.getMatchingStacks()));
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
    }
}