package al132.techemistry.items.parts;

import java.util.ArrayList;
import java.util.List;

public class PartMaterialRegistry {

    public static final List<PartMaterial> materials = new ArrayList<>();
    public static final List<GearItem> gears = new ArrayList<>();

    public static final PartMaterial PART_DEV =
            new PartMaterial("dev", 99, 99999, 10, 1);

    public static final PartMaterial PART_WOOD =
            new PartMaterial("wood", 0, 10, 1, 0.5);

    public static final PartMaterial PART_STONE =
            new PartMaterial("stone", 1, 40, 1, 1);

    public static final PartMaterial PART_IRON =
            new PartMaterial("iron", 2, 100, 1.2, 1.25);

    public static final PartMaterial PART_COPPER =
            new PartMaterial("copper", 1, 60, 2.1, 1);

    public static final PartMaterial PART_NICKEL =
            new PartMaterial("nickel", 2, 150, 0.8, 1.5);
}
