package me.hsgamer.modularmob.api.abstraction;

import me.hsgamer.modularmob.api.MobModifier;

import java.util.Objects;

public abstract class NumberMobModifier implements MobModifier {
    protected final Number value;

    protected NumberMobModifier(Number value) {
        this.value = value;
    }

    protected NumberMobModifier(Object value) {
        double number;
        try {
            number = Double.parseDouble(Objects.toString(value));
        } catch (NumberFormatException e) {
            number = 0.0;
        }
        this.value = number;
    }
}
