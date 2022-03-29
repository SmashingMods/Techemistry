package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.smeltery.SmelteryRecipe;
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
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.smeltery"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/smeltery_gui.png"),
                u, v, 111, 70);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.SMELTERY_BLOCK.get()));
    }

    /*
        @Override
        public void setIngredients(SmelteryRecipe recipe, IIngredients ingredients) {
            Ingredient flux = Ingredient.of(FluxRegistry.fluxes.toArray(new ItemStack[0]));
            ingredients.setInputIngredients(Lists.newArrayList(recipe.getIngredients().get(0), Ingredient.of(Registration.coke), flux));
            ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem().copy());
        }
    */
    @Override
    public void draw(SmelteryRecipe recipe, PoseStack ps, double mouseX, double mouseY) {
        Minecraft.getInstance().font.draw(ps, "Minimum Heat: " + HeatHelper.format(recipe.minimumHeat, KELVIN),
                45 - u, 2, Ref.TEXT_COLOR);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SmelteryRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - u, 24 - v)
                .addItemStacks(Arrays.asList(recipe.getIngredients().get(0).getItems()[0].copy()));
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - u, 24 + 18 - v)
                .addItemStack(new ItemStack(Registration.COKE_ITEM.get()));
        //TODO fluxes builder.addSlot(RecipeIngredientRole.INPUT, 43 - u, 23 + 36 - v)
        // .addItemStack(new ItemStack(Registration.COKE_ITEM.get()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 33 - v)
                .addItemStack(recipe.getResultItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 18 + 33 - v)
                .addItemStack(recipe.gasOutput);
    }
/*
    @Override
    public void setRecipe(RecipeLayout recipeLayout, SmelteryRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getLegacyAdapter().getItemStacks();

        int x = 43 - u;
        int y = 23 - v;
        guiItemStacks.init(0, true, x, y);
        ItemStack input = recipe.getIngredients().get(0).getItems()[0].copy();
        input.setCount(recipe.inputCount);
        guiItemStacks.set(0, Lists.newArrayList(input));

        y += 18;
        guiItemStacks.init(1, true, x, y);
        guiItemStacks.set(1, Lists.newArrayList(new ItemStack(Registration.coke)));
        y += 18;
        guiItemStacks.init(2, true, x, y);
        guiItemStacks.set(2, FluxRegistry.fluxes);

        x = 123 - u;
        y = 32 - v;
        guiItemStacks.init(3, false, x, y);
        guiItemStacks.set(3, recipe.getResultItem().copy());

        y += 18;
        /*
        if (!recipe.output2.isEmpty()) {
            guiItemStacks.init(4, false, x, y);
            guiItemStacks.set(4, recipe.output2.copy());
        }

    }*/
}