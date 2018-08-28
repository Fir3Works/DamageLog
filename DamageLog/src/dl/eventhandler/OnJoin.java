package dl.eventhandler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dl.Core;
import dl.DamageLog;
import dl.files.FileManager;

public class OnJoin implements Listener{
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {	
		Core.players.put(event.getPlayer(), new DamageLog());
		
		if(!FileManager.damageValues.isSet("DamageOutput." + event.getPlayer().getName())) {
			FileManager.damageValues.set("DamageOutput."  + event.getPlayer().getName(), 1d);
			FileManager.damageValues.set("DamageInput."  + event.getPlayer().getName(), 1d);
		}
	}
}