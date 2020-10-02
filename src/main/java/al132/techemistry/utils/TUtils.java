package al132.techemistry.utils;

import al132.chemlib.chemistry.CompoundRegistry;
import al132.chemlib.chemistry.ElementRegistry;
import al132.chemlib.items.CompoundItem;
import al132.chemlib.items.ElementItem;
import al132.chemlib.items.IChemical;
import al132.techemistry.Techemistry;
import al132.techemistry.capabilities.heat.CapabilityHeat;
import al132.techemistry.capabilities.heat.IHeatStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class TUtils {

    public static Tag<Item> tag(String name) {
        return new ItemTags.Wrapper(new ResourceLocation("forge", name));
    }


    public static Optional<IChemical> getChemicalByAbbreviation(String abbrev) {
        Optional<ElementItem> element = ElementRegistry.elements.values().stream()
                .filter(ele -> ele.getAbbreviation().equals(abbrev)).findFirst();
        if (element.isPresent()) return element.map(x -> x);
        else {
            Optional<CompoundItem> compound = CompoundRegistry.compounds.stream()
                    .filter(c -> c.getASCIIAbbreviation().equals(abbrev)).findFirst();
            return compound.map(x -> x);
        }
    }

    public static boolean isHandlerEmpty(IItemHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            if (!handler.getStackInSlot(i).isEmpty()) return false;
        }
        return true;
    }

    public static boolean isIngredientEmpty(Ingredient ingredient) {
        int length = ingredient.getMatchingStacks().length;
        return length == 0 || (length == 1 && ingredient.getMatchingStacks()[0].isEmpty());
    }

    public static ResourceLocation toLocation(String path) {
        return new ResourceLocation(Techemistry.data.MODID, path);
    }

    public static void addBlockTooltip(List<ITextComponent> tooltips, Block block) {
        String key = block.getTranslationKey() + ".tooltip";
        tooltips.add(new StringTextComponent(I18n.format(key)));
    }

    public static Ingredient toIngredient(String str) {
        return Ingredient.fromStacks(toStack(str));
    }

    public static Ingredient toIngredient(Item... items) {
        return Ingredient.fromItems(items);
    }

    public static Ingredient toIngredient(Block block) {
        return Ingredient.fromItems(block.asItem());
    }

    public static Ingredient toIngredient(Item item, int quantity) {
        return Ingredient.fromStacks(new ItemStack(item, quantity));
    }

    public static Ingredient toIngredient(String str, int quantity) {
        return Ingredient.fromStacks(toStack(str, quantity));
    }

    public static boolean isQuantityAdequate(ItemStack stack, Ingredient recipe) {
        return recipe == Ingredient.EMPTY || stack.getCount() >= recipe.getMatchingStacks()[0].getCount();
    }

    public static boolean canStack(ItemStack recipeStack, ItemStack slotStack) {
        return slotStack.isEmpty()
                || recipeStack.isEmpty()
                || (recipeStack.getItem() == slotStack.getItem() && recipeStack.getCount() + slotStack.getCount() <= recipeStack.getMaxStackSize());
    }

    public static ItemStack toStack(String str) {
        return toStack(str, 1);
    }

    public static Item toItem(String str) {
        return toStack(str).getItem();
    }

    public static ItemStack toStack(Item item) {
        return new ItemStack(item);
    }

    public static ItemStack toStack(Item item, int quantity) {
        return new ItemStack(item, quantity);
    }

    public static ItemStack toStack(String str, int quantity) {
        ResourceLocation resourceLocation = new ResourceLocation(str);
        ItemStack outputStack = ItemStack.EMPTY;
        Item outputItem = ForgeRegistries.ITEMS.getValue(resourceLocation);
        Block outputBlock = ForgeRegistries.BLOCKS.getValue(resourceLocation);
        ElementItem outputElement = ElementRegistry.getByName(str).orElse(null);//[this]
        CompoundItem outputCompound = CompoundRegistry.getByName(str.replace(" ", "_")).orElse(null);//)[this.replace(" ", "_")]
        if (outputElement != null) {
            outputStack = new ItemStack(outputElement, quantity);
        } else if (outputCompound != null) {
            outputStack = new ItemStack(outputCompound, quantity);
        } else if (outputItem != null) {
            outputStack = new ItemStack(outputItem, quantity);//.toStack(quantity = quantity, meta = actualMeta)
        } else if (outputBlock != null && outputBlock != Blocks.AIR && outputBlock != Blocks.WATER) {
            outputStack = new ItemStack(outputBlock, quantity);//.toStack(quantity = quantity, meta = actualMeta)
        }
        if (outputStack.isEmpty()) throw new RuntimeException("Unable to convert input [" + str + "] to an itemstack");
        return outputStack;
    }

    public static List<BlockPos> getSurroundingPositions(BlockPos pos) {
        ArrayList<BlockPos> output = new ArrayList<>();
        output.add(pos.up());
        output.add(pos.down());
        output.add(pos.north());
        output.add(pos.south());
        output.add(pos.east());
        output.add(pos.west());
        return output;
    }

    public static List<BlockState> getSurroundingBlocks(World world, BlockPos pos) {
        ArrayList<BlockState> output = new ArrayList<>();
        getSurroundingPositions(pos).forEach(position -> output.add(world.getBlockState(position)));
        return output;
    }

    public static List<LazyOptional<IEnergyStorage>> getSurroundingEnergyTiles(World world, BlockPos pos) {
        return getSurroundingPositions(pos).stream()
                .map(world::getTileEntity)
                .filter(Objects::nonNull)
                .map(x -> x.getCapability(CapabilityEnergy.ENERGY))
                .collect(Collectors.toList());
    }

    public static List<LazyOptional<IHeatStorage>> getSurroundingHeatCapabilities(World world, BlockPos pos) {
        return getSurroundingPositions(pos).stream()
                .map(world::getTileEntity)
                .filter(Objects::nonNull)
                .map(x -> x.getCapability(CapabilityHeat.HEAT_CAP))
                .collect(Collectors.toList());
    }

    public static List<IHeatStorage> getSurroundingHeatTiles(World world, BlockPos pos) {
        return getSurroundingHeatCapabilities(world, pos).stream()
                .filter(LazyOptional::isPresent)
                .map(x -> x.orElse(null))
                .collect(Collectors.toList());
    }
}
