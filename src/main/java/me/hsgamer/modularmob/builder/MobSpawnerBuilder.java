package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.FunctionalMassBuilder;
import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobSpawner;
import me.hsgamer.modularmob.spawner.NaturalMobSpawner;

public class MobSpawnerBuilder extends FunctionalMassBuilder<Config, MobSpawner> {
    private static final PathString TYPE = new PathString("type", null);

    public MobSpawnerBuilder(ModularMob plugin) {
        register(config -> new NaturalMobSpawner(plugin, config), "natural");
    }

    @Override
    protected String getType(Config config) {
        return config.getInstance(TYPE, "natural", String.class);
    }
}
