package net.jack.armorsets.itembuilder;


import net.jack.armorsets.utils.CC;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Demon {


    public static ItemStack[] getDemon() {
        ItemStack demonhelm = new ItemBuild(Material.DIAMOND_HELMET, 1)
                .setDisplayName(CC.translate("&c&lDemon Helmet"))
                .setLore(CC.translate
                        (" "
                        ), CC.translate("&cThe Demon Helmet"))
                .setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .build();

        ItemStack demonchest = new ItemBuild(Material.DIAMOND_CHESTPLATE, 1)
                .setDisplayName(CC.translate("&c&lDemon Chestplate"))
                .setLore(CC.translate
                        (" "), CC.translate("&cThe Demon Chestplate")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .build();

        ItemStack demonlegs = new ItemBuild(Material.DIAMOND_LEGGINGS, 1)
                .setDisplayName(CC.translate("&c&lDemon Leggings"))
                .setLore(CC.translate
                        (" "), CC.translate("&cThe Demon Leggings")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .build();

        ItemStack demonboots = new ItemBuild(Material.DIAMOND_BOOTS, 1)
                .setDisplayName(CC.translate("&c&lDemon Boots"))
                .setLore(CC.translate
                        (" "), CC.translate("&cThe Demon Boots")).setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .setEnchant(Enchantment.DURABILITY, 5)
                .setEnchant(Enchantment.DEPTH_STRIDER, 5)
                .build();

        return new ItemStack[]{demonboots, demonlegs, demonchest, demonhelm};
    }

    public void giveDemon(Player player) {
        for (ItemStack itemStack : getDemon()) {
            player.getInventory().addItem(itemStack);
        }
    }

    public static void effectGrant(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 100, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
    }
}



