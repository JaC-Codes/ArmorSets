package net.jack.armorsets.guiview;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.itembuilder.ItemBuild;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Objects;

public class GUICreator {

    private final Inventory armorSetsGui;
    private final Inventory demonInspect;

    private final ArmorSets armorSets;

    public GUICreator(ArmorSets armorSets) {
        this.armorSets = armorSets;
        armorSetsGui = Bukkit.createInventory(null, this.armorSets.getConfig().getInt("ArmorSets.inventory.size")
                , CC.translate(this.armorSets.getConfig().getString("ArmorSets.inventory.title")));
        demonInspect = Bukkit.createInventory(null, this.armorSets.getConfig().getInt("DemonSet.inventory.size")
                , CC.translate(this.armorSets.getConfig().getString("DemonSet.inventory.title")));

    }

    public void openInventory(Player player) {
        for (final String i : Objects.requireNonNull(this.armorSets.getConfig().getConfigurationSection("ArmorSets.inventory.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.armorSets.getConfig().getString("ArmorSets.inventory.items." + i + ".item")));
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(this.armorSets.getConfig().getString("ArmorSets.inventory.items." + i + ".name")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.armorSets.getConfig().getStringList("ArmorSets.inventory.items." + i + ".lore")) {
                lore.add(CC.translate(l));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            armorSetsGui.setItem(this.armorSets.getConfig().getInt("ArmorSets.inventory.items." + i + ".slot"), item);
        }

        player.openInventory(armorSetsGui);
    }

    public void openDemonInspect(Player player) {
        for (final String i : Objects.requireNonNull(this.armorSets.getConfig().getConfigurationSection("DemonSet.inventory.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.armorSets.getConfig().getString("DemonSet.inventory.items." + i + ".item")));
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(this.armorSets.getConfig().getString("DemonSet.inventory.items." + i + ".name")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.armorSets.getConfig().getStringList("DemonSet.inventory.items." + i + ".lore")) {
                lore.add(CC.translate(l));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            demonInspect.setItem(this.armorSets.getConfig().getInt("DemonSet.inventory.items." + i + ".slot"), item);
        }

        player.openInventory(demonInspect);

    }

}

