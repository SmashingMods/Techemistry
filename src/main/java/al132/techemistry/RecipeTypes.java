package al132.techemistry;

import al132.techemistry.blocks.calcination_chamber.CalcinationRecipe;
import al132.techemistry.blocks.distillery.DistilleryRecipe;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerRecipe;
import al132.techemistry.blocks.fermenter.FermenterRecipe;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationRecipe;
import al132.techemistry.blocks.macerator.MaceratorRecipe;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRecipe;
import al132.techemistry.blocks.smeltery.SmelteryRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;


import static al132.techemistry.Techemistry.MODID;

public class RecipeTypes {
    public static RecipeType<DistilleryRecipe> DISTILLERY;// = RecipeType.register(MODID + ":distillery");
    public static RecipeType<CalcinationRecipe> CALCINATION_CHAMBER;// = RecipeType.register(MODID + ":calcination_chamber");
    public static RecipeType<ElectrolyzerRecipe> ELECTROLYZER;// = RecipeType.register(MODID + ":electrolyzer");
    public static RecipeType<FermenterRecipe> FERMENTER;// = RecipeType.register(MODID + ":fermenter");
    public static RecipeType<FrothFlotationRecipe> FROTH_FLOTATION_CHAMBER;// = RecipeType.register(MODID + ":froth_flotation_chamber");
    public static RecipeType<MaceratorRecipe> MACERATOR;// = RecipeType.register(MODID + ":macerator");
    public static RecipeType<ReactionChamberRecipe> REACTION_CHAMBER;// = RecipeType.register(MODID + ":reaction_chamber");
    public static RecipeType<SmelteryRecipe> SMELTERY;// = RecipeType.register(MODID + ":smeltery");
}