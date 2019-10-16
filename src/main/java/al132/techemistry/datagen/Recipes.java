package al132.techemistry.datagen;

import al132.techemistry.Ref;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(Ref.macerator)
                .patternLine("III")
                .patternLine("ICI")
                .patternLine("IFI")
                .key('I', Tags.Items.INGOTS_IRON)
                .key('F', Items.FLINT)
                .key('C', Tags.Items.CHESTS_WOODEN)
                .setGroup("alchemistry2")
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
                .build(consumer);
    }
}
