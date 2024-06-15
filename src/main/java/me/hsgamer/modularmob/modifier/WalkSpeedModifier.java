package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.NumberMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class WalkSpeedModifier extends NumberMobModifier implements LivingMobModifier {
    public WalkSpeedModifier(Number value) {
        super(value);
    }

    public WalkSpeedModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(LivingEntity entity) {
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(value.doubleValue());
    }
}
