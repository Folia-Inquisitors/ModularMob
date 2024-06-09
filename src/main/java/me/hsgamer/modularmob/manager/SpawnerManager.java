package me.hsgamer.modularmob.manager;

import io.github.projectunified.minelib.plugin.base.Loadable;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobSpawner;
import me.hsgamer.modularmob.builder.MobSpawnerBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpawnerManager implements Loadable {
    private final ModularMob plugin;
    private final List<MobSpawner> spawners = new ArrayList<>();

    public SpawnerManager(ModularMob plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        File spawnersFolder = new File(plugin.getDataFolder(), "spawners");
        if (!spawnersFolder.exists()) {
            spawnersFolder.mkdirs();
        }

        MobSpawnerBuilder mobSpawnerBuilder = plugin.get(MobSpawnerBuilder.class);
        for (File file : Objects.requireNonNull(spawnersFolder.listFiles())) {
            if (file.getName().endsWith(".yml") || file.getName().endsWith(".yaml")) {
                BukkitConfig config = new BukkitConfig(file);
                config.setup();
                mobSpawnerBuilder.build(config).ifPresent(mobSpawner -> {
                    mobSpawner.enable();
                    spawners.add(mobSpawner);
                });
            }
        }
    }

    @Override
    public void disable() {
        spawners.forEach(MobSpawner::disable);
        spawners.clear();
    }
}
