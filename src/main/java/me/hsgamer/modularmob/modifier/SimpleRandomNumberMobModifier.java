package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.api.abstraction.RandomNumberMobModifier;
import org.bukkit.entity.Entity;

import java.util.function.Function;

public class SimpleRandomNumberMobModifier extends RandomNumberMobModifier {
    private final Function<Number, MobModifier> function;

    public SimpleRandomNumberMobModifier(Object value, Function<Number, MobModifier> function) {
        super(value);
        this.function = function;
    }

    @Override
    public void modify(Entity entity) {
        MobModifier modifier = function.apply(getValue());
        modifier.enable();
        modifier.modify(entity);
        modifier.disable();
    }
}
