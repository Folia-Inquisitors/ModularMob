package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.abstraction.BooleanMobModifier;
import org.bukkit.entity.Entity;

public class SilentModifier extends BooleanMobModifier {
    public SilentModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(Entity entity) {
        entity.setSilent(value);
    }
}
