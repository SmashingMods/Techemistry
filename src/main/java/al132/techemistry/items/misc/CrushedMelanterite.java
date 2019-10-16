package al132.techemistry.items.misc;

import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.items.BaseItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CrushedMelanterite extends BaseItem {

    public final List<ChemicalStack> components = new ArrayList<>();

    public CrushedMelanterite() {
        super("crushed_melanterite");
        components.add(new ChemicalStack("iron"));
        components.add(new ChemicalStack("sulfur"));
        components.add(new ChemicalStack("oxygen", 4));
        components.add(new ChemicalStack("water", 7));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(al132.chemlib.Utils.getAbbreviation(components)));
    }
}