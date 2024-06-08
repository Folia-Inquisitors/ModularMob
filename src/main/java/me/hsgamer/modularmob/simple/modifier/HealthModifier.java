package me.hsgamer.modularmob.simple.modifier;

import me.hsgamer.modularmob.api.LivingMobModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

public class HealthModifier implements LivingMobModifier {
    private final Double health;

    public HealthModifier(Object object) {
        Double health = null;
        try {
            health = Double.parseDouble(object.toString());
        } catch (NumberFormatException e) {
            JavaPlugin.getPlugin(JavaPlugin.class).getLogger().warning("Invalid health: " + object);
        }
        this.health = health;
    }

    @Override
    public void modify(LivingEntity entity) {
        if (health == null) return;
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        entity.setHealth(health);
    }
}
