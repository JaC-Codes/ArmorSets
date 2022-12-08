package net.jack.armorsets.guiview;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class GUICreator {

    private final Inventory armorSetsGui;
    private final Inventory demonInspect;
    private final Inventory warriorInspect;
    private final Inventory runeDisplay;

    private final ArmorSets armorSets;

    public GUICreator(ArmorSets armorSets) {
        this.armorSets = armorSets;
        armorSetsGui = Bukkit.createInventory(null, this.armorSets.getConfig().getInt("ArmorSets.inventory.size")
                , CC.translate(this.armorSets.getConfig().getString("ArmorSets.inventory.title")));
        demonInspect = Bukkit.createInventory(null, this.armorSets.getConfig().getInt("DemonSet.inventory.size")
                , CC.translate(this.armorSets.getConfig().getString("DemonSet.inventory.title")));
        warriorInspect = Bukkit.createInventory(null, this.armorSets.getConfig().getInt("WarriorSet.inventory.size")
                , CC.translate(this.armorSets.getConfig().getString("WarriorSet.inventory.title")));
        runeDisplay = Bukkit.createInventory(null, 9, CC.translate("&f&lRunes"));

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
            for (String enchant : this.armorSets.getConfig().getStringList("DemonSet.inventory.items." + i + ".enchants")) {
                String enchantName = enchant.split(":")[0];
                int enchantLevel = Integer.parseInt(enchant.split(":")[1]);
                meta.addEnchant(Objects.requireNonNull(Enchantment.getByName(enchantName)), enchantLevel, true);
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
            demonInspect.setItem(this.armorSets.getConfig().getInt("DemonSet.inventory.items." + i + ".slot"), item);
        }

        player.openInventory(demonInspect);

    }

    public void openWarriorInspect(Player player) {
        for (final String i : Objects.requireNonNull(this.armorSets.getConfig().getConfigurationSection("WarriorSet.inventory.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.armorSets.getConfig().getString("WarriorSet.inventory.items." + i + ".item")));
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(this.armorSets.getConfig().getString("WarriorSet.inventory.items." + i + ".name")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.armorSets.getConfig().getStringList("WarriorSet.inventory.items." + i + ".lore")) {
                lore.add(CC.translate(l));
            }
            for (String enchant : this.armorSets.getConfig().getStringList("WarriorSet.inventory.items." + i + ".enchants")) {
                String enchantName = enchant.split(":")[0];
                int enchantLevel = Integer.parseInt(enchant.split(":")[1]);
                meta.addEnchant(Objects.requireNonNull(Enchantment.getByName(enchantName)), enchantLevel, true);
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
            warriorInspect.setItem(this.armorSets.getConfig().getInt("WarriorSet.inventory.items." + i + ".slot"), item);
        }

        player.openInventory(warriorInspect);

    }

    public void openRuneDisplay(Player player) {
        for (final String i : Objects.requireNonNull(this.armorSets.getConfig().getConfigurationSection("RuneDisplay.inventory.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.armorSets.getConfig().getString("RuneDisplay.inventory.items." + i + ".item")));
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(this.armorSets.getConfig().getString("RuneDisplay.inventory.items." + i + ".name")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.armorSets.getConfig().getStringList("RuneDisplay.inventory.items." + i + ".lore")) {
                lore.add(CC.translate(l));
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
            warriorInspect.setItem(this.armorSets.getConfig().getInt("RuneDisplay.inventory.items." + i + ".slot"), item);
        }

        player.openInventory(runeDisplay);

    }

}

