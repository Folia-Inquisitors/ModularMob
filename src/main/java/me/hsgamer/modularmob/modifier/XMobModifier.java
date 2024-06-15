package me.hsgamer.modularmob.modifier;

import com.cryptomorin.xseries.XEntity;
import me.hsgamer.hscore.common.MapUtils;
import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.api.abstraction.RandomListMobModifier;
import me.hsgamer.modularmob.util.ConfigUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.entity.Entity;

public class XMobModifier implements MobModifier, RandomListMobModifier.Chance {
    private final ConfigurationSection section;

    public XMobModifier(Object value) {
        section = MapUtils.castOptionalStringObjectMap(value)
                .map(ConfigUtil::mapToConfigSection)
                .orElseGet(MemoryConfiguration::new);
    }


    @Override
    public void modify(Entity entity) {
        XEntity.edit(entity, section);
    }

    @Override
    public int getChance() {
        return section.getInt("chance", 1);
    }
}
