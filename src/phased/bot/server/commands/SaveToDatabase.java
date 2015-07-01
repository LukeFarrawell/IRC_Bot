package phased.bot.server.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import phased.bot.irc.IRCBot;
import phased.bot.server.Server;

public class SaveToDatabase extends Command{

	public SaveToDatabase() {
		super("set");
	}

	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		
		String setName = message.substring(0, message.indexOf(","));
		message = message.substring(message.indexOf(",") + 1, message.length());
		message = message.trim();
		
		
		ResultSet result = server.getDatabase().query("select message from messages where identifier = '"+setName+"';");
		try {
			if(result.next()) {
				String query = "UPDATE messages SET message = '"+message+"' WHERE identifier = '"+setName+"';";
				server.getDatabase().update(query);
			}else {
				String query = "INSERT INTO messages VALUES('"+setName+"', '"+message+"','"+sender+"', 0);";
				server.getDatabase().insert(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}
