package al132.techemistry.items.minerals;

import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.items.BaseItem;
import com.google.common.collect.Lists;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class MineralItem extends BaseItem {

    // public final List<ChemicalStack> components = new ArrayList<>();

    public final List<ChemicalStack> components;//= new ArrayList<>();


    public MineralItem(String name, List<ChemicalStack> components) {
        super(name);
        this.components = components;
    }

    public MineralItem(String name) {
        this(name, (List<ChemicalStack>) null);
    }

    public MineralItem(String name, ChemicalStack component) {//}, ChemicalStack... components) {
        this(name, Lists.newArrayList(component));
        //this.components.addAll(Arrays.asList(components));
    }

    public MineralItem(String name, ChemicalStack... components) {
        this(name, Lists.newArrayList(components));
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (components != null) {
            String line = components.stream().map(ChemicalStack::getAbbreviation).collect(Collectors.joining());
            if (line.charAt(0) == '(') line = line.substring(1, line.length() - 1);
            tooltip.add(new StringTextComponent(line));//.getAbbreviation()));
        }
    }
/*
    public String getAbbreviation() {
        StringBuilder builder = new StringBuilder();
        for (ChemicalStack component : this.components) {
            String abbreviation = ((IChemical) component.chemical).getAbbreviation();
            if (component.chemical instanceof CompoundItem) {
                builder.append("(" + abbreviation + ")");
            } else {
                builder.append(abbreviation);
            }
            if (component.quantity > 1) {
                //subscriptZeroCodepoint is subscript 0 unicode char, adding 1-9 gives the subscript for that num
                //i.e. ₀ + 3 = ₃
                int subscriptZeroCodepoint = 0x2080;//Character.codePointAt("₀",0) + Character.codePointAt("₀",1);//Character.codePointAt("₀", 0);

                for (char c : Integer.toString(component.quantity).toCharArray()) {
                    builder.append(Character.toChars(subscriptZeroCodepoint + Character.getNumericValue(c)));
                }

            }
        }
        return builder.toString();
    }*/
}
