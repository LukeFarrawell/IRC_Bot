package phased.bot.server.replies;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public abstract class Reply {
	
	public final String identifier;
	
	public Reply(String identifier) {
		this.identifier = identifier;
	}
	
	public abstract String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message);
	
	public String getIdentifier() {
		return identifier;
	}
	
}
