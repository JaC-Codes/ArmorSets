package net.jack.armorsets.itembuilder;

import net.jack.armorsets.utils.CC;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Warrior {

    public static ItemStack[] getWarrior() {
        ItemStack warriorhelm = new ItemBuild(Material.DIAMOND_HELMET, 1)
                .setDisplayName(CC.translate("&b&lWarrior Helmet"))
                .setLore(CC.translate
                        (" "
                        ), CC.translate("&bThe Warrior Helmet"))
                .setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .build();

        ItemStack warriorchest = new ItemBuild(Material.DIAMOND_CHESTPLATE, 1)
                .setDisplayName(CC.translate("&b&lWarrior Chestplate"))
                .setLore(CC.translate
                        (" "), CC.translate("&bThe Warrior Chestplate")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .build();

        ItemStack warriorlegs = new ItemBuild(Material.DIAMOND_LEGGINGS, 1)
                .setDisplayName(CC.translate("&b&lWarrior Leggings"))
                .setLore(CC.translate
                        (" "), CC.translate("&bThe Warrior Leggings")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .build();

        ItemStack warriorboots = new ItemBuild(Material.DIAMOND_BOOTS, 1)
                .setDisplayName(CC.translate("&b&lWarrior Boots"))
                .setLore(CC.translate
                        (" "), CC.translate("&bThe Warrior Boots")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .setEnchant(Enchantment.DEPTH_STRIDER, 5)
                .build();

        return new ItemStack[]{warriorboots, warriorlegs, warriorchest, warriorhelm};
    }

    public void giveWarrior(Player player) {
        for (ItemStack itemStack : getWarrior()) {
            player.getInventory().addItem(itemStack);
        }
    }

    public static void effectGrant(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2));
    }

    public static void removeEffect(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
            break;
        }
    }
}


