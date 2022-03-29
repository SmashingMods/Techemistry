package al132.techemistry.compat.jei;

import al132.techemistry.Ref;
import al132.techemistry.Registration;
import al132.techemistry.blocks.calcination_chamber.CalcinationRegistry;
import al132.techemistry.blocks.calcination_chamber.CalcinationScreen;
import al132.techemistry.blocks.distillery.DistilleryRegistry;
import al132.techemistry.blocks.distillery.DistilleryScreen;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerRegistry;
import al132.techemistry.blocks.electrolyzer.ElectrolyzerScreen;
import al132.techemistry.blocks.fermenter.FermenterContainer;
import al132.techemistry.blocks.fermenter.FermenterRegistry;
import al132.techemistry.blocks.fermenter.FermenterScreen;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationRegistry;
import al132.techemistry.blocks.froth_flotation_chamber.FrothFlotationScreen;
import al132.techemistry.blocks.gas_collector.CollectorRegistry;
import al132.techemistry.blocks.gas_collector.GasCollectorContainer;
import al132.techemistry.blocks.gas_collector.GasCollectorScreen;
import al132.techemistry.blocks.macerator.MaceratorContainer;
import al132.techemistry.blocks.macerator.MaceratorRegistry;
import al132.techemistry.blocks.macerator.MaceratorScreen;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberRegistry;
import al132.techemistry.blocks.reaction_chamber.ReactionChamberScreen;
import al132.techemistry.blocks.smeltery.SmelteryRegistry;
import al132.techemistry.blocks.smeltery.SmelteryScreen;
import al132.techemistry.compat.jei.category.*;
import al132.techemistry.utils.TUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import mezz.jei.load.registration.RecipeCatalystRegistration;
import mezz.jei.load.registration.RecipeCategoryRegistration;
import mezz.jei.load.registration.RecipeRegistration;
import mezz.jei.load.registration.RecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


@SuppressWarnings("removal")
@JeiPlugin
public class JEIIntegration implements IModPlugin {

    public static final String COLLECTOR_CATEGORY = "collector_recipe";
    public static final String MACERATOR_CATEGORY = "macerator_recipe";
    public static final String FERMENTER_CATEGORY = "fermenter_recipe";
    public static final String DISTILLERY_CATEGORY = "distillery_recipe";
    public static final String ELECTROLYZER_CATEGORY = "electrolyzer_recipe";
    public static final String CALCINATION_CATEGORY = "calcination_recipe";
    public static final String REACTION_CATEGORY = "reaction_recipe";
    public static final String SMELTERY_CATEGORY = "smeltery_recipe";
    public static final String FROTH_CATEGORY = "froth_recipe";


    public static final ResourceLocation COLLECTOR_RESOURCE = TUtils.toLocation(COLLECTOR_CATEGORY);
    public static final ResourceLocation MACERATOR_RESOURCE = TUtils.toLocation(MACERATOR_CATEGORY);
    public static final ResourceLocation FERMENTER_RESOURCE = TUtils.toLocation(FERMENTER_CATEGORY);
    public static final ResourceLocation DISTILLERY_RESOURCE = TUtils.toLocation(DISTILLERY_CATEGORY);
    public static final ResourceLocation ELECTROLYZER_RESOURCE = TUtils.toLocation(ELECTROLYZER_CATEGORY);
    public static final ResourceLocation CALCINATION_RESOURCE = TUtils.toLocation(CALCINATION_CATEGORY);
    public static final ResourceLocation REACTION_RESOURCE = TUtils.toLocation(REACTION_CATEGORY);
    public static final ResourceLocation SMELTERY_RESOURCE = TUtils.toLocation(SMELTERY_CATEGORY);
    public static final ResourceLocation FROTH_RESOURCE = TUtils.toLocation(FROTH_CATEGORY);


    @Override
    public ResourceLocation getPluginUid() {
        return TUtils.toLocation("jei");
    }


    @Override
    public void registerCategories(IRecipeCategoryRegistration reg) {
        IGuiHelper guiHelper = reg.getJeiHelpers().getGuiHelper();
        reg.addRecipeCategories(
                new CollectorRecipeCategory(guiHelper),
                new MaceratorRecipeCategory(guiHelper),
                new FermenterRecipeCategory(guiHelper),
                new DistilleryRecipeCategory(guiHelper),
                new ElectrolyzerRecipeCategory(guiHelper),
                new CalcinationRecipeCategory(guiHelper),
                new ReactionRecipeCategory(guiHelper),
                new SmelteryRecipeCategory(guiHelper),
                new FrothFlotationCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration reg) {
        Level level = Minecraft.getInstance().level;
        CollectorRegistry.init();
        reg.addRecipes(CollectorRegistry.recipes, COLLECTOR_RESOURCE);
        reg.addRecipes(MaceratorRegistry.getRecipes(level), MACERATOR_RESOURCE);
        reg.addRecipes(FermenterRegistry.getRecipes(level), FERMENTER_RESOURCE);
        reg.addRecipes(DistilleryRegistry.getRecipes(level), DISTILLERY_RESOURCE);
        reg.addRecipes(ElectrolyzerRegistry.getRecipes(level), ELECTROLYZER_RESOURCE);
        reg.addRecipes(CalcinationRegistry.getRecipes(level), CALCINATION_RESOURCE);
        reg.addRecipes(ReactionChamberRegistry.getRecipes(level), REACTION_RESOURCE);
        reg.addRecipes(SmelteryRegistry.getRecipes(level), SMELTERY_RESOURCE);
        reg.addRecipes(FrothFlotationRegistry.getRecipes(level), FROTH_RESOURCE);

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration reg) {
        reg.addRecipeCatalyst(new ItemStack(Registration.GAS_COLLECTOR_BLOCK.get()), COLLECTOR_RESOURCE);
        reg.addRecipeCatalyst(new ItemStack(Registration.MACERATOR_BLOCK.get()), MACERATOR_RESOURCE);
        reg.addRecipeCatalyst(new ItemStack(Registration.FERMENTER_BLOCK.get()), FERMENTER_RESOURCE);
        reg.addRecipeCatalyst(new ItemStack(Registration.DISTILLERY_CONTROLLER_BLOCK.get()), DISTILLERY_RESOURCE);
        reg.addRecipeCatalyst(new ItemStack(Registration.ELECTROLYZER_BLOCK.get()), ELECTROLYZER_RESOURCE);
        reg.addRecipeCatalyst(new ItemStack(Registration.CALCINATION_CHAMBER_BLOCK.get()), CALCINATION_RESOURCE);
        reg.addRecipeCatalyst(new ItemStack(Registration.REACTION_CHAMBER_BLOCK.get()), REACTION_RESOURCE);
        reg.addRecipeCatalyst(new ItemStack(Registration.SMELTERY_BLOCK.get()), SMELTERY_RESOURCE);
        reg.addRecipeCatalyst(new ItemStack(Registration.FROTH_FLOTATION_BLOCK.get()), FROTH_RESOURCE);

    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration reg) {
        reg.addRecipeClickArea(GasCollectorScreen.class, 69, 42, 38, 29, COLLECTOR_RESOURCE);
        reg.addRecipeClickArea(MaceratorScreen.class, 75, 40, 35, 22, MACERATOR_RESOURCE);
        reg.addRecipeClickArea(FermenterScreen.class, 98, 44, 36, 21, FERMENTER_RESOURCE);
        reg.addRecipeClickArea(DistilleryScreen.class, 72, 29, 45, 26, DISTILLERY_RESOURCE);
        reg.addRecipeClickArea(ElectrolyzerScreen.class, 103, 49, 34, 20, ELECTROLYZER_RESOURCE);
        reg.addRecipeClickArea(CalcinationScreen.class, 75, 40, 35, 22, CALCINATION_RESOURCE);
        reg.addRecipeClickArea(ReactionChamberScreen.class, 76, 41, 33, 22, REACTION_RESOURCE);
        reg.addRecipeClickArea(SmelteryScreen.class, 76, 41, 33, 22, SMELTERY_RESOURCE);
        reg.addRecipeClickArea(FrothFlotationScreen.class, 96, 49, 33, 20, FROTH_RESOURCE);

    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration reg) {
        reg.addRecipeTransferHandler(GasCollectorContainer.class, COLLECTOR_RESOURCE, 0, 1, 1, 36);
        reg.addRecipeTransferHandler(MaceratorContainer.class, MACERATOR_RESOURCE, 0, 2, 3, 36);
        reg.addRecipeTransferHandler(FermenterContainer.class, FERMENTER_RESOURCE, 0, 3, 4, 36);
    }
}