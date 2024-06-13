package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.RandomNumberMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class RandomFlySpeedModifier extends RandomNumberMobModifier implements LivingMobModifier {
    public RandomFlySpeedModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(LivingEntity entity) {
        entity.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(getValue().doubleValue());
    }
}
