package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.distillery.DistilleryRecipe;
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
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.distillery_controller"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/distillery_gui.png"),
                u, v, 111, 54);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.DISTILLERY_CONTROLLER_BLOCK.get()));
    }
/*
    @Override
    public void setIngredients(DistilleryRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        List<ItemStack> outputs = Lists.newArrayList(recipe.getResultItem().copy());
        if (!recipe.output2.isEmpty()) outputs.add(recipe.output2.copy());
        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }*/

    @Override
    public void draw(DistilleryRecipe recipe, PoseStack ps, double mouseX, double mouseY) {
        Minecraft.getInstance().font.draw(ps, "Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
                36 - u, 35 - u, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DistilleryRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 47 - u, 44 - v)
                .addItemStacks(Arrays.asList(recipe.getIngredients().get(0).getItems()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 127 - u, 35 - v)
                .addItemStack(recipe.getResultItem().copy());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 127 - u, 35 + 18 - v)
                .addItemStack(recipe.getRecipeOutput2().copy());
    }

    /*
    @Override
    public void setRecipe(RecipeLayout recipeLayout, DistilleryRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getLegacyAdapter().getItemStacks();


        int x = 46 - u;
        int y = 43 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.getIngredients().get(0).getItems()));

        x = 126 - u;
        y = 34 - v;
        guiItemStacks.init(1, false, x, y);
        guiItemStacks.set(1, recipe.getResultItem().copy());
        y += 18;
        if (!recipe.output2.isEmpty()) {
            guiItemStacks.init(2, false, x, y);
            guiItemStacks.set(2, recipe.output2.copy());
        }
    }*/
}