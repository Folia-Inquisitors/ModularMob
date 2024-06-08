package me.hsgamer.modularmob.api;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface MobFactory extends MobModifier {
    Entity spawn(Location location);
}
