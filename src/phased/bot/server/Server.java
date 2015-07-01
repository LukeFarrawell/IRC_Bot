package phased.bot.server;

import java.util.ArrayList;

import phased.bot.irc.IRCBot;
import phased.bot.server.actions.Action;
import phased.bot.server.commands.AddBot;
import phased.bot.server.commands.Calculate;
import phased.bot.server.commands.ChangeName;
import phased.bot.server.commands.ChatWith;
import phased.bot.server.commands.Command;
import phased.bot.server.commands.GetFromDatabase;
import phased.bot.server.commands.Google;
import phased.bot.server.commands.JoinRoom;
import phased.bot.server.commands.Kick;
import phased.bot.server.commands.RemoveChatWith;
import phased.bot.server.commands.SaveToDatabase;
import phased.bot.server.commands.StartConvo;
import phased.bot.server.commands.WolframAlphaSearch;
import phased.bot.server.replies.Chatter;
import phased.bot.server.replies.Reply;

public class Server {

	private ArrayList<Command> commands;

	private ArrayList<Action> actions;
	
	private ArrayList<IRCBot> bots;
	
	private DatabaseConnection database;
	
	private Reply reply;

	public Server() {
		commands = new ArrayList<Command>();
		actions = new ArrayList<Action>();
		bots = new ArrayList<IRCBot>();
		database = new DatabaseConnection();
		reply = new Chatter();
		registerDefaultCommands();
	}

	public String process(IRCBot bot, String channel, String sender, String login, String hostname, String message) {
		String output = "";

		for(int i = 0; i < commands.size(); i++) {
			
			if(message.toLowerCase().startsWith(commands.get(i).getIdentifier().toLowerCase())) {
				message = message.substring(commands.get(i).getIdentifier().length());
				
				output = commands.get(i).process(bot, this, channel, sender, login, hostname, message);
			}
		}

		return output;
	}

	public String processAction(IRCBot bot, String sender, String login, String hostname, String target, String action) {
		String output = "";

		for(int i = 0; i < actions.size(); i++) {
			if(action.toLowerCase().contains(actions.get(i).getIdentifier())) {
				output = actions.get(i).process(bot, this, sender, login, hostname, target, action);
			}
		}

		return output;
	}
	
	public String reply(IRCBot bot, String channel, String sender, String login, String hostname, String message) {
		message = message.substring(message.indexOf(",") + 1);
		message = message.trim();
		return reply.process(bot, this, channel, sender, login, hostname, message);
	}

	public void register(Command command) {
		commands.add(command);
	}

	public void register(Action action) {
		actions.add(action);
	}
	
	public DatabaseConnection getDatabase() {
		return database;
	}
	
	public void registerDefaultCommands() {
		register(new Calculate());
		register(new GetFromDatabase());
		register(new Google());
		register(new SaveToDatabase());
		register(new WolframAlphaSearch());
		register(new ChatWith());
		register(new RemoveChatWith());
		register(new AddBot());
		register(new ChangeName());
		register(new Kick());
		register(new JoinRoom());
		register(new StartConvo());
	}
	
	public void addBot(IRCBot bot) {
		bots.add(bot);
	}

	public ArrayList<IRCBot> getBots() {
		return bots;
	}

	public void kickBot(String name, String channel) {
		System.out.println(bots.size());
		for(int i = 0; i < bots.size(); i++) {
			System.out.println(bots.get(i).getChannel());
			if(bots.get(i).getChannel().equalsIgnoreCase(channel) && bots.get(i).getNick().equalsIgnoreCase(name)) {
				IRCBot bot = bots.get(i);
				bots.remove(i);
				bot.disconnect();
				bot = null;
				System.out.println("kicked the bot");
			}
		}
	}
}
