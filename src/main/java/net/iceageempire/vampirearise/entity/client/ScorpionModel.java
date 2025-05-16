package net.iceageempire.vampirearise.entity.client;

import net.iceageempire.vampirearise.entity.custom.ScorpionEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ScorpionModel extends GeoModel<ScorpionEntity> {
	@Override
	public Identifier getModelResource(GeoRenderState geoRenderState) {
		return Identifier.of("vampirearise", "geckolib/models/scorpion.geo.json");
	}

	@Override
	public Identifier getTextureResource(GeoRenderState geoRenderState) {
		return Identifier.of("vampirearise", "geckolib/textures/scorpion.png");
	}

	@Override
	public Identifier getAnimationResource(ScorpionEntity animatable) {
		return Identifier.of("vampirearise", "geckolib/animations/scorpion.animation.json");
	}
}
