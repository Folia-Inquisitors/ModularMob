package me.hsgamer.modularmob.api;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Optional;

public interface MobFactory extends MobModifier {
    Optional<Entity> spawn(Location location);
}
