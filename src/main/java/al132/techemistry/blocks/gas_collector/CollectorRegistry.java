package al132.techemistry.blocks.gas_collector;

import al132.techemistry.utils.TUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class CollectorRegistry {

    public static List<CollectorRecipe> recipes = new ArrayList<>();

    public static void init() {
        //This is an awful hack that won't support modded leaves, how do I use tags properly here?
        Ingredient leaves =
                Ingredient.fromStacks(
                        new ItemStack(Items.OAK_LEAVES),
                        new ItemStack(Items.ACACIA_LEAVES),
                        new ItemStack(Items.BIRCH_LEAVES),
                        new ItemStack(Items.DARK_OAK_LEAVES),
                        new ItemStack(Items.JUNGLE_LEAVES),
                        new ItemStack(Items.ACACIA_LEAVES));
        // Ingredient.fromTag(ItemTags.createOptional(new ResourceLocation("minecraft","leaves")));
        recipes.add(new CollectorRecipe(leaves, TUtils.toStack("oxygen", 2)));
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