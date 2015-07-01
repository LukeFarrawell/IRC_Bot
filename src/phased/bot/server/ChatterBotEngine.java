package phased.bot.server;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

public class ChatterBotEngine {
	private ChatterBotFactory factory = new ChatterBotFactory();
	private ChatterBot bot;
	private ChatterBotSession session;

	private int failCreateCount = 0;

	public ChatterBotEngine(int botID) {
		try {
			if(botID == 0) {
				bot = factory.create(ChatterBotType.CLEVERBOT);
			}else if(botID == 1){
				bot = factory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
			}
			session = bot.createSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void recreate() {
		try {
			bot = factory.create(ChatterBotType.CLEVERBOT);
			session = bot.createSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String replyTo(String statement) {
		try {
			return session.think(statement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(failCreateCount >= 20) {
			return "oops, something went wrong, try again later";
		}
		failCreateCount++;
		recreate();
		return "oops, something went wrong";
	}
}
