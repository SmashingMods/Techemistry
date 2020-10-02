package al132.techemistry;

import al132.techemistry.blocks.calcination_chamber.CalcinationRecipe;
import al132.techemistry.blocks.distillery.DistilleryRecipe;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerRecipe;
import al132.techemistry.blocks.fermenter.FermenterRecipe;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationRecipe;
import al132.techemistry.blocks.macerator.MaceratorRecipe;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRecipe;
import al132.techemistry.blocks.smeltery.SmelteryRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import static al132.techemistry.Techemistry.MODID;

public class RecipeTypes {
    public static IRecipeType<DistilleryRecipe> DISTILLERY = register(MODID + ":distillery");
    public static IRecipeType<CalcinationRecipe> CALCINATION_CHAMBER = register(MODID + ":calcination_chamber");
    public static IRecipeType<ElectrolyzerRecipe> ELECTROLYZER = register(MODID + ":electrolyzer");
    public static IRecipeType<FermenterRecipe> FERMENTER = register(MODID + ":fermenter");
    public static IRecipeType<FrothFlotationRecipe> FROTH_FLOTATION_CHAMBER = register(MODID + ":froth_flotation_chamber");
    public static IRecipeType<MaceratorRecipe> MACERATOR = register(MODID + ":macerator");
    public static IRecipeType<ReactionChamberRecipe> REACTION_CHAMBER = register(MODID + ":reaction_chamber");
    public static IRecipeType<SmelteryRecipe> SMELTERY = register(MODID + ":smeltery");


    static <T extends IRecipe<?>> IRecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new IRecipeType<T>() {
            public String toString() {
                return key;
            }
        });
    }
}