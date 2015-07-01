package phased.bot.irc;

import phased.bot.server.Server;


public class Main {
	public static void main(String [] args) {	
		Server server = new Server();
		server.addBot(new IRCBot(server, "Phaseds_IRC_Bot", "#phasedbottest"));
	}
}
