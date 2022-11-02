package com.smashingmods.techemistry.registration;

import com.smashingmods.techemistry.common.block.heatpipe.HeatPipeBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.smashingmods.techemistry.Techemistry.MODID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> HEAT_PIPE = BLOCKS.register("heat_pipe", HeatPipeBlock::new);

    public static void register(IEventBus pEventBus) {
        BLOCKS.register(pEventBus);
    }
}
