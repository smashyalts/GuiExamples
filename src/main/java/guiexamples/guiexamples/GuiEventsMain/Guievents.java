package guiexamples.guiexamples.GuiEventsMain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static org.bukkit.Material.DIAMOND_AXE;

public class Guievents implements Listener {
    private Inventory Inv;
    public ItemStack GuiItems;

    public void InitializeItems () {
    GuiItems = new ItemStack(DIAMOND_AXE);
    GuiItems.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
    GuiItems.setLore(Collections.singletonList("Slayer Of Hercules"));
    ItemMeta meta = GuiItems.getItemMeta();
    meta.setDisplayName(ChatColor.DARK_RED + "Dominator of Gods");
    GuiItems.setItemMeta(meta);
}
    @EventHandler
    public void onGuiOpen (PlayerInteractEvent e) {
        if (e.getPlayer().isSneaking() && e.getHand() == (EquipmentSlot.HAND) && e.getAction().isRightClick()) {
            Inv = Bukkit.createInventory(null, 9, ChatColor.DARK_AQUA + "Death Merchant");
            InitializeItems();
            Inv.setItem(4, GuiItems);
            openInventory(e.getPlayer()); }
    }

    private void openInventory (final HumanEntity Human) {
        Human.openInventory(Inv);

    }
    @EventHandler
    public void onInventory (InventoryClickEvent e) {
        if (e.getClickedInventory() == (Inv)){
            e.setCancelled(true);
            if (e.getWhoClicked().getGameMode() == (GameMode.CREATIVE)) {
                e.getWhoClicked().sendMessage("You have been deemed worthy of claiming the" + ChatColor.DARK_RED + " " + "Destroyer Of Gods");
                e.setCancelled(false);
            }

        }
    }
}

