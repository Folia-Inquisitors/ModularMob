package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.abstraction.StringMobModifier;
import org.bukkit.entity.Entity;

public class NameModifier extends StringMobModifier {
    public NameModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(Entity entity) {
        entity.setCustomName(value);
        entity.setCustomNameVisible(true);
    }
}
