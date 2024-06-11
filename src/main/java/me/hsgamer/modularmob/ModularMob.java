package me.hsgamer.modularmob;

import io.github.projectunified.minelib.plugin.base.BasePlugin;
import io.github.projectunified.minelib.plugin.command.CommandComponent;
import me.hsgamer.modularmob.builder.MobFactoryBuilder;
import me.hsgamer.modularmob.builder.MobModifierBuilder;
import me.hsgamer.modularmob.builder.MobSpawnerBuilder;
import me.hsgamer.modularmob.command.ReloadCommand;
import me.hsgamer.modularmob.command.SpawnCommand;
import me.hsgamer.modularmob.manager.MobManager;
import me.hsgamer.modularmob.manager.SpawnerManager;

import java.util.Arrays;
import java.util.List;

public final class ModularMob extends BasePlugin {
    @Override
    protected List<Object> getComponents() {
        return Arrays.asList(
                new MobFactoryBuilder(this),
                new MobModifierBuilder(this),
                new MobSpawnerBuilder(this),

                new MobManager(this),
                new SpawnerManager(this),

                new Permissions(this),
                new CommandComponent(this, new ReloadCommand(this), new SpawnCommand(this))
        );
    }
}
