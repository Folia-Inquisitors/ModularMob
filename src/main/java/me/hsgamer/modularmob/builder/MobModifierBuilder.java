package me.hsgamer.modularmob.builder;

import me.hsgamer.hscore.builder.Builder;
import me.hsgamer.modularmob.ModularMob;
import me.hsgamer.modularmob.api.MobModifier;
import me.hsgamer.modularmob.modifier.*;

public class MobModifierBuilder extends Builder<MobModifierBuilder.Input, MobModifier> {
    public MobModifierBuilder(ModularMob plugin) {
        register(input -> new NameModifier(input.value), "name");
        register(input -> new HealthModifier(input.value), "health");
        register(input -> new GlowingModifier(input.value), "glowing");
        register(input -> new GravityModifier(input.value), "gravity");
        register(input -> new InvulnerableModifier(input.value), "invulnerable");
        register(input -> new SilentModifier(input.value), "silent");
        register(input -> new XMobModifier(input.value), "xmob", "xentity", "x");
        register(input -> new FlySpeedModifier(input.value), "fly-speed");
        register(input -> new WalkSpeedModifier(input.value), "walk-speed");
        register(input -> new DamageModifier(input.value), "damage");
        register(input -> new ItemEquipmentModifier(input.value, ItemEquipmentModifier.Slot.HELMET), "helmet");
        register(input -> new ItemEquipmentModifier(input.value, ItemEquipmentModifier.Slot.CHESTPLATE), "chestplate");
        register(input -> new ItemEquipmentModifier(input.value, ItemEquipmentModifier.Slot.LEGGINGS), "leggings");
        register(input -> new ItemEquipmentModifier(input.value, ItemEquipmentModifier.Slot.BOOTS), "boots");
        register(input -> new ItemEquipmentModifier(input.value, ItemEquipmentModifier.Slot.MAIN_HAND), "main-hand");
        register(input -> new ItemEquipmentModifier(input.value, ItemEquipmentModifier.Slot.OFF_HAND), "off-hand");

        register(input -> new SimpleRandomNumberMobModifier(input.value, HealthModifier::new), "random-health");
        register(input -> new SimpleRandomNumberMobModifier(input.value, FlySpeedModifier::new), "random-fly-speed");
        register(input -> new SimpleRandomNumberMobModifier(input.value, WalkSpeedModifier::new), "random-walk-speed");
        register(input -> new SimpleRandomNumberMobModifier(input.value, DamageModifier::new), "random-damage");
        register(input -> new SimpleRandomListMobModifier(input.value, o -> new ItemEquipmentModifier(o, ItemEquipmentModifier.Slot.HELMET)), "random-helmet");
        register(input -> new SimpleRandomListMobModifier(input.value, o -> new ItemEquipmentModifier(o, ItemEquipmentModifier.Slot.CHESTPLATE)), "random-chestplate");
        register(input -> new SimpleRandomListMobModifier(input.value, o -> new ItemEquipmentModifier(o, ItemEquipmentModifier.Slot.LEGGINGS)), "random-leggings");
        register(input -> new SimpleRandomListMobModifier(input.value, o -> new ItemEquipmentModifier(o, ItemEquipmentModifier.Slot.BOOTS)), "random-boots");
        register(input -> new SimpleRandomListMobModifier(input.value, o -> new ItemEquipmentModifier(o, ItemEquipmentModifier.Slot.MAIN_HAND)), "random-main-hand");
        register(input -> new SimpleRandomListMobModifier(input.value, o -> new ItemEquipmentModifier(o, ItemEquipmentModifier.Slot.OFF_HAND)), "random-off-hand");
    }

    public static final class Input {
        public final String mobName;
        public final Object value;

        public Input(String mobName, Object value) {
            this.mobName = mobName;
            this.value = value;
        }
    }
}
