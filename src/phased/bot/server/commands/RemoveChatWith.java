package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class RemoveChatWith extends Command {

	public RemoveChatWith() {
		super("removechatwith");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		bot.setChattingWith("");
		return "";
	}

}
