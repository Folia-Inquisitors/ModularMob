package me.hsgamer.modularmob.modifier;

import com.cryptomorin.xseries.XItemStack;
import me.hsgamer.hscore.common.MapUtils;
import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.RandomListMobModifier;
import me.hsgamer.modularmob.util.ConfigUtil;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ItemEquipmentModifier implements LivingMobModifier, RandomListMobModifier.Chance {
    private final int chance;
    private final float dropChance;
    private final ItemStack item;
    private final Slot slot;

    public ItemEquipmentModifier(Object value, Slot slot) {
        this.slot = slot;
        Map<String, Object> map = MapUtils.castOptionalStringObjectMap(value).orElseGet(Collections::emptyMap);

        chance = Optional.ofNullable(map.get("chance")).map(String::valueOf).map(Integer::parseInt).orElse(1);
        dropChance = Optional.ofNullable(map.get("drop-chance")).map(String::valueOf).map(Float::parseFloat).orElse(0F);
        item = Optional.ofNullable(map.get("item"))
                .flatMap(MapUtils::castOptionalStringObjectMap)
                .map(itemMap -> XItemStack.edit(new ItemStack(Material.STONE), ConfigUtil.mapToConfigSection(itemMap), Function.identity(), null))
                .orElseGet(() -> new ItemStack(Material.AIR));
    }

    @Override
    public void modify(LivingEntity entity) {
        EntityEquipment equipment = entity.getEquipment();
        if (equipment == null) return;

        if (item.getType().isAir()) return;

        switch (slot) {
            case MAIN_HAND:
                equipment.setItemInMainHand(item);
                equipment.setItemInMainHandDropChance(dropChance);
                break;
            case OFF_HAND:
                equipment.setItemInOffHand(item);
                equipment.setItemInOffHandDropChance(dropChance);
                break;
            case HELMET:
                equipment.setHelmet(item);
                equipment.setHelmetDropChance(dropChance);
                break;
            case CHESTPLATE:
                equipment.setChestplate(item);
                equipment.setChestplateDropChance(dropChance);
                break;
            case LEGGINGS:
                equipment.setLeggings(item);
                equipment.setLeggingsDropChance(dropChance);
                break;
            case BOOTS:
                equipment.setBoots(item);
                equipment.setBootsDropChance(dropChance);
                break;
        }
    }

    @Override
    public int getChance() {
        return chance;
    }

    public enum Slot {
        MAIN_HAND,
        OFF_HAND,
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }
}
