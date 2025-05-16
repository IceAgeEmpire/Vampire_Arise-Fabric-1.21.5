package net.iceageempire.vampirearise.entity;

import com.google.gson.GsonBuilder;
import net.iceageempire.vampirearise.VampireArise;
import net.iceageempire.vampirearise.entity.custom.ScorpionEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<ScorpionEntity> SCORPION = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(VampireArise.MOD_ID, "scorpion"),
            EntityType.Builder.create(ScorpionEntity::new, SpawnGroup.CREATURE)
                    .dimensions(2.75f, 1f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(VampireArise.MOD_ID,"scorpion")))
    );


    public static void registerModEntities() {
        VampireArise.LOGGER.info("Registering Mod Entities for " + VampireArise.MOD_ID);

    }
}