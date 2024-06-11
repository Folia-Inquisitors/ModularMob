package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.hscore.bukkit.utils.ColorUtils;
import me.hsgamer.modularmob.api.MobModifier;
import org.bukkit.entity.Entity;

import java.util.Objects;

public class NameModifier implements MobModifier {
    private final String name;

    public NameModifier(Object value) {
        this.name = ColorUtils.colorize(Objects.toString(value));
    }

    @Override
    public void modify(Entity entity) {
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
    }
}
