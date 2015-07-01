# IRC Bot
An IRC Bot built ontop of PircBot.

An easy to extend IRC Bot, which allows you to register new commands without altering the bot.

#Libraries Required
[JDBC](http://mvnrepository.com/artifact/mysql/mysql-connector-java/5.1.35)  
[Commons-codec-1.10.jar](http://mvnrepository.com/artifact/commons-codec/commons-codec/1.10)  
[commons-logging-1.2.jar](http://mvnrepository.com/artifact/commons-logging/commons-logging/1.2)  
[httpclient-4.0.2.jar](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.0.2)  
[httpcore-4.0.1_1.jar](http://mvnrepository.com/artifact/org.apache.geronimo.bundles/httpcore/4.0.1_1)  
[pircbot.jar](http://www.jibble.org/pircbot.php)  
[WolframAlpha-1.1.jar](http://products.wolframalpha.com/api/libraries.html)

#Included API
This API does not need to be downloaded, as it is included in the source code.  
[Chatter Bot API](https://github.com/pierredavidbelanger/chatter-bot-api)

#Creating a new Command Or Action
Both Command and Action have a similar style, just import and extend Action.

```java
import phased.bot.irc.IRCBot;
import phased.bot.server.Server;
import phased.bot.server.commands.Command;

public class TestCommand extends Command{

	public TestCommand() {
		super("testCommand");
	}

	@Override
	public String process(IRCBot bot, Server server, String channel, String sender, String login, String hostname, String message) {
		return sender + ", I saw and processed your Test Command!";
	}
}
```

#Setting up the bot
###MySQL Setup
The bot requires a MySQL Database.  
Run the SQL script inside of the database folder named createDatabase.sql

###Creating the Bot
```java
	public static void main(String [] args) {	
		Server server = new Server();
		server.addOwner("Phased");
		server.addBot(new IRCBot(server, "Bot1", "#phasedbottest"));
	}
```

#Registering a new Command or Action
```java
  server.register(new TestCommand()); //register a Command
  server.register(new TestAction()); //register an Action
  server.registerOwner(new OwnerOnlyCommand()); //register a Command that only people that have been added as an Owner.
```
