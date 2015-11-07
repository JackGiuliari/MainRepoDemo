package com.zerogroup.applibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class operates on a given string. Its aim is to find a given pattern
 * inside the string (a "trigger") and to return a pre-defined String as a
 * "response".
 * 
 * @author Giac
 * @version 1.0
 */

public class StringResponse {

	private String myString;
	private Map<String, String> myResponses;

	/**
	 * Constructor method.
	 * 
	 * @param aString
	 *            the String on which the class will operate.
	 */
	public StringResponse(String aString) {
		myString = aString.toLowerCase();	//change to lower case, triggers are not case sensitive!!!
		myResponses = new HashMap<String, String>();
	}

	/**
	 * Finds the strings saved as "triggers" inside the given string.
	 * 
	 * @return an Object array containing all the "response" strings that were found.
	 */
	public Object[] findResponse() {
		List<String> toReply = new ArrayList<String>();
		Scanner scan = new Scanner(myString);

		while (scan.hasNext()) {
			String temp = scan.next();
			if (myResponses.containsKey(temp))
				toReply.add(myResponses.get(temp));
		}

		scan.close();
		return toReply.toArray();
	}

	/**
	 * Adds a new trigger-response pattern that will be used by findResponse()
	 * method.
	 * 
	 * @param trigger
	 *            The key string.
	 * @param response
	 *            The response that will be sent when the trigger is found in
	 *            myString.
	 * @return the value of the previous response related to the trigger (if
	 *         present)
	 */
	public String addResponse(String trigger, String response) {
		return (String) myResponses.put(trigger.toLowerCase(), response);	//all triggers are lower case
	}

	/**
	 * Checks if the trigger is already present as an option.
	 * 
	 * @param trigger
	 * @return true if trigger is already present, false otherwise.
	 */
	public boolean hasResponse(String trigger) {
		return myResponses.containsKey(trigger);
	}

	/**
	 * Removes the trigger-response pattern specified by the trigger
	 * 
	 * @param trigger
	 *            String key of the pattern that needs to be removed
	 * @return the response to the removed trigger(if present).
	 */
	public String removeResponse(String trigger) {
		return (String) myResponses.remove(trigger);
	}
}
