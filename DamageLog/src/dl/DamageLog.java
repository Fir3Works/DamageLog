package dl;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import dl.data.LogFile;
import dl.files.FileManager;
import dl.util.DamageDirection;
import dl.util.DamageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class DamageLog {
	
	
	//Variablen
	private List<LogFile> log = Lists.newArrayList();
	private final static int maxSize = FileManager.config.getInt("LogSize");
	private final static String LogTitle = FileManager.config.getString("Language.LogName");
	private final static String InfoName = FileManager.config.getString("Language.InfoName");
	private final static String MessageOut = FileManager.config.getString("Language.DamageOutMessage");
	private final static String MessageIn = FileManager.config.getString("Language.DamageInMessage");
		
	
	//Methoden
	public void addLog(LogFile logfile) {
		int logsize = this.log.size();
		
		if(logsize >= maxSize) {
			this.log.remove(0);
		}
		
		this.log.add(logfile);
		
	}

	public void printDamageLog(Player player) {
		String opponent;
		String finalmessage;
		Double damage;
		int counter = 0;
				
		player.sendMessage(LogTitle);
		
		for (LogFile logFile : log) {
			counter = counter++;
			damage = logFile.getDamage();
			String Message;
			
			if(logFile.getDirection() == DamageDirection.IN) {
				opponent = logFile.getAttacker();
				Message = MessageIn;
			
			}else {
				opponent = logFile.getDefender();
				Message = MessageOut;
			}
			
			finalmessage =  Message.replace("%damage%", damage.toString()).replace("%entity%", opponent); 
			
			BaseComponent comp = new TextComponent("");
			TextComponent dmgLog = new TextComponent(finalmessage);
			comp.addExtra(dmgLog);
			
			TextComponent infotextcomp = new TextComponent(InfoName);
			
			
			
			infotextcomp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, this.getHoverText(logFile)));
			comp.addExtra(infotextcomp);
			
			player.spigot().sendMessage(comp);
			
		}
	}
	
	private BaseComponent[] getHoverText(LogFile file) {
		List<String> stringlist = FileManager.config.getStringList("Language.HoverMessage");
		ComponentBuilder compbuilder = new ComponentBuilder(""); 
		
		int i = 0;
		for (String string : stringlist) {
			i++;
			compbuilder.append(string.replace("%type%", file.getType().getDisplayName()));
			if (i != stringlist.size()) {
			compbuilder.append("\n");
			}
		}
		
		return compbuilder.create();
	}
	
	//Getter
	public List<LogFile> getLog() {
		return log;
	}

	//Setter
	public void setLog(List<LogFile> log) {
		this.log = log;
	}
	
}
