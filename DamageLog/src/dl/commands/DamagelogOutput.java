package dl.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dl.Core;

public class DamagelogOutput implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Core.players.get((Player) sender).printDamageLog((Player) sender);
			return true;
		} else {
			sender.sendMessage("This command can only be used by players.");
			return true;
		}
	}
}