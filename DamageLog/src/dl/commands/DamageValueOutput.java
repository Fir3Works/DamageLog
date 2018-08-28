package dl.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dl.files.FileManager;
import dl.util.MathUtil;

public class DamageValueOutput implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			double damageout = FileManager.damageValues.getDouble("DamageOutput." + player.getName());
			double damagein = FileManager.damageValues.getDouble("DamageInput." + player.getName());
			double damagevalue = damageout/damagein;
			damagevalue = MathUtil.roundToTenth(damagevalue);
			
			player.sendMessage(FileManager.config.getString("Language.DamageValueMessage").replace("%damagevalue%", String.valueOf(damagevalue)));
			
			return true;
		} else {
			sender.sendMessage("This command can only be used by players.");
			return true;
		}
	}
}
