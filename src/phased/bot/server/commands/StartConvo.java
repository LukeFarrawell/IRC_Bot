package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class StartConvo extends Command{

	public StartConvo() {
		super("startconvo");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		return (bot.getChatWith()+", Hey!");
	}

}
