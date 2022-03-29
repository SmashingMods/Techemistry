package al132.techemistry.items;

import al132.alib.items.ABaseItem;
import al132.techemistry.Techemistry;
import al132.techemistry.setup.ModSetup;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;


import javax.annotation.Nullable;
import java.util.IllegalFormatException;
import java.util.List;

public class BaseItem extends ABaseItem {
    private String name;

    public BaseItem(String name, Properties properties) {
        super(ModSetup.ITEM_GROUP, properties);
        this.name = name;
    }

    public BaseItem(String name) {
        super(ModSetup.ITEM_GROUP, new Item.Properties());
        this.name = name;
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, levelIn, tooltips, flagIn);
        try {
            String line = I18n.get("item.techemistry." + this.name + ".tooltip");
            if (!line.isEmpty()) {
                tooltips.add(new TextComponent(line));
            }
        } catch (IllegalFormatException e) {
            Techemistry.LOGGER.warn("Invalid tooltip for item.alchemistry." + this.name);
        }
    }
}