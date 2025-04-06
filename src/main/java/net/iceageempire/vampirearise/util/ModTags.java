package net.iceageempire.vampirearise.util;

import net.iceageempire.vampirearise.VampireArise;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_RUBY_TOOL = createTag("needs_ruby_tool");
        public static final TagKey<Block> INCORRECT_FOR_RUBY_TOOL = createTag("incorrect_for_ruby_tool");

        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(VampireArise.MOD_ID, name));
        }
    }
    public static class Items{
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("magic_items");
        public static final TagKey<Item> RUBY_REPAIR = createTag("ruby_repair");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(VampireArise.MOD_ID, name));
        }
    }
}
