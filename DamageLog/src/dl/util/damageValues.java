package dl.util;

import org.bukkit.entity.Player;

import dl.data.LogFile;
import dl.files.FileManager;

public class damageValues {
	//Variablen
	
	//Methoden
	
	public static void updateDamageValue(Player player, LogFile logFile) {
		if(logFile.getDirection() == DamageDirection.IN) {
			double damagein = FileManager.damageValues.getDouble("DamageInput." + player.getName());
			double damage = logFile.getDamage();
			
			damagein = damagein + damage;
			
			FileManager.damageValues.set("DamageInput." +  player.getDisplayName(), damagein);
		}
		if(logFile.getDirection() == DamageDirection.OUT){
			double damageout = FileManager.damageValues.getDouble("DamageOutput." + player.getName());
			double damage = logFile.getDamage();
			
			damageout = damageout + damage;
			
			FileManager.damageValues.set("DamageOutput." + player.getDisplayName(), damageout);
		}
	}
}
