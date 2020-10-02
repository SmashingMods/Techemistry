package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.smeltery.FluxRegistry;
import al132.techemistry.blocks.smeltery.SmelteryRecipe;
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
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import static al132.techemistry.capabilities.heat.HeatHelper.TempType.KELVIN;

public class SmelteryRecipeCategory implements IRecipeCategory<SmelteryRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 35;
    private final static int v = 10;

    public SmelteryRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.SMELTERY_RESOURCE;
    }

    @Override
    public Class<? extends SmelteryRecipe> getRecipeClass() {
        return SmelteryRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.format("block.techemistry.smeltery");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.data.MODID, "textures/gui/smeltery_gui.png"),
                u, v, 111, 70);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Ref.smeltery));
    }

    @Override
    public void setIngredients(SmelteryRecipe recipe, IIngredients ingredients) {
        Ingredient flux = Ingredient.fromStacks(FluxRegistry.fluxes.toArray(new ItemStack[0]));
        ingredients.setInputIngredients(Lists.newArrayList(recipe.getIngredients().get(0), Ingredient.fromItems(Ref.coke), flux));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput().copy());
    }

    @Override
    public void draw(SmelteryRecipe recipe, double mouseX, double mouseY) {
        Minecraft.getInstance().fontRenderer.drawString("Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
                45 - u, 2, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SmelteryRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        int x = 43 - u;
        int y = 23 - v;
        guiItemStacks.init(0, true, x, y);
        ItemStack input = recipe.getIngredients().get(0).getMatchingStacks()[0].copy();
        input.setCount(recipe.inputCount);
        guiItemStacks.set(0, Lists.newArrayList(input));

        y += 18;
        guiItemStacks.init(1, true, x, y);
        guiItemStacks.set(1, Lists.newArrayList(new ItemStack(Ref.coke)));
        y += 18;
        guiItemStacks.init(2, true, x, y);
        guiItemStacks.set(2, FluxRegistry.fluxes);

        x = 123 - u;
        y = 32 - v;
        guiItemStacks.init(3, false, x, y);
        guiItemStacks.set(3, recipe.getRecipeOutput().copy());

        y += 18;
        /*
        if (!recipe.output2.isEmpty()) {
            guiItemStacks.init(4, false, x, y);
            guiItemStacks.set(4, recipe.output2.copy());
        }
*/
    }
}