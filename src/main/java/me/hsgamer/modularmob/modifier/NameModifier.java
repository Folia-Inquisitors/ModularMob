package me.hsgamer.modularmob.modifier;

import me.hsgamer.hscore.bukkit.utils.ColorUtils;
import me.hsgamer.modularmob.api.abstraction.StringMobModifier;
import org.bukkit.entity.Entity;

public class NameModifier extends StringMobModifier {
    public NameModifier(Object value) {
        super(value);
    }

    @Override
    public void modify(Entity entity) {
        entity.setCustomName(ColorUtils.colorize(value));
        entity.setCustomNameVisible(true);
    }
}
