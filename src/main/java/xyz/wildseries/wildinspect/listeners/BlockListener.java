package xyz.wildseries.wildinspect.listeners;

import xyz.wildseries.wildinspect.WildInspectPlugin;
import xyz.wildseries.wildinspect.coreprotect.LookupType;
import xyz.wildseries.wildinspect.utils.InspectPlayers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public final class BlockListener implements Listener {

    private WildInspectPlugin plugin;

    public BlockListener(WildInspectPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent e){
        if(!InspectPlayers.isInspectEnabled(e.getPlayer()))
            return;

        e.setCancelled(true);

        if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
            plugin.getCoreProtect().performLookup(LookupType.BLOCK_LOOKUP, e.getPlayer(), e.getClickedBlock(), 0);
            InspectPlayers.setClickMode(e.getPlayer(), Action.LEFT_CLICK_BLOCK);
        }

        else if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            List<String> containers = Arrays.asList("DISPENSER", "CHEST", "FURNACE", "BURNING_FURNACE", "TRAPPED_CHEST", "HOPPER", "DROPPER");
            if(containers.contains(e.getClickedBlock().getType().name())){
                plugin.getCoreProtect().performLookup(LookupType.CHEST_TRANSACTIONS, e.getPlayer(), e.getClickedBlock(), 0);
                InspectPlayers.setClickMode(e.getPlayer(), Action.RIGHT_CLICK_BLOCK);
            }
            else if (e.getItem() != null && e.getItem().getType().isBlock()) {
                plugin.getCoreProtect().performLookup(LookupType.BLOCK_LOOKUP, e.getPlayer(), e.getClickedBlock().getRelative(e.getBlockFace()), 0);
                InspectPlayers.setClickMode(e.getPlayer(), Action.LEFT_CLICK_BLOCK);
            }else{
                plugin.getCoreProtect().performLookup(LookupType.INTERACTION_LOOKUP, e.getPlayer(), e.getClickedBlock(), 0);
                InspectPlayers.setClickMode(e.getPlayer(), Action.RIGHT_CLICK_BLOCK);
            }
        }
    }

}
