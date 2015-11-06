package com.zerogroup.applibrary;

import android.telephony.SmsManager;
import android.util.Log;

/**
 * 
 * This class enables the user to send SMS messages in an automatic way. It has
 * various options: single-multiple addresses, single-multiple messages.
 * 
 * @author Giac
 * @version 1.0
 */
public class SmsSender {

	public static String SNDR = "MESSAGE_SENDER";

	/**
	 * Sends message to given address.
	 * 
	 * @param address
	 *            Addressto which the message has to be sent.
	 * @param message
	 *            Message to send.
	 */
	public SmsSender(String address, String message) {
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(address, null, message, null, null);
		Log.d(SNDR, "message sent");
	}

	/**
	 * Sends multiple messages to given address.
	 * 
	 * @param address
	 *            Address to which the message has to be sent.
	 * @param messages
	 *            An array containing all the messages that need to be delivered
	 *            to the address.
	 */
	public SmsSender(String address, String[] messages) {
		SmsManager manager = SmsManager.getDefault();
		for (int i = 0; i < messages.length; i++) {
			SmsSender sender = new SmsSender(address, messages[i]);
		}
	}

	/**
	 * Sends the same message to multiple addresses.
	 * 
	 * @param addresses
	 *            An array containing all the addresses to which the message has
	 *            to be sent.
	 * @param message
	 *            Message to send.
	 */
	public SmsSender(String[] addresses, String message) {
		SmsManager manager = SmsManager.getDefault();
		for (int i = 0; i < addresses.length; i++) {
			SmsSender sender = new SmsSender(addresses[i], message);
		}
	}

}
