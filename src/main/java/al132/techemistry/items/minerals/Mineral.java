package al132.techemistry.items.minerals;

import al132.chemlib.ChemLib;
import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.Ref;
import al132.techemistry.Techemistry;
import al132.techemistry.items.BaseItem;
import al132.techemistry.items.BaseRegItem;
import com.google.common.collect.Lists;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

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
        mineralItem = new BaseRegItem(name) {
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
                super.appendHoverText(stack, levelIn, tooltips, flagIn);
                thisMineral.appendHoverText(stack, levelIn, tooltips, flagIn);
            }
        };
        crushedItem = new BaseRegItem("crushed_" + name) {
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
                super.appendHoverText(stack, levelIn, tooltips, flagIn);
                thisMineral.appendHoverText(stack, levelIn, tooltips, flagIn);
            }
        };
        slurryItem = new BaseRegItem(name + "_slurry") {
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
                super.appendHoverText(stack, levelIn, tooltips, flagIn);
                thisMineral.appendHoverText(stack, levelIn, tooltips, flagIn);
            }
        };
        //this.slurryItem.setRegistryName(Techemistry.MODID, name + "_slurry");
        //this.mineralItem.setRegistryName(Techemistry.MODID, name);
        //this.crushedItem.setRegistryName(Techemistry.MODID, "crushed_" + name);

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


    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
        if (!formula.isEmpty()) {
            tooltips.add(new TextComponent(formula).withStyle(ChemLib.CHEM_TOOLTIP_COLOR));
        } else if (components != null) {
            String line = components.stream().map(ChemicalStack::getAbbreviation).collect(Collectors.joining());
            if (line.charAt(0) == '(') line = line.substring(1, line.length() - 1);
            tooltips.add(new TextComponent(line).withStyle(ChemLib.CHEM_TOOLTIP_COLOR));//.getAbbreviation()));
        }
    }
}