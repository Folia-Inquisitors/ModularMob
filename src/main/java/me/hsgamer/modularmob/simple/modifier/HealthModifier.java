package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.LivingMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class HealthModifier implements LivingMobModifier {
    private final double health;

    public HealthModifier(double health) {
        this.health = health;
    }

    @Override
    public void modify(LivingEntity entity) {
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        entity.setHealth(health);
    }
}
