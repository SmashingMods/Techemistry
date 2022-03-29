package al132.techemistry.datagen;

import al132.techemistry.Ref;
import al132.techemistry.items.parts.GearItem;
import al132.techemistry.utils.TUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;


public class Tags extends ItemTagsProvider {
    public Tags(DataGenerator generatorIn, BlockTagsProvider tagsProvider) {
        super(generatorIn, tagsProvider);
    }

    //@Override
    protected void registerTags() {
        for (GearItem gear : Ref.gears) {
            //this.getBuilder(TUtils.tag("gears/" + gear.material.materialName)).add(gear);
            //TODO this.getOrCreateBuilder(TUtils.tag("gears/" + gear.material.materialName)).add(gear);

        }
    }
}
