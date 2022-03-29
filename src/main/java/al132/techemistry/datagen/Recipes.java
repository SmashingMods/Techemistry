package al132.techemistry.datagen;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
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
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static al132.techemistry.utils.TUtils.*;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    protected void registerRecipes(Consumer<FinishedRecipe> consumer) {

        MaceratorRecipeBuilder.recipe(Items.WHEAT)
                .setTier(0)
                .addWeightedResult(Registration.CRUSHED_WHEAT_ITEM.get(), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.APPLE)
                .setTier(0)
                .addWeightedResult(new ItemStack(Registration.APPLE_SAUCE_ITEM.get()), 1)
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

        MaceratorRecipeBuilder.recipe(Registration.DOLOMITE_BLOCK.get().asItem())
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

        MaceratorRecipeBuilder.recipe(Registration.SULFUR_CHUNK_ITEM.get())
                .setTier(1)
                .addWeightedResult(TUtils.toStack("sulfur", 4), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.BONE_MEAL)
                .setTier(0)
                .addWeightedResult(TUtils.toStack("hydroxylapatite"), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Registration.SAL_AMMONIAC_ITEM.get())
                .setTier(1)
                .addWeightedResult(TUtils.toStack("ammonium_chloride"), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Registration.NATRON_ITEM.get())
                .setTier(1)
                .addWeightedResult(TUtils.toStack("sodium_carbonate"), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Items.IRON_ORE)
                .setTier(1)
                .addWeightedResult(TUtils.toStack("iron_oxide", 8), 1)
                .build(consumer);


        MaceratorRecipeBuilder.recipe(Registration.SULFIDE_ORE_BLOCK.get().asItem())
                .setTier(1)
                .addWeightedResult(new ItemStack(Ref.galena.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.pyrite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.chalcocite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.stibnite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.millerite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.cinnabar.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.braggite.mineralItem, 8), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Registration.OXIDE_ORE_BLOCK.get().asItem())
                .setTier(2)
                .addWeightedResult(new ItemStack(Ref.cassiterite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.pyrolusite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.cuprite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.magnetite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.hematite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.ilmenite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.uraninite.mineralItem, 8), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Registration.CARBONATE_ORE_BLOCK.get().asItem())
                .setTier(1)
                .addWeightedResult(new ItemStack(Ref.strontianite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.cerussite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.spherocobaltite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.siderite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Ref.rhodochrosite.mineralItem, 8), 1)
                .addWeightedResult(new ItemStack(Registration.NATRON_ITEM.get(), 2), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Registration.SULFATE_ORE_BLOCK.get().asItem())
                .setTier(1)
                .addWeightedResult(new ItemStack(Ref.melanterite.mineralItem, 4), 1)
                .addWeightedResult(new ItemStack(Ref.barite.mineralItem, 4), 1)
                .addWeightedResult(new ItemStack(Ref.celestite.mineralItem, 4), 1)
                .addWeightedResult(new ItemStack(Ref.anglesite.mineralItem, 4), 1)
                .build(consumer);

        MaceratorRecipeBuilder.recipe(Registration.PHOSPHATE_ORE_BLOCK.get().asItem())
                .setTier(1)
                .addWeightedResult(new ItemStack(Ref.vanadinite.mineralItem, 4), 1)
                .addWeightedResult(new ItemStack(Ref.pyromorphite.mineralItem, 4), 1)
                .build(consumer);

        for (Mineral mineral : Ref.minerals) {
            FrothFlotationRecipeBuilder.recipe(new ItemStack(mineral.slurryItem))
                    .setInputs(Ingredient.of(mineral.crushedItem))
                    .build(consumer);
        }

        CalcinationRecipeBuilder.recipe(TUtils.toStack("antimony_trioxide"))
                .setInput(Ingredient.of(TUtils.toStack("antimony_trisulfide")))
                .setGas(TUtils.toStack("sulfur_dioxide", 3))
                .setMinimumTemp(670)
                .build(consumer);

        //=====Anglesite
        CalcinationRecipeBuilder.recipe(TUtils.toStack("lead_oxide"))
                .setGas(toStack("sulfur_trioxide"))
                .setInput(Ingredient.of(Ref.anglesite.crushedItem))
                .setMinimumTemp(1360)
                .build(consumer);

        CalcinationRecipeBuilder.recipe(TUtils.toStack("lead_oxide"))
                .setGas(toStack("sulfur_trioxide"))
                .setInput(Ingredient.of(Ref.anglesite.slurryItem))
                .setMinimumTemp(1360)
                .build(consumer);

        //=====Barite
        CalcinationRecipeBuilder.recipe(TUtils.toStack("barium_oxide"))
                .setGas(toStack("sulfur_trioxide"))
                .setInput(Ingredient.of(Ref.barite.crushedItem))
                .setMinimumTemp(1360)
                .build(consumer);

        CalcinationRecipeBuilder.recipe(TUtils.toStack("barium_oxide"))
                .setGas(toStack("sulfur_trioxide"))
                .setInput(Ingredient.of(Ref.barite.slurryItem))
                .setMinimumTemp(1360)
                .build(consumer);


        ShapedRecipeBuilder.shaped(Registration.CALCINATION_CHAMBER_BLOCK.get())
                .pattern("BFB")
                .pattern("BFB")
                .pattern("BFB")
                .define('B', Items.BRICKS)
                .define('F', Items.FURNACE)
                .group(Techemistry.MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.DISTILLERY_COLUMN_BLOCK.get())
                .pattern("B B")
                .pattern("BFB")
                .pattern("B B")
                .define('B', Items.BRICKS)
                .define('F', Items.FURNACE)
                .group(Techemistry.MODID)
                .unlockedBy("furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.DISTILLERY_CONTROLLER_BLOCK.get())
                .pattern("BBB")
                .pattern("BFB")
                .pattern("BBB")
                .define('B', Items.BRICKS)
                .define('F', Items.FURNACE)
                .group(Techemistry.MODID)
                .unlockedBy("furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.ELECTROLYZER_BLOCK.get())
                .pattern("III")
                .pattern("I I")
                .pattern("FIF")
                .define('I', Items.IRON_BARS)
                .define('F', Items.FURNACE)
                .group(Techemistry.MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.FERMENTER_BLOCK.get())
                .pattern("III")
                .pattern("IBI")
                .pattern("IGI")
                .define('I', Items.IRON_INGOT)
                .define('B', Items.BUCKET)
                .define('G', tag("gears/iron"))
                .group(Techemistry.MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.FROTH_FLOTATION_BLOCK.get())
                .pattern("BNB")
                .pattern("BCB")
                .pattern("BNB")
                .define('B', Items.BRICKS)
                .define('C', Items.CAULDRON)
                .define('N', tag("ingots/nickel"))
                .group(Techemistry.MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.GAS_COLLECTOR_BLOCK.get())
                .pattern("III")
                .pattern("ICI")
                .pattern("IHI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.CHEST)
                .define('H', Items.HOPPER)
                .group(Techemistry.MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);


        for (PartMaterial mat : PartMaterialRegistry.materials) {
            if (Ingredient.of(ItemTags.create(new ResourceLocation(mat.materialTagName))).isEmpty()) {
                continue;
            }
            GearItem gear = Ref.gears.stream().filter(x -> x.material == mat).findFirst().get();
            ShapedRecipeBuilder.shaped(gear)
                    .pattern(" M ")
                    .pattern("MSM")
                    .pattern(" M ")
                    .define('S', Items.STICK)
                    .define('M', Ingredient.of(ItemTags.create(new ResourceLocation(mat.materialTagName))))
                    .group(Techemistry.MODID)
                    .unlockedBy("material", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE))
                    .save(consumer);
        }


        ShapedRecipeBuilder.shaped(Registration.MACERATOR_BLOCK.get())
                .pattern("III")
                .pattern("ICI")
                .pattern("IGI")
                .define('I', Ingredient.of(Tags.Items.INGOTS_IRON))
                .define('C', Items.CHEST)
                .define('G', tag("gears/iron"))
                .group(Techemistry.MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.PLATINUM_ELECTRODE_ITEM.get())
                .pattern("C")
                .pattern("P")
                .pattern("C")
                .define('C', tag("ingots/copper"))
                .define('P', tag("ingots/platinum"))
                .group(Techemistry.MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.REACTION_CHAMBER_BLOCK.get())
                .pattern("FBB")
                .pattern("F B")
                .pattern("FBB")
                .define('F', Items.FURNACE)
                .define('B', Items.BRICKS)
                .group(Techemistry.MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.SMELTERY_BLOCK.get())
                .pattern("B B")
                .pattern("FLF")
                .pattern("B B")
                .define('F', Items.FURNACE)
                .define('B', Items.BRICKS)
                .define('L', Items.BLAST_FURNACE)
                .group(Techemistry.MODID)
                .unlockedBy("furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAST_FURNACE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.SOLAR_HEATER_BLOCK.get())
                .pattern("GGG")
                .pattern("BBB")
                .pattern("CCC")
                .define('G', Ingredient.of(Tags.Items.GLASS))
                .define('B', Ingredient.of(Tags.Items.DYES_BLACK))
                .define('C', tag("ingots/copper"))
                .group(Techemistry.MODID)
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.SOLID_HEATER_BLOCK.get())
                .pattern("SSS")
                .pattern("FSF")
                .pattern("SSS")
                .define('S', Ingredient.of(Tags.Items.STONE))
                .define('F', Items.FURNACE)
                .group(Techemistry.MODID)
                .unlockedBy("furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.STEAM_BOILER_BLOCK.get())
                .pattern("BIB")
                .pattern("BIB")
                .pattern("BFB")
                .define('B', Items.BRICKS)
                .define('I', Items.IRON_BARS)
                .define('F', Items.FURNACE)
                .group(Techemistry.MODID)
                .unlockedBy("furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.STEAM_TURBINE_BLOCK.get())
                .pattern("BIB")
                .pattern("BGB")
                .pattern("BIB")
                .define('B', Items.BRICKS)
                .define('I', Items.IRON_BARS)
                .define('G', tag("gears/iron"))
                .group(Techemistry.MODID)
                .unlockedBy("bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICKS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.YEAST_GROWTH_PLATE_ITEM.get())
                .pattern("S")
                .pattern("B")
                .define('B', Items.BRICK)
                .define('S', Items.SUGAR)
                .group(Techemistry.MODID)
                .unlockedBy("brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICK))
                .save(consumer);
    }
}