package me.hsgamer.modularmob;

import io.github.projectunified.minelib.plugin.base.BasePlugin;
import io.github.projectunified.minelib.plugin.permission.PermissionComponent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions extends PermissionComponent {
    public static final Permission SPAWN = new Permission("modularmob.spawn", PermissionDefault.OP);
    public static final Permission RELOAD = new Permission("modularmob.reload", PermissionDefault.OP);

    public Permissions(BasePlugin plugin) {
        super(plugin);
    }
}
