package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class Kick extends Command{

	public Kick() {
		super("kick");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		
		message = message.trim();
		server.kickBot(message, channel);
		
		return "";
	}
	
}
