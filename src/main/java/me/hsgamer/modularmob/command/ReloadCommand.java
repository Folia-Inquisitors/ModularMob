package me.hsgamer.modularmob.command;

import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.Permissions;
import me.hsgamer.modularmob.manager.MobManager;
import me.hsgamer.modularmob.manager.SpawnerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class ReloadCommand extends Command {
    private final ModularMob plugin;

    public ReloadCommand(ModularMob plugin) {
        super("reloadmob", "Reload the plugin", "/reloadmob", Collections.singletonList("rlmob"));
        setPermission(Permissions.RELOAD.getName());
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }

        MobManager mobManager = plugin.get(MobManager.class);
        SpawnerManager spawnerManager = plugin.get(SpawnerManager.class);
        spawnerManager.disable();
        mobManager.disable();
        mobManager.enable();
        spawnerManager.enable();
        sender.sendMessage("Reloaded");
        return true;
    }
}
