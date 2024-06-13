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

        register(input -> new RandomHealthModifier(input.value), "random-health");
        register(input -> new RandomItemEquipmentModifier(input.value, (itemChance, equipment) -> {
            equipment.setHelmet(itemChance.getItem());
            equipment.setHelmetDropChance(itemChance.getDropChance());
        }), "random-helmet");
        register(input -> new RandomItemEquipmentModifier(input.value, (itemChance, equipment) -> {
            equipment.setChestplate(itemChance.getItem());
            equipment.setChestplateDropChance(itemChance.getDropChance());
        }), "random-chestplate");
        register(input -> new RandomItemEquipmentModifier(input.value, (itemChance, equipment) -> {
            equipment.setLeggings(itemChance.getItem());
            equipment.setLeggingsDropChance(itemChance.getDropChance());
        }), "random-leggings");
        register(input -> new RandomItemEquipmentModifier(input.value, (itemChance, equipment) -> {
            equipment.setBoots(itemChance.getItem());
            equipment.setBootsDropChance(itemChance.getDropChance());
        }), "random-boots");
        register(input -> new RandomItemEquipmentModifier(input.value, (itemChance, equipment) -> {
            equipment.setItemInMainHand(itemChance.getItem());
            equipment.setItemInMainHandDropChance(itemChance.getDropChance());
        }), "random-main-hand");
        register(input -> new RandomItemEquipmentModifier(input.value, (itemChance, equipment) -> {
            equipment.setItemInOffHand(itemChance.getItem());
            equipment.setItemInOffHandDropChance(itemChance.getDropChance());
        }), "random-off-hand");
        register(input -> new RandomFlySpeedModifier(input.value), "random-fly-speed");
        register(input -> new RandomWalkSpeedModifier(input.value), "random-walk-speed");
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
