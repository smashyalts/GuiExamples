package guiexamples.guiexamples.GuiEventsMain;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.EMERALD;

public class Guievents implements Listener {
    private Inventory Inv;

public void InitializeItems () { Inv.addItem(new ItemStack(EMERALD).add(4));
}
    @EventHandler
    public void onGuiOpen (PlayerInteractEvent e) {
        if (e.getPlayer().isSneaking() && e.getHand() == (EquipmentSlot.HAND))
            Inv = Bukkit.createInventory(null, 9, "Gui Example");
            InitializeItems();
            openInventory(e.getPlayer());

    }

    private void openInventory (final HumanEntity Human) {
        Human.openInventory(Inv);
    }
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() == (Inv)) {
            e.setCancelled(true);
        }
    }
}
