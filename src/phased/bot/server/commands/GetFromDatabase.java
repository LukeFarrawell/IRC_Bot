package phased.bot.server.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class GetFromDatabase extends Command{

	public GetFromDatabase() {
		super("get");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		String output = "";
		
		ResultSet result = server.getDatabase().query("select message from messages where identifier = '"+message+"';");
		try {
			if(result.next()) {
				output = sender+", "+result.getString("message").trim();
			}else {
				output = "no message stored with the indetentifier of "+message;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

}
