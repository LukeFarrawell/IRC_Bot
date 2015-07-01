package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class AddBot extends Command {

	public AddBot() {
		super("bot++");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		String name = message.substring(0, message.indexOf(','));
		message = message.substring(name.length(), message.length());

		String channelToJoin = message.substring(message.indexOf(",") + 1, message.length());
		message = channelToJoin.substring(channelToJoin.indexOf(",") + 1, channelToJoin.length());
		channelToJoin = channelToJoin.substring(0, channelToJoin.indexOf(","));


		message = message.trim();
		channelToJoin = channelToJoin.trim();
		name = name.trim();

		server.addBot(new IRCBot(server, name, channelToJoin, message));
		return "";
	}

}
