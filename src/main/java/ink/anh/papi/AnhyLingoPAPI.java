package ink.anh.papi;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ink.anh.api.LibraryManager;
import ink.anh.api.lingo.Translator;
import ink.anh.api.utils.LangUtils;
import ink.anh.api.utils.StringUtils;
import ink.anh.lingo.AnhyLingo;
import ink.anh.lingo.GlobalManager;

/**
 * This class handles placeholder requests related to the AnhyLingo plugin,
 * translating and formatting text based on player language preferences.
 */
public class AnhyLingoPAPI {

    private final AnhyLingo lingoPlugin = AnhyLingo.getInstance();
    private final LibraryManager libraryManager;

    /**
     * Constructor that initializes the AnhyLingoPAPI with access to the global library manager from AnhyLingo.
     */
    public AnhyLingoPAPI() {
        this.libraryManager = GlobalManager.getManager(lingoPlugin);
    }

    /**
     * Handles dynamic language-related placeholder requests.
     *
     * @param player The offline player for whom the placeholders are being processed.
     * @param dynamicPart The dynamic part of the placeholder.
     * @return A string representing the requested dynamic placeholder value or an empty string if not applicable.
     */
    public String onRequest(Player player, @NotNull String dynamicPart) {
        try {
            // Check if the dynamic part contains additional parameters separated by ",,"
            String[] parts = dynamicPart.split("@", 2);
            String key = parts[0];
            
            String[] placeholders;
            if (parts.length > 1) {
                String[] rawPlaceholders = parts[1].split(",,");
                placeholders = new String[rawPlaceholders.length];
                
                String[] langs = langs(player);
                for (int i = 0; i < rawPlaceholders.length; i++) {
                    placeholders[i] = StringUtils.colorize(Translator.translateKyeWorld(libraryManager, rawPlaceholders[i], langs));
                }
            } else {
                placeholders = new String[]{player.getName()};
            }
            
            String[] langs = langs(player);
            return StringUtils.colorize(StringUtils.formatString(Translator.translateKyeWorld(libraryManager, key, langs), placeholders));
        } catch (Exception e) {
            e.printStackTrace();
            return dynamicPart;
        }
    }

    /**
     * Returns the language settings for the player, defaulting to the system's default language if offline.
     *
     * @param player The player whose language settings are queried.
     * @return An array of language codes.
     */
    private String[] langs(Player player) {
        return player.isOnline() ? LangUtils.getPlayerLanguage((Player) player) : new String[] {libraryManager.getDefaultLang()};
    }
}
