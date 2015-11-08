package com.zerogroup.applibrary;

import java.util.Scanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Provides location
 * 
 * @author Giac
 *
 */
public class LocationOperator implements LocationListener {

	private String message;
	private Context context;
	private final String LOCATION_REQUEST = "sendLoc123"; // random location
															// token
	private final String LOCATION_SMS = "myLoc123";
	private LocationManager locationManager;
	private String provider;
	private String latitude;
	private String longitude;
	private String address;

	public LocationOperator(Context aContext, String aMessage, String anAddress) {
		message = aMessage;
		context = aContext;
		address = anAddress;
		Log.d("LOC OP", "LocationOperator initialized");
	}

	public boolean isLocationMessage() {
		Scanner scan = new Scanner(message);
		String firstToken = scan.next();
		Log.d("LOCATIONMESSAGE", "first is " + firstToken);
		
		if (firstToken.equals(LOCATION_SMS)) {
			latitude = scan.next();
			longitude = scan.next();
			scan.close();
			return true;
		}
		return false;
	}
	
	public void showLocationOnGoogleMaps(){
			Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + ",q=" + latitude + "," + longitude + "(" + address +")");
			Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
			mapIntent.setPackage("com.google.android.apps.maps");
			mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(mapIntent);
		
	}

	public boolean isLocationRequest() {
		Scanner scan = new Scanner(message);
		String firstToken = scan.next();
		scan.close();
		if (firstToken.equals(LOCATION_REQUEST)) {
			return true;
		}
		return false;
	}

	public String getLocation() {
		String myLocation = "";

		if (isLocationRequest()) { 

			// Get the location manager
			locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

			// boolean enabled =
			// isProviderEnabled(LocationManager.GPS_PROVIDER);
			// SERVICE????? WTF IS SERVICE?

			Criteria criteria = new Criteria();
			provider = locationManager.getBestProvider(criteria, false);
			Log.d("PROVIDER", "Provider " + provider + " has been selected.");

			// check
			if (!locationManager.isProviderEnabled(provider)) {
				Toast.makeText(context, provider + " provider disabled", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, provider + " provider enabled", Toast.LENGTH_SHORT).show();
			}

			Location location = locationManager.getLastKnownLocation(provider);
			// TODO: check location enabled

			if (location == null) {
				Log.d("LOCATION", "Location not available");
				Toast.makeText(context, "Location not avaivable", Toast.LENGTH_SHORT).show();

				return null;

			} else {
				Log.d("PROVIDER", "Provider " + provider + " has been selected.");
				latitude = String.valueOf(location.getLatitude());
				longitude = String.valueOf(location.getLongitude());
				myLocation = LOCATION_SMS + " " + latitude + ' ' + longitude;
				Log.d("LATITUDE", latitude);
				Log.d("LONGITUDE", longitude);
				Toast.makeText(context, "Location AVAIVABLE", Toast.LENGTH_SHORT).show();
				Log.d("LOCATION", "getLogation run. LOCATION: " + myLocation);
				return myLocation;
			}
		}

		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(context, "Enabled new provider " + provider, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(context, "Disabled provider " + provider, Toast.LENGTH_SHORT).show();

	}
}
