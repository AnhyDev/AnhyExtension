package ink.anh.papi;

import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import ink.anh.family.AnhyFamily;
import ink.anh.family.common.Family;
import ink.anh.family.info.FamilyTree;
import ink.anh.family.info.InfoGenerator;
import ink.anh.family.util.FamilyUtils;

public class FamilyPAPI {

    private final AnhyFamily familyPlugin;
    
    public FamilyPAPI() {
		this.familyPlugin = AnhyFamily.getInstance();
	}

	public String onRequest(OfflinePlayer player, String firstPart, @NotNull String params) {
        Family family = getFamily(player);
        
        switch (firstPart.toLowerCase()) {
            case "gender":
                return new FamilyPAPIGender(familyPlugin).onRequestGender(player, family, params);
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
                return InfoGenerator.generateFamilyInfo(family);
            case "family_tree":
                return new FamilyTree(family).buildFamilyTreeString();
            default:
                return null;
        }
    }

    private Family getFamily(OfflinePlayer player) {
    	Family family = null;
    	if (player.isOnline()) {
    		family = FamilyUtils.getFamily((Player) player);
    	}
    	
    	if (family == null) {
    		family = FamilyUtils.getFamily(player.getUniqueId());
    	}
		return family;
    }
}
