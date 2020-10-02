package al132.techemistry.items.parts;

import al132.techemistry.utils.TUtils;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class PartMaterialRegistry {

    public static final List<PartMaterial> materials = new ArrayList<>();

    public static final PartMaterial PART_DEV =
            new PartMaterial("dev", Ingredient.EMPTY, 99, 99999, 10, 1);

    public static final PartMaterial PART_WOOD =
            new PartMaterial("wood", Ingredient.fromTag(ItemTags.LOGS), 0, 10, 1, 0.5);

    public static final PartMaterial PART_STONE =
            new PartMaterial("stone", Ingredient.fromTag(Tags.Items.STONE), 1, 40, 1, 1);

    public static final PartMaterial PART_IRON =
            new PartMaterial("iron", Ingredient.fromTag(Tags.Items.INGOTS_IRON), 2, 100, 1.2, 1.25);

    public static final PartMaterial PART_COPPER =
            new PartMaterial("copper", Ingredient.fromTag(TUtils.tag("ingots/copper")), 1, 60, 2.1, 1);

    public static final PartMaterial PART_NICKEL =
            new PartMaterial("nickel", Ingredient.fromTag(TUtils.tag("ingots/nickel")), 2, 150, 0.8, 1.5);
}
