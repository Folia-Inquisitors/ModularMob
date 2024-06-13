package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.abstraction.BooleanMobModifier;
import org.bukkit.entity.Entity;

public class GravityModifier extends BooleanMobModifier {
    public GravityModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(Entity entity) {
        entity.setGravity(value);
    }
}
