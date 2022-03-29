package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.macerator.MaceratorRecipe;
import al132.techemistry.compat.jei.JEIIntegration;
import al132.techemistry.utils.RecipeUtils;
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
import java.util.stream.Collectors;

@SuppressWarnings("removal")
public class MaceratorRecipeCategory implements IRecipeCategory<MaceratorRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 35;
    private final static int v = 10;

    public MaceratorRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return JEIIntegration.MACERATOR_RESOURCE;
    }

    @Override
    public Class<? extends MaceratorRecipe> getRecipeClass() {
        return MaceratorRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent(I18n.get("block.techemistry.macerator"));
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.MODID, "textures/gui/macerator_gui.png"),
                u, v, 111, 74);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Registration.MACERATOR_BLOCK.get()));
    }
/*
    @Override
    public void setIngredients(MaceratorRecipe recipe, IIngredients ingredients) {
        List<List<ItemStack>> inputs = Lists.newArrayList(Lists.newArrayList());
        inputs.add(Lists.newArrayList(recipe.getIngredients().get(0).getItems()));
        inputs.add(Registration.gears.stream()
                .filter(gear -> gear.material.tier >= recipe.tier)
                .map(ItemStack::new)
                .collect(Collectors.toList()));
        ingredients.setInputLists(VanillaTypes.ITEM, inputs);

        List<List<ItemStack>> outputs = Lists.newArrayList(Lists.newArrayList());
        outputs.add(recipe.getAllSlot1Stacks());
        if (!recipe.output2.isEmpty()) outputs.add(Lists.newArrayList(recipe.output2));
        ingredients.setOutputLists(VanillaTypes.ITEM, outputs);
    }*/

    @Override
    public void draw(MaceratorRecipe recipe, PoseStack ps, double mouseX, double mouseY) {
        Minecraft.getInstance().font.draw(ps, "Tier: " + recipe.tier, 68 - u, 62 - u, Ref.TEXT_COLOR);
        if (recipe.useEfficiency) {
            Minecraft.getInstance().font.draw(ps, "Uses Efficiency", 42 - u, 92 - u, Ref.TEXT_COLOR);
        }

    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MaceratorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - u, 43 - v)
                .addItemStacks(Arrays.asList(recipe.getIngredients().get(0).getItems()));
        builder.addSlot(RecipeIngredientRole.INPUT, 84 - u, 16 - v)
                .addItemStacks(Ref.gears.stream().map(ItemStack::new).collect(Collectors.toList()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 43 - v)
                .addItemStacks(RecipeUtils.flatten(recipe.output));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - u, 43 + 18 - v)
                .addItemStack(recipe.output2);
    }
/*
    @Override
    public void setRecipe(RecipeLayout recipeLayout, MaceratorRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getLegacyAdapter().getItemStacks();


        int x = 43 - u;
        int y = 42 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.getIngredients().get(0).getItems()));

        x = 83 - u;
        y = 15 - v;
        guiItemStacks.init(1, true, x, y);
        guiItemStacks.set(1, Registration.gears.stream()
                .filter(gear -> gear.material.tier >= recipe.tier)
                .map(ItemStack::new)
                .collect(Collectors.toList()));

        x = 123 - u;
        y = 42 - v;
        guiItemStacks.init(2, false, x, y);
        guiItemStacks.set(2, recipe.getAllSlot1Stacks());

        y += 18;
        if (!recipe.output2.isEmpty()) {
            guiItemStacks.init(3, false, x, y);
            guiItemStacks.set(3, recipe.output2.copy());
        }
    }*/
}