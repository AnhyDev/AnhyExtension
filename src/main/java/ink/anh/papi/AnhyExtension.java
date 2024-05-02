package ink.anh.papi;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

/**
 * AnhyExtension is a PlaceholderAPI expansion that integrates with AnhyLibAPI
 * to provide placeholders specifically for plugins developed by AnhyDev.
 * This class ensures the extension's compatibility and handles placeholder requests.
 */
public class AnhyExtension extends PlaceholderExpansion {

    /**
     * Checks if the AnhyLibAPI plugin is available and can be registered.
     * 
     * @return {@code true} if the AnhyLibAPI plugin exists and can be registered, {@code false} otherwise.
     */
    @Override
    public boolean canRegister() {
        return checkPluginExist("AnhyLibAPI", "ink.anh.api.AnhyLibAPI");
    }

    /**
     * Specifies the name of the plugin required by this PlaceholderAPI expansion.
     * 
     * @return The name of the required plugin, "AnhyLibAPI".
     */
    @Override
    public String getRequiredPlugin() {
        return "AnhyLibAPI";
    }

    /**
     * Provides the name of the author of this PlaceholderAPI expansion.
     * 
     * @return The name of the author, "AnhyDev".
     */
    @Override
    public String getAuthor() {
        return "AnhyDev";
    }

    /**
     * Returns the unique identifier for this PlaceholderAPI expansion.
     * 
     * @return The identifier string "anhy".
     */
    @Override
    public String getIdentifier() {
        return "anhy";
    }

    /**
     * Provides the current version of this PlaceholderAPI expansion.
     * 
     * @return The version string "1.0.0".
     */
    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * Indicates that this PlaceholderAPI expansion should persist across plugin reloads.
     * 
     * @return {@code true} to indicate persistence is desired.
     */
    @Override
    public boolean persist() {
        return true;
    }

    /**
     * Processes the placeholder requests, returning the appropriate values based on input parameters.
     * 
     * @param player The offline player for whom the placeholders are being requested.
     * @param params The parameters specifying the placeholder request.
     * @return A string result of the placeholder request or an error message if applicable.
     */
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.isEmpty()) {
            return "AnhyExtension is an extension for the PlaceholderAPI plugin that enables the use of placeholders from plugins developed by AnhyDev, which are built on the AnhyLibAPI, also created by the same developer.";
        }
        
        if (!canRegister()) {
            return "Error: Unable to register AnhyExtension. Please check if AnhyLibAPI is properly installed and enabled.";
        }

        return new AnhyPAPI().onRequest(player, params);
    }

    /**
     * Checks if a given plugin by name and class exists on the server.
     * 
     * @param pluginName The name of the plugin to check.
     * @param className The class name to verify within the plugin.
     * @return {@code true} if both the plugin and class are present, {@code false} otherwise.
     */
    public static boolean checkPluginExist(String pluginName, String className) {
        return checkClassForName(className) && Bukkit.getServer().getPluginManager().getPlugin(pluginName) != null;
    }

    /**
     * Checks if a class can be found in the current runtime environment.
     * 
     * @param className The name of the class to check.
     * @return {@code true} if the class can be located, {@code false} otherwise.
     */
    public static boolean checkClassForName(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Extracts and returns the first part of a string split by an underscore.
     * 
     * @param input The string to be split.
     * @return The first part of the input string before the underscore.
     */
    public static String getFirstPart(String input) {
        String[] parts = input.split("_", 2);
        return parts[0];
    }
}
