package al132.techemistry.items;

import static al132.techemistry.Techemistry.MODID;

public class BaseRegItem extends BaseItem {
    public BaseRegItem(String name, Properties properties) {
        super(name, properties);
        this.setRegistryName(MODID, name);
    }

    public BaseRegItem(String name) {
        super(name);
        this.setRegistryName(MODID, name);
    }
}
