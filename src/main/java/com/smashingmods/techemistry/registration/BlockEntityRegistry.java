package com.smashingmods.techemistry.registration;

import com.smashingmods.techemistry.common.block.heatpipe.HeatPipeBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.smashingmods.techemistry.Techemistry.MODID;

public class BlockEntityRegistry {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<BlockEntityType<HeatPipeBlockEntity>> HEAT_PIPE_BLOCK_ENTITY
            = BLOCK_ENTITY_TYPES.register("heat_pipe_block_entity",
            () -> BlockEntityType.Builder.of(HeatPipeBlockEntity::new, BlockRegistry.HEAT_PIPE.get()).build(null));

    public static void register(IEventBus pEventBus) {
        BLOCK_ENTITY_TYPES.register(pEventBus);
    }
}
