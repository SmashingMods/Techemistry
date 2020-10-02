package al132.techemistry.items.parts;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public class PartMaterial {

    public final String materialName;
    public final Ingredient materialIngredient;
    public final int tier;
    public final int durability;
    public final double speed;
    public final double efficiency;

    public PartMaterial(String materialName, Ingredient materialIngredient, int tier, int durability, double speed, double efficiency) {
        this.materialName = materialName;
        this.materialIngredient = materialIngredient;
        this.tier = tier;
        this.durability = durability;
        this.speed = speed;
        this.efficiency = efficiency;
        PartMaterialRegistry.materials.add(this);
    }
}