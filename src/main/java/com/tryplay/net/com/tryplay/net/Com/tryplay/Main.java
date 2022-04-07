package com.tryplay.net.com.tryplay.net.Com.tryplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin implements Listener {
    private Saver saver;
    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(this,this);
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new placeholders(this).register();
        }
        this.saver = new Saver(this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin Activado");

    }

    @Override
    public void onDisable() {
        saver.save();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin Desactivado");
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e){
        if((System.currentTimeMillis() >=saver.getBreak(e.getPlayer()) + 60000)){
            saver.saveBreak(e.getPlayer(), System.currentTimeMillis());
        }else{
         e.setCancelled(true);
         e.getPlayer().sendMessage("§4§lPuedes volver a romper un bloque en "+ getBreak(e.getPlayer()));
        }

    }
    @EventHandler
    public void blockPlace(BlockPlaceEvent e){
        if((System.currentTimeMillis() >=saver.getPlace(e.getPlayer()) + 60000)){
            saver.savePlace(e.getPlayer(), System.currentTimeMillis());
        }else{
            e.setCancelled(true);
            e.getPlayer().sendMessage("§4§lPuedes volver a colocar un bloque en "+ getPlace(e.getPlayer()));
        }
    }

 public String getPlace(Player e){
        return TimeUnit.MILLISECONDS.toSeconds(( saver.getPlace(e.getPlayer()) + 60000) - System.currentTimeMillis())+ " segundos";
 }
 public String getBreak(Player e){
        return TimeUnit.MILLISECONDS.toSeconds(( saver.getBreak(e.getPlayer()) + 60000) - System.currentTimeMillis())+ " segundos";
 }

}
