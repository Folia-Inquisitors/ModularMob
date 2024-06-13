package me.hsgamer.modularmob.modifier;

import com.cryptomorin.xseries.XItemStack;
import me.hsgamer.hscore.common.MapUtils;
import me.hsgamer.hscore.common.Validate;
import me.hsgamer.modularmob.api.abstraction.LivingMobModifier;
import me.hsgamer.modularmob.api.abstraction.RandomListMobModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

public class RandomItemEquipmentModifier extends RandomListMobModifier<Map<String, Object>, RandomItemEquipmentModifier.ItemChance> implements LivingMobModifier {
    private final BiConsumer<ItemChance, EntityEquipment> consumer;

    public RandomItemEquipmentModifier(Object value, BiConsumer<ItemChance, EntityEquipment> consumer) {
        super(value);
        this.consumer = consumer;
    }

    @Override
    protected Optional<Map<String, Object>> getRawValue(Object value) {
        return MapUtils.castOptionalStringObjectMap(value);
    }

    @Override
    protected int getChance(Map<String, Object> rawValue) {
        return Validate.getNumber(Objects.toString(rawValue.get("chance"))).map(Number::intValue).orElse(1);
    }

    @Override
    protected ItemChance getEntity(Map<String, Object> rawValue) {
        float dropChance = Validate.getNumber(Objects.toString(rawValue.get("drop-chance"))).map(Number::floatValue).orElse(0F);
        Optional<ItemStack> item = MapUtils.getOptional(rawValue, "item")
                .flatMap(MapUtils::castOptionalStringObjectMap)
                .map(XItemStack::deserialize);
        return new ItemChance(item.orElse(null), dropChance);
    }

    @Override
    public void modify(LivingEntity entity) {
        EntityEquipment equipment = entity.getEquipment();
        if (equipment == null) return;
        ItemChance itemChance = getValue();
        if (!itemChance.isItemExists()) return;
        consumer.accept(getValue(), equipment);
    }

    public static final class ItemChance {
        private final ItemStack item;
        private final float dropChance;

        public ItemChance(ItemStack item, float dropChance) {
            this.item = item;
            this.dropChance = dropChance;
        }

        private boolean isItemExists() {
            return item != null && !item.getType().isAir();
        }

        public ItemStack getItem() {
            return item;
        }

        public float getDropChance() {
            return dropChance;
        }
    }
}
