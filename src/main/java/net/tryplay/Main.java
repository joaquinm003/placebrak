package net.tryplay;

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


    public String getPlace(Player e){
        return TimeUnit.MILLISECONDS.toSeconds(( saver.getPlace(e.getPlayer()) + 60000) - System.currentTimeMillis())+ " segundos";
    }
    public String getBreak(Player e){
        return TimeUnit.MILLISECONDS.toSeconds(( saver.getBreak(e.getPlayer()) + 60000) - System.currentTimeMillis())+ " segundos";
    }

    public Saver getSaver(){
        return this.saver;
    }

}
