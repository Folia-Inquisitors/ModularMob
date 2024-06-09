package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.Builder;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.simple.modifier.HealthModifier;
import me.hsgamer.modularmob.simple.modifier.NameModifier;

public class MobModifierBuilder extends Builder<MobModifierBuilder.Input, MobModifier> {
    public MobModifierBuilder(ModularMob plugin) {
        register(input -> new NameModifier(input.value), "name");
        register(input -> new HealthModifier(input.value), "health");
    }

    public static final class Input {
        public final String mobName;
        public final Object value;

        public Input(String mobName, Object value) {
            this.mobName = mobName;
            this.value = value;
        }
    }
}
