package al132.techemistry.blocks.gas_collector;

import al132.techemistry.Ref;
import al132.techemistry.blocks.calcination_chamber.CalcinationRegistry;
import al132.techemistry.utils.Utils;
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

        recipes.add(new CollectorRecipe(Ingredient.fromTag(ItemTags.LEAVES), Utils.toStack("oxygen", 2)));
        recipes.add(new CollectorRecipe(Ingredient.fromItems(Items.COW_SPAWN_EGG),
                Utils.toStack("methane")));
        recipes.add(new CollectorRecipe(Ingredient.fromStacks(new ItemStack(Blocks.CAMPFIRE)), Utils.toStack("carbon_dioxide")));

        CalcinationRegistry.recipes.stream()
                .map(recipe -> recipe.gas.getItem())
                .filter(item -> item != Items.AIR)
                .distinct()
                .forEach(item -> recipes.add(new CollectorRecipe(Ingredient.fromStacks(new ItemStack(Ref.calcinationChamber)), new ItemStack(item))));
    }
}