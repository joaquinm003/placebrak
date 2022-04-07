package com.tryplay.net.com.tryplay.net.Com.tryplay;

import org.bukkit.entity.Player;

public class Saver extends YmlSave{

    public Saver(Main main){
        super(main,"PlayerData.yml");
    }


    public void saveBreak(Player player,Long lastbreak){
        config.set("players."+player.getName()+".name", player.getName());
        config.set("players."+player.getName()+".lastBreak", lastbreak);
    }
    public void savePlace(Player player,Long lastPlace){
        config.set("players."+player.getName()+".name", player.getName());
        config.set("players."+player.getName()+".lastPlace", lastPlace);
    }
    public long getPlace(Player player){
        return config.getLong("players."+player.getName()+".lastPlace");
    }
    public long getBreak(Player player){
        return config.getLong("players."+player.getName()+".lastBreak");
    }
}



