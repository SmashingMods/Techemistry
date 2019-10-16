package al132.techemistry.items.parts;

import al132.techemistry.items.BaseItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GearItem extends BaseItem {

    public final PartMaterial material;

    public GearItem(PartMaterial material) {
        super("gear_" + material.materialName, new Item.Properties().maxDamage(material.durability));
        PartMaterialRegistry.gears.add(this);
        this.material = material;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        //super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent("Tier: " + material.tier));
        tooltip.add(new StringTextComponent("Efficiency: " + material.efficiency + "x"));
        tooltip.add(new StringTextComponent("Speed: " + material.speed + "x"));
        if (!this.isDamaged(stack)) {
            tooltip.add(new StringTextComponent("Max Durability: " + material.durability));
        }
    }
}