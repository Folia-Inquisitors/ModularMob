package me.hsgamer.modularmob.manager;

import io.github.projectunified.minelib.plugin.base.Loadable;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.builder.MobFactoryBuilder;

import java.io.File;
import java.util.*;

public class MobManager implements Loadable {
    private final ModularMob plugin;
    private final Map<String, MobFactory> mobFactoryMap = new HashMap<>();

    public MobManager(ModularMob plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        File mobFolder = new File(plugin.getDataFolder(), "mobs");
        if (!mobFolder.exists()) {
            mobFolder.mkdirs();
            plugin.saveResource("mobs/zombie.yml", false);
            plugin.saveResource("mobs/super-zombie.yml", false);
        }

        MobFactoryBuilder mobFactoryBuilder = plugin.get(MobFactoryBuilder.class);
        for (File file : Objects.requireNonNull(mobFolder.listFiles())) {
            if (file.getName().endsWith(".yml") || file.getName().endsWith(".yaml")) {
                BukkitConfig config = new BukkitConfig(file);
                config.setup();
                String name = config.getName();
                mobFactoryBuilder.build(new MobFactoryBuilder.Input(name, config)).ifPresent(mobFactory -> {
                    mobFactory.enable();
                    mobFactoryMap.put(name, mobFactory);
                });
            }
        }
    }

    @Override
    public void disable() {
        mobFactoryMap.values().forEach(MobFactory::disable);
        mobFactoryMap.clear();
    }

    public Optional<MobFactory> getMobFactory(String name) {
        return Optional.ofNullable(mobFactoryMap.get(name));
    }

    public Map<String, MobFactory> getMobFactoryMap() {
        return Collections.unmodifiableMap(mobFactoryMap);
    }
}
