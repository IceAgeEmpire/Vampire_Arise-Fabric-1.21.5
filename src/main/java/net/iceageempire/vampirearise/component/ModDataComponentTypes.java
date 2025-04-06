package net.iceageempire.vampirearise.component;

import net.iceageempire.vampirearise.VampireArise;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<BlockPos> COORDINATES = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(VampireArise.MOD_ID, "coordinates"),
            ComponentType.<BlockPos>builder().codec(BlockPos.CODEC).build()
    );

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(VampireArise.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        VampireArise.LOGGER.info("Registering Data Component Types for " + VampireArise.MOD_ID);
    }
}
