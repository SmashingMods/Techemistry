package al132.techemistry.items.parts;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;

public class PartMaterial {

    public final String materialName;
    public final String materialTagName;
    public final int tier;
    public final int durability;
    public final double speed;
    public final double efficiency;

    public PartMaterial(String materialName, String materialTagName, int tier, int durability, double speed, double efficiency) {
        this.materialName = materialName;
        this.materialTagName = materialTagName;
        this.tier = tier;
        this.durability = durability;
        this.speed = speed;
        this.efficiency = efficiency;
        PartMaterialRegistry.materials.add(this);
    }
}