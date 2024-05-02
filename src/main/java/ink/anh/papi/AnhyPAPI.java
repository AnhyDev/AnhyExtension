package ink.anh.papi;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import ink.anh.api.AnhyLibAPI;

public class AnhyPAPI {

	AnhyLibAPI anhyPlugin;
	
    public AnhyPAPI() {
    	canRegister();
	}

	public boolean canRegister() {
        return (anhyPlugin = (AnhyLibAPI) Bukkit.getPluginManager().getPlugin("AnhyLibAPI")) != null;
    }
    
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        String placeholder = "";

        String firstPart = getFirstPart(params);
        
        switch (firstPart.toLowerCase()) {
            case "gender":
            case "family":
            	if (AnhyExtension.checkPluginExist("AnhyFamily", "ink.anh.family.AnhyFamily")) {
            		placeholder = new FamilyPAPI().onRequest(player, firstPart, params);
            	}
                break;
            default:
                return placeholder;
        }
        
		return placeholder;
    }
    
    public String getFirstPart(String input) {
        // Розділяємо рядок по першому підкресленню, лімітуючи результат до двох частин
        String[] parts = input.split("_", 2);
        // Повертаємо першу частину
        return parts[0];
    }
}