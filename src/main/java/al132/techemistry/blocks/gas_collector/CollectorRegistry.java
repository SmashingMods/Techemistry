package al132.techemistry.blocks.gas_collector;

import al132.techemistry.utils.TUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;

import java.util.ArrayList;
import java.util.List;

public class CollectorRegistry {

    public static List<CollectorRecipe> recipes = new ArrayList<>();

    public static void init() {

        recipes.add(new CollectorRecipe(Ingredient.fromTag(ItemTags.LEAVES), TUtils.toStack("oxygen", 2)));
        recipes.add(new CollectorRecipe(Ingredient.fromItems(Items.COW_SPAWN_EGG),
                TUtils.toStack("methane")));
        recipes.add(new CollectorRecipe(Ingredient.fromStacks(new ItemStack(Blocks.CAMPFIRE)), TUtils.toStack("carbon_dioxide")));


        /*CalcinationRegistry.getRecipes(world).stream()
                .map(recipe -> recipe.getRecipeGas().getItem())
                .filter(item -> item != Items.AIR)
                .distinct()
                .forEach(item -> recipes.add(new CollectorRecipe(Ingredient.fromStacks(new ItemStack(Ref.calcinationChamber)), new ItemStack(item))));
    */
    }
}