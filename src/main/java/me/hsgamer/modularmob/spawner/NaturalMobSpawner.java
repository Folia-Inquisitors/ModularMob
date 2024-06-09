package me.hsgamer.modularmob.spawner;

import com.lewdev.probabilitylib.ProbabilityCollection;
import me.hsgamer.hscore.common.CollectionUtils;
import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.api.MobSpawner;
import me.hsgamer.modularmob.manager.MobManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.*;
import java.util.function.Consumer;

public class NaturalMobSpawner implements MobSpawner, Listener {
    private static final PathString ENTITY_PATH = new PathString("entity");
    private static final PathString CHANCE_PATH = new PathString("chance");
    private static final PathString REASON_PATH = new PathString("reason");
    private final ModularMob plugin;
    private final EntityType entityType;
    private final List<CreatureSpawnEvent.SpawnReason> reasons;
    private final ProbabilityCollection<Consumer<Entity>> spawnerChances;

    public NaturalMobSpawner(ModularMob plugin, Config config) {
        this.plugin = plugin;
        this.spawnerChances = new ProbabilityCollection<>();

        this.entityType = Optional.ofNullable(config.getNormalized(ENTITY_PATH))
                .map(Objects::toString)
                .flatMap(s -> {
                    try {
                        return Optional.of(EntityType.valueOf(s.toUpperCase(Locale.ROOT).trim()));
                    } catch (IllegalArgumentException e) {
                        return Optional.empty();
                    }
                })
                .orElse(null);

        MobManager mobManager = plugin.get(MobManager.class);
        for (String spawnChance : CollectionUtils.createStringListFromObject(config.getNormalized(CHANCE_PATH))) {
            String[] split = spawnChance.split(" ", 2);
            if (split.length == 2) {
                int chance;
                try {
                    chance = Integer.parseInt(split[0]);
                } catch (NumberFormatException e) {
                    plugin.getLogger().warning("Invalid chance: " + split[0]);
                    continue;
                }

                Consumer<Entity> consumer;
                if (split[1].equalsIgnoreCase("natural")) {
                    consumer = entity -> {
                    };
                } else {
                    Optional<MobFactory> optionalMobFactory = mobManager.getMobFactory(split[1]);
                    if (optionalMobFactory.isPresent()) {
                        MobFactory mobFactory = optionalMobFactory.get();
                        consumer = mobFactory::modify;
                    } else {
                        plugin.getLogger().warning("Mob not found: " + split[1]);
                        continue;
                    }
                }

                spawnerChances.add(consumer, chance);
            }
        }

        this.reasons = new ArrayList<>();
        for (String reason : CollectionUtils.createStringListFromObject(config.getNormalized(REASON_PATH))) {
            try {
                reasons.add(CreatureSpawnEvent.SpawnReason.valueOf(reason.toUpperCase(Locale.ROOT).trim()));
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Invalid reason: " + reason);
            }
        }
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (entityType == null) return;
        if (event.getEntityType() != entityType) return;
        if (!reasons.isEmpty() && !reasons.contains(event.getSpawnReason())) return;
        spawnerChances.get().accept(event.getEntity());
    }

    @Override
    public void enable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void disable() {
        HandlerList.unregisterAll(this);
    }
}
