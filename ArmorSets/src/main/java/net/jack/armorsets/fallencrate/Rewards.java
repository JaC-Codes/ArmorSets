package net.jack.armorsets.fallencrate;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Rewards {

    private final ArmorSets armorSets;
    private final Random random = new Random();

    public Rewards(ArmorSets armorSets) {
        this.armorSets = armorSets;
    }

    public void giveRewards(Player player, Location location) {
        int randomNumber1 = random.nextInt(100);
        int x = 0;
        for (String i : this.armorSets.getConfig().getConfigurationSection("Loot").getKeys(false)) {
            int chance = this.armorSets.getConfig().getInt("Loot." + i + ".chance");
            if (chance < randomNumber1) {
                ItemStack item = new ItemStack(Material.valueOf(this.armorSets.getConfig().getString("Loot." + i + ".item")));
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(CC.translate(this.armorSets.getConfig().getString("Loot." + i + ".name")));
                ArrayList<String> lore = new ArrayList<>();
                meta.setLore(armorSets.getConfig().getStringList("Loot." + i + ".lore").stream().map(CC::translate).toList());
                item.setItemMeta(meta);
                item.setAmount(this.armorSets.getConfig().getInt("Loot." + i + ".amount"));

                for (final String l : this.armorSets.getConfig().getConfigurationSection("Loot." + i + ".enchantments").getKeys(false)) {
                    item.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(this.armorSets.getConfig().getString("Loot." + i + ".enchantments." + l + ".enchant"))),
                            this.armorSets.getConfig().getInt("Loot." + i + ".enchantments." + i + ".level"));
                }
                if (!this.armorSets.getConfig().getString("Loot." + i + ".name").equalsIgnoreCase("DIRT")) {
                    location.getWorld().dropItemNaturally(location, item);
                }
                for (final String c : this.armorSets.getConfig().getStringList("Loot." + i + ".commands")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c
                            .replace("%player%", player.getName()));
                }
                x++;
                if (x >= 2) {
                    break;
                }
            }
        }
    }
}
