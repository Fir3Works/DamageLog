package dl;

import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Maps;

import dl.commands.DamageValueOutput;
import dl.commands.DamagelogOutput;
import dl.eventhandler.OnDamage;
import dl.eventhandler.OnDeath;
import dl.eventhandler.OnJoin;
import dl.files.FileManager;

public class Core extends JavaPlugin {
	public static Core plugin;
	private FileManager manager;
	public static HashMap<Player, DamageLog> players = Maps.newHashMap();
	
	@Override
	public void onLoad() {
		plugin = this;

	}

	@Override
	public void onEnable() {
		getCommand("damagelog").setExecutor(new DamagelogOutput());
		getCommand("damagevalue").setExecutor(new DamageValueOutput());
		try {
			this.manager = new FileManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Metrics(this);
		Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
		Bukkit.getPluginManager().registerEvents(new OnDamage(), this);
		Bukkit.getPluginManager().registerEvents(new OnDeath(), this);
	}

	public static Core getPlugin() {
		return plugin;
	}

	public FileManager getFileManager() {
		return this.manager;
	}
	
	@Override
	public void onDisable() {
		this.getFileManager().saveFiles();
	}
}