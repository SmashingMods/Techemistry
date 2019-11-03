package al132.techemistry.items;

import al132.alib.items.ABaseItem;
import al132.techemistry.Techemistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.IllegalFormatException;
import java.util.List;

public class BaseItem extends ABaseItem {
    private String name;

    public BaseItem(String name, Properties properties) {
        super(Techemistry.data, name, properties);
        this.name = name;
    }

    public BaseItem(String name) {
        super(Techemistry.data, name);
        this.name = name;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        try {
            String line = I18n.format("item.techemistry." + this.name + ".tooltip");
            if (!line.isEmpty()) {
                tooltip.add(new StringTextComponent(line));
            }
        } catch (IllegalFormatException e) {
            Techemistry.LOGGER.warn("Invalid tooltip for item.alchemistry." + this.name);
        }
    }
}