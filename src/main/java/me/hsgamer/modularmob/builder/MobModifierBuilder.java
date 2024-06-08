package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.Builder;
import me.hsgamer.modularmob.api.MobModifier;

public class MobModifierBuilder extends Builder<Object, MobModifier> {
    public static final MobModifierBuilder INSTANCE = new MobModifierBuilder();

    private MobModifierBuilder() {
    }
}
