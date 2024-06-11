package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.NumberMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class HealthModifier extends NumberMobModifier implements LivingMobModifier {
    public HealthModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(LivingEntity entity) {
        double health = Math.max(0, value.doubleValue());
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        entity.setHealth(health);
    }
}
