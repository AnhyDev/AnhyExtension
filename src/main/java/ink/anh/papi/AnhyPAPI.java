package ink.anh.papi;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ink.anh.api.utils.LangUtils;

/**
 * This class handles the processing of placeholder requests related to the AnhyLibAPI,
 * supporting various functionalities depending on the type of request and the state of the player.
 */
public class AnhyPAPI {
    
    /**
     * Handles placeholder requests and provides the relevant data based on the requested type.
     * Supported placeholders include details about family, gender, and language preferences.
     *
     * @param player The offline player for whom the placeholders are being processed.
     * @param params The parameters defining the type and specifics of the request.
     * @return A string representing the requested placeholder value or an empty string if not applicable.
     */
    public String onRequest(Player player, @NotNull String params) {
        String placeholder = "";
        
        // Handle dynamic placeholders prefixed with "@"
        if (params.startsWith("@")) {
            String dynamicPart = params.substring(1);
            if (AnhyExtension.checkPluginExist("AnhyLingo", "ink.anh.lingo.AnhyLingo")) {
                placeholder = new AnhyLingoPAPI().onRequest(player, dynamicPart);
                return placeholder;
            }
        }

        if (params.startsWith("@")) {
            String dynamicPart = params.substring(1);
            if (AnhyExtension.checkPluginExist("AnhyLingo", "ink.anh.lingo.AnhyLingo")) {
                placeholder = new AnhyLingoPAPI().onRequest(player, dynamicPart);
                return placeholder;
            }
        }

        // Extract the first part of the request to determine the type of placeholder being queried
        String firstPart = AnhyExtension.getFirstPart(params);
        
        switch (firstPart.toLowerCase()) {
        case "give":
            if (AnhyExtension.checkPluginExist("AnhyLingo", "ink.anh.lingo.AnhyLingo")) {
                placeholder = new AnhyLingoPAPI().handleGiveCommand(player, params);
                return placeholder;
            }
            break;
            case "gender":
            case "family":
                // Process family-related placeholders if the Family plugin is present
                if (AnhyExtension.checkPluginExist("AnhyFamily", "ink.anh.family.AnhyFamily")) {
                    placeholder = new FamilyPAPI().onRequest(player, firstPart, params);
                }
                break;
            case "lang":
                // Retrieve the primary language of an online player
                if (player.isOnline()) {
                    String[] langs = LangUtils.getPlayerLanguage((Player) player);
                    placeholder = (langs != null && langs.length != 0) ? langs[0] : "EN";
                } else {
                    placeholder = "EN";
                }
                break;
            case "langs":
                // Retrieve all languages as a comma-separated string for an online player
                if (player.isOnline()) {
                    String[] langs = LangUtils.getPlayerLanguage((Player) player);
                    placeholder = (langs != null && langs.length != 0) ? String.join(",", langs) : "EN";
                } else {
                    placeholder = "EN";
                }
                break;
            default:
                // Return empty if no recognized placeholder is found
                return placeholder;
        }
        
        return placeholder;
    }
}
