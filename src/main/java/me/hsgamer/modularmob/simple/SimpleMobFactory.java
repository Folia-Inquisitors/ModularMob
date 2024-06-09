package me.hsgamer.modularmob.simple;

import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.builder.MobFactoryBuilder;
import me.hsgamer.modularmob.builder.MobModifierBuilder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.*;

public class SimpleMobFactory implements MobFactory {
    private final EntityType entityType;
    private final List<MobModifier> mobModifiers;

    public SimpleMobFactory(ModularMob plugin, MobFactoryBuilder.Input input) {
        Config config = input.config;
        String name = input.mobName;

        MobModifierBuilder mobModifierBuilder = plugin.get(MobModifierBuilder.class);

        String type = "";
        List<MobModifier> modifiers = new ArrayList<>();
        for (Map.Entry<PathString, Object> entry : config.getNormalizedValues(false).entrySet()) {
            String key = PathString.toPath(entry.getKey());
            Object value = entry.getValue();
            if (key.equalsIgnoreCase("type")) {
                type = Objects.toString(value, "");
            } else {
                mobModifierBuilder.build(key, new MobModifierBuilder.Input(name, value)).ifPresent(modifiers::add);
            }
        }

        EntityType entityType;
        try {
            entityType = EntityType.valueOf(type.toUpperCase(Locale.ROOT).trim());
        } catch (IllegalArgumentException e) {
            entityType = null;
        }
        this.entityType = entityType;
        this.mobModifiers = Collections.unmodifiableList(modifiers);
    }

    @Override
    public void enable() {
        mobModifiers.forEach(MobModifier::enable);
    }

    @Override
    public void disable() {
        mobModifiers.forEach(MobModifier::disable);
    }

    @Override
    public Optional<Entity> spawn(Location location) {
        if (entityType == null) return Optional.empty();

        World world = location.getWorld();
        if (world == null) return Optional.empty();

        Entity entity = world.spawnEntity(location, entityType);
        modify(entity);
        return Optional.of(entity);
    }

    @Override
    public void modify(Entity entity) {
        if (entityType != null && !Objects.equals(entity.getType(), entityType)) return;
        mobModifiers.forEach(modifier -> modifier.modify(entity));
    }
}
