package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.NumberMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class FlySpeedModifier extends NumberMobModifier implements LivingMobModifier {
    public FlySpeedModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(LivingEntity entity) {
        entity.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(value.doubleValue());
    }
}
