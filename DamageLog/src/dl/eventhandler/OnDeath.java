package dl.eventhandler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import dl.Core;
import dl.DamageLog;
import dl.files.FileManager;

public class OnDeath implements Listener{
	
	@EventHandler
	public void onDeathEvent(PlayerRespawnEvent event) {
	
		if(FileManager.config.getBoolean("DamageLogOnRespawn")) {
			Core.players.get(event.getPlayer()).printDamageLog(event.getPlayer());
			Core.players.put(event.getPlayer(), new DamageLog());
		}
	}
}