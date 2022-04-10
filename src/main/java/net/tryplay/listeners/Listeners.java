package net.tryplay.listeners;

import net.tryplay.Main;
import net.tryplay.Saver;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Listeners implements Listener {

    private final Main plugin;
    private final Saver saver;

    public Listeners(Main plugin){
        this.plugin = plugin;
        this.saver = plugin.getSaver();
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e){
        if((System.currentTimeMillis() >= this.saver.getBreak(e.getPlayer()) + 60000)){
            this.saver.saveBreak(e.getPlayer(), System.currentTimeMillis());
        }else{
            e.setCancelled(true);
            e.getPlayer().sendMessage("§4§lPuedes volver a romper un bloque en "+ this.plugin.getBreak(e.getPlayer()));
        }

    }
    @EventHandler
    public void blockPlace(BlockPlaceEvent e){
        if((System.currentTimeMillis() >= this.saver.getPlace(e.getPlayer()) + 60000)){
            this.saver.savePlace(e.getPlayer(), System.currentTimeMillis());
        }else{
            e.setCancelled(true);
            e.getPlayer().sendMessage("§4§lPuedes volver a colocar un bloque en "+ this.plugin.getPlace(e.getPlayer()));
        }
    }

}
