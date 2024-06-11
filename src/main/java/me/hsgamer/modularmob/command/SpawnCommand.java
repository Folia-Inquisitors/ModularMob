package me.hsgamer.modularmob.command;

import io.github.projectunified.minelib.scheduler.location.LocationScheduler;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.Permissions;
import me.hsgamer.modularmob.api.MobFactory;
import me.hsgamer.modularmob.manager.MobManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpawnCommand extends Command {
    private final ModularMob plugin;

    public SpawnCommand(ModularMob plugin) {
        super("spawnmob", "Spawn the mob", "/spawnmob <mob>", Collections.emptyList());
        setPermission(Permissions.SPAWN.getName());
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(getUsage());
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return false;
        }

        Player player = (Player) sender;
        Location location = player.getLocation();

        Optional<MobFactory> optionalMobFactory = plugin.get(MobManager.class).getMobFactory(args[0]);
        if (!optionalMobFactory.isPresent()) {
            sender.sendMessage("The mob is not found");
            return false;
        }
        MobFactory mobFactory = optionalMobFactory.get();

        LocationScheduler.get(plugin, location).run(() -> {
            if (mobFactory.spawn(location).isPresent()) {
                sender.sendMessage("The mob is spawned");
            } else {
                sender.sendMessage("Failed to spawn the mob");
            }
        });
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return plugin.get(MobManager.class).getMobFactoryMap().keySet().stream()
                    .filter(s -> s.startsWith(args[0]))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
