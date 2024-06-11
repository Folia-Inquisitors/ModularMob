package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.abstraction.BooleanMobModifier;
import org.bukkit.entity.Entity;

public class InvulnerableModifier extends BooleanMobModifier {
    public InvulnerableModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(Entity entity) {
        entity.setInvulnerable(value);
    }
}
