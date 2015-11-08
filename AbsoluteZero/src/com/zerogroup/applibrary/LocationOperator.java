package com.zerogroup.applibrary;



import java.util.Scanner;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

	public LocationOperator(Context aContext, String aMessage) {
		message = aMessage;
		context = aContext;
		Log.d("LOC OP", "LocationOperator initialized");
	}

	public boolean isLocationMessage() {
		Scanner scan = new Scanner(message);
		String firstToken = scan.next();
		if (firstToken.equals(LOCATION_SMS)) {
			return true;
		}
		return false;
	}

	public boolean isLocationRequest() {
		Scanner scan = new Scanner(message);
		String firstToken = scan.next();
		if (firstToken.equals(LOCATION_REQUEST)) {
			return true;
		}
		return false;
	}

	public String getLocation() {
		String myLocation = "";
		// Get the location manager
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		//boolean enabled = isProviderEnabled(LocationManager.GPS_PROVIDER);
		//SERVICE????? WTF IS SERVICE?
		
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Log.d("PROVIDER", "Provider " + provider + " has been selected.");

		Location location = locationManager.getLastKnownLocation(provider);
		// TODO: check location enabled

		if (location == null) {
			Log.d("LOCATION", "Location not available");
			Toast.makeText(context, "Location not avaivable", Toast.LENGTH_SHORT).show();
			
			return null;

		} else {
			Log.d("PROVIDER", "Provider " + provider + " has been selected.");
			String lat = String.valueOf(location.getLatitude());
			String lon = String.valueOf(location.getLongitude());
			myLocation = LOCATION_SMS + " " + lat + ' ' + lon;
			Log.d("LATITUDE", lat);
			Log.d("LONGITUDE", lon);
			Toast.makeText(context, "Location AVAIVABLE", Toast.LENGTH_SHORT).show();
			

			return myLocation;

		}
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
