package phased.bot.server;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class WolframAlpha {
	 private static WAEngine engine = new WAEngine();
	 private static final String APP_ID = "XXXX";
	 
	 static {
		 engine.setAppID(APP_ID);
		 engine.addFormat("plaintext");
	 }
	 
	 public static String query(String input) {
		 String result = "";
		 
		  WAQuery query = engine.createQuery();
		  query.setInput(input);
		  int i = 0;
		  try {
			WAQueryResult queryResult = engine.performQuery(query);
			
			if (queryResult.isError()) {
				System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
                result = "Wolfram|Alpha search failed with an error";
			}else if(!queryResult.isSuccess()) {
				result = "Query produced 0 results";
			}else {
				 for (WAPod pod : queryResult.getPods()) {
	                    if (!pod.isError()) {
	                        for (WASubpod subpod : pod.getSubpods()) {
	                            for (Object element : subpod.getContents()) {
	                                if (element instanceof WAPlainText) {
	                                	if(i == 1) {
	                                	result = ((WAPlainText) element).getText();
	                                	return result;
	                                	}else {
	                                		i++;
	                                	}
	                                }
	                            }
	                        }
	                    }
	                }
			}
			
		} catch (WAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return result;
	 }
}
