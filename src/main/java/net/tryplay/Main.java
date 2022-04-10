package net.tryplay;

import net.tryplay.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin implements Listener {

    private Saver saver;

    @Override
    public void onEnable() {
        this.saver = new Saver(this);
        Bukkit.getPluginManager().registerEvents(new Listeners(this),this);
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new Placeholder(this).register();
        }
        send(Bukkit.getConsoleSender(), "&aPlugin activado");

    }

    @Override
    public void onDisable() {
        this.saver.save();
        send(Bukkit.getConsoleSender(), "&cPlugin desactivado");
    }

    public void send(CommandSender sender, String message){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }


    public String getPlace(Player player){
        return TimeUnit.MILLISECONDS.toSeconds(( this.saver.getPlace(player) + 60000) - System.currentTimeMillis())+ " segundos";
    }
    public String getBreak(Player player){
        return TimeUnit.MILLISECONDS.toSeconds(( this.saver.getBreak(player) + 60000) - System.currentTimeMillis())+ " segundos";
    }

    public Saver getSaver(){
        return this.saver;
    }

}
