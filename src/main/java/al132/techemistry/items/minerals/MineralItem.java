package al132.techemistry.items.minerals;

import al132.chemlib.ChemLib;
import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.items.BaseItem;
import com.google.common.collect.Lists;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class MineralItem extends BaseItem {
    private String formula = "";
    public final List<ChemicalStack> components;

    public MineralItem(String name, List<ChemicalStack> components) {
        super(name, new Item.Properties());
        this.components = components;
    }

    public MineralItem(String name, String formula) {
        this(name, (List<ChemicalStack>) null);
        this.formula = formula;
    }

    public MineralItem(String name, ChemicalStack... components) {
        this(name, Lists.newArrayList(components));
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, levelIn, tooltips, flagIn);
        if (!formula.isEmpty()) {
            tooltips.add(new TextComponent(formula).withStyle(ChemLib.CHEM_TOOLTIP_COLOR));
        } else if (components != null) {
            String line = components.stream().map(ChemicalStack::getAbbreviation).collect(Collectors.joining());
            if (line.charAt(0) == '(') line = line.substring(1, line.length() - 1);
            tooltips.add(new TextComponent(line).withStyle(ChemLib.CHEM_TOOLTIP_COLOR));//.getAbbreviation()));
        }
    }
}
