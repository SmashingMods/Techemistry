package al132.techemistry.blocks.smeltery;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import static al132.techemistry.utils.Utils.toStack;

public class FluxRegistry {

    public static List<ItemStack> fluxes = new ArrayList<>();

    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent e) {
        if (isFlux(e.getItemStack())) {
            e.getToolTip().add(new StringTextComponent("Smeltery Flux"));
        }
    }

    public static void init() {
        fluxes.add(toStack("calcium_oxide"));
        fluxes.add(toStack("magnesium_oxide"));
    }

    public static boolean isFlux(ItemStack stack) {
        return fluxes.stream().anyMatch(x -> x.getItem() == stack.getItem());
    }
}
