package net.tryplay;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class placeholders extends PlaceholderExpansion {

    private final Main plugin;
    private Saver saver;
    public placeholders(Main main) {
        this.plugin = main;
        this.saver = new Saver(main);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "TryPlay";
    }

    @Override
    public @NotNull String getAuthor() {
        return "TryPlays";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
    @Override
    public String getRequiredPlugin() {
        return "TryPlayp";
    }
    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }
    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("break")){
            return plugin.getBreak(player.getPlayer());
        }

        if(params.equalsIgnoreCase("place")){
            return plugin.getPlace(player.getPlayer());
        }

        return null; // Placeholder is unknown by the expansion
}
}
