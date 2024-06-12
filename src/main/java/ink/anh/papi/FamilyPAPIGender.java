package ink.anh.papi;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import ink.anh.api.LibraryManager;
import ink.anh.api.lingo.Translator;
import ink.anh.api.utils.LangUtils;
import ink.anh.family.GlobalManager;
import ink.anh.family.fplayer.PlayerFamily;
import ink.anh.family.fplayer.gender.Gender;

/**
 * Handles all gender-related placeholder requests for the family system, providing details such as
 * gender type, language keys, symbols, and associated Minecraft and HEX colors.
 */
public class FamilyPAPIGender {

    private final LibraryManager libraryManager;

    /**
     * Constructor that initializes the FamilyPAPIGender with access to the global library manager from AnhyFamily.
     *
     * @param familyPlugin The AnhyFamily plugin instance.
     */
    public FamilyPAPIGender() {
        this.libraryManager = GlobalManager.getInstance();
    }

    /**
     * Handles the gender-specific request based on the parameters provided.
     *
     * @param player The offline player for whom the gender data is being requested.
     * @param family The family data associated with the player.
     * @param params A string specifying the type of gender-related data requested.
     * @return The requested gender information or {@code null} if not applicable or unavailable.
     */
    public String onRequestGender(OfflinePlayer player, PlayerFamily family, @NotNull String params) {
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

    private String getGenderType(PlayerFamily family) {
        Gender gender = getGender(family);
        return gender != null ? gender.name() : null;
    }

    private String getGenderLangKey(PlayerFamily family) {
        Gender gender = getGender(family);
        return gender != null ? Gender.getKey(gender) : null;
    }

    private String getGenderHEXColor(PlayerFamily family) {
        Gender gender = getGender(family);
        return gender != null ? Gender.getColor(gender) : null;
    }

    private String getGenderMCColor(PlayerFamily family) {
        Gender gender = getGender(family);
        return gender != null ? Gender.getMinecraftColor(gender) : null;
    }

    private String getGenderSymbol(PlayerFamily family) {
        Gender gender = getGender(family);
        return gender != null ? Gender.getSymbol(gender) : null;
    }

    /**
     * Retrieves the language-specific name for the gender based on player's language settings.
     *
     * @param player The player for whom the language-specific name is to be retrieved.
     * @param family The family data of the player.
     * @return The translated name of the gender if available; otherwise, {@code null}.
     */
    private String getGenderLangName(OfflinePlayer player, PlayerFamily family) {
        Gender gender = getGender(family);
        if (gender == null) {
            return null;
        }
        return Translator.translateKyeWorld(libraryManager, Gender.getKey(gender) , langs(player));
    }

    private Gender getGender(PlayerFamily family) {
        return family != null ? family.getGender() : null;
    }

    /**
     * Returns the language settings for the player, defaulting to the system's default language if offline.
     *
     * @param player The player whose language settings are queried.
     * @return An array of language codes.
     */
    private String[] langs(OfflinePlayer player) {
        return player.isOnline() ? LangUtils.getPlayerLanguage((Player) player) : new String[] {libraryManager.getDefaultLang()};
    }
}
