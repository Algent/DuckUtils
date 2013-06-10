package eu.algent.DuckUtils.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand {

    public abstract void execute(CommandSender sender, String[] args)
            throws CommandException;

    protected Player commandSenderToPlayer(CommandSender sender) {
        if (sender instanceof Player)
            return (Player) sender;
        return null;
    }
}
