package dl.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import dl.Core;

public class FileManager {
	
	public FileManager() throws IOException {
		this.setupFolder();
		this.setupFiles();
	}
	
	//Variablen
	private final File main = Core.getPlugin().getDataFolder();
	
	public static FileConfiguration entityLanguage;
	public static FileConfiguration damageLanguage;
	public static FileConfiguration config;
	public static FileConfiguration damageValues;
	
	private File damageFile = new File(this.main + File.separator + "DamageTypeLanguage.yml");
	private File entityFile = new File(this.main + File.separator + "EntityLanguage.yml");
	private File configFile = new File(this.main + File.separator + "Config.yml");
	private File damageValueFile = new File(this.main + File.separator + "damageValues");
	
	//Methoden
	public void setupFolder() {
		if(!this.main.exists()) {
			this.main.mkdirs();
		}
	}
	
	public void setupFiles() {
		if(!this.damageFile.exists()) Core.getPlugin().saveResource("DamageTypeLanguage.yml", false);
		if(!this.entityFile.exists()) Core.getPlugin().saveResource("EntityLanguage.yml", false);
		if(!this.configFile.exists()) Core.getPlugin().saveResource("Config.yml", false);
		if(!this.damageValueFile.exists()) Core.getPlugin().saveResource("damageValues.yml", false);
		
		config = YamlConfiguration.loadConfiguration(configFile);
		entityLanguage = YamlConfiguration.loadConfiguration(entityFile);
		damageLanguage = YamlConfiguration.loadConfiguration(damageFile);
		damageValues = YamlConfiguration.loadConfiguration(damageValueFile);
	}
	
	public void reloadFiles() {
		config = YamlConfiguration.loadConfiguration(configFile);
		entityLanguage = YamlConfiguration.loadConfiguration(entityFile);
		damageLanguage = YamlConfiguration.loadConfiguration(damageFile);
		damageValues = YamlConfiguration.loadConfiguration(damageValueFile);
	}
	
	public void saveFiles() {
		try {
			damageValues.save(damageValueFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}