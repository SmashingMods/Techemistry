package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.calcination_chamber.CalcinationRecipe;
import al132.techemistry.blocks.gas_collector.CollectorRecipe;
import al132.techemistry.capabilities.heat.HeatHelper;
import al132.techemistry.compat.jei.JEIIntegration;
import com.google.common.collect.Lists;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.gui.recipes.RecipeLayout;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;


import java.util.Arrays;
import java.util.List;

import static al132.techemistry.capabilities.heat.HeatHelper.TempType.KELVIN;

@SuppressWarnings("removal")
public class CalcinationRecipeCategory implements IRecipeCategory<CalcinationRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 35;
    private final static int v = 10;

    public CalcinationRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.CALCINATION_RESOURCE;
    }

    @Override
    public Class<? extends CalcinationRecipe> getRecipeClass() {
        return CalcinationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.calcination_chamber"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/calcination_gui.png"),
                u, v, 111, 76);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.CALCINATION_CHAMBER_BLOCK.get()));
    }

    /*
    @Override
    public void setIngredients(CalcinationRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        List<ItemStack> outputs = Lists.newArrayList(recipe.getResultItem());
        if (!recipe.getRecipeGas().isEmpty()) outputs.add(recipe.getRecipeGas().copy());
        if (!recipe.getRecipeOutput2().isEmpty()) outputs.add(recipe.getRecipeOutput2().copy());
        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }
*/
    @Override
    public void draw(CalcinationRecipe recipe, PoseStack ps, double mouseX, double mouseY) {
        Minecraft.getInstance().font.draw(ps, "Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
                46 - u, 70, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CalcinationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - u, 50 - v)
                .addItemStacks(Arrays.asList(recipe.getIngredients().get(0).getItems()));
        builder.addSlot(RecipeIngredientRole.INPUT, 82 - u, 12 - v)
                .addItemStack(recipe.getRecipeGas().copy());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 42 - v)
                .addItemStack(recipe.getResultItem().copy());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 60 - v)
                .addItemStack(recipe.getRecipeOutput2().copy());
    }
    /*
    @Override
    public void setRecipe(RecipeLayout recipeLayout, CalcinationRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getLegacyAdapter().getItemStacks();
        guiItemStacks.addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
            if (slotIndex == 1) {
                tooltip.add(new TextComponent("Capture this gas by placing a Gas Collector above the Calcination Chamber"));
            }
        });
        int x = 43 - u;
        int y = 50 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.getIngredients().get(0).getItems()));

        if (!recipe.getRecipeGas().isEmpty()) {
            x = 82 - u;
            y = 12 - v;
            guiItemStacks.init(1, false, x, y);
            guiItemStacks.set(1, recipe.getRecipeGas().copy());
        }

        x = 123 - u;
        y = 41 - v;
        guiItemStacks.init(2, false, x, y);
        guiItemStacks.set(2, recipe.getResultItem().copy());

        if (!recipe.getRecipeOutput2().isEmpty()) {
            x = 123 - u;
            y = 59 - v;
            guiItemStacks.init(3, false, x, y);
            guiItemStacks.set(3, recipe.getRecipeOutput2().copy());
        }
    }*/
}