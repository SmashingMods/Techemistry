package al132.techemistry.items.parts;

public class PartMaterial {

    public final String materialName;
    public final int tier;
    public final int durability;
    public final double speed;
    public final double efficiency;

    public PartMaterial(String materialName, int tier, int durability, double speed, double efficiency) {
        this.materialName = materialName;
        this.tier = tier;
        this.durability = durability;
        this.speed = speed;
        this.efficiency = efficiency;
        PartMaterialRegistry.materials.add(this);
    }
}