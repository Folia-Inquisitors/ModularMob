package me.hsgamer.modularmob.api.abstraction;

import me.hsgamer.hscore.common.Validate;
import me.hsgamer.modularmob.api.MobModifier;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class RandomNumberMobModifier implements MobModifier {
    private final double min;
    private final double max;

    protected RandomNumberMobModifier(double min, double max) {
        this.min = min;
        this.max = max;
    }

    protected RandomNumberMobModifier(Object value) {
        String str = Objects.toString(value);
        String[] split = str.split("-");
        if (split.length == 2) {
            this.min = Validate.getNumber(split[0]).map(Number::doubleValue).orElse(0D);
            this.max = Validate.getNumber(split[1]).map(Number::doubleValue).orElse(0D);
        } else {
            double number = Validate.getNumber(str).map(Number::doubleValue).orElse(0D);
            this.min = number;
            this.max = number;
        }
    }

    protected Number getValue() {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
