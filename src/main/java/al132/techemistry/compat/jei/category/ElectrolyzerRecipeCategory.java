package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerRecipe;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;


import java.util.List;

import static al132.techemistry.capabilities.heat.HeatHelper.TempType.KELVIN;


@SuppressWarnings("removal")
public class ElectrolyzerRecipeCategory implements IRecipeCategory<ElectrolyzerRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 34;
    private final static int v = 8;

    public ElectrolyzerRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.ELECTROLYZER_RESOURCE;
    }

    @Override
    public Class<? extends ElectrolyzerRecipe> getRecipeClass() {
        return ElectrolyzerRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.electrolyzer"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/electrolyzer_gui.png"),
                u, v, 127, 81);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.ELECTROLYZER_BLOCK.get()));
    }

    /*
    @Override
    public void setIngredients(ElectrolyzerRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = Lists.newArrayList(recipe.getInput1());
        if (!recipe.input2.isEmpty()) inputs.add(recipe.input2);
        inputs.add(recipe.cathode);//.cathode));
        inputs.add(Ingredient.of(Registration.platinumElectrode));//.anode));
        ingredients.setInputIngredients(inputs);

        List<ItemStack> outputs = Lists.newArrayList(recipe.getOutput1().copy());
        if (!recipe.output2.isEmpty()) outputs.add(recipe.output2);
        if (!recipe.output3.isEmpty()) outputs.add(recipe.output3);
        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }
    */
    @Override
    public void draw(ElectrolyzerRecipe recipe, PoseStack ps, double mouseX, double mouseY) {
        Minecraft.getInstance().font.draw(ps, "Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
                35 - u, 72, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ElectrolyzerRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 39 - u, 49 - v)
                .addIngredients(recipe.getInput1());
        builder.addSlot(RecipeIngredientRole.INPUT, 80 - u, 49 - v)
                .addIngredients(Ingredient.of(recipe.input2.getItems()));
    }
    /*
    @Override
    public void setRecipe(RecipeLayout recipeLayout, ElectrolyzerRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getLegacyAdapter().getItemStacks();


        int x = 39 - u;
        int y = 49 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.getInput1().getMatchingStacks()));
        if (!recipe.input2.hasNoMatchingItems()) {
            x = 80 - u;
            guiItemStacks.init(1, true, x, y);
            guiItemStacks.set(1, Lists.newArrayList(recipe.input2.getMatchingStacks()));
        }

        x = 38 - u;
        y = 10 - v;
        guiItemStacks.init(2, true, x, y);
        guiItemStacks.set(2, recipe.anode.getMatchingStacks()[0].copy());

        x = 80 - u;
        y = 10 - v;
        guiItemStacks.init(3, true, x, y);
        guiItemStacks.set(3, recipe.cathode.getMatchingStacks()[0].copy());

        x = 142 - u;
        y = 33 - v;
        guiItemStacks.init(4, false, x, y);
        guiItemStacks.set(4, recipe.getOutput1().copy());

        if (!recipe.output2.isEmpty()) {
            y += 18;
            guiItemStacks.init(5, false, x, y);
            guiItemStacks.set(5, recipe.output2.copy());
        }

        if (!recipe.output3.isEmpty()) {
            y += 18;
            guiItemStacks.init(6, false, x, y);
            guiItemStacks.set(6, recipe.output3.copy());
        }

    }*/
}