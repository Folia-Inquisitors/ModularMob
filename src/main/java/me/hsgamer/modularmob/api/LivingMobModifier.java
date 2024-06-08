package me.hsgamer.modularmob.api;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public interface LivingMobModifier extends MobModifier {
    void modify(LivingEntity entity);

    default void modify(Entity entity) {
        if (entity instanceof LivingEntity) {
            modify((LivingEntity) entity);
        }
    }
}
