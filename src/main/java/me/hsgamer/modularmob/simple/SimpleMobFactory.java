package me.hsgamer.modularmob.simple;

import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.builder.MobModifierBuilder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.*;

public class SimpleMobFactory implements MobFactory {
    private final EntityType entityType;
    private final List<MobModifier> mobModifiers;

    public SimpleMobFactory(Config config) {
        String type = "";
        List<MobModifier> modifiers = new ArrayList<>();
        for (Map.Entry<PathString, Object> entry : config.getNormalizedValues(false).entrySet()) {
            String key = PathString.toPath(entry.getKey());
            Object value = entry.getValue();
            if (key.equalsIgnoreCase("type")) {
                type = Objects.toString(value, "");
            } else {
                MobModifierBuilder.INSTANCE.build(key, value).ifPresent(modifiers::add);
            }
        }

        try {
            this.entityType = EntityType.valueOf(type.toUpperCase(Locale.ROOT).trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid entity type: " + type);
        }
        this.mobModifiers = Collections.unmodifiableList(modifiers);
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
