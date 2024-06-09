package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.FunctionalMassBuilder;
import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobFactory;

public class MobFactoryBuilder extends FunctionalMassBuilder<MobFactoryBuilder.Input, MobFactory> {
    private static final PathString TYPE = new PathString("factory", null);

    public MobFactoryBuilder(ModularMob plugin) {
    }

    @Override
    protected String getType(Input input) {
        return input.config.getInstance(TYPE, "simple", String.class);
    }

    public static final class Input {
        public final String mobName;
        public final Config config;

        public Input(String mobName, Config config) {
            this.mobName = mobName;
            this.config = config;
        }
    }
}
