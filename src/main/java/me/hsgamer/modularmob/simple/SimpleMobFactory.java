package me.hsgamer.modularmob.simple;

import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.api.MobModifier;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.List;

public class SimpleMobFactory implements MobFactory {
    private final EntityType entityType;
    private final List<MobModifier> mobModifiers;

    public SimpleMobFactory(EntityType entityType, List<MobModifier> mobModifiers) {
        this.entityType = entityType;
        this.mobModifiers = mobModifiers;
    }

    @Override
    public Entity spawn(Location location) {
        World world = location.getWorld();
        assert world != null;
        Entity entity = world.spawnEntity(location, entityType);
        modify(entity);
        return entity;
    }

    @Override
    public void modify(Entity entity) {
        if (entity.getType() != entityType) return;
        mobModifiers.forEach(modifier -> modifier.modify(entity));
    }
}
