package com.chaksiva.batasariandroidapp.location;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.android.gms.location.LocationClient;

public class LocationService extends Service {
	LocationClient mLocationClient;
	public static final String BROADCAST_ACTION = "Hello World";
	public LocationManager locationManager;
	// flag for GPS status
	private boolean isGPSEnabled = false;

	// flag for network status
	private boolean isNetworkEnabled = false;

	private String lastAddress = "";
	Intent intent;
	int counter = 0;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Let it continue running until it is stopped.
		
		Log.i("LocationService-onDestroy", "onStartCommand");
		double[] latlon = { 0.0, 0.0 };
		String myDeviceModel = android.os.Build.MODEL;
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String deviceId = Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID);
		String number = tm.getLine1Number();
		try {
			Log.i("LocationService - onStartCommand", "onStartCommand");
			latlon = getGPS();

			if (latlon != null) {

				String latlonString = Double.toString(latlon[0]) + ","
						+ Double.toString(latlon[1]);

				Object[] object = new Object[] { getApplicationContext(),
						latlonString, deviceId, myDeviceModel + "-" + number };

				new SaveDetailsAsyncWebService().execute(object);

			} else {
				if (!lastAddress.contentEquals("Error_NULL_Location")) {
					lastAddress = "Error_NULL_Location";
					Object[] stringObject = new Object[] {
							getApplicationContext(),
							"0.0,0.0",
							deviceId,
							myDeviceModel + "-" + number
									+ "- Location Not tracked" };
					Log.i("LocationService-location not tracked",
							"Network not available");
					new SaveDetailsAsyncWebService().execute(stringObject);
				}
			}

		} catch (Exception e) {
			if (!lastAddress.contentEquals(Double.toString(latlon[0]))) {
				lastAddress = Double.toString(latlon[0]);
				String latlonString = Double.toString(latlon[0]) + ","
						+ Double.toString(latlon[1]);
				Object[] stringObject = new Object[] { getApplicationContext(),
						latlonString, deviceId,
						myDeviceModel + "-" + number + "- Address Not tracked" };
				Log.i("LocationService-location not tracked",
						"Network not available");
				new SaveDetailsAsyncWebService().execute(stringObject);
				Log.e("LocationService-ERROR", e.getMessage());
				e.printStackTrace();
			}
		}

		return START_STICKY;
	}

	private double[] getGPS() {
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

		// getting network status
		isNetworkEnabled = lm
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (!isNetworkEnabled && !isGPSEnabled) {
			return null;
		}
		Log.i("LocationService - getGPS", "isGPSEnabled : " + isGPSEnabled);
		Log.i("LocationService - getGPS", "isNetworkEnabled : "
				+ isNetworkEnabled);

		List<String> providers = lm.getProviders(true);

		/*
		 * Loop over the array backwards, and if you get an accurate location,
		 * then break out the loop
		 */
		Location l = null;

		for (int i = providers.size() - 1; i >= 0; i--) {
			l = lm.getLastKnownLocation(providers.get(i));
			if (l != null)
				break;
		}

		double[] gps = new double[3];
		if (l != null) {
			gps[0] = l.getLatitude();
			gps[1] = l.getLongitude();

		}
		Log.i("LocationService - getGPS", "GPS : " + gps);
		return gps;
	}

	@Override
	public void onCreate() {
		Log.i("LocationService-onDestroy", "onCreate");
		super.onCreate();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("LocationService-onDestroy", "onDestroy");
		// Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}

}
