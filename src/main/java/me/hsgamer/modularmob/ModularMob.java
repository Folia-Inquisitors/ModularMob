package me.hsgamer.modularmob;

import io.github.projectunified.minelib.plugin.base.BasePlugin;
import me.hsgamer.modularmob.builder.MobFactoryBuilder;
import me.hsgamer.modularmob.builder.MobModifierBuilder;

import java.util.Arrays;
import java.util.List;

public final class ModularMob extends BasePlugin {
    @Override
    protected List<Object> getComponents() {
        return Arrays.asList(
                new MobFactoryBuilder(this),
                new MobModifierBuilder(this)
        );
    }
}
