package com.zerogroup.applibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import android.telephony.SmsManager;
import android.util.Log;

/**
 * This class is made with the purpose of linking SmsBroadcastReceiver and StringResponse.
 * Its aim is to get as parameters the address and the content of a SMS message and then send the automatic response SMS.
 * 
 * @author Giac
 *
 */
public class SmsOperator {

	private String smsText;
	private String smsAddress;
	private Map<String, Integer> actions;
	private static boolean enabled;
	private static final String TAG = "OPERATOR";
	
	public SmsOperator(String anAddress, String aText){
		smsText = aText;
		smsAddress = anAddress;
		enabled = true;	//default value
		
	}
	
	public SmsOperator(){
		smsText = "";
		smsAddress = "";
		enabled = true;	//default value
		
	}
	
	public void operateSms(){
		StringResponse res = new StringResponse(smsText);
		res.addResponse("ciao", "hello");
		res.addResponse("white", "riot");

		String[] resToBeSent = res.findResponse();
		if (resToBeSent != null) {
			SmsSender sender = new SmsSender(smsAddress, resToBeSent);
			Log.d(TAG, "responses sent");
		} else {
			Log.d(TAG, "no responses");
		}
	}

	public static void enableSms() {
		enabled = true;
		Log.d(TAG, "Sms Operator Enabled");
	}
	
	public static void disableSms(){
		enabled = false;
		Log.d(TAG, "Sms Operator Enabled");
	}
	
	public static boolean isEnabled(){
		return enabled;
	}
	
}
