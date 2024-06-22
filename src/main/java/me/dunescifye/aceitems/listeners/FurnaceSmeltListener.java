package me.dunescifye.aceitems.listeners;

import com.jeff_media.customblockdata.CustomBlockData;
import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FurnaceSmeltListener implements Listener {

    public void furnaceSmeltHandler(AceItems plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent e) {
        Block b = e.getBlock();
        PersistentDataContainer container = new CustomBlockData(b, AceItems.getInstance());
        String blockID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
        if (blockID == null) return;
        switch (blockID) {
            case "July24Grill" -> {
                Furnace furnace = (Furnace) e.getBlock().getState();
                Bukkit.getScheduler().runTask(AceItems.getInstance(), () -> {
                    ItemStack smelting = furnace.getInventory().getSmelting();
                    if (smelting == null) return;

                    int amount = smelting.getAmount();
                    int extraAmount = Math.min(amount, 15);

                    ItemStack result = furnace.getInventory().getResult();
                    if (result == null) return;
                    int resultAmount = result.getAmount();
                    if (resultAmount + extraAmount > 64) {
                        extraAmount = 64 - resultAmount;
                    }

                    smelting.setAmount(amount - extraAmount);
                    result.setAmount(resultAmount + extraAmount);
                });
            }
            case "UltraJuly24Grill" -> {
                Furnace furnace = (Furnace) e.getBlock().getState();
                Bukkit.getScheduler().runTask(AceItems.getInstance(), () -> {
                    ItemStack smelting = furnace.getInventory().getSmelting();
                    if (smelting == null) return;
                    int smeltingAmount = smelting.getAmount();
                    int extraAmount = smeltingAmount;

                    ItemStack result = furnace.getInventory().getResult();
                    if (result == null) return;
                    int resultAmount = result.getAmount();

                    if (resultAmount + smeltingAmount > 64) {
                        extraAmount = 64 - resultAmount;
                    }
                    smelting.setAmount(smeltingAmount - extraAmount);
                    result.setAmount(resultAmount + extraAmount);
                });
            }
        }
    }

}
