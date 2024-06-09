package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.FunctionalMassBuilder;
import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobFactory;

public class MobFactoryBuilder extends FunctionalMassBuilder<Config, MobFactory> {
    private static final PathString TYPE = new PathString("factory", null);

    public MobFactoryBuilder(ModularMob plugin) {
    }

    @Override
    protected String getType(Config config) {
        return config.getInstance(TYPE, "simple", String.class);
    }
}
