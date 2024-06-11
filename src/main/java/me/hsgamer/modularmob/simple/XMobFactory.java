package me.hsgamer.modularmob.simple;

import com.cryptomorin.xseries.XEntity;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.builder.MobFactoryBuilder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class XMobFactory implements MobFactory {
    private final EntityType entityType;
    private final ConfigurationSection section;

    public XMobFactory(MobFactoryBuilder.Input input) {
        section = mapToConfigSection(input.config.getNormalizedValues(false));

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

    // Copy from [XSeries](https://github.com/CryptoMorin/XSeries/blob/82ca645e8edb4b94e42b0173637e6a178bea1f4b/src/main/java/com/cryptomorin/xseries/XItemStack.java#L1079-L1094)
    private static ConfigurationSection mapToConfigSection(Map<?, ?> map) {
        ConfigurationSection config = new MemoryConfiguration();

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();

            if (value == null) continue;
            if (value instanceof Map<?, ?>) {
                value = mapToConfigSection((Map<?, ?>) value);
            }

            config.set(key, value);
        }

        return config;
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
        XEntity.edit(entity, section);
        return Optional.of(entity);
    }

    @Override
    public void modify(Entity entity) {
        XEntity.edit(entity, section);
    }
}
