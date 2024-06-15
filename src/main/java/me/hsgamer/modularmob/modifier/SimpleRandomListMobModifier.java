package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.api.abstraction.RandomListMobModifier;
import org.bukkit.entity.Entity;

import java.util.Optional;
import java.util.function.Function;

public class SimpleRandomListMobModifier extends RandomListMobModifier<Object, MobModifier> {
    private final Function<Object, MobModifier> function;

    public SimpleRandomListMobModifier(Object value, Function<Object, MobModifier> function) {
        super(value);
        this.function = function;
    }

    @Override
    protected Optional<Object> getRawValue(Object value) {
        return Optional.empty();
    }

    @Override
    protected MobModifier getEntity(Object rawValue) {
        return function.apply(rawValue);
    }

    @Override
    public void modify(Entity entity) {
        getValue().modify(entity);
    }

    @Override
    public void enable() {
        collection.iterator().forEachRemaining(element -> element.getObject().enable());
    }

    @Override
    public void disable() {
        collection.iterator().forEachRemaining(element -> element.getObject().disable());
    }
}
