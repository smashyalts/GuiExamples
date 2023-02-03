package guiexamples.guiexamples.GuiEventsMain;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static guiexamples.guiexamples.Guiexamples.econ;
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
        //EconomyResponse r = econ.depositPlayer(player, 1.05);

        if (e.getPlayer().isSneaking() && e.getHand() == (EquipmentSlot.HAND)) {
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
            Player player = (Player) e.getWhoClicked();
            EconomyResponse r = econ.withdrawPlayer(player, 100);
            if (r.transactionSuccess()) {
                e.getWhoClicked().sendMessage("Your wealth has been deemed worthy of claiming the" + ChatColor.DARK_RED + " " + "Destroyer Of Gods");
                e.setCancelled(false);
            }
            else if (!r.transactionSuccess()){
                e.getWhoClicked().sendMessage(ChatColor.RED + "Your wealth has been deemed unworthy of claiming the" + ChatColor.DARK_RED + " " + "Destroyer Of Gods");
                e.getWhoClicked().sendMessage(ChatColor.RED + "You have " + ChatColor.AQUA + econ.format(r.balance) + ChatColor.RED + " and u need " + ChatColor.DARK_AQUA + (100 - r.balance) + ChatColor.RED + " to be deemed worthy of the" + ChatColor.DARK_RED + " Destroyer Of Gods");
            }

        }
    }
}

