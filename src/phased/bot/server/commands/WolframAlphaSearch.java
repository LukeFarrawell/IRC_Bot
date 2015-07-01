package phased.bot.server.commands;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;
import phased.bot.server.WolframAlpha;

public class WolframAlphaSearch extends Command{

	public WolframAlphaSearch() {
		super("wolfram");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		return WolframAlpha.query(message);
	}

}
