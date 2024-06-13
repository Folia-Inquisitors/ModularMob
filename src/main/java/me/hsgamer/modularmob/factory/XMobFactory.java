package me.hsgamer.modularmob.factory;

import me.hsgamer.modularmob.api.abstraction.BaseMobFactory;
import me.hsgamer.modularmob.builder.MobFactoryBuilder;
import me.hsgamer.modularmob.modifier.XMobModifier;
import org.bukkit.entity.Entity;

public class XMobFactory extends BaseMobFactory {
    private final XMobModifier modifier;

    public XMobFactory(MobFactoryBuilder.Input input) {
        super(input);
        this.modifier = new XMobModifier(input.config.getNormalizedValues(false));
    }

    @Override
    public void modify(Entity entity) {
        modifier.modify(entity);
    }
}
