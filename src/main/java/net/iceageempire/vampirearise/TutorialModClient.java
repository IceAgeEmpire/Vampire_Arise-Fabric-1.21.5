package net.iceageempire.vampirearise;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.iceageempire.vampirearise.block.ModBlocks;
//import net.iceageempire.vampirearise.entity.client.ScorpionModel;
import net.iceageempire.vampirearise.entity.ModEntities;
import net.iceageempire.vampirearise.entity.client.ScorpionEntityRenderer;
import net.iceageempire.vampirearise.entity.client.ScorpionModel;
import net.minecraft.client.render.RenderLayer;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINEAPPLE_CROP, RenderLayer.getCutout());
        EntityRendererRegistry.register(ModEntities.SCORPION, ctx -> new ScorpionEntityRenderer<>(ctx, new ScorpionModel()));
    }
}
