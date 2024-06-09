package me.hsgamer.modularmob.api;

import org.bukkit.entity.Entity;

public interface MobModifier extends MobLoadable {
    void modify(Entity entity);
}
