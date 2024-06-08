package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.FunctionalMassBuilder;
import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.api.MobFactory;

public class MobFactoryBuilder extends FunctionalMassBuilder<Config, MobFactory> {
    public static final MobFactoryBuilder INSTANCE = new MobFactoryBuilder();
    private static final PathString TYPE = new PathString("factory", null);

    private MobFactoryBuilder() {
    }

    @Override
    protected String getType(Config config) {
        return config.getInstance(TYPE, "simple", String.class);
    }
}
