package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.NumberMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class DamageModifier extends NumberMobModifier implements LivingMobModifier {
    public DamageModifier(Number value) {
        super(value);
    }

    public DamageModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(LivingEntity entity) {
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(value.doubleValue());
    }
}
