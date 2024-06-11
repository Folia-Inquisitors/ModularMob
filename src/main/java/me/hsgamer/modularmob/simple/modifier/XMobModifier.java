package me.hsgamer.modularmob.simple.modifier;

import com.cryptomorin.xseries.XEntity;
import me.hsgamer.hscore.common.MapUtils;
import me.hsgamer.modularmob.api.MobModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.entity.Entity;

import java.util.Map;

public class XMobModifier implements MobModifier {
    private final ConfigurationSection section;

    public XMobModifier(Object value) {
        section = MapUtils.castOptionalStringObjectMap(value)
                .map(XMobModifier::mapToConfigSection)
                .orElseGet(MemoryConfiguration::new);
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
    public void modify(Entity entity) {
        XEntity.edit(entity, section);
    }
}
