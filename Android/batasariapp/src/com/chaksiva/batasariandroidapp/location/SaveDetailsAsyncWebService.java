package com.chaksiva.batasariandroidapp.location;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class SaveDetailsAsyncWebService extends AsyncTask {
	private String URL = "http://yvsivateja-009.appspot.com/saveLocation.htm";

	Context context;

	@Override
	protected Object doInBackground(Object... params) {
		this.context = (Context) params[0];
		invokeSaveDetailsWS(params[1].toString(), params[2].toString(),
				params[3].toString());

		return null;
	}

	public void invokeSaveDetailsWS(String latlon, String deviceID,
			String addInfo) {
		String[] latlonArray = latlon.split(",");
		String mapURL = "http://maps.google.com/?q=" + latlon;
		Log.i("invokeSaveDetailsWS", "Calling InvokeSaveDetailsWS -Start");

		try {

			sendDeviceLocation(deviceID, latlonArray[0], latlonArray[1],
					mapURL, addInfo);
			String previousRequests = readFile();
			Log.i("invokeSaveDetailsWS", "Previous Requests : "
					+ previousRequests);
			if (previousRequests != "") {
				Log.i("invokeSaveDetailsWS", "Parsing Previous requests : "
						+ previousRequests);
				deleteFile();
				String[] completeRequests = previousRequests.split(";");
				String failedRequests = "";
				int processFailedReqCount = 0;
				for (int row = 0; row < completeRequests.length; row++) {
					String[] prvReq = completeRequests[row].split("`");
					if (prvReq.length > 0) {
						latlonArray = prvReq[0].split(",");
						mapURL = "http://maps.google.com/?q=" + prvReq[0];
						if (!sendPreviousDeviceLocation(prvReq[1],
								latlonArray[0], latlonArray[1], mapURL,
								prvReq[2])) {

							failedRequests += completeRequests[row] + ";";
						} else {
							processFailedReqCount++;
						}
					}
				}
				if (completeRequests.length > processFailedReqCount) {
					writeFailedRequestsToFile(failedRequests);
				}
			}
		} catch (IOException e) {
			String msg = e.getMessage();
			if (msg == null) {
				msg = "No msg to display";
			}
			Log.e("Network I/O error - ", msg);
			writeToFile(latlon, deviceID, addInfo);
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg == null) {
				msg = "No msg to display";
			}
			Log.i("invokeSaveDetailsWS:Exception", msg);

		}
	}

	private void deleteFile() {
		File mydir = context.getApplicationContext().getDir("location_service",
				Context.MODE_PRIVATE);
		File file = new File(mydir + "/data.file");
		if (file.exists()) {
			file.delete();
		}

	}

	private String readFile() {
		FileInputStream fis = null;
		String sb = "";
		try {
			File mydir = context.getApplicationContext().getDir(
					"location_service", Context.MODE_PRIVATE);
			File file = new File(mydir + "/data.file");
			if (file.exists()) {

				fis = new FileInputStream(file);

				Log.i("readFile",
						"Total file size to read (in bytes) : "
								+ fis.available());

				int content;

				while ((content = fis.read()) != -1) {
					sb += (char) content;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb;
	}

	private void writeFailedRequestsToFile(String failedContent) {
		try {
			File mydir = context.getApplicationContext().getDir(
					"location_service", Context.MODE_PRIVATE);

			if (!mydir.exists()) {
				mydir.mkdir();
			}
			String content = "";
			File file = new File(mydir + "/data.file");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			Log.i("writeFailedRequestsToFile",
					"Done Writing to file "
							+ file.getAbsolutePath()
							+ " to process later. Reason(s) might be network failure while processing failed requests");
		} catch (IOException e) {
			Log.e("writeFailedRequestsToFile:Exception", "Error Creating File");
		}
	}

	private void writeToFile(String latlon, String deviceID, String addInfo) {
		try {
			File mydir = context.getApplicationContext().getDir(
					"location_service", Context.MODE_PRIVATE);
			Log.i("writeToFile", "Application Dir : " + mydir);
			if (!mydir.exists()) {
				Log.i("MainActivity", "Creating directory : " + mydir);
				mydir.mkdir();
			}
			String content = "";
			File file = new File(mydir + "/data.file");
			if (!file.exists()) {
				file.createNewFile();
			} else {
				content = ";";
			}
			content = latlon + "`" + deviceID + "`" + addInfo
					+ "\n Triggered at " + new Date();
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			Log.i("writeToFile",
					"Done Writing to file "
							+ file.getAbsolutePath()
							+ " to process later. Reason(s) might be network failure resulted in request writing to file");

		} catch (IOException e) {
			Log.e("writeToFile", "Error Creating File");
		}

	}

	private String sendDeviceLocation(String deviceId, String latitude,
			String longitude, String mapURL, String addInfo)
			throws IOException, RuntimeException {
		Map<String, String> deviceInputs = new HashMap<String, String>();
		deviceInputs.put("deviceId", deviceId);
		deviceInputs.put("latitude", latitude);
		deviceInputs.put("longitude", longitude);
		deviceInputs.put("mapURL", mapURL);
		deviceInputs.put("addInfo", addInfo);

		return wsRequest(getJsonString(deviceInputs));
	}

	private Boolean sendPreviousDeviceLocation(String deviceId,
			String latitude, String longitude, String mapURL, String addInfo) {
		try {
			Map<String, String> deviceInputs = new HashMap<String, String>();
			deviceInputs.put("deviceId", deviceId);
			deviceInputs.put("latitude", latitude);
			deviceInputs.put("longitude", longitude);
			deviceInputs.put("mapURL", mapURL);
			deviceInputs.put("addInfo", addInfo);
			wsRequest(getJsonString(deviceInputs));
			return true;
		} catch (IOException e) {
			return false;
		} catch (RuntimeException e) {
			return false;
		}

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

	private String wsRequest(String jsonInput) throws IOException,
			RuntimeException {

		Log.i("AuthenticationWebService", "Json Input : " + jsonInput);
		URL targetUrl = new URL(URL);
		HttpURLConnection httpConnection = (HttpURLConnection) targetUrl
				.openConnection();
		httpConnection.setDoOutput(true);
		httpConnection.setRequestMethod("POST");
		httpConnection.setRequestProperty("Content-Type", "application/json");

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

	}
}
