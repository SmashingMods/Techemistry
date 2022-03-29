package al132.techemistry.items.parts;

import al132.techemistry.Ref;
import al132.techemistry.items.BaseItem;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class GearItem extends BaseItem {

    public final PartMaterial material;

    public GearItem(PartMaterial material) {
        super("gear_" + material.materialName, new Item.Properties().durability(material.durability));
        Ref.gears.add(this);
        this.material = material;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, levelIn, tooltips, flagIn);
        tooltips.add(new TextComponent("Tier: " + material.tier));
        tooltips.add(new TextComponent("Efficiency: " + material.efficiency + "x"));
        tooltips.add(new TextComponent("Speed: " + material.speed + "x"));
        if (!this.isDamaged(stack)) {
            tooltips.add(new TextComponent("Max Durability: " + material.durability));
        }
    }
}