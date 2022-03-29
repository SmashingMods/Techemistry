package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.gas_collector.CollectorRecipe;
import al132.techemistry.compat.jei.JEIIntegration;
import com.google.common.collect.Lists;
import mezz.jei.api.constants.VanillaTypes;
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

import java.util.Arrays;


@SuppressWarnings("removal")
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
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.gas_collector"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/gas_collector_gui.png"),
                u, v, 54, 89);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.GAS_COLLECTOR_BLOCK.get()));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CollectorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 79 - u, 74 - v)
                .addIngredientsUnsafe(Arrays.asList(recipe.input.getItems()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 79 - u, 12 - v)
                .addItemStack(recipe.output.copy());
    }
}