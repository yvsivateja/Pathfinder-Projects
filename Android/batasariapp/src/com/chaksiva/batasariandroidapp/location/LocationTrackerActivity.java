package com.chaksiva.batasariandroidapp.location;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaksiva.batasariandroidapp.R;
import com.chaksiva.batasariandroidapp.login.MainActivity;

public class LocationTrackerActivity extends ActionBarActivity {

	private final long SEC_IN_MILLISEC = 1000;
	private final long REPEAT_TIME_IN_SEC = 300;
	private final long REPEAT_TIME_IN_MILLISEC = REPEAT_TIME_IN_SEC
			* SEC_IN_MILLISEC;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_tracker);
		Button startButton = (Button) findViewById(R.id.btnStartService);
		startButton.setVisibility(View.GONE);

		Button stopButton = (Button) findViewById(R.id.btnStopService);
		stopButton.setVisibility(View.GONE);

		Intent intent = getIntent();
		Boolean canStart = intent.getBooleanExtra(
				MainActivity.ACTIVITY_PERMISSION_START, false);
		Boolean canStop = intent.getBooleanExtra(
				MainActivity.ACTIVITY_PERMISSION_STOP, false);

		if (canStart) {
			Log.i("LocationTrackerActivity", "can Start");
			startButton.setVisibility(View.VISIBLE);
		}
		if (canStop) {
			Log.i("LocationTrackerActivity", "can Stop");
			stopButton.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location_tracker, menu);
		return true;
	}

	// Method to start the service
	public void startService(View view) {
		Log.i("MainActivity", "startService Alarm set for sec(s)"
				+ REPEAT_TIME_IN_SEC);
		Intent myAlarm = new Intent(getApplicationContext(),
				AlarmReceiver.class);
		PendingIntent recurringAlarm = PendingIntent.getBroadcast(
				getApplicationContext(), 0, myAlarm,
				PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarms = (AlarmManager) this
				.getSystemService(Context.ALARM_SERVICE);
		Calendar updateTime = Calendar.getInstance();
		updateTime.setTimeInMillis(REPEAT_TIME_IN_MILLISEC);
		alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
				updateTime.getTimeInMillis(), REPEAT_TIME_IN_MILLISEC,
				recurringAlarm);

		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(4000); // As I am using LENGTH_LONG in Toast
					finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		startService(new Intent(getBaseContext(), LocationService.class));
		Toast.makeText(getApplicationContext(),
				"Location Tracking Started closing the application",
				Toast.LENGTH_LONG).show();
		thread.start();

	}

	// Method to stop the service
	public void stopService(View view) {
		Log.i("MainActivity", "stopService");

		String myDeviceModel = android.os.Build.MODEL;

		String deviceId = Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID);

		Object[] object = new Object[] { getApplicationContext(), "0.0,0.0",
				deviceId, myDeviceModel + "- User Requested to Stop Service" };

		new SaveDetailsAsyncWebService().execute(object);

		stopService(new Intent(getBaseContext(), LocationService.class));
		Intent myAlarm = new Intent(getApplicationContext(),
				AlarmReceiver.class);
		// myAlarm.putExtra("project_id",project_id); //put the SAME extras
		PendingIntent recurringAlarm = PendingIntent.getBroadcast(
				getApplicationContext(), 0, myAlarm,
				PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarms = (AlarmManager) getApplicationContext()
				.getSystemService(Context.ALARM_SERVICE);
		alarms.cancel(recurringAlarm);
		Toast.makeText(getApplicationContext(), "Location Tracking Stopped",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.refresh) {
			finish();
			Intent MainActivity = new Intent(this, MainActivity.class);
			startActivity(MainActivity);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
