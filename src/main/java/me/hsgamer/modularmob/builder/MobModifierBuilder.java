package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.Builder;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobModifier;

public class MobModifierBuilder extends Builder<MobModifierBuilder.Input, MobModifier> {
    public MobModifierBuilder(ModularMob plugin) {
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
