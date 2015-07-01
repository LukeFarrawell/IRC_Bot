package phased.bot.server.actions;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public abstract class Action {
	
	public final String identifier;
	
	public Action(String identifier) {
		this.identifier = identifier;
	}
	public abstract String process(IRCBot bot, Server server, String sender, String login, String hostname, String target, String action);
	
	public String getIdentifier() {
		return identifier;
	}
}
