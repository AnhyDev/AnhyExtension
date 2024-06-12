package ink.anh.papi;

import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import ink.anh.family.fplayer.PlayerFamily;
import ink.anh.family.fplayer.info.FamilyTree;
import ink.anh.family.fplayer.info.InfoGenerator;
import ink.anh.family.util.FamilyUtils;

/**
 * FamilyPAPI is responsible for handling all family-related placeholder requests.
 * It provides functionalities to fetch and format family data for players.
 */
public class FamilyPAPI {

    /**
     * Constructor that initializes the FamilyPAPI and retrieves an instance of AnhyFamily.
     */
    public FamilyPAPI() {
    }

    /**
     * Processes the request for family-related placeholders based on the specified parameters.
     * 
     * @param player The offline player for whom the placeholders are being requested.
     * @param firstPart The category of the family-related placeholder (e.g., gender, family).
     * @param params The full placeholder request string.
     * @return A string value representing the result of the requested placeholder or an empty string if no data is found.
     */
    public String onRequest(OfflinePlayer player, String firstPart, @NotNull String params) {
    	PlayerFamily family = getFamily(player);
        
        switch (firstPart.toLowerCase()) {
            case "gender":
                return new FamilyPAPIGender().onRequestGender(player, family, params);
            case "family":
                return family.toString();
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
                return new InfoGenerator().generateFamilyInfo(family);
            case "family_tree":
                return new FamilyTree(family).buildFamilyTreeString();
            default:
                return null;
        }
    }

    /**
     * Retrieves a Family object for the given player, either as an online player or via their UUID if offline.
     * 
     * @param player The player whose family data is to be retrieved.
     * @return A Family object representing the player's family, or null if none exists.
     */
    private PlayerFamily getFamily(OfflinePlayer player) {
    	PlayerFamily family = null;
        if (player.isOnline()) {
            family = FamilyUtils.getFamily((Player) player);
        }
        
        if (family == null) {
            family = FamilyUtils.getFamily(player.getUniqueId());
        }
        return family;
    }
}
