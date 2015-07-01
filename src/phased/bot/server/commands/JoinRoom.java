package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class JoinRoom extends Command {

	public JoinRoom() {
		super("join");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		message = message.trim();
		server.addBot(new IRCBot(server, bot.getNick(), message));
		return "";
	}

}
