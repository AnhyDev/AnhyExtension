package ink.anh.papi;

import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import ink.anh.api.LibraryManager;
import ink.anh.api.lingo.Translator;
import ink.anh.api.utils.LangUtils;
import ink.anh.api.utils.StringUtils;
import ink.anh.family.GlobalManager;
import ink.anh.family.fplayer.FamilyUtils;
import ink.anh.family.fplayer.PlayerFamily;
import ink.anh.family.fplayer.gender.Gender;
import ink.anh.family.fplayer.info.ProfileStringGenerator;
import ink.anh.family.fplayer.info.TreeStringGenerator;;

/**
 * FamilyPAPI is responsible for handling all family-related placeholder requests.
 * It provides functionalities to fetch and format family data for players.
 */
public class FamilyPAPI {

    private final LibraryManager libraryManager;

    /**
     * Constructor that initializes the FamilyPAPI and retrieves an instance of AnhyFamily.
     */
    public FamilyPAPI() {
        this.libraryManager = GlobalManager.getInstance();
    }

    /**
     * Processes the request for family-related placeholders based on the specified parameters.
     * 
     * @param player The offline player for whom the placeholders are being requested.
     * @param firstPart The category of the family-related placeholder (e.g., gender, family).
     * @param params The full placeholder request string.
     * @return A string value representing the result of the requested placeholder or an empty string if no data is found.
     */
    public String onRequest(Player player, String firstPart, @NotNull String params) {
        PlayerFamily family = getFamily(player);

        if (family == null) {
            return "";
        }

        switch (firstPart.toLowerCase()) {
            case "gender":
                return new FamilyPAPIGender().onRequestGender(player, family, params);
            case "family":
                return handleFamilyPlaceholder(player, family, params.toLowerCase());
            default:
                return family.toString();
        }
    }

    /**
     * Handles the processing of family-related placeholders.
     * 
     * @param family The player's family object.
     * @param params The specific family-related placeholder request.
     * @return The result of the family placeholder request.
     */
    private String handleFamilyPlaceholder(Player player, PlayerFamily family, String params) {
        switch (params) {
        case "family_firstname":
            return family.getFirstName();
        case "family_lastname":
            return getActualLastName(family.getLastName(), family.getGender());
            case "family_mother":
                return family.getMother() != null ? family.getMother().toString() : "";
            case "family_father":
                return family.getFather() != null ? family.getFather().toString() : "";
            case "family_spouse":
                return family.getSpouse() != null ? family.getSpouse().toString() : "";
            case "family_children":
                if (family.getChildren() != null && !family.getChildren().isEmpty()) {
                    return family.getChildren().stream()
                            .map(UUID::toString)
                            .collect(Collectors.joining(","));
                }
                return "";
            case "family_info":
                return new ProfileStringGenerator().generateFamilyInfo(family);
            case "family_info_translated":
                return StringUtils.colorize(StringUtils.formatString(Translator.translateKyeWorld(libraryManager, new ProfileStringGenerator().generateFamilyInfo(family),
            			langs(player)), new String[]{}));
            case "family_tree":
                return new TreeStringGenerator(family).buildFamilyTreeString();
            case "family_tree_translated":
                return StringUtils.colorize(StringUtils.formatString(Translator.translateKyeWorld(libraryManager, new TreeStringGenerator(family).buildFamilyTreeString(),
            			langs(player)), new String[]{}));
            default:
                return family.toString();
        }
    }

    /**
     * Retrieves a Family object for the given player, either as an online player or via their UUID if offline.
     * 
     * @param player The player whose family data is to be retrieved.
     * @return A Family object representing the player's family, or null if none exists.
     */
    private PlayerFamily getFamily(Player player) {
        PlayerFamily family = null;
        if (player.isOnline()) {
            family = FamilyUtils.getFamily((Player) player);
        }

        if (family == null) {
            family = FamilyUtils.getFamily(player.getUniqueId());
        }
        return family;
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
    
    private String getActualLastName(String[] lastName, Gender gender) {
    	if (lastName == null) return "";
    	
        if (gender == Gender.MALE) {
            return lastName[0] != null ? lastName[0] : "";
        } else if (gender == Gender.FEMALE && lastName.length > 1 && lastName[1] != null && !lastName[1].isEmpty()) {
            return lastName[1];
        } else {
            return lastName[0] != null ? lastName[0] : "";
        }
    }
}
