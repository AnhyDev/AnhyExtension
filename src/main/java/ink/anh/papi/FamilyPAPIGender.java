package ink.anh.papi;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import ink.anh.api.LibraryManager;
import ink.anh.api.lingo.Translator;
import ink.anh.api.utils.LangUtils;
import ink.anh.family.AnhyFamily;
import ink.anh.family.common.Family;
import ink.anh.family.gender.Gender;

public class FamilyPAPIGender {

    private final LibraryManager libraryManager;
    
    public FamilyPAPIGender(AnhyFamily familyPlugin) {
		this.libraryManager = familyPlugin.getGlobalManager();
    }

	public String onRequestGender(OfflinePlayer player, Family family, @NotNull String params) {
        
        switch (params.toLowerCase()) {
            case "gender":
                return getGenderType(family);
            case "gender_key":
                return getGenderLangKey(family);
            case "gender_lang":
                return getGenderLangName(player, family);
            case "gender_symbol":
                return getGenderSymbol(family);
            case "gender_mccolor":
                return getGenderMCColor(family);
            case "gender_hexcolor":
                return getGenderHEXColor(family);
            default:
                return null;
        }
    }

	private String getGenderType(Family family) {
    	Gender gender = getGender(family);
    	if(gender == null) {
    		return null;
    	}
    	
		return gender.name();
    }

	private String getGenderLangKey(Family family) {
    	Gender gender = getGender(family);
    	if(gender == null) {
    		return null;
    	}
    	
		return Gender.getKey(gender);
    }

    public String getGenderHEXColor(Family family) {
    	Gender gender = getGender(family);
    	if(gender == null) {
    		return null;
    	}
    	
		return Gender.getColor(gender);
    }

    public String getGenderMCColor(Family family) {
    	Gender gender = getGender(family);
    	if(gender == null) {
    		return null;
    	}
    	
		return Gender.getMinecraftColor(gender);
    }

    public String getGenderSymbol(Family family) {
    	Gender gender = getGender(family);
    	if(gender == null) {
    		return null;
    	}
    	
		return Gender.getSymbol(gender);
    }

    public String getGenderLangName(OfflinePlayer player, Family family) {
    	Gender gender = getGender(family);
    	if(gender == null) {
    		return null;
    	}
		return Translator.translateKyeWorld(libraryManager, Gender.getKey(gender) , langs(player));
    }

    private Gender getGender(Family family) {
    	if(family == null) {
    		return null;
    	}
		return family.getGender();
    }
    
    private String[] langs(OfflinePlayer player) {
    	return player.isOnline() ? LangUtils.getPlayerLanguage((Player) player) : new String[] {libraryManager.getDefaultLang()};
    }
}
