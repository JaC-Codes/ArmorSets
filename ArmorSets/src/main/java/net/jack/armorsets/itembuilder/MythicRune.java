package net.jack.armorsets.itembuilder;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class MythicRune implements Listener {

    static NamespacedKey key = new NamespacedKey(ArmorSets.getInstance(), "mythicrune");

    public static ItemStack getMythicRune() {
        ItemStack mythicrune = new ItemBuild(Material.NETHER_STAR, 1)
                .setDisplayName(CC.translate("&f&l** &r&d&lMythic Rune &f&l**"))
                .setLore(CC.translate(" "), CC.translate("&d&oMythical rune"), CC.translate("&fx2 your xp intake for 2 hours once you redeem!"))
                .build();

        PersistentDataContainer pdc = Objects.requireNonNull(mythicrune.getItemMeta()).getPersistentDataContainer();

        ItemMeta meta = mythicrune.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "mythicrune");
        mythicrune.setItemMeta(meta);

        return mythicrune;
    }

    public void giveMythicRune(Player player, int amount) {
        ItemStack item = getMythicRune();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || item.getType() == Material.AIR) return;
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                if (meta == null) return;
                if (meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    item.setAmount(0);
                }
            }
        }
    }
}
