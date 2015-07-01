package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class ChatWith extends Command {

	public ChatWith() {
		super("chatwith");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		bot.setChattingWith(message.trim());
		
		return "";
	}

}
