package al132.techemistry.items.minerals;

import al132.chemlib.ChemLib;
import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.Ref;
import al132.techemistry.items.BaseItem;
import com.google.common.collect.Lists;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class Mineral {

    public final BaseItem mineralItem;
    public final BaseItem crushedItem;
    public final BaseItem slurryItem;

    public final List<ChemicalStack> components;
    private String formula = "";


    public Mineral(String name, List<ChemicalStack> components) {
        Ref.minerals.add(this);
        Mineral thisMineral = this;
        mineralItem = new BaseItem(name) {
            @Override
            public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                super.addInformation(stack, worldIn, tooltip, flagIn);
                thisMineral.addInformation(stack, worldIn, tooltip, flagIn);
            }
        };
        crushedItem = new BaseItem("crushed_" + name) {
            @Override
            public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                super.addInformation(stack, worldIn, tooltip, flagIn);
                thisMineral.addInformation(stack, worldIn, tooltip, flagIn);
            }
        };
        slurryItem = new BaseItem(name + "_slurry") {
            @Override
            public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                super.addInformation(stack, worldIn, tooltip, flagIn);
                thisMineral.addInformation(stack, worldIn, tooltip, flagIn);
            }
        };
        this.components = components;
    }

    public Mineral(String name, String formula) {
        this(name, (List<ChemicalStack>) null);
        this.formula = formula;
    }

    public Mineral(String name, String... formula) {
        this(name, String.join("", formula));
    }

    public Mineral(String name, ChemicalStack component) {
        this(name, Lists.newArrayList(component));
        //this.components.addAll(Arrays.asList(components));
    }

    public Mineral(String name, ChemicalStack... components) {
        this(name, Lists.newArrayList(components));
    }


    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (!formula.isEmpty()) {
            tooltip.add(new StringTextComponent(formula).mergeStyle(ChemLib.CHEM_TOOLTIP_COLOR));
        } else if (components != null) {
            String line = components.stream().map(ChemicalStack::getAbbreviation).collect(Collectors.joining());
            if (line.charAt(0) == '(') line = line.substring(1, line.length() - 1);
            tooltip.add(new StringTextComponent(line).mergeStyle(ChemLib.CHEM_TOOLTIP_COLOR));//.getAbbreviation()));
        }
    }
}