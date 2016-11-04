package com.chaksiva.batasariandroidapp.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AuthenticationWebService extends AsyncTask {
	private String URL = "http://yvsivateja-009.appspot.com/authAndRegDevice.htm";
	Context context;

	@Override
	protected Object doInBackground(Object... params) {
		String message = "";
		try {
			this.context = (Context) params[0];
			Log.i("AuthenticationWebService", params[1].toString() + "-"
					+ params[2].toString() + "-" + params[3].toString() + "-"
					+ params[4].toString());
			message = authenticateAndGetPermission(params[1].toString(),
					params[2].toString(), params[3].toString(),
					params[4].toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	private String authenticateAndGetPermission(String companyId,
			String deviceModel, String phoneNumber, String deviceId) {
		Map<String, String> deviceInputs = new HashMap<String, String>();
		deviceInputs.put("deviceID", deviceId);
		deviceInputs.put("deviceName", deviceModel);
		deviceInputs.put("phoneNumber", phoneNumber);
		deviceInputs.put("companyIdentifier", companyId);
		deviceInputs.put("addtionalInfo", " ");

		return wsRequest(getJsonString(deviceInputs));
	}

	private String getJsonString(Map<String, String> deviceInputs) {
		Iterator<String> it = deviceInputs.keySet().iterator();
		int count = 0;
		String jsonString = "{";
		while (it.hasNext()) {
			String key = it.next();
			jsonString += "\"" + key + "\"";
			jsonString += ":" + "\"" + deviceInputs.get(key) + "\"";
			count++;
			if (count < deviceInputs.keySet().size())
				jsonString += ",";
		}
		jsonString += "}";
		return jsonString;
	}

	private String wsRequest(String jsonInput) {

		try {
			Log.i("AuthenticationWebService", "Json Input : " + jsonInput);
			URL targetUrl = new URL(URL);
			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl
					.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type",
					"application/json");

			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(jsonInput.getBytes());
			outputStream.flush();

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream()));

			String output;
			StringBuffer sb = new StringBuffer();
			Log.i("AuthenticationWebService", "Output from Server:\n");
			while ((output = responseBuffer.readLine()) != null) {
				sb.append(output);
			}

			httpConnection.disconnect();
			return sb.toString();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;

	}

}
