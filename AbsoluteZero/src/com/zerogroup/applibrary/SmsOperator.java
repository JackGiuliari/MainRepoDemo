package com.zerogroup.applibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * This class is made with the purpose of linking SmsBroadcastReceiver and
 * StringResponse. Its aim is to get as parameters the address and the content
 * of a SMS message and then send the automatic response SMS.
 * 
 * @author Giac
 *
 */
public class SmsOperator {

	private String smsText;
	private String smsAddress;
	private Map<String, Integer> actions;
	private Context context;
	private static boolean enabled;
	private static final String TAG = "OPERATOR";

	public SmsOperator(String anAddress, String aText, Context aContext) {
		smsText = aText;
		smsAddress = anAddress;
		enabled = true; // default value
		context = aContext;
	}

	public SmsOperator() {
		smsText = "";
		smsAddress = "";
		enabled = true; // default value
	}

	public void operateSms() {
		if (enabled) {
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

		LocationOperator locOp = new LocationOperator(context, smsText, smsAddress);
		String location = locOp.getLocation();
		if (location != null) {
			SmsSender sender = new SmsSender(smsAddress, location);
		}
		if(locOp.isLocationMessage()){
			locOp.showLocationOnGoogleMaps();
		}

	}

	public static void enableSms() {
		enabled = true;
		Log.d(TAG, "Sms Operator Enabled");
	}

	public static void disableSms() {
		enabled = false;
		Log.d(TAG, "Sms Operator Enabled");
	}

	public static boolean isEnabled() {
		return enabled;
	}

}
