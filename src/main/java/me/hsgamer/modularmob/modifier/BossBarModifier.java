package me.hsgamer.modularmob.modifier;

import com.cryptomorin.xseries.XEntity;
import me.hsgamer.hscore.common.MapUtils;
import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.util.ConfigUtil;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Entity;

import java.lang.reflect.Method;

public class BossBarModifier implements MobModifier {
    private static final Method EDIT_BOSS_BAR;

    static {
        try {
            EDIT_BOSS_BAR = XEntity.class.getDeclaredMethod("editBossBar", BossBar.class, ConfigurationSection.class);
            EDIT_BOSS_BAR.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    private final ConfigurationSection section;

    public BossBarModifier(Object value) {
        this.section = MapUtils.castOptionalStringObjectMap(value).map(ConfigUtil::mapToConfigSection).orElseGet(MemoryConfiguration::new);
    }

    private void editBossBar(BossBar bossBar) {
        try {
            EDIT_BOSS_BAR.invoke(null, bossBar, section);
        } catch (Exception ignored) {
            // IGNORED
        }
    }

    @Override
    public void modify(Entity entity) {
        BossBar bossBar = null;
        if (entity instanceof Boss) {
            Boss boss = (Boss) entity;
            bossBar = boss.getBossBar();
        }

        if (bossBar != null) {
            editBossBar(bossBar);
        }
    }
}
