package net.jack.armorsets.itembuilder;


import net.jack.armorsets.utils.CC;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;


public class Demon implements Listener {

    public static ArrayList<UUID> DEMON = new ArrayList<>();

    public static ItemStack[] getDemon() {
        ItemStack demonhelm = new ItemBuild(Material.DIAMOND_HELMET, 1)
                .setDisplayName(CC.translate("&c&lDemon Helmet"))
                .setLore(CC.translate
                        (" "
                        ), CC.translate("&cThe Demon Helmet")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .setEnchant(Enchantment.OXYGEN, 5)
                .setEnchant(Enchantment.MENDING, 5)
                .build();

        ItemStack demonchest = new ItemBuild(Material.DIAMOND_CHESTPLATE, 1)
                .setDisplayName(CC.translate("&c&lDemon Chestplate"))
                .setLore(CC.translate
                        (" "), CC.translate("&cThe Demon Chestplate")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .setEnchant(Enchantment.MENDING, 5)
                .build();

        ItemStack demonlegs = new ItemBuild(Material.DIAMOND_LEGGINGS, 1)
                .setDisplayName(CC.translate("&c&lDemon Leggings"))
                .setLore(CC.translate
                        (" "), CC.translate("&cThe Demon Leggings")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .setEnchant(Enchantment.MENDING, 5)
                .build();

        ItemStack demonboots = new ItemBuild(Material.DIAMOND_BOOTS, 1)
                .setDisplayName(CC.translate("&c&lDemon Boots"))
                .setLore(CC.translate
                        (" "), CC.translate("&cThe Demon Boots")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .setEnchant(Enchantment.SOUL_SPEED, 3)
                .setEnchant(Enchantment.SWIFT_SNEAK, 3)
                .setEnchant(Enchantment.DEPTH_STRIDER, 5)
                .setEnchant(Enchantment.MENDING, 5)
                .build();

        return new ItemStack[]{demonboots, demonlegs, demonchest, demonhelm};
    }

    public void giveDemon(Player player) {
        for (ItemStack itemStack : getDemon()) {
            player.getInventory().addItem(itemStack);
        }
    }

    public static void effectGrant(Player player) {
        if (DEMON.contains(player.getUniqueId())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1000000, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000000, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2));
        }
    }

    public static void removeEffect(Player player) {
            if (DEMON.contains(player.getUniqueId())) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                    break;
                    }
                }
            }
        }


