package me.hsgamer.modularmob.simple;

import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.builder.MobFactoryBuilder;
import me.hsgamer.modularmob.simple.modifier.XMobModifier;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.Locale;
import java.util.Optional;

public class XMobFactory implements MobFactory {
    private final EntityType entityType;
    private final XMobModifier modifier;

    public XMobFactory(MobFactoryBuilder.Input input) {
        this.modifier = new XMobModifier(input.config.getNormalizedValues(false));

        EntityType entityType = null;
        String entityTypeStr = input.config.getInstance(new PathString("entity"), String.class);
        if (entityTypeStr != null) {
            try {
                entityType = EntityType.valueOf(entityTypeStr.toUpperCase(Locale.ROOT).trim());
            } catch (IllegalArgumentException ignored) {
                // IGNORED
            }
        }
        this.entityType = entityType;
    }

    @Override
    public Optional<Entity> spawn(Location location) {
        if (entityType == null) {
            return Optional.empty();
        }

        World world = location.getWorld();
        if (world == null) {
            return Optional.empty();
        }

        Entity entity = world.spawnEntity(location, entityType);
        modify(entity);
        return Optional.of(entity);
    }

    @Override
    public void modify(Entity entity) {
        modifier.modify(entity);
    }
}
