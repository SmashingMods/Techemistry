package al132.techemistry.items.parts;

import al132.techemistry.utils.TUtils;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class PartMaterialRegistry {

    public static final List<PartMaterial> materials = new ArrayList<>();

    public static final PartMaterial PART_DEV =
            new PartMaterial("dev", "", 99, 99999, 10, 1);

    public static final PartMaterial PART_WOOD =
            new PartMaterial("wood", "minecraft:logs", 0, 10, 1, 0.5);

    public static final PartMaterial PART_STONE =
            new PartMaterial("stone", "minecraft:stone", 1, 40, 1, 1);

    public static final PartMaterial PART_IRON =
            new PartMaterial("iron", "forge:ingots/iron", 2, 100, 1.2, 1.25);

    public static final PartMaterial PART_COPPER =
            new PartMaterial("copper", "forge:ingots/copper", 1, 60, 2.1, 1);

    public static final PartMaterial PART_NICKEL =
            new PartMaterial("nickel", "forge:ingots/nickel", 2, 150, 0.8, 1.5);
}
