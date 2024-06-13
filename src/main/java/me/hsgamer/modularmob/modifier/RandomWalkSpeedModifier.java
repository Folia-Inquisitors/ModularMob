package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.RandomNumberMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class RandomWalkSpeedModifier extends RandomNumberMobModifier implements LivingMobModifier {
    public RandomWalkSpeedModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(LivingEntity entity) {
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(getValue().doubleValue());
    }
}
