package al132.techemistry.datagen;

import al132.techemistry.Ref;
import al132.techemistry.items.parts.GearItem;
import al132.techemistry.items.parts.PartMaterialRegistry;
import al132.techemistry.utils.TUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.ResourceLocation;

import java.nio.file.Path;

public class Tags extends ItemTagsProvider {
    public Tags(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTags() {
        for (GearItem gear : Ref.gears) {
            this.getBuilder(TUtils.tag("gears/" + gear.material.materialName)).add(gear);
        }
    }
}
