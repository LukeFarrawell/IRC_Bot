package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class Google extends Command{

	public Google() {
		super("google");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		return "http://www.google.com/search?q=" + message.replaceAll("\\s", "+");
	}

}
