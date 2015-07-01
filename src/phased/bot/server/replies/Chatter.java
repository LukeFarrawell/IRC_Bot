package phased.bot.server.replies;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class Chatter extends Reply {

	public Chatter() {
		super("");
	}

	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		message = message.trim();
		System.out.println(message);
		if(sender.toLowerCase().equals(bot.getChatWith().toLowerCase())) {
			return bot.chatter(message);
		}else {
			return sender + ", "+bot.chatter(message);
		}
	}

}