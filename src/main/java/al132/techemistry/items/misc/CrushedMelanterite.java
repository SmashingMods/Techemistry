package al132.techemistry.items.misc;

import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.items.BaseItem;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CrushedMelanterite extends BaseItem {

    public final List<ChemicalStack> components = new ArrayList<>();

    public CrushedMelanterite() {
        super("crushed_melanterite");

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, levelIn, tooltips, flagIn);
        tooltips.add(new TextComponent(al132.chemlib.Utils.getAbbreviation(components)));
    }
}