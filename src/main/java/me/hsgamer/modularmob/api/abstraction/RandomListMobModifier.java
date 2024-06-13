package me.hsgamer.modularmob.api.abstraction;

import com.lewdev.probabilitylib.ProbabilityCollection;
import me.hsgamer.modularmob.api.MobModifier;

import java.util.Optional;

public abstract class RandomListMobModifier<R, V> implements MobModifier {
    private final ProbabilityCollection<V> collection;

    protected RandomListMobModifier(Object value) {
        this.collection = new ProbabilityCollection<>();

        if (value instanceof Iterable) {
            for (Object o : (Iterable<?>) value) {
                Optional<R> optionalR = getRawValue(o);
                if (optionalR.isPresent()) {
                    R rawValue = optionalR.get();
                    int chance = getChance(rawValue);
                    V entity = getEntity(rawValue);
                    collection.add(entity, chance);
                }
            }
        } else {
            Optional<R> optionalR = getRawValue(value);
            if (optionalR.isPresent()) {
                R rawValue = optionalR.get();
                int chance = getChance(rawValue);
                V entity = getEntity(rawValue);
                collection.add(entity, chance);
            }
        }
    }

    protected abstract Optional<R> getRawValue(Object value);

    protected abstract int getChance(R rawValue);

    protected abstract V getEntity(R rawValue);

    protected V getValue() {
        return collection.get();
    }
}