package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class ChangeName extends Command {

	public ChangeName() {
		super("nick");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		message = message.trim();
		bot.changeNick(message);
		return "";
	}

}
