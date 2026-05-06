package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.FunctionalMassBuilder;
import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.factory.SimpleMobFactory;
import me.hsgamer.modularmob.factory.XMobFactory;

public class MobFactoryBuilder extends FunctionalMassBuilder<MobFactoryBuilder.Input, MobFactory> {
    public MobFactoryBuilder(ModularMob plugin) {
        register(input -> new SimpleMobFactory(plugin, input), "simple");
        register(XMobFactory::new, "xmob");
    }

    @Override
    protected String getType(Input input) {
        return input.config.getInstance(String.class, "simple", PathString.asArray("type"));
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
