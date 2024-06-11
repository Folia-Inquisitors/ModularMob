package me.hsgamer.modularmob.api.abstraction;

import me.hsgamer.modularmob.api.MobModifier;

import java.util.Objects;

public abstract class StringMobModifier implements MobModifier {
    protected final String value;

    protected StringMobModifier(Object value) {
        this.value = Objects.toString(value);
    }
}
