package com.smashingmods.techemistry.common.block.heatpipe;

import com.smashingmods.techemistry.Techemistry;
import com.smashingmods.techemistry.common.capability.Capabilities;
import com.smashingmods.techemistry.common.capability.HeatCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HeatPipeBlock extends AbstractPipeBlock {

    public HeatPipeBlock() {
        super(HeatPipeBlockEntity::new);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            HeatPipeBlockEntity blockEntity = (HeatPipeBlockEntity) pLevel.getBlockEntity(pPos);
            LazyOptional<HeatCapability> heatHandler = blockEntity.getCapability(Capabilities.HEAT_HANDLER);
            heatHandler.ifPresent(heatCapability -> {
                Techemistry.LOGGER.info(String.valueOf(heatCapability.getHeat()));
                heatCapability.incrementHeat(100);
            });
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (!pLevel.isClientSide()) {
            return (level, pPos, pBlockState, pBlockEntity) -> {
                if (pBlockEntity instanceof HeatPipeBlockEntity blockEntity) {
                    blockEntity.tick();
                }
            };
        }
        return null;
    }
}
