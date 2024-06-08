package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.MobModifier;
import org.bukkit.entity.Entity;

public class NameModifier implements MobModifier {
    private final String name;

    public NameModifier(String name) {
        this.name = name;
    }

    @Override
    public void modify(Entity entity) {
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
    }
}
