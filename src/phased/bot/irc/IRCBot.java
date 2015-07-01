package phased.bot.irc;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

import phased.bot.server.ChatterBotEngine;
import phased.bot.server.Server;

public class IRCBot extends PircBot{

	private String channelName;
	private String botName = "";

	private String responseLetter = "#";

	private Server server;
	
	private String chattingWith = "";
	
	private ChatterBotEngine chatterBot;

	public IRCBot(Server server, String botName, String channelName) {
		this.setVerbose(false); //debug
		connectToServer(botName, "irc.freenode.net");
		this.joinChannel(channelName);
		this.channelName = channelName;
		this.botName = botName;
		this.server = server;
		this.chatterBot = new ChatterBotEngine(0);
	}

	public IRCBot(Server server, String botName, String channelName,String responseLetter) {
		this.setVerbose(true); //debug
		connectToServer(botName, "irc.freenode.net");
		this.joinChannel(channelName);
		this.channelName = channelName;
		this.botName = botName;
		this.responseLetter = responseLetter;
		this.server = server;
		this.chatterBot = new ChatterBotEngine(0);
	}

	public void connectToServer(String botName, String hostName) {
		this.setName(botName);
		try {
			this.connect(hostName);
		} catch (NickAlreadyInUseException e) {
			connectToServer(botName += "_", hostName);
			System.out.println(botName + " is already in use in use");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IrcException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if(message.startsWith(responseLetter)) {
			message = message.substring(responseLetter.length());
			message = message.trim();
			String result = server.process(this, channel, sender, login, hostname, message);

			if(!result.equals("")) {
				sendMessage(channel, result);
			}
		}else if(sender.equalsIgnoreCase(chattingWith)) {
			sendMessage(channel, server.reply(this, channel, sender, login, hostname, message));
		}else if(message.startsWith(getNick())) {
			sendMessage(channel, server.reply(this, channel, sender, login, hostname, message));
		}
	}

	@Override
	public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
		if (recipientNick.equalsIgnoreCase(getNick())) {
			joinChannel(channel);
		}
	}

	@Override
	public void onAction(String sender, String login, String hostname, String target, String action) {
		String result = server.processAction(this, sender, login, hostname, target, action);
		if(!result.equals("")) {
			sendAction(channelName, result);
		}
	}
	
	public String chatter(String statement) {
		if(chatterBot == null) {
			resetChatterBot();
		}
		return chatterBot.replyTo(statement);
	}

	public void setDebug(boolean flag) {
		this.setVerbose(flag); 
	}

	public String getChannel() {
		return channelName;
	}
	
	public String getChatWith() {
		return chattingWith;
	}
	
	public void setChattingWith(String str) {
		chattingWith = str;
	}
	
	public void resetChatterBot() {
		this.chatterBot = new ChatterBotEngine(0);
	}
}
