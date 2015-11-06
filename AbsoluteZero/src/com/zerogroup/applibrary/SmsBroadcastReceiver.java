package com.zerogroup.applibrary;

import com.zerogroup.absolutezero.R;
import com.zerogroup.absolutezero.SmsActivity;
import com.zerogroup.absolutezero.R.drawable;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle intentExtra = intent.getExtras();

		String smsText = readSms(context, intent);
		String smsAddress = getAddress(context, intent);

		Log.d("MESSAGE", smsText);
		Log.d("ADDRESS", smsAddress);

		SmsOperator operator = new SmsOperator(smsAddress, smsText);
		operator.operateSms();

		sendNotification(smsAddress, smsText, context);

	}

	public String readSms(Context context, Intent intent) {
		String message = "";

		Bundle intentExtra = intent.getExtras();
		
		if(intentExtra != null){
			Object[] extraPdus = (Object[]) intentExtra.get("pdus");
			SmsMessage[] newSms = new SmsMessage[extraPdus.length];

			for(int i = 0; i< newSms.length; i++){
				newSms[i] = SmsMessage.createFromPdu((byte[]) extraPdus[i]);
				message = message + newSms[i].getMessageBody();
			}
			
			Log.d("MESSAGE", message);
		return message;
		}
		
		Log.d("MESSAGE", "no extras fund, return null");
		return null;
	}
	
	public String getAddress(Context context, Intent intent){
		String address = "";
		//Get extras from intent
		Bundle intentExtra = intent.getExtras();
		if(intentExtra != null){
			Object[] extraPdus = (Object[]) intentExtra.get("pdus");
			SmsMessage[] newSms = new SmsMessage[extraPdus.length];
			
			//ASSUMPTION: address is equal for all sms pieces. No need for cycle
			address = SmsMessage.createFromPdu((byte[]) extraPdus[0]).getOriginatingAddress();
			return address;
		}
		
		Log.d("ADDRESS", "no extras found, return null");
		return address;
	}
	
	public void sendNotification(String anAddress, String aMessage, Context context){
		Intent smsActivityIntent = new Intent(context, SmsActivity.class);
		smsActivityIntent.putExtra("ADDRESS", anAddress);
		smsActivityIntent.putExtra("MESSAGE", aMessage);
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(SmsActivity.class);
		stackBuilder.addNextIntent(smsActivityIntent);
		
		PendingIntent goToSms = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		Notification notif = new Notification.Builder(context)
				.setContentTitle(anAddress)
				.setContentText(aMessage)
				.setAutoCancel(true)
				.setContentIntent(goToSms)
				.setSmallIcon(R.drawable.ic_launcher).build();
		
		NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notifManager.notify(0, notif);
		
	}
	
}