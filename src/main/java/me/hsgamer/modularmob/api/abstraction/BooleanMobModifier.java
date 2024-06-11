package me.hsgamer.modularmob.api.abstraction;

import me.hsgamer.modularmob.api.MobModifier;

import java.util.Objects;

public abstract class BooleanMobModifier implements MobModifier {
    protected final boolean value;

    protected BooleanMobModifier(Object value) {
        this.value = Boolean.parseBoolean(Objects.toString(value));
    }
}
