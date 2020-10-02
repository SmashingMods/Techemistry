package al132.techemistry.datagen;

import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.blocks.macerator.WeightedItemStack;
import al132.techemistry.datagen.recipe.CalcinationRecipeBuilder;
import al132.techemistry.datagen.recipe.FrothFlotationRecipeBuilder;
import al132.techemistry.datagen.recipe.MaceratorRecipeBuilder;
import al132.techemistry.items.minerals.Mineral;
import al132.techemistry.items.parts.GearItem;
import al132.techemistry.items.parts.PartMaterial;
import al132.techemistry.items.parts.PartMaterialRegistry;
import al132.techemistry.utils.TUtils;
import com.google.common.collect.Lists;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static al132.techemistry.utils.TUtils.*;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        MaceratorRecipeBuilder.recipe(Items.WHEAT)
                .setTier(0)
                .addWeightedResult(Ref.crushedWheat, 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.APPLE)
                .setTier(0)
                .addWeightedResult(new ItemStack(Ref.appleSauce), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.SAND)
                .setTier(1)
                .addWeightedResult(TUtils.toStack("silicon_dioxide", 4), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.GRAVEL)
                .setTier(1)
                .addWeightedResult(Items.SAND, 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.COBBLESTONE)
                .setTier(1)
                .addWeightedResult(new ItemStack(Items.GRAVEL), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.EGG)
                .setTier(0)
                .addWeightedResult(TUtils.toStack("calcium_carbonate", 2), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.TURTLE_EGG)
                .setTier(0)
                .addWeightedResult(TUtils.toStack("calcium_carbonate", 8), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Ref.dolomite.asItem())
                .setTier(1)
                .addWeightedResult(TUtils.toStack("calcium_carbonate"), 1)
                .setResult2(TUtils.toStack("magnesium_carbonate"))
                .build(consumer);

        for (Mineral mineral : Ref.minerals) {
            MaceratorRecipeBuilder.recipe(mineral.mineralItem)
                    .setTier(1)
                    .addWeightedResult(TUtils.toStack(mineral.crushedItem), 1)
                    .build(consumer);
        }

        MaceratorRecipeBuilder.recipe(Ref.sulfurChunk)
                .setTier(1)
                .addWeightedResult(TUtils.toStack("sulfur", 4), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.BONE_MEAL)
                .setTier(0)
                .addWeightedResult(TUtils.toStack("hydroxylapatite"), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Ref.salAmmoniac)
                .setTier(1)
                .addWeightedResult(TUtils.toStack("ammonium_chloride"), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Ref.natron)
                .setTier(1)
                .addWeightedResult(TUtils.toStack("sodium_carbonate"), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.IRON_ORE)
                .setTier(1)
                .addWeightedResult(TUtils.toStack("iron_oxide", 8), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Ref.sulfideOre.asItem())
                .setTier(1)
                .addWeightedResult(new ItemStack(Ref.galena.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.pyrite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.chalcocite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.stibnite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.millerite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.cinnabar.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.braggite.mineralItem, 8), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Ref.oxideOre.asItem())
                .setTier(2)
                .addWeightedResult(new ItemStack(Ref.cassiterite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.pyrolusite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.cuprite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.magnetite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.hematite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.ilmenite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.uraninite.mineralItem, 8), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Ref.carbonateOre.asItem())
                .setTier(1)
                .addWeightedResult(new ItemStack(Ref.strontianite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.cerussite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.spherocobaltite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.siderite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.rhodochrosite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.natron, 2), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Ref.sulfateOre.asItem())
                .setTier(1)
                .addWeightedResult(new ItemStack(Ref.melanterite.mineralItem, 4), 1)
                .addWeightedResult(new ItemStack(Ref.barite.mineralItem, 4), 1)
                .addWeightedResult(new ItemStack(Ref.celestite.mineralItem, 4), 1)
                .addWeightedResult(new ItemStack(Ref.anglesite.mineralItem, 4), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Ref.phosphateOre.asItem())
                .setTier(1)
                .addWeightedResult(new ItemStack(Ref.vanadinite.mineralItem, 4), 1)
                .addWeightedResult(new ItemStack(Ref.pyromorphite.mineralItem, 4), 1)
                .build(consumer);

        for (Mineral mineral : Ref.minerals) {
            FrothFlotationRecipeBuilder.recipe(new ItemStack(mineral.slurryItem))
                    .setInputs(Ingredient.fromItems(mineral.crushedItem))
                    .build(consumer);
        }

        CalcinationRecipeBuilder.recipe(TUtils.toStack("antimony_trioxide"))
                .setInput(Ingredient.fromStacks(TUtils.toStack("antimony_trisulfide")))
                .setGas(TUtils.toStack("sulfur_dioxide", 3))
                .setMinimumTemp(670)
                .build(consumer);

        //=====Anglesite
        CalcinationRecipeBuilder.recipe(TUtils.toStack("lead_oxide"))
                .setGas(toStack("sulfur_trioxide"))
                .setInput(Ingredient.fromItems(Ref.anglesite.crushedItem))
                .setMinimumTemp(1360)
                .build(consumer);

        CalcinationRecipeBuilder.recipe(TUtils.toStack("lead_oxide"))
                .setGas(toStack("sulfur_trioxide"))
                .setInput(Ingredient.fromItems(Ref.anglesite.slurryItem))
                .setMinimumTemp(1360)
                .build(consumer);

        //=====Barite
        CalcinationRecipeBuilder.recipe(TUtils.toStack("barium_oxide"))
                .setGas(toStack("sulfur_trioxide"))
                .setInput(Ingredient.fromItems(Ref.barite.crushedItem))
                .setMinimumTemp(1360)
                .build(consumer);

        CalcinationRecipeBuilder.recipe(TUtils.toStack("barium_oxide"))
                .setGas(toStack("sulfur_trioxide"))
                .setInput(Ingredient.fromItems(Ref.barite.slurryItem))
                .setMinimumTemp(1360)
                .build(consumer);


        ShapedRecipeBuilder.shapedRecipe(Ref.calcinationChamber)
                .patternLine("BFB")
                .patternLine("BFB")
                .patternLine("BFB")
                .key('B', Items.BRICKS)
                .key('F', Items.FURNACE)
                .setGroup(Techemistry.MODID)
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.FURNACE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.distilleryColumn)
                .patternLine("B B")
                .patternLine("BFB")
                .patternLine("B B")
                .key('B', Items.BRICKS)
                .key('F', Items.FURNACE)
                .setGroup(Techemistry.MODID)
                .addCriterion("furnace", InventoryChangeTrigger.Instance.forItems(Items.FURNACE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.distilleryController)
                .patternLine("BBB")
                .patternLine("BFB")
                .patternLine("BBB")
                .key('B', Items.BRICKS)
                .key('F', Items.FURNACE)
                .setGroup(Techemistry.MODID)
                .addCriterion("furnace", InventoryChangeTrigger.Instance.forItems(Items.FURNACE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.electrolyzer)
                .patternLine("III")
                .patternLine("I I")
                .patternLine("FIF")
                .key('I', Items.IRON_BARS)
                .key('F', Items.FURNACE)
                .setGroup(Techemistry.MODID)
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.fermenter)
                .patternLine("III")
                .patternLine("IBI")
                .patternLine("IGI")
                .key('I', Items.IRON_INGOT)
                .key('B', Items.BUCKET)
                .key('G', tag("gears/iron"))
                .setGroup(Techemistry.MODID)
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.frothFlotationChamber)
                .patternLine("BNB")
                .patternLine("BCB")
                .patternLine("BNB")
                .key('B', Items.BRICKS)
                .key('C', Items.CAULDRON)
                .key('N', tag("ingots/nickel"))
                .setGroup(Techemistry.MODID)
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.gasCollector)
                .patternLine("III")
                .patternLine("ICI")
                .patternLine("IHI")
                .key('I', Items.IRON_INGOT)
                .key('C', Items.CHEST)
                .key('H', Items.HOPPER)
                .setGroup(Techemistry.MODID)
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
                .build(consumer);


        for (PartMaterial mat : PartMaterialRegistry.materials) {
            if (Ingredient.fromTag(ItemTags.createOptional(new ResourceLocation(mat.materialTagName))).hasNoMatchingItems()) {
                continue;
            }
            GearItem gear = Ref.gears.stream().filter(x -> x.material == mat).findFirst().get();
            ShapedRecipeBuilder.shapedRecipe(gear)
                    .patternLine(" M ")
                    .patternLine("MSM")
                    .patternLine(" M ")
                    .key('S', Items.STICK)
                    .key('M', Ingredient.fromTag(ItemTags.createOptional(new ResourceLocation(mat.materialTagName))))
                    .setGroup(Techemistry.MODID)
                    .addCriterion("material", InventoryChangeTrigger.Instance.forItems(Items.STONE))
                    .build(consumer);
        }


        ShapedRecipeBuilder.shapedRecipe(Ref.macerator)
                .patternLine("III")
                .patternLine("ICI")
                .patternLine("IGI")
                .key('I', Tags.Items.INGOTS_IRON)
                .key('C', Items.CHEST)
                .key('G', tag("gears/iron"))
                .setGroup(Techemistry.MODID)
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.platinumElectrode)
                .patternLine("C")
                .patternLine("P")
                .patternLine("C")
                .key('C', tag("ingots/copper"))
                .key('P', tag("ingots/platinum"))
                .setGroup(Techemistry.MODID)
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.reactionChamber)
                .patternLine("FBB")
                .patternLine("F B")
                .patternLine("FBB")
                .key('F', Items.FURNACE)
                .key('B', Items.BRICKS)
                .setGroup(Techemistry.MODID)
                .addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.FURNACE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.smeltery)
                .patternLine("B B")
                .patternLine("FLF")
                .patternLine("B B")
                .key('F', Items.FURNACE)
                .key('B', Items.BRICKS)
                .key('L', Items.BLAST_FURNACE)
                .setGroup(Techemistry.MODID)
                .addCriterion("furnace", InventoryChangeTrigger.Instance.forItems(Items.BLAST_FURNACE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.solarHeater)
                .patternLine("GGG")
                .patternLine("BBB")
                .patternLine("CCC")
                .key('G', Tags.Items.GLASS)
                .key('B', Tags.Items.DYES_BLACK)
                .key('C', tag("ingots/copper"))
                .setGroup(Techemistry.MODID)
                .addCriterion("glass", InventoryChangeTrigger.Instance.forItems(Items.GLASS))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.solidFuelHeater)
                .patternLine("SSS")
                .patternLine("FSF")
                .patternLine("SSS")
                .key('S', Tags.Items.STONE)
                .key('F', Items.FURNACE)
                .setGroup(Techemistry.MODID)
                .addCriterion("furnace", InventoryChangeTrigger.Instance.forItems(Items.FURNACE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.steamBoiler)
                .patternLine("BIB")
                .patternLine("BIB")
                .patternLine("BFB")
                .key('B', Items.BRICKS)
                .key('I', Items.IRON_BARS)
                .key('F', Items.FURNACE)
                .setGroup(Techemistry.MODID)
                .addCriterion("furnace", InventoryChangeTrigger.Instance.forItems(Items.FURNACE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.steamTurbine)
                .patternLine("BIB")
                .patternLine("BGB")
                .patternLine("BIB")
                .key('B', Items.BRICKS)
                .key('I', Items.IRON_BARS)
                .key('G', tag("gears/iron"))
                .setGroup(Techemistry.MODID)
                .addCriterion("bricks", InventoryChangeTrigger.Instance.forItems(Items.BRICKS))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Ref.yeastGrowthPlate)
                .patternLine("S")
                .patternLine("B")
                .key('B', Items.BRICK)
                .key('S', Items.SUGAR)
                .setGroup(Techemistry.MODID)
                .addCriterion("brick", InventoryChangeTrigger.Instance.forItems(Items.BRICK))
                .build(consumer);
    }
}