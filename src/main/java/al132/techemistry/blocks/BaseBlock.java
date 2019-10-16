package al132.techemistry.blocks;

import al132.techemistry.Techemistry;
import al132.alib.blocks.ABaseBlock;

public class BaseBlock extends ABaseBlock {
    public BaseBlock(String name, Properties properties) {
        super(Techemistry.data, name, properties.hardnessAndResistance(2.0f));
    }
}