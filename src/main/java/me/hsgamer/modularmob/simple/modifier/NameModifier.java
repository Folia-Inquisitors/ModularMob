package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.MobModifier;
import org.bukkit.entity.Entity;

public class NameModifier implements MobModifier {
    private final String name;

    public NameModifier(Object value) {
        this.name = String.valueOf(value);
    }

    @Override
    public void modify(Entity entity) {
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
    }
}
