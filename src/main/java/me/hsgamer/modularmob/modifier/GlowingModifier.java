package me.hsgamer.modularmob.modifier;

import me.hsgamer.modularmob.api.abstraction.BooleanMobModifier;
import org.bukkit.entity.Entity;

public class GlowingModifier extends BooleanMobModifier {
    public GlowingModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(Entity entity) {
        entity.setGlowing(value);
    }
}
