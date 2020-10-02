package al132.techemistry.compat.jei.category;

import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.macerator.MaceratorRecipe;
import al132.techemistry.compat.jei.JEIIntegration;
import al132.techemistry.items.parts.PartMaterialRegistry;
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
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

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
    public String getTitle() {
        return I18n.format("block.techemistry.macerator");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Techemistry.data.MODID, "textures/gui/macerator_gui.png"),
                u, v, 111, 74);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Ref.macerator));
    }

    @Override
    public void setIngredients(MaceratorRecipe recipe, IIngredients ingredients) {
        List<List<ItemStack>> inputs = Lists.newArrayList(Lists.newArrayList());
        inputs.add(Lists.newArrayList(recipe.getIngredients().get(0).getMatchingStacks()));
        inputs.add(Ref.gears.stream()
                .filter(gear -> gear.material.tier >= recipe.tier)
                .map(ItemStack::new)
                .collect(Collectors.toList()));
        ingredients.setInputLists(VanillaTypes.ITEM, inputs);

        List<List<ItemStack>> outputs = Lists.newArrayList(Lists.newArrayList());
        outputs.add(recipe.getAllSlot1Stacks());
        if (!recipe.output2.isEmpty()) outputs.add(Lists.newArrayList(recipe.output2));
        ingredients.setOutputLists(VanillaTypes.ITEM, outputs);
    }

    @Override
    public void draw(MaceratorRecipe recipe, double mouseX, double mouseY) {
        Minecraft.getInstance().fontRenderer.drawString("Tier: " + recipe.tier, 68 - u, 62 - u, Ref.TEXT_COLOR);
        if (recipe.useEfficiency) {
            Minecraft.getInstance().fontRenderer.drawString("Uses Efficiency", 42 - u, 92 - u, Ref.TEXT_COLOR);
        }

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MaceratorRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();


        int x = 43 - u;
        int y = 42 - v;
        guiItemStacks.init(0, true, x, y);
        guiItemStacks.set(0, Lists.newArrayList(recipe.getIngredients().get(0).getMatchingStacks()));

        x = 83 - u;
        y = 15 - v;
        guiItemStacks.init(1, true, x, y);
        guiItemStacks.set(1, Ref.gears.stream()
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
    }
}