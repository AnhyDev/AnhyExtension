package ink.anh.papi;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class AnhyExtension extends PlaceholderExpansion {
	
    @Override
    public boolean canRegister() {
        return (checkPluginExist("AnhyLibAPI", "ink.anh.api.AnhyLibAPI"));
    }

    @Override
    public String getRequiredPlugin() {
        return "AnhyLibAPI";
    }

    @Override
    public String getAuthor() {
        return "AnhyDev";
    }

    @Override
    public String getIdentifier() {
        return "anhy";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }
    
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.isEmpty()) {
            // За відсутності параметрів повертаємо опис експансії
            return "AnhyExtension is an extension for the PlaceholderAPI plugin that enables the use of placeholders from plugins developed by AnhyDev, which are built on the AnhyLibAPI, also created by the same developer.";
        }
        
        if (!canRegister()) {
            // Якщо розширення не може зареєструватися, повідомляємо про це
            return "Error: Unable to register AnhyExtension. Please check if AnhyLibAPI is properly installed and enabled.";
        }

        // Обробка запитів на плейсхолдер з переданими параметрами
        return new AnhyPAPI().onRequest(player, params);
    }

    public static boolean checkPluginExist(String pluginName, String className) {
        return checkClassForName(className) && Bukkit.getServer().getPluginManager().getPlugin(pluginName) != null;
    }

    public static boolean checkClassForName(String className) {
        try {
            return Class.forName(className) != null;
        } catch (ClassNotFoundException e) { }
        return false;
    }
}