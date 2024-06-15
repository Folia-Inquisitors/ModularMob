package me.hsgamer.modularmob.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

import java.util.Map;

public class ConfigUtil {
    // Copy from [XSeries](https://github.com/CryptoMorin/XSeries/blob/82ca645e8edb4b94e42b0173637e6a178bea1f4b/src/main/java/com/cryptomorin/xseries/XItemStack.java#L1079-L1094)
    public static ConfigurationSection mapToConfigSection(Map<?, ?> map) {
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
}
