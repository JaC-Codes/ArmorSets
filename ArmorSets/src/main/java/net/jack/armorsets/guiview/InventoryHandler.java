package net.jack.armorsets.guiview;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class InventoryHandler implements Listener {

    private final ArmorSets armorSets;
    private final GUICreator guiCreator;

    public InventoryHandler(ArmorSets armorSets) {
        this.armorSets = armorSets;
        this.guiCreator = new GUICreator(this.armorSets);
    }

    @EventHandler
    public void playerClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.armorSets.getConfig()
                .getString("ArmorSets.inventory.title")))) {
            event.setCancelled(true);
        }
        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate(this.armorSets.getConfig().getString
                ("DemonSet.inventory.open")))) {
            player.closeInventory();
            this.guiCreator.openDemonInspect(player);
        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate(this.armorSets.getConfig().getString
                ("WarriorSet.inventory.open")))) {
            player.closeInventory();
            this.guiCreator.openWarriorInspect(player);
        }
    }


    @EventHandler
    public void mainInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.armorSets.getConfig()
                .getString("DemonSet.inventory.title"))) || event.getView().getTitle().equalsIgnoreCase(CC.translate(this.armorSets.getConfig()
                .getString("WarriorSet.inventory.title")))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.armorSets.getConfig()
                .getString("DemonSet.inventory.title"))) || event.getView().getTitle().equalsIgnoreCase(CC.translate(this.armorSets.getConfig()
                .getString("WarriorSet.inventory.title")))) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    guiCreator.openInventory(player);
                }
            }.runTaskLater(ArmorSets.getInstance(), 5L);
        }
    }
}
