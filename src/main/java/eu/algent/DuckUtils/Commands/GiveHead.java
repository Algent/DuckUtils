package eu.algent.DuckUtils.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

public class GiveHead extends AbstractCommand implements CommandExecutor {

    // dugivehead command
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args) {

        try {
            execute(sender, args);
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args)
            throws CommandException {
        Player player = commandSenderToPlayer(sender);

        // Show usage if command arguments are invalid
        if (player == null && args.length != 2)
            throw new CommandException("Console Usage: /dugivehead <headOwner> <headReceiver>");
        if (args.length > 2)
            throw new CommandException("Usage: /dugivehead [headOwner] [headReceiver]");

        String headOwner = null;
        Player headReceiver = null;

        // From player with no arguments: give him his head
        if (args.length == 0) {
            headOwner = player.getName();
            headReceiver = player;
        }
        // From player with no Receiver: give him the requested head
        else if (args.length == 1) {
            headOwner = args[0];
            headReceiver = player;
        }
        // From player or console with full arguments: give x's head to y
        else if (args.length == 2) {
            headOwner = args[0];
            headReceiver = sender.getServer().getPlayer(args[1]);
            if (headReceiver == null)
                throw new CommandException("Error: " + args[1] + " is not an online player!");
        }

        // Give the Head
        if (headOwner != null && headReceiver != null) {
            giveHeadtoPlayer(headOwner, headReceiver);
        }
    }

    private void giveHeadtoPlayer(String headOwner, Player player) {
        ItemStack toGive = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        toGive.setData(new MaterialData(Material.SKULL_ITEM, (byte) 3));
        SkullMeta meta = (SkullMeta) toGive.getItemMeta();
        meta.setOwner(headOwner);
        meta.setDisplayName(ChatColor.RESET + headOwner + "'s Head");
        toGive.setItemMeta(meta);
        player.getInventory().addItem(toGive);
        player.sendMessage(ChatColor.AQUA + "You obtained " + headOwner + "'s head.");
    }
}
