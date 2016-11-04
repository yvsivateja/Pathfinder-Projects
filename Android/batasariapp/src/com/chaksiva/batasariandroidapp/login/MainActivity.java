package com.chaksiva.batasariandroidapp.login;

import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chaksiva.batasariandroidapp.R;
import com.chaksiva.batasariandroidapp.location.LocationTrackerActivity;

public class MainActivity extends ActionBarActivity {

	public static final String PREFS_NAME = "batsaripref";
	public static final String ACTIVITY_PERMISSION_START = "com.chaksiva.batasariandroidapp.login.MainActivity.BUTTONSTART";
	public static final String ACTIVITY_PERMISSION_STOP = "com.chaksiva.batasariandroidapp.login.MainActivity.BUTTONSTOP";
	public static final String ACTIVITY_PERMISSION_UNINSTALL = "com.chaksiva.batasariandroidapp.login.MainActivity.BUTTONUNINSTALL";

	private EditText companyIdentifier = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

		try {
			if (hasLoggedIn) {
				Log.i("MainActivity-onCreate", "Has Logged in " + hasLoggedIn);
				String companyIdentity = settings.getString(
						"company_identifier", "");
				String myDeviceModel = android.os.Build.MODEL;
				TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
				String deviceId = Secure.getString(this.getContentResolver(),
						Secure.ANDROID_ID);
				String number = tm.getLine1Number();
				if (number == null) {
					number = "";
				}
				Log.i("MainActivity", companyIdentity + "-" + myDeviceModel
						+ "-" + number + "-" + deviceId);
				Object[] stringObject = new Object[] { getApplicationContext(),
						companyIdentity, myDeviceModel, number, deviceId };

				@SuppressWarnings("unchecked")
				Object result = new AuthenticationWebService().execute(
						stringObject).get();

				if (result != null) {
					Log.i("MainActivity", result.toString());
					String[] returnValue = result.toString().split(",");
					if (returnValue[0].contentEquals("failure")) {
						Toast.makeText(
								getApplicationContext(),
								"Wrong company identifier.. Check your internet connection.",
								Toast.LENGTH_LONG).show();
					} else if (returnValue[0].contentEquals("success")) {

						Boolean canUninstall = returnValue[3]
								.contentEquals("Y") ? true : false;

						if (canUninstall) {
							deleteApplication();
							return;
						}

						Boolean canStart = returnValue[1].contentEquals("Y") ? true
								: false;
						Boolean canStop = returnValue[2].contentEquals("Y") ? true
								: false;

						Intent locationTrackerIntent = new Intent(this,
								LocationTrackerActivity.class);
						locationTrackerIntent.putExtra(
								ACTIVITY_PERMISSION_START, canStart);
						locationTrackerIntent.putExtra(
								ACTIVITY_PERMISSION_STOP, canStop);

						startActivity(locationTrackerIntent);
						finish();

					}

				} else {
					companyIdentifier = (EditText) findViewById(R.id.company_id);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		companyIdentifier = (EditText) findViewById(R.id.company_id);

	}

	@SuppressWarnings("unchecked")
	public void login(View view) {
		try {

			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			String myDeviceModel = android.os.Build.MODEL;
			TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			String deviceId = Secure.getString(this.getContentResolver(),
					Secure.ANDROID_ID);
			String number = tm.getLine1Number();
			Log.i("Batasari", "Company Identifier : "
					+ companyIdentifier.getText().toString());
			if (number == null) {
				number = "";
			}
			Log.i("MainActivity", companyIdentifier.getText().toString() + "-"
					+ myDeviceModel + "-" + number + "-" + deviceId);
			Object[] stringObject = new Object[] { getApplicationContext(),
					companyIdentifier.getText(), myDeviceModel, number,
					deviceId };

			Object result = new AuthenticationWebService()
					.execute(stringObject).get();

			if (result != null) {
				Log.i("MainActivity", result.toString());
				String[] returnValue = result.toString().split(",");
				if (returnValue[0].contentEquals("failure")) {
					Toast.makeText(
							getApplicationContext(),
							"Wrong company identifier.. Check your internet connection.",
							Toast.LENGTH_LONG).show();
				} else if (returnValue[0].contentEquals("success")) {
					editor.putBoolean("hasLoggedIn", true);
					editor.putString("company_identifier", companyIdentifier
							.getText().toString());
					Log.i("canStart", returnValue[1]);
					Log.i("canStop", returnValue[2]);
					Log.i("canUninstall", returnValue[3]);
					Boolean canStart = returnValue[1].contentEquals("Y") ? true
							: false;
					Boolean canStop = returnValue[2].contentEquals("Y") ? true
							: false;
					Boolean canUninstall = returnValue[3].contentEquals("Y") ? true
							: false;

					if (canUninstall) {
						deleteApplication();
						return;
					}
					editor.putBoolean("canStart", canStart);
					editor.putBoolean("canStop", canStop);
					editor.putBoolean("canUninstall", canUninstall);

					editor.commit();

					Intent locationTrackerIntent = new Intent(this,
							LocationTrackerActivity.class);
					locationTrackerIntent.putExtra(ACTIVITY_PERMISSION_START,
							canStart);
					locationTrackerIntent.putExtra(ACTIVITY_PERMISSION_STOP,
							canStop);
					locationTrackerIntent.putExtra(
							ACTIVITY_PERMISSION_UNINSTALL, canUninstall);
					startActivity(locationTrackerIntent);
					finish();

				}
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteApplication() {
		int nonMarketAppsUninstall = 0;
		Float apiVersion = getAPIVerison();

		Log.i("MainActivity", "INSTALL_NON_MARKET_APPS : "
				+ nonMarketAppsUninstall);

		Log.i("MainActivity", "Uninstalling Application");
		Uri packageUri = Uri.parse("package:com.chaksiva.batasariandroidapp");
		Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageUri);
		startActivity(uninstallIntent);
		finish();
		return;
		// System.exit(0);

	}

	@SuppressLint("UseValueOf")
	public static float getAPIVerison() {

		Float f = null;
		try {
			StringBuilder strBuild = new StringBuilder();
			strBuild.append(android.os.Build.VERSION.RELEASE.substring(0, 2));
			f = new Float(strBuild.toString());
		} catch (NumberFormatException e) {
			Log.e("", "error retriving api version" + e.getMessage());
		}
		Log.i("MainActivity", "Api version : " + f.floatValue());
		return f.floatValue();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
