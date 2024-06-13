package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.RandomNumberMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class RandomHealthModifier extends RandomNumberMobModifier implements LivingMobModifier {
    public RandomHealthModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(LivingEntity entity) {
        double health = getValue().doubleValue();
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        entity.setHealth(health);
    }
}
