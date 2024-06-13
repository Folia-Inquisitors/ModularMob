package me.hsgamer.modularmob.factory;

import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.api.abstraction.BaseMobFactory;
import me.hsgamer.modularmob.builder.MobFactoryBuilder;
import me.hsgamer.modularmob.builder.MobModifierBuilder;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SimpleMobFactory extends BaseMobFactory {
    private final List<MobModifier> mobModifiers;

    public SimpleMobFactory(ModularMob plugin, MobFactoryBuilder.Input input) {
        super(input);
        Config config = input.config;
        String name = input.mobName;

        MobModifierBuilder mobModifierBuilder = plugin.get(MobModifierBuilder.class);

        List<MobModifier> modifiers = new ArrayList<>();
        for (Map.Entry<PathString, Object> entry : config.getNormalizedValues(false).entrySet()) {
            String key = PathString.toPath(entry.getKey());
            Object value = entry.getValue();
            if (!key.equalsIgnoreCase("entity")) {
                mobModifierBuilder.build(key, new MobModifierBuilder.Input(name, value)).ifPresent(modifiers::add);
            }
        }
        this.mobModifiers = Collections.unmodifiableList(modifiers);
    }

    @Override
    public void enable() {
        mobModifiers.forEach(MobModifier::enable);
    }

    @Override
    public void disable() {
        mobModifiers.forEach(MobModifier::disable);
    }

    @Override
    public void modify(Entity entity) {
        if (entityType != null && entity.getType() != entityType) return;
        mobModifiers.forEach(modifier -> modifier.modify(entity));
    }
}
